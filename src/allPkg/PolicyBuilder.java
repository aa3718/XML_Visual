package allPkg;

import java.util.ArrayList;
import java.util.List;

public class PolicyBuilder {

    public List<Permission> permissions = new ArrayList<Permission>();
    public List<Prohibition> prohibitions = new ArrayList<Prohibition>();
    public List<Action> actions = new ArrayList<Action>();
    public List<Asset> assets = new ArrayList<Asset>();
    public List<Constraint> constraints = new ArrayList<Constraint>();

    public Policy build() {
        Policy policy = new Policy(permissions);
        policy.addProhibition(prohibitions);
        policy.addAction(actions);
        policy.addAsset(assets);
        policy.addConstraint(constraints);
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

    public PolicyBuilder withConstraint(Constraint constraint) {
        constraints.add(constraint);
        return this;
    }

}
