package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public final StringSchema required() {
        this.checking = true;
        Predicate<Object> predicateRequired = x -> x instanceof String && !x.equals("");
        super.addPredicate(predicateRequired);
        return this;
    }

    public final StringSchema contains(String str) {
        Predicate<Object> containedInPredicate = x -> x.toString().contains(str);
        super.addPredicate(containedInPredicate);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        Predicate<Object> predicateLength = x -> x.toString().length() >= minLength;
        super.addPredicate(predicateLength);
        return this;
    }

    @Override
    public final boolean isInvalid(Object object) {
        return !(object instanceof String) || ((String) object).isEmpty();
    }
}
