package com.vortex.vortex.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vortex.vortex.R;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KliningCalculation9Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KliningCalculation9Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KliningCalculation9Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KliningCalculation9Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KliningCalculation9Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KliningCalculation9Fragment newInstance(String param1, String param2) {
        KliningCalculation9Fragment fragment = new KliningCalculation9Fragment();
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
        View view = inflater.inflate(R.layout.fragment_klining_calculation9, container, false);

        EditText etPricePerVolume = view.findViewById(R.id.etPricePerVolume);
        EditText etWeightOfProductInContainer = view.findViewById(R.id.etWeightOfProductInContainer);
        EditText etExpense = view.findViewById(R.id.etExpense);
        EditText etRoom = view.findViewById(R.id.etRoom);

        TextView tvThePricePerKg = view.findViewById(R.id.tvThePricePerKg);
        TextView tvTheCostOfProcessingFacilities = view.findViewById(R.id.tvTheCostOfProcessingFacilities);

        Button button = view.findViewById(R.id.btnCalculation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPricePerVolume.getText().length() == 0 || etWeightOfProductInContainer.getText().length() == 0 || etExpense.getText().length() == 0|| etRoom.getText().length() == 0) {
                    Toast.makeText(getContext(), "Заполните пожалуйста все данные", Toast.LENGTH_SHORT).show();
                    return;
                }

                TableLayout tb = view.findViewById(R.id.tableL);
                tb.setVisibility(View.VISIBLE);
                int gray = Color.parseColor("#7B7979");
                button.setBackgroundColor(gray);

                double pricePerVolume = Double.valueOf(etPricePerVolume.getText().toString());
                double weightOfProductInContainer = Double.valueOf(etWeightOfProductInContainer.getText().toString());
                double expense = Double.valueOf(etExpense.getText().toString());
                double room = Double.valueOf(etRoom.getText().toString());

                double thePricePerKg = pricePerVolume / weightOfProductInContainer;
                double theCostOfProcessingFacilities = (thePricePerKg / 1000) * (expense * room);

                tvThePricePerKg.setText(String.valueOf(roundUp(thePricePerKg, 2)));
                tvTheCostOfProcessingFacilities.setText(String.valueOf(roundUp(theCostOfProcessingFacilities, 2)));

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
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
    }*/

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
