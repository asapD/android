package com.example.asapd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemInfoFragment extends Fragment {

    private int count = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemInfoFragment() {
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
    public static ItemInfoFragment newInstance(String param1, String param2) {
        ItemInfoFragment fragment = new ItemInfoFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_item_info, container, false);

        Button btn_m = rootView.findViewById(R.id.btn_minus);
        Button btn_p = rootView.findViewById(R.id.btn_plus);
        TextView tvCount =  rootView.findViewById(R.id.count);
        Button btn = rootView.findViewById(R.id.btn_basket);

        tvCount.setText(count+"");

        btn_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 0) {
                    count--;
                    tvCount.setText(count + "");
                }
            }
        });

        btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvCount.setText(count+"");
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                StoreItemFragment storeItemFragment = new StoreItemFragment();
                transaction.replace(R.id.menu_frame_layout, storeItemFragment);
                transaction.commit();
            }
        });
        return rootView;

    }
}
