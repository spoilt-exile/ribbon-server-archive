/**
 * This file is part of RibbonLastMessage application (check README).
 * Copyright (C) 2012-2013 Stanislav Nepochatov
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

package ribbonlastmessage;

/**
 * Message display frame class;
 * @author Stanislav Nepochatov
 */
public class messageFrame extends javax.swing.JFrame {

    /**
     * Creates new form messageFrame
     */
    public messageFrame() {
        initComponents();
    }
    
    /**
     * Get parsed network connection parameters
     * @return parsed array
     */
    public String[] getNetworkArgs() {
        return this.remoteField.getText().split(":");
    }
    
    /**
     * Show all messages properties in messageFrame
     * @param givenMessage a message to show
     */
    public void showMessage(MessageClasses.Message givenMessage) {
        this.setTitle("\"Стрічка\" >>> " + givenMessage.DATE);
        StringBuffer renderedBuf = new StringBuffer();
        renderedBuf.append("<html>");
        renderedBuf.append("<b>Заголовок:</b> " + givenMessage.HEADER + "<br>");
        renderedBuf.append("<b>Автор:</b> " + givenMessage.AUTHOR + "<br>");
        renderedBuf.append("<b>Індекс:</b> " + givenMessage.INDEX + "<br>");
        if (!givenMessage.ORIG_INDEX.equals("-1")) {
            renderedBuf.append("<b>Автор оригіналу:</b> " + givenMessage.ORIG_AUTHOR + "<br>");
            renderedBuf.append("<b>Індекс оригіналу:</b> " + givenMessage.ORIG_INDEX + "<br>");
        }
        renderedBuf.append("<b>Напрямки:</b> <font color=RED>" + Generic.CsvFormat.renderGroup(givenMessage.DIRS) + "</font><br>");
        renderedBuf.append("<b>Теги:</b> <font color=GREEN>" + Generic.CsvFormat.renderGroup(givenMessage.TAGS) + "</font><br>");
        renderedBuf.append("<b>Мова повідомлення:</b> " + givenMessage.LANG + "<br>");
        renderedBuf.append("<b>Дата повідмолення:</b> " + givenMessage.DATE + "<br>");
        if (!givenMessage.PROPERTIES.isEmpty()) {
            renderedBuf.append("<br><b>Системні ознаки:</b><br>");
            java.util.ListIterator<MessageClasses.MessageProperty> propIter = givenMessage.PROPERTIES.listIterator();
            while (propIter.hasNext()) {
                MessageClasses.MessageProperty currProp = propIter.next();
                renderedBuf.append("[");
                renderedBuf.append(currProp.TYPE + ",");
                renderedBuf.append(currProp.USER + ",");
                renderedBuf.append(currProp.TEXT_MESSAGE + ",");
                renderedBuf.append(currProp.DATE + "]<br>");
            }
            renderedBuf.append("<br>");
        }
        renderedBuf.append("<br>");
        renderedBuf.append(givenMessage.CONTENT.replace("\n", "<br>"));
        this.messageArea.setText(renderedBuf.toString());
    }
    
    /**
     * Set button state according to network state;
     */
    public void setState() {
        if (RibbonLastMessage.networkIsUp == true) {
            this.connectBut.setText("Від'єднати!");
        } else {
            this.connectBut.setText("З'єднати");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextPane();
        remoteField = new javax.swing.JTextField();
        connectBut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\"Стрічка\" >>>");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        messageArea.setContentType("text/html");
        messageArea.setEditable(false);
        jScrollPane1.setViewportView(messageArea);

        remoteField.setText("127.0.0.1:3003");

        connectBut.setText("З'єднати");
        connectBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButActionPerformed(evt);
            }
        });

        jLabel5.setText("Адреса:порт");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remoteField, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectBut)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(remoteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectBut)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButActionPerformed
        if (RibbonLastMessage.networkIsUp == false) {
            RibbonLastMessage.connect();
        } else {
            RibbonLastMessage.disconnect();
        }
    }//GEN-LAST:event_connectButActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        RibbonLastMessage.disconnect();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(messageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(messageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(messageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(messageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new messageFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectBut;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane messageArea;
    private javax.swing.JTextField remoteField;
    // End of variables declaration//GEN-END:variables
}