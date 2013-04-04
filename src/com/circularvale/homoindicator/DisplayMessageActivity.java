package com.circularvale.homoindicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // Get message from intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message.isEmpty()) {
        	message = "nameless human";
        }
        
        // Set up the layout
        setContentView(R.layout.activity_display_message);
        
        // Add the name to the layout
        TextView textView = (TextView) findViewById(R.id.welcome_text);
        textView.setTextSize(30);
        textView.append(message);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preferences, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.preferences_menu:
            	Intent intent = new Intent(this, SettingsActivity.class);
            	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
