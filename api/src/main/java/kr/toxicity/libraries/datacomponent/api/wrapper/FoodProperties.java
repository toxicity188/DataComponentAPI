package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record FoodProperties(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds, @NotNull List<PossibleEffect> effects) {
    public record PossibleEffect(@NotNull MobEffectInstance effect, float probability) {
    }
}
