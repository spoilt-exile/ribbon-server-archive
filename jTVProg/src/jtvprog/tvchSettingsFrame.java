/**
 * This file is part of JTVProg application (check README).
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

package jtvprog;

/**
 * Release order and channels modify window.
 * @author Stanislav Nepochatov
 */
public class tvchSettingsFrame extends javax.swing.JDialog {
    
    /**
     * Local channel set
     */
    private chSet localSet = new jtvprog.chSet(JTVProg.configer.Channels.pullList());
    
    /**
     * Default channel list model;
     */
    private javax.swing.DefaultListModel chListModel = new javax.swing.DefaultListModel();

    /** Creates new form tvchSettings */
    public tvchSettingsFrame(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.fillChannelList();
        JTVProg.logPrint(this, 3, "показ окна");
    }
    
    /**
     * Fill jList by channels;
     */
    private void fillChannelList() {
        for (Integer chCounter = 1; chCounter < localSet.getSetSize() + 1; chCounter++) {
            chListModel.addElement(localSet.getChannelByFOrder(chCounter));
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
        ChannelList = new javax.swing.JList();
        addBut = new javax.swing.JButton();
        editBut = new javax.swing.JButton();
        delBut = new javax.swing.JButton();
        cancelBut = new javax.swing.JButton();
        saveBut = new javax.swing.JButton();
        moveUpBut = new javax.swing.JButton();
        moveDownBut = new javax.swing.JButton();
        channelNameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        chNameField = new javax.swing.JTextField();
        chFileField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Настройка каналов");

        ChannelList.setModel(this.chListModel);
        ChannelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ChannelListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ChannelList);

        addBut.setText("Добавить");
        addBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButActionPerformed(evt);
            }
        });

        editBut.setText("Редактировать");
        editBut.setEnabled(false);
        editBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButActionPerformed(evt);
            }
        });

        delBut.setText("Удалить");
        delBut.setEnabled(false);
        delBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButActionPerformed(evt);
            }
        });

        cancelBut.setText("Отмена");
        cancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButActionPerformed(evt);
            }
        });

        saveBut.setText("Сохранить");
        saveBut.setEnabled(false);
        saveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButActionPerformed(evt);
            }
        });

        moveUpBut.setText("↑");
        moveUpBut.setEnabled(false);
        moveUpBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButActionPerformed(evt);
            }
        });

        moveDownBut.setText("↓");
        moveDownBut.setEnabled(false);
        moveDownBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButActionPerformed(evt);
            }
        });

        channelNameLabel.setText("Канал");

        jLabel1.setText("Файл");

        chNameField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chNameFieldCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveBut)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBut))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(addBut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editBut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(channelNameLabel)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chFileField)
                                    .addComponent(chNameField))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moveDownBut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveUpBut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(channelNameLabel)
                    .addComponent(chNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(chFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBut)
                    .addComponent(editBut)
                    .addComponent(delBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moveUpBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(moveDownBut))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBut)
                    .addComponent(saveBut))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChannelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ChannelListValueChanged
        Integer selectedIndex = this.ChannelList.getSelectedIndex();
        this.editBut.setEnabled(true);
        this.delBut.setEnabled(true);
        this.chNameField.setText(this.localSet.getChannelByFOrder(selectedIndex + 1));
        this.chFileField.setText(this.localSet.getFilenameByFOrder(selectedIndex + 1));
        if (selectedIndex == 0) {
            this.moveUpBut.setEnabled(false);
            this.moveDownBut.setEnabled(true);
        }
        else if (selectedIndex == this.localSet.getSetSize() - 1) {
            this.moveUpBut.setEnabled(true);
            this.moveDownBut.setEnabled(false);
        }
        else {
            this.moveUpBut.setEnabled(true);
            this.moveDownBut.setEnabled(true);
        }
    }//GEN-LAST:event_ChannelListValueChanged

    private void moveUpButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButActionPerformed
        Integer selectedIndex = this.ChannelList.getSelectedIndex();
        if (selectedIndex != 0) {
            Object aObject = chListModel.getElementAt(selectedIndex);
            Object bObject = chListModel.getElementAt(selectedIndex - 1);
            chListModel.set(selectedIndex, bObject);
            chListModel.set(selectedIndex - 1, aObject);
            this.ChannelList.setSelectedIndex(selectedIndex - 1);
            this.ChannelList.ensureIndexIsVisible(selectedIndex - 1);
            JTVProg.logPrint(this, 3, "сдвиг канала [" + (String) this.ChannelList.getSelectedValue() + "] на позицию вверх");
            this.localSet.moveChannelFillUp(selectedIndex);
            this.saveBut.setEnabled(true);
        }
        else {
            JTVProg.logPrint(this, 2, "канал уже наверху!");
        }
    }//GEN-LAST:event_moveUpButActionPerformed

    private void moveDownButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButActionPerformed
        Integer selectedIndex = this.ChannelList.getSelectedIndex();
        if (selectedIndex != chListModel.getSize() - 1) {
            Object aObject = chListModel.getElementAt(selectedIndex);
            Object bObject = chListModel.getElementAt(selectedIndex + 1);
            chListModel.set(selectedIndex, bObject);
            chListModel.set(selectedIndex + 1, aObject);
            this.ChannelList.setSelectedIndex(selectedIndex + 1);
            this.ChannelList.ensureIndexIsVisible(selectedIndex + 1);
            JTVProg.logPrint(this, 3, "сдвиг канала [" + (String) this.ChannelList.getSelectedValue() + "] на позицию вниз");
            this.localSet.moveChannelFillDown(selectedIndex);
            this.saveBut.setEnabled(true);
        }
        else {
            JTVProg.logPrint(this, 2, "канал уже внизу!");
        }
    }//GEN-LAST:event_moveDownButActionPerformed

    private void cancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButActionPerformed

    private void saveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButActionPerformed
        JTVProg.logPrint(this, 2, "обновление списка каналов:\n" + localSet.toString());
        JTVProg.configer.Channels.pushList(localSet.pullList());
        JTVProg.configer.createMainTable();
        this.dispose();
    }//GEN-LAST:event_saveButActionPerformed

    private void addButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButActionPerformed
        this.localSet.addChannel(this.chNameField.getText(), this.localSet.getSetSize() + 1, this.localSet.getSetSize() + 1, this.chFileField.getText());
        this.chListModel.addElement(this.chNameField.getText());
        this.saveBut.setEnabled(true);
    }//GEN-LAST:event_addButActionPerformed

    private void editButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButActionPerformed
        Integer selectedIndex = this.ChannelList.getSelectedIndex();
        this.localSet.setChannelStats(selectedIndex + 1, this.chNameField.getText(), this.chFileField.getText());
        this.chListModel.set(selectedIndex, this.chNameField.getText());
        this.saveBut.setEnabled(true);
    }//GEN-LAST:event_editButActionPerformed

    private void chNameFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chNameFieldCaretUpdate
        this.chFileField.setText(this.chNameField.getText().toLowerCase().replaceAll(" ", "_").replaceAll("\"", "") + ".txt");
    }//GEN-LAST:event_chNameFieldCaretUpdate

    private void delButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButActionPerformed
        Integer selectedIndex = this.ChannelList.getSelectedIndex();
        this.chListModel.removeElementAt(selectedIndex);
        this.localSet.removeChannel(this.localSet.getChannelByFOrder(selectedIndex + 1));
        this.saveBut.setEnabled(true);
    }//GEN-LAST:event_delButActionPerformed

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
            java.util.logging.Logger.getLogger(tvchSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tvchSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tvchSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tvchSettingsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                tvchSettingsFrame dialog = new tvchSettingsFrame(new javax.swing.JFrame());
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
    private javax.swing.JList ChannelList;
    private javax.swing.JButton addBut;
    private javax.swing.JButton cancelBut;
    private javax.swing.JTextField chFileField;
    private javax.swing.JTextField chNameField;
    private javax.swing.JLabel channelNameLabel;
    private javax.swing.JButton delBut;
    private javax.swing.JButton editBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton moveDownBut;
    private javax.swing.JButton moveUpBut;
    private javax.swing.JButton saveBut;
    // End of variables declaration//GEN-END:variables
}