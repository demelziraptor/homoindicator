package com.circularvale.homoindicator;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.circularvale.homoindicator.MESSAGE";
	private Spinner spinner_questions, spinner_answers;
	private static List<String> all_answers;
	private static ArrayAdapter<String> dataAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		populateSpinnerQuestions();
		collectAllItemsOnSpinnerAnswers();
		addListenerOnSpinnerItemSelection();
	}

	// add questions dropdown with custom layout
	public void populateSpinnerQuestions() {
		
		spinner_questions = (Spinner) findViewById(R.id.spinner_questions);
		Resources res = this.getResources();
		String[] questions_list = res.getStringArray(R.array.questions);
		
		ArrayAdapter<String> qdataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, questions_list);
		qdataAdapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
		spinner_questions.setAdapter(qdataAdapter);
	}
	
	// add items into list
	public void collectAllItemsOnSpinnerAnswers() {

		spinner_answers = (Spinner) findViewById(R.id.spinner_answers);
		
		Resources res = this.getResources();
		String[] all_answers_from_xml = res.getStringArray(R.array.answers);
		all_answers = new ArrayList<String>();
		for (String answer : all_answers_from_xml)
			all_answers.add(answer);
		
		List<String> answers_list = new ArrayList<String>();
		
		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, answers_list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_answers.setAdapter(dataAdapter);
	}
		
	// add items into spinner dynamically
	public static void addItemsOnSpinnerAnswers(Integer pos) {
		
		List<String> new_answers_list = new ArrayList<String>();
		for (String answer : all_answers)
			if (answer.substring(0, 1).equals(pos.toString())){
				new_answers_list.add(answer.substring(2));
			}

		dataAdapter.clear();
		dataAdapter.addAll(new_answers_list);
		dataAdapter.notifyDataSetChanged();
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner_questions = (Spinner) findViewById(R.id.spinner_questions);
		spinner_questions.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_name);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
}
