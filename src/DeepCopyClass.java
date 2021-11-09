import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;


public class DeepCopyClass<T> {

    private T field;

    DeepCopyClass(T items) {

        Class itemsClass = items.getClass();

        // создаём переменные, которые далее будем использовать
        // для создания объекта
        String str = "";
        int val = 0;
        List<String> listString = null;

        Field[] publicFields = itemsClass.getDeclaredFields();
        for (Field field : publicFields) {
            field.setAccessible(true);
            Class fieldType = field.getType();

            // в зависимости от типа поля
            // присваиваем значение переменной
            if(fieldType.getName() == "java.lang.String") {
                try {
                    str = (String)field.get(items);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(fieldType.getName() == "int")
                try {
                    val = (int)field.get(items);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            if(fieldType.getName() == "java.util.List")
                try {
                    listString = (List<String>) field.get(items);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

        }

        Constructor[] constructors = itemsClass.getConstructors();

        // создаём копию объекта
        try {
            field = (T)constructors[0].newInstance(str, val, listString);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public T getField() {
        return field;
    }
}
