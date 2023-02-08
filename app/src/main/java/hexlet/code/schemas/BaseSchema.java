package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private List<Predicate<Object>> conditions = new ArrayList<>();
    protected boolean checking;

    public final boolean isValid(Object object) {
        if (!checking && isInvalid(object)) {
            return true;
        }
        for (Predicate<Object> predicate : conditions) {
            if (!predicate.test(object)) {
                return false;
            }
        }
        return true;
    }

    public void addPredicate(Predicate<Object> predicate) {
        conditions.add(predicate);
    }

    abstract boolean isInvalid(Object object);
}
