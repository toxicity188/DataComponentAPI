package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import com.google.gson.JsonElement;
import kr.toxicity.libraries.datacomponent.api.Codec;
import kr.toxicity.libraries.datacomponent.api.Converter;
import kr.toxicity.libraries.datacomponent.api.ItemAdapter;
import kr.toxicity.libraries.datacomponent.api.Registry;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

@RequiredArgsConstructor
final class DataComponentTypeImpl<T, R> implements kr.toxicity.libraries.datacomponent.api.DataComponentType<T> {

    private static final Map<String, DataComponentTypeImpl<?, ?>> TYPE = new HashMap<>();

    static final Registry<? extends kr.toxicity.libraries.datacomponent.api.DataComponentType<?>> REGISTRY = new Registry<>() {
        @Override
        public @Nullable kr.toxicity.libraries.datacomponent.api.DataComponentType<?> get(@NotNull String key) {
            synchronized (TYPE) {
                return TYPE.get(key);
            }
        }

        @Override
        public @NotNull @Unmodifiable Collection<kr.toxicity.libraries.datacomponent.api.DataComponentType<?>> values() {
            synchronized (TYPE) {
                return List.copyOf(TYPE.values());
            }
        }
    };

    static final DataComponentTypeImpl<CustomData, net.minecraft.world.item.component.CustomData> CUSTOM_DATA = register(
            "custom_data",
            DataComponents.CUSTOM_DATA,
            Converters.CUSTOM_DATA,
            CodecImpl.CUSTOM_DATA
    );
    static final DataComponentTypeImpl<Integer, Integer> MAX_STACK_SIZE = register(
            "max_stack_size",
            DataComponents.MAX_STACK_SIZE,
            Converters.INTEGER,
            Codec.INTEGER
    );
    static final DataComponentTypeImpl<Integer, Integer> MAX_DAMAGE = register(
            "max_damage",
            DataComponents.MAX_DAMAGE,
            Converters.INTEGER,
            Codec.INTEGER
    );
    static final DataComponentTypeImpl<Integer, Integer> DAMAGE = register(
            "damage",
            DataComponents.DAMAGE,
            Converters.INTEGER,
            Codec.INTEGER
    );
    static final DataComponentTypeImpl<Component, net.minecraft.network.chat.Component> CUSTOM_NAME = register(
            "custom_name",
            DataComponents.CUSTOM_NAME,
            Converters.COMPONENT,
            CodecImpl.COMPONENT
    );
    static final DataComponentTypeImpl<Component, net.minecraft.network.chat.Component> ITEM_NAME = register(
            "item_name",
            DataComponents.ITEM_NAME,
            Converters.COMPONENT,
            CodecImpl.COMPONENT
    );
    static final DataComponentTypeImpl<ItemLore, net.minecraft.world.item.component.ItemLore> ITEM_LORE = register(
            "item_lore",
            DataComponents.LORE,
            Converters.ITEM_LORE,
            CodecImpl.ITEM_LORE
    );
    static final DataComponentTypeImpl<Rarity, net.minecraft.world.item.Rarity> RARITY = register(
            "rarity",
            DataComponents.RARITY,
            Converters.RARITY,
            CodecImpl.RARITY
    );
    static final DataComponentTypeImpl<AdventureModePredicate, net.minecraft.world.item.AdventureModePredicate> CAN_PLACE_ON = register(
            "can_place_on",
            DataComponents.CAN_PLACE_ON,
            Converters.ADVENTURE_MODE_PREDICATE,
            CodecImpl.ADVENTURE_MODE_PREDICATE
    );
    static final DataComponentTypeImpl<AdventureModePredicate, net.minecraft.world.item.AdventureModePredicate> CAN_BREAK = register(
            "can_break",
            DataComponents.CAN_BREAK,
            Converters.ADVENTURE_MODE_PREDICATE,
            CodecImpl.ADVENTURE_MODE_PREDICATE
    );
    static final DataComponentTypeImpl<CustomModelData, net.minecraft.world.item.component.CustomModelData> CUSTOM_MODEL_DATA = register(
            "custom_model_data",
            DataComponents.CUSTOM_MODEL_DATA,
            Converters.CUSTOM_MODEL_DATA,
            CodecImpl.CUSTOM_MODEL_DATA
    );
    static final DataComponentTypeImpl<Integer, Integer> REPAIR_COST = register(
            "repair_cost",
            DataComponents.REPAIR_COST,
            Converters.INTEGER,
            Codec.INTEGER
    );
    static final DataComponentTypeImpl<Unit, net.minecraft.util.Unit> CREATIVE_SLOT_LOCK = register(
            "creative_slot_lock",
            DataComponents.CREATIVE_SLOT_LOCK,
            Converters.UNIT,
            CodecImpl.UNIT
    );
    static final DataComponentTypeImpl<Boolean, Boolean> ENCHANTMENT_GLINT_OVERRIDE = register(
            "enchantment_glint_override",
            DataComponents.ENCHANTMENT_GLINT_OVERRIDE,
            Converters.BOOL,
            Codec.BOOL
    );
    static final DataComponentTypeImpl<Unit, net.minecraft.util.Unit> INTANGIBLE_PROJECTILE = register(
            "intangible_projectile",
            DataComponents.INTANGIBLE_PROJECTILE,
            Converters.UNIT,
            CodecImpl.UNIT
    );
    static final DataComponentTypeImpl<FoodProperties, net.minecraft.world.food.FoodProperties> FOOD = register(
            "food",
            DataComponents.FOOD,
            Converters.FOOD,
            CodecImpl.FOOD
    );
    static final DataComponentTypeImpl<Unit, net.minecraft.util.Unit> FIRE_RESISTANT = register(
            "fire_resistance",
            DataComponents.FIRE_RESISTANT,
            Converters.UNIT,
            CodecImpl.UNIT
    );
    static final DataComponentTypeImpl<Tool, net.minecraft.world.item.component.Tool> TOOL = register(
            "tool",
            DataComponents.TOOL,
            Converters.TOOL,
            CodecImpl.TOOL
    );
    static final DataComponentTypeImpl<DyedItemColor, net.minecraft.world.item.component.DyedItemColor> DYED_COLOR = register(
            "dyed_color",
            DataComponents.DYED_COLOR,
            Converters.DYED_ITEM_COLOR,
            CodecImpl.DYED_ITEM_COLOR
    );
    static final DataComponentTypeImpl<MapItemColor, net.minecraft.world.item.component.MapItemColor> MAP_COLOR = register(
            "map_color",
            DataComponents.MAP_COLOR,
            Converters.MAP_ITEM_COLOR,
            CodecImpl.MAP_ITEM_COLOR
    );
    static final DataComponentTypeImpl<MapId, net.minecraft.world.level.saveddata.maps.MapId> MAP_ID = register(
            "map_id",
            DataComponents.MAP_ID,
            Converters.MAP_ID,
            CodecImpl.MAP_ID
    );
    static final DataComponentTypeImpl<BundleContents, net.minecraft.world.item.component.BundleContents> BUNDLE_CONTENTS = register(
            "bundle_contents",
            DataComponents.BUNDLE_CONTENTS,
            Converters.BUNDLE_CONTENTS,
            CodecImpl.BUNDLE_CONTENTS
    );
    static final DataComponentTypeImpl<PotionContents, net.minecraft.world.item.alchemy.PotionContents> POTION_CONTENTS = register(
            "potion_contents",
            DataComponents.POTION_CONTENTS,
            Converters.POTION_CONTENTS,
            CodecImpl.POTION_CONTENTS
    );
    static final DataComponentTypeImpl<SuspiciousStewEffects, net.minecraft.world.item.component.SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = register(
            "suspicious_stew_effects",
            DataComponents.SUSPICIOUS_STEW_EFFECTS,
            Converters.SUSPICIOUS_STEW_EFFECTS,
            CodecImpl.SUSPICIOUS_STEW_EFFECTS
    );
    static final DataComponentTypeImpl<WritableBookContent, net.minecraft.world.item.component.WritableBookContent> WRITABLE_BOOK_CONTENT = register(
            "writable_book_content",
            DataComponents.WRITABLE_BOOK_CONTENT,
            Converters.WRITABLE_BOOK_CONTENT,
            CodecImpl.WRITABLE_BOOK_CONTENT
    );
    static final DataComponentTypeImpl<WrittenBookContent, net.minecraft.world.item.component.WrittenBookContent> WRITTEN_BOOK_CONTENT = register(
            "written_book_content",
            DataComponents.WRITTEN_BOOK_CONTENT,
            Converters.WRITTEN_BOOK_CONTENT,
            CodecImpl.WRITTEN_BOOK_CONTENT
    );
    static final DataComponentTypeImpl<BlockItemStateProperties, net.minecraft.world.item.component.BlockItemStateProperties> BLOCK_STATE = register(
            "block_state",
            DataComponents.BLOCK_STATE,
            Converters.BLOCK_STATE,
            CodecImpl.BLOCK_STATE
    );
    static final DataComponentTypeImpl<ArmorTrim, net.minecraft.world.item.armortrim.ArmorTrim> TRIM = register(
            "trim",
            DataComponents.TRIM,
            Converters.TRIM,
            CodecImpl.TRIM
    );
    static final DataComponentTypeImpl<CustomData, net.minecraft.world.item.component.CustomData> ENTITY_DATA = register(
            "entity_data",
            DataComponents.ENTITY_DATA,
            Converters.CUSTOM_DATA,
            CodecImpl.CUSTOM_DATA
    );
    static final DataComponentTypeImpl<CustomData, net.minecraft.world.item.component.CustomData> BUCKET_ENTITY_DATA = register(
            "bucket_entity_data",
            DataComponents.BUCKET_ENTITY_DATA,
            Converters.CUSTOM_DATA,
            CodecImpl.CUSTOM_DATA
    );
    static final DataComponentTypeImpl<CustomData, net.minecraft.world.item.component.CustomData> BLOCK_ENTITY_DATA = register(
            "block_entity_data",
            DataComponents.BLOCK_ENTITY_DATA,
            Converters.CUSTOM_DATA,
            CodecImpl.CUSTOM_DATA
    );
    static final DataComponentTypeImpl<Integer, Integer> OMINOUS_BOTTLE_AMPLIFIER = register(
            "ominous_bottle_amplifier",
            DataComponents.OMINOUS_BOTTLE_AMPLIFIER,
            Converters.INTEGER,
            Codec.INTEGER
    );
    static final DataComponentTypeImpl<List<String>, List<ResourceLocation>> RECIPES = register(
            "recipes",
            DataComponents.RECIPES,
            Converters.RESOURCE_LOCATION.list(),
            CodecImpl.RESOURCE_LOCATION.list()
    );

