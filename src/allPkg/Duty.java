package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Duty extends attributeHolders implements Rules {
    public String uid;
    public String id;
    private Asset asset;
    private Action action;
    private List<Constraint> constraints = new ArrayList<Constraint>();
    private List<Party> parties = new ArrayList<Party>();
    public ArrayList<String> attributeNameList = new ArrayList<String>();

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setConstraint(Constraint constraint) { constraints.add(constraint); }

    public void setParty(Party party) { parties.add(party); }

    public void setDuty(Duty duty) {}

    public void setEntireConstraint(List<Constraint> constraints) { this.constraints = constraints; }

    public void setEntireParty(List<Party> parties) { this.parties = parties; }

    public void setEntireDuty(List<Duty> duties) {}

    public Asset getAsset() {
        return asset;
    }

    public Action getAction() {
        return action;
    }

    public List<Constraint> getConstraint() {
        return constraints;
    }

    public List<Duty> getDuty() {
        return null;
    }

    public List<Party> getParty() { return parties; }

    public ArrayList<String> getAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("uid");
        return this.attributeNameList;
    }

    public void setAttribute(String attribute, String value) {
        if (attribute == "id"){
            this.id = value;
            return;
        }

        if (attribute == "uid"){
            this.uid = value;
            return;
        }
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void copyInstance(Duty duty) {
        duty.setAction(this.getAction());
        duty.setAsset(this.getAsset());
        duty.setEntireConstraint(this.getConstraint());
        duty.setEntireDuty(this.getDuty());
        duty.setEntireParty(this.getParty());
        duty.setId(this.id);
        duty.setUID(this.uid);
    }

}
