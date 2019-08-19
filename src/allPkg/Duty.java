package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Duty extends attributeHolders implements Rules {

    private String uid;
    private String id;
    private String fullUid;
    private String fullId;
    private List<Asset> assets = new ArrayList<Asset>();
    private List<Action> actions = new ArrayList<Action>();
    private List<Constraint> constraints = new ArrayList<Constraint>();
    private List<Party> parties = new ArrayList<Party>();
    private List<Duty> consequences = new ArrayList<Duty>();
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private boolean isObligation = false;

    Duty() {
        buildAttributeList();
    }

    public void setAction(Action action) {
        actions.add(action);
    }

    public void setAsset(Asset asset) {
        assets.add(asset);
    }

    public void setConstraint(Constraint constraint) { constraints.add(constraint); }

    public void setParty(Party party) { parties.add(party); }

    public void setDuty(Duty consequence) { consequences.add(consequence); }

    public void setEntireConstraint(List<Constraint> constraints) { this.constraints = constraints; }

    public void setEntireParty(List<Party> parties) { this.parties = parties; }

    public void setEntireDuty(List<Duty> duties) {}

    public void setEntireAsset(List<Asset> assets) { this.assets = assets; }

    public void setEntireAction(List<Action> actions) { this.actions = actions; }

    public void setIsObligation(boolean value) { this.isObligation = isObligation; }

    public boolean getIsObligation() { return isObligation; }

    public String getID() { return this.id; }

    public String getUID() { return this.uid; }

    public List<Asset> getAsset() {
        return assets;
    }

    public List<Action> getAction() {
        return actions;
    }

    public List<Constraint> getConstraint() {
        return constraints;
    }

    public List<Duty> getDuty() {
        return consequences;
    }

    public List<Party> getParty() { return parties; }

    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    public String getFullUid() { return this.fullUid; }

    public String getFullId() { return this.fullId; }

    public void buildAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("uid");
    }

    public void setAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.id = value;
            return;
        }

        if (attribute.equals("uid")){
            this.uid = value;
            return;
        }
    }

    public void setFullAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.fullId = value;
            return;
        }

        if (attribute.equals("uid")){
            this.fullUid = value;
            return;
        }
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullUID(String uid) {
        this.fullUid = uid;
    }

    public void setFullId(String id) {
        this.fullId = id;
    }

    public void copyInstance(Duty duty) {
        System.out.println("Copies it - In duty");
        duty.setEntireAction(this.getAction());
        duty.setEntireAsset(this.getAsset());
        duty.setEntireConstraint(this.getConstraint());
        duty.setEntireDuty(this.getDuty());
        duty.setEntireParty(this.getParty());
        duty.setId(this.id);
        duty.setUID(this.uid);
        duty.setIsObligation(this.isObligation);
        duty.setFullUID(this.fullUid);
        duty.setId(this.fullId);
    }

}
