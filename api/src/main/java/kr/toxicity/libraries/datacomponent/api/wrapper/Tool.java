package kr.toxicity.libraries.datacomponent.api.wrapper;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record Tool(@NotNull List<Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
    public record Rule(@NotNull List<String> blocks, @NotNull Optional<Float> speed, @NotNull Optional<Boolean> correctForDrops) {
    }
}
