/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.dialogos;

import controladores.Controlador;
import controladores.EmpleadoControlador;
import controladores.VacacionControlador;
import entidades.Empleado;
import entidades.Vacacion;
import com.personal.utiles.FormularioUtil;
import controladores.SaldoVacacionalControlador;
import controladores.TCAnalisisControlador;
import entidades.Periodo;
import entidades.SaldoVacacional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author fesquivelc
 */
public class DlgReprogramarVacacion extends javax.swing.JDialog {

    /**
     * Creates new form DlgInterrupcionVacacion
     */
    private final Vacacion vacacion;
    private final VacacionControlador vc;
    private final EmpleadoControlador ec;

    public DlgReprogramarVacacion(JInternalFrame padre, Vacacion vacacion) {
        super(JOptionPane.getFrameForComponent(padre), true);
        initComponents();
        this.vacacion = vacacion;
        ec = new EmpleadoControlador();
        vc = new VacacionControlador();
        controles();
        this.setLocationRelativeTo(padre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        dtFechaFin = new com.toedter.calendar.JDateChooser();
        dtFechaInterrupcion = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar interrupción");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Definir fecha de interrupción"));
        jPanel1.setToolTipText("");
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 0, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jPanel2, gridBagConstraints);

        jLabel1.setText("Empleado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Fecha de inicio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Fecha de fin:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Días restantes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(txtEmpleado, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(dtFechaInicio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(dtFechaFin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(dtFechaInterrupcion, gridBagConstraints);

        jLabel5.setText("Fecha de interrupción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jLabel5, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int accion = Controlador.MODIFICAR;
        if (FormularioUtil.dialogoConfirmar(this, accion)) {
            vacacion.setHayInterrupcion(true);
            vacacion.setFechaInterrupcion(dtFechaInterrupcion.getDate());
            vc.setSeleccionado(vacacion);
            if (vc.accion(accion)) {
                SaldoVacacional sv = buscarSaldo(vc.getSeleccionado().getEmpleado(), vc.getSeleccionado().getPeriodo());
                int[] saldos = obtenerSaldos(vc.getSeleccionado().getEmpleado(),vc.getSeleccionado().getPeriodo());
                sv.setDiasRestantes(30 - (saldos[0] + saldos[1] + saldos[2]));
                sv.setLunesViernes(saldos[0]);
                sv.setSabado(saldos[1]);
                sv.setDomingo(saldos[2]);
                svc.modificar(sv);
                List<String> dnis = new ArrayList<>();
                dnis.add(vacacion.getEmpleado());
                retrocederTiempo(dnis, vacacion.getFechaInicio());
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private final TCAnalisisControlador tcac = new TCAnalisisControlador();

    private void retrocederTiempo(List<String> dnis, Date fechaInicio) {
        tcac.retrocederTiempo(dnis, fechaInicio);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dtFechaFin;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private com.toedter.calendar.JDateChooser dtFechaInterrupcion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtEmpleado;
    // End of variables declaration//GEN-END:variables

    private void controles() {
        Empleado empleado = ec.buscarPorId(vacacion.getEmpleado());
        txtEmpleado.setText(empleado.getNombre() + " " + empleado.getApellidoPaterno() + " " + empleado.getApellidoMaterno());
        dtFechaInicio.setDate(vacacion.getFechaInicio());
        dtFechaFin.setDate(vacacion.getFechaFin());

        FormularioUtil.activarComponente(txtEmpleado, false);
        FormularioUtil.activarComponente(dtFechaInicio, false);
        FormularioUtil.activarComponente(dtFechaFin, false);
    }
    
    private final Calendar cal = Calendar.getInstance();
    private final SaldoVacacionalControlador svc = new SaldoVacacionalControlador();
    private int[] obtenerSaldos(String dni, Periodo periodo) {
        List<Vacacion> vacaciones = vc.buscarXEmpleadoXPeriodo(dni, periodo);
        int[] saldo = new int[3];
        int lunesViernes = 0;
        int sabado = 0;
        int domingo = 0;
        
        for (Vacacion v : vacaciones) {
            Date fechaInicio = v.getFechaInicio();
            Date fechaFin = v.isHayInterrupcion() ? v.getFechaInterrupcion() : v.getFechaFin();

            while (fechaInicio.compareTo(fechaFin) <= 0) {
                cal.setTime(fechaInicio);
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//                    sabadoADomingo++;
                } else {
                    lunesViernes++;
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                fechaInicio = cal.getTime();
            }
        }
        int division = lunesViernes / 5;
        sabado = division;
        domingo = division;
        
        saldo[0] = lunesViernes;
        saldo[1] = sabado;
        saldo[2] = domingo;
        return saldo;
    }
    private final Calendar calendar = Calendar.getInstance();
    public SaldoVacacional buscarSaldo(String dni, Periodo periodo) {
        SaldoVacacional sv = svc.buscarXPeriodo(dni, periodo);        
        return sv;
    }
}
