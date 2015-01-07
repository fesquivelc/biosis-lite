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
import biz.juvitec.entidades.Permiso;
import biz.juvitec.entidades.RegistroAsistencia;
import biz.juvitec.entidades.TCAnalisis;
import biz.juvitec.entidades.TCSistema;
import biz.juvitec.entidades.Vacacion;
import com.personal.utiles.FechaUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fesquivelc
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
                            registro.setTipoAsistencia('F');
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

    private RegistroAsistencia analizarJornada(Empleado empleado, Jornada jornada, Date fInicio, Date hInicio, Date fFin, Date hFin) {
        //SE VE SI LA JORNADA VA A SER ANALIZADA
        Date horaMaximaEntrada = jornada.getTardanzaHE();
        Calendar cal = Calendar.getInstance();
        cal.setTime(jornada.getTurnoHS());
        cal.add(Calendar.MINUTE, MIN_FIN_MARCACION);
        Date horaMaximaSalida = cal.getTime();

        if (FechaUtil.compararFechaHora(fInicio, hInicio, fInicio, horaMaximaEntrada) <= 0
                && FechaUtil.compararFechaHora(fFin, hFin, fInicio, horaMaximaSalida) >= 0) {
            //SE PROCEDE A ANALIZAR

            long tardanzaTotal = 0;
            long trabajoTotal = 0;

            boolean ocupaEntrada = false;
            boolean ocupaRefrigerio = false;
            boolean ocupaSalida = false;
            Date horaEntrada = null;
            Date horaSalida = null;

            RegistroAsistencia registro = new RegistroAsistencia();
            List<DetalleRegistroAsistencia> detalles = new ArrayList<>();

            registro.setEmpleado(empleado.getNroDocumento());
            registro.setFecha(fInicio);

            //PRIMERO SE ANALIZAN LOS PERMISOS POR HORA QUE PUDIESEN EXISTIR
            List<AsignacionPermiso> permisosXHora = apc.obtenerPermisosXHora(empleado.getNroDocumento(), fInicio, jornada.getDesdeHE(), horaMaximaSalida);

            for (AsignacionPermiso asignacion : permisosXHora) {
                List<DetalleRegistroAsistencia> detallesPermiso = analizarPermisoXHora(empleado.getNroDocumento(), asignacion.getPermiso(), horaMaximaSalida, registro);
                DetalleRegistroAsistencia salida = detallesPermiso.get(0);
                DetalleRegistroAsistencia entrada = detallesPermiso.get(0);

                if (salida.getHora().compareTo(jornada.getDesdeHE()) >= 0 && salida.getHora().compareTo(horaMaximaEntrada) <= 0) {
                    ocupaEntrada = true;
                    horaEntrada = entrada.getHora();
                }
                if (entrada.getHora().compareTo(jornada.getTurnoHS()) >= 0 && salida.getHora().compareTo(horaMaximaSalida) <= 0) {
                    ocupaSalida = true;
                    horaSalida = salida.getHora();
                }
                detalles.addAll(detallesPermiso);

                if (asignacion.getPermiso().getTipoPermiso().getTipoDescuento() == 'S') {
                    trabajoTotal = trabajoTotal - (entrada.getHora().getTime() - salida.getHora().getTime()); //OBTENEMOS LO QUE NO SE TRABAJA :3 
                }
            }

            //AHORA SE PROCEDE A ANALIZAR LA JORNADA
            //ANALIZAMOS EL EVENTO DE ENTRADA AL TURNO
            DetalleRegistroAsistencia detalleEntrada = new DetalleRegistroAsistencia();
            detalleEntrada.setEvento('E'); //LOS EVENTOS SON ENTRADA O SALIDA (E O S)
            detalleEntrada.setTipo('T');
            detalleEntrada.setRegistroAsistencia(registro);
            if (ocupaEntrada) {
                //detalleEntrada.setTipo("T"); // LOS TIPOS SON T = TURNO, P = PERMISO, R = REFRIGERIO
                detalleEntrada.setResultado('R'); // LOS RESULTADOS SON R = REGULAR, T = TARDANZA, F = FALTA
                detalleEntrada.setHora(horaEntrada);
            } else {
                Marcacion marcacionEntrada = this.mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getDesdeHE(), horaMaximaEntrada);
                if (marcacionEntrada == null) {
                    //MARCACION COMO FALTA
                    //detalleEntrada.setTipo("T");
                    detalleEntrada.setResultado('F');
                    registro.setTipoAsistencia('F');

                } else {
                    long tardanzaEntrada = tardanza(marcacionEntrada.getHora(), jornada.getToleranciaHE());
                    if (tardanzaEntrada > 0) {
                        //detalleEntrada.setTipo("T");
                        detalleEntrada.setResultado('T');

                        registro.setTipoAsistencia('T');

                        tardanzaTotal += tardanzaEntrada;
                    } else {
                        //detalleEntrada.setTipo("T");
                        detalleEntrada.setResultado('R');
                        registro.setTipoAsistencia('R');
                    }
                    detalleEntrada.setHora(marcacionEntrada.getHora());
                }
            }
            detalles.add(detalleEntrada);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // ANALIZAMOS LA SALIDA DEL TURNO
            DetalleRegistroAsistencia detalleSalida = new DetalleRegistroAsistencia();
            detalleSalida.setEvento('S'); //LOS EVENTOS SON ENTRADA O SALIDA (E O S)
            detalleSalida.setTipo('T');
            detalleSalida.setRegistroAsistencia(registro);
            if (ocupaSalida) {
                //detalleEntrada.setTipo("T"); // LOS TIPOS SON T = TURNO, P = PERMISO, R = REFRIGERIO
                detalleSalida.setResultado('R'); // LOS RESULTADOS SON R = REGULAR, T = TARDANZA, F = FALTA
                detalleSalida.setHora(horaSalida);
            } else {
                Marcacion marcacionSalida = this.mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getTurnoHS(), horaMaximaSalida);
                if (marcacionSalida == null) {
                    //MARCACION COMO FALTA
                    //detalleEntrada.setTipo("T");
                    detalleSalida.setResultado('F');
                    registro.setTipoAsistencia('F');

                } else {
                    //detalleEntrada.setTipo("T");
                    detalleSalida.setResultado('R');
                    detalleSalida.setHora(marcacionSalida.getHora());
                }

            }
            detalles.add(detalleSalida);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // ANALIZAMOS LA SALIDA A REFRIGERIO 
            // CASO ESPECIAL: CICLO DE UNA HORA
            // TOMAMOS COMO PUNTO DE PARTIDA LA HORA DE SALIDA A REFRIGERIO Y LE SUMAMOS UNA HORA COMO LIMITE MAXIMO
            // EN CASO DE NO EXISTIR DICHO LIMITE SE BUSCARA EN OTRO LIMITE QUE SERIA LA HORA MAXIMA DE SALIDA
            // TODO EL TIEMPO QUE EXISTA ENTRE LA HORA EN LA QUE DEBIO ENTRAR HASTA LA HORA EN LA QUE ENTRO
            // SE CONSIDERA COMO TARDANZA
            DetalleRegistroAsistencia detalleSalidaRefrigerio = new DetalleRegistroAsistencia();
            detalleSalidaRefrigerio.setEvento('S'); //LOS EVENTOS SON ENTRADA O SALIDA (E O S)
            detalleSalidaRefrigerio.setTipo('R');
            detalleSalidaRefrigerio.setRegistroAsistencia(registro);
            Marcacion marcacionSalidaRefrigerio = this.mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, jornada.getRefrigerioHS(), jornada.getToleranciaRefrigerioHE());
            if (marcacionSalidaRefrigerio == null) {
                //MARCACION COMO FALTA
                //detalleEntrada.setTipo("T");
                detalleSalidaRefrigerio.setResultado('F');

            } else {
                //detalleEntrada.setTipo("T");
                detalleSalidaRefrigerio.setResultado('R');
                detalleSalidaRefrigerio.setHora(marcacionSalidaRefrigerio.getHora());
            }
            detalles.add(detalleSalidaRefrigerio);

            if (marcacionSalidaRefrigerio != null) {
                Calendar calDesde = Calendar.getInstance();
                Calendar calHasta = Calendar.getInstance();

                calDesde.setTime(marcacionSalidaRefrigerio.getHora());
                calDesde.add(Calendar.MINUTE, 1);

                calHasta.setTime(marcacionSalidaRefrigerio.getHora());
                calHasta.add(Calendar.MINUTE, 60);

                DetalleRegistroAsistencia detalleEntradaRefrigerio = new DetalleRegistroAsistencia();
                detalleEntradaRefrigerio.setEvento('E'); //LOS EVENTOS SON ENTRADA O SALIDA (E O S)
                detalleEntradaRefrigerio.setTipo('R');
                detalleEntradaRefrigerio.setRegistroAsistencia(registro);
                Marcacion marcacionEntradaRefrigerio = this.mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, calDesde.getTime(), calHasta.getTime());
                if (marcacionEntradaRefrigerio == null) {
                    //MARCACION COMO FALTA
                    //detalleEntrada.setTipo("T");

                    marcacionEntradaRefrigerio = this.mc.buscarXFechaXhora(empleado.getNroDocumento(), fInicio, calHasta.getTime(), horaMaximaSalida);
                    if (marcacionEntradaRefrigerio == null) {
                        detalleEntradaRefrigerio.setResultado('F');
                        registro.setTipoAsistencia('F');
                    } else {
                        tardanzaTotal = tardanzaTotal + this.tardanza(calHasta.getTime(), marcacionEntradaRefrigerio.getHora());
                        registro.setTipoAsistencia('T');
                        detalleEntradaRefrigerio.setHora(marcacionEntradaRefrigerio.getHora());
                        detalleEntradaRefrigerio.setResultado('T');

                    }

                } else {
                    //detalleEntrada.setTipo("T");
                    detalleEntradaRefrigerio.setResultado('R');
                    detalleEntradaRefrigerio.setHora(marcacionSalidaRefrigerio.getHora());
                }
                detalles.add(detalleEntradaRefrigerio);
            }

            registro.setDetalleRegistroAsistenciaList(detalles);

            registro.setMilisegundosTardanza(tardanzaTotal);

            return registro;
        } else {
            return null;
        }
    }

    private List<DetalleRegistroAsistencia> analizarPermisoXHora(String nroDocumento, Permiso permiso, Date horaMaxima, RegistroAsistencia registro) {
        //TENEMOS QUE BUSCAR LAS MARCACIONES QUE ESTEN RELACIONADAS CON EL PERMISO
        Calendar cal = Calendar.getInstance();

        cal.setTime(permiso.getHoraInicio());
        cal.add(Calendar.MINUTE, 30); //HASTA 30 MINUTOS QUE SE PUEDE REGISTRAR LA SALIDA AL PERMISO

        Marcacion marcacionSalida = mc.buscarXFechaXhora(nroDocumento, permiso.getFechaInicio(), permiso.getHoraInicio(), cal.getTime());
        Marcacion marcacionEntrada = null;

        List<DetalleRegistroAsistencia> detalles = new ArrayList<>();

        Date horaEntrada;
        if (marcacionSalida == null) {
            cal.setTime(permiso.getHoraInicio());

        } else {
            cal.setTime(marcacionSalida.getHora());
        }
        cal.add(Calendar.SECOND, 20); //MERAMENTE ARBITRARIO
        horaEntrada = cal.getTime();

        marcacionEntrada = mc.buscarXFechaXhora(nroDocumento, permiso.getFechaInicio(), horaEntrada, horaMaxima);

        //DETALLE PARA LA MARCACION DE SALIDA
        DetalleRegistroAsistencia detalleSalida = new DetalleRegistroAsistencia();
        detalleSalida.setEvento('S');
        detalleSalida.setRegistroAsistencia(registro);
        detalleSalida.setTipo('P');
        detalleSalida.setPermiso(permiso);

        if (marcacionSalida == null) {
            detalleSalida.setResultado('N'); //NOR HAY MARCACION
            detalleSalida.setHora(permiso.getHoraInicio());
        } else {
            detalleSalida.setResultado('M'); //EXISTE MARCACION
            detalleSalida.setHora(marcacionSalida.getHora());

        }

        detalles.add(detalleSalida);

        //DETALLE PARA LA MARCACION DE ENTRADA
        DetalleRegistroAsistencia detalleEntrada = new DetalleRegistroAsistencia();
        detalleEntrada.setEvento('E');
        detalleEntrada.setRegistroAsistencia(registro);
        detalleEntrada.setPermiso(permiso);
        detalleEntrada.setTipo('P');

        if (marcacionEntrada == null) {
            detalleEntrada.setHora(horaMaxima);
            detalleEntrada.setResultado('N');
        } else {
            detalleEntrada.setHora(marcacionEntrada.getHora());
            detalleEntrada.setResultado('M');
        }

        detalles.add(detalleEntrada);

        return detalles;
    }

    private long tardanza(Date hora, Date horaTolerancia) {
        long resultado = hora.getTime() - horaTolerancia.getTime();
        if (resultado < 0) {
            return Math.abs(resultado);
        } else {
            return 0;
        }
    }

}
