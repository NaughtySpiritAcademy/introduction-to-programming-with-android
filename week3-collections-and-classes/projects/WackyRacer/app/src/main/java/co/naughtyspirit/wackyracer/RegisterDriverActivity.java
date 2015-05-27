package co.naughtyspirit.wackyracer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.naughtyspirit.wackyracer.ui.ImmersiveActivity;


public class RegisterDriverActivity extends ImmersiveActivity implements View.OnClickListener {

    private EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        findViewById(R.id.race_button).setOnClickListener(this);
        playerName = (EditText) findViewById(R.id.player_name);
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
