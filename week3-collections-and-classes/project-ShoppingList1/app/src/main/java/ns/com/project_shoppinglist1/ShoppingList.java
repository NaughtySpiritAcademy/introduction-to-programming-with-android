package ns.com.project_shoppinglist1;

import java.util.ArrayList;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ShoppingList {
    ArrayList<String> shoppingList;

    public void initialize() {
        shoppingList = new ArrayList<>();
        shoppingList.add("Krushi");
        shoppingList.add("Fystyci");
        shoppingList.add("Semki");
    }

    public ArrayList<String> getItems() {
        return shoppingList;
    }

    public void addItem(String item) {
        shoppingList.add(item);
    }

    public void addItemAtIndex(int index, String item) {
        if(index <= shoppingList.size()) {
            shoppingList.add(index, item);
        }
    }

    public void removeItem(int index) {
        shoppingList.remove(index);
    }

    public String getItem(int index) {
        return shoppingList.get(index);
    }

    public void setItem(int index, String item) {
        shoppingList.set(index, item);
    }
}
