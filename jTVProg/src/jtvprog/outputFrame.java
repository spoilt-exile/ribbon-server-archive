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

import ribbon.RibbonReleaser;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Release window class
 * @author spoilt
 */
public class outputFrame extends javax.swing.JDialog {

    /**
     * Default model for releaseList
     */
    private DefaultListModel releaseModel = new DefaultListModel();
    
    /** Creates new form outputFrame */
    public outputFrame(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        JTVProg.configer.ChannelProcessor.beginOutput();
        buildList();
        if (JTVProg.isPass()) {
            JTVProg.warningMessage("В данном выпуске пропущены каналы:\n" + JTVProg.getPassList() + "\nОбязательно перевыпустите программу позже.");
        }
    }
    
    /**
     * Build list with current operation list from channel processor and apply it
     */
    private void buildList() {
        if (releaseModel.isEmpty() == false) {
            releaseModel.removeAllElements();
        }
        java.util.ListIterator releaseItems = JTVProg.configer.ChannelProcessor.operOutHeaders.listIterator();
        while (releaseItems.hasNext()) {
            releaseModel.addElement(releaseItems.next());
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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        releaseText = new javax.swing.JTextArea();
        releaseLabel = new javax.swing.JLabel();
        outPrevBut = new javax.swing.JButton();
        outNextBut = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        modeBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        releaseList = new javax.swing.JList();
        ribbonReleaseBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Выпуск телепрограммы");

        jSplitPane1.setDividerLocation(200);

        releaseText.setColumns(20);
        releaseText.setEditable(false);
        releaseText.setRows(5);
        jScrollPane1.setViewportView(releaseText);

        releaseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        outPrevBut.setText("<");
        outPrevBut.setEnabled(false);
        outPrevBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outPrevButActionPerformed(evt);
            }
        });

        outNextBut.setText(">");
        outNextBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outNextButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(outPrevBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(releaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outNextBut)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outNextBut)
                    .addComponent(releaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outPrevBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        modeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "По каналам", "По дням" }));
        modeBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                modeBoxItemStateChanged(evt);
            }
        });

        releaseList.setModel(this.releaseModel);
        releaseList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                releaseListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(releaseList);

        ribbonReleaseBut.setText("Выпустить в систему");
        ribbonReleaseBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ribbonReleaseButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(modeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ribbonReleaseBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ribbonReleaseBut)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_modeBoxItemStateChanged
        JTVProg.configer.ChannelProcessor.setOutputMode(this.modeBox.getSelectedIndex());
        this.buildList();
        //this.releaseList.setSelectedIndex(0);
    }//GEN-LAST:event_modeBoxItemStateChanged

    private void releaseListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_releaseListValueChanged
        if (this.releaseList.getSelectedIndex() >= 0) {
            if (this.releaseList.getSelectedIndex() == 0) {
                this.outPrevBut.setEnabled(false);
            } else if (this.releaseList.getSelectedIndex() == (this.releaseModel.getSize() - 1)) {
                this.outNextBut.setEnabled(false);
            } else {
                this.outPrevBut.setEnabled(true);
                this.outNextBut.setEnabled(true);
            }
            JTVProg.configer.ChannelProcessor.currentIndex = this.releaseList.getSelectedIndex();
            JTVProg.configer.ChannelProcessor.currentChName = JTVProg.configer.ChannelProcessor.operOutStack.get(this.releaseList.getSelectedIndex());
            this.releaseLabel.setText(JTVProg.configer.ChannelProcessor.operOutHeaders.get(this.releaseList.getSelectedIndex()));
            this.releaseText.setText(JTVProg.configer.ChannelProcessor.operOutStack.get(this.releaseList.getSelectedIndex()));
            JTVProg.cilper.setClipboardContents(JTVProg.configer.ChannelProcessor.operOutStack.get(this.releaseList.getSelectedIndex()));
        }
    }//GEN-LAST:event_releaseListValueChanged

    private void outNextButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outNextButActionPerformed
        JTVProg.configer.ChannelProcessor.outputNext();
        this.releaseList.setSelectedIndex(JTVProg.configer.ChannelProcessor.currentIndex);
    }//GEN-LAST:event_outNextButActionPerformed

    private void outPrevButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outPrevButActionPerformed
        JTVProg.configer.ChannelProcessor.outputPrev();
        this.releaseList.setSelectedIndex(JTVProg.configer.ChannelProcessor.currentIndex);
    }//GEN-LAST:event_outPrevButActionPerformed

    private void ribbonReleaseButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ribbonReleaseButActionPerformed
        final RibbonReleaser releaser = new RibbonReleaser();
        Object[] options = {"Выпустить", "Отмена"};
        Integer result = javax.swing.JOptionPane.showOptionDialog(this,
            releaser.renderMessage(),
            "Выпуск в систему...",
            javax.swing.JOptionPane.YES_NO_CANCEL_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);
        if (result.equals(1)) {
            return;
        }
        if (JTVProg.tvApp.isInited) {
            releaser.release();
        } else {
            JTVProg.tvApp.connect(AppComponents.NetWorker.class, new Runnable() {

                @Override
                public void run() {
                    releaser.release();
                    Boolean resStat = releaser.getErrStatus();
                    if (resStat) {
                        JTVProg.warningMessage(releaser.getStatus());
                    } else {
                        final JPanel panel = new JPanel();
                        JOptionPane.showMessageDialog(panel, releaser.getStatus(), "Выпуск", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                
            });
        }
    }//GEN-LAST:event_ribbonReleaseButActionPerformed

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
            java.util.logging.Logger.getLogger(outputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(outputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(outputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(outputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                outputFrame dialog = new outputFrame(new javax.swing.JFrame());
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox modeBox;
    private javax.swing.JButton outNextBut;
    private javax.swing.JButton outPrevBut;
    private javax.swing.JLabel releaseLabel;
    private javax.swing.JList releaseList;
    private javax.swing.JTextArea releaseText;
    private javax.swing.JButton ribbonReleaseBut;
    // End of variables declaration//GEN-END:variables
}