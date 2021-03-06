/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp_makeupsolution.SwingApplication.View;

import java.util.List;
import javaapp_makeupsolution.Controller.AgendaDAO;
import javaapp_makeupsolution.Controller.ProdutoDAO;
import javaapp_makeupsolution.Model.Produto;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vdsbt33
 */
public class Produtos_Listar extends javax.swing.JFrame {

    private static Produtos_Listar self = null;
    private static List<Produto> produtos = null;
    
    /**
     * Creates new form Produtos_Buscar
     */
    private Produtos_Listar() {
        initComponents();
    }
    
    public static Produtos_Listar getSelf(){
        if (self == null){
            self = new Produtos_Listar();
        }
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
        dataInicioDP = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        dataFimDP = new org.jdesktop.swingx.JXDatePicker();
        atualizarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        produtoTable = new javax.swing.JTable();
        editarProdutoBtn = new javax.swing.JButton();
        excluirProdutoBtn = new javax.swing.JButton();
        custoTotalLabel = new javax.swing.JLabel();

        setAlwaysOnTop(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Produtos - Buscar");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Data inicial:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Data final:");

        atualizarBtn.setText("Buscar");
        atualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarBtn_onClick(evt);
            }
        });

        produtoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "cod", "Nome", "Preço", "Quantidade", "Data Compra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(produtoTable);
        if (produtoTable.getColumnModel().getColumnCount() > 0) {
            produtoTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            produtoTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        editarProdutoBtn.setText("Editar");
        editarProdutoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarProdutoBtn_onClick(evt);
            }
        });

        excluirProdutoBtn.setText("Excluir");
        excluirProdutoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirProdutoBtn_onClick(evt);
            }
        });

        custoTotalLabel.setText("Custo total: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFimDP, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(atualizarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(custoTotalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(editarProdutoBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirProdutoBtn)))
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
                    .addComponent(dataInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dataFimDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atualizarBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarProdutoBtn)
                    .addComponent(excluirProdutoBtn)
                    .addComponent(custoTotalLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void atualizarLista(){
        try {
            produtos = ProdutoDAO.getListProdutoBetween(AgendaDAO.DateToLocalDateTime(dataInicioDP.getDate()), AgendaDAO.DateToLocalDateTime(dataFimDP.getDate()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        DefaultTableModel model = (DefaultTableModel) produtoTable.getModel();
        model.setRowCount(0);
        custoTotalLabel.setText("Custo total: 0");

        if (!produtos.isEmpty() && dataInicioDP.getDate() != null){
            if (dataFimDP.getDate() != null && dataInicioDP.getDate().before(dataFimDP.getDate())){
                Double custoTotal = 0.0;
                
                Object[] columnsName = { "cod", "Nome", "Preço", "Quantidade", "Data Compra" };
                model.setColumnIdentifiers(columnsName);
                
                Object[] rowData = new Object[5];
                for (int i = 0; i < produtos.size(); i++){
                    rowData[0] = produtos.get(i).getCodProduto();
                    rowData[1] = produtos.get(i).getNomeProduto();
                    rowData[2] = produtos.get(i).getPrecoProduto();
                    rowData[3] = produtos.get(i).getQuantidadeProduto();
                    rowData[4] = AgendaDAO.LocalDateTimeToString(produtos.get(i).getDataProduto());
                    model.addRow(rowData);
                    
                    custoTotal += produtos.get(i).getPrecoProduto();
                }
                custoTotalLabel.setText("Custo total: " + custoTotal);
            } else {
                JOptionPane.showMessageDialog(null, "Verifique se as datas não foram inseridas invertidas.");
            }
        } else {
            produtoTable.removeAll();
        }
    }
    
    private void atualizarBtn_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarBtn_onClick

        atualizarLista();
    }//GEN-LAST:event_atualizarBtn_onClick

    private void editarProdutoBtn_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarProdutoBtn_onClick
        if (produtoTable.getSelectedRow() >= 0){
            Object rowData;
            rowData = produtoTable.getModel().getValueAt(produtoTable.getSelectedRow(), 0);
            Produtos_Editar produtos_editar = null;
            produtos_editar = Produtos_Editar.getSelf(ProdutoDAO.getProdutoByID((int) rowData));
            produtos_editar.setVisible(true);
            produtos_editar.atualizarCampos();
            this.setVisible(false);

        }
    }//GEN-LAST:event_editarProdutoBtn_onClick

    private void excluirProdutoBtn_onClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirProdutoBtn_onClick
        if (produtoTable.getSelectedRow() >= 0){
            Object rowData;
            rowData = produtoTable.getModel().getValueAt(produtoTable.getSelectedRow(), 0);

            if (ProdutoDAO.exists((int) rowData)){
                int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja o relatório selecionado?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION){
                    ProdutoDAO.Remover(ProdutoDAO.getProdutoByID((int) rowData));
                    atualizarLista();
                    JOptionPane.showMessageDialog(null, "Relatório removido com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "O relatório selecionado não existe.");
            }
        }
    }//GEN-LAST:event_excluirProdutoBtn_onClick

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
            java.util.logging.Logger.getLogger(Produtos_Listar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produtos_Listar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produtos_Listar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produtos_Listar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Produtos_Listar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarBtn;
    private javax.swing.JLabel custoTotalLabel;
    private org.jdesktop.swingx.JXDatePicker dataFimDP;
    private org.jdesktop.swingx.JXDatePicker dataInicioDP;
    private javax.swing.JButton editarProdutoBtn;
    private javax.swing.JButton excluirProdutoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable produtoTable;
    // End of variables declaration//GEN-END:variables
}
