package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record BundleContents(@NotNull List<ItemStack> itemStacks) {
}
