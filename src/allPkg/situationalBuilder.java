package allPkg;

import java.awt.*;

public class situationalBuilder {
    private Policy policy;
    private Color colorPermission = Color.black;
    private Color colorProhibition = Color.black;
    private Color colorConstraint = Color.black;
    private Color colorDuty = Color.black;
    private  Color colorObligation = Color.black;
    private boolean useIcons = false;
    private int lineThickness = 2;
    private int numberElementPerLine = 3;


    public situational build() {
        situational situational = new situational(policy);
        situational.addColorPermission(colorPermission);
        situational.addColorProhibition(colorProhibition);
        situational.addColorObligation(colorObligation);
        situational.addColorDuty(colorDuty);
        situational.addColorConstraint(colorConstraint);
        situational.addNumberElementPerLine(numberElementPerLine);
        situational.addUseIcons(useIcons);
        situational.addLineThickness(lineThickness);
        return situational;
    }

    public situationalBuilder setPolicy(Policy policy) {
        this.policy = policy;
        return this;
    }

    public situationalBuilder setColorPermission(Color color) {
        this.colorPermission = color;
        return this;
    }

    public situationalBuilder setColorProhibition(Color color) {
        this.colorProhibition = color;
        return this;
    }

    public situationalBuilder setColorObligation(Color color) {
        this.colorObligation = color;
        return this;
    }

    public situationalBuilder setColorDuty(Color color) {
        this.colorDuty = color;
        return this;
    }

    public situationalBuilder setColorConstraint(Color color) {
        this.colorConstraint = color;
        return this;
    }

    public situationalBuilder setUseIcons(Boolean useIcons) {
        this.useIcons = useIcons;
        return this;
    }

    public situationalBuilder setLineThickness(int lineThickness) {
        this.lineThickness = lineThickness;
        return this;
    }

    public situationalBuilder setNumberElementPerLine(int numberElementPerLine) {
        this.numberElementPerLine = numberElementPerLine;
        return this;
    }

}

