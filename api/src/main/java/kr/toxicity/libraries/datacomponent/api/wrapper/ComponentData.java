package kr.toxicity.libraries.datacomponent.api.wrapper;

import kr.toxicity.libraries.datacomponent.api.Codec;

public interface ComponentData<T> {
    Codec<T> codec();
}
