package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

public enum Unit implements ComponentData<Unit> {
    INSTANCE
    ;

    @Override
    public Codec<Unit> codec() {
        return NMS.nms().creativeSlotLock().codec();
    }
}
