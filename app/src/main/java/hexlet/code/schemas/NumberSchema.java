package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        this.checking = true;
        Predicate<Object> predicateChecking = x -> x instanceof Integer;
        super.addPredicate(predicateChecking);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> predicateNumberSign = x -> x instanceof Integer && (int) x > 0;
        super.addPredicate(predicateNumberSign);
        return this;
    }

    public NumberSchema range(int leftBorder, int rightBorder) {
        Predicate<Object> predicateRange = x -> (int) x >= leftBorder && (int) x <= rightBorder;
        super.addPredicate(predicateRange);
        return this;
    }

    @Override
    boolean isInvalid(Object object) {
        return !(object instanceof Integer);
    }
}
