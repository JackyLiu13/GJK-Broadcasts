/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gjkbroadcasts;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDate;


/**
 *
 * @author Jacky
 */
public class AnnouncementPnl extends javax.swing.JPanel  {

    /**
     * Creates new form announcementPanel
     * 
     */
  
    
    public AnnouncementPnl(){
        initComponents();
     
//    textArea1.setEditable(false);
//    read();
    }
    
   public void read(){
   try {
      File newfile = new File("ANNOUNCEMENTS.txt");
      Scanner reader = new Scanner(newfile);
      reader.useDelimiter("+");
      
      while (reader.hasNext()) {
        textArea1.append("\n" + reader.nextLine());
        
        String data = reader.next();
        System.out.println(data);
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
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

        annoucementsLbl = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        annoucementsLbl1 = new javax.swing.JLabel();

        annoucementsLbl.setFont(new java.awt.Font("Lucida Grande", 3, 18)); // NOI18N
        annoucementsLbl.setText("Annoucements:");

        annoucementsLbl1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        annoucementsLbl1.setText("Good Morning Eagles!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(annoucementsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(annoucementsLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(annoucementsLbl)
                .addGap(14, 14, 14)
                .addComponent(annoucementsLbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel annoucementsLbl;
    private javax.swing.JLabel annoucementsLbl1;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
