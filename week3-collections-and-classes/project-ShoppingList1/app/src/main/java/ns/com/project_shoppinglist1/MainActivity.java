package ns.com.project_shoppinglist1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView list;
    Button addBtn;

    ShoppingList shoppingList;
    ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        addBtn = (Button) findViewById(R.id.add);
        shoppingList = new ShoppingList();
        shoppingList.initialize();
        if(shoppingList.getItems() == null) {
            shoppingList.shoppingList = new ArrayList<>();
        }

        adapter = new ShoppingListAdapter(this, shoppingList);
        list.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog();
            }

        });
    }

    private void showAddItemDialog() {
        ItemDialog dialog = new ItemDialog(this, adapter);
        dialog.show();
    }


}
