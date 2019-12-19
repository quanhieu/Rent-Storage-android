package com.example.democrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterStorage extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Storage> storageArrayList;
    ViewHolderStorage viewHolderStorage;

    public AdapterStorage(Context context, int layout, ArrayList<Storage> storageArrayList) {
        this.context = context;
        this.layout = layout;
        this.storageArrayList = storageArrayList;
    }

    @Override
    public int getCount() {
        return storageArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return storageArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return storageArrayList.get(position).getId();
    }

    public class ViewHolderStorage {
        TextView tvId, tvStorage_type, tvDimensions, tvDate_time, tvStorage_feature, tvMounth_rent_price, tvReporter, tvNote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderStorage = new ViewHolderStorage();
            view = inflater.inflate(R.layout.adapter_item_lvstorage, parent, false);
            viewHolderStorage.tvId = view.findViewById(R.id.tvId);
            viewHolderStorage.tvStorage_type = view.findViewById(R.id.tvStorage_type);
            viewHolderStorage.tvDimensions = view.findViewById(R.id.tvDimensions);
            viewHolderStorage.tvDate_time = view.findViewById(R.id.tvDate_time);
            viewHolderStorage.tvStorage_feature = view.findViewById(R.id.tvStorage_feature);
            viewHolderStorage.tvMounth_rent_price = view.findViewById(R.id.tvMounth_rent_price);
            viewHolderStorage.tvReporter = view.findViewById(R.id.tvReporter);
            viewHolderStorage.tvNote = view.findViewById(R.id.tvNote);
            view.setTag(viewHolderStorage);
        } else {
            viewHolderStorage = (ViewHolderStorage) view.getTag();
        }
        Storage storage = storageArrayList.get(position);
        viewHolderStorage.tvId.setText("ID: " + storage.getId());
        viewHolderStorage.tvStorage_type.setText("STORAGE TYPE: " + storage.getStorage_type());
        viewHolderStorage.tvDimensions.setText("DIMENSIONS: " + storage.getDimens_in_square_meters());
        viewHolderStorage.tvDate_time.setText("DATE AND TIME: " + storage.getDate_time());
        viewHolderStorage.tvStorage_feature.setText("STORAGE FEATURE: " + storage.getStorage_feature());
        viewHolderStorage.tvMounth_rent_price.setText("MOUNTHLY RENT PRICE: " + storage.getMounthly_rent_price());
        viewHolderStorage.tvReporter.setText("REPORTER: " + storage.getName_of_reporter());
        viewHolderStorage.tvNote.setText("NOTE: " + storage.getNote());

        return view;
    }
}

