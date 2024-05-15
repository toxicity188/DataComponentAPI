package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DataComponentType<T> {
    static Registry<? extends DataComponentType<?>> registry() {
        return NMS.nms().componentRegistry();
    }
    @NotNull String key();
    @NotNull Codec<T> codec();

    @Nullable T set(@NotNull ItemAdapter adapter, @Nullable T t);
    @Nullable T get(@NotNull ItemAdapter adapter);

    @Nullable T setToJson(@NotNull ItemAdapter adapter, @Nullable JsonElement element);
    @Nullable JsonElement getToJson(@NotNull ItemAdapter adapter);
}
