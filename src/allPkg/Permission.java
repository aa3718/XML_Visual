package allPkg;

public class Permission implements elements {

    private Asset asset;
    private Action act;
    private Constraint constraint;
    private Party party;
    private Duty duty;
    public String name;

    Permission(String name) {
        this.name = name;
        System.out.println(name);
    }

    @Override
    public void getAsset(Asset ass) {
        this.asset = ass;
    }

    @Override
    public void getAction(Action act) {
        this.act = act;
    }

    @Override
    public void getContraint(Constraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public void getParty(Party party) {
        this.party = party;
    }

    @Override
    public void getDuty(Duty duty) {
        this.duty = duty;
    }

}