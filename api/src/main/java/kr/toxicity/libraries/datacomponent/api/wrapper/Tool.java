package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record Tool(@NotNull List<Rule> rules, float defaultMiningSpeed, int damagePerBlock) implements ComponentData<Tool> {
    public record Rule(@NotNull List<String> blocks, @NotNull Optional<Float> speed, @NotNull Optional<Boolean> correctForDrops) {
    }

    @Override
    public Codec<Tool> codec() {
        return NMS.nms().tool().codec();
    }
}
