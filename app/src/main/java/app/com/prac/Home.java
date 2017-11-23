package app.com.prac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    ImageButton PermitSwitch;
    Button Logout;
    TextView Username;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        firebaseAuth = FirebaseAuth.getInstance();
        PermitSwitch = (ImageButton) findViewById(R.id.permitSwitch);
        PermitSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(Home.this, PermitFill.class));
            }
        });
        Logout = (Button) findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(Home.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });
        Username = (TextView)findViewById(R.id.username);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Username.setText(user.getEmail());
    }

}