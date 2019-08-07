package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Prohibition implements Rules {
    private Asset asset;
    private Action action;
    private List<Constraint> constraints = new ArrayList<Constraint>();
    private List<Party> parties = new ArrayList<Party>();
    private List<Duty> duties = new ArrayList<Duty>();;

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setAction(Action action) {
        this.action = action;
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
        return duties;
    }

    public List<Party> getParty() { return parties; }
}
