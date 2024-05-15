package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import lombok.RequiredArgsConstructor;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.Unit;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AdventureModePredicate;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.saveddata.maps.MapId;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@RequiredArgsConstructor
final class CodecImpl<T> implements kr.toxicity.libraries.datacomponent.api.Codec<T> {

    private static final RegistryOps<JsonElement> OPS = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY).createSerializationContext(JsonOps.INSTANCE);

    static final CodecImpl<Component> COMPONENT = of(Component.class, ComponentSerialization.CODEC, t -> new JsonObject());
    static final CodecImpl<ItemLore> ITEM_LORE = of(ItemLore.class, ItemLore.CODEC, t -> new JsonArray());
    static final CodecImpl<Rarity> RARITY = of(Rarity.class, Rarity.CODEC, t -> new JsonPrimitive(t.name().toLowerCase()));
    static final CodecImpl<Unit> UNIT = of(Unit.class, Codec.unit(Unit.INSTANCE), t -> new JsonPrimitive(true));
    static final CodecImpl<AdventureModePredicate> ADVENTURE_MODE_PREDICATE = of(AdventureModePredicate.class, AdventureModePredicate.CODEC, t -> new JsonObject());
    static final CodecImpl<Tool> TOOL = of(Tool.class, Tool.CODEC, t -> new JsonObject());
    static final CodecImpl<DyedItemColor> DYED_ITEM_COLOR = of(DyedItemColor.class, DyedItemColor.CODEC, t -> new JsonObject());
    static final CodecImpl<MapItemColor> MAP_ITEM_COLOR = of(MapItemColor.class, MapItemColor.CODEC, t -> new JsonPrimitive(t.rgb()));
    static final CodecImpl<MapId> MAP_ID = of(MapId.class, MapId.CODEC, t -> new JsonPrimitive(t.id()));
    static final CodecImpl<BundleContents> BUNDLE_CONTENTS = of(BundleContents.class, BundleContents.CODEC, t -> new JsonArray());
    static final CodecImpl<PotionContents> POTION_CONTENTS = of(PotionContents.class, PotionContents.CODEC, t -> new JsonObject());
    static final CodecImpl<SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = of(SuspiciousStewEffects.class, SuspiciousStewEffects.CODEC, t -> new JsonArray());
    static final CodecImpl<WritableBookContent> WRITABLE_BOOK_CONTENT = of(WritableBookContent.class, WritableBookContent.CODEC, t -> new JsonObject());
    static final CodecImpl<WrittenBookContent> WRITTEN_BOOK_CONTENT = of(WrittenBookContent.class, WrittenBookContent.CODEC, t -> new JsonObject());
    static final CodecImpl<BlockItemStateProperties> BLOCK_STATE = of(BlockItemStateProperties.class, BlockItemStateProperties.CODEC, t -> new JsonObject());
    static final CodecImpl<FoodProperties> FOOD = of(FoodProperties.class, FoodProperties.DIRECT_CODEC, t -> new JsonObject());
    static final CodecImpl<ArmorTrim> TRIM = of(ArmorTrim.class, ArmorTrim.CODEC, t -> new JsonObject());

    private static <T> @NotNull CodecImpl<T> of(@NotNull Class<T> clazz, @NotNull Codec<T> codec, Function<T, JsonElement> function) {
        return new CodecImpl<>(
                clazz,
                codec,
                function
        );
    }

    private final Class<T> clazz;
    private final Codec<T> codec;
    private final Function<T, JsonElement> function;

    @Override
    public @NotNull Class<T> returnType() {
        return clazz;
    }

    @Override
    public @NotNull JsonElement encode(@NotNull T t) {
        var element = function.apply(t);
        return element instanceof JsonPrimitive ? element : codec.encode(t, OPS, element).getOrThrow();
    }

    @Override
    public @NotNull T decode(@NotNull JsonElement t) {
        return codec.decode(OPS, t).getOrThrow().getFirst();
    }
}
