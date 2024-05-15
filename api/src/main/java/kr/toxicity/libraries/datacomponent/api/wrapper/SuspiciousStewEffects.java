package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;

import java.util.List;

public record SuspiciousStewEffects(List<Entry> effects) implements ComponentData<SuspiciousStewEffects> {
    public record Entry(String effect, int duration) {
    }

    @Override
    public Codec<SuspiciousStewEffects> codec() {
        return NMS.nms().suspiciousStewEffects().codec();
    }
}
