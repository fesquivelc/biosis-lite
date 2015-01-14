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

    private List<RegistroAsistencia> analizarHorario(Empleado empleado, Horario horario, TCAnalisis partida, TCAnalisis llegada) {
        List<RegistroAsistencia> registros = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        Date fInicio = new Date(partida.getFecha().getTime());
        Date fFin = new Date(llegada.getFecha().getTime());

        Date hInicio = new Date(partida.getHora().getTime());
        Date hFin = new Date(llegada.getHora().getTime());

        RegistroAsistencia registro;

        while (fInicio.compareTo(fFin) < 1) {

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
        cal.setTime(hFin);
        cal.add(Calendar.MINUTE, MIN_FIN_MARCACION);
        Date horaMaximaSalida = cal.getTime();

        RegistroAsistencia registro = new RegistroAsistencia();

        if (FechaUtil.compararFechaHora(fInicio, hInicio, fInicio, horaMaximaSalida) <= 0
                && FechaUtil.compararFechaHora(fFin, hFin, fInicio, horaMaximaSalida) >= 0) {
            //ANALIZAMOS =D

            BigDecimal tardanzaTotal = new BigDecimal(0);

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

                } else if(asignacion.getPermiso().isCubreSalida()){
                    //EN CASO DE QUE EL PERMISO CUBRA LA SALIDA DEL EMPLEADO
                    ocupaSalida = asignacion.getPermiso().getHoraInicio();
                }else {
                    marcacionInicioPermiso = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, asignacion.getPermiso().getHoraInicio(), horaFinPermiso);
                }

                Marcacion marcacionFinPermiso = null;
                if (marcacionInicioPermiso != null) {
                    //
                    detallePermiso.setHoraInicio(marcacionInicioPermiso.getHora());
                    Long difPermiso = marcacionInicioPermiso.getHora().getTime() - asignacion.getPermiso().getHoraInicio().getTime();

                    cal.setTime(marcacionInicioPermiso.getHora());
                    cal.add(Calendar.SECOND, 10);

                    Date horaInicioPermiso = cal.getTime();

                    //AGREGAMOS A LA HORA DE SALIDA 
                    cal.setTime(asignacion.getPermiso().getHoraFin());
                    cal.add(Calendar.MILLISECOND, difPermiso.intValue());

                    horaFinPermiso = cal.getTime();

                    marcacionFinPermiso = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, horaInicioPermiso, horaMaximaSalida);
                    BigDecimal tardanzaPermisoMin = null;
                    if (marcacionFinPermiso != null) {
                        detallePermiso.setHoraFin(marcacionFinPermiso.getHora());
                        tardanzaPermisoMin = tardanzaMin(marcacionFinPermiso.getHora(), horaFinPermiso);
                        detallePermiso.setMinTardanza(tardanzaPermisoMin);
                        tardanzaTotal.add(tardanzaPermisoMin);

                    }
//                    else {
//                        //SE TOMA COMO TARDANZA TODO EL DIA
//                        detallePermiso.setHoraFin(jornada.getTurnoHS());
//                        tardanzaPermisoMin = tardanzaMin(jornada.getTurnoHS(), horaFinPermiso);
//                    }

                }
            }//FIN DEL {for} ANALISIS DE LOS PERMISOS

            //ANALISIS DE LOS DEMAS DETALLES
            //INICIO Y FIN DEL TURNO
            Marcacion marcacionInicioTurno = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getDesdeHE(), jornada.getToleranciaHE());
            Marcacion marcacionFinTurno = mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getTurnoHS(), horaMaximaSalida);

        }

        return registro;
    }

    private BigDecimal tardanzaMin(Date horaMarcada, Date horaComparar) {
        Long diferencia = horaMarcada.getTime() - horaComparar.getTime();
        if (diferencia > 0) {
            return new BigDecimal(diferencia.intValue() / (1000 * 60));
        } else {
            return new BigDecimal(0);
        }
    }

}