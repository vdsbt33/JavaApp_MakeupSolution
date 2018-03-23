/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.SwingApplication.View;

import java.awt.Dialog;
import javaapp_makeupsolution.Model.Cliente;
import javaapp_makeupsolution.Model.Endereco;
import javaapp_makeupsolution.Controller.ClienteDAO;
import javaapp_makeupsolution.Controller.EnderecoDAO;
import javaapp_makeupsolution.Controller.CidadeEnderecoDAO;
import javaapp_makeupsolution.Controller.BairroEnderecoDAO;
import javaapp_makeupsolution.Controller.RuaEnderecoDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author vdsbt33
 */
public class Clientes_Editar extends javax.swing.JFrame {

    private static Clientes_Editar self = null;
    
    private static Cliente cliente = null;
    
    /**
     * Creates new form Clientes_Editar
     */
    private  Clientes_Editar() {
        initComponents();
    }
    
    public static Clientes_Editar getSelf(Cliente alvo){
        if (self == null){
            self = new Clientes_Editar();
        }
        cliente = alvo;
        
        return self;
    }
    
    public static Clientes_Editar getSelf(){
        if (self == null){
            self = new Clientes_Editar();
        }
        
        return self;
    }
    
    public void atualizarCampos(){
        this.nomeClienteTbox.setText(cliente.getNome());
        this.descricaoClienteTbox.setText(cliente.getDescricao());
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
        nomeClienteTbox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoClienteTbox = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        adicionarEnderecoBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clientes - Editar");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nome:");

        nomeClienteTbox.setName("nomeClienteTbox"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Descrição:");

        descricaoClienteTbox.setColumns(20);
        descricaoClienteTbox.setRows(5);
        descricaoClienteTbox.setName("descricaoClienteTbox"); // NOI18N
        jScrollPane1.setViewportView(descricaoClienteTbox);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        adicionarEnderecoBtn.setText("Alterar Endereço");
        adicionarEnderecoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarEnderecoBtnAdicionarEnderecoBtn(evt);
            }
        });
        jPanel2.add(adicionarEnderecoBtn, new java.awt.GridBagConstraints());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        guardarBtn.setText("Guardar");
        guardarBtn.setToolTipText("");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnGuardarBtn(evt);
            }
        });
        jPanel1.add(guardarBtn, new java.awt.GridBagConstraints());

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnCancelarBtn(evt);
            }
        });
        jPanel1.add(cancelarBtn, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeClienteTbox)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeClienteTbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarEnderecoBtnAdicionarEnderecoBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarEnderecoBtnAdicionarEnderecoBtn
        Endereco_Editar endereco = null;
         /*
        Find a way here not to reset all fields even when "Save" button is clicked
        
        */
        
        endereco = Endereco_Editar.getSelf(cliente);
        
        
        endereco.atualizarCampos();
        
        
        this.setVisible(false);
            endereco.setVisible(true);
    }//GEN-LAST:event_adicionarEnderecoBtnAdicionarEnderecoBtn

    private void guardarBtnGuardarBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnGuardarBtn
        
        Boolean error = false;
        
        if (!this.nomeClienteTbox.getText().isEmpty() && !this.descricaoClienteTbox.getText().isEmpty()){
            cliente.setNome(this.nomeClienteTbox.getText());
            cliente.setDescricao(this.descricaoClienteTbox.getText());
            
            try {
                ClienteDAO.Atualizar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Um erro ocorreu ao atualizar o Cliente. \nErro:" + ex.getMessage());
                error = true;
            }
        }
        
        if (ClienteDAO.hasEndereco(cliente)) {
            if (!Endereco_Editar.getClienteEndereco().getCidadeEndereco().getNomeCidadeEndereco().isEmpty()) {
                if (!Endereco_Editar.getClienteEndereco().getBairroEndereco().getNomeBairroEndereco().isEmpty()){
                    if (!Endereco_Editar.getClienteEndereco().getRuaEndereco().getNomeRuaEndereco().isEmpty()){
                        Endereco_Editar.getClienteEndereco().setCliente(cliente);
                        try {
                            CidadeEnderecoDAO.Atualizar(Endereco_Editar.getClienteEndereco().getCidadeEndereco());
                            BairroEnderecoDAO.Atualizar(Endereco_Editar.getClienteEndereco().getBairroEndereco());
                            RuaEnderecoDAO.Atualizar(Endereco_Editar.getClienteEndereco().getRuaEndereco());
                            EnderecoDAO.Atualizar(Endereco_Editar.getClienteEndereco());
                            JOptionPane.showMessageDialog(null, "Endereço atualizado com sucesso!");

                        } catch (Exception ex){
                            JOptionPane.showMessageDialog(null, "Um erro ocorreu ao atualizar o Endereco. \nErro:" + ex.getMessage());
                            error = true;
                        }

                    }
                }
            }
        } else {
            if(Endereco_Editar.getClienteEndereco() != null){
                if (!Endereco_Editar.getClienteEndereco().getCidadeEndereco().getNomeCidadeEndereco().isEmpty()) {
                    if (!Endereco_Editar.getClienteEndereco().getBairroEndereco().getNomeBairroEndereco().isEmpty()){
                        if (!Endereco_Editar.getClienteEndereco().getRuaEndereco().getNomeRuaEndereco().isEmpty()){
                            Endereco_Editar.getClienteEndereco().setCliente(cliente);
                            try {
                                CidadeEnderecoDAO.Adicionar(Endereco_Editar.getClienteEndereco().getCidadeEndereco());
                                BairroEnderecoDAO.Adicionar(Endereco_Editar.getClienteEndereco().getBairroEndereco());
                                RuaEnderecoDAO.Adicionar(Endereco_Editar.getClienteEndereco().getRuaEndereco());
                                EnderecoDAO.Adicionar(Endereco_Editar.getClienteEndereco());
                                JOptionPane.showMessageDialog(null, "Endereço adicionado com sucesso!");

                            } catch (Exception ex){
                                JOptionPane.showMessageDialog(null, "Um erro ocorreu ao adicionar o Endereco. \nErro:" + ex.getMessage());
                                error = true;
                            }

                        }
                    }
                }
            }
            
        }
        
        if (error == false){
            this.setVisible(false);
            Clientes_Listar.getSelf().setVisible(true);
        }

    }//GEN-LAST:event_guardarBtnGuardarBtn

    private void cancelarBtnCancelarBtn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnCancelarBtn
        this.setVisible(false);
        Clientes_Listar.getSelf().setVisible(true);
    }//GEN-LAST:event_cancelarBtnCancelarBtn

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
            java.util.logging.Logger.getLogger(Clientes_Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes_Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes_Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes_Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes_Editar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarEnderecoBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextArea descricaoClienteTbox;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeClienteTbox;
    // End of variables declaration//GEN-END:variables
}
