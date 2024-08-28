package kr.toxicity.libraries.datacomponent;

import kr.toxicity.libraries.datacomponent.api.MinecraftVersion;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

record MinecraftVersionImpl(int first, int second, int third) implements Comparable<MinecraftVersionImpl>, MinecraftVersion {
    private static final Comparator<MinecraftVersionImpl> COMPARATOR = Comparator.comparing(MinecraftVersionImpl::first)
            .thenComparing(MinecraftVersionImpl::second)
            .thenComparing(MinecraftVersionImpl::third);
    @Override
    public int compareTo(@NotNull MinecraftVersionImpl o) {
        return COMPARATOR.compare(this, o);
    }

    public static final MinecraftVersionImpl V1_21_1 = new MinecraftVersionImpl(1, 21, 1);
    public static final MinecraftVersionImpl V1_21 = new MinecraftVersionImpl(1, 21, 0);
    public static final MinecraftVersionImpl V1_20_6 = new MinecraftVersionImpl(1, 20, 6);
    public static final MinecraftVersionImpl V1_20_5 = new MinecraftVersionImpl(1, 20, 5);

    public MinecraftVersionImpl(@NotNull String version) {
        this(version.split("\\."));
    }
    public MinecraftVersionImpl(@NotNull String[] version) {
        this(
                version.length > 0 ? Integer.parseInt(version[0]) : 0,
                version.length > 1 ? Integer.parseInt(version[1]) : 0,
                version.length > 2 ? Integer.parseInt(version[2]) : 0
        );
    }

    @Override
    public String toString() {
        return first + "." + second + "." + third;
    }
}