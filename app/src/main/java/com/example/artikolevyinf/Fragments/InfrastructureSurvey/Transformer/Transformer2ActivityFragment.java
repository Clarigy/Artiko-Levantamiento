package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;

public class Transformer2ActivityFragment extends Fragment {

    ImageView ivIcInfoIcOil, ivInfoIcAddress, ivInfoIcPCB, ivInfoCapacity, ivInfoAreaType, ivInfoType;
    Button btnAerialTransformer, btnPedestalTransformer, btnSubstationTransformer, btnRuralTransformer, btnUrbanTransformer, btnOilYesTransformer, btnOilNoTransformer, btnPcbYesTransformer, btnPcbPendingTransformer, btnPcbNoTransformer, btnPreviousTransformer2, btnNextTransformer2;
    //EditText
    EditText etLocationPlateTransformer, etCapacityTransformer;
    TextView tvVegetableOilTransformer, tvLocationPlateTransformer, tvPcbTransformer, tvAreaTypeTransformer, tvTypeTransformer;

    boolean isSelectedOil = false, isSelectedPCB = false, isBtnAreaSelected = false, isBtnTypeSelected = false;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;
    ArrayList<Button> buttonListPCB;
    ArrayList<Button> buttonListOil;
    ArrayList<Button> buttonListArea;
    ArrayList<Button> buttonListType;

    public Transformer2ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer2_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        //Se traen los componentes de la interfaz
        //ImageViews
        ivIcInfoIcOil = (ImageView) rootView.findViewById(R.id.info_ic_oil_iv_transformer);
        ivInfoIcAddress = (ImageView) rootView.findViewById(R.id.info_ic_address_iv_transformer);
        ivInfoIcPCB = (ImageView) rootView.findViewById(R.id.info_ic_pcb);
        ivInfoCapacity = rootView.findViewById(R.id.info_ic_capacity_iv_transformer);
        ivInfoAreaType = rootView.findViewById(R.id.info_ic_typeAreal_iv_transformer);
        ivInfoType = rootView.findViewById(R.id.info_ic_type_iv_transformer);
        //Buttons
        btnAerialTransformer = (Button) rootView.findViewById(R.id.aerial_btn_transformer);
        btnPedestalTransformer = (Button) rootView.findViewById(R.id.pedestal_btn_transformer);
        btnSubstationTransformer = (Button) rootView.findViewById(R.id.substation_btn_transformer);
        buttonListType = new ArrayList<Button>() {{ add(btnAerialTransformer); add(btnPedestalTransformer); add(btnSubstationTransformer); }};
        btnRuralTransformer = (Button) rootView.findViewById(R.id.typeArea_btn_rural_transformer);
        btnUrbanTransformer =(Button) rootView.findViewById(R.id.typeArea_btn_urban_transformer);
        buttonListArea = new ArrayList<Button>() {{ add(btnRuralTransformer); add(btnUrbanTransformer); }};
        btnOilYesTransformer = (Button) rootView.findViewById(R.id.oilYes_btn_transformer);
        btnOilNoTransformer = (Button) rootView.findViewById(R.id.oilNo_btn_transformer);
        buttonListOil = new ArrayList<Button>() {{ add(btnOilYesTransformer); add(btnOilNoTransformer); }};
        btnPcbYesTransformer = (Button) rootView.findViewById(R.id.pcbYes_btn_transformer);
        btnPcbPendingTransformer = (Button) rootView.findViewById(R.id.pcbPending_btn_transformer);
        btnPcbNoTransformer = (Button) rootView.findViewById(R.id.pcbNo_btn_transformer);
        buttonListPCB = new ArrayList<Button>() {{ add(btnPcbYesTransformer); add(btnPcbPendingTransformer); add(btnPcbNoTransformer); }};

