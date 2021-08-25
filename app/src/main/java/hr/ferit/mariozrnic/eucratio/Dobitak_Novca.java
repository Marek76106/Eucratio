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

public class Dobitak_Novca extends AppCompatActivity {

    TextView txtHappy;
    ImageView imgHappy;
    Button btnHappy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_dobitak_novca);

        Intent intent = getIntent();

        ArrayList<Double> rezz = (ArrayList<Double>) getIntent().getSerializableExtra("list");

        txtHappy=findViewById(R.id.tvHappy);
        imgHappy=findViewById(R.id.imgHappy);
        btnHappy=findViewById(R.id.btnHappy);

        txtHappy.setText("Čestitamo!\n\nSvaka čast na pametnoj kupnji!\n Iznos od " + rezz.get(1) + " EUR je manji nego da ste platili u kunama.\n\n" + rezz.get(2) + "HRK=" + String.format("%.5f",rezz.get(0)) + "EUR");




    }

    public void goBackHappy(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        boolean instrukcije = false;
        intent.putExtra("instr",instrukcije);
        startActivity(intent);
    }
}