package allPkg;

import java.awt.*;

public class bubbleMapBuilder {
    private Policy policy;
    private boolean useIcon = false;
    private Color colorPermission = Color.black;
    private Color colorProhibition = Color.black;
    private Color colorConstraint = Color.black;
    private Color colorDuty = Color.black;

    public bubbleMap build() {
        bubbleMap bubbleMap = new bubbleMap(policy);
        bubbleMap.addUseIcon(useIcon);
        bubbleMap.addColorPermission(colorPermission);
        bubbleMap.addColorProhibition(colorProhibition);
        bubbleMap.addColorDuty(colorDuty);
        bubbleMap.addColorConstraint(colorConstraint);
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


}
