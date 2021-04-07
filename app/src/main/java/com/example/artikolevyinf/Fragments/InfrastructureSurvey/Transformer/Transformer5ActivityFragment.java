package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

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

public class Transformer5ActivityFragment extends Fragment {

    ImageView ivInfoIcObservationsTransformer;
    EditText etObservationsTransformer;
    Button btnPreviousTransformer5, btnNextTransformer5;
    TextView tvObservationTransformer;

    public Transformer5ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer5_activity, container, false);

        ivInfoIcObservationsTransformer = (ImageView) rootView.findViewById(R.id.info_ic_observations_iv_lowVoltage);
        etObservationsTransformer = (EditText) rootView.findViewById(R.id.observations_et_lowVoltage);

        ivInfoIcObservationsTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite las observaciones encontradas.",0,getContext());
            }
        });

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer5 = rootView.findViewById(R.id.previous_btn_lowVoltage3);
        btnPreviousTransformer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer5 = rootView.findViewById(R.id.next_btn_lowVoltage3);
        btnNextTransformer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected +1,true);
                }
            }
        });

        tvObservationTransformer = rootView.findViewById(R.id.textView1);

        /** Se obtiene la información que va diligenciando para guardarla inmediatamente */
        saveInstantInformation();

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada */
        disableElementsByState();

        return  rootView;
    }

    public boolean verifyInformation(){
        //True porque todos los campos de esta pag son opcionales
        boolean informationComplete = true;

        String observationTranformer = etObservationsTransformer.getText().toString();

        if(!TextUtils.isEmpty(observationTranformer)){
            Globals.boolArrayCompleteInfrastructurePages.set(4,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(4,false);
        }

        return informationComplete;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            String observationsTransformer = transformerActivity.getStrObservation();
            etObservationsTransformer.setText(observationsTransformer);
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(TextUtils.isEmpty(etObservationsTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvObservationTransformer, ivInfoIcObservationsTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text y spinner
            Utils.changeEditTextStyleDisable(etObservationsTransformer, getContext());
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etObservationsTransformer, Globals.mapTransformerActivityData, "observation", "string");
    }
}