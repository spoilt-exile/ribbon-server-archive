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
 * Realease frame class;
 * @author Stanislav Nepochatov
 */
public class releaseDialog extends javax.swing.JDialog {
    
    /**
     * Message to release;
     */
    private MessageClasses.Message currMessage;
    
    /**
     * Current mode of parent editor;
     */
    private editorFrame.editMode currMode;
    
    /**
     * Original copyright string.
     */
    private String originalCopyRight;
    
    /**
     * New copyright string to apply.
     */
    public String applyCopyRight;
    
    /**
     * User list dialog object.
     */
    private userDialog userList;
    
    /**
     * User list dialog initiation flag.
     */
    private Boolean userDialogInit = false;

    /**
     * Creates new form releaseDialog
     */
    public releaseDialog(java.awt.Frame parent, boolean modal, MessageClasses.Message givenMessage, editorFrame.editMode givenMode) {
        super(parent, modal);
        initComponents();
        currMessage = givenMessage;
        currMode = givenMode;
        this.dirIndicator.setText(Generic.CsvFormat.renderGroup(currMessage.DIRS));
        this.langBox.setSelectedItem(currMessage.LANG);
        this.dirTree.setModel(DirClasses.DirEntryUI.rootDir);
        if (this.currMessage.DIRS != null) {
            javax.swing.tree.TreePath[] treePaths = new javax.swing.tree.TreePath[this.currMessage.DIRS.length];
            for (int index = 0; index < this.currMessage.DIRS.length; index++) {
                String[] pathChain = this.currMessage.DIRS[index].split("\\.");
                java.util.List<String> completePath = new java.util.ArrayList<String>();
                completePath.add(DirClasses.DirEntryUI.rootDir.DIR_NAME);
                completePath.addAll(java.util.Arrays.asList(pathChain));
                String[] pathDirChain = completePath.toArray(new String[completePath.size()]);
                DirClasses.DirEntryUI[] pathDir = new DirClasses.DirEntryUI[pathDirChain.length];
                String compDir = "";
                for (int dirIndex = 0; dirIndex < pathDirChain.length; ++dirIndex) {
                    if (dirIndex == 0) {
                        pathDir[dirIndex] = DirClasses.DirEntryUI.rootDir;
                    } else {
                        if (compDir.equals("")) {
                            compDir += pathDirChain[dirIndex];
                        } else {
                            compDir += "." + pathDirChain[dirIndex];
                        }
                        pathDir[dirIndex] = (DirClasses.DirEntryUI) DirClasses.DirEntryUI.rootDir.returnEndDir("", compDir);
                    }
                }
                treePaths[index] = new javax.swing.tree.TreePath(pathDir);
            }
            dirTree.setExpandsSelectedPaths(true);
            dirTree.getSelectionModel().setSelectionPaths(treePaths);
        }
        dirTree.expandRow(0);
        dirTree.setRootVisible(false);
        String copyRightString = this.currMessage.getCopyright();
        if (copyRightString == null) {
            this.copyLabel.setText("Немає даних щодо авторськіх прав!");
        } else {
            this.copyLabel.setText("Авторські права належать: " + copyRightString);
            this.originalCopyRight = copyRightString;
        }
        switch (currMode) {
            case POST:
                this.copyBox.setSelectedIndex(1);
                this.applyCopyRight = RibbonClient.ClientApplication.CURR_LOGIN;
                break;
            case RE_POST:
                this.copyBox.setSelectedIndex(0);
                this.applyCopyRight = this.originalCopyRight;
                this.urgentCheck.setSelected(this.currMessage.getProperty("URGENT") != null);
                break;
            case MODIFY:
                this.copyBox.setSelectedIndex(1);
                this.applyCopyRight = RibbonClient.ClientApplication.CURR_LOGIN;
                //Disable processing forbidden modification;
                this.urgentCheck.setSelected(this.currMessage.getProperty("URGENT") != null);
                this.forbidCheck.setEnabled(false);
                break;
        }
        userList = new userDialog(null, true, this);
    }
    
    /**
     * Get copyright from list of the users.
     * @return copyright string
     */
    public void getCopyrightByList() {
        if (!this.userDialogInit) {
            this.userDialogInit = true;
            userList.setVisible(true);
        } else {
            this.userDialogInit = false;
        }
        this.copyLabel.setText("Авторські права належать: " + this.applyCopyRight);
    }
    
