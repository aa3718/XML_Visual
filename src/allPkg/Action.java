package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Action extends attributeHolders {
    //private ArrayList<String> nameAction = new ArrayList<String>();
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private String name;
    private String id;
    private ArrayList<Constraint> refinements = new ArrayList<Constraint>();
    private Rules parentType;
    private String fullName;
    private String fullId;

    Action() {
        buildAttributeList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setConstraint(Constraint refinement) { this.refinements.add(refinement); }

    public List<Constraint> getConstraint() {
        return refinements;
    }

    public String getID() { return this.id; }

    public void setParentType(Rules rule) {
        this.parentType = rule;
        if(parentType instanceof Permission){
            System.out.println("It is an instanceOFFF Insinde");
        }

    }

    public Rules getParentType() { return parentType;}

    public void buildAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("name");
    }

    @Override
    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.id = value;
            return;
        }

        if (attribute.equals("name")){
            this.name = value;
            return;
        }
    }


    void setFullAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.fullId = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("id",subString);
            } else {
                setAttribute("id",value);
            }
            return;
        }

        if (attribute.equals("name")){
            this.fullName = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("name",subString);
            } else {
                setAttribute("name",value);
            }
            return;
        }
    }

    public void copyInstance(Action action) {
        action.setName(this.name);
        action.setID(this.id);
        action.setFullID(this.fullId);
        action.setFullName(this.fullName);
    }

    public String getName() { return this.name; }

    public String getFullName() { return this.fullName; }

    public String getFullID() { return this.fullId; }

    public void setFullID(String fullId) {
        this.fullId = fullId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
