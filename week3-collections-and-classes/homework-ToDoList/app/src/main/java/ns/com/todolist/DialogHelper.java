package ns.com.todolist;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by nmp on 15-5-18.
 */
public class DialogHelper {
    private static DialogHelper dialogHelper;
    private static Context context;

    public static void initialize(Context ctx) {
        context = ctx;
    }

    public static void showErrorDialog(String message) {
        new AlertDialog.Builder(context).setTitle("Error!")
                .setMessage(message)
                .setNeutralButton("OK", null)
                .show();
    }
}
