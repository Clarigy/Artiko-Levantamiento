package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;

public class Transformer3ActivityFragment extends Fragment {

    Button btnPhaseRopenTransformer, btnPhaseRcloseTransformer, btnPhaseSopenTransformer, btnPhaseScloseTransformer, btnPhaseTopenTransformer, btnPhaseTcloseTransformer, btnEquipStateInUseTransformer, btnEquipStateInWasteTransformer, btnEquipStateInDisuseTransformer, btnTransformerStateInServiceTransformer, btnTransformerStateDisconnectedTransformer, btnPreviousTransformer3, btnNextTransformer3;
    ImageView ivInfoIcPhaseRTransformer, ivInfoIcPhaseSTransformer, ivInfoIcPhaseTTransformer, ivInfoIcEquipStateTransformer, ivInfoIcTransformerStateTransformer;
    ArrayList<ImageView> ArrayIvInfoIcList = new ArrayList<>();
    TextView tvPhaseRTransformer, tvPhaseSTransformer, tvPhaseTTransformer, tvEquipStateTransformer, tvTransformerState;

    /** Booleanos para validar la selección de los botones */
    boolean isSelectedPhaseR = false, isSelectedPhaseS = false, isSelectedPhaseT = false, isSelectedEquipState = false, isSelectedTransformerState = false;
    /** Arrays para los grupos de botones*/
    ArrayList<Button> buttonListPhaseR, buttonListPhaseS, buttonListPhaseT, buttonListEquipState, buttonListTransformerState;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;



    public Transformer3ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer3_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        btnPhaseRopenTransformer = (Button) rootView.findViewById(R.id.phaseR_open_btn_transformer);
        btnPhaseRcloseTransformer = (Button) rootView.findViewById(R.id.phaseR_close_btn_transformer);
        buttonListPhaseR = new ArrayList<Button>() {{ add(btnPhaseRopenTransformer); add(btnPhaseRcloseTransformer); }};
        btnPhaseSopenTransformer = (Button) rootView.findViewById(R.id.phaseS_open_btn_transformer);
        btnPhaseScloseTransformer = (Button) rootView.findViewById(R.id.phaseS_close_btn_transformer);
        buttonListPhaseS = new ArrayList<Button>() {{ add(btnPhaseSopenTransformer); add(btnPhaseScloseTransformer); }};
        btnPhaseTopenTransformer = (Button) rootView.findViewById(R.id.phaseT_open_btn_transformer);
        btnPhaseTcloseTransformer = (Button) rootView.findViewById(R.id.phaseT_close_btn_transformer);
        buttonListPhaseT = new ArrayList<Button>() {{ add(btnPhaseTopenTransformer); add(btnPhaseTcloseTransformer); }};
        btnEquipStateInUseTransformer = (Button) rootView.findViewById(R.id.PCI_measurer_btn_transformer);
        btnEquipStateInWasteTransformer = (Button) rootView.findViewById(R.id.PCI_embedded_btn_transformer);
        btnEquipStateInDisuseTransformer = (Button) rootView.findViewById(R.id.PCI_no_btn_transformer);
        buttonListEquipState = new ArrayList<Button>() {{ add(btnEquipStateInUseTransformer); add(btnEquipStateInWasteTransformer); add(btnEquipStateInDisuseTransformer); }};
        btnTransformerStateInServiceTransformer = (Button) rootView.findViewById(R.id.transformerState_inService);
        btnTransformerStateDisconnectedTransformer = (Button) rootView.findViewById(R.id.transformerState_disconnected_btn_transformer);
        buttonListTransformerState = new ArrayList<Button>() {{ add(btnTransformerStateInServiceTransformer); add(btnTransformerStateDisconnectedTransformer); }};

        tvPhaseRTransformer = rootView.findViewById(R.id.textView1);
        tvPhaseSTransformer = rootView.findViewById(R.id.textView2);
        tvPhaseTTransformer = rootView.findViewById(R.id.textView3);
        tvEquipStateTransformer = rootView.findViewById(R.id.textView4);
        tvTransformerState = rootView.findViewById(R.id.textView5);

        ivInfoIcPhaseRTransformer = (ImageView) rootView.findViewById(R.id.info_ic_phaseR_iv_transformer);
        ivInfoIcPhaseSTransformer = (ImageView) rootView.findViewById(R.id.info_ic_phaseS_iv_transformer);
        ivInfoIcPhaseTTransformer = (ImageView) rootView.findViewById(R.id.info_ic_phaseT_iv_transformer);
        ivInfoIcEquipStateTransformer = (ImageView) rootView.findViewById(R.id.info_ic_pci_active_iv_transformer);
        ivInfoIcTransformerStateTransformer = (ImageView) rootView.findViewById(R.id.info_ic_transformerState_iv_transformer);

        ArrayIvInfoIcList.add(ivInfoIcPhaseRTransformer);
        ArrayIvInfoIcList.add(ivInfoIcPhaseSTransformer);
        ArrayIvInfoIcList.add(ivInfoIcPhaseTTransformer);
        ArrayIvInfoIcList.add(ivInfoIcEquipStateTransformer);
        ArrayIvInfoIcList.add(ivInfoIcTransformerStateTransformer);

