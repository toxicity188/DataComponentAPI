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

    Codec<Integer> INTEGER = of(JsonPrimitive::new, JsonElement::getAsInt);
    Codec<String> STRING = of(JsonPrimitive::new, JsonElement::getAsString);
    Codec<Boolean> BOOL = of(JsonPrimitive::new, JsonElement::getAsBoolean);

    static <T> @NotNull Codec<T> of(@NotNull Function<T, JsonElement> encoder, @NotNull Function<JsonElement, T> decoder) {
        return new Codec<T>() {
            @Override
            public @NotNull JsonElement encode(@NotNull T t) throws IllegalStateException {
                return encoder.apply(t);
            }

            @Override
            public @NotNull T decode(@NotNull JsonElement t) throws IllegalStateException {
                return decoder.apply(t);
            }
        };
    }
}
