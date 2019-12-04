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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    List<Course> courseList = new ArrayList<Course>();
    ItemAdapter itemAdapter;




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);
        //courseList.add(new Course("Art"));

        greetingText = findViewById(R.id.greetingText);
        date = findViewById(R.id.dateText);
        //courseList = new ArrayList<Course>();

        currentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());
        //
        date.setText(currentDate);
        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("User");
        final FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        Log.d("onCreate ", "User ID set " + userID);
        Course math = new Course("Math");
        math.setPeriod(2);
        math.setHomework("no hw ");
        math.setInstructor("Mr Flores");
        courseList.add(math);
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
                Course english = new Course("English");

                english.setPeriod(Integer.parseInt(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("English")
                                .child("period").getValue()).toString()
                ));
                english.setInstructor(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("English")
                                .child("instructor").getValue()).toString()
                );
                english.setHomework(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("English")
                                .child("homework").getValue()).toString()
                );
                myUser.setCourse(english);
                addCourse(english);

//                english.setTools(
//                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("English")
//                                .child("period").getValue()).toString()
//                );
                Course math = new Course("Math");

                math.setPeriod(Integer.parseInt(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("Math")
                                .child("period").getValue()).toString()
                ));
                math.setInstructor(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("Math")
                                .child("instructor").getValue()).toString()
                );
                math.setHomework(
                        Objects.requireNonNull(dataSnapshot.child(userID).child("classes").child("Math")
                                .child("homework").getValue()).toString()
                );
                Log.d("COURSES","math hw: " + math.getHomework());

                myUser.setCourse(math);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //courseList = myUser.getCourses();
        Log.d("courselist", "size "+ courseList.size());


        itemAdapter = new ItemAdapter(this, 0, courseList);

        ListView listView = (ListView) findViewById(R.id.names_list_view);
//       // Log.d("LISTVIEW", ListView.)
        listView.setAdapter(itemAdapter);



    }


    private void addCourse(Course c){
        courseList.add(c);
    }
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



