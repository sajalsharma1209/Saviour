package com.example.saviour.Main_Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.saviour.Main_Activity.Menu_Add_Members.Add_Number;
import com.example.saviour.Main_Activity.Menu_Edit_Sos_Message.Edit_Sos_Message;
import com.example.saviour.Main_Activity.Menu_Rate_Us.Rate_Us;
import com.example.saviour.Main_Activity.Menu_View_Members.View_Members;
import com.example.saviour.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
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
        CardView card1, card2, card3, card4, card5, card6;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        card1 = v.findViewById(R.id.instruction);
        card2 = v.findViewById(R.id.add_member);
        card3 = v.findViewById(R.id.editmessage);
        card4 = v.findViewById(R.id.view_members);
        card5 = v.findViewById(R.id.rating);
        card6 = v.findViewById(R.id.about);

        card1.setOnClickListener(v1 -> {
//            Intent intent = new Intent(Menu.this.getActivity(), instructions.class);
//            Menu.this.startActivity(intent);
            instructiondialogBox();

        });

        card2.setOnClickListener(v2 -> {
            Intent intent = new Intent(getActivity(), Add_Number.class);
            startActivity(intent);
        });
        card3.setOnClickListener(v3 ->
        {
            Intent intent = new Intent(Menu.this.getActivity(), Edit_Sos_Message.class);
            Menu.this.startActivity(intent);
        });
        card4.setOnClickListener(v4 -> {
            Intent intent = new Intent(Menu.this.getActivity(), View_Members.class);
            Menu.this.startActivity(intent);
        });
        card5.setOnClickListener(c6 -> {
            Intent intent = new Intent(Menu.this.getActivity(), Rate_Us.class);
            Menu.this.startActivity(intent);
        });
        card6.setOnClickListener(view -> aboutUsDialogBox());
        return v;
    }

    private void aboutUsDialogBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = requireActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_box_about_us, viewGroup, false);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        buttonOk.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();

    }

    private void instructiondialogBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        ViewGroup viewGroup = requireActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_box_instruction, viewGroup, false);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        buttonOk.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.show();

    }
}