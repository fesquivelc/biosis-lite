/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import biz.juvitec.controladores.AsignacionHorarioControlador;
import biz.juvitec.controladores.AsignacionPermisoControlador;
import biz.juvitec.controladores.DetalleGrupoControlador;
import biz.juvitec.controladores.FeriadoControlador;
import biz.juvitec.controladores.MarcacionControlador;
import biz.juvitec.controladores.RegistroAsistenciaControlador;
import biz.juvitec.controladores.TCAnalisisControlador;
import biz.juvitec.controladores.TCSistemaControlador;
import biz.juvitec.controladores.VacacionControlador;
import biz.juvitec.entidades.AsignacionHorario;
import biz.juvitec.entidades.AsignacionPermiso;
import biz.juvitec.entidades.DetalleGrupoHorario;
import biz.juvitec.entidades.DetalleRegistroAsistencia;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.Feriado;
import biz.juvitec.entidades.GrupoHorario;
import biz.juvitec.entidades.Horario;
import biz.juvitec.entidades.Jornada;
import biz.juvitec.entidades.Marcacion;
import biz.juvitec.entidades.RegistroAsistencia;
import biz.juvitec.entidades.TCAnalisis;
import biz.juvitec.entidades.TCSistema;
import biz.juvitec.entidades.Vacacion;
import com.personal.utiles.FechaUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class AnalisisAsistencia {

    private TCAnalisisControlador tcac;
    private FeriadoControlador fc;
    private AsignacionPermisoControlador apc;
    private VacacionControlador vc;
    private MarcacionControlador mc;
    private TCSistemaControlador tcsc;
    private DetalleGrupoControlador dgc;
    private AsignacionHorarioControlador ahc;
    private RegistroAsistenciaControlador rac;

    private final int MIN_FIN_MARCACION = 300;

    public AnalisisAsistencia() {
        iniciar();
    }

    private void iniciar() {
        tcac = new TCAnalisisControlador();
        fc = new FeriadoControlador();
        apc = new AsignacionPermisoControlador();
        vc = new VacacionControlador();
        mc = new MarcacionControlador();
        dgc = new DetalleGrupoControlador();
        ahc = new AsignacionHorarioControlador();
        rac = new RegistroAsistenciaControlador();
        tcsc = TCSistemaControlador.getInstance();
    }

    private TCAnalisis obtenerPuntoPartida(Empleado empleado) {
        TCAnalisis partida = tcac.buscarPorId(empleado.getNroDocumento());
        if (partida == null) {
            partida = new TCAnalisis();
            TCSistema sistema = tcsc.buscarPorId("BIOSIS");
            Date contrato = empleado.getFechaInicioContrato();
            Date fechaCero = sistema.getFechaCero();

            if (contrato.compareTo(fechaCero) < 0) {
                partida.setFecha(fechaCero);
                partida.setHora(sistema.getHoraCero());
            } else {
                partida.setFecha(contrato);
                partida.setHora(contrato);
            }
        }
        return partida;
    }

    private TCAnalisis obtenerPuntoLlegada() {
        TCAnalisis llegada = new TCAnalisis();
        Date fechaHoraActual = new Date();
        llegada.setFecha(FechaUtil.soloFecha(fechaHoraActual));
        llegada.setHora(FechaUtil.soloHora(fechaHoraActual));

        return llegada;
    }

    public List<RegistroAsistencia> analizarEmpleados(List<Empleado> empleados) {
        //LA FECHA Y HORA DE FINAL DEL ANALISIS ES LA MISMA DE LA CONSULTA
        List<RegistroAsistencia> registros = new ArrayList<>();
        TCAnalisis llegada = obtenerPuntoLlegada();
        for (Empleado empleado : empleados) {
            //OBTENEMOS LA FECHA Y HORA DE PARTIDA DEL ANALISIS
            TCAnalisis partida = obtenerPuntoPartida(empleado);

            //OBTENEMOS LOS HORARIOS DEL EMPLEADO
            List<Horario> horarios = obtenerHorarios(empleado);

            //ANALIZAMOS POR HORARIO
            for (Horario horario : horarios) {

                registros.addAll(analizarHorario(empleado, horario, partida, llegada));
            }
            if (!registros.isEmpty()) {
                this.rac.guardarLote(registros);
                llegada.setEmpleado(empleado.getNroDocumento());
                this.tcac.modificar(llegada);
            }

            registros.clear();
        }
        return registros;
    }

    public List<Horario> obtenerHorarios(Empleado empleado) {
        //PRIMERO OBTENEMOS LOS GRUPOS HORARIOS ASIGNADOS

        List<DetalleGrupoHorario> detalles = dgc.buscarXEmpleado(empleado);
        List<GrupoHorario> grupos = new ArrayList<>();
        List<Horario> horarios = new ArrayList<>();
        List<AsignacionHorario> asignaciones = new ArrayList<>();

        for (DetalleGrupoHorario detalle : detalles) {
            grupos.add(detalle.getGrupoHorario());
        }

        if (!grupos.isEmpty()) {
            asignaciones.addAll(ahc.buscarXGrupos(grupos));
        }

        asignaciones.addAll(ahc.buscarXEmpleado(empleado));

        if (!asignaciones.isEmpty()) {
            for (AsignacionHorario asignacion : asignaciones) {
                horarios.add(asignacion.getHorario());
            }
        }

        return horarios;
    }

    public List<Horario> obtenerHorarios(Empleado empleado, Date fechaInicio, Date fechaFin) {
        //PRIMERO OBTENEMOS LOS GRUPOS HORARIOS ASIGNADOS

        List<DetalleGrupoHorario> detalles = dgc.buscarXEmpleado(empleado, fechaInicio, fechaFin);
        List<GrupoHorario> grupos = new ArrayList<>();
        List<Horario> horarios = new ArrayList<>();
        List<AsignacionHorario> asignaciones = new ArrayList<>();

        for (DetalleGrupoHorario detalle : detalles) {
            grupos.add(detalle.getGrupoHorario());
        }

        if (!grupos.isEmpty()) {
            asignaciones.addAll(ahc.buscarXGrupos(grupos));
        }

        asignaciones.addAll(ahc.buscarXEmpleado(empleado));

        if (!asignaciones.isEmpty()) {
            for (AsignacionHorario asignacion : asignaciones) {
                horarios.add(asignacion.getHorario());
            }
        }

        return horarios;
    }

    private List<RegistroAsistencia> analizarHorario(Empleado empleado, Horario horario, TCAnalisis partida, TCAnalisis llegada) {
        List<RegistroAsistencia> registros = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        Date fInicio = new Date(partida.getFecha().getTime());
        Date fFin = new Date(llegada.getFecha().getTime());

        Date hInicio = new Date(partida.getHora().getTime());
        Date hFin = new Date(llegada.getHora().getTime());

        RegistroAsistencia registro;

        while (fInicio.compareTo(fFin) < 0) {
            //COMAPRAMOS QUE SE ENCUENTRE ENTRE LAS FECHAS DEL HORARIO
            if (fInicio.compareTo(horario.getFechaInicio()) >= 0
                    && fInicio.compareTo(horario.getFechaFin()) <= 0) {
                System.out.println("FECHA INICIO: " + fInicio.toString() + " FECHA FIN: " + fFin.toString());
                registro = new RegistroAsistencia();
                registro.setFecha(fInicio);
                registro.setEmpleado(empleado.getNroDocumento());
                registro.setHorario(horario);

                AsignacionPermiso permisoXFecha = this.apc.buscarXDia(empleado.getNroDocumento(), fInicio);

                if (permisoXFecha != null) {
                    //SE GUARDA EL REGISTRO COMO UN PERMISO
                    registro.setPermiso(permisoXFecha.getPermiso());
                    registro.setTipoAsistencia('P');
                } else {
                    Vacacion vacacion = this.vc.buscarXDia(empleado.getNroDocumento(), fInicio);

                    if (vacacion != null) {
                        //SE GUARDA EL REGISTRO COMO VACACION
                        registro.setVacacion(vacacion);
                        registro.setTipoAsistencia('V');

                    } else {
                        boolean diaLaboral = isDiaLaboral(fInicio, horario);
                        if (diaLaboral) {
                            //TOMAMOS EN CUENTA QUE SEA FERIADO
                            Feriado feriado = this.fc.buscarXDia(fInicio);
                            if (feriado != null) {
                                //SE REGISTRA COMO FERIADO
                                registro.setFeriado(feriado);
                                registro.setTipoAsistencia('E'); //RECORDAR QUE E PERTENECE A LOS FERIADOS
                            } else {
                                //TOMAMOS EN CUENTA EL ONOMASTICO
                                if (isOnomastico(empleado, fInicio)) {
                                    //SE REGISTRA COMO ONOMASTICO
                                } else {
                                    //SE PROCEDE AL ANALISIS DE LA JORNADA
                                    registro = analizarJornada(empleado, horario.getJornada(), fInicio, hInicio, fFin, hFin);
                                    if (registro != null) {
                                        registro.setHorario(horario);
                                    }
                                }
                            }
                        } else if (isOnomastico(empleado, fInicio)) {
                            //SE BUSCA EL DIA LABORAL MAS CERCANO PARA ASIGNARLE EL PERMISO POR ONOMASTICO
                            //Y SE AGREGA AL REGISTRO
                        } else {
                            //NO HAY SUCESO SUSCEPTIBLE A REGISTRO
                            registro = null;
                        }
                    }
                }// FIN DEL ELSE PRINCIPAL

                if (registro != null) {
                    registros.add(registro);
                }
            }

            cal.setTime(fInicio);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            fInicio = cal.getTime();

            cal.setTime(hInicio);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            hInicio = cal.getTime();
        }// FIN DEL WHILE
        return registros;
    }

    private boolean isDiaLaboral(Date fInicio, Horario horario) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fInicio);
        int nDia = cal.get(Calendar.DAY_OF_WEEK);
        //LOS DIAS VAN DESDE EL 1 AL 7 Y CUENTAN DESDE DOMINGO A SABADO
        switch (nDia) {
            case 1:
                return horario.isDomingo();
            case 2:
                return horario.isLunes();
            case 3:
                return horario.isMartes();
            case 4:
                return horario.isMiercoles();
            case 5:
                return horario.isJueves();
            case 6:
                return horario.isViernes();
            case 7:
                return horario.isSabado();
            default:
                return false;
        }
    }

    private boolean isOnomastico(Empleado empleado, Date fInicio) {
        Calendar calEmpleado = Calendar.getInstance();
        Calendar calFecha = Calendar.getInstance();

        calEmpleado.setTime(empleado.getFechaNacimiento());
        calFecha.setTime(fInicio);

        calEmpleado.set(Calendar.YEAR, calFecha.get(Calendar.YEAR));

        return calEmpleado.getTime().compareTo(fInicio) == 0;
    }

