package hr.ferit.mariozrnic.eucratio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;




public class ShopItemsAdapter extends ArrayAdapter<ShopItems> {

    private Context mContext;
    int mResource;

    public ShopItemsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ShopItems> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String naziv = getItem(position).getNaziv();
        Double cijenaHr=getItem(position).getCijenaHr();
        Double cijenaEur=getItem(position).getCijenaEur();

        ShopItems sItem = new ShopItems(naziv,cijenaHr);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvNaziv = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvcijenaHRK = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvcijenaEUR = (TextView) convertView.findViewById(R.id.textView3);

        tvNaziv.setText(naziv);
        tvcijenaHRK.setText("HRK:"+ String.format("%.2f", cijenaHr));
        tvcijenaEUR.setText("EUR:"+ String.format("%.2f", cijenaEur));

        return convertView;
    }
}
