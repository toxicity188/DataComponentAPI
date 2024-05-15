package kr.toxicity.libraries.datacomponent.api;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface MinecraftVersion {
    int first();
    int second();
    int third();

    static @NotNull MinecraftVersion current() {
        return DataComponentAPI.api().current();
    }
}
