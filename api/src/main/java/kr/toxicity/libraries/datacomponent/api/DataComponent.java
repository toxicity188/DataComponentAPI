package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public interface DataComponent {
    @Nullable default <T> T get(@NotNull DataComponentType<T> type) {
        var get = getToJson(type);
        return get != null ? type.codec().decode(get) : null;
    }
    @Nullable JsonElement getToJson(@NotNull DataComponentType<?> type);
    @NotNull
    JsonObject getToJson();
    default boolean has(@NotNull DataComponentType<?> type) {
        return get(type) != null;
    }
    void set(@NotNull ItemAdapter adapter);
}
