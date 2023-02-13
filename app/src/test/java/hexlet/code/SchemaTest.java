package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaTest {
    @Test
    public void testStringSchema() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();
        String testFirstString = "what does the fox say";
        String testSecondString = "moon";
        int testInteger = 29;
        int minLength = 5;

        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertTrue(stringSchema.isValid(testFirstString));
        assertTrue(stringSchema.isValid(testSecondString));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(testInteger));

        assertTrue(stringSchema.minLength(minLength).isValid(testFirstString));
        assertFalse(stringSchema.minLength(minLength).isValid(testSecondString));


        stringSchema.contains("wh");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("what");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("whatthe");
        assertFalse(stringSchema.isValid(testFirstString));

        assertFalse(stringSchema.isValid(testFirstString));
    }
    @Test
    public void testNumberSchema() {
        Validator validator = new Validator();
        NumberSchema numberSchema = validator.number();
        int testFirstPositiveNumber = 5;
        int testSecondPositiveNumber = 10;
        int testNegativeNumber = -10;

        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.positive().isValid(null));

        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(testFirstPositiveNumber));
        assertFalse(numberSchema.isValid(testNegativeNumber));
        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid("0"));

        numberSchema.range(testFirstPositiveNumber, testSecondPositiveNumber);

        assertTrue(numberSchema.isValid(testFirstPositiveNumber));
        assertTrue(numberSchema.isValid(testSecondPositiveNumber));
        assertFalse(numberSchema.isValid(0));
        assertFalse(numberSchema.isValid(11));
    }
    @Test
    public void testMapSchema() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");

        assertTrue(mapSchema.isValid(null));

        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(testMap));

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(testMap));
        testMap.put("key2", "value2");
        assertTrue(mapSchema.isValid(testMap));
    }
    @Test
    public void testMapShape() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();
        Map<String, BaseSchema> mapSchemas = new HashMap<>();
        mapSchemas.put("name", validator.string().required());
        mapSchemas.put("age", validator.number().positive());
        mapSchema.shape(mapSchemas);

        Map<String, Object> testFirstMap = new HashMap<>();
        testFirstMap.put("name", "Kolya");
        testFirstMap.put("age", 18);
        assertTrue(mapSchema.isValid(testFirstMap));

        Map<String, Object> testSecondMap = new HashMap<>();
        testSecondMap.put("name", "Maya");
        testSecondMap.put("age", null);
        assertTrue(mapSchema.isValid(testSecondMap));

        Map<String, Object> testThirdMap = new HashMap<>();
        testThirdMap.put("name", "");
        testThirdMap.put("age", null);
        assertFalse(mapSchema.isValid(testThirdMap));

        Map<String, Object> testForthMap = new HashMap<>();
        testForthMap.put("name", "Valya");
        testForthMap.put("age", -5);
        assertFalse(mapSchema.isValid(testForthMap));
    }
}
