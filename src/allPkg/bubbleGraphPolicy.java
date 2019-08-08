package allPkg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class bubbleGraphPolicy extends JPanel {
    private Policy policy;
    private boolean useIcon;
    private ArrayList<String> nameAction;

    bubbleGraphPolicy(Policy policy,boolean useIcon) {
        this.useIcon = useIcon;
        this.policy = policy;
        buildAction();
        System.out.println("BUILT ONCE");
    }


    public void paint(Graphics g) {

        int numberPerLine = 8;
        int lineNumber = 0;


        // Get any Action and build list
        for (int i = 0; i < nameAction.size(); i++) {

            if (i % numberPerLine == 0) {
                lineNumber++;
            }

            String pictureName = nameAction.get(i);

            if(useIcon) {
                try {
                    g.drawImage(ImageIO.read(new File("/Users/Chapman/Desktop/icons/" + pictureName + ".png")), 5 + (5 * (i % numberPerLine)) + (150 * (i % numberPerLine)), 5 + (150 * lineNumber), null);
                } catch (Exception e) {
                    System.out.println("Error uploading");
                    e.printStackTrace();
                }
            } else {
                g.drawString(pictureName,15 + (15 * (i % numberPerLine)) + (120 * (i % numberPerLine)),15 + (50 * lineNumber));
            }

            for (int j = 0; j < policy.permissions.size(); j++) {
                if (policy.permissions.get(j).getAction().getName().equals(pictureName)) {
                    g.setColor(Color.getHSBColor(200,150+(j*250),50));
                    g.drawRect(15 + (15 * (i % numberPerLine)) + (120 * (i % numberPerLine)),15 + (50 * lineNumber),25,25);
                }
            }
            g.setColor(Color.black);
        }


    }


    public void buildAction() {
        nameAction = new ArrayList<String>();
        nameAction.add("Attribute");
        nameAction.add("CommercialUse"); //
        nameAction.add("DerivativeWorks");
        nameAction.add("Distribution");
        nameAction.add("Notice");
        nameAction.add("Reproduction");
        nameAction.add("ShareAlike");
        nameAction.add("Sharing");
        nameAction.add("SourceCode");
        nameAction.add("acceptTracking"); //
        nameAction.add("adHocShare");
        nameAction.add("aggregate");
        nameAction.add("annotate"); //
        nameAction.add("anonymize"); //
        nameAction.add("append"); //
        nameAction.add("appendTo"); //
        nameAction.add("archive");
        nameAction.add("attachPolicy");
        nameAction.add("attachSource");
        nameAction.add("attribute");
        nameAction.add("commercialize");
        nameAction.add("compensate"); //
        nameAction.add("concurrentUse");
        nameAction.add("copy"); //
        nameAction.add("delete"); //
        nameAction.add("derive"); //
        nameAction.add("digitize");  //
        nameAction.add("display");
        nameAction.add("distribute");
        nameAction.add("ensureExclusivity");
        nameAction.add("execute");
        nameAction.add("export");
        nameAction.add("extract");
        nameAction.add("extractChar");
        nameAction.add("extractPage");
        nameAction.add("extractWord");
        nameAction.add("give");
        nameAction.add("grantUse");
        nameAction.add("include");
        nameAction.add("index");
        nameAction.add("inform");
        nameAction.add("install");
        nameAction.add("lease");
        nameAction.add("lend"); //
        nameAction.add("license");
        nameAction.add("modify");
        nameAction.add("move");
        nameAction.add("nextPolicy");
        nameAction.add("obtainConsent");
        nameAction.add("pay");
        nameAction.add("play");
        nameAction.add("present");
        nameAction.add("preview");//
        nameAction.add("print");
        nameAction.add("read");
        nameAction.add("reproduce");
        nameAction.add("reviewPolicy");
        nameAction.add("secondaryUse");
        nameAction.add("sell");
        nameAction.add("share");
        nameAction.add("shareAlike");
        nameAction.add("stream");
        nameAction.add("synchronize");
        nameAction.add("textToSpeech");
        nameAction.add("transfer");
        nameAction.add("transform");
        nameAction.add("translate");
        nameAction.add("uninstall");
        nameAction.add("use");
        nameAction.add("watermark");
        nameAction.add("write");
        nameAction.add("writeTo");
    }


}
