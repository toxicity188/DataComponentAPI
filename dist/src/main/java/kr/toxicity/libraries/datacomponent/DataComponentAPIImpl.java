package kr.toxicity.libraries.datacomponent;

import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import kr.toxicity.libraries.datacomponent.api.MinecraftVersion;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class DataComponentAPIImpl extends DataComponentAPI {
    private final MinecraftVersion current;
    private final NMS nms;

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
