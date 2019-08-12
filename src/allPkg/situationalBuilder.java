package allPkg;

import java.awt.*;

public class situationalBuilder {
    private Policy policy;
    private Color colorPermission = Color.black;
    private Color colorProhibition = Color.black;
    private Color colorConstraint = Color.black;
    private Color colorDuty = Color.black;

    private int numberElementPerLine = 3;


    public situational build() {
        situational situational = new situational(policy);
        situational.addColorPermission(colorPermission);
        situational.addColorProhibition(colorProhibition);
        situational.addColorDuty(colorDuty);
        situational.addColorConstraint(colorConstraint);
        situational.addNumberElementPerLine(numberElementPerLine);
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

    public situationalBuilder setColorDuty(Color color) {
        this.colorDuty = color;
        return this;
    }

    public situationalBuilder setColorConstraint(Color color) {
        this.colorConstraint = color;
        return this;
    }

    public situationalBuilder setNumberElementPerLine(int numberElementPerLine) {
        this.numberElementPerLine = numberElementPerLine;
        return this;
    }

}

