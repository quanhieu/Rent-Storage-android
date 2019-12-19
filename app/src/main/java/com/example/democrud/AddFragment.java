package com.example.democrud;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText edt_dimensions_in_square_meters, edt_mouhthly_rent_price, edt_name_of_reporter, edt_note;
    private Spinner spinner_storage_type, spinner_storage_feature;
    private Button btnAdd;
    StorageDAO storageDAO;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        edt_dimensions_in_square_meters = (EditText) view.findViewById(R.id.edt_dimensions_in_square_meters);
        edt_mouhthly_rent_price = (EditText) view.findViewById(R.id.edt_mouhthly_rent_price);
        edt_name_of_reporter = (EditText) view.findViewById(R.id.edt_name_of_reporter);
        edt_note = (EditText) view.findViewById(R.id.edt_note);
        spinner_storage_type = (Spinner) view.findViewById(R.id.spinner_storage_type);
        spinner_storage_feature = (Spinner) view.findViewById(R.id.spinner_storage_feature);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        AddDataAndSetAdapterForSpinner();
        storageDAO = new StorageDAO(getContext());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                String sSpinner_storage_type = spinner_storage_type.getSelectedItem().toString();
                String sEdt_dimensions_in_square_meters = edt_dimensions_in_square_meters.getText().toString();
                String sSpinner_storage_feature = spinner_storage_feature.getSelectedItem().toString();
                String sEdt_mouhthly_rent_price = edt_mouhthly_rent_price.getText().toString();
                String sEdt_name_of_reporter = edt_name_of_reporter.getText().toString();
                String sEdt_note = edt_note.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sDatetime = simpleDateFormat.format(calendar.getTime());
                if (sSpinner_storage_type != null && sEdt_dimensions_in_square_meters != null && sSpinner_storage_feature != null && sEdt_mouhthly_rent_price != null && sEdt_name_of_reporter != null
                        && !sSpinner_storage_type.equals("") && !sEdt_dimensions_in_square_meters.equals("") && !sSpinner_storage_feature.equals("") && !sEdt_mouhthly_rent_price.equals("") && !sEdt_name_of_reporter.equals("") ) {
                    Storage storage = new Storage();
                    storage.setStorage_type(sSpinner_storage_type);
                    storage.setDimens_in_square_meters(sEdt_dimensions_in_square_meters);
                    storage.setStorage_feature(sSpinner_storage_feature);
                    storage.setMounthly_rent_price(sEdt_mouhthly_rent_price);
                    storage.setName_of_reporter(sEdt_name_of_reporter);
                    storage.setNote(sEdt_note);
                    storage.setDate_time(sDatetime);
                    storageDAO.AddStorage(storage);
                    Toast.makeText(getContext(), "Add success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Input is required", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: UpdateStorage argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void AddDataAndSetAdapterForSpinner() {
        List<String> list_storage_type = new ArrayList<>();
        list_storage_type.add("Home");
        list_storage_type.add("Business");
        list_storage_type.add("Apartment");
        List<String> list_storage_feature = new ArrayList<>();
        list_storage_feature.add("PRIVATE SPACE");
        list_storage_feature.add("SHARE SPACE");
        list_storage_feature.add("CCTV");
        list_storage_feature.add("AIR CONDITIONING");
        list_storage_feature.add("STORAGE FOR SHORT TERM");
        list_storage_feature.add("STORAGE FOR LONG TERM");
        list_storage_feature.add("COLLECT AND DELIVERY SERVICE");
        ArrayAdapter<String> adapter_storage_type = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list_storage_type);
        adapter_storage_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_storage_type.setAdapter(adapter_storage_type);
        ArrayAdapter<String> adapter_storage_feature = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list_storage_feature);
        adapter_storage_feature.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_storage_feature.setAdapter(adapter_storage_feature);
    }
}
