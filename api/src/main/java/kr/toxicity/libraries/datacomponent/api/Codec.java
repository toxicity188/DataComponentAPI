package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface Codec<T> {
    @NotNull
    JsonElement encode(@NotNull T t) throws IllegalStateException;
    @NotNull
    T decode(@NotNull JsonElement t) throws IllegalStateException;
    @NotNull
    Class<T> returnType();

    Codec<Integer> INTEGER = of(Integer.TYPE, JsonPrimitive::new, JsonElement::getAsInt);
    Codec<String> STRING = of(String.class, JsonPrimitive::new, JsonElement::getAsString);
    Codec<Boolean> BOOL = of(Boolean.TYPE, JsonPrimitive::new, JsonElement::getAsBoolean);

    static <T> @NotNull Codec<T> of(@NotNull Class<T> tClass, @NotNull Function<T, JsonElement> encoder, @NotNull Function<JsonElement, T> decoder) {
        return new Codec<>() {
            @Override
            public @NotNull JsonElement encode(@NotNull T t) throws IllegalStateException {
                return encoder.apply(t);
            }

            @Override
            public @NotNull T decode(@NotNull JsonElement t) throws IllegalStateException {
                return decoder.apply(t);
            }

            @Override
            public @NotNull Class<T> returnType() {
                return tClass;
            }
        };
    }
    default <R> @NotNull Codec<R> map(@NotNull Class<R> rClass, @NotNull Converter<R, T> converter) {
        return new Codec<>() {
            @Override
            public @NotNull JsonElement encode(@NotNull R r) throws IllegalStateException {
                return Codec.this.encode(converter.asVanilla(r));
            }

            @Override
            public @NotNull R decode(@NotNull JsonElement t) throws IllegalStateException {
                return converter.asWrapper(Codec.this.decode(t));
            }

            @Override
            public @NotNull Class<R> returnType() {
                return rClass;
            }
        };
    }
}
