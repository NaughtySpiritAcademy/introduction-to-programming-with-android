package ns.com.project_shoppinglist1;

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
    public EditText itemNumberView;

    public boolean isEdit = false;
    public int itemIndex = 0;

    ShoppingListAdapter adapter;

    public ItemDialog(Context context, ShoppingListAdapter adapter) {
        super(context);
        this.adapter = adapter;
        setContentView(R.layout.item_dialog);
        setTitle("Add item");

        itemView = (EditText) findViewById(R.id.item);
        itemNumberView = (EditText) findViewById(R.id.item_number);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                String item = itemView.getText().toString();
                if (!isEdit) {
                    if (itemNumberView.getText().toString() != null && !itemNumberView.getText().toString().isEmpty()) {
                        int number = Integer.parseInt(itemNumberView.getText().toString());
                        adapter.addItemAtIndex(number, item);
                    } else {
                        adapter.addItem(item);
                    }
                } else {
                    adapter.setItem(itemIndex, item);
                }
                break;
            case R.id.cancel:
                break;
        }

        dismiss();
    }
}
