package kr.toxicity.libraries.datacomponent.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;

public interface Registry<T> {
    @Nullable T get(@NotNull String key);
    @NotNull
    @Unmodifiable
    Collection<T> values();
}
