package co.naughtyspirit.wackyracer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import co.naughtyspirit.wackyracer.ui.ImmersiveActivity;
import co.naughtyspirit.wackyracer.ui.LeaderboardAdapter;


public class LeaderboardActivity extends ImmersiveActivity {

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
}
