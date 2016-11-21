package com.evolutiondso.www.activeandroidorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* To save or insert 1 records (1 object) we just need to call the method save()
       To retrieve a list of items we use new Select().from(Friend.class).execute()
       To delete a record we use methods delete.*/

    EditText et_main_name, et_main_phone;
    Button btn_main_add,btn_main_remove, btn_main_list;
    //private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_main_name = (EditText) findViewById(R.id.et_name);
        et_main_phone = (EditText) findViewById(R.id.et_phone);
        btn_main_add = (Button) findViewById(R.id.btn_add);
        btn_main_remove = (Button) findViewById(R.id.btn_remove);
        btn_main_list = (Button) findViewById(R.id.btn_list);

        et_main_name.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                //Toast.makeText(getApplicationContext(), "Write a Name", Toast.LENGTH_LONG).show();
                et_main_name.setText("");
                return false;
            }
        });
        et_main_phone.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                et_main_phone.setText("");
                //Toast.makeText(getApplicationContext(), "Write a Phone Number", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }



    public void add_friend(View view) {


        String name = et_main_name.getText().toString().trim();
        String phone = et_main_phone.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            et_main_name.requestFocus();
            Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            et_main_phone.requestFocus();
            Toast.makeText(getApplicationContext(), "Please enter a phone!", Toast.LENGTH_SHORT).show();
            return;
        }

        //We Call Our Class Friend and Create an Object.
        Friend friend;
        friend = new Friend();
        //Then We save that Friend in the Actual AADB
        friend.setName(name).setPhone(phone).save();


        et_main_name.setText("");
        et_main_phone.setText("");
        et_main_name.requestFocus();

        Toast.makeText(getApplicationContext(), "You Added a Friend!", Toast.LENGTH_SHORT).show();

    }

    public void gotolist(View view) {
        Intent lists = new Intent(getApplicationContext(), ListaFirends.class);
        startActivity(lists);
    }
}
