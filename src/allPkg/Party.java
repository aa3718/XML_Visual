package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Party extends attributeHolders{

    private static ArrayList<String> nameParty = new ArrayList<String>();
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private String function;
    private String uid;
    private String scope;
    private String id;
    private String fullFunction;
    private String fullUid;
    private String fullScope;
    private String fullId;
    private ArrayList<Constraint> refinements = new ArrayList<Constraint>();

    Party() {
        buildAttributeList();
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getFunction() { return this.function; }

    public String getUID() { return this.uid; }

    public String getID() { return this.id; }

    public String getFullID() { return this.fullId; }

    public String getFullUid() { return this.fullUid; }

    public String getFullFunction() { return this.fullFunction; }

    public String getFullScope() { return this.fullScope; }

    public void setFullUid(String uid) { this.fullUid = uid; }

    public void setFullId(String id) { this.fullId = id; }

    public void setFullFunction(String function) { this.fullFunction = function; }

    public void setFullScope(String scope) { this.fullScope = scope; }

    public void setConstraint(Constraint refinement) { this.refinements.add(refinement); }

    public void setFullConstraint(ArrayList<Constraint> refinement) { this.refinements = refinement; }

    public List<Constraint> getConstraint() {
        return refinements;
    }

    public static void buildParty() {
        nameParty.add("assigner");
        nameParty.add("assignee");
        nameParty.add("attributedParty");
        nameParty.add("consentingParting");
        nameParty.add("informedParty");
        nameParty.add("compensatedParty");
        nameParty.add("trackingParty");
    }

    public void setAttribute(String attribute, String value) {
        if (attribute.equals("function")){
            this.setFunction(value);
            return;
        }

        if (attribute.equals("uid")){
            this.setUID(value);
            return;
        }

        if (attribute.equals("id")){
            this.setID(value);
            return;
        }

        if (attribute.equals("scope")){
            this.setScope(value);
            return;
        }
    }

    public void setFullAttribute(String attribute, String value) {
        if (attribute.equals("function")){
            this.fullFunction = value;
            return;
        }

        if (attribute.equals("uid")){
            this.fullUid = value;
            return;
        }

        if (attribute.equals("id")){
            this.fullId = value;
            return;
        }

        if (attribute.equals("scope")){
            this.fullScope = value;
            return;
        }
    }

    public void buildAttributeList() {
        attributeNameList.add("function");
        attributeNameList.add("relation");
        attributeNameList.add("scope");
        attributeNameList.add("uid");
        attributeNameList.add("id");
    }

    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    public void copyInstance(Party party) {
        party.setScope(this.scope);
        party.setFunction(this.function);
        party.setID(this.id);
        party.setUID(this.uid);
        party.setFullConstraint(this.refinements);
        party.setFullFunction(this.fullFunction);
        party.setFullUid(this.fullUid);
        party.setFullScope(this.fullScope);
        party.setFullId(this.fullId);
    }

}
