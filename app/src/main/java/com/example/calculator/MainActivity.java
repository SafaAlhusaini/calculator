package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity {


//initialize the variables

    TextView formula , result;
    //MartialButton
    Button btn_C, btn_eql,btn_point;
    Button btn_D, btn_P,btn_S,btn_M;
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formula = findViewById(R.id.some_id);
        result = findViewById(R.id.some_id2);

        assigndID(btn_eql,R.id.rec_2);
        assigndID(btn_C,R.id.ellipse_15);
        assigndID(btn_point,R.id.ellipse_13);
        //----------------------------------------
        assigndID(btn_D,R.id.rec_1);
        assigndID(btn_M,R.id.ellipse_8);
        assigndID(btn_P,R.id.ellipse_14);
        assigndID(btn_S,R.id.ellipse_12);
        //--------------------------------------
        assigndID(btn_0,R.id.rec_1);
        assigndID(btn_1,R.id.ellipse_1);
        assigndID(btn_2,R.id.ellipse_2);
        assigndID(btn_3,R.id.ellipse_3);
        assigndID(btn_4,R.id.ellipse_5);
        assigndID(btn_5,R.id.ellipse_6);
        assigndID(btn_6,R.id.ellipse_7);
        assigndID(btn_7,R.id.ellipse_9);
        assigndID(btn_8,R.id.ellipse_10);
        assigndID(btn_9,R.id.ellipse_11);

    }


    void assigndID( Button btn , int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this::OnClickNumbers);
    }


    public void OnClickNumbers(View view)
    {
        Button button = (Button) view;
        String btntext = button.getText().toString();
        String formulaToCal = formula.getText().toString();

        if(btntext.equals("C"))
        {
            formulaToCal = formulaToCal.substring(0,formulaToCal.length()-1);
        } 
       else if (btntext.equals("="))
        {
            formula.setText(result.getText());
            return;

        }
        else
        {
            formulaToCal += btntext; // formulaToCal = formulaToCal + btntext --> concat
        }
       formula.setText(formulaToCal);

        String finalResult = calformula(formulaToCal);
        if(!finalResult.equals("Err")){
            result.setText(finalResult);
        }
    }

    String calformula(String f){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,f,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}


