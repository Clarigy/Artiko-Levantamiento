package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;

public class Transformer6ActivityFragment extends Fragment {

    Button btnPCIMeasurerTransformer, btnPCIEmbeddedTransformer, btnPCINoTransformer, btnSubnormalYesTransformer, btnSubnormalMixedTransformer, btnSubnormalNoTransformer, btnPreviousTransformer6, btnNextTransformer6;
    ImageView ivInfoIcPciActiveTransformer, ivInfoIcPowerTransformer, ivInfoIcPropertyTransformer, ivInfoIcPhaseSequenceTransformer, ivInfoIcSubnormalTransformer;
    EditText etPowerTransformer;
    Spinner spnPropertyTransformer, spnPhaseSequenceTransformer;
    TextView tvActivePciTransformer, tvPowerTransformer, tvPropertyTransformer, tvPhaseSequenceTransformer, tvSubnormalTransformer;

    //Booleanos para validar la selección de los botones
    boolean isSelectedPCI = false, isSelectedSubnormal = false;
    ArrayList<Button> buttonListPCI, buttonListSubnormal;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    public Transformer6ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_transformer6_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        btnPCIMeasurerTransformer = (Button) rootView.findViewById(R.id.PCI_measurer_btn_transformer);
        btnPCIEmbeddedTransformer = (Button) rootView.findViewById(R.id.PCI_embedded_btn_transformer);
        btnPCINoTransformer = (Button) rootView.findViewById(R.id.PCI_no_btn_transformer);
        buttonListPCI = new ArrayList<Button>() {{ add(btnPCIMeasurerTransformer); add(btnPCIEmbeddedTransformer); add(btnPCINoTransformer);}};
        btnSubnormalYesTransformer = (Button) rootView.findViewById(R.id.typeArea_btn_rural);
        btnSubnormalMixedTransformer = (Button) rootView.findViewById(R.id.subnormal_btn_mixed);
        btnSubnormalNoTransformer = (Button) rootView.findViewById(R.id.typeArea_btn_urban);
        buttonListSubnormal = new ArrayList<Button>() {{ add(btnSubnormalYesTransformer); add(btnSubnormalMixedTransformer); add(btnSubnormalNoTransformer);}};

        ivInfoIcPciActiveTransformer = (ImageView) rootView.findViewById(R.id.info_ic_pci_active_iv_transformer);
        ivInfoIcPowerTransformer = (ImageView) rootView.findViewById(R.id.info_ic_power_iv_transformer);
        ivInfoIcPropertyTransformer = (ImageView) rootView.findViewById(R.id.info_ic_property_iv_transformer);
        ivInfoIcPhaseSequenceTransformer = (ImageView) rootView.findViewById(R.id.info_ic_phase_sequence_iv_transformer);
        ivInfoIcSubnormalTransformer = (ImageView) rootView.findViewById(R.id.info_ic_typeAreal_iv_lowVoltage);

        etPowerTransformer = (EditText) rootView.findViewById(R.id.power_et_transformer);

        spnPropertyTransformer = (Spinner) rootView.findViewById(R.id.property_spn_transformer);
        spnPhaseSequenceTransformer = (Spinner) rootView.findViewById(R.id.transformer_phase_sequence_spn_transformer);

        tvActivePciTransformer = rootView.findViewById(R.id.textView1);
        tvPowerTransformer = rootView.findViewById(R.id.textView2);
        tvPropertyTransformer = rootView.findViewById(R.id.textView3);
        tvPhaseSequenceTransformer = rootView.findViewById(R.id.textView4);
        tvSubnormalTransformer = rootView.findViewById(R.id.textView5);

