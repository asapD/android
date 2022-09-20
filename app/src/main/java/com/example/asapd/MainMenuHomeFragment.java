package com.example.asapd;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuHomeFragment extends Fragment {

//    private Button btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_toQRPage;
    private View inflatedView = null;

    private Button btn_fruit, btn_vegetable, btn_fish, btn_meat;
    private Button btn_book, btn_life, btn_convenience_store, btn_stationary;

    public MainMenuHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuHomeFragment newInstance(String param1, String param2) {
        MainMenuHomeFragment fragment = new MainMenuHomeFragment();
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
        inflatedView = inflater.inflate(R.layout.fragment_main_menu_home, container, false);
        btn_toQRPage = inflatedView.findViewById(R.id.btn_toQRPage);

        Fragment subFragment = new StoreListFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.child_menu_frame_layout, subFragment).commit();

        btn_toQRPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QRCodeActivity.class);
                startActivity(intent);
            }
        });

        btn_convenience_store = inflatedView.findViewById(R.id.btn_convenience_store);

        btn_convenience_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 편의점 카테고리를 갖는 가게들을 보여주는 fragment로 전환
                ((HomePageActivity)getActivity()).replaceFragment(StoreListFragment.newInstance("",""));
            }
        });


        return inflatedView;
    }



}