package hr.ferit.mariozrnic.eucratio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {



    ListView listView;
    ArrayList<ShopItems> itemList = new ArrayList<>();
    EditText input,input2;
    ImageView enter;
    Button btn;
    ArrayList<Double> results = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        Intent nekiIntent = getIntent();

        boolean checkInst= nekiIntent.getBooleanExtra("instr", true);

        View tutorialView = findViewById(R.id.tutorialView);

        if (!checkInst){
            tutorialView.setVisibility(View.GONE);
        }

        tutorialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);

            }
        });

        listView=findViewById(R.id.listView);
        input=findViewById(R.id.input);
        input2=findViewById(R.id.input2);
        enter=findViewById(R.id.add);
        btn=findViewById(R.id.btnButton);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String name = itemList.get(i).getNaziv();
               makeToast(name);
            }
        });

        ShopItemsAdapter adapter = new ShopItemsAdapter(this,R.layout.adapter_view_layout,itemList);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= input.getText().toString();
                String cijenaHR= input2.getText().toString();
                if(text == null || text.length()==0 || cijenaHR == null || cijenaHR.length()==0){
                    makeToast(getString(R.string.obavijest1));
                }else{
                    addItem(text,cijenaHR);
                    input.setText("");
                    input2.setText("");
                    makeToast("Proizvod " + text + " je dodan.");
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = itemList.get(i).getNaziv();
                makeToast("Proizvod " + name + " je izbrisan.");
                deleteItemFromList(i);
                return false;
            }
        });

    }

    private void deleteItemFromList(int remove) {
        itemList.remove(remove);
        listView.setAdapter(listView.getAdapter());
    }

    public void addItem(String item,String price){
        String ime_predmeta= item;
        Double cijenaHR_predmeta=Double.parseDouble(price);
        ShopItems tempObjekt = new ShopItems(ime_predmeta,cijenaHR_predmeta);
        itemList.add(tempObjekt);
        listView.setAdapter(listView.getAdapter());
   }

    Toast t;

    private void makeToast(String s) {
        if ( t!= null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }

    public void calculateAll(View view) {
        int size = itemList.size();
        Double HRK = 0.0;
        Double EUR1=0.0;
        Double EUR2=0.0;
        String temp="";
        Double temp1=0.0;

        if(size == 0){
            makeToast(getString(R.string.obavijest2));
        }
        else {
            try {
                    for (int i = 0; i < size; i++) {
                        HRK = HRK + itemList.get(i).getCijenaHr();
                        temp1=itemList.get(i).getCijenaEur();
                        temp= String.format("%.2f",temp1);
                        temp = temp.replace(",", ".");
                        EUR2 = EUR2 + Double.parseDouble(temp); //ono sto racuna na zaokruzivanje
                    }
                    EUR1 = HRK / 7.5345;

                results.add(EUR1);
                results.add(EUR2);
                results.add(HRK);
                if(EUR2<EUR1 && EUR1-EUR2>=0.01){
                    Intent intent = new Intent(this,Dobitak_Novca.class);
                    intent.putExtra("list", results);
                    startActivity(intent);
                }else if (EUR1<EUR2 && EUR2-EUR1>=0.01){
                    Intent intent = new Intent(this,Gubitak_Novca.class);
                    intent.putExtra("list",results);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,Sredina.class);
                    intent.putExtra("list",results);
                    startActivity(intent);
                }

            } catch (Exception e) {
                       makeToast(e.getMessage());
            }

        }
    }

}