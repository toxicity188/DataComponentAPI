package kr.toxicity.test;

import com.google.gson.JsonParser;
import kr.toxicity.libraries.datacomponent.DataComponentAPIBukkit;
import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import kr.toxicity.libraries.datacomponent.api.NMS;
import kr.toxicity.libraries.datacomponent.api.wrapper.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        DataComponentAPIBukkit.load();

        var apply = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND_SWORD));
        apply.set(NMS.nms().damage(), 3);
        apply.set(NMS.nms().repairCost(), 20);
        apply.set(NMS.nms().rarity(), Rarity.EPIC);
        getLogger().info(apply.serialize().toString());

        var data = DataComponentAPI.api().deserializer().deserialize(
                JsonParser.parseString("{\"damage\":3,\"max_stack_size\":1,\"repair_cost\":20,\"tool\":{\"rules\":[{\"blocks\":\"minecraft:cobweb\",\"speed\":15.0,\"correct_for_drops\":true},{\"blocks\":\"#minecraft:sword_efficient\",\"speed\":1.5}],\"damage_per_block\":2},\"max_damage\":1561,\"item_lore\":[],\"rarity\":\"epic\"}").getAsJsonObject()
        );
        var diamond = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND));
        getLogger().info(diamond.serialize().toString());
        data.set(diamond);
        getLogger().info(diamond.serialize().toString());

        getLogger().info(DataComponentAPI.api().adapter(new ItemStack(Material.NETHERITE_CHESTPLATE)).serialize().toString());
    }
}