        //Se llenan los spinner
        String[] propertyArray = {"", "AIR-E", "PARTICULAR", "MIXTO", "CENS", "MYPIMES", "SIN PROPIEDAD", "PUBLICA", "STN"};
        ArrayAdapter<String> adapterProperty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, propertyArray);
        spnPropertyTransformer.setAdapter(adapterProperty);
        String[] phaseArray = {"", "R", "S", "T", "RS", "RT", "SR", "ST", "TS", "TR", "RST", "RTS", "STR", "SRT", "TRS", "TSR"};
        ArrayAdapter<String> adaptePhase = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, phaseArray);
        spnPhaseSequenceTransformer.setAdapter(adaptePhase);

        //Se muestran los números del campo de potencia nominal
        etPowerTransformer.setTransformationMethod(null);
        //Se escucha cada cambio del campo de texto y se toma el string para concatenar una "," en los valores
        etPowerTransformer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.addCharToString(etPowerTransformer, ".",7);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //Onclick de los botones
        btnPCIMeasurerTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCI, btnPCIMeasurerTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCI = true;
                Globals.mapTransformerActivityData.put("activePci", "PCI Medidor");
            }
        });
        btnPCIEmbeddedTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCI, btnPCIEmbeddedTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCI = true;
                Globals.mapTransformerActivityData.put("activePci", "PCI Medidor Embebido");
            }
        });
        btnPCINoTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCI, btnPCINoTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCI = true;
                Globals.mapTransformerActivityData.put("activePci", "No");
            }
        });
        btnSubnormalYesTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSubnormal, btnSubnormalYesTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedSubnormal = true;
                Globals.mapTransformerActivityData.put("subnormal", "Si");
            }
        });
        btnSubnormalMixedTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSubnormal, btnSubnormalMixedTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedSubnormal = true;
                Globals.mapTransformerActivityData.put("subnormal", "Mixto");
            }
        });
        btnSubnormalNoTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSubnormal, btnSubnormalNoTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedSubnormal = true;
                Globals.mapTransformerActivityData.put("subnormal", "No");
            }
        });

        ivInfoIcPciActiveTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcPowerTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite el número correspondiente a la potencia nominal en kVA.",0,getContext());
            }
        });
        ivInfoIcPropertyTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcPhaseSequenceTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcSubnormalTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer6 = rootView.findViewById(R.id.previous_btn_transformer6);
        btnPreviousTransformer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer6 = rootView.findViewById(R.id.next_btn_transformer6);
        btnNextTransformer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected +1,true);
                }
            }
        });

        /** Se obtiene la información que va diligenciando para guardarla inmediatamente */
        saveInstantInformation();

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada */
        disableElementsByState();

        return rootView;
    }

    private void changeColorButton(ArrayList<Button> buttonList, Button clickedButton) {
        for (Button button: buttonList
        ) {
            //Si no esta seleccionado se pone unselected style
            button.setTextColor(getResources().getColor(R.color.colorBlueDark));
            button.setBackground(getResources().getDrawable(R.drawable.unselected_button));
        }
        //Se cambia el color del boton clickeado a azul con letra blanca
        clickedButton.setTextColor(getResources().getColor(R.color.colorWhite));
        clickedButton.setBackground(getResources().getDrawable(R.drawable.button_blue));
    }

    public boolean verifyInformation(){
        //True porque todos los campos de esta pag son opcionales
        boolean informationComplete = true;

        String nominalPowerTransformer = etPowerTransformer.getText().toString();
        String propertyTransformer = spnPropertyTransformer.getSelectedItem().toString();
        String phaseSequenceTransformer = spnPhaseSequenceTransformer.getSelectedItem().toString();

        if(isSelectedPCI && !TextUtils.isEmpty(nominalPowerTransformer) && !TextUtils.isEmpty(propertyTransformer) && !TextUtils.isEmpty(phaseSequenceTransformer) && isSelectedSubnormal){
            Globals.boolArrayCompleteInfrastructurePages.set(5,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(5,false);
        }

        if(isSelectedPCI == false){
            Globals.mapTransformerActivityData.put("activePci", "");
        }
        if(isSelectedSubnormal == false){
            Globals.mapTransformerActivityData.put("subnormal", "");
        }

        return informationComplete;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            isSelectedPCI = Utils.changeButtonSelectionFromDB(buttonListPCI, transformerActivity.getStrActivePci(), Globals.mapTransformerActivityData, "activePci", isSelectedPCI, getContext());
            etPowerTransformer.setText(transformerActivity.getStrNominalPower());
            spnPropertyTransformer.setSelection(((ArrayAdapter<String>)spnPropertyTransformer.getAdapter()).getPosition(transformerActivity.getStrProperty()));
            spnPhaseSequenceTransformer.setSelection(((ArrayAdapter<String>)spnPhaseSequenceTransformer.getAdapter()).getPosition(transformerActivity.getStrPhaseSequence()));
            isSelectedSubnormal = Utils.changeButtonSelectionFromDB(buttonListSubnormal, transformerActivity.getStrSubnormal(), Globals.mapTransformerActivityData, "subnormal", isSelectedSubnormal, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(isSelectedPCI == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvActivePciTransformer, ivInfoIcPciActiveTransformer, getContext());
            }
            if(TextUtils.isEmpty(etPowerTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPowerTransformer, ivInfoIcPowerTransformer, getContext());
            }
            if(TextUtils.isEmpty(spnPropertyTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPropertyTransformer, ivInfoIcPropertyTransformer, getContext());
            }
            if(TextUtils.isEmpty(spnPhaseSequenceTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPhaseSequenceTransformer, ivInfoIcPhaseSequenceTransformer, getContext());
            }
            if(isSelectedSubnormal == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSubnormalTransformer, ivInfoIcSubnormalTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text y spinner
            Utils.changeEditTextStyleDisable(etPowerTransformer, getContext());
            Utils.changeSpinnerStyleDisable(spnPropertyTransformer,getContext());
            Utils.changeSpinnerStyleDisable(spnPhaseSequenceTransformer,getContext());

            //deshabilitar botones
            btnPCIMeasurerTransformer.setEnabled(false);
            btnPCIEmbeddedTransformer.setEnabled(false);
            btnPCINoTransformer.setEnabled(false);

            btnSubnormalYesTransformer.setEnabled(false);
            btnSubnormalMixedTransformer.setEnabled(false);
            btnSubnormalNoTransformer.setEnabled(false);
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etPowerTransformer, Globals.mapTransformerActivityData, "nominalPower", "string");
        Utils.addListenerSpinner(spnPropertyTransformer, Globals.mapTransformerActivityData, "property");
        Utils.addListenerSpinner(spnPhaseSequenceTransformer, Globals.mapTransformerActivityData, "phaseSequence");
    }
}