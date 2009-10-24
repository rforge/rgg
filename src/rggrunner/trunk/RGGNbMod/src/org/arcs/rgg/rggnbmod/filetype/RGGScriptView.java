/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RGGVisualView.java
 *
 * Created on 31.10.2008, 14:14:39
 */
package org.arcs.rgg.rggnbmod.filetype;

import java.awt.Image;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.EditorKit;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author ahmet
 */
public class RGGScriptView extends javax.swing.JPanel implements
        MultiViewDescription, MultiViewElement {

    private RGGEditorSupport support;

    

    /** Creates a new instance of RGGVisualView */
    public RGGScriptView(RGGEditorSupport ed) {
        support = ed;
        initComponents();
        EditorKit kit = CloneableEditorSupport.getEditorKit("text/x-xml");
        if (kit != null) {
           editorPane.setEditorKit(kit);
        }
    }

    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ONLY_OPENED;
    }

    public String getDisplayName() {
        return "Script";
    }

    public Image getIcon() {
        return null;
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    public String preferredID() {
        return "script";
    }

    public MultiViewElement createElement() {

        return this;
    }

    public JComponent getVisualRepresentation() {
        return this;
    }

    public JComponent getToolbarRepresentation() {
        return new JPanel();
    }

    public UndoRedo getUndoRedo() {
        return null;
    }

    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }

    public Action[] getActions() {
        return support.getDataObject().
                getNodeDelegate().getActions(false);
    }

    public Lookup getLookup() {
        return ((RGGDataObject) support.getDataObject()).getNodeDelegate().getLookup();
    }

    public void componentOpened() {
    }

    public void componentClosed() {
    }

    public void componentShowing() {
    }

    public void componentHidden() {
    }

    public void componentActivated() {
        editorPane.setText(support.getEditingRGG().generateRScript());
    }

    public void componentDeactivated() {
    }

    public void setMultiViewCallback(
            MultiViewElementCallback multiViewElementCallback) {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorScrollPane = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();

        setLayout(new java.awt.BorderLayout());

        editorScrollPane.setViewportView(editorPane);

        add(editorScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JScrollPane editorScrollPane;
    // End of variables declaration//GEN-END:variables
}
