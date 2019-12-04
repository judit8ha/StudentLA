package edu.utep.cs.cs4330.sla;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class profile extends AppCompatActivity {

    protected TextView greetingText;
    TextView date;
    User myUser = new User();

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    String currentDate;
    List<Course> courseList;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        greetingText = findViewById(R.id.greetingText);
        date = findViewById(R.id.dateText);
        currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        //
        date.setText(currentDate);
        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("User");
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        Log.d("onCreate ", "User ID set " + userID);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("firebase", "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d("firebase", "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            String userName;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("on Data Change ", "on data change from On create");
                User u = new User();
                u.setName(Objects.requireNonNull(dataSnapshot.child(userID).child("name").getValue()).toString());
                Log.d("USER", u.getName());
                userName = u.getName();
                myUser.name = userName;
                greetingText.setText("Hello " + myUser.getName() + "!");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        courseList = dbHandler.allItems();

        itemAdapter = new ItemAdapter(this, 0, itemList);

        ListView listView = (ListView) findViewById(R.id.names_list_view);
        listView.setAdapter(itemAdapter);



    }



//    private void loadStudent(){
//        User alec = new User();
//        alec.setName("Alec");
//        alec.setEmail("alec@aol.com");
//
//        User abbie = new User();
//        abbie.setName("Abbie");
//        abbie.setEmail("abbie@aol.com");
//
//    }
    private void showData(DataSnapshot dataSnapshot){
        Log.d("show Data ", "Show data method");
        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            Log.d("SHOW DATA", "datasnapshot children" + ds.child("User"));

            //user.setName(ds.child(userID).child("name").getValue()));
            Log.d("name", myUser.getName());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}



