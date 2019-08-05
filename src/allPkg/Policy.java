package allPkg;

import java.util.List;

public class Policy {

    public List<Permission> permissions;
    public List<Prohibition> prohibitions;
    public List<Action> actions;
    public List<Asset> assets;
    public List<Constraint> constraints;
    public String uid;
    public String type;
    public String conflict;
    public String undefined;
    public Boolean inheritAllowed;
    public String inheritFrom;
    public String inheritRelation;
    public String profile;


    public Policy(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addProhibition(List<Prohibition> prohibitions) {
        this.prohibitions = prohibitions;
    }
    public void addAction(List<Action> actions) { this.actions = actions; }
    public void addAsset(List<Asset> assets) { this.assets = assets; }
    public void addConstraint(List<Constraint> constraints) { this.constraints = constraints; }

    public void setType(String type) {
        int indexLast = type.lastIndexOf("/");
        this.type = type.substring(indexLast);
    }

    public String getType() {
        return this.type;
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

    public Action getAction() {
        return actions.get(0);
    }

    public Asset getAsset() {
        return assets.get(0);
    }

    public Constraint getConstraint() {
        return constraints.get(0);
    }

    public String getProfile() {
        return this.profile;
    }


}
