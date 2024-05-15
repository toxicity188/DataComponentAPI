package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record PotionContents(@NotNull Optional<Integer> customColor, @NotNull List<MobEffectInstance> customEffects) implements ComponentData<PotionContents> {
    @Override
    public Codec<PotionContents> codec() {
        return NMS.nms().potionContents().codec();
    }
}
