package ns.com.project_shoppinglist2;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ItemDialog extends Dialog implements View.OnClickListener {
    public EditText itemView;
    public EditText itemQuantityView;

    String oldItem = "";

    public boolean isEdit = false;
    public int itemIndex = 0;

    ShoppingListAdapter adapter;

    public ItemDialog(Context context, ShoppingListAdapter adapter) {
        super(context);
        this.adapter = adapter;
        setContentView(R.layout.item_dialog);
        setTitle("Add item");

        itemView = (EditText) findViewById(R.id.item);
        itemQuantityView = (EditText) findViewById(R.id.item_quantity);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                String item = itemView.getText().toString();
                int quantity = 1;
                if (itemQuantityView.getText().toString() != null && !itemQuantityView.getText().toString().isEmpty()) {
                    quantity = Integer.parseInt(itemQuantityView.getText().toString());
                }
                if (!isEdit) {
                    adapter.addItem(item, quantity);
                } else {
                    adapter.updateItem(oldItem, item, quantity);
                }
                break;
            case R.id.cancel:
                break;
        }

        dismiss();
    }
}