        for (ImageView infoIcon: ArrayIvInfoIcList
             ) {
            infoIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.toastMessage("Seleccione una opción.", 0, getContext());
                }
            });
        }

        btnPhaseRopenTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseR, btnPhaseRopenTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseR = true;
                Globals.mapTransformerActivityData.put("phaseR", "Abierto");
            }
        });
        btnPhaseRcloseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseR, btnPhaseRcloseTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseR = true;
                Globals.mapTransformerActivityData.put("phaseR", "Cerrado");
            }
        });
        btnPhaseSopenTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseS, btnPhaseSopenTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseS = true;
                Globals.mapTransformerActivityData.put("phaseS", "Abierto");
            }
        });
        btnPhaseScloseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseS, btnPhaseScloseTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseS = true;
                Globals.mapTransformerActivityData.put("phaseS", "Cerrado");
            }
        });
        btnPhaseTopenTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseT, btnPhaseTopenTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseT = true;
                Globals.mapTransformerActivityData.put("phaseT", "Abierto");
            }
        });
        btnPhaseTcloseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPhaseT, btnPhaseTcloseTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedPhaseT = true;
                Globals.mapTransformerActivityData.put("phaseT", "Cerrado");
            }
        });
        btnEquipStateInUseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListEquipState, btnEquipStateInUseTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedEquipState = true;
                Globals.mapTransformerActivityData.put("equipState", "En uso");
            }
        });
        btnEquipStateInWasteTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListEquipState, btnEquipStateInWasteTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedEquipState = true;
                Globals.mapTransformerActivityData.put("equipState", "En deshecho");
            }
        });
        btnEquipStateInDisuseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListEquipState, btnEquipStateInDisuseTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedEquipState = true;
                Globals.mapTransformerActivityData.put("equipState", "En desuso");
            }
        });
        btnTransformerStateInServiceTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListTransformerState, btnTransformerStateInServiceTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedTransformerState = true;
                Globals.mapTransformerActivityData.put("transformerState", "En servicio");
            }
        });
        btnTransformerStateDisconnectedTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListTransformerState, btnTransformerStateDisconnectedTransformer, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);

                isSelectedTransformerState = true;
                Globals.mapTransformerActivityData.put("transformerState", "Desconectado");
            }
        });

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer3 = rootView.findViewById(R.id.previous_btn_transformer3);
        btnPreviousTransformer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer3 = rootView.findViewById(R.id.next_btn_transformer3);
        btnNextTransformer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected +1,true);
                }
            }
        });

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada */
        disableElementsByState();

        return  rootView;
    }

    public boolean verifyInformation(){

        if(isSelectedPhaseR && isSelectedPhaseS && isSelectedPhaseT && isSelectedEquipState && isSelectedTransformerState){
            Globals.boolArrayCompleteInfrastructurePages.set(2,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(2,false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            //En caso de tener los campos necesarios completos, puede pasar a la siguiente
            if (isSelectedPhaseR && isSelectedPhaseS && isSelectedPhaseT && isSelectedEquipState && isSelectedTransformerState) {
                return true;
            }
        }

        if(isSelectedPhaseR == false){
            Utils.toastMessage("Debe de seleccionar una opción de estado de fase R.",1,getContext());
        }
        if(isSelectedPhaseS == false){
            Utils.toastMessage("Debe de seleccionar una opción de estado de fase S.",1,getContext());
        }
        if(isSelectedPhaseT == false){
            Utils.toastMessage("Debe de seleccionar una opción de estado de fase T.",1,getContext());
        }
        if(isSelectedEquipState == false){
            Utils.toastMessage("Debe de seleccionar una opción de estado de equipo.",1,getContext());
        }
        if(isSelectedTransformerState == false){
            Utils.toastMessage("Debe de seleccionar una opción de estado de transformador.",1,getContext());
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            isSelectedPhaseR = Utils.changeButtonSelectionFromDB(buttonListPhaseR, transformerActivity.getStrPhaseR(), Globals.mapTransformerActivityData, "phaseR", isSelectedPhaseR, getContext());
            isSelectedPhaseS = Utils.changeButtonSelectionFromDB(buttonListPhaseS, transformerActivity.getStrPhaseS(), Globals.mapTransformerActivityData, "phaseS", isSelectedPhaseS, getContext());
            isSelectedPhaseT = Utils.changeButtonSelectionFromDB(buttonListPhaseT, transformerActivity.getStrPhaseT(), Globals.mapTransformerActivityData, "phaseT", isSelectedPhaseT, getContext());
            isSelectedEquipState = Utils.changeButtonSelectionFromDB(buttonListEquipState, transformerActivity.getStrEquipState(), Globals.mapTransformerActivityData, "equipState", isSelectedEquipState, getContext());
            isSelectedTransformerState = Utils.changeButtonSelectionFromDB(buttonListTransformerState, transformerActivity.getStrTransformerState(), Globals.mapTransformerActivityData, "transformerState", isSelectedTransformerState, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(isSelectedPhaseR == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPhaseRTransformer, ivInfoIcPhaseRTransformer, getContext());
            }
            if(isSelectedPhaseS == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPhaseSTransformer, ivInfoIcPhaseSTransformer, getContext());
            }
            if(isSelectedPhaseT == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPhaseTTransformer, ivInfoIcPhaseTTransformer, getContext());
            }
            if(isSelectedEquipState == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvEquipStateTransformer, ivInfoIcEquipStateTransformer, getContext());
            }
            if(isSelectedTransformerState == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvTransformerState, ivInfoIcTransformerStateTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){

            //Desabilitar botones
            btnPhaseRcloseTransformer.setEnabled(false);
            btnPhaseRopenTransformer.setEnabled(false);

            btnPhaseScloseTransformer.setEnabled(false);
            btnPhaseSopenTransformer.setEnabled(false);

            btnPhaseTcloseTransformer.setEnabled(false);
            btnPhaseTopenTransformer.setEnabled(false);

            btnEquipStateInUseTransformer.setEnabled(false);
            btnEquipStateInWasteTransformer.setEnabled(false);
            btnEquipStateInDisuseTransformer.setEnabled(false);

            btnTransformerStateInServiceTransformer.setEnabled(false);
            btnTransformerStateDisconnectedTransformer.setEnabled(false);
        }
    }
}