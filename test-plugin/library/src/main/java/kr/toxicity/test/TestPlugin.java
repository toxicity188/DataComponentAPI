package kr.toxicity.test;

import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import kr.toxicity.libraries.datacomponent.api.NMS;
import kr.toxicity.libraries.datacomponent.api.wrapper.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        var apply = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND_SWORD));
        apply.set(NMS.nms().damage(), 3);
        apply.set(NMS.nms().repairCost(), 20);
        apply.set(NMS.nms().rarity(), Rarity.EPIC);
        getLogger().info(apply.serialize().toString());
    }
}
