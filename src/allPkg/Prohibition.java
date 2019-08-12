package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Prohibition implements Rules {

    private List<Asset> assets = new ArrayList<Asset>();
    private List<Action> actions = new ArrayList<Action>();
    private List<Constraint> constraints = new ArrayList<Constraint>();
    private List<Party> parties = new ArrayList<Party>();
    private List<Duty> remedies = new ArrayList<Duty>();;

    public void setAction(Action action) {
        actions.add(action);
    }

    public void setAsset(Asset asset) {
        assets.add(asset);
    }

    public void setConstraint(Constraint constraint) { constraints.add(constraint); }

    public void setParty(Party party) {
        parties.add(party);
    }

    public void setDuty(Duty remedy) {
        remedies.add(remedy);
    }

    public void setEntireConstraint(List<Constraint> constraints) { this.constraints = constraints; }

    public void setEntireParty(List<Party> parties) { this.parties = parties; }

    public void setEntireDuty(List<Duty> remedies) { this.remedies = remedies; }

    public void setEntireAsset(List<Asset> assets) { this.assets = assets; }

    public void setEntireAction(List<Action> actions) { this.actions = actions; }

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
        return remedies;
    }

    public List<Party> getParty() { return parties; }
}

//private Asset asset;
//private Action action;