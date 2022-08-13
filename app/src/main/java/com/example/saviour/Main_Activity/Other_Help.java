package com.example.saviour.Main_Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.saviour.Main_Activity.Other_help.Others_Helpline;
import com.example.saviour.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Other_Help#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Other_Help extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Other_Help() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Other_Help.
     */
    // TODO: Rename and change types and number of parameters
    public static Other_Help newInstance(String param1, String param2) {
        Other_Help fragment = new Other_Help();
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

        CardView police, medical, fire, women, covid, other;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_other__help, container, false);

        police = v.findViewById(R.id.police);
        medical = v.findViewById(R.id.medical);
        fire = v.findViewById(R.id.fire);
        women = v.findViewById(R.id.women);
        covid = v.findViewById(R.id.covid);
        other = v.findViewById(R.id.others);

        police.setOnClickListener(view -> {
            String number = "100";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        });

        medical.setOnClickListener(view -> {
            //Toast.makeText(requireContext(), "medical", Toast.LENGTH_SHORT).show();
            String number = "108";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);

        });

        fire.setOnClickListener(view -> {
            //Toast.makeText(requireContext(), "fire", Toast.LENGTH_SHORT).show();
            String number = "101";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);

        });

        women.setOnClickListener(view -> {
            // Toast.makeText(requireContext(), "women", Toast.LENGTH_SHORT).show();
            String number = "1090";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);

        });

        covid.setOnClickListener(view -> {
            String number = "1075";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        });

        other.setOnClickListener(view -> {

            Intent intent = new Intent(getContext(), Others_Helpline.class);
            startActivity(intent);

        });

        return v;
    }
}