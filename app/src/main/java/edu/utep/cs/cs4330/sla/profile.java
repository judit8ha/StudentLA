package edu.utep.cs.cs4330.sla;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class profile extends AppCompatActivity {

    protected TextView greetingText;
    TextView date;
    Student student;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private  String userID;
    String currentDate;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        greetingText = findViewById(R.id.greetingText);
        date = findViewById(R.id.dateText);
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        greetingText.setText("Hello " + student.getName());
        date.setText(currentDate);
        //mAuth = FirebaseAuth.getInstance();

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();
//        FirebaseUser user = mAuth.getCurrentUser();
//        userID = user.getUid();
//        Log.d("onCreate ", "User ID set " + userID);
////        mAuthListener = new FirebaseAuth.AuthStateListener() {
////            @Override
////            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
////                FirebaseUser user = firebaseAuth.getCurrentUser();
////                if (user != null) {
////                    // User is signed in
////                    Log.d("firebase", "onAuthStateChanged:signed_in:" + user.getUid());
////                    toastMessage("Successfully signed in with: " + user.getEmail());
////                } else {
////                    // User is signed out
////                    Log.d("firebase", "onAuthStateChanged:signed_out");
////                    toastMessage("Successfully signed out.");
////                }
////                // ...
////            }
////        };
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("on Data Change ", "on data change from On create");
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                //showData(dataSnapshot);
//                student.setName(dataSnapshot.child(userID).child("name").getValue().toString());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        greetingText.setText("Hello " + student.getName());
//        date.setText(currentDate);

    }



    private void loadStudent(){
        Student alec = new Student();
        alec.setName("Alec");
        alec.setEmail("alec@aol.com");

        Student abbie = new Student();
        abbie.setName("Abbie");
        abbie.setEmail("abbie@aol.com");

    }
    private void showData(DataSnapshot dataSnapshot){
        Log.d("show Data ", "Show data method");
        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            Log.d("SHOW DATA", "datasnapshot children" + ds.child("User"));
            student.setName(ds.child(userID).getValue(Student.class).getName());
            Log.d("name",student.getName());
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



