package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
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


public class geometry2 extends JComponent {

    private Policy policy;
    private int latestX = 0;
    private int latestY = 0;
    private boolean isPermission;
    private int numberOfTotalElementsPolicy;
    private int lineNumber;
    private int ruleLengthSize;
    private String name;
    private int startBoxX;
    private int startBoxY;
    private int maxYBoxOnLine;
    private int baseYForLine;
    private int theYforDutyWithConstraint;

    private int prefferedRuleBoxSizeW;
    private int numberElementPerLine;
    private int spaceBetweenAttributes;
    private int spaceBetweenAttrIconAndAttrStringsX;
    private int spaceAtBottomOfBoxes;
    private int sizeOfInnerBoxesW;
    private int widenessOfConstraintLines;
    private int operatorSpaceFromLeft;
    private int arcWDuty;
    private int arcHDuty;
    private int spaceBetweenRulesAndDuty;
    private int spaceBetweenDutyAndDuty;
    private int bottomPadding;

    private Color colorPermission;
    private Color colorProhibition;
    private Color colorConstraint;
    private Color colorDuty;

    private boolean makeDutyRounded;
    private boolean makeDutyHex;

    geometry2(Policy policy) {
        this.policy = policy;
        //setBackground(Color.blue);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions();
        ruleLengthSize = 400;
        name = "Permission";
        isPermission = true;
        maxYBoxOnLine = 0;
        lineNumber = 0;

        for (int i = 0; i < numberOfTotalElementsPolicy; i++) {

            // Enables switch from Permission to Prohibition
            if (policy.getNumberOfPermissions() == i) {
                name = "Prohibition";
                isPermission = false;
            }

            // Draws main box
            //g.drawRect((5 + (5 * (i % numberElementPerLine)) + (200 * (i % numberElementPerLine))), (5 + (lineNumber * ruleLengthSize + (lineNumber * 5))), prefferedRuleBoxSizeW, ruleLengthSize);
            //latestX = 250 + (5 * (i % numberElementPerLine)) + (200 * (i % numberElementPerLine));

            if (lineNumber == 0) {
                baseYForLine = 5;
            }

            // Signals to go to the line
            if ((i % numberElementPerLine == 0) && (i != 0)) {
                lineNumber++;
                baseYForLine += maxYBoxOnLine;
                maxYBoxOnLine = 0;
            }

            latestY = baseYForLine;

            // Writes main Rule Name
            g.drawString(name, 290 + (205 * (i % numberElementPerLine)), latestY+25);
            latestX = 290 + (205 * (i % numberElementPerLine));
            latestY += 25;

            startBoxX = latestX + 5;
            startBoxY = latestY + 5;

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

                drawInnerAAP(rule,g,false);
                drawConstraints(rule,g,false);

                startBoxY += spaceBetweenRulesAndDuty;
                latestY += spaceBetweenRulesAndDuty;

                // Draw Duties
                for (int j = 0; j < rule.getDuty().size(); j++) {

                    g.drawString("Duty",startBoxX+10, startBoxY+18);

                    // Transform from Title P or P
                    latestY += 28;

                    drawInnerAAP(rule.getDuty().get(j),g,true);
                    drawConstraints(rule.getDuty().get(j),g, true);

                    latestY += spaceBetweenDutyAndDuty;
                    startBoxY += spaceBetweenDutyAndDuty;

                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error");
            }

            if (isPermission) {
                g.setColor(colorPermission);
            } else {
                g.setColor(colorProhibition);
            }
            g.drawRect((285 + (5 * (i % numberElementPerLine)) + (200 * (i % numberElementPerLine))), baseYForLine, prefferedRuleBoxSizeW, (latestY - baseYForLine) + bottomPadding);
            g.setColor(Color.black);

            if(((latestY - baseYForLine) + bottomPadding) > maxYBoxOnLine) {
                maxYBoxOnLine = ((latestY - baseYForLine) + bottomPadding);
            }
        }

    }


    public void drawInnerAAP(Rules rule, Graphics g,Boolean isDuty) {
        try {
            for (int j = 0; j < rule.getParty().size(); j++) {

                //String nameAttribute = policy.getPermission(i).getParty().get(j).getFunction();
                String nameAttribute = "profile";
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + nameAttribute + ".png"));
                g.drawImage(image, latestX, latestY, null);
                g.drawString(rule.getParty().get(j).getFunction() + ": " + rule.getParty().get(j).getUID(), latestX + spaceBetweenAttrIconAndAttrStringsX, latestY + 20);
                latestY += spaceBetweenAttributes;
            }

