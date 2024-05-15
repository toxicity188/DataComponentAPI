package kr.toxicity.libraries.datacomponent.api;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface Converter<T, R> {
    @NotNull R asVanilla(@NotNull T t);
    @NotNull T asWrapper(@NotNull R r);

    static <T, R> Converter<T, R> of(@NotNull Function<T, R> vanilla, @NotNull Function<R, T> wrapper) {
        return new Converter<>() {
            @Override
            public @NotNull R asVanilla(@NotNull T t) {
                return vanilla.apply(t);
            }

            @Override
            public @NotNull T asWrapper(@NotNull R r) {
                return wrapper.apply(r);
            }
        };
    }
}
