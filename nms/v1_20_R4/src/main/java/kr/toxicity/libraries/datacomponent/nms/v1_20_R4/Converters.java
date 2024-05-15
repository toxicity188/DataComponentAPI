package kr.toxicity.libraries.datacomponent.nms.v1_20_R4;

import io.papermc.paper.adventure.PaperAdventure;
import kr.toxicity.libraries.datacomponent.api.Converter;
import kr.toxicity.libraries.datacomponent.api.wrapper.*;
import net.kyori.adventure.text.Component;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

final class Converters {
    private static final Converter<String, ResourceLocation> RESOURCE_LOCATION = Converter.of(
            s -> new ResourceLocation("minecraft", s),
            ResourceLocation::getPath
    );
    private static final Converter<String, MobEffect> MOB_EFFECT = Converter.of(
            s -> Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.get(RESOURCE_LOCATION.asVanilla(s)), s),
            e -> RESOURCE_LOCATION.asWrapper(Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.getKey(e)))
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

    static final Converter<Integer, Integer> INTEGER = Converter.of(i -> i, i -> i);
    static final Converter<Boolean, Boolean> BOOL = Converter.of(b -> b, b -> b);
    static final Converter<Component, net.minecraft.network.chat.Component> COMPONENT = Converter.of(
            PaperAdventure::asVanilla,
            PaperAdventure::asAdventure
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
}
