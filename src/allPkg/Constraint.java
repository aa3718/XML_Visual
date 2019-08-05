package allPkg;

import java.util.ArrayList;

public class Constraint extends attributeHolders{
    public ArrayList<String> attributeNameList = new ArrayList<String>();
    public static ArrayList<String> nameLeftOperand = new ArrayList<String>();
    public static ArrayList<String> nameOperators = new ArrayList<String>();
    public String id;
    public String name;
    public String operator;
    public String rightOperand;
    public String dataType;
    public String unit;
    public String status;

    public static void buildOperators() {
        nameOperators.add("eq");
        nameOperators.add("gt");
        nameOperators.add("gteq");
        nameOperators.add("hasPart");
        nameOperators.add("isA");
        nameOperators.add("isAllOf");
        nameOperators.add("isAnyOf");
        nameOperators.add("isNoneOf");
        nameOperators.add("isPartOf");
        nameOperators.add("It");
        nameOperators.add("Iteq");
        nameOperators.add("neq");

    }


    public static void buildLeftOperand() {
        nameLeftOperand.add("absolutePosition");
        nameLeftOperand.add("absoluteSize");
        nameLeftOperand.add("absoluteSpatialPosition");
        nameLeftOperand.add("absoluteTemporalPosition");
        nameLeftOperand.add("count");
        nameLeftOperand.add("dateTime");
        nameLeftOperand.add("delayPeriod");
        nameLeftOperand.add("deliveryChannel");
        nameLeftOperand.add("device");
        nameLeftOperand.add("elapsedTime");
        nameLeftOperand.add("event");
        nameLeftOperand.add("fileFormat");
        nameLeftOperand.add("industry");
        nameLeftOperand.add("language");
        nameLeftOperand.add("media");
        nameLeftOperand.add("meteredTime");
        nameLeftOperand.add("payAmount");
        nameLeftOperand.add("percentage");
        nameLeftOperand.add("product");
        nameLeftOperand.add("purpose");
        nameLeftOperand.add("recipient");
        nameLeftOperand.add("relativePosition");
        nameLeftOperand.add("relativeSize");
        nameLeftOperand.add("relativeSpatialPosition");
        nameLeftOperand.add("relativeTemporalPosition");
        nameLeftOperand.add("resolution");
        nameLeftOperand.add("spatial");
        nameLeftOperand.add("spatialCoordinates");
        nameLeftOperand.add("system");
        nameLeftOperand.add("systemDevice");
        nameLeftOperand.add("timeInternal");
        nameLeftOperand.add("unitOfCount");
        nameLeftOperand.add("version");
        nameLeftOperand.add("virtualLocation");

    }

    @Override
    public ArrayList<String> getAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("name");
        attributeNameList.add("operator");
        attributeNameList.add("dataType");
        attributeNameList.add("unit");
        attributeNameList.add("status");
        attributeNameList.add("rightOperand");
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if(attribute == "id") {
            this.id = value;
            return;
        }

        if(attribute == "name") {
            this.name = value;
            return;
        }

        if(attribute == "operator") {
            this.operator = value;
            return;
        }

        if(attribute == "dataType") {
            this.dataType = value;
            return;
        }

        if(attribute == "unit") {
            this.unit = value;
            return;
        }

        if(attribute == "status") {
            this.status = value;
            return;
        }

        if(attribute == "rightOperand") {
            this.rightOperand = value;
            return;
        }
    }

}
