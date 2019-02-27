package hkust.comp4521.courseinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class welcome extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        
        // Find the reference to the Welcome screen by using its ID
        LinearLayout screen = (LinearLayout) findViewById(R.id.WelcomeScreen);
        
        // Create an OnClick listener so that when the user touches the screen (clicks) the Menu activity is created
        screen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Create the Activity corresponding to the Menu screen
				startActivity(new Intent(welcome.this,menu.class));
				
			}
		});

    }
}