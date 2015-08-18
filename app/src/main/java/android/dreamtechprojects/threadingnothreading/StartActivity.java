package android.dreamtechprojects.threadingnothreading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Sony on 18-08-2015.
 */

// This is a starting Activity.
// User decides to launch one of the two modes -
// 1. Threading 2. No Threading
public class StartActivity extends Activity {

    RadioGroup radioGroup;
    RadioButton radioButtonThreading;
    RadioButton radioButtonNoTreading;
    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout);

        //Initialize radioGroup and button and cast it accordingly
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button = (Button) findViewById(R.id.startActivityButton);
        radioButtonNoTreading = (RadioButton) findViewById(R.id.radioButtonNoThreading);
        radioButtonThreading = (RadioButton) findViewById(R.id.radioButtonThreading);

        // Implement the listener when the Button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the ID of the radio button that is checked
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                // Get the Radio Button reference which is checked
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedRadioButtonId);

                // Invoke corresponding activity based on which Radio button is checked
                if (checkedRadioButton.getId() == radioButtonNoTreading.getId()) {
                    intent = new Intent(getApplicationContext(), NoThreading.class);
                    startActivity(intent);

                } else if (checkedRadioButton.getId() == radioButtonThreading.getId()) {
                    intent = new Intent(getApplicationContext(), Threading.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Select one of the choice and click the button", Toast.LENGTH_LONG).show();

                }
            }
        });


    }
}