        tvVegetableOilTransformer = rootView.findViewById(R.id.textView1);
        tvLocationPlateTransformer = rootView.findViewById(R.id.textView2);
        tvPcbTransformer = rootView.findViewById(R.id.textView3);
        tvAreaTypeTransformer = rootView.findViewById(R.id.textView200);
        tvTypeTransformer = rootView.findViewById(R.id.textView201);

        //On clic para los iconos de información
        ivIcInfoIcOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoIcAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Presione sobre la casilla para capturar la latitud y longitud del lugar donde se encuentra.", 1, getContext());
            }
        });
        ivInfoIcPCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la capacidad.", 0, getContext());
            }
        });
        ivInfoAreaType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        //On Clic para los botones
        btnAerialTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListType, btnAerialTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnTypeSelected = true;
                Globals.mapTransformerActivityData.put("type", "Aéreo");
            }
        });
        btnPedestalTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListType, btnPedestalTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnTypeSelected = true;
                Globals.mapTransformerActivityData.put("type", "Pedestal");
            }
        });
        btnSubstationTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListType, btnSubstationTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnTypeSelected = true;
                Globals.mapTransformerActivityData.put("type", "Subestación");
            }
        });
        btnRuralTransformer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListArea, btnRuralTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaSelected = true;
                Globals.mapTransformerActivityData.put("areaType", "Rural");
            }
        });
        btnUrbanTransformer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListArea, btnUrbanTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaSelected = true;
                Globals.mapTransformerActivityData.put("areaType", "Urbano");
            }
        });
        btnOilYesTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListOil, btnOilYesTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedOil = true;
                Globals.mapTransformerActivityData.put("vegetableOil","Si");
            }
        });
        btnOilNoTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListOil, btnOilNoTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedOil = true;
                Globals.mapTransformerActivityData.put("vegetableOil","No");
            }
        });
        btnPcbYesTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCB, btnPcbYesTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCB = true;
                Globals.mapTransformerActivityData.put("pcbEquip","Si");
            }
        });
        btnPcbPendingTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCB, btnPcbPendingTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCB = true;
                Globals.mapTransformerActivityData.put("pcbEquip","Pendiente");
            }
        });
        btnPcbNoTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPCB, btnPcbNoTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSelectedPCB = true;
                Globals.mapTransformerActivityData.put("pcbEquip","No");
            }
        });

        //EditText ubicación
        etLocationPlateTransformer = rootView.findViewById(R.id.location_transformer_et);
        etLocationPlateTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.loadCurrentLocationWithVerification(getActivity(), getContext(), etLocationPlateTransformer, Globals.cardGeneralActivitySelected.getDbLatitude(), Globals.cardGeneralActivitySelected.getDbLongitude());
            }
        });

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer2 = rootView.findViewById(R.id.previous_btn_transformer2);
        btnPreviousTransformer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer2 = rootView.findViewById(R.id.next_btn_transformer2);
        btnNextTransformer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()) {
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected + 1, true);
                }

            }
        });

        etCapacityTransformer = rootView.findViewById(R.id.capacity_et_transformer);
        etCapacityTransformer.setTransformationMethod(null);
        if(!TextUtils.isEmpty(Globals.cardGeneralActivitySelected.getStrSerial())){
            String[] capacity = Globals.cardGeneralActivitySelected.getStrSerial().split(" ");
            etCapacityTransformer.setText(capacity[1]);
        }else{
            etCapacityTransformer.setEnabled(true);
            etCapacityTransformer.setElevation(10);
            etCapacityTransformer.setBackground(getResources().getDrawable(R.drawable.input_general));
        }

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


    public boolean verifyInformation(){
        String etLocation = etLocationPlateTransformer.getText().toString();
        String capacity = etCapacityTransformer.getText().toString();

        if(isSelectedOil == true && !TextUtils.isEmpty(etLocation) && isSelectedPCB == true && !TextUtils.isEmpty(capacity) && isBtnAreaSelected && isBtnTypeSelected){
            Globals.boolArrayCompleteInfrastructurePages.set(1,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(1,false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            if (isSelectedOil == true && !TextUtils.isEmpty(etLocation) && isSelectedPCB == true && !TextUtils.isEmpty(capacity) && isBtnAreaSelected && isBtnTypeSelected) {
                return true;
            }
        }

        if(isSelectedOil == false){
            Utils.toastMessage("Debe de seleccionar una opción de aceite vegetal.", 1, getContext());
        }

        if(TextUtils.isEmpty(etLocation)){
            etLocationPlateTransformer.setError("Este campo no puede quedar vacío");
            etLocationPlateTransformer.requestFocus();
        }
        if(isSelectedPCB == false){
            Utils.toastMessage("Debe de seleccionar una opción de equipo fabricado con PCB.", 1, getContext());
        }
        if(TextUtils.isEmpty(capacity)){
            etCapacityTransformer.setError("Este campo no puede quedar vacío");
            etCapacityTransformer.requestFocus();
        }
        if(isBtnAreaSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de área.",1,getContext());
        }
        if(isBtnTypeSelected == false){
            Utils.toastMessage("Debe de seleccionar un tipo.",1,getContext());
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            isSelectedOil = Utils.changeButtonSelectionFromDB(buttonListOil, transformerActivity.getStrVegetableOil(), Globals.mapTransformerActivityData, "vegetableOil", isSelectedOil, getContext());
            isBtnAreaSelected = Utils.changeButtonSelectionFromDB(buttonListArea, transformerActivity.getStrAreaType(), Globals.mapTransformerActivityData, "areaType", isBtnAreaSelected, getContext());
            isBtnTypeSelected = Utils.changeButtonSelectionFromDB(buttonListType, transformerActivity.getStrType(), Globals.mapTransformerActivityData, "type", isBtnTypeSelected, getContext());
            if(transformerActivity.getDbTransformerLatitude() != 0.0 && transformerActivity.getDbTransformerLongitude() != 0.0){
                String latLong = transformerActivity.getDbTransformerLatitude().toString() + ", " + transformerActivity.getDbTransformerLongitude().toString();
                etLocationPlateTransformer.setText(latLong);
                Globals.mapTransformerActivityData.put("dbLatitude", transformerActivity.getDbTransformerLatitude());
                Globals.mapTransformerActivityData.put("dbLongitude", transformerActivity.getDbTransformerLongitude());
            }
            isSelectedPCB = Utils.changeButtonSelectionFromDB(buttonListPCB, transformerActivity.getStrPcbEquip(), Globals.mapTransformerActivityData, "pcbEquip", isSelectedPCB, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(isSelectedOil == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvVegetableOilTransformer, ivIcInfoIcOil, getContext());
            }

            if(TextUtils.isEmpty(etLocationPlateTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvLocationPlateTransformer, ivInfoIcAddress, getContext());
            }

            if(isSelectedPCB == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPcbTransformer, ivInfoIcPCB, getContext());
            }

            if(isBtnAreaSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvAreaTypeTransformer, ivInfoAreaType, getContext());
            }

            if(isBtnTypeSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvTypeTransformer, ivInfoType, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text
            Utils.changeEditTextStyleDisable(etLocationPlateTransformer, getContext());

            //Desabilitar el boton fallido y camara
            btnOilNoTransformer.setEnabled(false);
            btnOilYesTransformer.setEnabled(false);

            btnPcbNoTransformer.setEnabled(false);
            btnPcbPendingTransformer.setEnabled(false);
            btnPcbYesTransformer.setEnabled(false);

            btnRuralTransformer.setEnabled(false);
            btnUrbanTransformer.setEnabled(false);

            btnAerialTransformer.setEnabled(false);
            btnPedestalTransformer.setEnabled(false);
            btnSubstationTransformer.setEnabled(false);
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etLocationPlateTransformer, Globals.mapTransformerActivityData, "", "location");
        Utils.addListenerEditText(etCapacityTransformer, Globals.mapTransformerActivityData, "capacity", "int");
    }
}