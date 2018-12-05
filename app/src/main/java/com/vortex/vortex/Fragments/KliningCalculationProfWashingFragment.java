package com.vortex.vortex.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.vortex.vortex.R;

import static com.vortex.vortex.Calculations.RoundUp.roundUp;

public class KliningCalculationProfWashingFragment extends Fragment {

    String[] data_base_means = {"ВЫБРАТЬ", "MIX ENERGY", "MIX BASIC", "MIX ACTIV", "MIX FERMENT", "MIX SENSITIV"};
    String[] data_amplifiers = {"ВЫБРАТЬ", "MIX FORTE PLUS", "MIX POWER PLUS", "MIX INTENSIV PLUS", "MIX ENZYM PLUS", "MIX COLOR PLUS"};
    String[] data_bleaches = {"ВЫБРАТЬ", "MIX HYPO", "MIX OXYDEZ", "MIX OXY"};
    String[] data_base_means_desc = {"Отлично удаляет белковые, масляно-жировые, глиняные и почвенные загрязнения. Предотвращает посерение тканей.", "Эффективно эмульгирует и растворяет белковые, масляно-жировые, пигментные и почвенные загрязнения. Предотвращает посерение тканей", "Мягко и эффективно эмульгирует и удаляет пигментные, масляные и жировые загрязнения, не вызывая переноса красителя.", "Эффективно удаляет белковые, жировые, растительные загрязнения, пятна крахмала, крови. Не содержит в составе щелочных компонентов.", "Бережно и эффективно удаляет загрязнения различной природы: почвенные, белковые, жировые, пигментные, не вызывая переноса красителя. Оказывает минимальное воздействие на материал тканей"};

    private Spinner spinner_base_means;
    private Spinner spinner_amplifiers;
    private Spinner spinner_bleaches;

    private TableRow tableRow_base_means;
    private TableRow tr_cost_base_means;
    private TableRow tr_cost_washing_base_means;

    private TableRow tr_amplifiers_purpose_name;
    private TableRow tr_amplifiers_purpose_desc;
    private TableRow tr_amplifiers_color_fabric;
    private TableRow tr_amplifiers_type_fabric;
    private TableRow tr_amplifiers_washing_temerature;

    private TableRow tr_cost_means_amplifiers;
    private TableRow tr_cost_washing_amplifiers;

    private TableRow tr_cost_means_bleaches;
    private TableRow tr_cost_washing_bleaches;

    private TableRow tr_cost_means_mix_soft;
    private TableRow tr_cost_washing_mix_soft;

    private TableRow tr_cost_means_mix_zero;
    private TableRow tr_cost_washing_mix_zero;

    private EditText et_consumption_base_means;
    private EditText et_volume_cans_base_means;
    private EditText et_cost_cans_base_means;

    private EditText et_consumption_1kg_line_amplifiers;
    private EditText et_volume_cans_amplifiers;
    private EditText et_cost_cans_amplifiers;

    private EditText ed_consumption_1kg_line_bleaches;
    private EditText ed_volume_cans_bleaches;
    private EditText et_cost_cans_bleaches;

    private EditText et_consumption_1kg_line_mix_soft;
    private EditText et_volume_cans_mix_soft;
    private EditText et_cost_cans_mix_soft;

    private EditText et_consumption_1kg_line_mix_zero;
    private EditText et_volume_cans_mix_zero;
    private EditText et_cost_cans_mix_zero;

    private TextView tv_description_base_means;
    private TextView tv_result_cost_1kg_means;
    private TextView tv_result_cost_1kg_washing;

    private TextView tv_purpose_combination_description;
    private TextView tv_result_color_fabric;
    private TextView tv_result_type_fabric;
    private TextView tv_result_washing_temperature;
    private TextView tv_result_cost_1kg_means_amplifiers;
    private TextView tv_result_cost_1kg_washing_amplifiers;

    private TextView tv_result_cost_1kg_means_bleaches;
    private TextView tv_result_cost_1kg_washing_bleaches;

    private TextView tv_result_cost_1kg_means_mix_soft;
    private TextView tv_result_cost_1kg_washing_mix_soft;

