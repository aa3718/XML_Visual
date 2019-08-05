package allPkg;

public class Permission {

    private Asset asset;
    private Action action;
    private Constraint constraint;
    private Party party;
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
        this.duty = duty;
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

}