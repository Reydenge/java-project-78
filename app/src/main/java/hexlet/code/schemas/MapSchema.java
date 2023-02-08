package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public final MapSchema required() {
        this.checking = true;
        Predicate<Object> predicateChecking = x -> x instanceof Map;
        super.addPredicate(predicateChecking);
        return this;
    }

    public final MapSchema sizeof(int size) {
        Predicate<Object> predicateSize = x -> ((Map<?, ?>) x).size() == size;
        super.addPredicate(predicateSize);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> schema) {
        Predicate<Object> predicateShape = x -> shapeChecking(schema, (Map<?, ?>) x);
        this.addPredicate(predicateShape);
        return this;
    }

    private boolean shapeChecking(Map<String, BaseSchema> schema, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> mapEntry : schema.entrySet()) {
            String key = mapEntry.getKey();
            if (!map.containsKey(key) || !mapEntry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean isInvalid(Object object) {
        return !(object instanceof Map) || (((Map<?, ?>) object).isEmpty());
    }
}
