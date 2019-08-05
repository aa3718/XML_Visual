package allPkg;

import java.util.ArrayList;

public class Party {

    public static ArrayList<String> nameParty = new ArrayList<String>();
    public String function;
    public String uid;
    public String scope;
    public String id;
    public String idref;


    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setIDREF(String idref) {
        this.idref = idref;
    }

    public void setID(String id) {
        this.id = id;
    }

    public static void buildParty() {
        nameParty.add("assigner");
        nameParty.add("assignee");
        nameParty.add("attributedParty");
        nameParty.add("consentingParting");
        nameParty.add("informedParty");
        nameParty.add("compensatedParty");
        nameParty.add("trackingParty");
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setId(String id) {
        this.id = id;
    }

}
