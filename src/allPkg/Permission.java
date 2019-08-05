package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Permission extends Rules {

    private Asset asset;
    private Action action;
    private Constraint constraint;
    private Party party;
    private List<Duty> duties = new ArrayList<Duty>();
    private Duty duty;

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setContraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public void setDuty(Duty duty) {
        duties.add(duty);
    }

    public Asset getAsset() {
        return asset;
    }

    public Action getAction() {
        return action;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public Duty getDuty(int index) {
        return duties.get(index);
    }

}