/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Desktop;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.net.URI;

/**
 *
 * @author User
 */
public class DialogAbout2 extends javax.swing.JPanel {
    
    private NewJFrame parent;
    private GlassPane2 glassPane;

    /**
     * Creates new form DialogAbout2
     */
    public DialogAbout2(NewJFrame parent) {
        this.parent = parent; //переменной parent присваиваем то, что было передано в параметрах
        initComponents(); // проводим инициализацию
        setSize(500, 236);//задаем размер окна About
        
        parent.setControlsFocusable(false);
        glassPane = new GlassPane2(); //создаем объект с именем glassPane
        glassPane.add(this); //добавляем к нему этот компонент (в данном случае dialogAbout)
        
        int x = (parent.getWidth()/2) - getWidth()/2;
        int y = (parent.getHeight()/2) - getHeight()/2;
        setBounds(x, y, getWidth(), getHeight()); //размещаем по центру родительского окна
        parent.setGlassPane(glassPane);//Устанавливаем свойства glassPane
        glassPane.setVisible(true);//делаем glassPane видимой
    }
    
    private void closeDialog() {
        parent.getGlassPane().setVisible(false);
        parent.setControlsFocusable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeaderPanel = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jHeaderLabel = new javax.swing.JLabel();
        jDataPanel = new javax.swing.JPanel();
        jLabelMessage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(500, 309));

        jHeaderPanel.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jHeaderPanel.setPreferredSize(new java.awt.Dimension(286, 30));

        jHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jHeaderLabel.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jHeaderLabel.setText("About jSSC-Terminal");

        javax.swing.GroupLayout jHeaderPanelLayout = new javax.swing.GroupLayout(jHeaderPanel);
        jHeaderPanel.setLayout(jHeaderPanelLayout);
        jHeaderPanelLayout.setHorizontalGroup(
            jHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabel)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        jHeaderPanelLayout.setVerticalGroup(
            jHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDataPanel.setBackground(new java.awt.Color(231, 233, 237));
        jDataPanel.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jDataPanel.setPreferredSize(new java.awt.Dimension(276, 75));

        jLabelMessage.setFont(NimbusGui.DEFAULT_FONT);
        jLabelMessage.setText("<html>\n&nbsp;&nbsp;This is a free and open source (GPL3 license) application that demonstrate some functions of jSSC (Java Simple Serial Connector) library. You can use this terminal for your work and make changes in source code (under terms of GPL3 license).<br><br>\n\n<center><strong>Based on jSSC-0.8-tb2</strong><br><br></center>\n\n<center>Author: Sokolov Alexey (scream3r)</center>\n</html>");

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 2));

        jButtonClose.setFont(NimbusGui.DEFAULT_FONT);
        jButtonClose.setText("Close");
        jButtonClose.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        jButtonClose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonCloseKeyPressed(evt);
            }
        });

        jLabel1.setFont(NimbusGui.DEFAULT_FONT);
        jLabel1.setText("<html><a href=\\\"http://scream3r.org\\\">http://scream3r.org</a></html>");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jDataPanelLayout = new javax.swing.GroupLayout(jDataPanel);
        jDataPanel.setLayout(jDataPanelLayout);
        jDataPanelLayout.setHorizontalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabelMessage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDataPanelLayout.setVerticalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jHeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        // TODO add your handling code here:
        closeDialog();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonCloseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCloseKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
        else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jButtonClose.doClick();
        }
    }//GEN-LAST:event_jButtonCloseKeyPressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("http://scream3r.org"));
            }
            catch (Exception ex) {
                //Do nothing
            }
        }
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JPanel jDataPanel;
    private javax.swing.JLabel jHeaderLabel;
    private javax.swing.JPanel jHeaderPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}