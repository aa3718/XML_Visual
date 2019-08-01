package allPkg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class TestParse {
    Policy policy = null;

    public void setXmlFunction(String filename, JPanel visuall) {

        try {
            // Read from an xml file using a parser
            // "/Users/Chapman/file.txt"
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Check to make sure it is part of ODRL allPkg.Policy
            if (checkPolicy(doc)) {
                System.out.println("It is indeed a Policy!");
            } else {
                System.out.println("Error: Not an ODRL Policy.");
            }

            // Create Policy Builder instance
            PolicyBuilder builder = new PolicyBuilder();

            // Go through the Policies Permissions
            NodeList nList = doc.getElementsByTagName("o:permission");
            for (int allEl = 0; allEl < nList.getLength(); allEl++) {
                Node nNode = nList.item(allEl);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //Adding permission to panel
                    Icon permissionII = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
                    JButton permissionI = new JButton("Permission",permissionII);
                    permissionI.setBounds((180*allEl+80),50,100,100);
                    permissionI.setVerticalTextPosition(SwingConstants.BOTTOM);
                    permissionI.setHorizontalTextPosition(SwingConstants.CENTER);
                    visuall.add(permissionI);

                    // Policy building  // Need to add all attributes
                    Permission permission = new Permission("Pancake");
                    builder.withPermission(permission);

                    // Testing and Tag name elements for Permissions
                    eElement.getElementsByTagName("o:asset");
                    System.out.println("PermissionAsset");

                    eElement.getElementsByTagName("o:action");
                    System.out.println("PermissionAction");

                }
            }

            int exisitingElementX = nList.getLength()*180;
            System.out.println(exisitingElementX);

            // Go through the Policies Prohibitions
            NodeList nList2 = doc.getElementsByTagName("o:prohibition");
            for (int allEl = 0; allEl < nList2.getLength(); allEl++) {
                Node nNode2 = nList2.item(allEl);
                System.out.println("\nCurrent Element :" + nNode2.getNodeName());
                if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode2;

                    // Adding prohibition to panel
                    Icon prohibitionII = new ImageIcon("/Users/Chapman/Downloads/prohibition-symbol.png");
                    JButton prohibitionI = new JButton("Prohibition",prohibitionII);
                    prohibitionI.setVerticalTextPosition(SwingConstants.BOTTOM);
                    prohibitionI.setHorizontalTextPosition(SwingConstants.CENTER);
                    prohibitionI.setBounds((180*allEl+80+exisitingElementX),50,100,100);
                    visuall.add(prohibitionI);

                    // Policy building  // Need to add all attributes
                    Prohibition pProhibition = new Prohibition();
                    builder.withProhibition(pProhibition);

                    // Testing and Tag name elements for Prohibitions
                    eElement.getElementsByTagName("o:asset");
                    System.out.println("ConstraintAsset");

                    eElement.getElementsByTagName("o:action");
                    System.out.println("ConstraintAction");

                }
            }

            /*
                System.out.println(eElement.getAttribute("id"));
            }
            */

            this.policy = builder.build();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public boolean checkPolicy(Document doc) {
        if (doc.getDocumentElement().getNodeName() == "o:Policy") {
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            return true;
        } else {
            return false;
        }
    }

    public Policy getPolicy() {
        return policy;
    }

}

