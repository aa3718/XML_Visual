package allPkg;

public class Duty extends Rules {
    private Asset asset;
    private Action action;
    private Constraint constraint;

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setContraint(Constraint constraint) {
        this.constraint = constraint;
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

    public void setDuty(Duty duty) {}

}
