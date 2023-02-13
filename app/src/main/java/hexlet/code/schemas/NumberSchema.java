package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public final NumberSchema required() {
        this.isRequired = true;
        Predicate<Object> predicateIsRequired = x -> x instanceof Integer;
        super.addPredicate(predicateIsRequired);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> predicateNumberSign = x -> (int) x > 0;
        super.addPredicate(predicateNumberSign);
        return this;
    }

    public final NumberSchema range(int leftBorder, int rightBorder) {
        Predicate<Object> predicateRange = x -> (int) x >= leftBorder && (int) x <= rightBorder;
        super.addPredicate(predicateRange);
        return this;
    }

    @Override
    public final boolean isInvalid(Object object) {
        return !(object instanceof Integer);
    }
}
