package kr.toxicity.libraries.datacomponent.api;

import org.jetbrains.annotations.NotNull;

public interface DataComponent {
    @NotNull Codec<DataComponent> codec();
}
