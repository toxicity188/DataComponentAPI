package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ItemLore(@NotNull List<Component> lines, @NotNull List<Component> styledLines) implements ComponentData<ItemLore> {
    @Override
    public Codec<ItemLore> codec() {
        return NMS.nms().itemLore().codec();
    }
}
