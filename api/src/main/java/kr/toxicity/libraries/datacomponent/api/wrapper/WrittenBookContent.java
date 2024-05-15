package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record WrittenBookContent(@NotNull Filterable<String> title, @NotNull String author, int generation, @NotNull List<Filterable<Component>> pages, boolean resolved) implements ComponentData<WrittenBookContent> {
    @Override
    public Codec<WrittenBookContent> codec() {
        return NMS.nms().writtenBookContent().codec();
    }
}
