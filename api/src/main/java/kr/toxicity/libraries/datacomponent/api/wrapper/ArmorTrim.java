package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.TrimPattern;
import org.jetbrains.annotations.NotNull;

public record ArmorTrim(@NotNull TrimMaterial material, @NotNull TrimPattern pattern, boolean showInTooltip) {
}
