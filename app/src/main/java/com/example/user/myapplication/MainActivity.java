package com.example.user.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    Boolean newNumber = true;
    Boolean rep = true;
    String inpExpression;
    String beautifulString;
    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button delButton = (Button) findViewById(R.id.delete_button);
        final Button divButton = (Button) findViewById(R.id.div_button);
        final Button mulButton = (Button) findViewById(R.id.mul_button);
        final Button subButton = (Button) findViewById(R.id.sub_button);
        final Button addButton = (Button) findViewById(R.id.add_button);
        final Button eqlButton = (Button) findViewById(R.id.equal_button);
        final Button num0 = (Button) findViewById(R.id.num_0_button);
        final Button num1 = (Button) findViewById(R.id.num_1_button);
        final Button num2 = (Button) findViewById(R.id.num_2_button);
        final Button num3 = (Button) findViewById(R.id.num_3_button);
        final Button num4 = (Button) findViewById(R.id.num_4_button);
        final Button num5 = (Button) findViewById(R.id.num_5_button);
        final Button num6 = (Button) findViewById(R.id.num_6_button);
        final Button num7 = (Button) findViewById(R.id.num_7_button);
        final Button num8 = (Button) findViewById(R.id.num_8_button);
        final Button num9 = (Button) findViewById(R.id.num_9_button);
        final Button decimal = (Button) findViewById(R.id.decimal_button);
        final TextView inputExpression = (TextView) findViewById(R.id.text_view_input);
        final TextView outputValue = (TextView) findViewById(R.id.text_view_output);


        //setting max length of input textView
        inputExpression.setMaxLines(2);

        //setting the value of input and output strings so it would not be NULL
        inpExpression = inputExpression.getText().toString();
        output = outputValue.getText().toString();

        //equal button is pressed
        //set input equal to output then set output value to empty

        eqlButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(output.equals("")) return;

                newNumber = true;
                inpExpression = output;
                output = "";
//                decUsed = false; //to be checked on the output value

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        //just append '.' if there is no other decimal in the number
        decimal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(newNumber){
                    inpExpression = "";
                    inpExpression += '.';
                    newNumber = false;
                    return;
                }

                boolean used = false;
                int ln = inpExpression.length();
                char c;

                for(int i=0; i<ln; i++){
                    c = inpExpression.charAt(i);
                    if(c == '.'){
                        used = true;
                    }
                    else if(c >= '*' && c <= '/'){
                        used = false;
                    }
                }

                if(used == false){
                    inpExpression += '.';
                    output = Double.toString(calculateValue());
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        //similer function call on click for all button exp('=', '.'):
        //check if input is empty
        //update the decUsed and opUsed variables
        //check validity of remaining expression
        //calculate the expression value output the result

        delButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(inpExpression.equals("Infinity")){
                    inpExpression = "";
                    beautify();
                    inputExpression.setText(beautifulString);
                    outputValue.setText(output);
                    return;
                }

                if (inpExpression != null && inpExpression.length() > 0) {
                    inpExpression = inpExpression.substring(0, inputExpression.length()-1);
                }

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        //Long press of del will clear Both the input and the output values

        delButton.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                inpExpression = "";
                output = "";

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
                return true;
            }
        });

        divButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '/';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });


        mulButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '*';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '+';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        subButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '-';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });


        num0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '0';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '1';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                inputExpression.setText(inpExpression);
                outputValue.setText(output);
            }
        });

        num2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '2';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '3';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '4';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '5';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '6';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '7';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '8';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

        num9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                char c = '9';

                addNewElement(c);

                if(checkValidity()){
                    output = Double.toString(calculateValue());
                }
                else{
                    output = "";
                }

                beautify();
                inputExpression.setText(beautifulString);
                outputValue.setText(output);
            }
        });

    }

//
//    // Only used in del function, when last element is sure to be remove
//    // this function update the number of operator used or if the decimal is used
//
//    void removeLast(){
//        char c = inpExpression.charAt(inpExpression.length()-1);
//
//        if(c == '.'){
//            decUsed = false;
//        }
//        else if( !(c >= '0' && c <= '9') ){
//            opUsed--;
//        }
//    }

    //checks if the given expression is valid to be sent for producing output values

    public boolean checkValidity(){
        if(inpExpression.length() != 0 &&
            (inpExpression.charAt(inpExpression.length()-1) == '.' ||
            (inpExpression.charAt(inpExpression.length()-1) >= '0' && inpExpression.charAt(inpExpression.length()-1) <= '9' ) ) ){
            return true;
        }
        else return false;
    }


