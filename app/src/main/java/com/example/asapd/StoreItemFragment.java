package com.example.asapd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreItemFragment extends Fragment {


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

    public StoreItemFragment(){
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
    public static StoreItemFragment newInstance(String param1, String param2) {
        StoreItemFragment fragment = new StoreItemFragment();
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
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_store_itemlist, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mList = ItemData.createList(5);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerAdapter = new RecyclerItemAdapter(getActivity(),mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(new RecyclerStoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int pos) {
                Bundle bundle = new Bundle();
                bundle.putInt("resId", mList.get(pos).getResId());
                bundle.putString("name", mList.get(pos).getTitle());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               ItemInfoFragment itemInfoFragment = new ItemInfoFragment();
                transaction.replace(R.id.menu_frame_layout, itemInfoFragment);
                transaction.commit();
            }
        });


        TextView store_name = rootView.findViewById(R.id.store_name);
        if(getArguments() != null)
        {
            String name = getArguments().getString("title");
            store_name.setText(name);
        }
        return rootView;

    }
}