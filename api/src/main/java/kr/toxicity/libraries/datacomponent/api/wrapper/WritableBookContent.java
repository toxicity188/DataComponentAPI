package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record WritableBookContent(@NotNull List<Filterable<String>> pages) implements ComponentData<WritableBookContent> {
    @Override
    public Codec<WritableBookContent> codec() {
        return NMS.nms().writableBookContent().codec();
    }
}
