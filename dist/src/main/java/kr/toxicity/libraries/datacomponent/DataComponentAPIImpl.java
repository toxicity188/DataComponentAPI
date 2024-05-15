package kr.toxicity.libraries.datacomponent;

import com.google.gson.*;
import kr.toxicity.libraries.datacomponent.api.*;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class DataComponentAPIImpl extends DataComponentAPI {
    private final MinecraftVersion current;
    private final NMS nms;
    private final Serializer serializer;

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
        } else {
            throw new UnsupportedOperationException("Unsupported minecraft version: " + current);
        }
        serializer = e -> {
            List<Consumer<ItemAdapter>> consumer = Collections.synchronizedList(new ArrayList<>());
            for (Map.Entry<String, JsonElement> entry : e.entrySet()) {
                var type = nms.componentRegistry().get(entry.getKey());
                if (type != null) {
                    consumer.add(a -> type.setToJson(a, entry.getValue()));
                }
            }
            return new DataComponent() {
                @Override
                public void set(@NotNull ItemAdapter adapter) {
                    consumer.forEach(e -> e.accept(adapter));
                }

                @Override
                public @NotNull JsonObject get() {
                    return e;
                }
            };
        };
    }

    @Override
    public @NotNull Serializer serializer() {
        return serializer;
    }

    private record RegistryTypeAdapter<T>(DataComponentType<T> type) implements JsonSerializer<T>, JsonDeserializer<T> {
        @Override
        public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
            return type.codec().encode(src);
        }

        @Override
        public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return type.codec().decode(json);
        }
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
