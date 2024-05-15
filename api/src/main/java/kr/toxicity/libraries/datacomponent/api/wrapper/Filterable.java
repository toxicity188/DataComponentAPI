package kr.toxicity.libraries.datacomponent.api.wrapper;

import java.util.Optional;

public record Filterable<T>(T raw, Optional<T> filtered) {
}
