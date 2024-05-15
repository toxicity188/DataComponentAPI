package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

public record CustomData(@NotNull CompoundTag tag) implements ComponentData<CustomData> {
    @Override
    public Codec<CustomData> codec() {
        return NMS.nms().entityData().codec();
    }
}