//    private long tardanza(Date hora, Date horaTolerancia) {
//        long resultado = hora.getTime() - horaTolerancia.getTime();
//        if (resultado < 0) {
//            return Math.abs(resultado);
//        } else {
//            return 0;
//        }
//    }
    private RegistroAsistencia analizarJornada(Empleado empleado, Jornada jornada, Date fInicio, Date hInicio, Date fFin, Date hFin) {
        //COMPARAMOS =D
        Calendar cal = Calendar.getInstance();
        cal.setTime(jornada.getTurnoHS());
        cal.add(Calendar.MINUTE, MIN_FIN_MARCACION);
        Date horaMaximaSalida = cal.getTime();

        RegistroAsistencia registro = null;

        if (FechaUtil.compararFechaHora(fInicio, hInicio, fInicio, horaMaximaSalida) <= 0
                && FechaUtil.compararFechaHora(fFin, hFin, fInicio, horaMaximaSalida) >= 0) {
            //ANALIZAMOS =D
            registro = new RegistroAsistencia();
            BigDecimal tardanzaTotal = new BigDecimal(0);
            BigDecimal trabajoTotal = new BigDecimal(0);

            Date ocupaEntrada = null;
            Date ocupaSalida = null;
            boolean ocupaRefrigerio = false;
            boolean refrigerioEspecial = jornada.isRefrigerioEspecial();

            Date turnoHoraEntrada = null;
            Date turnoHoraSalida = null;

            List<DetalleRegistroAsistencia> detalles = new ArrayList<>();

            registro.setEmpleado(empleado.getNroDocumento());
            registro.setFecha(fInicio);

            List<AsignacionPermiso> permisosXHora = apc.obtenerPermisosXHora(empleado.getNroDocumento(), fInicio, jornada.getDesdeHE(), horaMaximaSalida);

            //SE PROCEDE A ANALIZAR LOS PERMISOS
            BigDecimal diferenciaTotalPermisos = new BigDecimal(0);
            for (AsignacionPermiso asignacion : permisosXHora) {
                //SE ANALIZA PERMISO A PERMISO

                DetalleRegistroAsistencia detallePermiso = new DetalleRegistroAsistencia();
                detallePermiso.setRegistroAsistencia(registro);
                detallePermiso.setTipoRegistro('P');
                detallePermiso.setPermiso(asignacion.getPermiso());

                //PARA ANALIZAR UN PERMISO TENEMOS
                cal.setTime(asignacion.getPermiso().getHoraInicio());
                cal.add(Calendar.MINUTE, 30);
                Date horaFinPermiso = cal.getTime();
                Marcacion marcacionInicioPermiso = null;
                if (asignacion.getPermiso().isCubreEntrada()) {
                    marcacionInicioPermiso = new Marcacion();
                    marcacionInicioPermiso.setHora(jornada.getTurnoHE());

                } else if (asignacion.getPermiso().isCubreSalida()) {
                    //EN CASO DE QUE EL PERMISO CUBRA LA SALIDA DEL EMPLEADO
                    ocupaSalida = asignacion.getPermiso().getHoraInicio();
                } else {
                    marcacionInicioPermiso = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, asignacion.getPermiso().getHoraInicio(), horaFinPermiso);
                }

                Marcacion marcacionFinPermiso = null;

                if (marcacionInicioPermiso != null) {

                    detallePermiso.setHoraInicio(marcacionInicioPermiso.getHora());
                    Long difPermiso = marcacionInicioPermiso.getHora().getTime() - asignacion.getPermiso().getHoraInicio().getTime();

                    cal.setTime(marcacionInicioPermiso.getHora());
                    cal.add(Calendar.SECOND, 10);

                    Date horaInicioPermiso = cal.getTime();

                    //AGREGAMOS A LA HORA DE SALIDA 
                    cal.setTime(asignacion.getPermiso().getHoraFin());
                    cal.add(Calendar.MILLISECOND, difPermiso.intValue());

                    horaFinPermiso = cal.getTime();

                    if (asignacion.getPermiso().isCubreSalida()) {
                        marcacionFinPermiso = new Marcacion();
                        marcacionFinPermiso.setHora(jornada.getTurnoHS());
                    } else {
                        marcacionFinPermiso = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, horaInicioPermiso, horaMaximaSalida);
                    }

                    BigDecimal tardanzaPermisoMin = null;
                    if (marcacionFinPermiso != null && !asignacion.getPermiso().isCubreSalida()) {
                        if (asignacion.getPermiso().isCubreEntrada()) {
                            ocupaEntrada = marcacionFinPermiso.getHora();
                        }
                        detallePermiso.setHoraFin(marcacionFinPermiso.getHora());
                        tardanzaPermisoMin = tardanzaMin(marcacionFinPermiso.getHora(), horaFinPermiso);
                        detallePermiso.setMinTardanza(tardanzaPermisoMin);
                        tardanzaTotal.add(tardanzaPermisoMin);

                    }

                    diferenciaTotalPermisos.add((detallePermiso.getHoraInicio() != null && detallePermiso.getHoraFin() != null) ? tardanzaMin(detallePermiso.getHoraInicio(), detallePermiso.getHoraFin()) : new BigDecimal(0));
//                    else {
//                        //SE TOMA COMO TARDANZA TODO EL DIA
//                        detallePermiso.setHoraFin(jornada.getTurnoHS());
//                        tardanzaPermisoMin = tardanzaMin(jornada.getTurnoHS(), horaFinPermiso);
//                    }

                }

                detalles.add(detallePermiso);
            }//FIN DEL {for} ANALISIS DE LOS PERMISOS

            //ANALISIS DE LOS DEMAS DETALLES
            //INICIO Y FIN DEL TURNO
            if (ocupaEntrada != null) {
                turnoHoraEntrada = ocupaEntrada;
            } else {
                Marcacion marcacionInicioTurno = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getDesdeHE(), jornada.getTardanzaHE());
                turnoHoraEntrada = (marcacionInicioTurno != null) ? marcacionInicioTurno.getHora() : null;
            }
            if (ocupaSalida != null) {
                turnoHoraSalida = ocupaSalida;
            } else {
                Marcacion marcacionFinTurno = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getTurnoHS(), horaMaximaSalida);
                turnoHoraSalida = (marcacionFinTurno != null) ? marcacionFinTurno.getHora() : null;
            }
            char tipoAsistencia;
            BigDecimal diferenciaTurnoES = (turnoHoraEntrada != null && turnoHoraSalida != null) ? tardanzaMin(turnoHoraEntrada, turnoHoraSalida) : null;
            BigDecimal tardanzaEntradaTurno = (turnoHoraEntrada != null) ? tardanzaMin(turnoHoraEntrada, jornada.getToleranciaHE()) : new BigDecimal(0);

            tardanzaTotal.add(tardanzaEntradaTurno);

            if (turnoHoraEntrada == null || turnoHoraSalida == null) {
                tipoAsistencia = 'F';
            } else if (tardanzaEntradaTurno.compareTo(new BigDecimal(0)) > 0) {
                tipoAsistencia = 'T';
            } else {
                tipoAsistencia = 'R';
            }

            DetalleRegistroAsistencia detalleTurno = new DetalleRegistroAsistencia();
            detalleTurno.setRegistroAsistencia(registro);
            detalleTurno.setHoraInicio(turnoHoraEntrada);
            detalleTurno.setHoraFin(turnoHoraSalida);
            detalleTurno.setOrden(0);
            detalleTurno.setMinTardanza(tardanzaEntradaTurno);
            detalleTurno.setTipoRegistro('T');
            detalleTurno.setResultado(tipoAsistencia);

            detalles.add(detalleTurno);

            //ANALISIS DE REFRIGERIO =D
            Date refrigerioHoraInicio = null;
            Date refrigerioHoraFin = null;
            Date refrigerioHoraFinEsperada = null;
            Date refrigerioHoraTolerancia = null;
            BigDecimal tardanzaRefrigerio = null;
            char tipoAsistenciaRefrigerio;
            Marcacion marcacionInicioRefrigerio = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getRefrigerioHS(), jornada.getRefrigerioHE());

            if (marcacionInicioRefrigerio != null) {
                refrigerioHoraInicio = marcacionInicioRefrigerio.getHora();
                if (jornada.isRefrigerioEspecial()) {
                    Long diferenciaMilis = jornada.getRefrigerioHS().getTime() - refrigerioHoraInicio.getTime();
//                    Long diferenciaMin = diferenciaMilis / (1000 * 60);
                    if (diferenciaMilis > jornada.getMinRefrigerio() * 60 * 1000) {
                        refrigerioHoraFinEsperada = jornada.getRefrigerioHE();

                    } else {
                        cal.setTime(refrigerioHoraInicio);
                        cal.add(Calendar.MINUTE, jornada.getMinRefrigerio());
                        refrigerioHoraFinEsperada = cal.getTime();
                    }
                    refrigerioHoraTolerancia = refrigerioHoraFinEsperada;
                    //ANALIZAMOS LA DIFERENCIA ENTRE LA HORA DE INICIO Y LA HORA DE FIN DEL PERMISSO

                } else {
                    refrigerioHoraFinEsperada = jornada.getRefrigerioHE();
                    refrigerioHoraTolerancia = jornada.getToleranciaRefrigerioHE();
                }

                //BUSCAMOS LA HORA DE ENTRADA DEL REFRIGERIO
                cal.setTime(refrigerioHoraInicio);
                cal.add(Calendar.MINUTE, 1);
                Marcacion marcacionFinRefrigerio = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, cal.getTime(), jornada.getTurnoHS());

                if (marcacionFinRefrigerio != null) {
                    refrigerioHoraFin = marcacionFinRefrigerio.getHora();
                    tardanzaRefrigerio = tardanzaMin(refrigerioHoraFin, refrigerioHoraFinEsperada);
                    tardanzaTotal.add(tardanzaRefrigerio);
                }

            }//FIN DEL ANALISIS DE REFRIGERIO

            if (refrigerioHoraInicio == null || refrigerioHoraFin == null) {
                tipoAsistenciaRefrigerio = 'F';
            } else if (tardanzaRefrigerio.compareTo(new BigDecimal(0)) > 0) {
                tipoAsistenciaRefrigerio = 'T';
            } else {
                tipoAsistenciaRefrigerio = 'R';
            }

            DetalleRegistroAsistencia detalleRefrigerio = new DetalleRegistroAsistencia();
            detalleRefrigerio.setHoraInicio(refrigerioHoraInicio);
            detalleRefrigerio.setHoraFin(refrigerioHoraFin);
            detalleRefrigerio.setRegistroAsistencia(registro);
            detalleRefrigerio.setResultado(tipoAsistenciaRefrigerio);
            detalleRefrigerio.setTipoRegistro('R');
            detalleRefrigerio.setOrden(1);
            detalleRefrigerio.setMinTardanza(tardanzaRefrigerio);
            detalles.add(detalleRefrigerio);

            char resultadoAsistencia = ' ';
            if (tipoAsistencia == 'F' || tipoAsistenciaRefrigerio == 'F') {
                resultadoAsistencia = 'F';
                trabajoTotal = new BigDecimal(0);
            } else if (tipoAsistencia == 'T' || tipoAsistenciaRefrigerio == 'T') {
                resultadoAsistencia = 'T';
            } else if (tipoAsistencia == 'R' && tipoAsistenciaRefrigerio == 'R') {
                resultadoAsistencia = 'R';
            }

            if (tipoAsistencia != 'F' && tipoAsistenciaRefrigerio != 'F') {
                tardanzaTotal = tardanzaTotal.add(detalleRefrigerio.getMinTardanza()).add(detalleTurno.getMinTardanza());
                Date ini;
//                if(detalleTurno.getMinTardanza() != null){
                ini = detalleTurno.getMinTardanza().compareTo(new BigDecimal(0)) == 0 ? jornada.getTurnoHE() : detalleTurno.getHoraInicio();
//                }
                Long difTrabajo = (detalleTurno.getHoraFin().getTime() - ini.getTime()) - (detalleRefrigerio.getHoraFin().getTime() - detalleRefrigerio.getHoraInicio().getTime());
                trabajoTotal = new BigDecimal(difTrabajo / (60 * 1000));
            } else if (tipoAsistencia == 'F') {
                detalleTurno.setMinTardanza(null);
                tardanzaTotal = null;
            }

            registro.setFecha(fInicio);
            registro.setTipoAsistencia(resultadoAsistencia);
            System.out.println("TARDANZA TOTAL: " + tardanzaTotal);
            registro.setMinTardanza(tardanzaTotal);
            registro.setMinTrabajados(trabajoTotal);
            registro.setDetalleRegistroAsistenciaList(detalles);
        }

        return registro;
    }

