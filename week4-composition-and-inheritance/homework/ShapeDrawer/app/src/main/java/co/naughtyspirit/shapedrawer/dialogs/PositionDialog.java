package co.naughtyspirit.shapedrawer.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import co.naughtyspirit.shapedrawer.R;

public class PositionDialog extends Dialog implements View.OnClickListener {
    public EditText itemNumberView;

    public int itemIndex = 0;
    OnPositionSelectedListener listener;

    public PositionDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_position);
        setTitle("Enter shape position");

        itemNumberView = (EditText) findViewById(R.id.item_number);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(listener == null)
            return;

        switch (view.getId()) {
            case R.id.ok:
                if (itemNumberView.getText().toString() != null && !itemNumberView.getText().toString().isEmpty()) {
                    int number = Integer.parseInt(itemNumberView.getText().toString());
                    listener.onPositionSelected(number);
                } else {
                    listener.onPositionSelected(0);
                }
                break;
            case R.id.cancel:
                listener.onPositionSelected(-1);
                break;
        }

        dismiss();
    }

    public void setOnPositionSelectedListener(OnPositionSelectedListener listener) {
        this.listener = listener;
    }

    public void removeOnPositionSelectedListener(OnPositionSelectedListener listener) {
        this.listener = null;
    }

    public interface OnPositionSelectedListener {
        void onPositionSelected(int position);
    }
}