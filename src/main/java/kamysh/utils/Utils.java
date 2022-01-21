package kamysh.utils;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    @SneakyThrows
    public static Object runGetter(String field, Object o) throws IllegalAccessException, InvocationTargetException {
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.toLowerCase())) {
                    return method.invoke(o);

                }
            }
        }
        return null;
    }

}
