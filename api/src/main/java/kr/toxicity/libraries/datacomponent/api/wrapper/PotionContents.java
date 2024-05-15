package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record PotionContents(@NotNull Optional<Integer> customColor, @NotNull List<MobEffectInstance> customEffects) {
}
