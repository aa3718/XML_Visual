package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.text.AttributedCharacterIterator;
import java.awt.image.BufferedImage;
import java.io.File;


public class geometry2 extends JPanel {


    public void paint(Graphics g) {

        g.drawRect(5,5,200,400);
        g.drawString("Permission",20,20);

        g.drawRect(25,25,175,175);
        g.drawRect(25,220,175,180);
        g.drawString("Duty",20,215);

        g.drawRect(210,5,200,400);
        g.drawString("Prohibition",225,20);

        g.drawRect(230,25,175,175);


        try {
            BufferedImage image = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
            g.drawImage(image,35,30,null);
            g.drawString("Assignee",60,45);

            BufferedImage image2 = ImageIO.read(new File("/Users/Chapman/Downloads/play-button.png"));
            g.drawImage(image2, 35, 70, null);
            g.drawString("Play",60,85);

            BufferedImage image3 = ImageIO.read(new File("/Users/Chapman/Downloads/umbrella.png"));
            g.drawImage(image3, 35, 110, null);
            g.drawString("Movie",60,125);

            BufferedImage image10 = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
            g.drawImage(image10, 35, 225, null);
            g.drawString("Assignee",60,240);

            BufferedImage image9 = ImageIO.read(new File("/Users/Chapman/Downloads/payment-method.png"));
            g.drawImage(image9, 35, 265, null);
            g.drawString("Pay",60,280);


            g.drawLine(25,145,200,145);
            g.drawLine(25,147,200,147);
            g.drawString("Constraint",30,165);

            BufferedImage image12 = ImageIO.read(new File("/Users/Chapman/Downloads/calendar-2.png"));
            g.drawImage(image12, 35, 170, null);
            g.drawString("2011-11-11",70,190);

            BufferedImage image13 = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
            g.drawImage(image13,240,30,null);
            g.drawString("Assignee",275,45);

            BufferedImage image14 = ImageIO.read(new File("/Users/Chapman/Downloads/play-button.png"));
            g.drawImage(image14, 240, 70, null);
            g.drawString("Play",275,85);

            BufferedImage image15 = ImageIO.read(new File("/Users/Chapman/Downloads/umbrella.png"));
            g.drawImage(image15, 240, 110, null);
            g.drawString("Movie",275,125);

            g.drawLine(230,145,405,145);
            g.drawLine(230,147,405,147);
            g.drawString("Constraint",235,165);

            BufferedImage image16 = ImageIO.read(new File("/Users/Chapman/Downloads/placeholder-2.png"));
            g.drawImage(image16, 240, 170, null);
            g.drawString("tML-ISO-3166:fr",280,190);


        } catch (Exception e) {
            System.out.println("Error");
        }

    }

        public static void main (String[]args){
            JFrame frameNew = new JFrame();
            frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameNew.setSize(500, 900);
            frameNew.getContentPane().add(new geometry2());
            frameNew.setVisible(true);
        }
}