    private TextView tv_result_cost_1kg_means_mix_zero;
    private TextView tv_result_cost_1kg_washing_mix_zero;

    private TextView tv_result_all;
    private TextView tv_mix_soft_chekbox;

    private Boolean isBaseMeans;
    private Boolean isAmplifiers;
    private Boolean isBleaches;
    private Boolean isMixSoft;
    private Boolean isMixSoftCalculations;
    private Boolean isMixZero;

    private CheckBox cb_mix_soft;

    private double consumption_base_means;
    private double volume_base_means;
    private double cost_cans_base_means;

    private double consumption_1kg_line_amplifiers;
    private double volume_cans_amplifiers;
    private double cost_cans_amplifiers;

    private double consumption_1kg_line_bleaches;
    private double volume_cans_bleaches;
    private double cost_cans_bleaches;

    private double consumption_1kg_line_mix_soft;
    private double volume_cans_mix_soft;
    private double cost_cans_mix_soft;

    private double consumption_1kg_line_mix_zero;
    private double volume_cans_mix_zero;
    private double cost_cans_mix_zero;

    private double amountMixZero;
    private double amountMixSoft;
    private double amountBleaches;
    private double amountAmplifiers;
    private double amountBaseMeans;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KliningCalculationProfWashingFragment() {
        // Required empty public constructor
    }

