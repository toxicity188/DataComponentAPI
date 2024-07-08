package kr.toxicity.libraries.datacomponent;

import com.google.gson.*;
import kr.toxicity.libraries.datacomponent.api.*;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

@SuppressWarnings("unused")
public final class DataComponentAPIImpl extends DataComponentAPI {
    private final MinecraftVersion current;
    private final NMS nms;
    private final Deserializer deserializer;

    DataComponentAPIImpl() {
        this(new MinecraftVersionImpl(
                Bukkit.getBukkitVersion().split("-")[0]
        ));
    }
    private DataComponentAPIImpl(@NotNull String version) {
        this(new MinecraftVersionImpl(version));
    }
    private DataComponentAPIImpl(@NotNull MinecraftVersion current){
        this.current = current;
        if (current.equals(MinecraftVersionImpl.V1_20_5) || current.equals(MinecraftVersionImpl.V1_20_6)) {
            nms = new kr.toxicity.libraries.datacomponent.nms.v1_20_R4.NMSImpl();
        } else if (current.equals(MinecraftVersionImpl.V1_21)) {
            nms = new kr.toxicity.libraries.datacomponent.nms.v1_21_R1.NMSImpl();
        } else {
            throw new UnsupportedOperationException("Unsupported minecraft version: " + current);
        }
        deserializer = e -> {
            Map<DataComponentType<?>, JsonElement> consumer = Collections.synchronizedMap(new HashMap<>());
            for (Map.Entry<String, JsonElement> entry : e.entrySet()) {
                var type = nms.componentRegistry().get(entry.getKey());
                if (type != null) {
                    consumer.put(type, entry.getValue());
                }
            }
            return new DataComponent() {
                @Override
                public void set(@NotNull ItemAdapter adapter) {
                    consumer.forEach(adapter::setToJson);
                }

                @Override
                public @NotNull JsonObject getToJson() {
                    return e;
                }

                @Override
                public @Nullable JsonElement getToJson(@NotNull DataComponentType<?> type) {
                    return consumer.get(type);
                }
            };
        };
    }

    @Override
    public @NotNull Deserializer deserializer() {
        return deserializer;
    }

    static void load() {
        DataComponentAPI.api(new DataComponentAPIImpl());
    }
    static void load(@NotNull String version) {
        DataComponentAPI.api(new DataComponentAPIImpl(version));
    }

    @Override
    public @NotNull MinecraftVersion current() {
        return current;
    }
    @Override
    public @NotNull NMS nms() {
        return nms;
    }
}
