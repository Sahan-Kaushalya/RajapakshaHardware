/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.ImageIcon;
import lk.syncx.models.kaushalya.app.findAppData;

/**
 *
 * @author Welcome
 */
public class loading extends javax.swing.JFrame {

    /**
     * Creates new form loading
     */
    public loading() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon/logo.png")).getImage());
        loadPanel();
    }

    private void loadPanel() {
        SignIn userlog = new SignIn();
        findAppData fd = new findAppData();
        fd.finding(jProgressBar1, loadingText, this, userlog);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        loadingText = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo/100logo.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 210, 220));

        loadingText.setFont(new java.awt.Font("Microsoft Tai Le", 1, 8)); // NOI18N
        loadingText.setForeground(new java.awt.Color(255, 255, 255));
        loadingText.setText("loading Text ............................");
        jPanel1.add(loadingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 550, -1));

        jProgressBar1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar1.setStringPainted(true);
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 304, 550, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo/log.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatIntelliJLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loading().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel loadingText;
    // End of variables declaration//GEN-END:variables
}
