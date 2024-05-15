package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

public record MapId(int id) implements ComponentData<MapId> {
    @Override
    public Codec<MapId> codec() {
        return NMS.nms().mapId().codec();
    }
}
