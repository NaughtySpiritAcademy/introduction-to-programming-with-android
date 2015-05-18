package ns.com.project_shoppinglist1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ShoppingListAdapter extends BaseAdapter{
    ArrayList<String> items = new ArrayList<>();
    Context context;
    ShoppingList shoppingList;

    public ShoppingListAdapter(Context context, ShoppingList shoppingList) {
        this.context = context;
        this.items = shoppingList.getItems();
        if(items == null) {
            items = new ArrayList<>();
        }
        this.shoppingList = shoppingList;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int i) {
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

        final String item = getItem(position);
        TextView itemView = (TextView) convertView.findViewById(R.id.item);
        itemView.setText(item);
        TextView numberView = (TextView) convertView.findViewById(R.id.number);
        numberView.setText(position + "");

        Button remove = (Button) convertView.findViewById(R.id.remove);
        Button edit = (Button) convertView.findViewById(R.id.edit);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDialog dialog = new ItemDialog(context, ShoppingListAdapter.this);
                dialog.setTitle("Edit item");
                dialog.itemIndex = position;
                dialog.isEdit = true;
                dialog.itemView.setText(shoppingList.getItem(position));
                dialog.itemNumberView.setVisibility(View.INVISIBLE);
                dialog.show();
            }
        });

        return convertView;
    }

    public void addItem(String item) {
        shoppingList.addItem(item);
        notifyDataSetChanged();
    }

    public void addItemAtIndex(int index, String item) {
        shoppingList.addItemAtIndex(index, item);
        notifyDataSetChanged();
    }

    public void removeItem(int index) {
        shoppingList.removeItem(index);
        notifyDataSetChanged();
    }

    public void setItem(int itemIndex, String item) {
        shoppingList.setItem(itemIndex, item);
        notifyDataSetChanged();
    }
}
