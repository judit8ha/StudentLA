//package edu.utep.cs.cs4330.sla;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//public class login   extends AppCompatActivity {
//    FirebaseAuth mAuth;
//    EditText usernameEditText;
//    EditText passwordEditText;
//    Button loginButton;
//    ProgressBar loadingProgressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mAuth = FirebaseAuth.getInstance();
//        usernameEditText = findViewById(R.id.studentid);
//        passwordEditText = findViewById(R.id.password);
//        loginButton = findViewById(R.id.login);
//        loginButton.setEnabled(true);
//    }
//
//    public void onLoginButtonClicked(final View v) {
//        String email = usernameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("firebase login", "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(login.this, "Authentication successful.",
//                                    Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(login.this, profile.class);
//                            Log.d("login", "Intent to start activity created");
//                            startActivity(intent);
//                            //updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("firebase login", "signInWithEmail:failure", task.getException());
//                            Toast.makeText(login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            //updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//    }
//}
