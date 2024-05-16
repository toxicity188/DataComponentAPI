package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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
        return new Codec<>() {
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
    default <R> @NotNull Codec<R> map(@NotNull Converter<R, T> converter) {
        return new Codec<>() {
            @Override
            public @NotNull JsonElement encode(@NotNull R r) throws IllegalStateException {
                return Codec.this.encode(converter.asVanilla(r));
            }

            @Override
            public @NotNull R decode(@NotNull JsonElement t) throws IllegalStateException {
                return converter.asWrapper(Codec.this.decode(t));
            }
        };
    }
    default Codec<List<T>> list() {
        return new Codec<>() {
            @Override
            public @NotNull JsonArray encode(@NotNull List<T> t) throws IllegalStateException {
                var array = new JsonArray();
                t.forEach(e -> array.add(Codec.this.encode(e)));
                return array;
            }

            @Override
            public @NotNull List<T> decode(@NotNull JsonElement t) throws IllegalStateException {
                var list = new ArrayList<T>();
                t.getAsJsonArray().forEach(e -> list.add(Codec.this.decode(e)));
                return list;
            }
        };
    }
}
