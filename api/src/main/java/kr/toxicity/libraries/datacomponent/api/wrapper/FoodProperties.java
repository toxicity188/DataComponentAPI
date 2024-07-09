package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record FoodProperties(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds, @NotNull Optional<ItemStack> usingConvertsTo, @NotNull List<PossibleEffect> effects) implements ComponentData<FoodProperties> {
    public record PossibleEffect(@NotNull MobEffectInstance effect, float probability) {
    }

    @Override
    public Codec<FoodProperties> codec() {
        return NMS.nms().food().codec();
    }
}
