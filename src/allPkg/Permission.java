package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Permission implements Rules {

    private List<Asset> assets = new ArrayList<Asset>();
    private List<Action> actions = new ArrayList<Action>();
    private List<Constraint> constraints = new ArrayList<Constraint>();
    private List<Duty> duties = new ArrayList<Duty>();
    private List<Party> parties = new ArrayList<Party>();

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

    public void setDuty(Duty duty) {
        duties.add(duty);
    }

    public void setEntireConstraint(List<Constraint> constraints) { this.constraints = constraints; }

    public void setEntireParty(List<Party> parties) { this.parties = parties; }

    public void setEntireDuty(List<Duty> duties) { this.duties = duties; }

    public void setEntireAsset(List<Asset> assets) { this.assets = assets; }

    public void setEntireAction(List<Action> actions) { this.actions = actions; }

    public List<Asset> getAsset() {
        return assets;
    }

    public List<Action> getAction() { return actions; }

    public List<Constraint> getConstraint() {
        return constraints;
    }

    public List<Duty> getDuty() {
        return duties;
    }

    public List<Party> getParty() { return parties; }

}