package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record AdventureModePredicate(@NotNull List<BlockPredicate> predicates, boolean showInTooltip) implements ComponentData<AdventureModePredicate> {
    @Override
    public Codec<AdventureModePredicate> codec() {
        return NMS.nms().canBreak().codec();
    }
}
