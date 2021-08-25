package hr.ferit.mariozrnic.eucratio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class Sredina extends AppCompatActivity {

    TextView txtSredina;
    ImageView imgSredina;
    Button btnSredina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sredina);

        Intent intent = getIntent();

        ArrayList<Double> rezz = new ArrayList<>();

        rezz =  (ArrayList<Double>) getIntent().getSerializableExtra("list");

        Double finalRez = rezz.get(0)-rezz.get(1);

        txtSredina=findViewById(R.id.tvSredina);
        imgSredina=findViewById(R.id.imgSredina);
        btnSredina=findViewById(R.id.btnSredina);

        txtSredina.setText("Čestitamo!\n\nMukotrpnim kalkulacijama smo zaključili da niste značajno izgubili Vaš novac. No, niste ga ni dobili.\n\nUtvrđena je razlika od\n"+ String.format("%.5f",finalRez) + " EUR.");
    }

    public void goBackSredina(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        boolean instrukcije = false;
        intent.putExtra("instr",instrukcije);
        startActivity(intent);
    }
}