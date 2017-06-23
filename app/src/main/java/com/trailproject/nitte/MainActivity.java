package com.trailproject.nitte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView mFireBase;
    Firebase mRef;

   //Edit text and Button
    EditText mMessage;
    Button mSend;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mFireBase = (TextView) findViewById(R.id.firebase);
        mMessage = (EditText)findViewById(R.id.message);
        mSend = (Button)findViewById(R.id.send);

        mRef = new Firebase("https://trialproject-d1eb6.firebaseio.com/Name");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Removed

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);

                String message = map.get("Name").toString();
                mFireBase.setText(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = mMessage.getText().toString();

                if(!Message.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Name", Message);
                    map.put("Age", "25");
                    mRef.push().setValue(map);
                }
            }
        });

    }
}
