package allPkg;

import java.util.ArrayList;

public class Asset extends attributeHolders{
    public ArrayList<String> attributeNameList = new ArrayList<String>();
    public String relation;
    public String uid;
    public String id;

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setID(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<String> getAttributeList() {
        attributeNameList.add("relation");
        attributeNameList.add("uid");
        attributeNameList.add("id");
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if (attribute == "relation"){
            this.relation = value;
            return;
        }

        if (attribute == "uid"){
            this.uid = value;
            return;
        }

        if (attribute == "id"){
            this.id = value;
            return;
        }

    }
}
