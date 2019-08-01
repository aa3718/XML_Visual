package allPkg;

import java.util.List;

public class Policy {

    public List<Permission> permissions;
    public List<Prohibition> prohibitions;
    public String uid;
    public String type;
    public Boolean inheritAllowed;
    public String inheritFrom;
    public String inheritRelation;
    public String profile;

    public Policy(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addProhibition(List<Prohibition> prohibitions) {
        this.prohibitions = prohibitions;
    }

}
