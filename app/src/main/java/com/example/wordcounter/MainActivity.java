package com.example.wordcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordcounter.utilities.TextCounter;

public class MainActivity extends AppCompatActivity {

    TextView tvCountResult;
    EditText ecUserInput;
    Spinner spSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvCountResult = findViewById(R.id.tvCountResult);
        this.ecUserInput = findViewById(R.id.edUserInput);
        this.spSelect = findViewById(R.id.spSelect);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.count_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spSelect.setAdapter(adapter);
    }

    public void onBtnCountClick(View view) {
        String userPhrase = this.ecUserInput.getText().toString();

        if (this.spSelect.getSelectedItem().toString().equalsIgnoreCase("characters")) {
            int charsCount = TextCounter.getCharCount(userPhrase);
            this.tvCountResult.setText(String.valueOf(charsCount));
        } else {
            int wordCount = countWords(userPhrase);
            this.tvCountResult.setText(String.valueOf(wordCount));

            Toast.makeText(this, "Word count: " + wordCount, Toast.LENGTH_SHORT).show();
        }
    }

    private int countWords(String text) {
        if (text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
