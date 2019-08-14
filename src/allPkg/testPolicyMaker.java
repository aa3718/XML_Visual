package allPkg;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class testPolicyMaker {

    public List<Action> globalActions = new ArrayList<Action>();
    public List<Asset> globalAssets = new ArrayList<Asset>();
    public List<Party> globalParties = new ArrayList<Party>();
    public List<Constraint> globalConstraints = new ArrayList<Constraint>();
    public ArrayList<String> logicalConstraint = new ArrayList<String>();
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
            buildLogicalConstraint();

            // Making policy global elements
            NodeList nodePolicyList = doc.getElementsByTagName("o:Policy");
            if (nodePolicyList.getLength() != 0) {
                for (int i = 0; i < nodePolicyList.getLength(); i++) {
                    Node nodeP = nodePolicyList.item(i);
                    forPolicy(nodeP, builder);
                }
            }

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

            // Making obligations
            NodeList nodeObligationList = doc.getElementsByTagName("o:obligation");
            if (nodeObligationList.getLength() != 0) {
                for (int i = 0; i < nodeObligationList.getLength(); i++) {
                    Duty duty = new Duty();
                    builder.withObligation(duty);
                    Node nodeP = nodeObligationList.item(i);
                    forAll(nodeP, duty, builder);
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

                if (nodePP.getNodeName().equals("o:action")) {
                    Action action = new Action();
                    setAttributes(nodePP, action, builder,false);
                    forRefinement(nodePP,rules,action,builder,action.getName());
                    rules.setAction(action);
                    builder.withAction(action);
                }

                if (nodePP.getNodeName().equals("o:asset")) {
                    Asset asset = new Asset();
                    setAttributes(nodePP, asset, builder,false);
                    forRefinement(nodePP,rules,asset,builder,asset.getUID());
                    rules.setAsset(asset);
                    builder.withAsset(asset);
                }

                if (nodePP.getNodeName().equals("o:constraint")) {
                    Constraint constraint = new Constraint();
                    setAttributes(nodePP, constraint, builder,false);
                    rules.setConstraint(constraint);
                    builder.withConstraint(constraint);
                }

                if (nodePP.getNodeName().equals("o:party")) {
                    Party party = new Party();
                    setAttributes(nodePP, party, builder,false);
                    forRefinement(nodePP,rules,party,builder,party.getFunction());
                    rules.setParty(party);
                    builder.withParty(party);
                }

                if (nodePP.getNodeName().equals("o:duty") || nodePP.getNodeName().equals("o:remedy") || nodePP.getNodeName().equals("o:consequence")) {
                    System.out.println("In consequence and remedy area");
                    Duty duty = new Duty();
                    setAttributes(nodePP, duty, builder,false);
                    rules.setDuty(duty);
                    builder.withDuty(duty);
                    forAll(nodePP, duty, builder);
                }
            }
        }

        // Adding global to the Permission, Prohibition or Obligation
            for (int i = 0; i < globalAssets.size(); i++) {
                rules.setAsset(globalAssets.get(i));
            }
            for (int i = 0; i < globalActions.size(); i++) {
                rules.setAction(globalActions.get(i));
            }
            for (int i = 0; i < globalParties.size(); i++) {
                rules.setParty(globalParties.get(i));
        }
    }


    public void forRefinement(Node ruleNode, Rules rule,attributeHolders attributeHolders, PolicyBuilder builder, String on) {
        if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) ruleNode;
            NodeList nodeListForEachElement = eElement.getChildNodes();

            for (int k = 0; k < nodeListForEachElement.getLength(); k++) {
                Node nodePP = nodeListForEachElement.item(k);
                if (nodePP.getNodeName().equals("o:refinement")) {
                    Constraint constraint = new Constraint();
                    constraint.setConstraintOn(on);
                    setAttributes(nodePP, constraint, builder,false);
                    attributeHolders.setConstraint(constraint);
                    rule.setConstraint(constraint);
                    builder.withConstraint(constraint);
                }
            }
        }
    }


    public void forPolicy(Node ruleNode, PolicyBuilder builder) {
        if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) ruleNode;
            NodeList nodeListForEachElement = eElement.getChildNodes();

            for (int k = 0; k < nodeListForEachElement.getLength(); k++) {
                Node nodePP = nodeListForEachElement.item(k);

                if (nodePP.getNodeName().equals("o:action")) {
                    Action action = new Action();
                    setAttributes(nodePP, action, builder,false);
                    globalActions.add(action);
                    builder.withAction(action);
                }

                if (nodePP.getNodeName().equals("o:asset")) {
                    Asset asset = new Asset();
                    setAttributes(nodePP, asset, builder,false);
                    globalAssets.add(asset);
                    builder.withAsset(asset);
                }

                if (nodePP.getNodeName().equals("o:party")) {
                    Party party = new Party();
                    setAttributes(nodePP, party, builder,false);
                    globalParties.add(party);
                    builder.withParty(party);
                }

                if (nodePP.getNodeName().equals("o:constraint") || nodePP.getNodeName().equals("o:refinement")) {
                    Constraint constraint = new Constraint();
                    setAttributes(nodePP, constraint, builder,true);
                    globalConstraints.add(constraint);
                    //builder.withConstraint(constraint);
                }

            }
        }
    }


    // Takes in one node and set attribute to the model
    public void setAttributes(Node node, attributeHolders element, PolicyBuilder builder, boolean fromGlobalConstraint) {
        NamedNodeMap map = node.getAttributes();
        int numberAttr = map.getLength();

        // No attributes
        if (numberAttr == 0) {
            return;
        }

        for (int i = 0; i < numberAttr; i++) {
            Attr attribute = (Attr) map.item(i);

            if (attribute.getName().equals("idref")) {

                String reference = attribute.getValue();
                if (element instanceof Asset) {
                    try {
                        (builder.findAsset(reference)).copyInstance((Asset)element);
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                        System.out.println("Error Cloning");
                    }
                }
                if (element instanceof Action) {
                    try {
                        (builder.findAction(reference)).copyInstance((Action)element);
                    } catch (Exception e) {
                        System.out.println("Error Cloning");
                    }
                }
                if (element instanceof Constraint) {
                    try {
                        (builder.findConstraint(reference)).copyInstance((Constraint)element);
                    } catch (Exception e) {
                        System.out.println("Error Cloning");
                    }
                }
                /*
                if (element instanceof Duty) {
                    try {
                        (builder.findDuty(reference)).copyInstance((Duty)element);
                    } catch (Exception e) {
                        System.out.println("Error Cloning");
                    }
                }
                */
                if (element instanceof Party) {
                    try {
                        (builder.findParty(reference)).copyInstance((Party)element);
                    } catch (Exception e) {
                        System.out.println("Error Cloning");
                    }
                }
                return;
            }

            if (attribute.getName().equals("uid") && element instanceof Duty) {

                String reference = attribute.getValue().substring(attribute.getValue().lastIndexOf("#")+1);
                System.out.println(reference);
                try {
                    (builder.findDuty(reference)).copyInstance((Duty)element);
                } catch (Exception e) {
                    System.out.println("Error Cloning");
                }
            }

            ArrayList<String> theList = element.getAttributeList();

            System.out.println(attribute.getName() + "<-Attribute name");
            System.out.println(attribute.getValue() + "<-Attribute value");

            if (theList.contains(attribute.getName())) {
                System.out.println(attribute.getValue().lastIndexOf("/") + "<- Number / in value");
                if (attribute.getValue().lastIndexOf("/") > 0) {
                    String subString = attribute.getValue().substring(attribute.getValue().lastIndexOf("/") + 1);
                    if (attribute.getValue().lastIndexOf("#")+1 > attribute.getValue().lastIndexOf("/") + 1) {
                        subString = attribute.getValue().substring(attribute.getValue().lastIndexOf("#") + 1);
                    }
                    element.setAttribute(attribute.getName(), subString);
                } else {
                    element.setAttribute(attribute.getName(), attribute.getValue());
                }
            }
        }
        if (!fromGlobalConstraint) {
            if (element instanceof Constraint) {
                System.out.println("Constraint is logical!!!!");
                if (logicalConstraint.contains(((Constraint)element).getName())) {
                    System.out.println("Constraint is logical");
                    searchForLogical((Constraint) element);
                }
            }
        }
    }


    public void searchForLogical(Constraint constraint) {
        if (logicalConstraint.contains(constraint.getName())) {
            constraint.setLogicalConstraint(true);
            System.out.println(constraint.getRightOperand() + " " + constraint.getName());
            String idString = constraint.getRightOperand().replaceAll("#", "");
            String[] ids = idString.split(" ");

            for (int j = 0; j < ids.length; j++) {
                for (int k = 0; k < globalConstraints.size(); k++) {
                    if (globalConstraints.get(k).getID().equals(ids[j])) {
                        constraint.setAttachedConstraint(globalConstraints.get(k));
                        Constraint constraintTemp = globalConstraints.get(k);
                        searchForLogical(constraintTemp);
                    }
                }
            }
        }
    }


    public void buildLogicalConstraint() {
        logicalConstraint.add("xone");
        logicalConstraint.add("or");
        logicalConstraint.add("and");
        logicalConstraint.add("andSequence");
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
//       System.out.println(policy.getConstraint(1).getRightOperand());
    }




}