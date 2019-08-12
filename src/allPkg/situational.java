package allPkg;

import javax.swing.*;
import java.awt.*;

public class situational extends JComponent {

    private Policy policy;
    private int sizeOfCircles = 300;
    private int edgeInnerCircle;
    private Color colorPermission;
    private Color colorProhibition;
    private Color colorConstraint;
    private Color colorDuty;

    private int numberElementPerLine;
    private int numberOfTotalElementsPolicy;
    private int lineNumber;
    private int baseYForLine;
    private int sizeOfInnerCircle;


    situational(Policy policy) {
       this.policy = policy;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions();
        lineNumber = 0;
        sizeOfInnerCircle = sizeOfCircles - 80;

        for (int i = 0; i < numberOfTotalElementsPolicy; i++) {

            if (lineNumber == 0) {
                baseYForLine = 5;
            }

            // Signals to go to the line
            if ((i % numberElementPerLine == 0) && (i != 0)) {
                lineNumber++;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));

            // Draw Outer Circle
            g2.drawOval(260 + ((sizeOfCircles+10) * (i%numberElementPerLine)), (lineNumber*(sizeOfCircles+10))+10, sizeOfCircles, sizeOfCircles);
            edgeInnerCircle = (260 + ((sizeOfCircles+10) * (i%numberElementPerLine))) - ((sizeOfCircles-80)/2);

            // Draw Inner Circle
            g2.drawOval(edgeInnerCircle, (lineNumber*(sizeOfCircles+10))+40, sizeOfInnerCircle, sizeOfInnerCircle);

            int leftMiddleInnerCircle = edgeInnerCircle + ((sizeOfInnerCircle+10) * (i%numberElementPerLine));
            int leftYInnerCircle = (lineNumber*(sizeOfCircles+10))+40;

            // Draw line
            g2.setStroke(new BasicStroke(1));
            g.drawLine(edgeInnerCircle,(((sizeOfCircles-80)/2))+45,(leftMiddleInnerCircle+sizeOfCircles-80),(((sizeOfCircles-80)/2))+45);

            /*
            try {
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person*.png"));
                g.drawImage(image, edgeInnerCircle+15, (((sizeOfCircles-80)/2))+8, null);

                BufferedImage image2 = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person-.png"));
                g.drawImage(image2, (edgeInnerCircle+sizeOfCircles - 155), (((sizeOfCircles - 80)/2))+10, null);

                BufferedImage leftArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/arrowTop.png"));
                g.drawImage(leftArrow, 345, 50, null);

                BufferedImage rightArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/arrowBottom.png"));
                g.drawImage(rightArrow, 415, 190, null);


                BufferedImage constraint = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/time.png"));
                g.drawImage(constraint, 270, ((sizeOfCircles - 80)/2), null);

                BufferedImage action = ImageIO.read(new File("/Users/Chapman/Desktop/icons/distribute.png"));
                g.drawImage(action, 430, 70, null);



            } catch (Exception e) {
                e.printStackTrace();
            }
            */
        }


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

    public void addNumberElementPerLine(int numberElementPerLine) {
        this.numberElementPerLine = numberElementPerLine;
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
