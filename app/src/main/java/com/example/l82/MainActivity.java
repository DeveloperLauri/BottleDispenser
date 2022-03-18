package com.example.l82;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView text, printAmount;
    EditText choiceInput;
    Button submitButton;
    SeekBar seekBar;

    int choice, amount;
    String tmp, returnMoney;

    BottleDispenser bd = BottleDispenser.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //input textview functionality
        choiceInput = (EditText) findViewById(R.id.choiceInput);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view1 -> choice = Integer.valueOf(choiceInput.getText().toString()));

        //seekbar functionality
        printAmount = (TextView) findViewById(R.id.textView3);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                amount = progress;
                printAmount.setText(""+amount);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addMoneySlot(View view){

        bd.addMoney(amount);
        text = (TextView) findViewById(R.id.textView);
        text.setText("Money added: " + amount);
        seekBar.setProgress(0);
    }

    public void cashOut(View view){

        returnMoney = bd.returnMoney();
        text = (TextView) findViewById(R.id.textView);
        text.setText(returnMoney);
    }

    public void buySoda(View view) {

        System.out.println("Testi " + choice);
        tmp = bd.buyBottle(choice);
        text = (TextView) findViewById(R.id.textView);
        text.setText(tmp);
    }

    public void listSodas(View view){
        bd.listBottles();
    }
}