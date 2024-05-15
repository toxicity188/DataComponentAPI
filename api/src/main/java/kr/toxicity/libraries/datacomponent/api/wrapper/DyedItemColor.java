package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

public record DyedItemColor(int rgb, boolean showInTooltip) implements ComponentData<DyedItemColor> {
    @Override
    public Codec<DyedItemColor> codec() {
        return NMS.nms().dyedColor().codec();
    }
}
