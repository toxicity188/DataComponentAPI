package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import io.papermc.paper.adventure.PaperAdventure;
import kr.toxicity.libraries.datacomponent.api.Converter;
import kr.toxicity.libraries.datacomponent.api.TrimPattern;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import kr.toxicity.libraries.datacomponent.api.wrapper.CompoundTag;
import kr.toxicity.libraries.datacomponent.api.wrapper.Tag;
import net.kyori.adventure.text.Component;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

final class Converters {
    static final Converter<Integer, Integer> INTEGER = Converter.of(i -> i, i -> i);
    static final Converter<Boolean, Boolean> BOOL = Converter.of(b -> b, b -> b);
    static final Converter<Component, net.minecraft.network.chat.Component> COMPONENT = Converter.of(
            PaperAdventure::asVanilla,
            PaperAdventure::asAdventure
    );
    private static final Converter<Tag<?>, net.minecraft.nbt.Tag> TAG = Converter.of(
            t -> {
                var object = t.value();
                if (object instanceof Byte value) return ByteTag.valueOf(value);
                if (object instanceof Short value) return ShortTag.valueOf(value);
                if (object instanceof Integer value) return IntTag.valueOf(value);
                if (object instanceof Long value) return LongTag.valueOf(value);
                if (object instanceof UUID value) return NbtUtils.createUUID(value);
                if (object instanceof Float value) return FloatTag.valueOf(value);
                if (object instanceof Double value) return DoubleTag.valueOf(value);
                if (object instanceof String value) return StringTag.valueOf(value);
                if (object instanceof byte[] value) return new ByteArrayTag(value);
                if (object instanceof int[] value) return new IntArrayTag(value);
                if (object instanceof long[] value) return new LongArrayTag(value);
                throw new UnsupportedOperationException("unsupported type.");
            },
            t -> {
                var getter = new TagValueGetter();
                t.accept(getter);
                var object = getter.object;
                if (object instanceof Byte value) return new Tag<>(Tag.BYTE, value);
                if (object instanceof Short value) return new Tag<>(Tag.SHORT, value);
                if (object instanceof Integer value) return new Tag<>(Tag.INT, value);
                if (object instanceof Long value) return new Tag<>(Tag.LONG, value);
                if (object instanceof UUID value) return new Tag<>(Tag.UUID, value);
                if (object instanceof Float value) return new Tag<>(Tag.FLOAT, value);
                if (object instanceof Double value) return new Tag<>(Tag.DOUBLE, value);
                if (object instanceof String value) return new Tag<>(Tag.STRING, value);
                if (object instanceof byte[] value) return new Tag<>(Tag.BYTE_ARRAY, value);
                if (object instanceof int[] value) return new Tag<>(Tag.INT_ARRAY, value);
                if (object instanceof long[] value) return new Tag<>(Tag.LONG_ARRAY, value);
                throw new UnsupportedOperationException("unsupported type.");
            }
    );

