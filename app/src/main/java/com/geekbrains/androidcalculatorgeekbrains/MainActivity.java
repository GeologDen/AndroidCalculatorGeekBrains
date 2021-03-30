package com.geekbrains.androidcalculatorgeekbrains;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView = null;
    ButtonsListener listener = null;

    private final static String keyActions = "Actions";
    Actions actions = new Actions();

    Integer[] buttonResources = new Integer[]
            {
                    R.id.button0,
                    R.id.button1,
                    R.id.button2,
                    R.id.button3,
                    R.id.button4,
                    R.id.button5,
                    R.id.button6,
                    R.id.button7,
                    R.id.button8,
                    R.id.button9,
                    R.id.buttonBackspace,
                    R.id.buttonComma,
                    R.id.buttonDivide,
                    R.id.buttonMinus,
                    R.id.buttonMultiply,
                    R.id.buttonPercent,
                    R.id.buttonPlus,
                    R.id.buttonPositiveNegative,
                    R.id.buttonResetValue,
                    R.id.buttonResult
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        listener = new ButtonsListener();
        for (Integer buttonResource : buttonResources) {
            prepareButton(buttonResource);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(keyActions, actions);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        actions = (Actions) savedInstanceState.getSerializable(keyActions);
        updateTextView(actions.resultString());
    }

    private void prepareButton(Integer resource) {
        Button button = findViewById(resource);
        button.setOnClickListener(listener);
    }

    private void updateTextView(String string) {
        textView.setText(string);
    }

    class ButtonsListener extends Activity implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String buttonName = getButtonName(v);
            if (buttonName == null) return;
            actions.add(buttonName);
            updateTextView(actions.resultString());
            Log.w("123", actions.resultString());
        }

        @Nullable
        private String getButtonName(View view) {
            Button b = (Button)view;
            if (b == null) return null;
            return b.getText().toString();
        }
    }

}