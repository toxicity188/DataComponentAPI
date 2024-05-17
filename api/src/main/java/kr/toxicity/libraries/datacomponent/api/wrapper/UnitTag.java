package kr.toxicity.libraries.datacomponent.api.wrapper;

public enum UnitTag implements Tag<Unit> {
    INSTANCE
    ;

    @Override
    public Unit value() {
        return Unit.INSTANCE;
    }
}
