package com.evolutiondso.www.activeandroidorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class ListaFirends extends AppCompatActivity {

    private ListView list;
    private ArrayList<Friend> friends;
    private ArrayAdapter<Friend> fadapter;
    private List<Friend> ls;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_firends);

        et = (EditText) findViewById(R.id.lf_et_delete);
        friends = new ArrayList<Friend>();

        //Here is where we call the data of the table Friend ActiveAndroid_DB
        ls = new Select().from(Friend.class).execute();
        friends.addAll(ls);


    //Log.d("TAG", "onCreate: " + friends.get(0).getName());
        fadapter =new ArrayAdapter<Friend>(getApplicationContext(),android.R.layout.simple_list_item_1,friends);
        list = (ListView)findViewById(R.id.list_friends);
        list.setAdapter(fadapter);

        et.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                Toast.makeText(getApplicationContext(), "Write a Name to Delete", Toast.LENGTH_LONG).show();
                et.setText("");
                return false;
            }
        });




    }



    public void backmain(View view) {
        Intent begining = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(begining);
    }

    public void deleteFriend(View view) {
        String nom;
        EditText et_name = (EditText) findViewById(R.id.lf_et_delete);
        nom = et_name.getText().toString();
        new Delete()
         .from(Friend.class)
         .where("name = ?", nom)
         .execute();



        friends = new ArrayList<Friend>();
        ls = new Select().from(Friend.class).execute();
        friends.addAll(ls);
        fadapter =new ArrayAdapter<Friend>(getApplicationContext(),android.R.layout.simple_list_item_1,friends);
        list = (ListView)findViewById(R.id.list_friends);
        list.setAdapter(fadapter);
        Toast.makeText(getApplicationContext(), "Friend Deleted!", Toast.LENGTH_SHORT).show();

        et_name.setText("");

//        You can delete the exact position,Friend object by Id and delete it.
//        Friend del = Friend.load(Friend.class, 1);
//        del.delete();
//        Or you can delete it staticall
//            Friend.delete(Friend.class, 1);
//        You can also use the query builder syntax
//            new Delete().from(Friend.class).where("Id = ?", 1).execute();


    }
}
