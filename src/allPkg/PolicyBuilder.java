package allPkg;

import java.util.ArrayList;
import java.util.List;

public class PolicyBuilder {

    public List<Permission> permissions = new ArrayList<Permission>();
    public List<Prohibition> prohibitions = new ArrayList<Prohibition>();

    //private PolicyBuilder() {};

    //public static PolicyBuilder aPolicy() {
       // return new PolicyBuilder();
    //}

    public Policy build() {
        Policy policy = new Policy(permissions);
        policy.addProhibition(prohibitions);
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

}
