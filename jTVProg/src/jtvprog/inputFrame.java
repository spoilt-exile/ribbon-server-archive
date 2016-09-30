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
 * Input frame class
 * @author Stanislav Nepochatov
 */
public class inputFrame extends javax.swing.JFrame {

    /** Creates new form inputFrame */
    public inputFrame() {
        initComponents();
        JTVProg.mainWindow.tvFillBut.setEnabled(false);
        JTVProg.mainWindow.lockMenues();
        if (JTVProg.configer.ChannelProcessor == null) {
            JTVProg.logPrint(this, 3, "начало заполнения каналов");
            JTVProg.configer.ChannelProcessor = new chProcSet(JTVProg.configer.Channels.pullList());
            JTVProg.configer.ChannelProcessor.beginInput();
            this.infoLabel.setText(JTVProg.configer.ChannelProcessor.currentChName + "[" + JTVProg.configer.ChannelProcessor.currentIndex + "/" + JTVProg.configer.ChannelProcessor.getSetSize() + "]");
            this.inputPane.setText(JTVProg.configer.ChannelProcessor.getCurrentContent());
        } else {
            JTVProg.logPrint(this, 3, "возобновление заполнения каналов");
            this.infoLabel.setText(JTVProg.configer.ChannelProcessor.currentChName + "[" + JTVProg.configer.ChannelProcessor.currentIndex + "/" + JTVProg.configer.ChannelProcessor.getSetSize() + "]");
            this.inputPane.setText(JTVProg.configer.ChannelProcessor.getCurrentContent());
            if (JTVProg.configer.ChannelProcessor.currentIndex == 1) {
                this.prevBut.setEnabled(false);
                this.nextBut.setEnabled(true);
            } else if (JTVProg.configer.ChannelProcessor.currentIndex == JTVProg.configer.ChannelProcessor.getSetSize()) {
                this.nextBut.setEnabled(false);
                this.finishBut.setEnabled(true);
            } else {
                this.prevBut.setEnabled(true);
                this.nextBut.setEnabled(true);
            }
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
        inputPane = new javax.swing.JTextPane();
        nextBut = new javax.swing.JButton();
        prevBut = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        pasteBut = new javax.swing.JButton();
        flushBut = new javax.swing.JButton();
        finishBut = new javax.swing.JButton();
        passBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Заполнение каналов");
        setIconImage(JTVProg.configer.jtvprogIcon);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setViewportView(inputPane);

        nextBut.setText("Следующий >>");
        nextBut.setMaximumSize(new java.awt.Dimension(154, 25));
        nextBut.setMinimumSize(new java.awt.Dimension(154, 25));
        nextBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButActionPerformed(evt);
            }
        });

        prevBut.setText("<< Предыдущий");
        prevBut.setEnabled(false);
        prevBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButActionPerformed(evt);
            }
        });

        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLabel.setText("Информация");
        infoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        pasteBut.setText("Вставить");
        pasteBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteButActionPerformed(evt);
            }
        });

        flushBut.setText("Очистить");
        flushBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flushButActionPerformed(evt);
            }
        });

        finishBut.setText("Завершить ввод");
        finishBut.setEnabled(false);
        finishBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButActionPerformed(evt);
            }
        });

        passBox.setText("Пропустить канал");
        passBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                passBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextBut, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pasteBut)
                        .addGap(18, 18, 18)
                        .addComponent(flushBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finishBut)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevBut)
                    .addComponent(nextBut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasteBut)
                    .addComponent(flushBut)
                    .addComponent(finishBut)
                    .addComponent(passBox))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButActionPerformed
        if (1 <= JTVProg.configer.ChannelProcessor.currentIndex && JTVProg.configer.ChannelProcessor.currentIndex <= JTVProg.configer.ChannelProcessor.getSetSize()) {
            String errorMessage;
            if ((errorMessage = JTVProg.configer.ChannelProcessor.checkInputDP(this.inputPane.getText())) != null) {
                JTVProg.warningMessage(errorMessage);
            }
            else {
                JTVProg.configer.ChannelProcessor.performInput(this.inputPane.getText());
                JTVProg.configer.ChannelProcessor.inputNext();
                this.prevBut.setEnabled(true);
                this.infoLabel.setText(JTVProg.configer.ChannelProcessor.currentChName + "[" + JTVProg.configer.ChannelProcessor.currentIndex + "/" + JTVProg.configer.ChannelProcessor.getSetSize() + "]");
                this.inputPane.setText(JTVProg.configer.ChannelProcessor.getCurrentContent());
                this.passBox.setSelected(JTVProg.configer.ChannelProcessor.currentUnit.isPassedNull);
            }
            if (JTVProg.configer.ChannelProcessor.currentIndex == JTVProg.configer.ChannelProcessor.getSetSize()) {
                this.finishBut.setEnabled(true);
                this.nextBut.setEnabled(false);
            }
        }
    }//GEN-LAST:event_nextButActionPerformed

    private void prevButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButActionPerformed
        if (!inputPane.getText().isEmpty()) {
            JTVProg.configer.ChannelProcessor.performInput(this.inputPane.getText());
            JTVProg.configer.markWrited(JTVProg.configer.ChannelProcessor.currentIndex - 1);
        }
        JTVProg.configer.ChannelProcessor.inputPrev();
        this.infoLabel.setText(JTVProg.configer.ChannelProcessor.currentChName + "[" + JTVProg.configer.ChannelProcessor.currentIndex + "/" + JTVProg.configer.ChannelProcessor.getSetSize() + "]");
        this.inputPane.setText(JTVProg.configer.ChannelProcessor.getCurrentContent());
        this.passBox.setSelected(JTVProg.configer.ChannelProcessor.currentUnit.isPassedNull);
        if (this.nextBut.isEnabled() == false) {
            this.nextBut.setEnabled(true);
        }
        if (this.nextBut.getText().equals("Завершить X")) {
            this.nextBut.setText("Следующий >>");
        }
        if (JTVProg.configer.ChannelProcessor.currentIndex == 1) {
            this.prevBut.setEnabled(false);
        }
    }//GEN-LAST:event_prevButActionPerformed

    private void pasteButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteButActionPerformed
        this.inputPane.setText(JTVProg.cilper.getClipboardContents());
    }//GEN-LAST:event_pasteButActionPerformed

    private void flushButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flushButActionPerformed
        this.inputPane.setText("");
    }//GEN-LAST:event_flushButActionPerformed

    private void finishButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButActionPerformed
        String errorMessage;
        if ((errorMessage = JTVProg.configer.ChannelProcessor.checkInputDP(this.inputPane.getText())) != null) {
            JTVProg.warningMessage(errorMessage);
        } else {
            JTVProg.configer.ChannelProcessor.performInput(this.inputPane.getText());
            JTVProg.configer.markWrited(JTVProg.configer.ChannelProcessor.currentIndex - 1);
            JTVProg.configer.ChannelProcessor.endInput();
            this.dispose();
        }
    }//GEN-LAST:event_finishButActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JTVProg.configer.ChannelProcessor.isInputOver()) {
            JTVProg.logPrint(this, 3, "отмена заполнения каналов. Состаяние сохранено.");
            JTVProg.mainWindow.tvFillBut.setEnabled(true);
        } else {
            JTVProg.logPrint(this, 3, "окончание заполнения каналов.");
        }
    }//GEN-LAST:event_formWindowClosing

    private void passBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_passBoxItemStateChanged
        Boolean flag = this.passBox.isSelected();
        JTVProg.configer.ChannelProcessor.currentUnit.isPassedNull = flag;
        JTVProg.setPass(JTVProg.configer.ChannelProcessor.currentUnit.chName, flag);
        this.inputPane.setEditable(!flag);
    }//GEN-LAST:event_passBoxItemStateChanged

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
            java.util.logging.Logger.getLogger(inputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new inputFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finishBut;
    private javax.swing.JButton flushBut;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JTextPane inputPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextBut;
    private javax.swing.JCheckBox passBox;
    private javax.swing.JButton pasteBut;
    private javax.swing.JButton prevBut;
    // End of variables declaration//GEN-END:variables
}