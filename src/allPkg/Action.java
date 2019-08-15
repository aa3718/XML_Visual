package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Action extends attributeHolders {
    //private ArrayList<String> nameAction = new ArrayList<String>();
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private String name;
    private String id;
    private ArrayList<Constraint> refinements = new ArrayList<Constraint>();
    private Rules parentType;

    Action() {
        buildAttributeList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setConstraint(Constraint refinement) { this.refinements.add(refinement); }

    public List<Constraint> getConstraint() {
        return refinements;
    }

    public String getID() { return this.id; }

    public void setParentType(Rules rule) {
        this.parentType = rule;
        if(parentType instanceof Permission){
            System.out.println("It is an instanceOFFF Insinde");
        }

    }

    public Rules getParentType() { return parentType;}

    /*
    public void buildAction() {
        nameAction.add("Attribute");
        nameAction.add("CommercialUse"); //
        nameAction.add("DerivativeWorks");
        nameAction.add("Distribution");
        nameAction.add("Notice");
        nameAction.add("Reproduction");
        nameAction.add("ShareAlike");
        nameAction.add("Sharing");
        nameAction.add("SourceCode");
        nameAction.add("acceptTracking"); //
        nameAction.add("adHocShare");
        nameAction.add("aggregate");
        nameAction.add("annotate"); //
        nameAction.add("anonymize"); //
        nameAction.add("append"); //
        nameAction.add("appendTo"); //
        nameAction.add("archive");
        nameAction.add("attachPolicy");
        nameAction.add("attachSource");
        nameAction.add("attribute");
        nameAction.add("commercialize");
        nameAction.add("compensate"); //
        nameAction.add("concurrentUse");
        nameAction.add("copy"); //
        nameAction.add("delete"); //
        nameAction.add("derive"); //
        nameAction.add("digitize");  //
        nameAction.add("display");
        nameAction.add("distribute");
        nameAction.add("ensureExclusivity");
        nameAction.add("execute");
        nameAction.add("export");
        nameAction.add("extract");
        nameAction.add("extractChar");
        nameAction.add("extractPage");
        nameAction.add("extractWord");
        nameAction.add("give");
        nameAction.add("grantUse");
        nameAction.add("include");
        nameAction.add("index");
        nameAction.add("inform");
        nameAction.add("install");
        nameAction.add("lease");
        nameAction.add("lend"); //
        nameAction.add("license");
        nameAction.add("modify");
        nameAction.add("move");
        nameAction.add("nextPolicy");
        nameAction.add("obtainConsent");
        nameAction.add("pay");
        nameAction.add("play");
        nameAction.add("present");
        nameAction.add("preview");//
        nameAction.add("print");
        nameAction.add("read");
        nameAction.add("reproduce");
        nameAction.add("reviewPolicy");
        nameAction.add("secondaryUse");
        nameAction.add("sell");
        nameAction.add("share");
        nameAction.add("shareAlike");
        nameAction.add("stream");
        nameAction.add("synchronize");
        nameAction.add("textToSpeech");
        nameAction.add("transfer");
        nameAction.add("transform");
        nameAction.add("translate");
        nameAction.add("uninstall");
        nameAction.add("use");
        nameAction.add("watermark");
        nameAction.add("write");
        nameAction.add("writeTo");
    }
    */

    public void buildAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("name");
    }

    @Override
    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if (attribute.equals("id")){
            this.id = value;
            return;
        }

        if (attribute.equals("name")){
            this.name = value;
            return;
        }
    }

    public void copyInstance(Action action) {
        action.setName(this.name);
        action.setID(this.id);
    }

    public String getName() { return this.name; }

}
