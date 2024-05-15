package kr.toxicity.libraries.datacomponent.api.wrapper;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record TrimMaterial(
        @NotNull String assetName,
        @NotNull String ingredient,
        float itemModelIndex,
        @NotNull Map<ArmorMaterial, String> overrideArmorMaterials,
        @NotNull Component description
) {
}
