package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class situational extends JComponent {
    private Policy policy;
    private int sizeOfCircles = 300;
    private int edgeElement;
    private Color colorPermission;
    private Color colorProhibition;
    private Color colorConstraint;
    private Color colorDuty;

    private int numberOfTotalElementsPolicy;


    situational(Policy policy) {
       this.policy = policy;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions();


        //for (int i = 0; i < numberOfTotalElementsPolicy; i++) {
            g.drawOval(260, 5, sizeOfCircles, sizeOfCircles);
            g.drawOval(260 + (sizeOfCircles / 2) - ((sizeOfCircles - 80) / 2), 45, sizeOfCircles - 80, sizeOfCircles - 80);
            edgeElement = 260 + (sizeOfCircles / 2) - ((sizeOfCircles - 80) / 2);

            g.drawLine(edgeElement-1,(((sizeOfCircles - 80)/2))+45,(edgeElement+sizeOfCircles - 80),(((sizeOfCircles - 80)/2))+45);

            try {
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person*.png"));
                g.drawImage(image, edgeElement+15, (((sizeOfCircles - 80)/2))+8, null);

                BufferedImage image2 = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person-.png"));
                g.drawImage(image2, (edgeElement+sizeOfCircles - 155), (((sizeOfCircles - 80)/2))+10, null);

                BufferedImage leftArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/arrowTop.png"));
                g.drawImage(leftArrow, 345, 50, null);

                BufferedImage rightArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/arrowBottom.png"));
                g.drawImage(rightArrow, 415, 190, null);


                BufferedImage constraint = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/time.png"));
                g.drawImage(constraint, 270, ((sizeOfCircles - 80)/2), null);



            } catch (Exception e) {
                e.printStackTrace();
            }
        //}


    }

    public void addColorPermission(Color color) {
        this.colorPermission = color;
    }

    public void addColorProhibition(Color color) {
        this.colorProhibition = color;
    }

    public void addColorDuty(Color color) {
        this.colorDuty = color;
    }

    public void addColorConstraint(Color color) {
        this.colorConstraint = color;
    }

    /*
    public static void main (String[]args){
        JFrame frameNew = new JFrame();
        frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameNew.setSize(500, 900);
        situational situational = new situational();
        frameNew.add(situational);
        frameNew.setVisible(true);
    }
*/

}
