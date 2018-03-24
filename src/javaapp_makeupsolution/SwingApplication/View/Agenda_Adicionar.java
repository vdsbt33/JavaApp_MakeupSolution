/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.SwingApplication.View;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javaapp_makeupsolution.Model.Cliente;
import javaapp_makeupsolution.Controller.ClienteDAO;
import javaapp_makeupsolution.Model.Agenda;
import javaapp_makeupsolution.Controller.AgendaDAO;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


/**
 *
 * @author vdsbt33
 */
public class Agenda_Adicionar extends javax.swing.JFrame {
    private static Agenda_Adicionar self = null;
    
    private static Cliente cliente = null;
    
    /**
     * Creates new form Agenda_Adicionar
     */
    private Agenda_Adicionar() {
        initComponents();
        
        horaAgendaSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(horaAgendaSpinner, "HH:mm");
        horaAgendaSpinner.setEditor(editor);
        
        custoAgendaSpinner.setModel(new SpinnerNumberModel(0.0, -10000.0, 10000.0, 0.1));
        JSpinner.NumberEditor editorNumber = new JSpinner.NumberEditor(custoAgendaSpinner);
        custoAgendaSpinner.setEditor(editorNumber);
        
        
        //horaAgendaSpinner.setValue(new Date());
    }

    public static Agenda_Adicionar getSelf(Cliente alvo){
        if (self == null){
            self = new Agenda_Adicionar();
        }
        cliente = alvo;
        return self;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        custoAgendaSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        guardarAgendaBtn = new javax.swing.JButton();
        cancelarAgendaBtn = new javax.swing.JButton();
        dataAgendaDP = new org.jdesktop.swingx.JXDatePicker();
        horaAgendaSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agendamento");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Custo (R$):");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Data:");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        guardarAgendaBtn.setText("Guardar");
        guardarAgendaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAgendaBtn_onClick(evt);
            }
        });
        jPanel1.add(guardarAgendaBtn, new java.awt.GridBagConstraints());

        cancelarAgendaBtn.setText("Cancelar");
        cancelarAgendaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAgendaBtn_onClick(evt);
            }
        });
        jPanel1.add(cancelarAgendaBtn, new java.awt.GridBagConstraints());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Hora:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(custoAgendaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataAgendaDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(horaAgendaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 102, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dataAgendaDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horaAgendaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(custoAgendaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarAgendaBtn_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAgendaBtn_onClick
        this.setVisible(false);
        Clientes_Listar.getSelf().setVisible(true);
    }//GEN-LAST:event_cancelarAgendaBtn_onClick

    private void guardarAgendaBtn_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAgendaBtn_onClick
        if (dataAgendaDP.getDate() != null){
            if (horaAgendaSpinner.getValue() != null){
                if (Double.valueOf(custoAgendaSpinner.getValue().toString()) >= 0){
                    LocalDateTime ldt = LocalDateTime.ofInstant(dataAgendaDP.getDate().toInstant(), ZoneId.systemDefault());
                    Date m = (Date) horaAgendaSpinner.getValue();
                    LocalDateTime time = LocalDateTime.ofInstant(m.toInstant(), ZoneId.systemDefault());
                    ldt = ldt.plusHours(time.getHour());
                    ldt = ldt.plusMinutes(time.getMinute());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    ldt.format(formatter);
                    System.out.println(AgendaDAO.LocalDateTimeToString(ldt));
                    System.out.println(custoAgendaSpinner.getValue());
                    Agenda agenda = new Agenda(cliente, Double.valueOf(custoAgendaSpinner.getValue().toString()), ldt);
                    AgendaDAO.Adicionar(agenda);
                    JOptionPane.showMessageDialog(null, "Relatório de agenda adicionado com sucesso!");
                    this.setVisible(false);
                    Clientes_Listar.getSelf().setVisible(true);
                }
            }
        }
        
    }//GEN-LAST:event_guardarAgendaBtn_onClick

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agenda_Adicionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda_Adicionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda_Adicionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda_Adicionar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda_Adicionar().setVisible(true);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarAgendaBtn;
    private javax.swing.JSpinner custoAgendaSpinner;
    private org.jdesktop.swingx.JXDatePicker dataAgendaDP;
    private javax.swing.JButton guardarAgendaBtn;
    private javax.swing.JSpinner horaAgendaSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
