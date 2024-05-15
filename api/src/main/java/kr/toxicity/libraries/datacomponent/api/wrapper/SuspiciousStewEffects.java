package kr.toxicity.libraries.datacomponent.api.wrapper;

import java.util.List;

public record SuspiciousStewEffects(List<Entry> effects) {
    public record Entry(String effect, int duration) {
    }
}
