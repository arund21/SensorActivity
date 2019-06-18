package e.sensoractivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SensorsDataActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView tvGyroSensorData, etProxiSensor, etLightSensor;
    private EditText etFirstNumber, etSecondNumber;
    private Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_data);
        setTitle("Sensors Data");
        tvGyroSensorData = findViewById(R.id.tvGyroSensorData);
        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        etProxiSensor = findViewById(R.id.etProxiSensor);
        etLightSensor = findViewById(R.id.etLightSensor);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFirstNumber.getText().toString())) {
                    etFirstNumber.setError("Please Enter First Number");
                    return;
                }
                if (TextUtils.isEmpty(etSecondNumber.getText().toString())) {
                    etSecondNumber.setError("Please Enter Second Number");
                    return;
                }
                else {
                    sensorGyro();
                    proximity();
                    lightInstance();
                }
            }
        });
    }
    private  void sensorGyro(){
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            SensorEventListener gyrolistener = new SensorEventListener() {

                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[1] < 0) {
                        Double Result = Double.parseDouble(etFirstNumber.getText().toString()) + Double.parseDouble(etSecondNumber.getText().toString());
                        tvGyroSensorData.setText(Double.toString(Result));
                    } else if (event.values[1] > 0) {

                        Double Result = Double.parseDouble(etFirstNumber.getText().toString()) - Double.parseDouble(etSecondNumber.getText().toString());
                        tvGyroSensorData.setText(Double.toString(Result));
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }

            };

            if (sensor != null) {
                sensorManager.registerListener(gyrolistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Toast.makeText(this, "No Gyro Sensor Found", Toast.LENGTH_LONG).show();
            }


    }

    private void proximity(){

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <=4){
                    Double Result = Double.parseDouble(etFirstNumber.getText().toString()) + Double.parseDouble(etSecondNumber.getText().toString());
                    etProxiSensor.setText(Double.toString(Result));
                }
                else {
                    Double Result = Double.parseDouble(etFirstNumber.getText().toString()) - Double.parseDouble(etSecondNumber.getText().toString());
                    etProxiSensor.setText(Double.toString(Result));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No Gyro Sensor Found", Toast.LENGTH_LONG).show();
        }
    }

    private void lightInstance(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        SensorEventListener lightsensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <=10){
                    Double Result = Double.parseDouble(etFirstNumber.getText().toString()) + Double.parseDouble(etSecondNumber.getText().toString());
                    etProxiSensor.setText(Double.toString(Result));
                }
                else {
                    Double Result = Double.parseDouble(etFirstNumber.getText().toString()) - Double.parseDouble(etSecondNumber.getText().toString());
                    etProxiSensor.setText(Double.toString(Result));
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(lightsensor, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No Light Sensor Found", Toast.LENGTH_LONG).show();
        }
    }
}
