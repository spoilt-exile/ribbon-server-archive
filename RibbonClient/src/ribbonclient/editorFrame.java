/**
 * This file is part of RibbonClient application (check README).
 * Copyright (C) 2013 Stanislav Nepochatov
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
**/

package ribbonclient;

/**
 * Editor window class.
 * @author Stanislav Nepochatov
 */
public class editorFrame extends javax.swing.JDialog {
    
    /**
     * Message to process;
     */
    private MessageClasses.Message editedMessage;
    
    /**
     * Editor mode enumeration
     */
    public enum editMode {
        POST,           //Post new message
        MODIFY,         //Modify message which already exist
        RE_POST         //Post message which bases on existent message
    };
    
    /**
     * Current mode of the editor;
     */
    private editMode currMode;

    /** Creates new form editorFrame */
    public editorFrame(java.awt.Frame parent, boolean modal, MessageClasses.Message givenMessage, editMode givenMode) {
        super(parent, modal);
        initComponents();
        editedMessage = givenMessage;
        currMode = givenMode;
        if (editedMessage != null) {
            this.headerField.setText(editedMessage.HEADER);
            this.editorPane.setText(editedMessage.CONTENT);
            this.tagField.setText(Generic.CsvFormat.renderCommonLine(givenMessage.TAGS));
        } else {
            editedMessage = new MessageClasses.Message();
        }
        switch (currMode) {
            case POST:
                break;
            case MODIFY:
                break;
            case RE_POST:
                editedMessage.ORIG_INDEX = editedMessage.INDEX;
                editedMessage.DIRS = null;
                break;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JTextPane();
        editorBar = new javax.swing.JToolBar();
        copyBut = new javax.swing.JButton();
        pasteBut = new javax.swing.JButton();
        flushBut = new javax.swing.JButton();
        releaseBut = new javax.swing.JButton();
        headerField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tagField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редактор \"Стрічка\"");

        jScrollPane1.setViewportView(editorPane);

        editorBar.setBorder(null);

        copyBut.setText("Копіювати");
        copyBut.setFocusable(false);
        copyBut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyBut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editorBar.add(copyBut);

        pasteBut.setText("Вставити");
        pasteBut.setFocusable(false);
        pasteBut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteBut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editorBar.add(pasteBut);

        flushBut.setText("Відчистити");
        flushBut.setFocusable(false);
        flushBut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        flushBut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editorBar.add(flushBut);

        releaseBut.setText("Випуск!");
        releaseBut.setFocusable(false);
        releaseBut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        releaseBut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        releaseBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseButActionPerformed(evt);
            }
        });
        editorBar.add(releaseBut);

        jLabel1.setText("Заголовок");

        jLabel2.setText("Теги");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addComponent(editorBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(headerField)
                            .addComponent(tagField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editorBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tagField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void releaseButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseButActionPerformed
        editedMessage.CONTENT = this.editorPane.getText();
        editedMessage.HEADER = this.headerField.getText();
        editedMessage.TAGS = this.tagField.getText().split(",");
        releaseDialog realeser = new releaseDialog(null, true, editedMessage, currMode);
        realeser.setVisible(true);
    }//GEN-LAST:event_releaseButActionPerformed

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
            java.util.logging.Logger.getLogger(editorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                editorFrame dialog = new editorFrame(new javax.swing.JFrame(), true, new MessageClasses.Message(), editMode.POST);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyBut;
    private javax.swing.JToolBar editorBar;
    private javax.swing.JTextPane editorPane;
    private javax.swing.JButton flushBut;
    private javax.swing.JTextField headerField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pasteBut;
    private javax.swing.JButton releaseBut;
    private javax.swing.JTextField tagField;
    // End of variables declaration//GEN-END:variables
}
