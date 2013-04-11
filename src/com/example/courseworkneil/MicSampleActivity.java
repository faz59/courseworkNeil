package com.example.courseworkneil;

import com.ubhave.dataformatter.csv.CSVFormatter;
import com.ubhave.sensormanager.ESException;
import com.ubhave.sensormanager.ESSensorManager;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.sensors.SensorUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MicSampleActivity extends Activity {
	View currentView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mic_sample);
	   	findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					currentView = v;
					ESSensorManager esSensorManager = null;
					SensorData noise = null;
					try {
						esSensorManager = ESSensorManager.getSensorManager(MicSampleActivity.this.getApplicationContext());
						noise = esSensorManager.getDataFromSensor(SensorUtils.SENSOR_TYPE_MICROPHONE);
					} catch (ESException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CSVFormatter formatter = CSVFormatter.getCSVFormatter(SensorUtils.SENSOR_TYPE_MICROPHONE);
					String csv = formatter.toString(noise);
					alert("cool", csv);
				}
			});
	}
	
	public void alert(String title, String message)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(currentView.getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				dialog.dismiss();
			}
		});
        builder.setCancelable(false);
        final AlertDialog myAlertDialog = builder.create();
        myAlertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mic_sample, menu);
		return true;
	}
}
