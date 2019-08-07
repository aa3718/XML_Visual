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

public class geometry extends JPanel{

    public void paint(Graphics g) {

        int value = 10;

        System.out.println(value);
        change(value);
        System.out.println(value);

        g.drawRoundRect(200,200,150,150,-200,40);

        g.drawOval(200, 200, 200, 200);
        g.drawOval(100, 170, 400, 600);
        g.drawString("Permission",265,240);

        g.drawOval(200, 500, 200, 200);
        g.drawString("Duty",285,540);

        try {
            BufferedImage image = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
            g.drawImage(image, 220, 300, null);
            BufferedImage image2 = ImageIO.read(new File("/Users/Chapman/Downloads/play-button.png"));
            g.drawImage(image2, 295, 300, null);
            g.drawString("Play",290,335);
            BufferedImage image3 = ImageIO.read(new File("/Users/Chapman/Downloads/umbrella.png"));
            g.drawImage(image3, 360, 300, null);
            BufferedImage image4 = ImageIO.read(new File("/Users/Chapman/Downloads/right-arrow.png"));
            g.drawImage(image4, 255, 300, null);
            BufferedImage image5 = ImageIO.read(new File("/Users/Chapman/Downloads/right-arrow.png"));
            g.drawImage(image5, 330, 300, null);

/*
            BufferedImage image6 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
            g.drawImage(image6, 290, 410, null);
            BufferedImage image7 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
            g.drawImage(image7, 290, 435, null);
            BufferedImage image8 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
            g.drawImage(image8, 290, 460, null);
*/


            g.drawLine(300,400,300,500);
            //BufferedImage image6 = ImageIO.read(new File("/Users/Chapman/Downloads/two-ways-2.png"));
            //g.drawImage(image6, 270, 420, null);
            BufferedImage image10 = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
            g.drawImage(image10, 220, 600, null);
            BufferedImage image11 = ImageIO.read(new File("/Users/Chapman/Downloads/right-arrow.png"));
            g.drawImage(image11, 255, 600, null);
            BufferedImage image9 = ImageIO.read(new File("/Users/Chapman/Downloads/payment-method-2.png"));
            g.drawImage(image9, 290, 600, null);

            g.drawOval(200, 200, 200, 200);

            g.drawLine(230,370,200,400);

            g.drawOval(150, 400, 100, 100);

            g.drawString("Constraint",167,430);

            BufferedImage image16 = ImageIO.read(new File("/Users/Chapman/Downloads/calendar-2.png"));
            g.drawImage(image16, 190, 434, null);
            g.drawString("2011-11-11",163,475);

            //+40 +20

            } catch (Exception e) {
                System.out.println("Error");
            }
    }

    public void change(int value) {
        value += 10;
    }

    public static void main(String[] args) {
        JFrame frameNew = new JFrame();
        frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameNew.setSize(500, 900);
        frameNew.getContentPane().add(new geometry());
        frameNew.setVisible(true);
    }

}



/*
        Polygon h = new Polygon();
        for (int i = 0; i < 6; i++){
            h.addPoint((int) (105 + 95 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (290 + 80 * Math.sin(i * 2 * Math.PI / 6)));
        }

        g.drawPolygon(h);
        g.drawRect(5,5,200,400);
        g.drawString("Permission",30,20);
        //g.drawRoundRect(25,220,175,180, 35,35);
        g.drawRect(25,25,175,175);
        //g.drawRect(25,220,175,180);
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
            g.drawImage(image10, 60, 255, null);
            g.drawString("Assignee",85,270);

            BufferedImage image9 = ImageIO.read(new File("/Users/Chapman/Downloads/payment-method.png"));
            g.drawImage(image9, 60, 295, null);
            g.drawString("Pay",85,310);


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
        */
