package allPkg;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Random;

public class xmlCreator {
    public static int numberForPP = 4;
    public static int dictionaryNumberAssetType = 6;



    public static void main(String argv[]) {

        try {
            // ODRL Namespace
            String namespace = "http://www.w3.org/ns/odrl/2/";

            // Document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // First required policy root node
            Element root = document.createElementNS(namespace,"o:Policy");
            document.appendChild(root);

            // Set random number of Permission elements
            Random randomPermission = new Random();
            int randomPermissionNumber = randomPermission.nextInt(numberForPP);

            // SYSTEMOUT
            System.out.println("Number of permission: "+ randomPermissionNumber);

            // Set random number of Prohibition elements dependent on the number of Permission
            Random randomProhibition = new Random();
            int randomProhibitionNumber;
            if (randomPermissionNumber != 0) {
                randomProhibitionNumber = randomProhibition.nextInt(numberForPP);
            } else {
                randomProhibitionNumber = randomProhibition.nextInt(numberForPP - 1) + 1;
            }

            // SYSTEMOUT
            System.out.println("Number of prohibitions: "+ randomProhibitionNumber);

            // Append all Permissions
            ArrayList<Element> permissions = new ArrayList<Element>();
            for (int i = 0; i < randomPermissionNumber; i++) {
                Element permission = document.createElement("o:permission");
                permissions.add(permission);
                root.appendChild(permission);
            }

            // Append all Prohibitions
            ArrayList<Element> prohibitions = new ArrayList<Element>();
            for (int i = 0; i < randomProhibitionNumber; i++) {
                Element prohibition = document.createElement("o:permission");
                prohibitions.add(prohibition);
                root.appendChild(prohibition);
            }

            for (Element element : permissions) {
                Random randomAsset = new Random();
                int randomAssetActionNumber = randomAsset.nextInt(numberForPP-3)+1;

                // SYSTEMOUT
                System.out.println("Number of A/A: "+ randomAssetActionNumber);

                for(int i = 0; i < randomAssetActionNumber; i++) {
                    Element asset = document.createElement("o:asset");
                    Element action = document.createElement("o:action");
                    element.appendChild(asset);
                    element.appendChild(action);
                }

                Random randomConstraint = new Random();
                int randomConstraintNumber = randomConstraint.nextInt(numberForPP-3);

                // SYSTEMOUT
                System.out.println("Number of constraint: "+ randomConstraintNumber);

                for(int i = 0; i < randomConstraintNumber; i++) {
                    Element constraint = document.createElement("o:constraint");
                    element.appendChild(constraint);
                }

                Random randomDuty = new Random();
                int randomDutyNumber = randomDuty.nextInt(numberForPP-3);

                System.out.println("Number of duty: "+ randomDutyNumber);

                ArrayList<Element> duties = new ArrayList<Element>();
                for(int i = 0; i < randomDutyNumber; i++) {
                    Element duty = document.createElement("o:duty");
                    duties.add(duty);
                    element.appendChild(duty);
                }

            }

            for (Element element : prohibitions) {
                Random randomAsset = new Random();
                int randomAssetActionNumber = randomAsset.nextInt(numberForPP-3)+1;

                // SYSTEMOUT
                System.out.println("Number of A/A: "+ randomAssetActionNumber);

                for(int i = 0; i < randomAssetActionNumber; i++) {
                    Element asset = document.createElement("o:asset");
                    Element refinement = document.createElement("o:refinement");
                    asset.appendChild(refinement);
                    Element action = document.createElement("o:action");
                    element.appendChild(asset);
                    element.appendChild(action);
                }

                Random randomConstraint = new Random();
                int randomConstraintNumber = randomConstraint.nextInt(numberForPP-3);

                // SYSTEMOUT
                System.out.println("Number of constraint: "+ randomConstraintNumber);

                for(int i = 0; i < randomConstraintNumber; i++) {
                    Element constraint = document.createElement("o:constraint");
                    element.appendChild(constraint);
                }

                Random randomDuty = new Random();
                int randomDutyNumber = randomDuty.nextInt(numberForPP-3);

                System.out.println("Number of duty: "+ randomDutyNumber);

                ArrayList<Element> duties = new ArrayList<Element>();
                for(int i = 0; i < randomDutyNumber; i++) {
                    Element duty = document.createElement("o:duty");
                    duties.add(duty);
                    element.appendChild(duty);
                }

            }

            /*
            // Create Permission Array with random number and each have one asset and one random action
            if (randomPermissionNumber != 0) {
                ArrayList<Element> permissions = new ArrayList<Element>();

                for (int i = 0; i < randomPermissionNumber; i++) {
                    Element permission = document.createElement("o:permission");
                    permissions.add(permission);

                    // Set random number of Assets elements dependent on number of Permissions and Prohibitions
                    Random randomAsset = new Random();
                    int randomAssetNumber = randomAsset.nextInt(numberForPP);
                    int randomNumber = randomAsset.nextInt(numberForPP - 3) + 1;

                    ArrayList<Element> assets = new ArrayList<Element>();
                    for (int j = 0; j < randomAssetNumber; j++) {
                        Element asset = document.createElement("o:asset");

                        // Same random number of  for each asset
                        Element action = document.createElement("o:action");

                        Attr attribute = document.createAttribute("id");
                        attribute.setValue("ac"+ randomNumber);

                        asset.setAttributeNode(attribute);
                        action.setAttributeNode(attribute);
                        assets.add(asset);
                    }

                    Attr attr = document.createAttribute("id");
                    attr.setValue(assets.get(randomNumber).getAttribute("id"));
                    permission.setAttributeNode(attr);
                    permission.appendChild(asset);
                }
            }


            //you can also use staff.setAttribute("id", "1") for this

            // firstname element
            Element asset = document.createElement("o:asset");
            Attr attr = document.createAttribute("id");
            attr.setValue("as "+i);
            firstName.setAttributeNode(attr);
            employee.appendChild(firstName);

            // lastname element
            Element lastname = document.createElement("o:action");
            Attr attrr = document.createAttribute("id");
            attrr.setValue("ac1");
            lastname.setAttributeNode(attrr);
            employee.appendChild(lastname);

            // email element
            Element email = document.createElement("o:constraint");
            Attr attrrr = document.createAttribute("id");
            attrrr.setValue("c1");
            email.setAttributeNode(attrrr);
            Element department = document.createElement("o:duty");
            email.appendChild(department);
            employee.appendChild(email);
            */


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult("/Users/Chapman/Documents/inputFile2.xml");

            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");


        } catch (Exception e) {
            System.out.println("Error");
        }

    }


}
