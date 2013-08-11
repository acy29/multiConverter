package com.example.multiconverter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MassActivity extends Activity {

	double mass1 = 1000000;
	double mass2 = 1000000; // 1000000mg = 1km smallest mass

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mass_main);

		final EditText data1 = (EditText) findViewById(R.id.data1);
		final EditText data2 = (EditText) findViewById(R.id.data2);

		final RadioGroup rgMass1 = (RadioGroup) findViewById(R.id.mass1);
		final RadioGroup rgMass2 = (RadioGroup) findViewById(R.id.mass2);

		final ImageView swap = (ImageView) findViewById(R.id.swap);

		// event listener on data 1
		data1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!data1.getText().toString().equals("")) {
					double aux = (mass1 / mass2)
							* Double.parseDouble(data1.getText().toString());
					data2.setText(Double.toString(aux));
				} else {
					data2.setText("");
				}

			}
		});

		// event listener on RadioGroup 1
		rgMass1
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						double aux = process(checkedId, 1);
						if (!data1.getText().toString().equals("")) {
							aux *= Double.parseDouble(data1.getText()
									.toString());
							data2.setText(Double.toString(aux));
						}
					}
				});

		// event listener on RadioGroup 2
		rgMass2
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						double aux = process(checkedId, 2);
						if (!data1.getText().toString().equals("")) {
							aux *= Double.parseDouble(data1.getText()
									.toString());
							data2.setText(Double.toString(aux));
						}

					}
				});

		// swap the data1 wich data2
		swap.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String aux = "" + data2.getText();
				data2.setText("" + data1.getText());
				data1.setText(aux);

				// get the checked radio button rg1
				int radioButtonID1 = rgMass1.getCheckedRadioButtonId();
				// get the checked radio button rg2
				int radioButtonID2 = rgMass2.getCheckedRadioButtonId();

				// get the rb of rg1 checked of rg 2
				RadioButton rg1_radioButton_checked = (RadioButton) rgMass1
						.findViewById(radioButtonID2);
				// get the rb of rg2 checked of rg 1
				RadioButton rg2_radioButton_checked = (RadioButton) rgMass2
						.findViewById(radioButtonID1);

				rg1_radioButton_checked.setChecked(true);
				rg2_radioButton_checked.setChecked(true);

			}
		});

	}

	//update the mass in mg
	private double process(int checkedId, int rg) {

		double mass = 1;

		switch (checkedId) {
		case R.id.kilogram:
			mass = 1000000;
			break;
		case R.id.pound:
			mass = 453.59237;
			break;
		case R.id.gram:
			mass = 1000;
			break;
		case R.id.milligram:
			mass = 1;
			break;
		case R.id.ounce:
			mass = 28000.3495231;
			break;
		default:
			break;
		}

		if (rg == 1) {
			mass1 = mass;
		} else {
			mass2 = mass;
		}

		return mass1 / mass2;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// listen menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.length:
	    	Intent intent = new Intent(MassActivity.this, MainActivity.class);
	    	startActivity(intent);
	        return true;
	    case R.id.temperature:
	    	intent = new Intent(MassActivity.this, TemperatureActivity.class);
	    	startActivity(intent);
	        return true;
	    case R.id.mass:
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

}
