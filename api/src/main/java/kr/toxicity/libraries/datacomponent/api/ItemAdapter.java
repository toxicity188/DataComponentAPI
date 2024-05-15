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
        object.entrySet().forEach(e -> {
            var type = DataComponentType.registry().get(e.getKey());
            if (type != null) {
                setToJson(type, e.getValue());
            }
        });
    }
    default @NotNull JsonObject serialize() {
        var result = new JsonObject();
        DataComponentType.registry().values().forEach(r -> {
            try {
                var get = getToJson(r);
                if (get != null) result.add(r.key(), get);
            } catch (Exception e) {
                System.out.println(r.key());
                e.printStackTrace();
            }
        });
        return result;
    }
}
