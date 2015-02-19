/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.juvitec.biosislite;

import com.personal.utiles.ReporteUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vistas.reportes.beans.RTardanzaFaltaBean;

/**
 *
 * @author fesquivelc
 */
public class pruebaReporteT {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        ReporteUtil reporteador =new ReporteUtil();
        List<RTardanzaFaltaBean> beans = new ArrayList<>();
        
        for(int i = 1; i<=10; i++){
            RTardanzaFaltaBean bean = new RTardanzaFaltaBean();
            bean.setNombre(i+"");
            bean.setCodigoModular(i+"");
            bean.setDocumentoIdentidad(i+"");
            bean.setHoraMarcacion(new Date());
            
            beans.add(bean);
        }
        
        File reporte = new File("reportes/r_tardanzas.jasper");
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("titulo", "TARDANZAS EN EL DIA");
        map.put("usuario", "admin");
        
        reporteador.generarReporte(beans, reporte, map, null);
    }
    
}
