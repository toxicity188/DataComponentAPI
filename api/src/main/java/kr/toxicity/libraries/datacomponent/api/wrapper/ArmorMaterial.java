package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public record ArmorMaterial(
        @NotNull Map<Type, Integer> defense,
        int enchantmentValue,
        @NotNull SoundEvent equipSound,
        @NotNull Supplier<Ingredient> repairIngredient,
        @NotNull List<Layer> layers,
        float toughness,
        float knockbackResistance
) {
    public enum Type {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        BODY
    }
    public record Layer(String id, String suffix, boolean dyeable) {
        public Layer(String id) {
            this(id, "", false);
        }
    }
}
