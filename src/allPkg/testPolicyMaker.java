package allPkg;

import org.w3c.dom.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;


public class testPolicyMaker {
    Policy policy = null;

    public void setXmlFunction(String filename) {

        try {

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

            // Making permissions
            NodeList nodePermissionList = doc.getElementsByTagName("o:permission");
            if (nodePermissionList.getLength() != 0) {
                for (int i = 0; i < nodePermissionList.getLength(); i++) {
                    Permission permission = new Permission();
                    builder.withPermission(permission);
                    Node nodeP = nodePermissionList.item(i);
                    forAll(nodeP, permission, builder);
                }
            }

            // Making prohibitions
            NodeList nodeProhibitionList = doc.getElementsByTagName("o:prohibition");
            if (nodeProhibitionList.getLength() != 0) {
                for (int i = 0; i < nodeProhibitionList.getLength(); i++) {
                    Prohibition prohibition = new Prohibition();
                    builder.withProhibition(prohibition);
                    Node nodeP = nodeProhibitionList.item(i);
                    forAll(nodeP, prohibition, builder);
                }
            }

            this.policy = builder.build();
            print();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void forAll(Node ruleNode, Rules rules, PolicyBuilder builder) {
        if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) ruleNode;
            NodeList nodeListForEachElement = eElement.getChildNodes();
            for (int k = 0; k < nodeListForEachElement.getLength(); k++) {
                Node nodePP = nodeListForEachElement.item(k);

                if (nodePP.getNodeName() == "o:action") {
                    Action action = new Action();
                    setAttributes(nodePP, action);
                    rules.setAction(action);
                    builder.withAction(action);
                }

                if (nodePP.getNodeName() == "o:asset") {
                    Asset asset = new Asset();
                    setAttributes(nodePP, asset);
                    rules.setAsset(asset);
                    builder.withAsset(asset);
                }

                if (nodePP.getNodeName() == "o:constraint") {
                    Constraint constraint = new Constraint();
                    setAttributes(nodePP, constraint);
                    rules.setContraint(constraint);
                    builder.withConstraint(constraint);
                }

                if (nodePP.getNodeName() == "o:duty") {
                    Duty duty = new Duty();
                    rules.setDuty(duty);
                    forAll(nodePP, duty, builder);
                }

            }
        }
    }


    // Takes in one node and set attribute to the model
    public void setAttributes(Node node, attributeHolders element) {
        NamedNodeMap map = node.getAttributes();
        int numberAttr = map.getLength();

        // No attributes
        if (numberAttr == 0) {
            return;
        }

        for (int i = 0; i < numberAttr; i++) {
            Attr attribute = (Attr) map.item(i);
            ArrayList<String> theList = element.getAttributeList();

            for (int j = 0; j < theList.size(); j++) {

                if (attribute.getName() == theList.get(j)) {
                    if (attribute.getValue().lastIndexOf("/") > 0) {
                        String subString = attribute.getValue().substring(attribute.getValue().lastIndexOf("/") + 1);
                        element.setAttribute(attribute.getName(), subString);
                    } else {
                        element.setAttribute(attribute.getName(), attribute.getValue());

                    }

                }

            }

        }
    }

    // Check if the root element is a Policy
    public boolean checkPolicy(Document doc) {
        if (doc.getDocumentElement().getNodeName() == "o:Policy") {
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            return true;
        } else {
            return false;
        }
    }

    // Return the Policy imported and build
    public Policy getPolicy() {
        return policy;
    }


    public void print() {
        System.out.println(this.policy.getAction().name);
        System.out.println(this.policy.permissions.get(0).getAsset().id);
        System.out.println(this.policy.permissions.get(0).getDuty(0).getConstraint().name);
    }

    /*
    public static void main(String argv[]) {
        String name = "/Users/Chapman/file.txt";
        testPolicyMaker test = new testPolicyMaker();
        test.setXmlFunction(name);

        System.out.println(test.policy.getAction().name);
        System.out.println(test.policy.permissions.get(0).getAsset().id);
        System.out.println(test.policy.prohibitions.get(0).getConstraint().name);

    }

    //Adding permission to panel
                    Icon permissionII = new ImageIcon("/Users/Chapman/Downloads/permissionI.png");
                    JButton permissionI = new JButton("Permission", permissionII);
                    permissionI.setBounds((180 * allEl + 80), 100, 100, 100);
                    permissionI.setVerticalTextPosition(SwingConstants.BOTTOM);
                    permissionI.setHorizontalTextPosition(SwingConstants.CENTER);
                    permissionI.setFocusPainted(false);
                    visuall.add(permissionI);
// Adding prohibition to panel
                    Icon prohibitionII = new ImageIcon("/Users/Chapman/Downloads/prohibition-symbol.png");
                    JButton prohibitionI = new JButton("Prohibition",prohibitionII);
                    prohibitionI.setVerticalTextPosition(SwingConstants.BOTTOM);
                    prohibitionI.setHorizontalTextPosition(SwingConstants.CENTER);
                    prohibitionI.setBounds((180*allEl+80+exisitingElementX),100,100,100);
                    prohibitionI.setFocusPainted(false);
                    visuall.add(prohibitionI);

    */

}