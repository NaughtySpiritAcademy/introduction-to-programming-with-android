package co.naughtyspirit.wackyracer.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.naughtyspirit.wackyracer.Car;
import co.naughtyspirit.wackyracer.Driver;
import co.naughtyspirit.wackyracer.R;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/27/15.
 */
public class LeaderboardAdapter extends BaseAdapter {
    private final Activity context;
    private final List<Car> cars;

    static class ViewHolder {
        protected TextView playerView;
        protected TextView scoreView;
    }

    public LeaderboardAdapter(Activity context, List<Car> cars) {
        this.context = context;
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car lhs, Car rhs) {
                Driver dl = lhs.getDriver();
                Driver dr = rhs.getDriver();
                if (dl.getPoints() > dr.getPoints()) return -1;
                if (dl.getPoints() == dr.getPoints()) return 0;
                return 1;
            }
        });
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.leaderboard_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.playerView = (TextView) view.findViewById(R.id.leaderboard_player_name);
            viewHolder.scoreView = (TextView) view.findViewById(R.id.leaderboard_player_score);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        Driver driver = cars.get(position).getDriver();
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.playerView.setText(driver.getName());
        holder.scoreView.setText(driver.getPoints() + " pts");
        return view;

    }
}
