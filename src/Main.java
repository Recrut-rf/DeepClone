import java.util.ArrayList;
import java.util.List;


/**
 * Created by SOTRUDNIK on 02.11.2021.
 */
public class Main {

    public static void main(String args[]) throws NoSuchFieldException {

        // переменные для инициализации объектов Man

        List<String> favoriteBooks = new ArrayList<String>();
        favoriteBooks.add("1");
        favoriteBooks.add("2");
        favoriteBooks.add("3");

        Main main = new Main();

        Man m1 = new Man("m1", 1, favoriteBooks);

        // клонируем
        Man m2 = main.deepCopy(m1);

        System.out.println(m1.toString());
        System.out.println(m2.toString());

        // модифицируем m1
        m1.setAge(0);
        m1.setName("");
        m1.setFavoriteBooks(null);

        // опять выведем оба объекта, чтобы убедиться, что значения у них разные
        System.out.println(m1.toString());
        System.out.println(m2.toString());
    }

    public <T> T deepCopy(T obj) {
        DeepCopyClass<T> deepCopyClass = new DeepCopyClass<>(obj);
        return deepCopyClass.getField();
    }

}
