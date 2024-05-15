package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record MobEffectInstance(
        @NotNull String effect,
        int duration,
        int amplifier,
        boolean ambient,
        boolean showParticles,
        boolean showIcon,
        @Nullable MobEffectInstance hiddenEffect
) {
}
