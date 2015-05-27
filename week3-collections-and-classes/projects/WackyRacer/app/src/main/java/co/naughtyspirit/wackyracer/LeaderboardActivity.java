package co.naughtyspirit.wackyracer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import co.naughtyspirit.wackyracer.ui.LeaderboardAdapter;


public class LeaderboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        TextView numberOfPlayers = (TextView) findViewById(R.id.number_of_players);
        int n = getIntent().getIntExtra(Constants.NUMBER_OF_PLAYERS_EXTRA, 0);
        numberOfPlayers.setText(getString(R.string.number_of_players, n));

        List<Car> cars = ((WackyRacerApp) getApplication()).getCars();
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, cars);
        ListView leaderboard = (ListView) findViewById(R.id.leaderboard);
        leaderboard.setAdapter(adapter);

        findViewById(R.id.new_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, RegisterDriverActivity.class);
                startActivity(intent);
            }
        });
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
}
