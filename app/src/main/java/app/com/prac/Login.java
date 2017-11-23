package app.com.prac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button loginSubmit;
    TextView logging;
    TextView signUp;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText)findViewById(R.id.loginPassword);
        loginSubmit = (Button)findViewById(R.id.loginSubmit);
        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        logging = (TextView) findViewById(R.id.loggingin);
        signUp = (TextView) findViewById(R.id.regLink);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }
//This is the method that is used to login the user
    public void loginUser() {
        //This captures the username and password from the page
        String emailStringLogin = email.getText().toString();
        String passwordStringLogin = password.getText().toString();
        if (TextUtils.isEmpty(emailStringLogin)) {
            Toast.makeText(Login.this, "Please enter your Email address.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordStringLogin)) {
            Toast.makeText(Login.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        //This makes use of firebase Authenticaation to login
        try {
            logging.setText("Logging in...");
            firebaseAuth.signInWithEmailAndPassword(emailStringLogin, passwordStringLogin)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, Home.class));
                            }
                            else
                            {
                                logging.setText("");
                                password.setText("");
                                Toast.makeText(Login.this, "Your username/password is incorrect.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } catch (Exception e)
        {
            logging.setText("");
            Toast.makeText(Login.this, "Please check your network settings and try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
