package com.example.work06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity  implements recyclerViewInterface{
    Button btn;
    ToggleButton tb;
    EditText et1, et2;
    TextView tv1, tv2;
    RecyclerView recyclerView;
    myAdapter myAdapter1;
    String[] numbers = new String[20];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        btn = findViewById(R.id.button);
        et1 = findViewById(R.id.editTextNumberSigned);
        et2 = findViewById(R.id.editTextNumberSigned2);
        tv1 = findViewById(R.id.textView6);
        tv2 = findViewById(R.id.textView9);
        tb = findViewById(R.id.toggleButton);
        myAdapter1 = new myAdapter(getApplicationContext(), numbers, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("") || et2.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please select",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(isNumeric(et1.getText().toString()) == false || isNumeric(et2.getText().toString()) == false)
                {
                    Toast.makeText(getApplicationContext(),"Wrong Input",Toast.LENGTH_SHORT).show();
                }
                else {
                    tv1.setText("");
                    tv2.setText("");
                    numbers = getArray();
                    myAdapter1.items = numbers;
                    recyclerView.setAdapter(myAdapter1);
                    myAdapter1.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        tv1.setText(Integer.toString(position));
        double mul;
        double sum;
        if(tb.isChecked() == false)
        {
            sum = ((position + 1) * (Double.valueOf(numbers[position]) + Double.parseDouble(numbers[0]))) / 2;
            tv2.setText(Double.toString(sum));
        }
        else
        {
            mul = Double.parseDouble(numbers[1])  / Double.parseDouble(numbers[0]);
            sum = ((Double.parseDouble(numbers[0]) * (Math.pow(mul, position+1) - 1)) / (mul-1));
            tv2.setText(Double.toString(sum));
        }
    }
    public String[] getArray()
    {
        NumberFormat numFormat = new DecimalFormat();
        numFormat = new DecimalFormat("0.###E0");
        String arr[] = new String[20];
        double num = 0, first = 0;
        String v;
        String firstNum = et1.getText().toString();
        String mul = et2.getText().toString();
        arr[0] = firstNum;
        first = Double.parseDouble(firstNum);
        if (tb.isChecked() == false) {
            for (int i = 1; i < 20; i++) {
                num = first + (i * Double.valueOf(mul));
                if(num <= 0.000009)
                    arr[i] =  numFormat.format(num);
                else
                    arr[i] = String.format("%.4f" ,num);
            }
            return arr;
        }
        else {
            for (int i = 1; i < 20; i++) {
                num = first * (Math.pow(Double.valueOf(mul), i));
                if(num <= 0.0009 && num > 0 || num >= -0.0009 && num < 0)
                    arr[i] =  numFormat.format(num);
                else
                    arr[i] = String.format("%.4f" ,num);
            }
            return arr;
        }
    }


    public static boolean isNumeric(String toCheck)
    {
        try{ //checking if in the command below theres a problem, like turning a / or .. to double number
            Double.parseDouble(toCheck);
            return true;
        } catch (NumberFormatException e) { // if the try found a problem he will come to here and return false
            return false;
        }
    }


}

