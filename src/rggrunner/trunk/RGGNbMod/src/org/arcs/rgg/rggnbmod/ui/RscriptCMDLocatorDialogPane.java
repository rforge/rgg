/*
 * RscriptCMDLocatorDialogPane.java
 *
 * Created on 14. Februar 2008, 21:30
 */
package org.arcs.rgg.rggnbmod.ui;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author  ilhami
 */
public class RscriptCMDLocatorDialogPane extends javax.swing.JPanel {

    /** Creates new form RscriptCMDLocatorDialogPane */
    public RscriptCMDLocatorDialogPane() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        finishButton = new javax.swing.JButton();
        fileChooser = new javax.swing.JFileChooser();
        errorLabel = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        locationTextField = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        headerLabel = new javax.swing.JLabel();

        finishButton.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.finishButton.text")); // NOI18N
        finishButton.setEnabled(false);

        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/arcs/rgg/rggnbmod/resources/error.gif"))); // NOI18N
        errorLabel.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.errorLabel.text")); // NOI18N
        errorLabel.setVisible(false);

        browseButton.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.browseButton.text")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        locationTextField.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.locationTextField.text")); // NOI18N
        locationTextField.setEnabled(false);

        locationLabel.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.locationLabel.text")); // NOI18N

        headerLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        headerLabel.setForeground(java.awt.Color.red);
        headerLabel.setText(org.openide.util.NbBundle.getMessage(RscriptCMDLocatorDialogPane.class, "RscriptCMDLocatorDialogPane.headerLabel.text")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(separator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .add(headerLabel)
                    .add(layout.createSequentialGroup()
                        .add(locationLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(locationTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 289, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(browseButton))
                    .add(errorLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(headerLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(separator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(locationLabel)
                    .add(locationTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(browseButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(errorLabel)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        int val = fileChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            if (checkRscriptCMD()) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        locationTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                        errorLabel.setVisible(false);
                        finishButton.setEnabled(true);
                    }
                });
            } else {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        errorLabel.setVisible(true);
                    }
                });
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private boolean checkRscriptCMD() {
        File[] files = fileChooser.getSelectedFile().listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().startsWith("Rscript")) {
                return true;
            }
        }
        return false;
    }

    public File getRscriptCMDDir() {
        return fileChooser.getSelectedFile();
    }

    public JButton getFinishButton() {
        return finishButton;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton finishButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
}
