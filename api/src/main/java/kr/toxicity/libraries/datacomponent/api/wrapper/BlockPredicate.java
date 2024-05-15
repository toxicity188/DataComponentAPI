package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record BlockPredicate(@NotNull Optional<List<String>> blocks) {
}
