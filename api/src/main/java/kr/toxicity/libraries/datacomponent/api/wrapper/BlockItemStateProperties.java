package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record BlockItemStateProperties(@NotNull Map<String, String> properties) implements ComponentData<BlockItemStateProperties> {
    @Override
    public Codec<BlockItemStateProperties> codec() {
        return NMS.nms().blockState().codec();
    }
}
