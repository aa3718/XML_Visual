package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Constraint extends attributeHolders {
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private static ArrayList<String> nameLeftOperand = new ArrayList<String>();
    private static ArrayList<String> nameOperators = new ArrayList<String>();
    private String id;
    private String name;
    private String operator;
    private String rightOperand;
    private String dataType;
    private String unit;
    private String status;
    private String leftOperand;
    private String on;

    public void setConstraintOn(String on) { this.on = on; }

    public void setID(String id) {
        this.id = id;
    }

    public String getOn() { return this.on;}

    public void setName(String name) {
        this.name = name;
    }

    public void setOperator(String operator) { this.operator = operator; }

    public void setRightOperand(String rightOperand) {
        this.rightOperand = rightOperand;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setLeftOperand(String leftOperand) { this.leftOperand = leftOperand; }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() { return this.name; }

    public String getID() { return this.id; }

    public String getOperator() { return this.operator; }

    public String getRightOperand() { return this.rightOperand; }

    public  String getLeftOperand() { return this.leftOperand; }

    public void setConstraint(Constraint refinement) {}

    public List<Constraint> getConstraint() { return null; }

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
        attributeNameList.add("leftOperand");
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

        if(attribute == "leftOperand") {
            this.leftOperand = value;
            return;
        }
    }

    public void copyInstance(Constraint constraint) {
        constraint.setOperator(this.operator);
        constraint.setName(this.name);
        constraint.setID(this.id);
        constraint.setDataType(this.dataType);
        constraint.setRightOperand(this.rightOperand);
        constraint.setStatus(this.status);
        constraint.setUnit(this.unit);
        constraint.setLeftOperand(this.leftOperand);
    }

}
