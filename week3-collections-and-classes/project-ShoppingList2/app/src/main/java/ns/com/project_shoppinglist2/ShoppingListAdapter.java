package ns.com.project_shoppinglist2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ShoppingListAdapter extends BaseAdapter{
    ArrayList<ShoppingItem> items = new ArrayList<>();
    Context context;
    ShoppingList shoppingList;

    public ShoppingListAdapter(Context context, ShoppingList shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
        refreshData();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ShoppingItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }

        final String item = getItem(position).name;
        TextView itemView = (TextView) convertView.findViewById(R.id.item);
        itemView.setText(item);

        TextView numberView = (TextView) convertView.findViewById(R.id.number);
        numberView.setText(position + "");

        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        quantity.setText(getItem(position).quantity + "");


        Button remove = (Button) convertView.findViewById(R.id.remove);
        Button edit = (Button) convertView.findViewById(R.id.edit);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(item);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDialog dialog = new ItemDialog(context, ShoppingListAdapter.this);
                dialog.setTitle("Edit item");
                dialog.itemIndex = position;
                dialog.isEdit = true;
                dialog.itemView.setText(item);
                dialog.oldItem = item;
                dialog.itemQuantityView.setText(shoppingList.getQuantity(item) + "");
                dialog.show();
            }
        });

        return convertView;
    }

    public void removeItem(String item) {
        shoppingList.removeItem(item);
        notifyDataSetChanged();
    }

    public void updateItem(String oldItem, String newItem, int quantity) {
        shoppingList.updateItem(oldItem, newItem, quantity);
        notifyDataSetChanged();
    }


    @Override
    public void notifyDataSetChanged() {
        refreshData();
        super.notifyDataSetChanged();
    }

    private void refreshData() {
        HashMap<String, Integer> map = shoppingList.getItems();
        items.clear();
        for(String key : map.keySet()) {
            items.add(new ShoppingItem(key, map.get(key)));
        }
    }

    public void addItem(String item, int quantity) {
        shoppingList.addItem(item, quantity);
        notifyDataSetChanged();
    }

    class ShoppingItem {
        String name = "";
        int quantity = 1;

        public ShoppingItem(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }
    }
}
