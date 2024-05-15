package kr.toxicity.libraries.datacomponent.api.wrapper;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ItemLore(@NotNull List<Component> lines, @NotNull List<Component> styledLines) {
}
