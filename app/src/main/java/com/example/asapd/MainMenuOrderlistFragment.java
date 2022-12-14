package com.example.asapd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuOrderlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuOrderlistFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerItemAdapter mRecyclerAdapter;
    private ArrayList<ItemData> mList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuOrderlistFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuOrderlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuOrderlistFragment newInstance(String param1, String param2) {
        MainMenuOrderlistFragment fragment = new MainMenuOrderlistFragment();
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_orderlist, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mList = ItemData.createList(5);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerAdapter = new RecyclerItemAdapter(getActivity(),mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;

    }
}