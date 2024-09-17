package kr.toxicity.libraries.datacomponent.nms.v1_21_R1;

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
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
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

    static final CodecImpl<Component> COMPONENT = of(ComponentSerialization.CODEC, t -> t.stream().count() == 1 && t.getStyle().isEmpty() && t.getContents() instanceof PlainTextContents textContents ? new JsonPrimitive(textContents.text()) : new JsonObject());
    static final CodecImpl<ItemLore> LORE = of(ItemLore.CODEC, t -> new JsonArray());
    static final CodecImpl<Rarity> RARITY = of(Rarity.CODEC, t -> new JsonPrimitive(t.name().toLowerCase()));
    static final CodecImpl<Unit> UNIT = of(Codec.unit(Unit.INSTANCE), t -> new JsonPrimitive(true));
    static final CodecImpl<AdventureModePredicate> ADVENTURE_MODE_PREDICATE = of(AdventureModePredicate.CODEC, t -> new JsonObject());
    static final CodecImpl<Tool> TOOL = of(Tool.CODEC, t -> new JsonObject());
    static final CodecImpl<DyedItemColor> DYED_ITEM_COLOR = of(DyedItemColor.CODEC, t -> new JsonObject());
    static final CodecImpl<MapItemColor> MAP_ITEM_COLOR = of(MapItemColor.CODEC, t -> new JsonPrimitive(t.rgb()));
    static final CodecImpl<MapId> MAP_ID = of(MapId.CODEC, t -> new JsonPrimitive(t.id()));
    static final CodecImpl<BundleContents> BUNDLE_CONTENTS = of(BundleContents.CODEC, t -> new JsonArray());
    static final CodecImpl<PotionContents> POTION_CONTENTS = of(PotionContents.CODEC, t -> new JsonObject());
    static final CodecImpl<SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = of(SuspiciousStewEffects.CODEC, t -> new JsonArray());
    static final CodecImpl<WritableBookContent> WRITABLE_BOOK_CONTENT = of(WritableBookContent.CODEC, t -> new JsonObject());
    static final CodecImpl<WrittenBookContent> WRITTEN_BOOK_CONTENT = of(WrittenBookContent.CODEC, t -> new JsonObject());
    static final CodecImpl<BlockItemStateProperties> BLOCK_STATE = of(BlockItemStateProperties.CODEC, t -> new JsonObject());
    static final CodecImpl<FoodProperties> FOOD = of(FoodProperties.DIRECT_CODEC, t -> new JsonObject());
    static final CodecImpl<ArmorTrim> TRIM = of(ArmorTrim.CODEC, t -> new JsonObject());
    static final CodecImpl<CustomModelData> CUSTOM_MODEL_DATA = of(CustomModelData.CODEC, t -> new JsonPrimitive(t.value()));
    static final CodecImpl<CustomData> CUSTOM_DATA = of(CustomData.CODEC, t -> new JsonObject());
    static final CodecImpl<ResourceLocation> RESOURCE_LOCATION = of(ResourceLocation.CODEC, t -> new JsonPrimitive(t.toString()));

    private static <T> @NotNull CodecImpl<T> of(@NotNull Codec<T> codec, Function<T, JsonElement> function) {
        return new CodecImpl<>(
                codec,
                function
        );
    }

    private final Codec<T> codec;
    private final Function<T, JsonElement> function;

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
