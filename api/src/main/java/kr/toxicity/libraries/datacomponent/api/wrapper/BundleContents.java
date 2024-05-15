package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.NMS;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record BundleContents(@NotNull List<ItemStack> itemStacks) implements ComponentData<BundleContents> {
    @Override
    public Codec<BundleContents> codec() {
        return NMS.nms().bundleContents().codec();
    }
}
