package kr.toxicity.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import kr.toxicity.libraries.datacomponent.DataComponentAPIBukkit;
import kr.toxicity.libraries.datacomponent.api.DataComponentAPI;
import kr.toxicity.libraries.datacomponent.api.DataComponentType;
import kr.toxicity.libraries.datacomponent.api.NMS;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        DataComponentAPIBukkit.load();

        var apply = DataComponentAPI.api().adapter(new ItemStack(Material.NETHERITE_SWORD));
        apply.set(DataComponentType.DAMAGE, 3);
        apply.set(DataComponentType.REPAIR_COST, 10);
        apply.set(DataComponentType.ENCHANTMENT_GLINT_OVERRIDE, false);
        apply.set(DataComponentType.ITEM_NAME, Component.text("Hello"));
        apply.set(DataComponentType.CUSTOM_NAME, Component.text("World"));
        apply.set(DataComponentType.CUSTOM_MODEL_DATA, new CustomModelData(1));

        var map = new HashMap<String, Tag<?>>();
        map.put("a", new Tag<>(Tag.INT, 1));
        map.put("b", new Tag<>(Tag.STRING, "ddd"));
        map.put("c", new Tag<>(Tag.INT_ARRAY, new int[] {1, 2, 3}));
        apply.set(DataComponentType.CUSTOM_DATA, new CustomData(new CompoundTag(map)));

        var get = apply.build();

        var data = getDataFolder();
        if (!data.exists()) data.mkdirs();

        var result = new File(data, "result.json");
        var compare = new File(data, "compare.yml");
        try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(result)))) {
            writer.setIndent(" ");
            var itemJson = new JsonObject();
            itemJson.addProperty("type", Material.NETHERITE_SWORD.name());
            itemJson.add("components", apply.serialize());
            new Gson().toJson(itemJson, writer);
        } catch (Exception e) {

        }
        try {
            var yaml = new YamlConfiguration();
            yaml.set("item" , get);
            yaml.save(compare);
        } catch (Exception e) {

        }
    }
}
