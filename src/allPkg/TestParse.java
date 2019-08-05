package allPkg;

import org.w3c.dom.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class TestParse {
    Policy policy = null;

    public void setXmlFunction(String filename, JPanel visuall, JPanel visual, JFrame frame) {

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

            // Create frame for drawing graph
            JFrame frameNew = new JFrame();
            frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameNew.setSize(500, 500);

            // Go through the Policies Permissions
            NodeList nList = doc.getElementsByTagName("o:permission");









            for (int allEl = 0; allEl < nList.getLength(); allEl++) {
                Node nNode = nList.item(allEl);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //Adding permission to panel
                    Icon permissionII = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
                    JButton permissionI = new JButton("Permission", permissionII);
                    permissionI.setBounds((180 * allEl + 80), 100, 100, 100);
                    permissionI.setVerticalTextPosition(SwingConstants.BOTTOM);
                    permissionI.setHorizontalTextPosition(SwingConstants.CENTER);
                    permissionI.setFocusPainted(false);
                    visuall.add(permissionI);

                    // Policy building  // Need to add all attributes
                    Permission permission = new Permission();
                    builder.withPermission(permission);

                    // Testing and Tag name elements for Permissions
                    //eElement.getElementsByTagName("o:asset");

                    NodeList nListP = eElement.getChildNodes();

                    for (int allElP = 0; allElP < nListP.getLength(); allElP++) {
                        Node nNodeP = nListP.item(allElP);
                        System.out.println("\nCurrent Element :" + nNodeP.getNodeName());
                        if (nNodeP.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementP = (Element) nNodeP;

                            NamedNodeMap map = nNodeP.getAttributes();
                            int numberAttr = map.getLength();
                            System.out.println("heyi " + numberAttr);
                            for (int i = 0; i < numberAttr; i++) {
                                Attr attribute = (Attr) map.item(i);
                                int index = attribute.getValue().lastIndexOf("/");
                                if (index > 0) {
                                    System.out.println("Not sure " + attribute.getValue().substring(index+1));
                                } else {
                                    System.out.println("Not sure " + attribute.getValue());
                                }
                            }
                        }


                        System.out.println("PermissionAsset");

                        eElement.getElementsByTagName("o:action");
                        eElement.getAttribute("name");

                    }
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
                    prohibitionI.setBounds((180*allEl+80+exisitingElementX),100,100,100);
                    prohibitionI.setFocusPainted(false);
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

