# DataComponentAPI

This is a simple API for data component!

### Usage in library plugin
``` java
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
```

### Usage in shade
``` java
public class TestPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        DataComponentAPIBukkit.load();

        var apply = DataComponentAPI.api().adapter(new ItemStack(Material.DIAMOND_SWORD));
        apply.set(NMS.nms().damage(), 3);
        apply.set(NMS.nms().repairCost(), 20);
        apply.set(NMS.nms().rarity(), Rarity.EPIC);
        getLogger().info(apply.serialize().toString());
    }
}
```
