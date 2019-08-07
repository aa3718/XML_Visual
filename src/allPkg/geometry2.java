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
import java.util.ArrayList;


public class geometry2 extends JPanel {

    private Policy policy;
    private int prefferedRuleBoxSizeW = 200;
    private int latestX = 0;
    private int latestY = 0;
    private boolean isPermission;
    private int numberOfTotalElementsPolicy;
    private int lineNumber = 0;
    private int ruleLengthSize;
    private int numberElementPerLine = 4;
    private String name;
    private int startBoxX;
    private int startBoxY;

    geometry2(Policy policy) {
        this.policy = policy;
    }

    public void paint(Graphics g) {

        numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions();
        ruleLengthSize = prefferedRuleBoxSizeW * 2 + 80;
        name = "Permission";
        isPermission = true;

        for (int i = 0; i < numberOfTotalElementsPolicy; i++) {

            // Enables switch from Permission to Prohibition
            if (policy.getNumberOfPermissions() == i) {
                name = "Prohibition";
                isPermission = false;
            }

            // Signals to go to the line
            if ((i % numberElementPerLine == 0) && (i != 0)) {
                lineNumber++;
            }

            // Draws main box
            g.drawRect((5 + (5 * (i % numberElementPerLine)) + (200 * (i % numberElementPerLine))), (5 + (lineNumber * ruleLengthSize + (lineNumber * 5))), prefferedRuleBoxSizeW, ruleLengthSize);
            latestX = 5 + (5 * (i % numberElementPerLine)) + (200 * (i % numberElementPerLine));
            latestY = (5 + (lineNumber * ruleLengthSize));

            // Writes main Rule Name
            g.drawString(name, 20 + (205 * (i % numberElementPerLine)), 25 + (lineNumber * ruleLengthSize));
            latestX = 20 + (205 * (i % numberElementPerLine));
            latestY = 25 + (lineNumber * ruleLengthSize);

            startBoxX = latestX;
            startBoxY = latestY;

            // Transform from Title P or P
            latestX += 15;
            latestY += 10;

            try {

                // Get the Element (Permission or Prohibition) depending on Boolean
                Rules rule;
                if (isPermission) {
                    rule = policy.getPermission(i);
                } else {
                    rule = policy.getProhibition(i - policy.getNumberOfPermissions());
                }

                drawInnerAAP(rule,g);

                // Draw Constraints
                drawConstraints(rule,g,false);

                // Draw Duties
                for (int j = 0; j < rule.getDuty().size(); j++) {
                    g.drawString("Duty",latestX-5, latestY+5);
                    latestX -= 5;
                    latestY += 5;
                    startBoxY = latestY+5;

                    // Transform from Title P or P
                    latestX += 15;
                    latestY += 10;




                    // startBoxX don't change, startBoxY

                    drawConstraints(rule.getDuty().get(j),g, true);

                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        }

    }


    public void drawInnerAAP(Rules rule, Graphics g) {
        try {
            for (int j = 0; j < rule.getParty().size(); j++) {

                //String nameAttribute = policy.getPermission(i).getParty().get(j).getFunction();
                String nameAttribute = "profile";
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + nameAttribute + ".png"));
                g.drawImage(image, latestX, latestY, null);

                g.drawString(rule.getParty().get(j).getFunction() + ": " + rule.getParty().get(j).getUID(), latestX + 35, latestY + 15);
                latestY += 40;
            }

            // Draw Action
            g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + rule.getAction().getName() + ".png")), latestX, latestY, null);
            g.drawString(rule.getAction().getName(), latestX + 35, latestY + 15);
            latestY += 40;

            // Draw Asset
            g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/asset.png")), latestX, latestY, null);
            g.drawString(rule.getAsset().getUID(), latestX + 35, latestY + 15);
            latestY += 30;

            // Draw Rule box after depending on last points
            g.drawRect(startBoxX + 5, startBoxY + 5, prefferedRuleBoxSizeW - 25, latestY - (startBoxY + 5));

            startBoxX = startBoxX + 5;
            startBoxY = startBoxY + 5 + latestY - (startBoxY + 5);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public void drawConstraints(Rules rule, Graphics g, Boolean isDuty) {
        try {
            for (int j = 0; j < rule.getConstraint().size(); j++) {
                latestX -= 10;
                g.drawRect(latestX, latestY, (prefferedRuleBoxSizeW - 25), 2);
                latestY += 2;
                if (j == 0) {
                    g.drawString("Constraint", latestX + 5, latestY + 13);
                    latestX += 5;
                    latestY += 13;
                }
                String nameAttribute = rule.getConstraint().get(j).getName();
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/" + nameAttribute + ".png"));
                g.drawImage(image, latestX + 5, latestY + 5, null);
                latestX += 5;
                latestY += 5;
                g.drawString(nameAttribute, latestX + 35, latestY + 20);
                latestY += 30;

                if (rule.getConstraint().get(j).getLeftOperand() != null) {
                    String nameAttributeLO = rule.getConstraint().get(j).getLeftOperand();
                    g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + nameAttributeLO + ".png")), latestX, latestY, null);
                    g.drawString(nameAttributeLO, latestX + 35, latestY + 20);
                    latestY += 40;
                }

                // Draw Operator
                String nameAttributeO = rule.getConstraint().get(j).getOperator();
                g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/operator/" + nameAttributeO + ".png")), latestX, latestY, null);
                // g.drawString(rule.getConstraint().get(j).getOperator(),latestX,latestY);
                latestY += 40;

                // Draw Value
                g.drawString(rule.getConstraint().get(j).getRightOperand(), latestX, latestY);
                latestY += 30;

                if (!isDuty) {
                    g.drawRect(startBoxX, startBoxY, prefferedRuleBoxSizeW - 25, latestY - (startBoxY));
                } else {
                    g.drawRoundRect(startBoxX, startBoxY, prefferedRuleBoxSizeW - 25, latestY - (startBoxY),30,30);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}



/*
            try {
                for (policy.)
                    if (policy.getNumberOfPermissions() == i) {
                        name = "Prohibition";
                    }
                policy.getPermission(i).
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Downloads/profile.png"));
                g.drawImage(image, 35, 30, null);
                g.drawString("Assignee", 60, 45);

            } catch (Exception e) {

            }

*/

        /*
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
        */




