package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class bubbleMap extends JPanel {
    private boolean bubbleWithLines;
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
    }

    public void paint(Graphics g) {

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

    }

    public void draw(Color wantedColor, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(sizeLines));
        //Color color = new Color(wantedColor.getRed(), wantedColor.getGreen(), wantedColor.getBlue(), wantedColor.getAlfpha());
        g.setColor(wantedColor);
        g.drawOval(x - (numberOnAction*5) -5, y - (numberOnAction*5) -5, width+(numberOnAction*10), height+(numberOnAction*10));
        g.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
        movingXCord = x - (numberOnAction*5) -5;
        movingYCord = y - (numberOnAction*5) -5;
        numberOnAction++;
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

    public void addBubbleWithLines(boolean bubbleWithLines) { this.bubbleWithLines = bubbleWithLines; }

    public void addColorObligation(Color color) { this.colorObligation = color; }

    public void addLineSize(int sizeLines) { this.sizeLines = sizeLines; }

    public void addWidthHeight(int width, int height) { this.width = width; this.height = height; }

    public void addSizeBetweenLines(int sizeInBetweenLines) { this.sizeInBetweenLines = sizeInBetweenLines; }

}