    private final String key;
    private final DataComponentType<R> type;
    private final Converter<T, R> converter;
    private final Codec<R> codec;
    private final Codec<T> reversed;

    private static <T, R> @NotNull DataComponentTypeImpl<T, R> register(@NotNull String key, DataComponentType<R> type, Converter<T, R> converter, Codec<R> codec) {
        var instance = new DataComponentTypeImpl<>(key, type, converter, codec, codec.map(converter));
        TYPE.put(key, instance);
        return instance;
    }

    @Override
    public @NotNull String key() {
        return key;
    }

    @Override
    public @NotNull Codec<T> codec() {
        return reversed;
    }

    @Override
    public @Nullable T get(@NotNull ItemAdapter adapter) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.get(type);
            return r != null ? converter.asWrapper(r) : null;
        } else throw new IllegalArgumentException("adapter");
    }

    @Override
    public T set(@NotNull ItemAdapter adapter, @Nullable T t) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.set(type, t != null ? converter.asVanilla(t) : null);
            return r != null ? converter.asWrapper(r) : null;
        } else throw new IllegalArgumentException("adapter");
    }
    @Override
    public @Nullable JsonElement getToJson(@NotNull ItemAdapter adapter) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.get(type);
            return r != null ? codec.encode(r) : null;
        } else throw new IllegalArgumentException("adapter");
    }

    @Override
    public @Nullable T setToJson(@NotNull ItemAdapter adapter, @Nullable JsonElement element) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.set(type, element != null ? codec.decode(element) : null);
            return r != null ? converter.asWrapper(r) : null;
        } else throw new IllegalArgumentException("adapter");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DataComponentTypeImpl<?, ?> that = (DataComponentTypeImpl<?, ?>) object;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
