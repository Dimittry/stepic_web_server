package reflection;

public enum Types {
    BYTE,
    BOOLEAN,
    SHORT,
    CHAR,
    INT,
    FLOAT,
    LONG,
    DOUBLE,
    STRING;

    public static Types getType(Class<?> clazz) {
        return Types.valueOf(clazz.getSimpleName().toUpperCase());
    }
}
