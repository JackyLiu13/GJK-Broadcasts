/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gjkbroadcasts;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Kenny
 */
public class WeatherPnl extends javax.swing.JPanel {

    ImageIcon weatherSunImage = new ImageIcon("sun.png");
    ImageIcon weatherCloudImage = new ImageIcon("cloud.png");
    ImageIcon weatherPartlySunnyImage = new ImageIcon("partlysunny.png");
    ImageIcon weatherRainingImage = new ImageIcon("raining.png");
    ImageIcon weatherThunderImage = new ImageIcon("thunder.png");
    ImageIcon weatherSnowImage = new ImageIcon("snow.png");

    String[] splitResult;
    BufferedImage image = null;
    String result1;

    /**
     * Creates new form WeatherPnl
     */
    public WeatherPnl() {
        initComponents();
        weatherGet();
        
        Font f = new Font("Alba Super", Font.ITALIC + Font.BOLD, 18);
        jWeatherLabelPicture.setText("");
        weatherDisplayLbl.setFont(f);
        weatherDisplayLbl.setText("<html>" + "Location:" + readLocation() + "<br/>" +splitResult[3] + "<br/>" + splitResult[4] + "<br/>" + splitResult[7] + "<br/>" + splitResult[8] + "<br/>" + splitResult[14] + "</html>");
        weatherCheckImages();
        weatherDisplayLbl.repaint();
        jWeatherLabelPicture.repaint();


    }

    public void weatherCheckImages() {
        if (splitResult[3].equals("main:Clear")) {
            jWeatherLabelPicture.setIcon(weatherSunImage);
        }
        if (splitResult[3].equals("main:Thunder")) {
            jWeatherLabelPicture.setIcon(weatherThunderImage);
        }
        if (splitResult[3].equals("main:Clouds")) {
            jWeatherLabelPicture.setIcon(weatherCloudImage);
        }
        if (splitResult[3].equals("main:Snow")) {
            jWeatherLabelPicture.setIcon(weatherSnowImage);
        }
        if (splitResult[3].equals("main:Raining")) {
            jWeatherLabelPicture.setIcon(weatherRainingImage);
        }
        if (splitResult[3].equals("main:partly Sunny")) {
            jWeatherLabelPicture.setIcon(weatherPartlySunnyImage);
        }
    }

    public void weatherGet() {
        String apiKey = "30c6df4b6f586ef179d19b567be776ec";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + readLocation() + "&units=metric" + "&appid=" + apiKey;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherPnl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner s = null;
        try {
            s = new Scanner(url.openStream());
        } catch (IOException ex) {
            Logger.getLogger(WeatherPnl.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuffer sb = new StringBuffer();
        int counter = 0;
        while (s.hasNext()) {
            sb.append(s.next());
            counter++;
        }

        result1 = sb.toString();
        String finalWeatherString = result1.replace("\"", "");
        splitResult = finalWeatherString.split(",");
    }

    public static String readLocation() {

        String location = "";

        Scanner s = null;
        try {
            s = new Scanner(new File("location.txt")); //reads in the weather
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WeatherPnl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //check is username has been used before
        if (s.hasNext()) {
            location = s.nextLine(); //split the line to have strings if same as text box
        }
        return location;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jWeatherLabelPicture = new javax.swing.JLabel();
        weatherDisplayLbl = new javax.swing.JLabel();

        jWeatherLabelPicture.setText("jLabel1");

        weatherDisplayLbl.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(weatherDisplayLbl)
                .addGap(112, 112, 112)
                .addComponent(jWeatherLabelPicture)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weatherDisplayLbl)
                    .addComponent(jWeatherLabelPicture))
                .addContainerGap(120, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jWeatherLabelPicture;
    private javax.swing.JLabel weatherDisplayLbl;
    // End of variables declaration//GEN-END:variables
}
