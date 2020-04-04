package com.example.dwis.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et1,et2,et3;
    Button bt1,bt2,bt3,bt4,bt5;
    String name,email;
    int pno;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        bt3=(Button)findViewById(R.id.button3);
        bt4=(Button)findViewById(R.id.button4);
        bt5=(Button)findViewById(R.id.button5);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        MyHelper ob=new MyHelper(this,"dbname",null,1);
        db=ob.getWritableDatabase();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button:
                name=et1.getText().toString().trim();
                email=et2.getText().toString().trim();
                pno=Integer.parseInt(et3.getText().toString());
                String query="insert into user values('"+name+"','"+email+"','"+pno+"')";
                db.execSQL(query);
                Toast.makeText(this,"Record Inserted",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                break;
            case R.id.button2:
                name=et1.getText().toString().trim();
                pno=Integer.parseInt(et3.getText().toString());
                String query2="update user set phoneno='"+pno+"' where name='"+name+"'";
                db.execSQL(query2);
                Toast.makeText(this,"Record Updated",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                break;
            case R.id.button3:
                name=et1.getText().toString().trim();
                String query3="delete from user where name='"+name+"'";
                db.execSQL(query3);
                Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                break;
            case R.id.button4:
                name=et1.getText().toString().trim();
                String query4="select * from user where name='"+name+"'";
                Cursor c;
                c=db.rawQuery(query4,null);
                if(c.moveToNext())
                {
                    String e=c.getString(c.getColumnIndex("email"));
                    String p=c.getString(c.getColumnIndex("phoneno"));
                    et2.setText("Email is : "+e);
                    et3.setText("Age is : "+p);
                }
                else
                {
                    Toast.makeText(this,"No Record Found",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button5:
                et1.setText("");
                et2.setText("");
                et3.setText("");

        }

    }
}
