package com.geekbrains.androidcalculatorgeekbrains;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView resultTextView; // вывод результата
    TextView operationTextView; // вывод знака операции
    EditText numberEditText; // ввод чисел
    Double operand = null;  // операнд
    String lastOperation = "="; // финальная операция


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTextView);
        operationTextView = findViewById(R.id.operationTextView);
        numberEditText = findViewById(R.id.numberEditText);
    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        resultTextView.setText(operand.toString());
        operationTextView.setText(lastOperation);

    }


    public void onNumberClick(View view) {

        Button button = (Button) view;
        numberEditText.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }


    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberEditText.getText().toString();

        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberEditText.setText("");
            }
        }
        lastOperation = op;
        operationTextView.setText(lastOperation);
    }

    @SuppressLint("SetTextI18n")
    private void performOperation(Double number, String operation) {

        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "÷":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "X":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
                case "CHNG":
                    Intent runActivity = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(runActivity);
                    break;

            }
        }
        resultTextView.setText(operand.toString().replace('.', ','));
        numberEditText.setText("");
    }


}