            // Draw Action
            if (rule.getAction() != null) {
                g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + rule.getAction().getName() + ".png")), latestX, latestY, null);
                g.drawString(rule.getAction().getName(), latestX + spaceBetweenAttrIconAndAttrStringsX, latestY + 20);
                latestY += spaceBetweenAttributes;
            }

            // Draw Asset
            if (rule.getAsset() != null) {
                g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/asset.png")), latestX, latestY, null);
                g.drawString(rule.getAsset().getUID(), latestX + spaceBetweenAttrIconAndAttrStringsX, latestY + 20);
                latestY += spaceBetweenAttributes/2;
            }
            latestY += spaceAtBottomOfBoxes;

            // Draw Rule box after depending on last points

            if (isDuty == false) {
                g.setColor(colorPermission);
                g.drawRect(startBoxX, startBoxY, sizeOfInnerBoxesW, latestY - startBoxY);
            } else {
                if (rule.getConstraint().isEmpty()) {
                    g.setColor(colorDuty);
                    g.drawRoundRect(startBoxX, startBoxY, sizeOfInnerBoxesW, latestY - startBoxY,arcWDuty,arcHDuty);
                }
                theYforDutyWithConstraint = startBoxY;
            }
            g.setColor(Color.black);
            startBoxY = latestY;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public void drawConstraints(Rules rule, Graphics g, Boolean isDuty) {
        try {
            for (int j = 0; j < rule.getConstraint().size(); j++) {

                g.setColor(colorConstraint);
                g.drawRect(startBoxX, startBoxY, sizeOfInnerBoxesW, widenessOfConstraintLines);
                g.setColor(Color.black);
                latestY += widenessOfConstraintLines;
                startBoxY += widenessOfConstraintLines;

                if (j == 0) {
                    g.drawString("Constraint", latestX + 7, latestY + 15);
                    latestY += 30;
                }

                String nameAttribute = rule.getConstraint().get(j).getName();
                BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/" + nameAttribute + ".png"));
                g.drawImage(image, latestX, latestY + 5, null);
                latestY += 5;

                g.drawString(nameAttribute, latestX + spaceBetweenAttrIconAndAttrStringsX, latestY + 20);
                latestY += spaceBetweenAttributes;

                if (rule.getConstraint().get(j).getLeftOperand() != null) {
                    String nameAttributeLO = rule.getConstraint().get(j).getLeftOperand();
                    g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + nameAttributeLO + ".png")), latestX, latestY, null);
                    g.drawString(nameAttributeLO, latestX + spaceBetweenAttrIconAndAttrStringsX, latestY + 20);
                    latestY += spaceBetweenAttributes;
                }

                // Draw Operator
                String nameAttributeO = rule.getConstraint().get(j).getOperator();
                g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/operator/" + nameAttributeO + ".png")), latestX + operatorSpaceFromLeft, latestY, null);
                // g.drawString(rule.getConstraint().get(j).getOperator(),latestX,latestY);
                latestY += spaceBetweenAttributes;

                // Draw Value
                g.drawString(rule.getConstraint().get(j).getRightOperand(), latestX, latestY);
                latestY += spaceAtBottomOfBoxes;

                g.setColor(colorConstraint);
                if (isDuty == false) {
                    g.drawRect(startBoxX, startBoxY, sizeOfInnerBoxesW, latestY - startBoxY);
                } else {
                    g.drawRoundRect(startBoxX, theYforDutyWithConstraint, sizeOfInnerBoxesW, latestY - theYforDutyWithConstraint, arcWDuty, arcHDuty);
                }
                g.setColor(Color.black);
                startBoxY = latestY;

            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void addNumberElementPerLine(int number) {
        this.numberElementPerLine = number;
    }

    public void addThePrefferedRuleBoxSizeW(int number) {
        this.prefferedRuleBoxSizeW = number;
    }

    public void addSpaceBetweenAttributes(int number) {
        this.spaceBetweenAttributes = number;
    }

    public void addSpaceBetweenAttrIconAndAttrStringsX(int number) {
        this.spaceBetweenAttrIconAndAttrStringsX = number;
    }

    public void addSpaceAtBottomOfBoxes(int number) {
        this.spaceAtBottomOfBoxes = number;
    }

    public void addSizeOfInnerBoxesW(int number) {
        this.sizeOfInnerBoxesW = number;
    }

    public void addWidenessOfConstraintLines(int number) {
        this.widenessOfConstraintLines = number;
    }

    public void addOperatorSpaceFromLeft(int number) {
        this.operatorSpaceFromLeft = number;
    }

    public void addArcWDuty(int number) {
        this.arcWDuty = number;
    }

    public void addArcHDuty(int number) {
        this.arcHDuty = number;
    }

    public void addSpaceBetweenRulesAndDuty(int number) {
        this.spaceBetweenRulesAndDuty = number;
    }

    public void addSpaceBetweenDutyAndDuty(int number) {
        this.spaceBetweenDutyAndDuty = number;
    }

    public void addBottomPadding(int number) {
        this.bottomPadding = number;
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

    public void addDutyRounded(boolean bool) {
        this.makeDutyRounded = bool;
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




