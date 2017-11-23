package app.com.prac;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;;

public class PermitFill extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    EditText ApplicantName;
    EditText ApplicantTitle;
    EditText ContactNumber;
    EditText Company;
    EditText Address;
    EditText ContactPerson;
    EditText JobTitle;
    EditText RegistrationDate;
    EditText CrewNumber;
    EditText CastNumber;
    EditText StartTime;
    EditText EndTime;
    EditText Location;
    Button SubmitPermit;
    RadioGroup radioGroup;
    RadioButton filmOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_in);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        ApplicantName = (EditText) findViewById(R.id.applicantName);
        ApplicantTitle = (EditText) findViewById(R.id.applicantTitle);
        ContactNumber = (EditText) findViewById(R.id.applicantNumber);
        Company = (EditText) findViewById(R.id.productionCompany);
        Address = (EditText) findViewById(R.id.address);
        ContactPerson = (EditText) findViewById(R.id.contactPerson);
        JobTitle = (EditText) findViewById(R.id.jobTitle);
        RegistrationDate = (EditText) findViewById(R.id.registrationDate);
        CrewNumber = (EditText) findViewById(R.id.crewNumber);
        CastNumber = (EditText) findViewById(R.id.castNumber);
        StartTime = (EditText) findViewById(R.id.startTime);
        EndTime = (EditText) findViewById(R.id.endTime);
        Location = (EditText) findViewById(R.id.location);
        SubmitPermit = (Button)findViewById(R.id.submitPermit);
        SubmitPermit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePermitInfo();
            }
        });
    }

    public void savePermitInfo()
    {
        String Name = ApplicantName.getText().toString().trim();
        String Title = ApplicantTitle.getText().toString().trim();
        String Number = ContactNumber.getText().toString().trim();
        String CompanyName = Company.getText().toString().trim();
        String CompanyAddress = Address.getText().toString().trim();
        String Person = ContactPerson.getText().toString().trim();
        String JbTitle = JobTitle.getText().toString().trim();
        String Date = RegistrationDate.getText().toString().trim();
        String Crew = CrewNumber.getText().toString().trim();
        String Cast = CastNumber.getText().toString().trim();
        String Start = StartTime.getText().toString().trim();
        String End = EndTime.getText().toString().trim();
        String Loc = Location.getText().toString().trim();
        if (TextUtils.isEmpty(Name)||TextUtils.isEmpty(Title)||TextUtils.isEmpty(Number)||TextUtils.isEmpty(CompanyName)||TextUtils.isEmpty(CompanyAddress)
                ||TextUtils.isEmpty(Person)||TextUtils.isEmpty(JbTitle)||TextUtils.isEmpty(Date)||TextUtils.isEmpty(Crew)
                ||TextUtils.isEmpty(Cast)||TextUtils.isEmpty(Start)||TextUtils.isEmpty(Start)||TextUtils.isEmpty(End)||TextUtils.isEmpty(Loc)) {
            Toast.makeText(PermitFill.this, "Please fill in all the fields before submission.", Toast.LENGTH_SHORT).show();
            return;
        }
        PermitInformation permitInformation= new PermitInformation(Name, Title, Number, CompanyName, CompanyAddress, Person, JbTitle, Date, Crew, Cast, Start, End, Loc);
//        try {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child(user.getUid()).push().setValue(permitInformation)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(PermitFill.this, "Permit has successfully been sent.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PermitFill.this, Home.class));
                            }
                            else
                            {
                                Toast.makeText(PermitFill.this, "Please check your network connection and try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//        }
//        catch(Exception e)
//        {
//            Toast.makeText(PermitFill.this, "Please check your internet connection and try again.", Toast.LENGTH_SHORT).show();
//        }
    }
    }