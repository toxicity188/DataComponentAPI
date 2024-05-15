package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record Tag<T>(@NotNull Type<T> type, @NotNull T value) {

    public static final Type<Byte> BYTE = new Type<>(Byte.class);
    public static final Type<Short> SHORT = new Type<>(Short.class);
    public static final Type<Integer> INT = new Type<>(Integer.class);
    public static final Type<Long> LONG = new Type<>(Long.class);
    public static final Type<UUID> UUID = new Type<>(UUID.class);
    public static final Type<Float> FLOAT = new Type<>(Float.class);
    public static final Type<String> STRING = new Type<>(String.class);
    public static final Type<Double> DOUBLE = new Type<>(Double.class);

    public static final Type<byte[]> BYTE_ARRAY = new Type<>(byte[].class);
    public static final Type<int[]> INT_ARRAY = new Type<>(int[].class);
    public static final Type<long[]> LONG_ARRAY = new Type<>(long[].class);

    public record Type<T>(@NotNull Class<T> valueType) {
    }
}
