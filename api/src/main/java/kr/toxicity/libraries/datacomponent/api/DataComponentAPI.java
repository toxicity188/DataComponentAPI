package kr.toxicity.libraries.datacomponent.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public abstract class DataComponentAPI {
    private static DataComponentAPI instance;

    public static @NotNull DataComponentAPI api() {
        return Objects.requireNonNull(instance);
    }
    public static void api(@NotNull DataComponentAPI api) {
        if (instance != null) throw new RuntimeException("DataComponentAPI is already loaded.");
        instance = api;
    }

    public abstract @NotNull MinecraftVersion current();
    public abstract @NotNull NMS nms();
    public abstract @NotNull Serializer serializer();

    public @NotNull ItemAdapter adapter(@NotNull ItemStack itemStack) {
        return nms().adapter(itemStack);
    }
}
