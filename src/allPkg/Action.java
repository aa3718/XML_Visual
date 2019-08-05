package allPkg;

import java.util.ArrayList;

public class Action extends attributeHolders {

    public static ArrayList<String> nameAction = new ArrayList<String>();
    public ArrayList<String> attributeNameList = new ArrayList<String>();
    public String name;
    public String id;


    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

    public static void buildAction() {
        nameAction.add("Attribute");
        nameAction.add("CommercialUse");
        nameAction.add("DerivativeWorks");
        nameAction.add("Distribution");
        nameAction.add("Notice");
        nameAction.add("Reproduction");
        nameAction.add("ShareAlike");
        nameAction.add("Sharing");
        nameAction.add("SourceCode");
        nameAction.add("acceptTracking");
        nameAction.add("adHocShare");
        nameAction.add("aggregate");
        nameAction.add("annotate");
        nameAction.add("anonymize");
        nameAction.add("append");
        nameAction.add("appendTo");
        nameAction.add("archive");
        nameAction.add("attachPolicy");
        nameAction.add("attachSource");
        nameAction.add("attribute");
        nameAction.add("commercialize");
        nameAction.add("compensate");
        nameAction.add("concurrentUse");
        nameAction.add("copy");
        nameAction.add("delete");
        nameAction.add("derive");
        nameAction.add("digitize");
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
        nameAction.add("lend");
        nameAction.add("license");
        nameAction.add("modify");
        nameAction.add("move");
        nameAction.add("nextPolicy");
        nameAction.add("obtainConsent");
        nameAction.add("pay");
        nameAction.add("play");
        nameAction.add("present");
        nameAction.add("preview");
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

    @Override
    public ArrayList<String> getAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("name");
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if (attribute == "id"){
            this.id = value;
            return;
        }

        if (attribute == "name"){
            this.name = value;
            return;
        }
    }

}