//    private RegistroAsistencia analizarJornada2(Empleado empleado, Jornada jornada, Date fInicio, Date hInicio, Date fFin, Date hFin){
//        
//    }
    
    public BigDecimal tardanzaMin(Date horaMarcada, Date horaComparar) {
        Long diferencia = horaMarcada.getTime() - horaComparar.getTime();
        if (diferencia > 0) {
//            System.out.println("MINUTOS: "+Double.parseDouble(diferencia+"")/(1000 * 60));
            return BigDecimal.valueOf(Double.parseDouble(diferencia.toString())/ (1000 * 60));
        } else {
            return BigDecimal.ZERO;
        }
    }
    
    private DetalleRegistroAsistencia analizarTurno(String empleadoDNI, Date fechaInicio, Date fechaFin, Date horaDesde, Date horaToleranciaInicio, Date horaMaximaInicio, Date horaFin, int minutosMaximoFin){
        DetalleRegistroAsistencia registroTurno = new DetalleRegistroAsistencia();
        registroTurno.setOrden(0);
        registroTurno.setTipoRegistro('R');
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaFin);
        calendar.add(Calendar.MINUTE, minutosMaximoFin);
        
        Date horaMaximaFin = calendar.getTime();
        
        Marcacion marcacionInicio = mc.buscarXFechaXhora(empleadoDNI, fechaInicio, horaDesde, horaMaximaInicio);
        
        char resultadoInicio;
        char resultadoFin;
        
        if(marcacionInicio == null){
            registroTurno.setHoraInicio(null);
            resultadoInicio = 'F';
        }else{
            BigDecimal tardanzaMin = tardanzaMin(marcacionInicio.getHora(), horaToleranciaInicio);
            registroTurno.setHoraInicio(marcacionInicio.getHora());
            if(tardanzaMin.compareTo(BigDecimal.ZERO) > 0){
                resultadoInicio = 'T';
            }else{
                resultadoInicio = 'R';
            }
            registroTurno.setMinTardanza(tardanzaMin);
        }
        
        calendar.setTime(horaFin);
        calendar.add(Calendar.MINUTE, minutosMaximoFin);
        Marcacion marcacionFin = mc.buscarXFechaXhora(empleadoDNI, fechaFin, horaFin, calendar.getTime());
        
        if(marcacionFin == null){
            registroTurno.setHoraFin(null);
            resultadoFin = 'F';
        }else{
            resultadoFin = 'R';
            registroTurno.setHoraFin(marcacionFin.getHora());            
        }
        
        if(resultadoInicio == 'F' || resultadoFin == 'F'){
            registroTurno.setResultado('F');
        }else{
            registroTurno.setResultado(resultadoInicio);
        }
        
        return registroTurno;
    }

    private DetalleRegistroAsistencia analizarRefrigerio(String empleadoDNI, Date fechaInicio, Date horaInicio, Date horaFin, Date horaMaximaFin, int minutosHoraInicio, int minutosRefrigerio) {
        DetalleRegistroAsistencia registroRefrigerio = new DetalleRegistroAsistencia();
        registroRefrigerio.setOrden(1);
        registroRefrigerio.setTipoRegistro('R');

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.MINUTE, minutosHoraInicio);

        Date horaMaximaInicio = calendar.getTime();

        Marcacion marcacionInicio = mc.buscarXFechaXhora(empleadoDNI, fechaInicio, horaInicio, horaMaximaInicio);

        if (marcacionInicio == null) {
            registroRefrigerio.setResultado('F');
            registroRefrigerio.setHoraInicio(null);
            registroRefrigerio.setHoraFin(null);
        } else {
            registroRefrigerio.setHoraInicio(marcacionInicio.getHora());
            calendar.setTime(marcacionInicio.getHora());
            calendar.add(Calendar.MINUTE, minutosRefrigerio);
            Date horaEsperadaFin;
            if (horaFin.before(calendar.getTime())) {
                horaEsperadaFin = calendar.getTime();
            } else {
                horaEsperadaFin = horaFin;
            }

            calendar.add(Calendar.MINUTE, -minutosRefrigerio);
            calendar.add(Calendar.SECOND, 1);
            Date limiteInferiorHoraFin = calendar.getTime();

            Marcacion marcacionFin = mc.buscarXFechaXhora(empleadoDNI, fechaInicio, limiteInferiorHoraFin, horaMaximaFin);

            if (marcacionFin == null) {
                registroRefrigerio.setHoraFin(null);
                registroRefrigerio.setResultado('F');
            } else {
                BigDecimal minTardanza = this.tardanzaMin(marcacionFin.getHora(), horaEsperadaFin);
                registroRefrigerio.setHoraFin(marcacionFin.getHora());
                registroRefrigerio.setMinTardanza(minTardanza);

                if (minTardanza.compareTo(BigDecimal.ZERO) > 0) {
                    registroRefrigerio.setResultado('T');
                } else {
                    registroRefrigerio.setResultado('R');
                }
            }
        }

        return registroRefrigerio;
    }
}
