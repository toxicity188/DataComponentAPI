package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record CompoundTag(@NotNull Map<String, Tag<?>> tags) implements Tag<CompoundTag> {
    @Override
    public CompoundTag value() {
        return this;
    }
}
