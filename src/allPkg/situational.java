package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class situational extends JPanel {

    private Policy policy;
    private int sizeOfCircles = 300;
    private int edgeInnerCircle;
    private int edgeOuterCircle;
    private int edgeOuterCircleY;
    private Color colorPermission;
    private Color colorProhibition;
    private Color colorObligation;
    private Color colorConstraint;
    private Color colorDuty;
    private int middleY;
    private int lineThickness;

    private double newChangeCX;
    private double newChangeCY;

    private int numberElementPerLine;
    private int numberOfTotalElementsPolicy;
    private int lineNumber;
    private int baseYForLine;
    private int sizeOfInnerCircle;
    private boolean isPermission;
    private boolean isProhibition;
    private boolean isObligation;
    private boolean useIcons;


    situational(Policy policy) {
       this.policy = policy;
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        isPermission = true;
        isProhibition = false;
        isObligation = false;
        numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions() + policy.getNumberOfObligations();
        lineNumber = 0;
        sizeOfInnerCircle = sizeOfCircles - 80;

        for (int i = 0; i < numberOfTotalElementsPolicy; i++) {

            if (lineNumber == 0) {
                baseYForLine = 5;
            }

            if (policy.getNumberOfPermissions() == i) {
                isPermission = false;
                isProhibition = true;
                isObligation = false;
            }

            // Enables switch from Permission to Prohibition
            if (policy.getNumberOfProhibitions() + policy.getNumberOfPermissions() == i) {
                isPermission = false;
                isProhibition = false;
                isObligation = true;
            }

            // Signals to go to the line
            if ((i % numberElementPerLine == 0) && (i != 0)) {
                lineNumber++;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(lineThickness));

            // Draw Outer Circle
            g2.drawOval(280 + ((sizeOfCircles + 10) * (i % numberElementPerLine)), (lineNumber * (sizeOfCircles + 10)) + 10, sizeOfCircles, sizeOfCircles);
            edgeOuterCircle = 280 + ((sizeOfCircles + 10) * (i % numberElementPerLine));
            edgeInnerCircle = (280 + ((sizeOfCircles + 10) * (i % numberElementPerLine))) + 40;
            edgeOuterCircleY = (lineNumber * (sizeOfCircles + 10)) + 10;

            // Draw Inner Circle
            g2.drawOval(edgeInnerCircle, (lineNumber * (sizeOfCircles + 10)) + 50, sizeOfInnerCircle, sizeOfInnerCircle);

            // Set the middle Y coordinate for the line
            middleY = ((lineNumber * (sizeOfCircles + 10)) + 10) + sizeOfCircles / 2;

            // Draw line
            g2.setStroke(new BasicStroke(1));
            g.drawLine(edgeInnerCircle, middleY, edgeInnerCircle + sizeOfInnerCircle, middleY);

            System.out.println(sizeOfCircles*(0.75) + "<--__ YEs");
            double yYy = edgeOuterCircleY + (sizeOfCircles*0.75);

            Rules rule;
            if (isPermission) {
                rule = policy.getPermission(i);
            } else if (isProhibition) {
                rule = policy.getProhibition(i - policy.getNumberOfPermissions());
            } else {
                rule = policy.getObligation(i - (policy.getNumberOfPermissions() + policy.getNumberOfProhibitions()));
            }


            try {
                if (isPermission) {
                    BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/permission.png"));
                    g.drawImage(image, edgeOuterCircle-5, (int)yYy, null);
                }  else if (isProhibition) {
                    BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/prohibition.png"));
                    g.drawImage(image, edgeOuterCircle-5, (int)yYy, null);
                } else {
                    BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/obligation.png"));
                    g.drawImage(image, edgeOuterCircle-5, (int)yYy, null);
                }

                drawParties(rule,g);
                drawActions(rule,g,false);
                drawDuties(rule,g);

                int index = 0;
                for (int j = 0; j < rule.getConstraint().size(); j++) {
                    drawConstraint(rule.getConstraint().get(j), g, index);
                    index++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void drawParties(Rules rule,Graphics g) {
        for (int j = 0; j < rule.getParty().size(); j++) {
            try {
                Random randomP = new Random();
                int random = randomP.nextInt(2);

                if (rule.getParty().get(j).getFunction().equals("assignee")) {
                    BufferedImage image = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person"+ random +".png"));
                    g.drawImage(image, edgeInnerCircle + 15, middleY-40, null);
                    BufferedImage rightArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/bottomArrow.png"));
                    g.drawImage(rightArrow, edgeInnerCircle+95, middleY+15, null);
                }

                if (rule.getParty().get(j).getFunction().equals("assigner")) {
                    BufferedImage image2 = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/person"+ random +".png"));
                    g.drawImage(image2, (edgeInnerCircle + sizeOfInnerCircle - 80), middleY-40, null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawActions(Rules rule,Graphics g,Boolean fromDuty) {
        int actionLineNumber = 0;
        String words;
        for (int j = 0; j < rule.getAction().size(); j++) {
            try {
                if ((j % 3 == 0) && (j != 0)) {
                    actionLineNumber++;
                }
                if (fromDuty) {
                    if (useIcons) {
                        BufferedImage action = ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + rule.getAction().get(j).getName() + ".png"));
                        g.drawImage(action, edgeInnerCircle + (25 * (j % 3)) + 45, middleY - 80 + (20 * actionLineNumber), null);
                    } else {
                        if (isObligation) {
                            words = "If not done: ";
                        } else if (isProhibition) {
                            words = "If done anyway: ";
                        } else {
                            words = "Duty to ";
                        }
                        g.drawString(words + rule.getAction().get(j).getName(),edgeInnerCircle + (25 * (j % 3)) + 30,middleY - 50 - (20 * actionLineNumber));
                    }

                } else {
                    if (useIcons) {
                        BufferedImage action = ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + rule.getAction().get(j).getName() + ".png"));
                        g.drawImage(action, edgeInnerCircle + (25 * (j % 3)) + 45, middleY + 50 - (10 * actionLineNumber), null);
                    } else {
                        if (isObligation) {
                            words = "Obligation to ";
                        } else if (isProhibition) {
                            words = "Prohibition to ";
                        } else {
                            words = "Permission to ";
                        }
                        g.drawString(words + rule.getAction().get(j).getName(),edgeInnerCircle + (25 * (j % 3)) + 30,middleY + 50 + (10 * actionLineNumber));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawDuties(Rules rule, Graphics g) {
        try {
            if (rule.getDuty().size() != 0) {
                BufferedImage leftArrow = ImageIO.read(new File("/Users/Chapman/Desktop/icons/situational/topArrow.png"));
                g.drawImage(leftArrow, edgeInnerCircle+95, middleY-30, null);
            }
            for (int j = 0; j < rule.getDuty().size(); j++) {
                drawActions(rule.getDuty().get(j), g,true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawConstraint(Constraint constraint, Graphics g, int index) {
        try {
            if (constraint.getIsLogicalConstraint()) {
                for (int j = 0; j < constraint.getAttachedConstraint().size(); j++) {
                    if (constraint.getAttachedConstraint().get(j).getIsLogicalConstraint()) {
                        drawConstraint(constraint.getAttachedConstraint().get(j), g, index);
                    } else {
                        BufferedImage constraintImageName = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/" + constraint.getAttachedConstraint().get(j).getName() + ".png"));
                        if (index !=0 ) {
                            if (index == 1) {
                                newChangeCX = 0 * Math.cos(0.45) - 130 * Math.sin(0.45);
                                newChangeCY = 0 * Math.sin(0.45) + 130 * Math.cos(0.45);
                            } else {
                                newChangeCX = newChangeCX * Math.cos(0.45) - newChangeCY * Math.sin(0.45);
                                newChangeCY = newChangeCX * Math.sin(0.45) + newChangeCY * Math.cos(0.45);
                            }
                        }

                        int drawX = (edgeOuterCircle + (sizeOfInnerCircle / 2) + 30) + (int)newChangeCX;
                        int drawY = (130 - (int)newChangeCY) + (middleY - (sizeOfCircles / 2) + 10);

                        if (index == 0) {
                            g.drawImage(constraintImageName, edgeOuterCircle + (sizeOfInnerCircle / 2) + 30, middleY - (sizeOfCircles / 2) + 10, null);
                        } else {
                            g.drawImage(constraintImageName, drawX, drawY, null);
                        }
                        index++;
                        }
                    }
                } else {
                    System.out.println(constraint.getName() + "name constraint");
                    BufferedImage constraintImageName = ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/" + constraint.getName() + ".png"));

                    if (index !=0 ) {
                        if (index == 1) {
                            newChangeCX = 0 * Math.cos(0.45) - 130 * Math.sin(0.45);
                            newChangeCY = 0 * Math.sin(0.45) + 130 * Math.cos(0.45);
                        } else {
                            newChangeCX = newChangeCX * Math.cos(0.45) - newChangeCY * Math.sin(0.45);
                            newChangeCY = newChangeCX * Math.sin(0.45) + newChangeCY * Math.cos(0.45);
                        }
                    }

                    int drawX = (edgeOuterCircle + (sizeOfInnerCircle / 2) + 30) + (int)newChangeCX;
                    int drawY = (130 - (int)newChangeCY) + (middleY - (sizeOfCircles / 2) + 10);

                    if (index == 0) {
                        g.drawImage(constraintImageName, edgeOuterCircle + (sizeOfInnerCircle / 2) + 30, middleY - (sizeOfCircles / 2) + 10, null);
                    } else {
                        g.drawImage(constraintImageName, drawX, drawY, null);
                    }
                index++;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColorPermission(Color color) {
        this.colorPermission = color;
    }

    public void addLineThickness(int lineThickness) {
        this.lineThickness = lineThickness;
    }

    public void addColorObligation(Color color) {
        this.colorObligation = color;
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

    public void addUseIcons(boolean useIcons) {
        this.useIcons = useIcons;
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
