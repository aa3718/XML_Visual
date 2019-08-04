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

    /*
    geometry(Policy policy){
        this.policy = policy;
    }
*/
    public void paint(Graphics g) {
/*
        if(policy.permissions != null) {
            policy.permissions.size();
        }
*/
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


                BufferedImage image6 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
                g.drawImage(image6, 290, 410, null);
                BufferedImage image7 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
                g.drawImage(image7, 290, 435, null);
                BufferedImage image8 = ImageIO.read(new File("/Users/Chapman/Downloads/link-3.png"));
                g.drawImage(image8, 290, 460, null);

/*
                BufferedImage image6 = ImageIO.read(new File("/Users/Chapman/Downloads/two-ways-2.png"));
                g.drawImage(image6, 270, 420, null);
*/
                BufferedImage image10 = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
                g.drawImage(image10, 220, 600, null);
                BufferedImage image11 = ImageIO.read(new File("/Users/Chapman/Downloads/right-arrow.png"));
                g.drawImage(image11, 255, 600, null);
                BufferedImage image9 = ImageIO.read(new File("/Users/Chapman/Downloads/payment-method-2.png"));
                g.drawImage(image9, 290, 600, null);

                g.drawOval(200, 200, 200, 200);

            } catch (Exception e) {
                System.out.println("Error");
            }
    }

    public static void main(String[] args) {
        JFrame frameNew = new JFrame();
        frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameNew.setSize(500, 900);
        frameNew.getContentPane().add(new geometry());
        frameNew.setVisible(true);
    }

}
