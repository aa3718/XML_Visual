package allPkg;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class xmlMaking {
    private Document documents;
    private Policy policy;


    public xmlMaking(Policy policy) {
        this.policy = policy;
    }

    public void createFile() {

        try {
            String namespace = "http://www.w3.org/ns/odrl/2/";

            // Document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            documents = document;

            // First required policy root node
            Element root = document.createElementNS(namespace, "o:Policy");
            documents.appendChild(root);

            getAttributesPolicy(root,policy);

            for (int i = 0; i < policy.getAllPermission().size(); i++) {
                Element permission = document.createElement("o:permission");
                root.appendChild(permission);

                getRule(policy.getAllPermission().get(i), permission);
            }


            for (int i = 0; i < policy.getAllProhibition().size(); i++) {
                Element prohibition = document.createElement("o:prohibition");
                root.appendChild(prohibition);

                getRule(policy.getAllProhibition().get(i), prohibition);

            }

            for (int i = 0; i < policy.getAllObligation().size(); i++) {
                Element obligation = document.createElement("o:obligation");
                root.appendChild(obligation);

                getRule(policy.getAllObligation().get(i), obligation);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transf = tf.newTransformer();
            DOMSource domSource = new DOMSource(documents);
            StreamResult streamResult = new StreamResult(new File("/Users/Chapman/Documents/ODRLxmlTestFile/madeFile.xml"));

            transf.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getRule(Rules rules, Element root) {

            for (int j =0; j < rules.getAction().size(); j++) {
                Element action = documents.createElement("o:action");
                root.appendChild(action);
                getAttributesAction(action,rules.getAction().get(j));
                getRefinement(rules.getAction().get(j).getConstraint(),action);
            }

            for (int j = 0; j < rules.getAsset().size(); j++) {
                Element asset = documents.createElement("o:asset");
                root.appendChild(asset);
                getAttributesAsset(asset,rules.getAsset().get(j));
                getRefinement(rules.getAsset().get(j).getConstraint(),asset);
            }

            for (int j =0; j < rules.getParty().size(); j++) {
                rules.getParty().get(j);
                Element party = documents.createElement("o:party");
                root.appendChild(party);
                getAttributesParty(party,rules.getParty().get(j));
                getRefinement(rules.getParty().get(j).getConstraint(),party);

            }

            for (int j =0; j < rules.getConstraint().size(); j++) {
                rules.getConstraint().get(j);
                Element constraint = documents.createElement("o:constraint");
                getAttributesConstraint(constraint,rules.getConstraint().get(j));
                root.appendChild(constraint);
            }

            for (int j =0; j < rules.getDuty().size(); j++) {
                Element duty;
                if (rules instanceof Permission) {
                    duty = documents.createElement("o:duty");
                } else if (rules instanceof Prohibition) {
                    duty = documents.createElement("o:remedy");
                } else {
                    duty = documents.createElement("o:consequence");
                }
                root.appendChild(duty);
                getRule(rules.getDuty().get(j),duty);
            }
    }


    public void getAttributesPolicy(Element element, Policy component) {

        if (component.getType() != null) {
            Attr type = documents.createAttribute("type");
            type.setValue(component.getType());
            element.setAttributeNode(type);
        }

        if (component.getUID() != null) {
            Attr uid = documents.createAttribute("uid");
            uid.setValue(component.getUID());
            element.setAttributeNode(uid);
        }

        if (component.getProfile() != null) {
            Attr profile = documents.createAttribute("profile");
            profile.setValue(component.getProfile());
            element.setAttributeNode(profile);
        }

        if (component.getConflict() != null) {
            Attr conflict = documents.createAttribute("conflict");
            conflict.setValue(component.getProfile());
            element.setAttributeNode(conflict);
        }

        if (component.getInheritFrom() != null) {
            Attr inherit = documents.createAttribute("inheritFrom");
            inherit.setValue(component.getInheritFrom());
            element.setAttributeNode(inherit);
        }

    }


    public void getAttributesAsset(Element element, Asset component) {
        if (component.getID() != null) {
            Attr id = documents.createAttribute("id");
            id.setValue(component.getID());
            element.setAttributeNode(id);
        }

        if (component.getUID() != null) {
            Attr uid = documents.createAttribute("uid");
            uid.setValue(component.getFullUid());
            element.setAttributeNode(uid);
        }

        if (component.getRelation() != null) {
            Attr relation = documents.createAttribute("relation");
            relation.setValue(component.getFullRelation());
            element.setAttributeNode(relation);
        }

    }

    public void getAttributesAction(Element element, Action component) {
        if (component.getID() != null) {
            Attr id = documents.createAttribute("id");
            id.setValue(component.getID());
            element.setAttributeNode(id);
        }

        if (component.getName() != null) {
            Attr name = documents.createAttribute("name");
            name.setValue(component.getFullName());
            element.setAttributeNode(name);
        }

    }

    public void getAttributesParty(Element element, Party component) {
        if (component.getID() != null) {
            Attr id = documents.createAttribute("id");
            id.setValue(component.getID());
            element.setAttributeNode(id);
        }

        if (component.getUID() != null) {
            Attr uid = documents.createAttribute("uid");
            uid.setValue(component.getFullUid());
            element.setAttributeNode(uid);
        }

        if (component.getFunction() != null) {
            Attr function = documents.createAttribute("function");
            function.setValue(component.getFullFunction());
            element.setAttributeNode(function);
        }

        if (component.getFullScope() != null) {
            Attr scope = documents.createAttribute("scope");
            scope.setValue(component.getFullScope());
            element.setAttributeNode(scope);
        }

    }

    public void getAttributesConstraint(Element element, Constraint component) {
        if (component.getID() != null) {
            Attr id = documents.createAttribute("id");
            id.setValue(component.getID());
            element.setAttributeNode(id);
        }

        if (component.getName() != null) {
            Attr name = documents.createAttribute("name");
            name.setValue(component.getFullName());
            element.setAttributeNode(name);
        }

        if (component.getOperator() != null) {
            Attr operand = documents.createAttribute("operator");
            operand.setValue(component.getFullOperator());
            element.setAttributeNode(operand);
        }

        if (component.getRightOperand() != null) {
            Attr rightOperand = documents.createAttribute("rightOperand");
            rightOperand.setValue(component.getRightOperand());
            element.setAttributeNode(rightOperand);
        }

        if (component.getFullUnit() != null) {
            Attr unit = documents.createAttribute("unit");
            unit.setValue(component.getFullUnit());
            element.setAttributeNode(unit);
        }

        if (component.getFullDataType() != null) {
            Attr dataType = documents.createAttribute("dataType");
            dataType.setValue(component.getFullDataType());
            element.setAttributeNode(dataType);
        }

        if (component.getFullStatus() != null) {
            Attr status = documents.createAttribute("status");
            status.setValue(component.getFullStatus());
            element.setAttributeNode(status);
        }
    }


    public void getRefinement(List<Constraint> constraints,Element parent) {

        for (int k = 0; k < constraints.size(); k++) {
            constraints.get(k);
            Element refinement = documents.createElement("o:refinement");
            getAttributesConstraint(refinement,constraints.get(k));
            parent.appendChild(refinement);
        }
    }


}
