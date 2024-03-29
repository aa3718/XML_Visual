package allPkg;

import java.util.ArrayList;
import java.util.List;

public class Constraint extends attributeHolders {
    private ArrayList<String> attributeNameList = new ArrayList<String>();
    private ArrayList<String> nameLeftOperand = new ArrayList<String>();
    private ArrayList<String> nameOperators = new ArrayList<String>();
    private String id;
    private String name;
    private String operator;
    private String rightOperand;
    private String dataType;
    private String unit;
    private String status;
    private String leftOperand;
    private String on;
    private String fullId;
    private String fullName;
    private String fullOperator;
    private String fullRightOperand;
    private String fullDataType;
    private String fullUnit;
    private String fullStatus;
    private boolean isLogicalConstraint;
    private ArrayList<Constraint> attachedConstraint = new ArrayList<Constraint>();
    private String optionalLogicalOperand;
    private Rules parentType;

    public Constraint() {
        buildAttributeList();
    }

    public void setConstraintOn(String on) { this.on = on; }

    public void setID(String id) {
        this.id = id;
    }

    public void setOptionalLogicalOperand(String optionalLogicalOperand) { this.optionalLogicalOperand = optionalLogicalOperand; }

    public String getOptionalLogicalOperand() { return optionalLogicalOperand; }

    public String getOn() { return this.on;}

    public void setAttachedConstraint(Constraint constraint) { this.attachedConstraint.add(constraint); }

    public ArrayList<Constraint> getAttachedConstraint() {return attachedConstraint; }

    public void setLogicalConstraint(boolean isLogicalConstraint) { this.isLogicalConstraint = isLogicalConstraint; }

    public boolean getIsLogicalConstraint() { return this.isLogicalConstraint; }

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

    public  String getFullName() { return this.fullName; }

    public  String getFullID() { return this.fullId; }

    public  String getFullOperator() { return this.fullOperator; }

    public  String getFullRightOperand() { return this.fullRightOperand; }

    public  String getFullUnit() { return this.fullUnit; }

    public  String getFullDataType() { return this.fullDataType; }

    public  String getFullStatus() { return this.fullStatus; }

    public void setFullID(String id) {
        this.fullId = id;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public void setFullOperator(String operator) { this.fullOperator = operator; }

    public void setFullRightOperand(String rightOperand) {
        this.fullRightOperand = rightOperand;
    }

    public void setFullDataType(String dataType) {
        this.fullDataType = dataType;
    }

    public void setFullUnit(String unit) {
        this.fullUnit = unit;
    }

    public void setFullStatus(String fullStatus) {
        this.fullStatus = fullStatus;
    }

    public void setParentType(Rules rule) { this.parentType = rule; }

    public Rules getParentType() { return parentType;}

    public void setConstraint(Constraint refinement) {}

    public List<Constraint> getConstraint() { return null; }

    public void buildOperators() {
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
/*
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
*/

    public void buildAttributeList() {
        attributeNameList.add("id");
        attributeNameList.add("name");
        attributeNameList.add("operator");
        attributeNameList.add("dataType");
        attributeNameList.add("unit");
        attributeNameList.add("status");
        attributeNameList.add("rightOperand");
        attributeNameList.add("leftOperand");
    }

    @Override
    public ArrayList<String> getAttributeList() {
        return this.attributeNameList;
    }

    @Override
    void setAttribute(String attribute, String value) {
        if(attribute.equals("id")) {
            this.id = value;
            return;
        }

        if(attribute.equals("name")) {
            this.name = value;
            return;
        }

        if(attribute.equals("operator")) {
            this.operator = value;
            return;
        }

        if(attribute.equals("dataType")) {
            this.dataType = value;
            return;
        }

        if(attribute.equals("unit")) {
            this.unit = value;
            return;
        }

        if(attribute.equals("status")) {
            this.status = value;
            return;
        }

        if(attribute.equals("rightOperand")) {
            this.rightOperand = value;
            return;
        }

        if(attribute.equals("leftOperand")) {
            this.leftOperand = value;
            return;
        }
    }

    void setFullAttribute(String attribute, String value) {
        if(attribute.equals("id")) {
            this.fullId = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("id",subString);
            } else {
                setAttribute("id",value);
            }
            return;
        }

        if(attribute.equals("name")) {
            this.fullName = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("name",subString);
            } else {
                setAttribute("name",value);
            }
            return;
        }

        if(attribute.equals("operator")) {
            this.fullOperator = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("operator",subString);
            } else {
                setAttribute("operator",value);
            }
            return;
        }

        if(attribute.equals("dataType")) {
            this.fullDataType = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("dataType",subString);
            } else {
                setAttribute("dataType",value);
            }
            return;
        }

        if(attribute.equals("unit")) {
            this.fullUnit = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("unit",subString);
            } else {
                setAttribute("unit",value);
            }
            return;
        }

        if(attribute.equals("status")) {
            this.fullStatus = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("status",subString);
            } else {
                setAttribute("status",value);
            }
            return;
        }

        if(attribute.equals("rightOperand")) {
            this.fullRightOperand = value;
            if (value.lastIndexOf("/") > 0) {
                String subString = value.substring(value.lastIndexOf("/") + 1);
                setAttribute("rightOperand",subString);
            } else {
                setAttribute("rightOperand",value);
            }
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
        constraint.setLogicalConstraint(this.isLogicalConstraint);
        constraint.setFullName(this.fullName);
        constraint.setFullOperator(this.fullOperator);
        constraint.setFullID(this.fullId);
        constraint.setFullDataType(this.fullDataType);
        constraint.setFullRightOperand(fullRightOperand);
        constraint.setFullStatus(this.fullStatus);
        constraint.setFullUnit(this.fullUnit);

    }

}
