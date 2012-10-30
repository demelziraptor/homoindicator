package com.circularvale.homoindicator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.circularvale.homoindicator.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
				EditText editText = (EditText) findViewById(R.id.edit_name);
				String message = editText.getText().toString();
				intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
    }
}
