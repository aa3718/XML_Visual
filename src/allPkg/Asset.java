package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Asset extends attributeHolders {
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private String relation;
    private String uid;
    private String id;
    private String fullRelation;
    private String fullUid;
    private String fullId;
    private ArrayList<Constraint> refinements = new ArrayList<Constraint>();

    Asset() {
        buildAttributeList();
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getUID() { return this.uid; }

    public String getID() { return this.id; }

    public void setConstraint(Constraint refinement) { this.refinements.add(refinement); }

    public void setFullConstraint(ArrayList<Constraint> refinements) { this.refinements = refinements; }

    public List<Constraint> getConstraint() {
        return refinements;
    }

    public String getRelation() {
        return relation;
    }

    public String getFullRelation() {
        return fullRelation;
    }

    public String getFullID() {
        return fullId;
    }

    public String getFullUid() {
        return fullUid;
    }

    public void setFullID(String fullId) {
        this.fullId = fullId;
    }

    public void setFullRelation(String fullRelation) {
        this.fullRelation = fullRelation;
    }

    public void setFullUid(String fullUid) {
        this.fullUid = fullUid;
    }

    public void buildAttributeList() {
        attributeNameList.add("relation");
        attributeNameList.add("uid");
        attributeNameList.add("id");
    }

    @Override
    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if (attribute.equals("relation")){
            this.relation = value;
            return;
        }

        if (attribute.equals("uid")){
            this.uid = value;
            return;
        }

        if (attribute.equals("id")){
            this.id = value;
            return;
        }

    }

    void setFullAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.fullId = value;
            return;
        }

        if (attribute.equals("relation")){
            this.fullRelation = value;
            return;
        }

        if (attribute.equals("uid")){
            this.fullUid = value;
            return;
        }
    }

    public void copyInstance(Asset asset) {
        asset.setRelation(this.relation);
        asset.setUID(this.uid);
        asset.setID(this.id);
        asset.setFullConstraint(this.refinements);
        asset.setFullUid(this.fullUid);
        asset.setFullRelation(this.fullRelation);
        asset.setFullID(this.fullId);
    }

}
