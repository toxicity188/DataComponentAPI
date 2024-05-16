package kr.toxicity.test;

import com.google.gson.JsonParser;
import kr.toxicity.libraries.datacomponent.DataComponentAPIBukkit;
import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import kr.toxicity.libraries.datacomponent.api.DataComponentType;
import kr.toxicity.libraries.datacomponent.api.NMS;
import kr.toxicity.libraries.datacomponent.api.wrapper.Rarity;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        DataComponentAPIBukkit.load();

        var apply = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND_SWORD));
        apply.set(DataComponentType.DAMAGE, 3);
        apply.set(DataComponentType.REPAIR_COST, 20);
        apply.set(DataComponentType.RARITY, Rarity.EPIC);
        getLogger().info(apply.serialize().toString());

        // Serialization.
        var data = DataComponentAPI.api().deserializer().deserialize(
                JsonParser.parseString("{\"damage\":3,\"max_stack_size\":1,\"repair_cost\":20,\"tool\":{\"rules\":[{\"blocks\":\"minecraft:cobweb\",\"speed\":15.0,\"correct_for_drops\":true},{\"blocks\":\"#minecraft:sword_efficient\",\"speed\":1.5}],\"damage_per_block\":2},\"max_damage\":1561,\"item_lore\":[],\"rarity\":\"epic\"}").getAsJsonObject()
        );
        var diamond = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND));
        getLogger().info(diamond.serialize().toString());
        data.set(diamond);
        getLogger().info(diamond.serialize().toString());

        var value = data.get(NMS.nms().rarity());
        if (value != null) getLogger().info(value.name());

        for (Material material : Material.values()) {
            if (material.isItem()) {
                try {
                    DataComponentAPI.api().adapter(new ItemStack(material)).serialize();
                } catch (Exception e) {
                    getLogger().info(material.name());
                }
            }
        }
        var test = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND));
        test.set(NMS.nms().customName(), Component.empty());
        System.out.println(test.serialize());
    }
}
