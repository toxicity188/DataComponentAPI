package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface ItemAdapter {
    @NotNull
    ItemStack build();

    default <T> void set(@NotNull DataComponentType<T> type, @Nullable T t) {
        type.set(this, t);
    }
    default <T> T get(@NotNull DataComponentType<T> type) {
        return type.get(this);
    }

    default void setToJson(@NotNull DataComponentType<?> type, @Nullable JsonElement element) {
        type.setToJson(this, element);
    }
    default JsonElement getToJson(@NotNull DataComponentType<?> type) {
        return type.getToJson(this);
    }

    default void deserialize(@NotNull JsonObject object) {
        DataComponentAPI.api().deserializer().deserialize(object).set(this);
    }
    default @NotNull JsonObject serialize() throws IllegalStateException {
        var result = new JsonObject();
        DataComponentType.registry().values().forEach(r -> {
            var get = getToJson(r);
            if (get != null) result.add(r.key(), get);
        });
        return result;
    }
}
