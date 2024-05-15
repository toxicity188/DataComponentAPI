package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import kr.toxicity.libraries.datacomponent.api.DataComponentType;
import kr.toxicity.libraries.datacomponent.api.ItemAdapter;
import kr.toxicity.libraries.datacomponent.api.NMS;
import kr.toxicity.libraries.datacomponent.api.Registry;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class NMSImpl implements NMS {

    @Override
    public @NotNull ItemAdapter adapter(@NotNull ItemStack itemStack) {
        return new ItemAdapterImpl(itemStack);
    }

    @Override
    public @NotNull Registry<? extends DataComponentType<?>> componentRegistry() {
        return DataComponentTypeImpl.REGISTRY;
    }

    @Override
    public @NotNull DataComponentType<Integer> maxStackSize() {
        return DataComponentTypeImpl.MAX_STACK_SIZE;
    }

    @Override
    public @NotNull DataComponentType<Integer> maxDamage() {
        return DataComponentTypeImpl.MAX_DAMAGE;
    }

    @Override
    public @NotNull DataComponentType<Integer> damage() {
        return DataComponentTypeImpl.DAMAGE;
    }

    @Override
    public @NotNull DataComponentType<Component> customName() {
        return DataComponentTypeImpl.CUSTOM_NAME;
    }

    @Override
    public @NotNull DataComponentType<ItemLore> itemLore() {
        return DataComponentTypeImpl.ITEM_LORE;
    }

    @Override
    public @NotNull DataComponentType<Rarity> rarity() {
        return DataComponentTypeImpl.RARITY;
    }

    @Override
    public @NotNull DataComponentType<AdventureModePredicate> canPlaceOn() {
        return DataComponentTypeImpl.CAN_PLACE_ON;
    }

    @Override
    public @NotNull DataComponentType<AdventureModePredicate> canBreak() {
        return DataComponentTypeImpl.CAN_BREAK;
    }

    @Override
    public @NotNull DataComponentType<Integer> repairCost() {
        return DataComponentTypeImpl.REPAIR_COST;
    }

    @Override
    public @NotNull DataComponentType<Unit> creativeSlotLock() {
        return DataComponentTypeImpl.CREATIVE_SLOT_LOCK;
    }

    @Override
    public @NotNull DataComponentType<Boolean> enchantmentGlintOverride() {
        return DataComponentTypeImpl.ENCHANTMENT_GLINT_OVERRIDE;
    }

    @Override
    public @NotNull DataComponentType<Unit> intangibleProjectile() {
        return DataComponentTypeImpl.INTANGIBLE_PROJECTILE;
    }

    @Override
    public @NotNull DataComponentType<Unit> fireResistant() {
        return DataComponentTypeImpl.FIRE_RESISTANT;
    }

    @Override
    public @NotNull DataComponentType<Tool> tool() {
        return DataComponentTypeImpl.TOOL;
    }

    @Override
    public @NotNull DataComponentType<DyedItemColor> dyedColor() {
        return DataComponentTypeImpl.DYED_COLOR;
    }

    @Override
    public @NotNull DataComponentType<MapItemColor> mapColor() {
        return DataComponentTypeImpl.MAP_COLOR;
    }

    @Override
    public @NotNull DataComponentType<MapId> mapId() {
        return DataComponentTypeImpl.MAP_ID;
    }

    @Override
    public @NotNull DataComponentType<BundleContents> bundleContents() {
        return DataComponentTypeImpl.BUNDLE_CONTENTS;
    }

    @Override
    public @NotNull DataComponentType<PotionContents> potionContents() {
        return DataComponentTypeImpl.POTION_CONTENTS;
    }

    @Override
    public @NotNull DataComponentType<SuspiciousStewEffects> suspiciousStewEffects() {
        return DataComponentTypeImpl.SUSPICIOUS_STEW_EFFECTS;
    }

    @Override
    public @NotNull DataComponentType<WritableBookContent> writableBookContent() {
        return DataComponentTypeImpl.WRITABLE_BOOK_CONTENT;
    }

    @Override
    public @NotNull DataComponentType<WrittenBookContent> writtenBookContent() {
        return DataComponentTypeImpl.WRITTEN_BOOK_CONTENT;
    }

    @Override
    public @NotNull DataComponentType<BlockItemStateProperties> blockState() {
        return DataComponentTypeImpl.BLOCK_STATE;
    }
}
