package allPkg;

import java.util.ArrayList;
import java.util.List;

public class PolicyBuilder {

    public ArrayList<Permission> permissions = new ArrayList<Permission>();
    public ArrayList<Prohibition> prohibitions = new ArrayList<Prohibition>();
    public ArrayList<Action> actions = new ArrayList<Action>();
    public ArrayList<Asset> assets = new ArrayList<Asset>();
    public ArrayList<Constraint> constraints = new ArrayList<Constraint>();
    public ArrayList<Duty> duties = new ArrayList<Duty>();
    public ArrayList<Party> parties = new ArrayList<Party>();

    public Policy build() {
        Policy policy = new Policy(permissions);
        policy.addProhibition(prohibitions);
        policy.addAction(actions);
        policy.addAsset(assets);
        policy.addConstraint(constraints);
        policy.addDuty(duties);
        policy.addParty(parties);
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
            if (actions.get(i).id.equals(id)) {
                return actions.get(i);
            }
        }
        return null;
    }

    public Asset findAsset(String id) {
        for (int i = 0; i < assets.size(); i++) {
            if (assets.get(i).id.equals(id)) {
                return assets.get(i);
            }
        }
        return null;
    }

    public Constraint findConstraint(String id) {
        for (int i = 0; i < constraints.size(); i++) {
            if (constraints.get(i).id.equals(id)) {
                return constraints.get(i);
            }
        }
        return null;
    }

    public Duty findDuty(String uid) {
        System.out.println("gets here!! ");
        /*
        for (int i = 0; i < duties.size(); i++) {
            if (duties.get(i).id.equals(uid)) {
                return duties.get(i);
            }
        }
        */
        for (int i = 0; i < duties.size(); i++) {
            if (duties.get(i).uid.equals(uid)) {
                System.out.println("Found in duty!! L)");
                return duties.get(i);
            }
        }
        return null;
    }

    public Party findParty(String id) {
        for (int i = 0; i < parties.size(); i++) {
            if (parties.get(i).id.equals(id)) {
                return parties.get(i);
            }
        }
        return null;
    }
}