//    use stack to store the values, store the operands and pop only when there is '*' or '/' at the top
//    finally pop all element from the stack any get the Final value of the expression
    public double calculateValue(){
        double ret = 1.0, operand1, operand2;
        Stack<String> stk = new Stack<String> ();
        char c;
        int ln = inpExpression.length(), lst = 0;
        String op = "", num2, cur_operator;
        boolean numActive = false;

        for(int i=0; i<ln; i++){
            c = inpExpression.charAt(i);

            if((c >= '*' && c <= '/' && c != '.') && numActive){
                num2 = inpExpression.substring(lst, i);
                op = inpExpression.substring(i, i+1);
                if(num2.equals(".")) num2 += '0';
                operand2 = Double.valueOf(num2);

                if(!stk.empty() && (stk.peek().equals("*") || stk.peek().equals("/"))){
                    cur_operator = stk.peek();
                    stk.pop();

                    if(cur_operator.equals("/")){
                        operand1 = Double.valueOf(stk.peek());
                        stk.pop();
                        operand2 = operand1/operand2;
                    }
                    else if(cur_operator.equals("*")){
                        operand1 = Double.valueOf(stk.peek());
                        stk.pop();
                        operand2 = operand1 * operand2;
                    }
                }

                stk.push(Double.toString(operand2));
                stk.push(op);
                lst = i+1;
                numActive = false;
            }
            else numActive = true;


            //last operand input
            if(i == ln-1){
                num2 = inpExpression.substring(lst, i+1);
                if(num2.equals(".")) num2 += '0';
                operand2 = Double.valueOf(num2);

                if(!stk.empty() && (stk.peek().equals("*") || stk.peek().equals("/"))){
                    cur_operator = stk.peek();
                    stk.pop();

                    if(cur_operator.equals("/")){
                        operand1 = Double.valueOf(stk.peek());
                        stk.pop();
                        operand2 = operand1/operand2;
                    }
                    else if(cur_operator.equals("*")){
                        operand1 = Double.valueOf(stk.peek());
                        stk.pop();
                        operand2 = operand1 * operand2;
                    }
                }

                stk.push(Double.toString(operand2));
            }

        }

        ret = Double.valueOf(stk.peek());
        stk.pop();

        while(!stk.empty()){
            op = stk.peek();
            stk.pop();

            if(op.equals("+")){
                operand1 = Double.valueOf(stk.peek());
                stk.pop();
                ret += operand1;
            }
            else{
                operand1 = Double.valueOf(stk.peek());
                stk.pop();
                ret = operand1 - ret;
            }
        }

//        BigDecimal b = new BigDecimal(ret, MathContext.DECIMAL32);
//        b.setScale(6, RoundingMode.FLOOR);

        return ret;
    }

    //single function to add the new element for all the button
    //0. append any number or decimal(decimal case is being handled in decimal function)
    //1. (_, num) append the element
    //2. ('-', op) do noting
    //3. (op-{'-'}, '-') append
    //4. (op, op-{'-'}) replace
    public void addNewElement(char c){
        if(inpExpression.equals("Infinity") || inpExpression.equals("NaN")){
            if((c >= '0' && c <= '9') || c == '.' || c == '-'){
                inpExpression = "";
                inpExpression += c;
                newNumber = false;
            }
            return;
        }
        if(newNumber == true && ((c >= '0' && c <= '9') || c == '.' || c == '-')){
            inpExpression = "";
            inpExpression += c;
            newNumber = false;
            return;
        }
        newNumber = false;
        if(inpExpression.length() == 0){
            if( (c >= '0' && c <= '9') || c == '.' || c == '-'){
                inpExpression += c;
            }
            return ;
        }

        char plst = NULL;
        if(inpExpression.length() > 1){
            plst = inpExpression.charAt(inpExpression.length()-2);
        }
        char lst = inpExpression.charAt(inpExpression.length() - 1);

        if((c >= '0' && c <= '9') || lst == '.' || (lst >= '0' && lst <= '9')){
            inpExpression += c;
        }
        else if(lst != '-' && c == '-'){
                inpExpression += c;
        }
        else if(lst=='-' && (plst != NULL && plst >= '*' && plst <= '/') && (c >= '*' && c <= '/')){
            //doNothing
        }
        else if(inpExpression.length() > 1){
            inpExpression = inpExpression.substring(0, inpExpression.length()-1) + c;
        }
    }


    public void beautify(){
        beautifulString = "";
        char c;
        int ln = inpExpression.length();

        for(int i=0; i<ln; i++){
            c = inpExpression.charAt(i);
            if(c == '/'){
                beautifulString += "\u00F7";
            }
            else if(c == '*'){
                beautifulString += "\u00D7";
            }
            else beautifulString += c;
        }
    }

}


