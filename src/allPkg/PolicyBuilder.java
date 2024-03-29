package allPkg;

import java.util.ArrayList;

public class PolicyBuilder {

    public ArrayList<Permission> permissions = new ArrayList<Permission>();
    public ArrayList<Prohibition> prohibitions = new ArrayList<Prohibition>();
    public ArrayList<Duty> obligations = new ArrayList<Duty>();
    public ArrayList<Action> actions = new ArrayList<Action>();
    public ArrayList<Asset> assets = new ArrayList<Asset>();
    public ArrayList<Constraint> constraints = new ArrayList<Constraint>();
    public ArrayList<Duty> duties = new ArrayList<Duty>();
    public ArrayList<Party> parties = new ArrayList<Party>();
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private String uid;
    private String type;
    private String conflict;
    private String inheritFrom;
    private String profile;

    public PolicyBuilder() {
        buildAttributeList();
    }

    public Policy build() {
        Policy policy = new Policy(permissions);
        policy.addProhibition(prohibitions);
        policy.addObligation(obligations);
        policy.addAction(actions);
        policy.addAsset(assets);
        policy.addConstraint(constraints);
        policy.addDuty(duties);
        policy.addParty(parties);
        policy.setUID(uid);
        policy.setConflict(conflict);
        policy.setInheritFrom(inheritFrom);
        policy.setType(type);
        policy.setProfile(profile);
        return policy;
    }

    public PolicyBuilder withPermission(Permission permission) {
        permissions.add(permission);
        return this;
    }

    public PolicyBuilder withProhibition(Prohibition prohibition) {
        prohibitions.add(prohibition);
        return this;
    }

    public PolicyBuilder withObligation(Duty obligation) {
        obligations.add(obligation);
        return this;
    }

    public PolicyBuilder withAction(Action action) {
        actions.add(action);
        return this;
    }

    public PolicyBuilder withAsset(Asset asset) {
        assets.add(asset);
        return this;
    }

    public PolicyBuilder withParty(Party party) {
        parties.add(party);
        return this;
    }

    public PolicyBuilder withConstraint(Constraint constraint) {
        constraints.add(constraint);
        return this;
    }

    public PolicyBuilder withDuty(Duty duty) {
        duties.add(duty);
        return this;
    }

    public Action findAction(String id) {
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getID().equals(id)) {
                return actions.get(i);
            }
        }
        return null;
    }

    public Asset findAsset(String id) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).getID().equals(id)) {
                return assets.get(i);
            }
        }
        return null;
    }

    public Constraint findConstraint(String id) {
        for (int i = 0; i < constraints.size(); i++) {
            if (constraints.get(i).getID().equals(id)) {
                return constraints.get(i);
            }
        }
        return null;
    }

    public Duty findDuty(String uid) {
        /*
        for (int i = 0; i < duties.size(); i++) {
            if (duties.get(i).getID().equals(uid)) {
                return duties.get(i);
            }
        }
        */
        for (int i = 0; i < duties.size(); i++) {
            if (duties.get(i).getUID().equals(uid)) {
                System.out.println("Found in duty!! L)");
                return duties.get(i);
            }
        }
        return null;
    }

    public Party findParty(String id) {
        for (int i = 0; i < parties.size(); i++) {
            if (parties.get(i).getID().equals(id)) {
                return parties.get(i);
            }
        }
        return null;
    }

    public void buildAttributeList() {
        attributeNameList.add("uid");
        attributeNameList.add("conflict");
        attributeNameList.add("type");
        attributeNameList.add("profile");
        attributeNameList.add("inheritFrom");
    }

    public ArrayList<String> getAttributeNameList() { return this.attributeNameList; }

    public PolicyBuilder setPolicyAttributes(String attribute, String value) {
        if (attribute.equals("uid")){
            this.uid = value;
            return this;
        }

        if (attribute.equals("type")){
            this.type = value;
            return this;
        }

        if (attribute.equals("conflict")){
            this.conflict = value;
            return this;
        }

        if (attribute.equals("inheritFrom")){
            this.inheritFrom = value;
            return this;
        }

        if (attribute.equals("profile")){
            this.profile = value;
            return this;
        }
        return this;
    }


}
