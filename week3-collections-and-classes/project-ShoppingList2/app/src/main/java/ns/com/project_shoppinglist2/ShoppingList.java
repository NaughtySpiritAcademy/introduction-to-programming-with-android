package ns.com.project_shoppinglist2;

import java.util.HashMap;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ShoppingList {
    HashMap<String, Integer> shoppingList;

    public void initialize() {
        shoppingList = new HashMap<>();
        shoppingList.put("Krushi", 3);
        shoppingList.put("Fystyci", 6);
        shoppingList.put("Semki", 2);
    }

    public HashMap<String, Integer> getItems() {
        return shoppingList;
    }

    public void addItem(String item, int quantity) {
        shoppingList.put(item, quantity);
    }

    public void removeItem(String item) {
        shoppingList.remove(item);
    }

    public int getQuantity(String item) {
        return shoppingList.get(item);
    }

    public void updateItem(String oldItem, String newItem, int quantity) {
        shoppingList.remove(oldItem);
        shoppingList.put(newItem, quantity);
    }
}