    @SuppressWarnings("all")
    private static class TagValueGetter implements StreamTagVisitor {
        private Object object;
        @Override
        public ValueResult visitEnd() {
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(String value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(byte value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(short value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(int value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(long value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(float value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(double value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(byte[] value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(int[] value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visit(long[] value) {
            object = value;
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visitList(TagType<?> entryType, int length) {
            return ValueResult.BREAK;
        }

        @Override
        public EntryResult visitEntry(TagType<?> type) {
            return EntryResult.BREAK;
        }

        @Override
        public EntryResult visitEntry(TagType<?> type, String key) {
            return EntryResult.BREAK;
        }

        @Override
        public EntryResult visitElement(TagType<?> type, int index) {
            return EntryResult.BREAK;
        }

        @Override
        public ValueResult visitContainerEnd() {
            return ValueResult.BREAK;
        }

        @Override
        public ValueResult visitRootEntry(TagType<?> rootType) {
            return ValueResult.BREAK;
        }
    }

    static final Converter<String, ResourceLocation> RESOURCE_LOCATION = Converter.of(
            s -> new ResourceLocation("minecraft", s),
            ResourceLocation::getPath
    );
    private static final Converter<String, MobEffect> MOB_EFFECT = Converter.of(
            s -> Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.get(RESOURCE_LOCATION.asVanilla(s)), s),
            e -> RESOURCE_LOCATION.asWrapper(Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.getKey(e)))
    );
    private static final Converter<String, Item> ITEM = Converter.of(
            s -> Objects.requireNonNull(BuiltInRegistries.ITEM.get(RESOURCE_LOCATION.asVanilla(s)), s),
            e -> RESOURCE_LOCATION.asWrapper(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(e)))
    );
    private static final Converter<MobEffectInstance, net.minecraft.world.effect.MobEffectInstance> MOB_EFFECT_INSTANCE = MobEffectConverter.INSTANCE;

    private static class MobEffectConverter implements Converter<MobEffectInstance, net.minecraft.world.effect.MobEffectInstance> {

        private static final MobEffectConverter INSTANCE = new MobEffectConverter();

        @NotNull
        @Override
        public net.minecraft.world.effect.MobEffectInstance asVanilla(@NotNull MobEffectInstance mobEffectInstance) {
            var hidden = mobEffectInstance.hiddenEffect();
            return new net.minecraft.world.effect.MobEffectInstance(
                    Holder.direct(MOB_EFFECT.asVanilla(mobEffectInstance.effect())),
                    mobEffectInstance.duration(),
                    mobEffectInstance.amplifier(),
                    mobEffectInstance.ambient(),
                    mobEffectInstance.showParticles(),
                    mobEffectInstance.showIcon(),
                    hidden != null ? INSTANCE.asVanilla(hidden) : null
            );
        }

        @Override
        public @NotNull MobEffectInstance asWrapper(net.minecraft.world.effect.@NotNull MobEffectInstance mobEffectInstance) {
            var hidden = mobEffectInstance.hiddenEffect;
            return new MobEffectInstance(
                    MOB_EFFECT.asWrapper(mobEffectInstance.getEffect().value()),
                    mobEffectInstance.getDuration(),
                    mobEffectInstance.getAmplifier(),
                    mobEffectInstance.isAmbient(),
                    mobEffectInstance.isVisible(),
                    mobEffectInstance.showIcon(),
                    hidden != null ? INSTANCE.asWrapper(hidden) : null
            );
        }
    }

    private static final Converter<String, Block> BLOCK = Converter.of(
            s -> Objects.requireNonNull(BuiltInRegistries.BLOCK.get(RESOURCE_LOCATION.asVanilla(s)), s),
            b -> RESOURCE_LOCATION.asWrapper(BuiltInRegistries.BLOCK.getKey(b))
    );
    private static final Converter<BlockPredicate, net.minecraft.advancements.critereon.BlockPredicate> BLOCK_PREDICATE = Converter.of(
            s -> new net.minecraft.advancements.critereon.BlockPredicate(s.blocks().map(l -> HolderSet.direct(l.stream().map(n -> Holder.direct(BLOCK.asVanilla(n))).toList())), Optional.empty(), Optional.empty()),
            p -> new BlockPredicate(p.blocks().map(h -> h.stream().map(b -> BLOCK.asWrapper(b.value())).toList()))
    );
    private static final Converter<Ingredient, net.minecraft.world.item.crafting.Ingredient> INGREDIENT = Converter.of(
            i -> net.minecraft.world.item.crafting.Ingredient.of(i.entries().map(CraftItemStack::asNMSCopy)),
            i -> new Ingredient(Arrays.stream(i.getItems()).map(net.minecraft.world.item.ItemStack::asBukkitCopy))
    );
    private static final Converter<SoundEvent, net.minecraft.sounds.SoundEvent> SOUND_EVENT = Converter.of(
            s -> net.minecraft.sounds.SoundEvent.createFixedRangeEvent(RESOURCE_LOCATION.asVanilla(s.id()), s.distanceToTravel()),
            s -> new SoundEvent(RESOURCE_LOCATION.asWrapper(s.getLocation()), s.getRange(1F))
    );
    private static final Converter<ArmorMaterial, net.minecraft.world.item.ArmorMaterial> ARMOR_MATERIAL = Converter.of(
            a -> {
                var get = INGREDIENT.asVanilla(a.repairIngredient().get());
                return new net.minecraft.world.item.ArmorMaterial(
                        a.defense().entrySet().stream().collect(Collectors.toMap(e -> switch (e.getKey()) {
                            case HELMET -> ArmorItem.Type.HELMET;
                            case CHESTPLATE -> ArmorItem.Type.CHESTPLATE;
                            case LEGGINGS -> ArmorItem.Type.LEGGINGS;
                            case BOOTS -> ArmorItem.Type.BOOTS;
                            case BODY -> ArmorItem.Type.BODY;
                        }, Map.Entry::getValue)),
                        a.enchantmentValue(),
                        Holder.direct(SOUND_EVENT.asVanilla(a.equipSound())),
                        () -> get,
                        a.layers().stream().map(l -> new net.minecraft.world.item.ArmorMaterial.Layer(RESOURCE_LOCATION.asVanilla(l.id()), l.suffix(), l.dyeable())).toList(),
                        a.toughness(),
                        a.knockbackResistance()
                );
            },
            a -> {
                var assetsName = Arrays.stream(net.minecraft.world.item.ArmorMaterial.Layer.class.getDeclaredFields()).filter(f -> ResourceLocation.class.isAssignableFrom(f.getType())).findFirst().map(f -> {
                    try {
                        f.setAccessible(true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return f;
                }).orElseThrow();
                var suffix = Arrays.stream(net.minecraft.world.item.ArmorMaterial.Layer.class.getDeclaredFields()).filter(f -> String.class.isAssignableFrom(f.getType())).findFirst().map(f -> {
                    try {
                        f.setAccessible(true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return f;
                }).orElseThrow();
                var get = INGREDIENT.asWrapper(a.repairIngredient().get());
                return new ArmorMaterial(
                        a.defense().entrySet().stream().collect(Collectors.toMap(e -> switch (e.getKey()) {
                            case HELMET -> ArmorMaterial.Type.HELMET;
                            case CHESTPLATE -> ArmorMaterial.Type.CHESTPLATE;
                            case LEGGINGS -> ArmorMaterial.Type.LEGGINGS;
                            case BOOTS -> ArmorMaterial.Type.BOOTS;
                            case BODY -> ArmorMaterial.Type.BODY;
                        }, Map.Entry::getValue)),
                        a.enchantmentValue(),
                        SOUND_EVENT.asWrapper(a.equipSound().value()),
                        () -> get,
                        a.layers().stream().map(l -> {
                            try {
                                return new ArmorMaterial.Layer(RESOURCE_LOCATION.asWrapper((ResourceLocation) assetsName.get(l)), (String) suffix.get(l), l.dyeable());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).toList(),
                        a.toughness(),
                        a.knockbackResistance()
                );
            }
    );
    private static final Converter<TrimMaterial, net.minecraft.world.item.armortrim.TrimMaterial> TRIM_MATERIAL = Converter.of(
            t -> new net.minecraft.world.item.armortrim.TrimMaterial(
                    t.assetName(),
                    Holder.direct(ITEM.asVanilla(t.ingredient())),
                    t.itemModelIndex(),
                    t.overrideArmorMaterials().entrySet().stream().collect(Collectors.toMap(e -> Holder.direct(ARMOR_MATERIAL.asVanilla(e.getKey())), Map.Entry::getValue)),
                    COMPONENT.asVanilla(t.description())
            ),
            t -> new TrimMaterial(
                    t.assetName(),
                    ITEM.asWrapper(t.ingredient().value()),
                    t.itemModelIndex(),
                    t.overrideArmorMaterials().entrySet().stream().collect(Collectors.toMap(e -> ARMOR_MATERIAL.asWrapper(e.getKey().value()), Map.Entry::getValue)),
                    COMPONENT.asWrapper(t.description())
            )
    );
    private static final Converter<TrimPattern, net.minecraft.world.item.armortrim.TrimPattern> TRIM_PATTERN = Converter.of(
            t -> new net.minecraft.world.item.armortrim.TrimPattern(
                    RESOURCE_LOCATION.asVanilla(t.assetId()),
                    Holder.direct(ITEM.asVanilla(t.templateItem())),
                    COMPONENT.asVanilla(t.description()),
                    t.decal()
            ),
            t -> new TrimPattern(
                    RESOURCE_LOCATION.asWrapper(t.assetId()),
                    ITEM.asWrapper(t.templateItem().value()),
                    COMPONENT.asWrapper(t.description()),
                    t.decal()
            )
    );

    static final Converter<ItemLore, net.minecraft.world.item.component.ItemLore> ITEM_LORE = Converter.of(
            l -> new net.minecraft.world.item.component.ItemLore(
                    l.lines().stream().map(COMPONENT::asVanilla).toList(),
                    l.styledLines().stream().map(COMPONENT::asVanilla).toList()
            ),
            l -> new ItemLore(
                    l.lines().stream().map(COMPONENT::asWrapper).toList(),
                    l.styledLines().stream().map(COMPONENT::asWrapper).toList()
            )
    );
    static final Converter<Rarity, net.minecraft.world.item.Rarity> RARITY = Converter.of(
            r -> switch (r) {
                case COMMON -> net.minecraft.world.item.Rarity.COMMON;
                case UNCOMMON -> net.minecraft.world.item.Rarity.UNCOMMON;
                case RARE -> net.minecraft.world.item.Rarity.RARE;
                case EPIC -> net.minecraft.world.item.Rarity.EPIC;
            },
            r -> switch (r) {
                case COMMON -> Rarity.COMMON;
                case UNCOMMON -> Rarity.UNCOMMON;
                case RARE -> Rarity.RARE;
                case EPIC -> Rarity.EPIC;
            }
    );
    static final Converter<CustomModelData, net.minecraft.world.item.component.CustomModelData> CUSTOM_MODEL_DATA = Converter.of(
            c -> new net.minecraft.world.item.component.CustomModelData(c.value()),
            c -> new CustomModelData(c.value())
    );
    static final Converter<AdventureModePredicate, net.minecraft.world.item.AdventureModePredicate> ADVENTURE_MODE_PREDICATE = Converter.of(
            a -> new net.minecraft.world.item.AdventureModePredicate(a.predicates().stream().map(BLOCK_PREDICATE::asVanilla).toList(), a.showInTooltip()),
            a -> new AdventureModePredicate(a.predicates.stream().map(BLOCK_PREDICATE::asWrapper).toList(), a.showInTooltip())
    );
    static final Converter<Unit, net.minecraft.util.Unit> UNIT = Converter.of(
            a -> net.minecraft.util.Unit.INSTANCE,
            a -> Unit.INSTANCE
    );
    static final Converter<Tool, net.minecraft.world.item.component.Tool> TOOL = Converter.of(
            t -> new net.minecraft.world.item.component.Tool(
                    t.rules().stream().map(r -> new net.minecraft.world.item.component.Tool.Rule(HolderSet.direct(r.blocks().stream().map(b -> Holder.direct(BLOCK.asVanilla(b))).toList()), r.speed(), r.correctForDrops())).toList(),
                    t.defaultMiningSpeed(),
                    t.damagePerBlock()
            ),
            t -> new Tool(
                    t.rules().stream().map(r -> new Tool.Rule(r.blocks().stream().map(b -> BLOCK.asWrapper(b.value())).toList(), r.speed(), r.correctForDrops())).toList(),
                    t.defaultMiningSpeed(),
                    t.damagePerBlock()
            )
    );
    static final Converter<CompoundTag, net.minecraft.nbt.CompoundTag> COMPOUND_TAG = Converter.of(
            t -> {
                var tag = new net.minecraft.nbt.CompoundTag();
                t.tags().forEach((k, v) -> tag.put(k, TAG.asVanilla(v)));
                return tag;
            },
            t -> {
                var map = new HashMap<String, Tag<?>>();
                t.getAllKeys().forEach(k -> {
                    var get = t.get(k);
                    if (get != null) map.put(k, TAG.asWrapper(get));
                });
                return new CompoundTag(map);
            }
    );
    static final Converter<DyedItemColor, net.minecraft.world.item.component.DyedItemColor> DYED_ITEM_COLOR = Converter.of(
            d -> new net.minecraft.world.item.component.DyedItemColor(d.rgb(), d.showInTooltip()),
            d -> new DyedItemColor(d.rgb(), d.showInTooltip())
    );
    static final Converter<MapItemColor, net.minecraft.world.item.component.MapItemColor> MAP_ITEM_COLOR = Converter.of(
            m -> new net.minecraft.world.item.component.MapItemColor(m.rgb()),
            m -> new MapItemColor(m.rgb())
    );
    static final Converter<MapId, net.minecraft.world.level.saveddata.maps.MapId> MAP_ID = Converter.of(
            m -> new net.minecraft.world.level.saveddata.maps.MapId(m.id()),
            m -> new MapId(m.id())
    );
    static final Converter<BundleContents, net.minecraft.world.item.component.BundleContents> BUNDLE_CONTENTS = Converter.of(
            m -> new net.minecraft.world.item.component.BundleContents(m.itemStacks().stream().map(CraftItemStack::asNMSCopy).toList()),
            m -> {
                var list = new ArrayList<ItemStack>();
                for (net.minecraft.world.item.ItemStack itemStack : m.items()) {
                    list.add(CraftItemStack.asBukkitCopy(itemStack));
                }
                return new BundleContents(list);
            }
    );
    static final Converter<PotionContents, net.minecraft.world.item.alchemy.PotionContents> POTION_CONTENTS = Converter.of(
            p -> new net.minecraft.world.item.alchemy.PotionContents(
                    Optional.empty(),
                    p.customColor(),
                    p.customEffects().stream().map(MOB_EFFECT_INSTANCE::asVanilla).toList()
            ),
            p -> new PotionContents(
                    p.customColor(),
                    p.customEffects().stream().map(MOB_EFFECT_INSTANCE::asWrapper).toList()
            )
    );
    static final Converter<SuspiciousStewEffects, net.minecraft.world.item.component.SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = Converter.of(
            e -> new net.minecraft.world.item.component.SuspiciousStewEffects(
                    e.effects().stream().map(t -> new net.minecraft.world.item.component.SuspiciousStewEffects.Entry(Holder.direct(MOB_EFFECT.asVanilla(t.effect())), t.duration())).toList()
            ),
            e -> new SuspiciousStewEffects(
                    e.effects().stream().map(t -> new SuspiciousStewEffects.Entry(MOB_EFFECT.asWrapper(t.effect().value()), t.duration())).toList()
            )
    );
    static final Converter<WritableBookContent, net.minecraft.world.item.component.WritableBookContent> WRITABLE_BOOK_CONTENT = Converter.of(
            w -> new net.minecraft.world.item.component.WritableBookContent(
                    w.pages().stream().map(f -> new net.minecraft.server.network.Filterable<>(f.raw(), f.filtered())).toList()
            ),
            w -> new WritableBookContent(
                    w.pages().stream().map(f -> new Filterable<>(f.raw(), f.filtered())).toList()
            )
    );
    static final Converter<WrittenBookContent, net.minecraft.world.item.component.WrittenBookContent> WRITTEN_BOOK_CONTENT = Converter.of(
            w -> new net.minecraft.world.item.component.WrittenBookContent(
                    new net.minecraft.server.network.Filterable<>(w.title().raw(), w.title().filtered()),
                    w.author(),
                    w.generation(),
                    w.pages().stream().map(f -> new net.minecraft.server.network.Filterable<>(COMPONENT.asVanilla(f.raw()), f.filtered().map(COMPONENT::asVanilla))).toList(),
                    w.resolved()
            ),
            w -> new WrittenBookContent(
                    new Filterable<>(w.title().raw(), w.title().filtered()),
                    w.author(),
                    w.generation(),
                    w.pages().stream().map(f -> new Filterable<>(COMPONENT.asWrapper(f.raw()), f.filtered().map(COMPONENT::asWrapper))).toList(),
                    w.resolved()
            )
    );
    static final Converter<BlockItemStateProperties, net.minecraft.world.item.component.BlockItemStateProperties> BLOCK_STATE = Converter.of(
            s -> new net.minecraft.world.item.component.BlockItemStateProperties(new HashMap<>(s.properties())),
            s -> new BlockItemStateProperties(new HashMap<>(s.properties()))
    );
    static final Converter<FoodProperties, net.minecraft.world.food.FoodProperties> FOOD = Converter.of(
            f -> new net.minecraft.world.food.FoodProperties(
                    f.nutrition(),
                    f.saturation(),
                    f.canAlwaysEat(),
                    f.eatSeconds(),
                    f.effects().stream().map(e -> new net.minecraft.world.food.FoodProperties.PossibleEffect(MOB_EFFECT_INSTANCE.asVanilla(e.effect()), e.probability())).toList()
            ),
            f -> new FoodProperties(
                    f.nutrition(),
                    f.saturation(),
                    f.canAlwaysEat(),
                    f.eatSeconds(),
                    f.effects().stream().map(e -> new FoodProperties.PossibleEffect(MOB_EFFECT_INSTANCE.asWrapper(e.effect()), e.probability())).toList()
            )
    );
    static final Converter<ArmorTrim, net.minecraft.world.item.armortrim.ArmorTrim> TRIM = Converter.of(
            t -> new net.minecraft.world.item.armortrim.ArmorTrim(
                    Holder.direct(TRIM_MATERIAL.asVanilla(t.material())),
                    Holder.direct(TRIM_PATTERN.asVanilla(t.pattern())),
                    t.showInTooltip()
            ),
            t -> new ArmorTrim(
                    TRIM_MATERIAL.asWrapper(t.material().value()),
                    TRIM_PATTERN.asWrapper(t.pattern().value()),
                    t.showInTooltip
            )
    );

    static final Converter<CustomData, net.minecraft.world.item.component.CustomData> CUSTOM_DATA = Converter.of(
            c -> net.minecraft.world.item.component.CustomData.of(COMPOUND_TAG.asVanilla(c.tag())),
            c -> new CustomData(COMPOUND_TAG.asWrapper(c.copyTag()))
    );
}
