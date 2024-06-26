package kr.toxicity.libraries.datacomponent.api;

import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public interface NMS {
    static @NotNull NMS nms() {
        return DataComponentAPI.api().nms();
    }
    @NotNull
    ItemAdapter adapter(@NotNull ItemStack itemStack);
    @NotNull
    Registry<? extends DataComponentType<?>> componentRegistry();

    @NotNull
    DataComponentType<CustomData> customData();
    @NotNull
    DataComponentType<Integer> maxStackSize();
    @NotNull
    DataComponentType<Integer> maxDamage();
    @NotNull
    DataComponentType<Integer> damage();
    @NotNull
    DataComponentType<Component> customName();
    @NotNull
    DataComponentType<Component> itemName();
    @NotNull
    DataComponentType<ItemLore> lore();
    @NotNull
    DataComponentType<Rarity> rarity();
    @NotNull
    DataComponentType<AdventureModePredicate> canPlaceOn();
    @NotNull
    DataComponentType<AdventureModePredicate> canBreak();
    @NotNull
    DataComponentType<CustomModelData> customModelData();
    @NotNull
    DataComponentType<Integer> repairCost();
    @NotNull
    DataComponentType<Unit> creativeSlotLock();
    @NotNull
    DataComponentType<Boolean> enchantmentGlintOverride();
    @NotNull
    DataComponentType<Unit> intangibleProjectile();
    @NotNull
    DataComponentType<FoodProperties> food();
    @NotNull
    DataComponentType<Unit> fireResistant();
    @NotNull
    DataComponentType<Tool> tool();
    @NotNull
    DataComponentType<DyedItemColor> dyedColor();
    @NotNull
    DataComponentType<MapItemColor> mapColor();
    @NotNull
    DataComponentType<MapId> mapId();
    @NotNull
    DataComponentType<BundleContents> bundleContents();
    @NotNull
    DataComponentType<PotionContents> potionContents();
    @NotNull
    DataComponentType<SuspiciousStewEffects> suspiciousStewEffects();
    @NotNull
    DataComponentType<WritableBookContent> writableBookContent();
    @NotNull
    DataComponentType<WrittenBookContent> writtenBookContent();
    @NotNull
    DataComponentType<ArmorTrim> trim();
    @NotNull
    DataComponentType<BlockItemStateProperties> blockState();
    @NotNull
    DataComponentType<CustomData> entityData();
    @NotNull
    DataComponentType<CustomData> bucketEntityData();
    @NotNull
    DataComponentType<CustomData> blockEntityData();
    @NotNull
    DataComponentType<Integer> ominousBottleAmplifier();
    @NotNull
    DataComponentType<List<String>> recipes();
}
