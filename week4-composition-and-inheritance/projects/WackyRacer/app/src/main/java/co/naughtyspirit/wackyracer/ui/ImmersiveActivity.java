package co.naughtyspirit.wackyracer.ui;

import android.app.Activity;
import android.view.View;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/27/15.
 */
public abstract class ImmersiveActivity extends Activity {

    @Override
    protected void onResume() {
        enableImmersiveMode();
        super.onResume();
    }

    private void enableImmersiveMode() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}
