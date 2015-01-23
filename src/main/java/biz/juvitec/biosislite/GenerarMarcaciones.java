/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import algoritmo.AnalisisAsistencia;
import biz.juvitec.controladores.EmpleadoControlador;
import biz.juvitec.controladores.JornadaControlador;
import biz.juvitec.controladores.MarcacionControlador;
import biz.juvitec.entidades.Empleado;
import biz.juvitec.entidades.Jornada;
import com.personal.utiles.FechaUtil;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author fesquivelc
 */
public class GenerarMarcaciones {

    /**
     * @param args the command line arguments
     */
    private static final Logger LOG = Logger.getLogger(GenerarMarcaciones.class.getName());

    private static void agregarHora(Calendar fsuma, Calendar cal) {
        fsuma.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
        fsuma.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
        fsuma.set(Calendar.SECOND, cal.get(Calendar.SECOND));
        fsuma.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
    }

    public void getPrueba() {
        System.out.println(getClass().getClassLoader().getResource("."));
    }

    public static void main(String[] args) {
        AnalisisAsistencia aa = new AnalisisAsistencia();
        JornadaControlador jc = new JornadaControlador();
        EmpleadoControlador ec = new EmpleadoControlador();
        MarcacionControlador mc = new MarcacionControlador();
        Jornada jornada = jc.buscarPorId("J-276-728");
        DateFormat dfHora = new SimpleDateFormat("HH:mm:ss");        
        //DEFINIENDO PARAMETROS
        Calendar cal = Calendar.getInstance();
        Date horaDesdeHE = jornada.getDesdeHE();                
        cal.setTime(jornada.getTardanzaHE());
        cal.add(Calendar.MINUTE, 10);        
        Date horaHastaHE = cal.getTime();
        
        cal.setTime(jornada.getRefrigerioHS());
        cal.add(Calendar.MINUTE, 40);
        
        Date horaDesdeHSR = cal.getTime();
        
        cal.setTime(jornada.getRefrigerioHS());
        cal.add(Calendar.MINUTE, 90);
        
        Date horaHastaHSR = cal.getTime();
        
        cal.setTime(jornada.getRefrigerioHE());
        cal.add(Calendar.MINUTE, -30);
        
        Date horaDesdeHER = cal.getTime();
        
        cal.setTime(jornada.getRefrigerioHE());
        cal.add(Calendar.MINUTE, 30);
        
        Date horaHastaHER = cal.getTime();
        
        cal.setTime(jornada.getTurnoHS());
        
        Date horaDesdeHS = cal.getTime();
        
        cal.add(Calendar.MINUTE, 400);
        
        Date horaHastaHS = cal.getTime();
        
        

        String sql = "INSERT INTO TB_EVENT_LOG (nDateTime,nReaderIdn,nEventIdn,nUserID,nIsLog,nTNAEvent,nIsUseTA,nType) "
                + "VALUES ("
                + "DATEDIFF(s, '1970-01-01', :fechaHora)"
                + ",'40301'"
                + ",'55'"
                + ",(SELECT nUserIdn FROM TB_USER u WHERE u.sUserID = :dni)"
                + ",'1'"
                + ",'255'"
                + ",0"
                + ",0"
                + ")";

        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(2015, 0, 1);
        
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(2015, 0, 22);
        
        Date inicio = FechaUtil.soloFecha(fechaInicio.getTime());
        Date fin = FechaUtil.soloFecha(fechaFin.getTime());
        
        Calendar iterador = Calendar.getInstance();
        Calendar fsuma = Calendar.getInstance();
        EntityManager em = mc.getDao().getEntityManager();
        for (int i = 1; i <= 4000; i++) {
            //BUSCAMOS AL EMPLEADO
            Empleado empleado = ec.buscarPorId(conCeros(i));
            //LLENAMOS SUS MARCACIONES DESDE EL PRIMERO DE ENERO
//            System.out.println("EMPLEADO: "+empleado.getNroDocumento());
            iterador.setTime(inicio);
            Date fecha;
            em.getTransaction().begin();
            do{
                fsuma.setTime(iterador.getTime());
                
                cal.setTime(jornada.getDesdeHE());
                cal.add(Calendar.MILLISECOND, randTiempo(horaDesdeHE, horaHastaHE));
                
                agregarHora(fsuma,cal);
                
                em.createNativeQuery(sql).setParameter("dni", empleado.getNroDocumento()).setParameter("fechaHora", fsuma.getTime()).executeUpdate();
//                System.out.println("HORA ENTRADA: "+fsuma.getTime());
                
                fsuma.setTime(iterador.getTime());
                
                cal.setTime(jornada.getRefrigerioHS());
                cal.add(Calendar.MILLISECOND, randTiempo(horaDesdeHSR, horaHastaHSR));
                
                agregarHora(fsuma,cal);
                
                em.createNativeQuery(sql).setParameter("dni", empleado.getNroDocumento()).setParameter("fechaHora", fsuma.getTime()).executeUpdate();
//                System.out.println("HORA SALIDA A REFRIGERIO: "+fsuma.getTime());
                
                fsuma.setTime(iterador.getTime());
                
                cal.setTime(jornada.getRefrigerioHE());
                cal.add(Calendar.MINUTE, -30);
                cal.add(Calendar.MILLISECOND, randTiempo(horaDesdeHER, horaHastaHER));
//                System.out.println("HORA ENTRADA DE REFRIGERIO: "+cal.getTime()+" "+jornada.getRefrigerioHE());
                agregarHora(fsuma,cal);
                
                em.createNativeQuery(sql).setParameter("dni", empleado.getNroDocumento()).setParameter("fechaHora", fsuma.getTime()).executeUpdate();
//                System.out.println("HORA ENTRADA DE REFRIGERIO: "+fsuma.getTime());
                fsuma.setTime(iterador.getTime());
                
                cal.setTime(jornada.getTurnoHS());
                cal.add(Calendar.MILLISECOND, randTiempo(horaDesdeHS, horaHastaHS));
                
                agregarHora(fsuma,cal);
                
                em.createNativeQuery(sql).setParameter("dni", empleado.getNroDocumento()).setParameter("fechaHora", fsuma.getTime()).executeUpdate();
//                System.out.println("HORA SALIDA: "+fsuma.getTime());
                iterador.add(Calendar.DATE, 1);
            }while(iterador.getTime().compareTo(fin) <= 0);
            
            em.getTransaction().commit();
        }

//        Calendar result = Calendar.getInstance();
//        for (int i = 1; i <= 30 * 12; i++) {
//            int milis = randTiempo(horaDesdeTurno, horaFin);
//            result.setTime(horaDesdeTurno);
//            result.add(Calendar.MILLISECOND, milis);
//            BigDecimal tardanza = aa.tardanzaMin(result.getTime(), jornada.getToleranciaHE());
//            if (tardanza.compareTo(BigDecimal.ZERO) > 0) {
//                System.out.println("HORA: " + dfHora.format(result.getTime()) + " TARDANZA: " + tardanza);
//            }
//
//        }

        System.exit(0);

    }
    static Random rand;

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        if (rand == null) {
            rand = new Random();
        }

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static int randTiempo(Date horaMinima, Date horaMaxima) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Long hMin = horaMinima.getTime();
        Long hMax = horaMaxima.getTime();
        Long maximo = hMax - hMin;
        return randInt(0, maximo.intValue());
    }

    public static String conCeros(int dni) {
        if (dni <= 9) {
            return "0000000" + dni;
        } else if (dni <= 99) {
            return "000000" + dni;
        } else if (dni <= 999) {
            return "00000" + dni;
        } else {
            return "0000" + dni;
        }
    }

}
