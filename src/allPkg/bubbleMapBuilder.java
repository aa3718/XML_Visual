package allPkg;

import java.awt.*;

public class bubbleMapBuilder {
    private Policy policy;
    private boolean useIcon = false;
    private Color colorPermission = Color.pink;
    private Color colorProhibition = Color.red.brighter();
    private Color colorConstraint = Color.blue.brighter();
    private Color colorDuty = Color.yellow.brighter();
    private Color colorObligation = Color.orange.brighter();
    private int sizeLines = 2;
    private int width = 35;
    private int height = 35;

    public bubbleMap build() {
        bubbleMap bubbleMap = new bubbleMap(policy);
        bubbleMap.addUseIcon(useIcon);
        bubbleMap.addColorPermission(colorPermission);
        bubbleMap.addColorProhibition(colorProhibition);
        bubbleMap.addColorObligation(colorObligation);
        bubbleMap.addColorDuty(colorDuty);
        bubbleMap.addColorConstraint(colorConstraint);
        bubbleMap.addLineSize(sizeLines);
        bubbleMap.addWidthHeight(width,height);
        return bubbleMap;
    }

    public bubbleMapBuilder setPolicy(Policy policy) {
        this.policy = policy;
        return this;
    }

    public bubbleMapBuilder setColorPermission(Color color) {
        this.colorPermission = color;
        return this;
    }

    public bubbleMapBuilder setColorProhibition(Color color) {
        this.colorProhibition = color;
        return this;
    }

    public bubbleMapBuilder setWidthHeight(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public bubbleMapBuilder setColorObligation(Color color) {
        this.colorObligation = color;
        return this;
    }

    public bubbleMapBuilder setColorDuty(Color color) {
        this.colorDuty = color;
        return this;
    }

    public bubbleMapBuilder setColorConstraint(Color color) {
        this.colorConstraint = color;
        return this;
    }

    public bubbleMapBuilder setUseIcon(Boolean useIcon) {
        this.useIcon = useIcon;
        return this;
    }

    public bubbleMapBuilder setSizeLines(int sizeLines) {
        this.sizeLines = sizeLines;
        return this;
    }


}
