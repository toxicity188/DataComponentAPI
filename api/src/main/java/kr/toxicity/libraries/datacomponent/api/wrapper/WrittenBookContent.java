package kr.toxicity.libraries.datacomponent.api.wrapper;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record WrittenBookContent(@NotNull Filterable<String> title, @NotNull String author, int generation, @NotNull List<Filterable<Component>> pages, boolean resolved) {
}
