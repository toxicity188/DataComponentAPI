package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

public enum Rarity implements ComponentData<Rarity> {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC
    ;
    @Override
    public Codec<Rarity> codec() {
        return NMS.nms().rarity().codec();
    }
}
