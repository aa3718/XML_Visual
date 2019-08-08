package allPkg;

import java.awt.*;

public class geometry2Builder {
    private Policy policy;
    private int numberElementPerLine = 4;
    private int prefferedRuleBoxSizeW = 200;
    private int spaceBetweenAttributes = 40;
    private int spaceBetweenAttrIconAndAttrStringsX = 35;
    private int spaceAtBottomOfBoxes = 15;
    private int sizeOfInnerBoxesW = prefferedRuleBoxSizeW - 25;
    private int widenessOfConstraintLines = 2;
    private int operatorSpaceFromLeft = 80;
    private int arcWDuty = 40;
    private int arcHDuty = 40;
    private int spaceBetweenRulesAndDuty = 15;
    private int spaceBetweenDutyAndDuty = 25;
    private int bottomPadding = 15;

    private Color mainBoxColor = Color.black;
    private Color colorPermission = Color.black;
    private Color colorProhibition = Color.black;
    private Color colorConstraint = Color.black;
    private Color colorDuty = Color.black;

    private boolean makeDutyRounded;
    private boolean makeDutyHex;

    public geometry2 build() {
        geometry2 geometry2 = new geometry2(policy);
        geometry2.addThePrefferedRuleBoxSizeW(prefferedRuleBoxSizeW);
        geometry2.addNumberElementPerLine(numberElementPerLine);
        geometry2.addSpaceBetweenAttributes(spaceBetweenAttributes);
        geometry2.addSpaceBetweenAttrIconAndAttrStringsX(spaceBetweenAttrIconAndAttrStringsX);
        geometry2.addSpaceAtBottomOfBoxes(spaceAtBottomOfBoxes);
        geometry2.addSizeOfInnerBoxesW(sizeOfInnerBoxesW);
        geometry2.addWidenessOfConstraintLines(widenessOfConstraintLines);
        geometry2.addOperatorSpaceFromLeft(operatorSpaceFromLeft);
        geometry2.addArcWDuty(arcWDuty);
        geometry2.addArcHDuty(arcHDuty);
        geometry2.addSpaceBetweenRulesAndDuty(spaceBetweenDutyAndDuty);
        geometry2.addSpaceBetweenDutyAndDuty(spaceBetweenDutyAndDuty);
        geometry2.addBottomPadding(bottomPadding);
        geometry2.addColorPermission(colorPermission);
        geometry2.addColorProhibition(colorProhibition);
        geometry2.addColorDuty(colorDuty);
        geometry2.addColorConstraint(colorConstraint);
        geometry2.addDutyRounded(makeDutyRounded);
        geometry2.addColorMainBox(mainBoxColor);
        return geometry2;
    }

    public geometry2Builder setPolicy(Policy policy) {
        this.policy = policy;
        return this;
    }

    public geometry2Builder setNumberElementPerLine(int number) {
        this.numberElementPerLine = number;
        return this;
    }

    public geometry2Builder setSpaceBetweenAttributes(int number) {
        this.spaceBetweenAttributes = number;
        return this;
    }

    public geometry2Builder setSpaceBetweenAttrIconAndAttrStringsX(int number) {
        this.spaceBetweenAttrIconAndAttrStringsX = number;
        return this;
    }

    public geometry2Builder setSpaceAtBottomOfBoxes(int number) {
        this.spaceAtBottomOfBoxes = number;
        return this;
    }

    public geometry2Builder setSizeOfInnerBoxesW(int number) {
        this.sizeOfInnerBoxesW = number;
        return this;
    }

    public geometry2Builder setWidenessOfConstraintLines(int number) {
        this.widenessOfConstraintLines = number;
        return this;
    }

    public geometry2Builder setOperatorSpaceFromLeft(int number) {
        this.operatorSpaceFromLeft = number;
        return this;
    }

    public geometry2Builder setArcWDuty(int number) {
        this.arcWDuty = number;
        return this;
    }

    public geometry2Builder setArcHDuty(int number) {
        this.arcHDuty = number;
        return this;
    }

    public geometry2Builder setSpaceBetweenRulesAndDuty(int number) {
        this.spaceBetweenRulesAndDuty = number;
        return this;
    }

    public geometry2Builder setSpaceBetweenDutyAndDuty(int number) {
        this.spaceBetweenDutyAndDuty = number;
        return this;
    }

    public geometry2Builder setBottomPadding(int number) {
        this.bottomPadding = number;
        return this;
    }

    public geometry2Builder setColorMainBox(Color color) {
        this.mainBoxColor = color;
        return this;
    }

    public geometry2Builder setColorPermission(Color color) {
        this.colorPermission = color;
        return this;
    }

    public geometry2Builder setColorProhibition(Color color) {
        this.colorProhibition = color;
        return this;
    }

    public geometry2Builder setColorDuty(Color color) {
        this.colorDuty = color;
        return this;
    }

    public geometry2Builder setColorConstraint(Color color) {
        this.colorConstraint = color;
        return this;
    }

    public geometry2Builder makeDutyRounded(boolean bool) {
        this.makeDutyRounded = bool;
        return this;
    }

}
