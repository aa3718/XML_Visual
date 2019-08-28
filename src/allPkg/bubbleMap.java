package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class bubbleMap extends JPanel {
    private Policy policy;
    private boolean useIcon;
    private ArrayList<String> nameAction;
    private ArrayList<String> nameLeftOperand;
    private int xCord;
    private int yCord;
    private int xXCord;
    private int movingXCord;
    private int movingYCord;
    private int previousX;
    private int previousY;
    private Color colorPermission;
    private Color colorProhibition;
    private Color colorConstraint;
    private Color colorDuty;
    private Color colorObligation;
    private int sizeLines;
    private ArrayList<Action> actionS;
    private int width;
    private int height;
    int numberOnAction;
    private boolean isPermission;
    private boolean isObligation;
    private boolean isProhibition;
    private String name;
    private int numberOnActionList[];
    private int numberOnConstraintList[];
    private int sizeInBetweenLines;


    bubbleMap(Policy policy) {
        this.policy = policy;
        buildAction();
        buildLeftOperand();
        numberOnActionList = new int[nameAction.size()];
        numberOnConstraintList = new int[nameLeftOperand.size()];
        System.out.println(useIcon + " <= use icon");
    }

    public void paint(Graphics g) {

        System.out.println(useIcon + "INSIDE FUNCTINO USEICON");

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        int numberOfTotalElementsPolicy = policy.getNumberOfProhibitions() + policy.getNumberOfPermissions() + policy.getNumberOfObligations();
        isPermission = true;
        isObligation = false;
        isProhibition = false;
        int numberPerLine = 7;
        int lineNumber = 0;
        numberOnAction = 0;
        Rules rule;

        for (int j = 0; j < numberOfTotalElementsPolicy; j++) {

            previousX = 0;
            previousY = 0;
            numberPerLine = 7;
            lineNumber = 0;
            Color colorForLine = Color.pink;

            if (policy.getNumberOfPermissions() == j) {
                isPermission = false;
                isProhibition = true;
                colorForLine = Color.red.brighter();
            }

            // Enables switch from Permission to Prohibition
            if (policy.getNumberOfProhibitions() + policy.getNumberOfPermissions() == j) {
                isPermission = false;
                isProhibition = false;
                isObligation = true;
                colorForLine = Color.orange.brighter();
            }

            System.out.println(isPermission + "<- permission");
            System.out.println(isProhibition + "<- prohibition");
            System.out.println(isObligation + "<- obligation");

            if (isPermission) {
                rule = policy.getPermission(j);
            } else if (isProhibition) {
                rule = policy.getProhibition(j - policy.getNumberOfPermissions());
            } else {
                rule = policy.getObligation(j - (policy.getNumberOfPermissions() + policy.getNumberOfProhibitions()));
            }

            for (int i = 0; i < nameAction.size(); i++) {

                if (i % numberPerLine == 0 && i != 0) {
                    lineNumber++;
                }

                String pictureName = nameAction.get(i);
                xCord = 350 + (85 * (i % numberPerLine));
                yCord = 50 + (95 * lineNumber);

                for (int k = 0; k < rule.getAction().size(); k++) {
                    System.out.println(pictureName + " " + rule.getAction().get(k).getName());
                    if (rule.getAction().get(k).getName().equals(pictureName)) {
                        if (isPermission) {
                            draw(colorPermission, g, xCord, yCord, i);
                            numberOnActionList[i] = numberOnActionList[i] + 1;
                            System.out.println(pictureName + " name ACTION");
                        } else if (isProhibition) {
                            draw(colorProhibition, g, xCord, yCord, i);
                            numberOnActionList[i] = numberOnActionList[i] + 1;
                            System.out.println(pictureName + " name ACTION");
                        } else if (isObligation) {
                            draw(colorObligation, g, xCord, yCord, i);
                            numberOnActionList[i] = numberOnActionList[i] + 1;
                            System.out.println(pictureName + " name ACTION");
                        }
                    }
                    drawLineBetween(g,colorForLine);
                }

                recursiveDuty(rule,g,xXCord,yCord,i,pictureName,colorDuty);

            }

            lineNumber = 0;
            xCord = 350 + (85 * (numberPerLine));
            numberPerLine = 4;

            for (int i= 0; i < nameLeftOperand.size(); i++) {
                if (i % numberPerLine == 0 && i!=0) {
                    lineNumber++;
                }

                String pictureName = nameLeftOperand.get(i);
                xXCord = xCord + (85 * (i % numberPerLine)) ;
                yCord = 50 + (80 * lineNumber);

                for (int k = 0; k <rule.getConstraint().size() ; k++) {
                    if (rule.getConstraint().get(k).getName().equals(pictureName)) {
                        draw(colorConstraint, g, xXCord, yCord, i);
                        numberOnConstraintList[i] = numberOnConstraintList[i] + 1;
                    }
                    drawLineBetween(g,colorForLine);
                }
                recursiveDutyConstraint(rule,g,xXCord,yCord,i,pictureName,colorDuty);
            }
            lineNumber = 0;




        }

        numberPerLine = 7;
        drawIcons(g,numberPerLine,lineNumber);



        /*
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        int numberPerLine = 7;
        int lineNumber = 0;
        numberOnAction = 0;

        for (int i = 0; i < nameAction.size(); i++) {

            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            String pictureName = nameAction.get(i);
            xCord = 350 + (85 * (i % numberPerLine));
            yCord = 50 + (95 * lineNumber);

            for (int j = 0; j < policy.getAllAction().size(); j++) {
                if(policy.getAllAction().get(j).getName().equals(pictureName)) {
                    if (policy.getAllAction().get(j).getParentType() instanceof Permission) {
                        draw(colorPermission,g,xCord,yCord);
                    } else if (policy.getAllAction().get(j).getParentType() instanceof Prohibition) {
                        draw(colorProhibition,g,xCord,yCord);
                    } else if (policy.getAllAction().get(j).getParentType() instanceof Obligation) {
                        draw(colorObligation,g,xCord,yCord);
                    } else {
                        draw(colorDuty,g,xCord,yCord);
                    }
                }
            }

            g.setColor(Color.black);

            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("resources/icons/icons/" + pictureName + ".png")), xCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName,xCord,yCord);
            }
            numberOnAction = 0;
        }

        lineNumber = 0;
        xCord = 350 + (85 * (numberPerLine));
        numberPerLine = 4;

        for (int i= 0; i < nameLeftOperand.size(); i++) {
            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            String pictureName = nameLeftOperand.get(i);
            xXCord = xCord + (85 * (i % numberPerLine)) ;
            yCord = 50 + (80 * lineNumber);

            for (int j = 0; j < policy.getAllConstraint().size(); j++) {
                if (policy.getAllConstraint().get(j).getName().equals(pictureName)) {
                    draw(colorConstraint,g,xXCord,yCord);
                    System.out.println("drawing constrant" + policy.getAllConstraint().get(j).getName());
                }
            }
            g.setColor(Color.black);

            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("resources/icons/icons/name/" + pictureName + ".png")), xXCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName,xXCord,yCord);
            }
        }
        numberOnAction = 0;
        */
    }

    public void drawIcons(Graphics g, int numberPerLine, int lineNumber) {

        for (int i = 0; i < nameAction.size(); i++) {
            String pictureName = nameAction.get(i);

            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            xCord = 350 + (85 * (i % numberPerLine));
            yCord = 50 + (95 * lineNumber);

            System.out.println(useIcon + "INSIDE FUNCTINO USEICON");
            if (useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("resources/icons/icons/" + pictureName + ".png")), xCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName, xCord, yCord);
            }
        }

        lineNumber = 0;
        xCord = 350 + (85 * (numberPerLine));
        numberPerLine = 4;

        for (int i= 0; i < nameLeftOperand.size(); i++) {
            String pictureName = nameLeftOperand.get(i);

            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            xXCord = xCord + (85 * (i % numberPerLine)) ;
            yCord = 50 + (80 * lineNumber);

            System.out.println(useIcon + "Insisde the lood use icon!!!");
            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("resources/icons/icons/name/" + pictureName + ".png")), xXCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                System.out.println("print word");
                g.drawString(pictureName,xXCord,yCord);
            }
        }


    }

    public void drawLineBetween(Graphics g,Color colorForLine) {

        if(previousX != 0 && previousY != 0 ) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(sizeInBetweenLines));
            g.setColor(colorForLine);
            g.drawLine(previousX, previousY, movingXCord, movingYCord);
            g.setColor(Color.black);
            g2.setStroke(new BasicStroke(1));
        }

        previousX = movingXCord;
        previousY = movingYCord;


    }


    public void recursiveDuty(Rules rule, Graphics g, int x, int y, int index, String pictureName, Color colorForLine) {
        for (int j = 0; j < rule.getDuty().size(); j++) {
            for (int k = 0; k < rule.getDuty().get(j).getAction().size(); k++) {
                if (rule.getDuty().get(j).getAction().get(k).getName().equals(pictureName)) {
                    System.out.println(rule.getDuty().get(j).getAction().get(k).getName());
                    draw(colorDuty, g, x, y, index);
                    drawLineBetween(g,colorForLine);
                    recursiveDuty(rule.getDuty().get(j), g, x, y, index, pictureName, colorForLine);
                }
            }
        }
    }


    public void recursiveDutyConstraint(Rules rule, Graphics g, int x, int y, int index, String pictureName, Color colorForLine) {
        for (int j = 0; j < rule.getDuty().size(); j++) {
            for (int k = 0; k < rule.getDuty().get(j).getConstraint().size(); k++) {
                if (rule.getDuty().get(j).getConstraint().get(k).getName().equals(pictureName)) {
                    draw(colorConstraint, g, x, y, index);
                    drawLineBetween(g,colorForLine);
                }
            }
        }
    }

    public void draw(Color wantedColor, Graphics g, int x, int y, int index) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(sizeLines));
        //Color color = new Color(wantedColor.getRed(), wantedColor.getGreen(), wantedColor.getBlue(), wantedColor.getAlfpha());
        g.setColor(wantedColor);
        g.drawOval(x - (numberOnActionList[index]*5) -5, y - (numberOnActionList[index]*5) -5, width+(numberOnActionList[index]*10), height+(numberOnActionList[index]*10));
        g.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
        movingXCord = x - (numberOnActionList[index]*5) -5;
        movingYCord = y - (numberOnActionList[index]*5) -5;
        //numberOnAction++;
    }

    public void buildLeftOperand() {
        nameLeftOperand = new ArrayList<String>();
        nameLeftOperand.add("absolutePosition");//
        nameLeftOperand.add("absoluteSize");//
        nameLeftOperand.add("absoluteSpatialPosition");//
        nameLeftOperand.add("absoluteTemporalPosition");//
        nameLeftOperand.add("count");//
        nameLeftOperand.add("dateTime");//
        nameLeftOperand.add("delayPeriod");//
        nameLeftOperand.add("deliveryChannel");//
        nameLeftOperand.add("elapsedTime");//
        nameLeftOperand.add("event");//
        nameLeftOperand.add("fileFormat");//
        nameLeftOperand.add("industry");//
        nameLeftOperand.add("language");//
        nameLeftOperand.add("media");//
        nameLeftOperand.add("meteredTime");//
        nameLeftOperand.add("payAmount");//
        nameLeftOperand.add("percentage");//
        nameLeftOperand.add("product");//
        nameLeftOperand.add("purpose");//
        nameLeftOperand.add("recipient");//
        nameLeftOperand.add("relativePosition");//
        nameLeftOperand.add("relativeSize");//
        nameLeftOperand.add("relativeSpatialPosition");//
        nameLeftOperand.add("relativeTemporalPosition");//
        nameLeftOperand.add("resolution");//
        nameLeftOperand.add("spatial");//
        nameLeftOperand.add("spatialCoordinates");//
        nameLeftOperand.add("systemDevice");//
        nameLeftOperand.add("timeInterval");//
        nameLeftOperand.add("unitOfCount");//
        nameLeftOperand.add("version");//
        nameLeftOperand.add("virtualLocation");//
    }

    public void buildAction() {
        nameAction = new ArrayList<String>();
        nameAction.add("acceptTracking"); //
        nameAction.add("aggregate"); //
        nameAction.add("annotate"); //
        nameAction.add("anonymize"); //
        nameAction.add("archive"); //
        nameAction.add("attribute"); //
        nameAction.add("Attribution"); //
        nameAction.add("CommercialUse"); //
        nameAction.add("compensate");  //
        nameAction.add("concurrentUse"); //
        nameAction.add("delete"); //
        nameAction.add("derive"); //
        nameAction.add("DerivativeWorks");//
        nameAction.add("digitize");  //
        nameAction.add("display");//
        nameAction.add("distribute");//
        nameAction.add("Distribution"); //
        nameAction.add("ensureExclusivity");//
        nameAction.add("execute");//
        nameAction.add("extract");//
        nameAction.add("give"); //
        nameAction.add("grantUse"); //
        nameAction.add("include");//
        nameAction.add("index");//
        nameAction.add("inform"); //
        nameAction.add("install"); //
        nameAction.add("modify");//
        nameAction.add("move");//
        nameAction.add("nextPolicy");//
        nameAction.add("Notice"); //
        nameAction.add("obtainConsent");//
        nameAction.add("play");//
        nameAction.add("present");//
        nameAction.add("print");//
        nameAction.add("read");//
        nameAction.add("reproduce");//
        nameAction.add("Reproduction");//
        nameAction.add("reviewPolicy");//
        nameAction.add("sell");//
        nameAction.add("ShareAlike");//
        nameAction.add("Sharing");//
        nameAction.add("SourceCode");//
        nameAction.add("stream");//
        nameAction.add("synchronize");//
        nameAction.add("textToSpeech");//
        nameAction.add("transform");//
        nameAction.add("translate");//
        nameAction.add("uninstall");//
        nameAction.add("watermark");//
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

    public void addUseIcon(boolean useIcon) { this.useIcon = useIcon; }

    public void addColorObligation(Color color) { this.colorObligation = color; }

    public void addLineSize(int sizeLines) { this.sizeLines = sizeLines; }

    public void addWidthHeight(int width, int height) { this.width = width; this.height = height; }

    public void addSizeBetweenLines(int sizeInBetweenLines) { this.sizeInBetweenLines = sizeInBetweenLines; }

}
