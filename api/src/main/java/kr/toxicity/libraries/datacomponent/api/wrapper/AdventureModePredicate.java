package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record AdventureModePredicate(@NotNull List<BlockPredicate> predicates, boolean showInTooltip) {
}
