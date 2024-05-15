package kr.toxicity.libraries.datacomponent.api;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public record TrimPattern(@NotNull String assetId, @NotNull String templateItem, @NotNull Component description, boolean decal) {
}
