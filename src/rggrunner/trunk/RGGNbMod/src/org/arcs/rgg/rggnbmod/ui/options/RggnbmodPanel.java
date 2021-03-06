
package org.arcs.rgg.rggnbmod.ui.options;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import org.arcs.rgg.rggnbmod.common.RGGProcess;
import org.openide.util.NbPreferences;

final class RggnbmodPanel extends javax.swing.JPanel {

    private final RggnbmodOptionsPanelController controller;
    private boolean valid=true;
    
    RggnbmodPanel(RggnbmodOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        binDirChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        locationTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        binDirChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, "R /bin directory:");

        locationTextField.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, "Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/arcs/rgg/rggnbmod/resources/error.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, "Rscript not found in !"+binDirChooser.getSelectedFile());
        errorLabel.setVisible(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, "(e.g. \"C:\\Programme\\R\\R-2.7.0\\bin\" on Windows)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(errorLabel))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(locationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        int val = binDirChooser.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            if (checkRscriptCMD(binDirChooser)) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        locationTextField.setText(binDirChooser.getSelectedFile().getAbsolutePath());
                        errorLabel.setVisible(false);
                    }
                });
            }else {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        errorLabel.setText("RScript command not found in "
                                +binDirChooser.getSelectedFile()+"!");
                        errorLabel.setVisible(true);
                    }
                });
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed
    
    private boolean checkRscriptCMD(JFileChooser fileChooser) {
        File[] files = fileChooser.getSelectedFile().listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().startsWith("Rscript")) {
                valid=true;
                return true;
            }
        }
        return false;
    }
    
    void load() {
    // TODO read settings and initialize GUI
    // Example:        
    // someCheckBox.setSelected(Preferences.userNodeForPackage(RggnbmodPanel.class).getBoolean("someFlag", false));
    // or for org.openide.util with API spec. version >= 7.4:
     locationTextField.setText(NbPreferences.root().node("org/arcs/rgg/rggnbmod").get("RSCRIPTCMDDIR", ""));     
    // or:
    // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
    }

    void store() {
    // TODO store modified settings
    // Example:
    // Preferences.userNodeForPackage(RggnbmodPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
    // or for org.openide.util with API spec. version >= 7.4:
     NbPreferences.root().node("org/arcs/rgg/rggnbmod").put("RSCRIPTCMDDIR", locationTextField.getText());
    // or:
    // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
     RGGProcess.setRscriptCmdDir(NbPreferences.root().node("org/arcs/rgg/rggnbmod").get("RSCRIPTCMDDIR", ""));
    }

    boolean valid() {
        return valid;
    }

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser binDirChooser;
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField locationTextField;
    // End of variables declaration//GEN-END:variables
}
