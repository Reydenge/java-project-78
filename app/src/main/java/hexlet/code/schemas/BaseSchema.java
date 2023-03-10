package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private LinkedList<Predicate<Object>> conditions = new LinkedList<>();
    protected boolean isRequired;

    public final boolean isValid(Object object) {
        if (!isRequired && isInvalid(object)) {
            return true;
        }
        for (Predicate<Object> predicate : conditions) {
            if (!predicate.test(object)) {
                return false;
            }
        }
        return true;
    }

    public final void addPredicate(Predicate<Object> predicate) {
        conditions.addFirst(predicate);
    }

    abstract boolean isInvalid(Object object);
}
