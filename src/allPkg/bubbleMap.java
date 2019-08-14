package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class bubbleMap extends JComponent {
    private Policy policy;
    private boolean useIcon;
    private ArrayList<String> nameAction;
    private ArrayList<String> nameLeftOperand;
    private int xCord;
    private int yCord;
    private int xXCord;
    private Color colorPermission;
    private Color colorProhibition;
    private Color colorConstraint;
    private Color colorDuty;

    bubbleMap(Policy policy) {
        this.policy = policy;
        buildAction();
        buildLeftOperand();
    }

    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        int numberPerLine = 7;
        int lineNumber = 0;


        // Get any Action and build list
        for (int i = 0; i < nameAction.size(); i++) {

            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            String pictureName = nameAction.get(i);
            xCord = 280 + (65 * (i % numberPerLine));
            yCord = 5 + (70 * lineNumber);

            for (int j = 0; j < policy.getAllPermission().size(); j++) {
                for (int k = 0; k < policy.getPermission(j).getAction().size(); k++) {
                    if (policy.getPermission(j).getAction().get(k).getName().equals(pictureName)) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(2));
                        g.setColor(Color.pink);
                        g.fillOval(xCord - 5, yCord - 5, 35, 35);
                        g.setColor(Color.black);
                        g2.setStroke(new BasicStroke(1));
                    }
                }
            }
            g.setColor(Color.black);


            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + pictureName + ".png")), xCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName,xCord,yCord);
            }
        }

        lineNumber = 0;
        xCord = 280 + (65 * (numberPerLine));

        for (int i= 0; i < nameLeftOperand.size(); i++) {
            if (i % numberPerLine == 0 && i!=0) {
                lineNumber++;
            }

            String pictureName = nameLeftOperand.get(i);
            xXCord = xCord + (65 * (i % numberPerLine));
            yCord = 5 + (70 * lineNumber);

            for (int j = 0; j < policy.getAllConstraint().size(); j++) {
                if (policy.getAllConstraint().get(j).getName().equals(pictureName)) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(2));
                    g.setColor(Color.red);
                    g.fillOval(xXCord-5,yCord-5,35,35);
                    g.setColor(Color.black);
                    g2.setStroke(new BasicStroke(1));
                }
            }
            g.setColor(Color.black);

            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/name/" + pictureName + ".png")), xXCord, yCord, null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName,xXCord,yCord);
            }

        }

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

}
