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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final String key;
    private final DataComponentType<R> type;
    private final Converter<T, R> converter;
    private final Codec<R> codec;

    private static <T, R> @NotNull DataComponentTypeImpl<T, R> register(@NotNull String key, DataComponentType<R> type, Converter<T, R> converter, Codec<R> codec) {
        var instance = new DataComponentTypeImpl<>(key, type, converter, codec);
        TYPE.put(key, instance);
        return instance;
    }

    @Override
    public @NotNull String key() {
        return key;
    }

    @Override
    public @Nullable T get(@NotNull ItemAdapter adapter) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.get(type);
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
    public T set(@NotNull ItemAdapter adapter, @Nullable T t) {
        if (adapter instanceof ItemAdapterImpl itemAdapter) {
            R r = itemAdapter.itemStack.set(type, t != null ? converter.asVanilla(t) : null);
            return r != null ? converter.asWrapper(r) : null;
        } else throw new IllegalArgumentException("adapter");
    }
}
