package co.naughtyspirit.sunny;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherDetailsActivity extends AppCompatActivity {
    public static final String TAG = WeatherDetailsActivity.class.getSimpleName();

    private List<String> locations = new ArrayList<String>();
    private String location;
    private int temperature;

    private TextView tvTemperature, tvTemperatureWarning;
    private ImageView ivWeatherIcon;
    private Button btnShare;
    private Button btnShareWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        Log.d(TAG, getIntent().getStringExtra(Constants.KEY_LOCATION));
        initLocations();

        location = getIntent().getStringExtra(Constants.KEY_LOCATION);
        if(TextUtils.isEmpty(location)) {
            Toast.makeText(this, "Location cannot be empty!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if(!locations.contains(location)) {
            Toast.makeText(this, "Location is not supported!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initUI();
        initTemperature();
    }

    private void initLocations() {
        locations.add("Plovdiv");
        locations.add("Sofia");
        locations.add("Burgas");
    }

    private void initUI() {
        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
        tvTemperatureWarning = (TextView) findViewById(R.id.tv_temperature_warning);
        ivWeatherIcon = (ImageView) findViewById(R.id.iv_weather);
        btnShare = (Button) findViewById(R.id.btn_share);
        btnShareWith = (Button) findViewById(R.id.btn_share_with);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getTempMessage());
                startActivity(Intent.createChooser(intent, "Send using:"));

                //if(isAvailable(this, intent)) ...
            }
        });

        btnShareWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
            }
        });
    }

    @NonNull
    private String getTempMessage() {
        return "Hey, did you know that today's weather temp for " + location + " will be " + temperature + "!";
    }

    private void initTemperature() {
        temperature = new Random().nextInt(50);
        tvTemperature.setText(temperature + "");
        tvTemperatureWarning.setText("Today's temperature for " + location + " is ");
        if(temperature < 15) {
            tvTemperature.setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
            ivWeatherIcon.setImageResource(R.drawable.ic_cold);
        } else if(temperature >= 15 && temperature < 30) {
            tvTemperature.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            ivWeatherIcon.setImageResource(R.drawable.ic_sunny);
        } else {
            tvTemperature.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
            ivWeatherIcon.setImageResource(R.drawable.ic_hot);
        }
    }

    private boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private boolean isAvailable(Intent intent) {
        return intent.resolveActivity(getPackageManager()) != null;
    }

    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, Constants.PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == Constants.PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {Phone.NUMBER};
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(Phone.NUMBER);
                String number = cursor.getString(column);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"
                        + number));
                intent.putExtra("address", number);
                intent.setType("vnd.android-dir/mms-sms");
                intent.putExtra("sms_body", getTempMessage());
                startActivity(intent);

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
