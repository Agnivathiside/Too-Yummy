package com.example.android.tooyummy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int quantity=0,price=0;
    String Order_summery_str="";
    //EditText name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //name_input=findViewById(R.id.name_input);
    }

    public void increament_quabtity(View view) {
        quantity++;
        display_num(quantity);
    }

    public void decreament_quantity(View view){
        quantity--;
        if (quantity<0){
            quantity=0;
            Context context = getApplicationContext();
            CharSequence text = "Quantity can not be negative !";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            display_num(quantity);
        }
    }

    public void display_num(int score){
        TextView quantity= (TextView) findViewById(R.id.number);
        quantity.setText(String.valueOf(score));
    }

    public int calcualte_price(int quant){
        //return quant*10;
        CheckBox box_whipped_cream= (CheckBox) findViewById(R.id.toppings_check_box);
        CheckBox box_chocolate=(CheckBox) findViewById(R.id.chocolate_toppings);
        if (is_ckecked_whipped_cream(box_whipped_cream).equals("YES") && is_checked_chocolate(box_chocolate).equals("NO")){
            return quant * 15;
        }else if(is_checked_chocolate(box_chocolate).equals("YES") && is_ckecked_whipped_cream(box_whipped_cream).equals("NO")){
            return quant * 20;
        }else if(is_ckecked_whipped_cream(box_whipped_cream).equals("YES") && is_checked_chocolate(box_chocolate).equals("YES")){
            return quant * 25;
        }
        return quant*10;
    }
    public void create_order_summery(View view) {
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        TextView order_summery=(TextView) findViewById(R.id.order_summery);
        CheckBox box_whipped_cream= (CheckBox) findViewById(R.id.toppings_check_box);
        CheckBox box_chocolate=(CheckBox) findViewById(R.id.chocolate_toppings);
        EditText name_input=(EditText) findViewById(R.id.name_input);
        String name=name_input.getText().toString();
        //order_summery+="\n NAME: "+name;
        Order_summery_str+="ORDER SUMMERY";
        Order_summery_str+="\nNAME: "+name;
        Order_summery_str+="\nQuantity: "+quantity;
//        if (box.isChecked()){
//            Order_summery_str+="\nWhipped Cream: Yes";
//        }else {
//            Order_summery_str+="\nWhipped Cream: No";
//        }
        Order_summery_str+="\nWhipped Cream: "+is_ckecked_whipped_cream(box_whipped_cream);
        Order_summery_str+="\nChocolate: "+is_checked_chocolate(box_chocolate);
        Order_summery_str+="\nTotal: $"+calcualte_price(quantity);
        Order_summery_str+="\nThank You!!";

        //Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "TOO YUMMY ORDER FOR "+name);
        intent.putExtra(Intent.EXTRA_TEXT, Order_summery_str);
        //if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        //}

        //order_summery.setText(Order_summery_str);
        Order_summery_str="";
    }

    public String is_ckecked_whipped_cream(CheckBox view) {
        //CheckBox box= (CheckBox) findViewById(R.id.toppings_check_box);
        if (view.isChecked()){
            return "YES";
        }
        return "NO";
    }
    public String is_checked_chocolate(CheckBox view){
        if(view.isChecked()) return "YES";
        return "NO";
    }
}