    /**
     * Set copyright to apply.
     * @param givenCopyright copyright string to apply;
     */
    public void setApplyiedCopyright(String givenCopyright) {
        this.applyCopyRight = givenCopyright;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dirTree = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        dirIndicator = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        langBox = new javax.swing.JComboBox();
        cancelBut = new javax.swing.JButton();
        releaseBut = new javax.swing.JButton();
        copyBox = new javax.swing.JComboBox();
        copyLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        urgentCheck = new javax.swing.JCheckBox();
        forbidCheck = new javax.swing.JCheckBox();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Випуск повідомлення");
        setResizable(false);

        dirTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                dirTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(dirTree);

        jLabel1.setText("Обрані напрямки");

        dirIndicator.setEditable(false);

        jLabel2.setText("Мова повідомлення");

        langBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UA", "RU", "EN", "ES", "DE", "UKN" }));

        cancelBut.setText("Скасувати");
        cancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButActionPerformed(evt);
            }
        });

        releaseBut.setForeground(new java.awt.Color(153, 0, 0));
        releaseBut.setText("Випустити");
        releaseBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseButActionPerformed(evt);
            }
        });

        copyBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Не змінювати", "Змінити права на мене", "Змінити права за списокм" }));
        copyBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                copyBoxItemStateChanged(evt);
            }
        });

        copyLabel.setText("Авторські права належать:");

        jLabel4.setText("Авторські права");

        urgentCheck.setText("ТЕРМІНОВЕ");

        forbidCheck.setText("Заборонити обробку");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dirIndicator)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(releaseBut)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBut))
                    .addComponent(copyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(langBox, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(copyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(urgentCheck)
                                .addGap(18, 18, 18)
                                .addComponent(forbidCheck)))
                        .addGap(0, 92, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dirIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(langBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(copyBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(copyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(urgentCheck)
                            .addComponent(forbidCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelBut)
                            .addComponent(releaseBut))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dirTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_dirTreeValueChanged
        javax.swing.tree.TreePath[] pathes = dirTree.getSelectionPaths();
        String[] dirStrings = new String[pathes.length];
        for (Integer selIndex = 0; selIndex < pathes.length; selIndex++) {
            dirStrings[selIndex] = DirClasses.DirEntryUI.getEndDir(pathes[selIndex]).FULL_DIR_NAME;
        }
        this.dirIndicator.setText(Generic.CsvFormat.renderGroup(dirStrings));
    }//GEN-LAST:event_dirTreeValueChanged

    private void cancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButActionPerformed

    private void releaseButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseButActionPerformed
        String command = null;
        if (this.applyCopyRight != null && !this.applyCopyRight.equals(this.originalCopyRight)) {
            this.currMessage.setCopyright(RibbonClient.ClientApplication.CURR_LOGIN, applyCopyRight);
        }
        if (this.forbidCheck.isSelected()) {
            this.currMessage.addProperty(RibbonClient.ClientApplication.CURR_LOGIN, "PROCESSING_FORBIDDEN", null);
        }
        if (this.urgentCheck.isSelected()) {
            this.currMessage.addProperty(RibbonClient.ClientApplication.CURR_LOGIN, "URGENT", null);
        }
        switch (currMode) {
            case POST:
                command = "RIBBON_POST_MESSAGE:-1," + this.dirIndicator.getText() + "," + ((String)this.langBox.getSelectedItem()) + ",{" +
                this.currMessage.HEADER + "}," + Generic.CsvFormat.renderGroup(currMessage.TAGS) + "," + 
                Generic.CsvFormat.renderMessageProperties(currMessage.PROPERTIES) + "\n" + currMessage.CONTENT + "\nEND:";
                break;
            case MODIFY:
                MessageClasses.MessageProperty urgent = this.currMessage.getProperty("URGENT");
                if (urgent != null && !this.urgentCheck.isSelected()) {
                    this.currMessage.PROPERTIES.remove(urgent);
                }
                command = "RIBBON_MODIFY_MESSAGE:" + currMessage.INDEX + "," + this.dirIndicator.getText() + "," + 
                ((String)this.langBox.getSelectedItem()) + ",{" + currMessage.HEADER + "}," + Generic.CsvFormat.renderGroup(currMessage.TAGS) +
                "," + Generic.CsvFormat.renderMessageProperties(currMessage.PROPERTIES) + "\n" + currMessage.CONTENT + "\nEND:";
                break;
            case RE_POST:
                
                currMessage.cleanProperties();
                command = "RIBBON_POST_MESSAGE:" + currMessage.ORIG_INDEX + "," + this.dirIndicator.getText() + "," + 
                ((String)this.langBox.getSelectedItem()) + ",{" + this.currMessage.HEADER + "}," + 
                Generic.CsvFormat.renderGroup(currMessage.TAGS) + "," + Generic.CsvFormat.renderMessageProperties(currMessage.PROPERTIES) + 
                "\n" + currMessage.CONTENT + "\nEND:";
                break;
        }
        String result = RibbonClient.ClientApplication.appWorker.sendCommandWithReturn(command);
        if (result.startsWith("OK:")) {
            this.dispose();
        } else {
            RibbonClient.ClientApplication.reportError(result);
        }
    }//GEN-LAST:event_releaseButActionPerformed

    private void copyBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_copyBoxItemStateChanged
        Integer copyID = this.copyBox.getSelectedIndex();
        switch (copyID) {
            case 0:
                this.copyLabel.setText("Авторські права належать: " + this.originalCopyRight);
                this.applyCopyRight = this.originalCopyRight;
                break;
            case 1:
                this.copyLabel.setText("Авторські права належать: " + RibbonClient.ClientApplication.CURR_LOGIN);
                this.applyCopyRight = RibbonClient.ClientApplication.CURR_LOGIN;
                break;
            case 2:
                this.getCopyrightByList();
                break;
        }
    }//GEN-LAST:event_copyBoxItemStateChanged

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
            java.util.logging.Logger.getLogger(releaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(releaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(releaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(releaseDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                releaseDialog dialog = new releaseDialog(new javax.swing.JFrame(), true, new MessageClasses.Message(), editorFrame.editMode.POST);
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
    private javax.swing.JButton cancelBut;
    private javax.swing.JComboBox copyBox;
    private javax.swing.JLabel copyLabel;
    private javax.swing.JTextField dirIndicator;
    private javax.swing.JTree dirTree;
    private javax.swing.JCheckBox forbidCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox langBox;
    private javax.swing.JButton releaseBut;
    private javax.swing.JCheckBox urgentCheck;
    // End of variables declaration//GEN-END:variables
}
