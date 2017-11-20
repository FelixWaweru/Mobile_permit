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
    Button submit;
    TextView loginLink;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    public void loginUser() {
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        if (TextUtils.isEmpty(emailString)) {
            Toast.makeText(Login.this, "Please enter your Email address.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(Login.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, Home.class));
                            }
                            else if(!task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Your username/password is incorrect.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } catch (Exception e)
        {
            Toast.makeText(Login.this, "Please check your network settings and try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
