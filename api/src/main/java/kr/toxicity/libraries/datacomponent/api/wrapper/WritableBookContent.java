package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record WritableBookContent(@NotNull List<Filterable<String>> pages) {
}
