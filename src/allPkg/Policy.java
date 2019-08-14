package allPkg;

import java.util.List;

public class Policy {

    public List<Permission> permissions;
    private List<Prohibition> prohibitions;
    private List<Action> actions;
    private List<Asset> assets;
    private List<Constraint> constraints;
    private List<Duty> duties;
    private List<Party> parties;
    private List<Duty> obligations;

    private String uid;
    private String type;
    private String conflict;
    private String inheritFrom;
    private String profile;

    public Policy(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addProhibition(List<Prohibition> prohibitions) {
        this.prohibitions = prohibitions;
    }

    public void addAction(List<Action> actions) {
        this.actions = actions;
    }

    public void addObligation(List<Duty> obligations) { this.obligations = obligations; }

    public void addAsset(List<Asset> assets) {
        this.assets = assets;
    }

    public void addConstraint(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    public void addDuty(List<Duty> duties) {
        this.duties = duties;
    }

    public void addParty(List<Party> parties) {
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

    public List<Permission> getAllPermission() {
        return permissions;
    }

    public List<Prohibition> getAllProhibition() {
        return prohibitions;
    }

    public List<Duty> getAllObligation() { return obligations; }

    public List<Action> getAllAction() {
        return actions;
    }

    public List<Asset> getAllAsset() {
        return assets;
    }

    public List<Constraint> getAllConstraint() {
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
