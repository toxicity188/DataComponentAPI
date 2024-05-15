package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public interface Deserializer {
    @NotNull DataComponent deserialize(@NotNull JsonObject element);
}
