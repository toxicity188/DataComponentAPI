package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import kr.toxicity.libraries.datacomponent.api.ItemAdapter;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.jetbrains.annotations.NotNull;

final class ItemAdapterImpl implements ItemAdapter {
    final ItemStack itemStack;

    ItemAdapterImpl(org.bukkit.inventory.ItemStack itemStack) {
        this.itemStack = CraftItemStack.asNMSCopy(itemStack);
    }


    @Override
    public org.bukkit.inventory.@NotNull ItemStack build() {
        return CraftItemStack.asBukkitCopy(itemStack);
    }
}
