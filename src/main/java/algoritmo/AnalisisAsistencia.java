/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import biz.juvitec.controladores.AsignacionPermisoControlador;
import biz.juvitec.controladores.FeriadoControlador;
import biz.juvitec.controladores.MarcacionControlador;
import biz.juvitec.controladores.PermisoControlador;
import biz.juvitec.controladores.TCAnalisisControlador;
import biz.juvitec.controladores.TCSistemaControlador;
import biz.juvitec.controladores.VacacionControlador;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.RegistroAsistencia;
import biz.juvitec.entidades.TCAnalisis;
import biz.juvitec.entidades.TCSistema;
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

    public AnalisisAsistencia() {
        iniciar();
    }
    
    private void iniciar(){
        tcac = new TCAnalisisControlador();
        fc = new FeriadoControlador();
        apc = new AsignacionPermisoControlador();
        vc = new VacacionControlador();
        mc = new MarcacionControlador();
        tcsc = TCSistemaControlador.getInstance();
    }
    
    private TCAnalisis obtenerPuntoPartida(Empleado empleado){
        TCAnalisis partida = tcac.buscarPorId(empleado.getNroDocumento());
        if(partida == null){
            partida = new TCAnalisis();
            TCSistema sistema = tcsc.buscarPorId("BIOSIS");
            Date contrato = empleado.getFechaInicioContrato();
            Date fechaCero = sistema.getFechaCero();
            
            if(contrato.compareTo(fechaCero) < 0){
                partida.setFecha(fechaCero);
                partida.setHora(sistema.getHoraCero());
            }else{
                partida.setFecha(contrato);
                partida.setHora(contrato);
            }
        }
        return partida;
    }
    
    private TCAnalisis obtenerPuntoLlegada(){
        TCAnalisis llegada = new TCAnalisis();
        llegada.setFecha(new Date());
        llegada.setHora(new Date());
        
        return llegada;
    }
    
    public List<RegistroAsistencia> analizarEmpleados(List<Empleado> empleados){
        //LA FECHA Y HORA DE FINAL DEL ANALISIS ES LA MISMA DE LA CONSULTA
        TCAnalisis llegada = obtenerPuntoLlegada();
        for(Empleado empleado : empleados){
            //OBTENEMOS LA FECHA Y HORA DE PARTIDA DEL ANALISIS
            TCAnalisis partida = obtenerPuntoPartida(empleado);
            
            //OBTENEMOS LOS HORARIOS DEL EMPLEADO
            
        }
        return null;
    }
}
