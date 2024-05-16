package kr.toxicity.libraries.datacomponent.api;

import com.google.gson.JsonElement;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;

import java.util.List;
import java.util.function.Supplier;

import static kr.toxicity.libraries.datacomponent.api.NMS.nms;

@SuppressWarnings("unused")
public interface DataComponentType<T> {

    Supplier<DataComponentType<Integer>> MAX_STACK_SIZE = () -> nms().maxStackSize();
    Supplier<DataComponentType<Integer>> MAX_DAMAGE = () -> nms().maxDamage();
    Supplier<DataComponentType<Integer>> DAMAGE = () -> nms().damage();
    Supplier<DataComponentType<Component>> CUSTOM_NAME = () -> nms().customName();
    Supplier<DataComponentType<Component>> ITEM_NAME = () -> nms().itemName();
    Supplier<DataComponentType<ItemLore>> LORE = () -> nms().lore();
    Supplier<DataComponentType<Rarity>> RARITY = () -> nms().rarity();
    Supplier<DataComponentType<AdventureModePredicate>> CAN_PLACE_ON = () -> nms().canPlaceOn();
    Supplier<DataComponentType<AdventureModePredicate>> CAN_BREAK = () -> nms().canBreak();
    Supplier<DataComponentType<CustomModelData>> CUSTOM_MODEL_DATA = () -> nms().customModelData();
    Supplier<DataComponentType<Integer>> REPAIR_COST = () -> nms().repairCost();
    Supplier<DataComponentType<Unit>> CREATIVE_SLOT_LOCK = () -> nms().creativeSlotLock();
    Supplier<DataComponentType<Boolean>> ENCHANTMENT_GLINT_OVERRIDE = () -> nms().enchantmentGlintOverride();
    Supplier<DataComponentType<Unit>> INTANGIBLE_PROJECTILE = () -> nms().intangibleProjectile();
    Supplier<DataComponentType<FoodProperties>> FOOD = () -> nms().food();
    Supplier<DataComponentType<Unit>> FIRE_RESISTANT = () -> nms().fireResistant();
    Supplier<DataComponentType<Tool>> TOOL = () -> nms().tool();
    Supplier<DataComponentType<DyedItemColor>> DYED_COLOR = () -> nms().dyedColor();
    Supplier<DataComponentType<MapItemColor>> MAP_COLOR = () -> nms().mapColor();
    Supplier<DataComponentType<MapId>> MAP_ID = () -> nms().mapId();
    Supplier<DataComponentType<BundleContents>> BUNDLE_CONTENTS = () -> nms().bundleContents();
    Supplier<DataComponentType<PotionContents>> POTION_CONTENTS = () -> nms().potionContents();
    Supplier<DataComponentType<SuspiciousStewEffects>> SUSPICIOUS_STEW_EFFECTS = () -> nms().suspiciousStewEffects();
    Supplier<DataComponentType<WritableBookContent>> WRITABLE_BOOK_CONTENT = () -> nms().writableBookContent();
    Supplier<DataComponentType<WrittenBookContent>> WRITTEN_BOOK_CONTENT = () -> nms().writtenBookContent();
    Supplier<DataComponentType<ArmorTrim>> TRIM = () -> nms().trim();
    Supplier<DataComponentType<BlockItemStateProperties>> BLOCK_STATE = () -> nms().blockState();
    Supplier<DataComponentType<CustomData>> ENTITY_DATA = () -> nms().entityData();
    Supplier<DataComponentType<CustomData>> BUCKET_ENTITY_DATA = () -> nms().bucketEntityData();
    Supplier<DataComponentType<CustomData>> BLOCK_ENTITY_DATA = () -> nms().blockEntityData();
    Supplier<DataComponentType<Integer>> OMINOUS_BOTTLE_AMPLIFIER = () -> nms().ominousBottleAmplifier();
    Supplier<DataComponentType<List<String>>> RECIPES = () -> nms().recipes();

    static Registry<? extends DataComponentType<?>> registry() {
        return NMS.nms().componentRegistry();
    }

    @NotNull String key();
    @NotNull Codec<T> codec();

    @Nullable T set(@NotNull ItemAdapter adapter, @Nullable T t);
    @Nullable T get(@NotNull ItemAdapter adapter);

    @Nullable T setToJson(@NotNull ItemAdapter adapter, @Nullable JsonElement element);
    @Nullable JsonElement getToJson(@NotNull ItemAdapter adapter);

}
