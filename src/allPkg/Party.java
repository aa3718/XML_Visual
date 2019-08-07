package allPkg;

import java.util.ArrayList;

public class Party extends attributeHolders{

    public static ArrayList<String> nameParty = new ArrayList<String>();
    public ArrayList<String> attributeNameList = new ArrayList<String>();
    public String function;
    public String uid;
    public String scope;
    public String id;

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
        if (attribute == "function"){
            this.setFunction(value);
            return;
        }

        if (attribute == "uid"){
            this.setUID(value);
            return;
        }

        if (attribute == "id"){
            this.setID(value);
            return;
        }

        if (attribute == "scope"){
            this.setScope(value);
            return;
        }
    }

    public ArrayList<String> getAttributeList() {
        attributeNameList.add("function");
        attributeNameList.add("relation");
        attributeNameList.add("scope");
        attributeNameList.add("uid");
        attributeNameList.add("id");
        return this.attributeNameList;
    }

    public void copyInstance(Party party) {
        party.setScope(this.scope);
        party.setFunction(this.function);
        party.setID(this.id);
        party.setUID(this.uid);
    }

}
