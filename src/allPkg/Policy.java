package allPkg;

import java.util.ArrayList;

public class Policy {

    public ArrayList<Permission> permissions;
    private ArrayList<Prohibition> prohibitions;
    private ArrayList<Action> actions;
    private ArrayList<Asset> assets;
    private ArrayList<Constraint> constraints;
    private ArrayList<Duty> duties;
    private ArrayList<Party> parties;
    private ArrayList<Duty> obligations;

    private String uid;
    private String type;
    private String conflict;
    private String inheritFrom;
    private String profile;

    public Policy(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addProhibition(ArrayList<Prohibition> prohibitions) {
        this.prohibitions = prohibitions;
    }

    public void addAction(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void addObligation(ArrayList<Duty> obligations) { this.obligations = obligations; }

    public void addAsset(ArrayList<Asset> assets) {
        this.assets = assets;
    }

    public void addConstraint(ArrayList<Constraint> constraints) {
        this.constraints = constraints;
    }

    public void addDuty(ArrayList<Duty> duties) {
        this.duties = duties;
    }

    public void addParty(ArrayList<Party> parties) {
        this.parties = parties;
    }


    public void setType(String type) {
        int indexLast = type.lastIndexOf("/");
        this.type = type.substring(indexLast);
    }

    public String getType() {
        return this.type;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setUID(String UID) {
        int indexLast = UID.lastIndexOf("/");
        this.uid = UID.substring(indexLast);;
    }

    public String getUID() {
        return this.uid;
    }

    public void setProfile(String profile) {
        int indexLast = profile.lastIndexOf("/");
        this.profile = profile.substring(indexLast);;
    }

    public Permission getPermission(int index) {
        return permissions.get(index);
    }

    public Prohibition getProhibition(int index) {
        return prohibitions.get(index);
    }

    public Duty getObligation(int index) { return obligations.get(index); }

    public Action getAction(int index) {
        return actions.get(index);
    }

    public Asset getAsset(int index) {
        return assets.get(index);
    }

    public Constraint getConstraint(int index) {
        return constraints.get(index);
    }

    public ArrayList<Permission> getAllPermission() {
        return permissions;
    }

    public ArrayList<Prohibition> getAllProhibition() {
        return prohibitions;
    }

    public ArrayList<Duty> getAllDuties() {
        return duties;
    }

    public ArrayList<Duty> getAllObligation() { return obligations; }

    public ArrayList<Action> getAllAction() {
        return actions;
    }

    public ArrayList<Asset> getAllAsset() {
        return assets;
    }

    public ArrayList<Constraint> getAllConstraint() {
        return constraints;
    }

    // Statistics for Policy
    public int getNumberOfPermissions() {
        return permissions.size();
    }

    public int getNumberOfProhibitions() {
        return prohibitions.size();
    }

    public int getNumberOfObligations() {
        return obligations.size();
    }

    public int getNumberOfConstraints() {
        return constraints.size();
    }

    public int getNumberOfParties() {
        return parties.size();
    }

    public int getNumberOfDuties() {
        return duties.size();
    }

    /*
    public void buildLogicalConstraint() {
        logicalConstraint = new ArrayList<String>();
        logicalConstraint.add("xone");
        logicalConstraint.add("or");
        logicalConstraint.add("and");
        logicalConstraint.add("andSequence");
    }
  */


}
