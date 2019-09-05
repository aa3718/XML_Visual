package allPkg;

import java.awt.*;

public class bubbleMapLinesBuilder {;

        private Policy policy;
        private boolean useIcon = true;
        private boolean bubbleWithLines = true;
        private Color colorPermission = Color.pink;
        private Color colorProhibition = Color.red.brighter();
        private Color colorConstraint = Color.blue.brighter();
        private Color colorDuty = Color.yellow.brighter();
        private Color colorObligation = Color.orange.brighter();
        private int sizeLines = 2;
        private int width = 35;
        private int height = 35;
        private int sizeInBetweenLines = 2;

        public bubbleMapLines build() {
            bubbleMapLines bubbleMap = new bubbleMapLines(policy);
            bubbleMap.addUseIcon(useIcon);
            bubbleMap.addColorPermission(colorPermission);
            bubbleMap.addColorProhibition(colorProhibition);
            bubbleMap.addColorObligation(colorObligation);
            bubbleMap.addColorDuty(colorDuty);
            bubbleMap.addColorConstraint(colorConstraint);
            bubbleMap.addLineSize(sizeLines);
            bubbleMap.addWidthHeight(width,height);
            bubbleMap.addSizeBetweenLines(sizeInBetweenLines);
            bubbleMap.addBubbleWithLines(bubbleWithLines);
            return bubbleMap;
        }

        public bubbleMapLinesBuilder setPolicy(Policy policy) {
            this.policy = policy;
            return this;
        }

        public bubbleMapLinesBuilder setColorPermission(Color color) {
            this.colorPermission = color;
            return this;
        }

        public bubbleMapLinesBuilder setColorProhibition(Color color) {
            this.colorProhibition = color;
            return this;
        }

        public bubbleMapLinesBuilder setWidthHeight(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public bubbleMapLinesBuilder setColorObligation(Color color) {
            this.colorObligation = color;
            return this;
        }

        public bubbleMapLinesBuilder setColorDuty(Color color) {
            this.colorDuty = color;
            return this;
        }

        public bubbleMapLinesBuilder setColorConstraint(Color color) {
            this.colorConstraint = color;
            return this;
        }

        public bubbleMapLinesBuilder setUseIcon(Boolean useIcon) {
            this.useIcon = useIcon;
            return this;
        }

        public bubbleMapLinesBuilder setBubbleWithLines(Boolean bubbleWithLines) {
            this.bubbleWithLines = bubbleWithLines;
            return this;
        }

        public bubbleMapLinesBuilder setSizeLines(int sizeLines) {
            this.sizeLines = sizeLines;
            return this;
        }

        public bubbleMapLinesBuilder setSizeInBetweenLines(int sizeInBetweenLines) {
            this.sizeInBetweenLines = sizeInBetweenLines;
            return this;
        }

    }
