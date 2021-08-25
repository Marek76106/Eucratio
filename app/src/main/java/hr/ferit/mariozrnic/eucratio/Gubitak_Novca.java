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

public class Gubitak_Novca extends AppCompatActivity {

    TextView txtSad;
    ImageView imgSad;
    Button btnSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_gubitak_novca);

        Intent intent = getIntent();

        ArrayList<Double> rezz = (ArrayList<Double>) getIntent().getSerializableExtra("list");

        txtSad=findViewById(R.id.tvSad);
        imgSad=findViewById(R.id.imgSad);
        btnSad=findViewById(R.id.btnSad);

        txtSad.setText("Žao nam je!\n\nOvom kupnjom ste izgubili novac!\nIznos od " + rezz.get(1) + " EUR je veći nego da ste platili u kunama.\n\n" + rezz.get(2) + "HRK=" + String.format("%.5f",rezz.get(0)) + "EUR");

    }

    public void goBackSad(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        boolean instrukcije = false;
        intent.putExtra("instr",instrukcije);
        startActivity(intent);
    }
}