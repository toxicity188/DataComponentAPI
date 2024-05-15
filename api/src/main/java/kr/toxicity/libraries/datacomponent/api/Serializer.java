package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public interface Serializer {
    @NotNull DataComponent serialize(@NotNull JsonObject element);
}
