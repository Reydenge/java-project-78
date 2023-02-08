### Hexlet tests and linter status:
[![Actions Status](https://github.com/Reydenge/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/Reydenge/java-project-78/actions) <a href="https://codeclimate.com/github/Reydenge/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/60784ab84d03bded6f70/maintainability" /></a> <a href="https://codeclimate.com/github/Reydenge/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/60784ab84d03bded6f70/test_coverage" /></a>

**Data validator** - library for checking the correctness of the entered data.

____
### Validator
```java
new Validator();
```
Class to start working with the validator. It is possible to work with such data types as *Integer*, *String* and *Map*.
### String
- required - Any non-empty string
- minLength - The length of the string is equal or greater then the specified number
- contains - The string contains a certain substring
```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
// As long as the required() method is called, null is considered valid
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// is already false, since another contains("whatthe") check has been added
```

### Number
- required - Any number including zero
- positive - Positive Number
- range - The range in which the numbers should fall, including the boundaries
```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

// As long as the required() method is called, null is considered valid
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false
//  Zero is not a positive number
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

### Map
- required - Map data type is required
- sizeof - The number of key-value pairs in the Map object must be equal to the given
```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

### Nested Map validation
- shape - Allows to describe validation for values of Map object by keys
```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```
