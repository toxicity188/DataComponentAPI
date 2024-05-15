package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public record Ingredient(@NotNull Stream<ItemStack> entries) {
    public Ingredient(@NotNull ItemStack... stacks) {
        this(Arrays.stream(stacks));
    }
}
