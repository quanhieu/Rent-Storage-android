package com.example.democrud;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Storage> storageArrayList;
    StorageDAO storageDAO;
    AdapterStorage adapterStorage;
    ListView lvStorage;
    public static int RESQUEST_CODE_UPDATE = 7799;
    public static int RESQUEST_CODE_DELETE = 339;

    private OnFragmentInteractionListener mListener;

    public LoadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoadFragment newInstance(String param1, String param2) {
        LoadFragment fragment = new LoadFragment();
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
        View view = inflater.inflate(R.layout.fragment_load, container, false);
        lvStorage = (ListView) view.findViewById(R.id.lvStorage);
        storageDAO = new StorageDAO(getContext());
        showStorageArrayList();
        registerForContextMenu(lvStorage);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        int id = storageArrayList.get(position).getId();
        switch (item.getItemId()) {
            case R.id.update:
                Intent intent = new Intent(getActivity(), UpdateStorage.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, RESQUEST_CODE_UPDATE);
                break;
            case R.id.delete:
                boolean check = storageDAO.DeleteStorage(id);
                if(check) {
                    showStorageArrayList();
                }
                break;
        }
        return super.onContextItemSelected(item);
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

    public void showStorageArrayList() {
        storageArrayList = storageDAO.GetStorageArrayList();
        adapterStorage = new AdapterStorage(getContext(), R.layout.adapter_item_lvstorage, storageArrayList);
        lvStorage.setAdapter(adapterStorage);
        adapterStorage.notifyDataSetChanged();
    }
}
