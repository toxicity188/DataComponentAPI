package kr.toxicity.libraries.datacomponent;

import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DataComponentAPIBukkit extends JavaPlugin {
    @Override
    public void onEnable() {
        DataComponentAPI.api(new DataComponentAPIImpl());
    }
    public static void load() {
        DataComponentAPIImpl.load();
    }
    public static void load(@NotNull String version) {
        DataComponentAPIImpl.load(version);
    }
}
