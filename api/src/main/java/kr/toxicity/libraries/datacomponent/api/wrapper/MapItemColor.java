package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

public record MapItemColor(int rgb) implements ComponentData<MapItemColor> {
    @Override
    public Codec<MapItemColor> codec() {
        return NMS.nms().mapColor().codec();
    }
}
