package co.naughtyspirit.wackyracer.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import co.naughtyspirit.wackyracer.Car;
import co.naughtyspirit.wackyracer.Motorcycle;
import co.naughtyspirit.wackyracer.R;
import co.naughtyspirit.wackyracer.Spaceship;
import co.naughtyspirit.wackyracer.Vehicle;
import co.naughtyspirit.wackyracer.VehicleType;
import co.naughtyspirit.wackyracer.ui.ImmersiveActivity;
import co.naughtyspirit.wackyracer.utils.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 6/1/15.
 */
public class SelectVehicleActivity extends ImmersiveActivity {


    private int currentVehicleIndex;
    private VehicleType[] vehicleTypes = VehicleType.values();
    private ImageView vehicleImage;

    private TextView vehicleInitialSpeed;
    private TextView vehicleTopSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);

        currentVehicleIndex = 0;
        vehicleImage = (ImageView) findViewById(R.id.vehicle_image);
        vehicleInitialSpeed = (TextView) findViewById(R.id.vehicle_initial_speed);
        vehicleTopSpeed = (TextView) findViewById(R.id.vehicle_top_speed);
        setVehicleImageResource();
        findViewById(R.id.next_vehicle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentVehicleIndex--;
                if (currentVehicleIndex < 0) {
                    currentVehicleIndex = vehicleTypes.length - 1;
                }
                setVehicleImageResource();
            }
        });
        findViewById(R.id.previous_vehicle).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentVehicleIndex++;
                if (currentVehicleIndex >= vehicleTypes.length) {
                    currentVehicleIndex = 0;
                }
                setVehicleImageResource();
            }
        });
        vehicleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectVehicleActivity.this, GameActivity.class);
                intent.putExtra(Constants.PLAYER_NAME_EXTRA, getIntent().getStringExtra(Constants.PLAYER_NAME_EXTRA));
                intent.putExtra(Constants.VEHICLE_TYPE_EXTRA, vehicleTypes[currentVehicleIndex].name());
                startActivity(intent);
            }
        });
    }

    private void setVehicleImageResource() {
        VehicleType vehicleType = vehicleTypes[currentVehicleIndex];
        int vehicleRes = Constants.vehicleTypeToResource.get(vehicleType);
        vehicleImage.setImageResource(vehicleRes);
        onVehicleChanged(vehicleType);
    }

    private void onVehicleChanged(VehicleType vehicleType) {
        Vehicle vehicle;
        switch (vehicleType) {
            case CAR:
                vehicle = new Car(null, null);
                break;
            case MOTORCYCLE:
                vehicle = new Motorcycle(null, null);
                break;
            default:
                vehicle = new Spaceship(null, null);
        }
        vehicleInitialSpeed.setText("Initial speed\n" + vehicle.getInitialSpeed() + " km/h");
        vehicleTopSpeed.setText("Top speed\n" + vehicle.getTopSpeed() + " km/h");
    }
}