    public static KliningCalculationProfWashingFragment newInstance(String param1, String param2) {
        KliningCalculationProfWashingFragment fragment = new KliningCalculationProfWashingFragment();
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
        View view = inflater.inflate(R.layout.fragment_klining_calculation_prof_washing, container, false);

        getActivity().setTitle("Комплексная стирка");

        spinner_base_means = view.findViewById(R.id.spinner_base_means);
        spinner_amplifiers = view.findViewById(R.id.spinner_amplifiers);
        spinner_bleaches = view.findViewById(R.id.spinner_bleaches);

        tableRow_base_means = view.findViewById(R.id.tr_description_base_means);
        tr_cost_base_means = view.findViewById(R.id.tr_cost_base_means);
        tr_cost_washing_base_means = view.findViewById(R.id.tr_cost_washing_base_means);

        tr_amplifiers_purpose_name = view.findViewById(R.id.tr_amplifiers_purpose_name);
        tr_amplifiers_purpose_desc = view.findViewById(R.id.tr_amplifiers_purpose_desc);
        tr_amplifiers_color_fabric = view.findViewById(R.id.tr_amplifiers_color_fabric);
        tr_amplifiers_type_fabric = view.findViewById(R.id.tr_amplifiers_type_fabric);
        tr_amplifiers_washing_temerature = view.findViewById(R.id.tr_amplifiers_washing_temerature);

        tr_cost_means_amplifiers = view.findViewById(R.id.tr_cost_means_amplifiers);
        tr_cost_washing_amplifiers = view.findViewById(R.id.tr_cost_washing_amplifiers);

        tr_cost_means_bleaches = view.findViewById(R.id.tr_cost_means_bleaches);
        tr_cost_washing_bleaches = view.findViewById(R.id.tr_cost_washing_bleaches);

        tr_cost_means_mix_soft = view.findViewById(R.id.tr_cost_means_mix_soft);
        tr_cost_washing_mix_soft = view.findViewById(R.id.tr_cost_washing_mix_soft);

        tv_description_base_means = view.findViewById(R.id.tv_description_base_means);
        tv_result_cost_1kg_washing = view.findViewById(R.id.tv_result_cost_1kg_washing);
        tv_result_cost_1kg_means = view.findViewById(R.id.tv_result_cost_1kg_means);

        tr_cost_means_mix_zero = view.findViewById(R.id.tr_cost_means_mix_zero);
        tr_cost_washing_mix_zero = view.findViewById(R.id.tr_cost_washing_mix_zero);

        tv_purpose_combination_description = view.findViewById(R.id.tv_purpose_combination_description);
        tv_result_color_fabric = view.findViewById(R.id.tv_result_color_fabric);
        tv_result_type_fabric = view.findViewById(R.id.tv_result_type_fabric);
        tv_result_washing_temperature = view.findViewById(R.id.tv_result_washing_temperature);
        tv_result_cost_1kg_means_amplifiers = view.findViewById(R.id.tv_result_cost_1kg_means_amplifiers);
        tv_result_cost_1kg_washing_amplifiers = view.findViewById(R.id.tv_result_cost_1kg_washing_amplifiers);

        tv_result_cost_1kg_means_bleaches = view.findViewById(R.id.tv_result_cost_1kg_means_bleaches);
        tv_result_cost_1kg_washing_bleaches = view.findViewById(R.id.tv_result_cost_1kg_washing_bleaches);

        tv_result_cost_1kg_means_mix_soft = view.findViewById(R.id.tv_result_cost_1kg_means_mix_soft);
        tv_result_cost_1kg_washing_mix_soft = view.findViewById(R.id.tv_result_cost_1kg_washing_mix_soft);

        tv_result_cost_1kg_means_mix_zero = view.findViewById(R.id.tv_result_cost_1kg_means_mix_zero);
        tv_result_cost_1kg_washing_mix_zero = view.findViewById(R.id.tv_result_cost_1kg_washing_mix_zero);

        tv_result_all = view.findViewById(R.id.tv_result_all);
        tv_mix_soft_chekbox = view.findViewById(R.id.tv_mix_soft_chekbox);

        et_consumption_base_means = view.findViewById(R.id.et_consumption);
        et_volume_cans_base_means = view.findViewById(R.id.et_volume_cans);
        et_cost_cans_base_means = view.findViewById(R.id.et_cost_cans);

        et_consumption_1kg_line_amplifiers = view.findViewById(R.id.et_consumption_1kg_line_amplifiers);
        et_volume_cans_amplifiers = view.findViewById(R.id.et_volume_cans_amplifiers);
        et_cost_cans_amplifiers = view.findViewById(R.id.et_cost_cans_amplifiers);

        ed_consumption_1kg_line_bleaches = view.findViewById(R.id.ed_consumption_1kg_line_bleaches);
        ed_volume_cans_bleaches = view.findViewById(R.id.ed_volume_cans_bleaches);
        et_cost_cans_bleaches = view.findViewById(R.id.et_cost_cans_bleaches);

        et_consumption_1kg_line_mix_soft = view.findViewById(R.id.et_consumption_1kg_line_mix_soft);
        et_volume_cans_mix_soft = view.findViewById(R.id.et_volume_cans_mix_soft);
        et_cost_cans_mix_soft = view.findViewById(R.id.et_cost_cans_mix_soft);

        et_consumption_1kg_line_mix_zero = view.findViewById(R.id.et_consumption_1kg_line_mix_zero);
        et_volume_cans_mix_zero = view.findViewById(R.id.et_volume_cans_mix_zero);
        et_cost_cans_mix_zero = view.findViewById(R.id.et_cost_cans_mix_zero);

        isBaseMeans = false;
        isAmplifiers = false;
        isBleaches = false;
        isMixSoft = false;
        isMixSoftCalculations = false;
        isMixZero = false;

        cb_mix_soft = view.findViewById(R.id.cb_mix_soft);

        amountMixZero = 0;
        amountMixSoft = 0;
        amountBleaches = 0;
        amountAmplifiers = 0;
        amountBaseMeans = 0;


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner, data_base_means);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_means);
        spinner_base_means.setAdapter(adapter);

        ArrayAdapter<String> adapter_amperfiles = new ArrayAdapter<>(getActivity(), R.layout.spinner, data_amplifiers);
        adapter_amperfiles.setDropDownViewResource(R.layout.spinner_dropdown_item_means);
        spinner_amplifiers.setAdapter(adapter_amperfiles);

        ArrayAdapter<String> adapter_bleaches = new ArrayAdapter<>(getActivity(), R.layout.spinner, data_bleaches);
        adapter_bleaches.setDropDownViewResource(R.layout.spinner_dropdown_item_means);
        spinner_bleaches.setAdapter(adapter_bleaches);

        spinner_base_means.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                isBaseMeans = true;
                switch (pos) {
                    case 0:
                        BaseMeansEnabled();
                        tv_description_base_means.setText("");
                        isBaseMeans = false;
                        et_consumption_base_means.getText().clear();
                        et_volume_cans_base_means.getText().clear();
                        et_cost_cans_base_means.getText().clear();
                        break;
                    case 1:
                        BaseMeansEnabled();
                        tv_description_base_means.setText(data_base_means_desc[0]);
                        break;
                    case 2:
                        BaseMeansEnabled();
                        tv_description_base_means.setText(data_base_means_desc[1]);
                        break;
                    case 3:
                        BaseMeansEnabled();
                        tv_description_base_means.setText(data_base_means_desc[2]);
                        break;
                    case 4:
                        BaseMeansEnabled();
                        tv_description_base_means.setText(data_base_means_desc[3]);
                        break;
                    case 5:
                        BaseMeansEnabled();
                        tv_description_base_means.setText(data_base_means_desc[4]);
                        break;
                    default:
                        BaseMeansEnabled();
                        tv_description_base_means.setText("");
                        isBaseMeans = false;
                }
                CalculationBaseMeans();
                PurposeOfTheCombination();
                ConsumptionMixZero();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_amplifiers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                isAmplifiers = true;
                et_consumption_1kg_line_amplifiers.getText().clear();
                et_volume_cans_amplifiers.getText().clear();
                et_cost_cans_amplifiers.getText().clear();
                switch (pos) {
                    case 0:
                        isAmplifiers = false;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        isAmplifiers = false;
                }
                PurposeOfTheCombination();
                CalculationAmplifiers();
                ConsumptionMixZero();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_bleaches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                isBleaches = true;

                ed_consumption_1kg_line_bleaches.getText().clear();
                ed_volume_cans_bleaches.getText().clear();
                et_cost_cans_bleaches.getText().clear();
                switch (pos) {
                    case 0:
                        isBleaches = false;
                        break;
                    case 1:
                        ed_consumption_1kg_line_bleaches.setText("3.5");
                        break;
                    case 2:
                        ed_consumption_1kg_line_bleaches.setText("4");
                        break;
                    case 3:
                        ed_consumption_1kg_line_bleaches.setText("7");
                        break;
                    default:
                        isBleaches = false;
                }
                CalculationBleaches();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cb_mix_soft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    et_consumption_1kg_line_mix_soft.setText("6");
                    isMixSoft = true;
                    tv_mix_soft_chekbox.setTextColor(Color.BLACK);
                } else {
                    et_consumption_1kg_line_mix_soft.getText().clear();
                    tv_mix_soft_chekbox.setTextColor(R.color.colorGray);
                    isMixSoft = false;
                }
                MixSoftEnabled();
                CalculationMixSoft();
            }
        });

        et_consumption_base_means.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBaseMeans();
            }
        });

        et_volume_cans_base_means.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBaseMeans();
            }
        });

        et_cost_cans_base_means.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBaseMeans();
            }
        });

        et_cost_cans_amplifiers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationAmplifiers();
            }
        });

        et_consumption_1kg_line_amplifiers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationAmplifiers();
            }
        });

        et_volume_cans_amplifiers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationAmplifiers();
            }
        });

        ed_consumption_1kg_line_bleaches.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBleaches();

            }
        });

        ed_volume_cans_bleaches.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBleaches();

            }
        });

        et_cost_cans_bleaches.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationBleaches();
            }
        });

        et_volume_cans_mix_soft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationMixSoft();
            }
        });

        et_cost_cans_mix_soft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationMixSoft();
            }
        });

        et_volume_cans_mix_zero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationMixZero();
            }
        });

        et_cost_cans_mix_zero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationMixZero();
            }
        });

        et_consumption_1kg_line_mix_zero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CalculationMixZero();
            }
        });

        return view;
    }

    private void CalculationAmount(double amountMixZero, double amountMixSoft, double amountBleaches, double amountAmplifiers, double amountBaseMeans) {
        double amount = amountMixZero + amountMixSoft + amountBleaches + amountAmplifiers + amountBaseMeans;

        tv_result_all.setText(String.valueOf(String.valueOf(roundUp(amount, 2))));
    }


    private void ConsumptionMixZero() {
        String result = "";

        int baseMeans = spinner_base_means.getSelectedItemPosition();
        int amplifiers = spinner_amplifiers.getSelectedItemPosition();
        if (isBaseMeans) {

            switch (baseMeans) {
                case 1:
                    result = "2";
                    isMixZero = true;
                    break;
                case 2:
                    result = "2";
                    isMixZero = true;
                    break;
                case 5:
                    result = "Не применяется";
                    isMixZero = false;
                    break;
                default:
                    break;
            }

            et_consumption_1kg_line_mix_zero.setText(result);

        } else if (isBaseMeans && isAmplifiers) {

            switch (baseMeans) {
                case 3:

                    switch (amplifiers) {
                        case 1:
                            result = "2";
                            isMixZero = true;
                            break;
                        case 2:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 3:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 4:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 5:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                    }

                    break;
                case 4:

                    switch (amplifiers) {
                        case 1:
                            result = "2";
                            isMixZero = true;
                            break;
                        case 2:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 3:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 4:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                        case 5:
                            result = "Не применяется";
                            isMixZero = false;
                            break;
                    }

                    break;
                default:
                    break;
            }
            et_consumption_1kg_line_mix_zero.setText(result);
        } else {
            et_consumption_1kg_line_mix_zero.getText().clear();
        }
    }

    private void CalculationMixZero() {

        if (et_consumption_1kg_line_mix_zero.getText().length() != 0 && et_volume_cans_mix_zero.getText().length() != 0 && et_cost_cans_mix_zero.getText().length() != 0 && isMixZero) {

            consumption_1kg_line_mix_zero = Double.parseDouble(String.valueOf(et_consumption_1kg_line_mix_zero.getText()));
            volume_cans_mix_zero = Double.parseDouble(String.valueOf(et_volume_cans_mix_zero.getText()));
            cost_cans_mix_zero = Double.parseDouble(String.valueOf(et_cost_cans_mix_zero.getText()));

            Double cost_means = cost_cans_mix_zero / volume_cans_mix_zero;
            Double cost_washing = consumption_1kg_line_mix_zero / 1000 * cost_means;

            if (cost_means != null && cost_washing != null) {
                tr_cost_means_mix_zero.setVisibility(View.VISIBLE);
                tr_cost_washing_mix_zero.setVisibility(View.VISIBLE);

                tv_result_cost_1kg_washing_mix_zero.setText(String.valueOf(roundUp(cost_washing, 2)));
                tv_result_cost_1kg_means_mix_zero.setText(String.valueOf(roundUp(cost_means, 2)));
                amountMixZero = cost_washing;
            }
        } else {
            tr_cost_means_mix_zero.setVisibility(View.GONE);
            tr_cost_washing_mix_zero.setVisibility(View.GONE);

            tv_result_cost_1kg_means_mix_zero.setText("");
            tv_result_cost_1kg_washing_mix_zero.setText("");

            amountMixZero = 0;
        }
        CalculationAmount(amountMixZero, amountMixSoft, amountBleaches, amountAmplifiers, amountBaseMeans);
    }

    private void CalculationMixSoft() {
        if (et_volume_cans_mix_soft.getText().length() != 0 && et_cost_cans_mix_soft.getText().length() != 0 && isMixSoft) {

            isMixSoftCalculations = true;
            consumption_1kg_line_mix_soft = Double.parseDouble(String.valueOf(et_consumption_1kg_line_mix_soft.getText()));
            volume_cans_mix_soft = Double.parseDouble(String.valueOf(et_volume_cans_mix_soft.getText()));
            cost_cans_mix_soft = Double.parseDouble(String.valueOf(et_cost_cans_mix_soft.getText()));

            Double cost_means = cost_cans_mix_soft / volume_cans_mix_soft;
            Double cost_washing = consumption_1kg_line_mix_soft / 1000 * cost_means;

            if (cost_means != null && cost_washing != null) {
                tr_cost_means_mix_soft.setVisibility(View.VISIBLE);
                tr_cost_washing_mix_soft.setVisibility(View.VISIBLE);

                tv_result_cost_1kg_means_mix_soft.setText(String.valueOf(roundUp(cost_means, 2)));
                tv_result_cost_1kg_washing_mix_soft.setText(String.valueOf(roundUp(cost_washing, 2)));

                amountMixSoft = cost_washing;
            }
        } else {
            isMixSoftCalculations = false;
            MixSoftEnabled();
            tr_cost_means_mix_soft.setVisibility(View.GONE);
            tr_cost_washing_mix_soft.setVisibility(View.GONE);

            tv_result_cost_1kg_means_mix_soft.setText("");
            tv_result_cost_1kg_washing_mix_soft.setText("");
            amountMixSoft = 0;
        }
        CalculationAmount(amountMixZero, amountMixSoft, amountBleaches, amountAmplifiers, amountBaseMeans);
    }

    private void CalculationBleaches() {

        if (ed_volume_cans_bleaches.getText().length() != 0 && et_cost_cans_bleaches.getText().length() != 0 && isBleaches) {

            consumption_1kg_line_bleaches = Double.parseDouble(String.valueOf(ed_consumption_1kg_line_bleaches.getText()));
            volume_cans_bleaches = Double.parseDouble(String.valueOf(ed_volume_cans_bleaches.getText()));
            cost_cans_bleaches = Double.parseDouble(String.valueOf(et_cost_cans_bleaches.getText()));

            Double cost_means = cost_cans_bleaches / volume_cans_bleaches;
            Double cost_washing = consumption_1kg_line_bleaches / 1000 * cost_means;

            if (cost_means != null && cost_washing != null) {
                tr_cost_means_bleaches.setVisibility(View.VISIBLE);
                tr_cost_washing_bleaches.setVisibility(View.VISIBLE);

                tv_result_cost_1kg_means_bleaches.setText(String.valueOf(roundUp(cost_means, 2)));
                tv_result_cost_1kg_washing_bleaches.setText(String.valueOf(roundUp(cost_washing, 2)));

                amountBleaches = cost_washing;
            }
        } else {
            AmplifiersEnabled();
            tr_cost_means_bleaches.setVisibility(View.GONE);
            tr_cost_washing_bleaches.setVisibility(View.GONE);

            tv_result_cost_1kg_means_bleaches.setText("");
            tv_result_cost_1kg_washing_bleaches.setText("");
            amountBleaches = 0;
        }
        CalculationAmount(amountMixZero, amountMixSoft, amountBleaches, amountAmplifiers, amountBaseMeans);
    }

    private void CalculationAmplifiers() {

        if (et_consumption_1kg_line_amplifiers.getText().length() != 0 && et_volume_cans_amplifiers.getText().length() != 0 && et_cost_cans_amplifiers.getText().length() != 0 && isAmplifiers) {

            consumption_1kg_line_amplifiers = Double.parseDouble(String.valueOf(et_consumption_1kg_line_amplifiers.getText()));
            volume_cans_amplifiers = Double.parseDouble(String.valueOf(et_volume_cans_amplifiers.getText()));
            cost_cans_amplifiers = Double.parseDouble(String.valueOf(et_cost_cans_amplifiers.getText()));

            Double cost_means = cost_cans_amplifiers / volume_cans_amplifiers;
            Double cost_washing = consumption_1kg_line_amplifiers / 1000 * cost_means;

            if (cost_means != null && cost_washing != null) {
                tr_cost_means_amplifiers.setVisibility(View.VISIBLE);
                tr_cost_washing_amplifiers.setVisibility(View.VISIBLE);

                tv_result_cost_1kg_means_amplifiers.setText(String.valueOf(roundUp(cost_means, 2)));
                tv_result_cost_1kg_washing_amplifiers.setText(String.valueOf(roundUp(cost_washing, 2)));

                amountAmplifiers = cost_washing;
            }
        } else {
            AmplifiersEnabled();
            tr_cost_means_amplifiers.setVisibility(View.GONE);
            tr_cost_washing_amplifiers.setVisibility(View.GONE);

            tv_result_cost_1kg_means_amplifiers.setText("");
            tv_result_cost_1kg_washing_amplifiers.setText("");
            amountAmplifiers = 0;
        }
        CalculationAmount(amountMixZero, amountMixSoft, amountBleaches, amountAmplifiers, amountBaseMeans);
    }

    private void CalculationBaseMeans() {
        if (et_consumption_base_means.getText().length() != 0 && et_volume_cans_base_means.getText().length() != 0 && et_cost_cans_base_means.getText().length() != 0 && isBaseMeans) {

            consumption_base_means = Double.parseDouble(String.valueOf(et_consumption_base_means.getText()));
            volume_base_means = Double.parseDouble(String.valueOf(et_volume_cans_base_means.getText()));
            cost_cans_base_means = Double.parseDouble(String.valueOf(et_cost_cans_base_means.getText()));
            Double cost_means = cost_cans_base_means / volume_base_means;
            Double cost_washing = consumption_base_means / 1000 * cost_means;

            if (cost_means != null && cost_washing != null) {
                tr_cost_base_means.setVisibility(View.VISIBLE);
                tr_cost_washing_base_means.setVisibility(View.VISIBLE);

                tv_result_cost_1kg_washing.setText(String.valueOf(roundUp(cost_washing, 2)));
                tv_result_cost_1kg_means.setText(String.valueOf(roundUp(cost_means, 2)));

                amountBaseMeans = cost_washing;
            }
        } else {
            BaseMeansEnabled();
            tr_cost_base_means.setVisibility(View.GONE);
            tr_cost_washing_base_means.setVisibility(View.GONE);

            tv_result_cost_1kg_washing.setText("");
            tv_result_cost_1kg_means.setText("");
            amountBaseMeans = 0;
        }
        CalculationAmount(amountMixZero, amountMixSoft, amountBleaches, amountAmplifiers, amountBaseMeans);
    }

    private void PurposeOfTheCombination() {
        int base_means = spinner_base_means.getSelectedItemPosition();
        int amplifiers = spinner_amplifiers.getSelectedItemPosition();

        if (base_means == 0 || amplifiers == 0)
            return;

        if (base_means == 1 && amplifiers == 1) {
            tv_purpose_combination_description.setText("Высокощелочная стирка рабочей одежды от тяжелых жировых, почвенных и пигментных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, лён");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 1 && amplifiers == 2) {
            tv_purpose_combination_description.setText("Стирка высокозагрязненного белья от жировых, пигментных загрязнений и нефтяных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("60-95");
        } else if (base_means == 1 && amplifiers == 3) {
            tv_purpose_combination_description.setText("Среднетемпературная стирка сильнозагрязненных белых изделий с оптическим отбеливанием");
            tv_result_color_fabric.setText("Белое");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 1 && amplifiers == 4) {
            tv_purpose_combination_description.setText("Стирка тяжелых белковых, жировых и растительных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 1 && amplifiers == 5) {
            tv_purpose_combination_description.setText("Стирка высокозагрязенного белья с защитой цвета");
            tv_result_color_fabric.setText("Цветное");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 2 && amplifiers == 1) {
            tv_purpose_combination_description.setText("Стирка рабочей одежды, тяжелых почвенных и пигментных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 2 && amplifiers == 2) {
            tv_purpose_combination_description.setText("Стирка атмосферных, почвенных и жировых загрязнений средней загрязненности");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные ткани");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 2 && amplifiers == 3) {
            tv_purpose_combination_description.setText("Стирка средних загрязнений с оптическим отбеливанием");
            tv_result_color_fabric.setText("Белое");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 2 && amplifiers == 4) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка растительных, жировых, белковых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 2 && amplifiers == 5) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка средних загрязнений с защитой цвета");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, вискоза, смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 3 && amplifiers == 1) {
            tv_purpose_combination_description.setText("Стирка жировых и пищевых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, лен, смешанные ткани");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 3 && amplifiers == 2) {
            tv_purpose_combination_description.setText("Стирка атмосферных, жировых, масляных и несложных нефтяных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Все типы");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 3 && amplifiers == 3) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка несложных жировых и пигментных загрязнений с оптическим отбеливанием");
            tv_result_color_fabric.setText("Белое");
            tv_result_type_fabric.setText("Все типы");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 3 && amplifiers == 4) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка растительных и белковых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, ограничено шерсть и шелк");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 3 && amplifiers == 5) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка цветного и деликатного белья");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные, деликатные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 4 && amplifiers == 1) {
            tv_purpose_combination_description.setText("Стирка от тяжелых белковых, жировых и растительных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные ткани");
            tv_result_washing_temperature.setText("20-95");
        } else if (base_means == 4 && amplifiers == 2) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка растительных и жировых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 4 && amplifiers == 3) {
            tv_purpose_combination_description.setText("Стирка белых вещей от растительных и животных загрязнений с оптическим отбеливанием");
            tv_result_color_fabric.setText("Белое");
            tv_result_type_fabric.setText("Хлопок и смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 4 && amplifiers == 4) {
            tv_purpose_combination_description.setText("Низкотемпературная стирка от тяжелых растительных и белковых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, ограничено шерсть и шелк");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 4 && amplifiers == 5) {
            tv_purpose_combination_description.setText("Стирка цветного белья от растительных и животных загрязнений  с защитой цвета");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, смешанные ткани");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 5 && amplifiers == 1) {
            tv_purpose_combination_description.setText("Данная комбинация не применима");
            tv_result_color_fabric.setText("-");
            tv_result_type_fabric.setText("-");
            tv_result_washing_temperature.setText("-");
        } else if (base_means == 5 && amplifiers == 2) {
            tv_purpose_combination_description.setText("Мягкая низкотемпературная стирка деликатных тканей от средних масляных и нефтяных загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Все типы");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 5 && amplifiers == 3) {
            tv_purpose_combination_description.setText("Мягкая стирка  деликатных белых изделий с оптическим отбеливанием");
            tv_result_color_fabric.setText("Белое");
            tv_result_type_fabric.setText("Все типы");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 5 && amplifiers == 4) {
            tv_purpose_combination_description.setText("Мягкая низкотемпературная стирка деликатных тканей от растительных и белковых загрязнений");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Хлопок, ограничено шерсть и шелк");
            tv_result_washing_temperature.setText("30-60");
        } else if (base_means == 5 && amplifiers == 5) {
            tv_purpose_combination_description.setText("Мягкая низкотемпературная стирка деликатных тканей с защитой цвета");
            tv_result_color_fabric.setText("Белое и цветное");
            tv_result_type_fabric.setText("Все типы");
            tv_result_washing_temperature.setText("30-60");
        }
        AmplifiersEnabled();
    }

    private void MixSoftEnabled() {
        if (isMixSoft && isMixSoftCalculations) {
            tr_cost_means_mix_soft.setVisibility(View.VISIBLE);
            tr_cost_washing_mix_soft.setVisibility(View.VISIBLE);
        } else {
            tr_cost_means_mix_soft.setVisibility(View.GONE);
            tr_cost_washing_mix_soft.setVisibility(View.GONE);
        }
    }

    private void BaseMeansEnabled() {
        et_consumption_base_means.setEnabled(isBaseMeans);
        et_volume_cans_base_means.setEnabled(isBaseMeans);
        et_cost_cans_base_means.setEnabled(isBaseMeans);
        if (isBaseMeans)
            tableRow_base_means.setVisibility(View.VISIBLE);
        else {
            tableRow_base_means.setVisibility(View.GONE);
            AmplifiersEnabled();
        }
    }

    private void AmplifiersEnabled() {
        if (isAmplifiers && isBaseMeans) {
            tr_amplifiers_purpose_name.setVisibility(View.VISIBLE);
            tr_amplifiers_purpose_desc.setVisibility(View.VISIBLE);
            tr_amplifiers_color_fabric.setVisibility(View.VISIBLE);
            tr_amplifiers_type_fabric.setVisibility(View.VISIBLE);
            tr_amplifiers_washing_temerature.setVisibility(View.VISIBLE);
        } else {
            tr_amplifiers_purpose_name.setVisibility(View.GONE);
            tr_amplifiers_purpose_desc.setVisibility(View.GONE);
            tr_amplifiers_color_fabric.setVisibility(View.GONE);
            tr_amplifiers_type_fabric.setVisibility(View.GONE);
            tr_amplifiers_washing_temerature.setVisibility(View.GONE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
