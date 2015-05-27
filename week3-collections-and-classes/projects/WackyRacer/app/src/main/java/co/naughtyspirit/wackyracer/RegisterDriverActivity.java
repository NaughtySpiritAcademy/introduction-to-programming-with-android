package co.naughtyspirit.wackyracer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterDriverActivity extends Activity implements View.OnClickListener {

    private EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        findViewById(R.id.race_button).setOnClickListener(this);
        playerName = (EditText) findViewById(R.id.player_name);
    }

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.race_button) {
            String player = playerName.getText().toString().trim();
            if (TextUtils.isEmpty(player)) {
                Toast.makeText(this, getString(R.string.player_name_validation_text), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(Constants.PLAYER_NAME_EXTRA, player);
            startActivity(intent);
        }
    }
}
