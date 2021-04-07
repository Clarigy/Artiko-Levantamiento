package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.artikolevyinf.MainActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Fragments.ActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class LowVoltageSection1ActivityFragment extends Fragment {

    Spinner spnCanalizationLowVoltage;
    SearchableSpinner spnMaterialLowVoltage;
    EditText etFinalSupportLowVoltage, etStartSupportLowVoltage;
    ImageView ivInfoFinalSupportLowVoltage, ivInfoStartSupportLowVoltage, ivInfoMaterialLowVoltage, ivInfoCanalizationLowVoltage;
    Button btnFailActivity, btnNextActivity;
    TextView tvStartSupportLowVoltage, tvFinalSupportLowVoltage, tvMaterialLowVoltage, tvCanalizationLowVoltage, tvDeleteActivity;

    public LowVoltageSection1ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_section1_activity, container, false);

        etFinalSupportLowVoltage = rootView.findViewById(R.id.finalSupport_et_lowVoltage);
        etStartSupportLowVoltage = rootView.findViewById(R.id.startSupport_et_lowVoltage);
        spnMaterialLowVoltage = rootView.findViewById(R.id.material_spn_lowVoltage);
        spnCanalizationLowVoltage = rootView.findViewById(R.id.canalization_spn_lowVoltage);

        ivInfoFinalSupportLowVoltage = rootView.findViewById(R.id.info_ic_finalSupport_iv_lowVoltage);
        ivInfoStartSupportLowVoltage = rootView.findViewById(R.id.info_ic_startSupport_iv_lowVoltage);
        ivInfoMaterialLowVoltage = rootView.findViewById(R.id.info_ic_material_iv_lowVoltage);
        ivInfoCanalizationLowVoltage = rootView.findViewById(R.id.info_ic_canalization_iv_lowVoltage);

        btnFailActivity = rootView.findViewById(R.id.failed_btn_lowVoltage);
        btnNextActivity = rootView.findViewById(R.id.next_btn_lowVoltage);

        tvStartSupportLowVoltage = rootView.findViewById(R.id.textView1);
        tvFinalSupportLowVoltage = rootView.findViewById(R.id.textView2);
        tvMaterialLowVoltage = rootView.findViewById(R.id.textView3);
        tvCanalizationLowVoltage = rootView.findViewById(R.id.textView4);
        tvDeleteActivity = rootView.findViewById(R.id.deleteActivity_tv_lowVoltage);
        if(Globals.cardGeneralActivitySelected.getIsCreated()){
            tvDeleteActivity.setText("Eliminar actividad");
            tvDeleteActivity.setPaintFlags(tvDeleteActivity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            /**Funcionalidad del texto "Eliminar actividad"*/
            tvDeleteActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /** Función que se enviara a la ventana emergente */
                    Callable<Void> function = new Callable<Void>() {
                        public Void call() {
                            ((MainActivity)getActivity()).showSelectedFragment(new ActivityFragment(), "Main Activities");
                            Utils.toastMessage("La actividad ha sido eliminada.", 1, getContext());
                            Utils.deleteGeneralActivityToSQLite(getContext(), Globals.cardGeneralActivitySelected.getStrId());
                            Utils.deleteLowVoltageSectionActivityToSQLite(getContext(), Globals.cardGeneralActivitySelected.getStrId());
                            int positionCards = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray,  Globals.cardGeneralActivitySelected.getStrId());
                            Globals.dataInfrastructureSurveyActivitiesArray.remove(positionCards);
                            /** Actualiza las cards de inicio*/
                            Utils.getActivities();
                            return null;
                        }
                    };
                    Utils.alertInformation("Eliminar actividad", "Esta a punto de eliminar una actividad, si lo hace perderá toda la información de la misma.\n¿Desea continuar?", getContext(), getActivity(), function, null);

                }
            });
        }


        /** Se asigna los valores de los apoyos inicial y final */
        etFinalSupportLowVoltage.setText(Globals.strStartSupportLowVoltageSection);
        etStartSupportLowVoltage.setText(Globals.strFinalSupportLowVoltageSection);

        /** Se incluye la información a los Spinners */
        String[] optionMaterial = {"","ACSR 4 AWG","ACSR 2 AWG","ACSR 1 AWG","ACSR 1/0 AWG","ACSR 2/0 AWG","ACSR 3/0 AWG","ACSR 4/0 AWG","ACSR 266 kcmil","ACSR 336 kcmil","ACSR 397 kcmil","ACSR 477 kcmil","ACSR 605 kcmil","ACSR 795 kcmil","semiaislado 4 AWG","semiaislado 2 AWG","semiaislado 1 AWG","semiaislado 1/0 AWG","semiaislado 2/0 AWG","semiaislado 3/0 AWG","semiaislado 4/0 AWG","semiaislado 266 kcmil","semiaislado 336 kcmil","semiaislado 477 kcmil","semiaislado 795 kcmil","cobre 2 AWG","cobre 1/0 AWG","cobre 2/0 AWG","EPR 2 AWG","EPR 1 AWG","EPR 1/0 AWG","EPR 2/0 AWG","EPR 3/0 AWG","EPR 4/0 AWG","EPR 250 kcmil","EPR 300 kcmil","EPR 350 kcmil","EPR 400 kcmil","EPR 500 kcmil","EPR 600 kcmil","EPR 750 kcmil","aluminio 2 AWG","aluminio 1/0 AWG","aluminio 4/0 AWG","aluminio 500 kcmil","aluminio 750 kcmil","cobre aislado XLP o EPR, 15 kV- 4 AWG","cobre aislado XLP o EPR, 15 kV- 2 AWG","cobre aislado XLP o EPR, 15 kV- 1/0 AWG","cobre aislado XLP o EPR, 15 kV- 2/0 AWG","cobre aislado XLP o EPR, 15 kV- 3/0 AWG","cobre aislado XLP o EPR, 15 kV- 4/0 AWG","cobre aislado XLP o EPR, 15 kV- 300 Kcmil","cobre aislado XLP o EPR, 15 kV- 350 Kcmil","cobre aislado XLP o EPR, 15 kV- 400 Kcmil","AAAC aislado XLP o EPR, 15 kV- 500 Kcmil","AAAC aislado XLP o EPR, 15 kV- 750 Kcmil","ACSR 1 AWG","ACSR 336 kcmil","semiaislado 336 kcmil","EPR 500 kcmil","cobre aislado XLP o EPR, 35 kV- 2 AWG","cobre aislado XLP o EPR, 35 kV- 2/0 AWG","cobre aislado XLP o EPR, 35 kV- 3/0 AWG","cobre aislado XLP o EPR, 35 kV- 4/0 AWG","cobre aislado XLP o EPR, 35 kV- 250 kcmil","cobre aislado XLP o EPR, 35 kV- 300 kcmil","cobre aislado XLP o EPR, 35 kV- 350 kcmil","cobre aislado XLP o EPR, 35 kV- 400 kcmil","cobre aislado XLP o EPR, 35 kV- 500 kcmil","cobre aislado XLP o EPR, 35 kV- 600 kcmil","cobre aislado XLP o EPR, 35 kV- 750 kcmil","desnudo ACSR 266 kcmil","desnudo ACSR 336 kcmil","desnudo ACSR 397 kcmil","desnudo ACSR 477 kcmil","desnudo ACSR 605 kcmil","desnudo ACSR 795 kcmil","cobre aislado XLP 35 kV- 2 AWG","cobre aislado EPR 35 kV- 2 AWG","cobre aislado XLP 35 kV- 2/0 AWG","cobre aislado EPR 35 kV- 2/0 AWG","cobre aislado XLP 35 kV- 3/0 AWG","cobre aislado XLP 35 kV- 4/0 AWG","cobre aislado XLP 35 kV- 250 kcmil","cobre aislado XLP 35 kV- 300 kcmil","cobre aislado XLP 35 kV- 350 kcmil","cobre aislado XLP 35 kV- 400 kcmil","cobre aislado XLP 35 kV- 500 kcmil","cobre aislado XLP 35 kV- 600 kcmil","cobre aislado XLP 35 kV- 750 kcmil","cobre aislado EPR 35 kV- 3/0 AWG","cobre aislado EPR 35 kV- 4/0 AWG","cobre aislado EPR 35 kV- 250 kcmil","cobre aislado EPR 35 kV- 300 kcmil","cobre aislado EPR 35 kV- 350 Kcmil","cobre aislado EPR 35 kV- 400 Kcmil","cobre aislado EPR 35 kV- 500 Kcmil","cobre aislado EPR 35 kV- 600 kcmil","cobre aislado EPR 35 kV- 750 kcmil","cobre aislado XLP 15 kV- 4 AWG","cobre aislado XLP 15 kV- 2 AWG","cobre aislado XLP 15 kV- 1/0 AWG","cobre aislado XLP 15 kV- 2/0 AWG","cobre aislado XLP 15 kV- 3/0 AWG","cobre aislado XLP 15 kV- 4/0 AWG","cobre aislado XLP 15 kV- 300 Kcmil","cobre aislado XLP 15 kV- 350 Kcmil","cobre aislado XLP 15 kV- 400 Kcmil","AAAC aislado XLP 15 kV- 500 Kcmil","AAAC aislado XLP 15 kV- 750 Kcmil","cobre aislado EPR 15 kV- 4 AWG","cobre aislado EPR 15 kV- 2 AWG","cobre aislado EPR 15 kV- 1/0 AWG","cobre aislado EPR 15 kV- 2/0 AWG","cobre aislado EPR 15 kV- 3/0 AWG","cobre aislado EPR 15 kV- 4/0 AWG","cobre aislado EPR 15 kV- 300 Kcmil","cobre aislado EPR 15 kV- 350 Kcmil","cobre aislado EPR 15 kV- 400 Kcmil","AAAC aislado EPR 15 kV- 500 Kcmil","AAAC aislado EPR 15 kV- 750 Kcmil"};
        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionMaterial);
        spnMaterialLowVoltage.setAdapter(adapterMaterial);
        spnMaterialLowVoltage.setTitle("Seleccione el calibre del material");
        spnMaterialLowVoltage.setPositiveButton("Aceptar");
        String[] optionCanalization = {"","Canalización 1", "Canalización 2", "Canalización 3"};
        ArrayAdapter<String> adapterCanalization = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionCanalization);
        spnCanalizationLowVoltage.setAdapter(adapterCanalization);


        /** Mensaje toast para los iconos de información */
        ivInfoFinalSupportLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Este dato fue capturado al elegir el primer apoyo sobre el mapa.", 1, getContext());
            }
        });
        ivInfoStartSupportLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Este dato fue capturado al elegir el segundo apoyo sobre el mapa.", 1, getContext());
            }
        });
        ivInfoMaterialLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoCanalizationLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** OnClic Botones para pasar de pag */
        btnFailActivity.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                /** Se crean las opciones de la ventana emergente */
                String[] options = {"", "Perro bravo", "Reja con candado", "Otro"};

                /** Función que se enviara a la ventana emergente */
                Callable<Void> function = new Callable<Void>() {
                    public Void call() {
                        failActivity();
                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertFail(options, getContext(), getActivity(), function);

                /** Reestablecer valores por defecto - Lista para ver que todas las pag estan completas*/
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<>();
                Globals.boolIsActivityFinishComplete = true;
            }
        });
        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected + 1,true);
                }
            }
        });

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** Se obtiene la información que va diligenciando para guardarla inmediatamente */
        saveInstantInformation();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        disableElementsByState();

        return rootView;
    }

    private boolean verifyInformation(){
        /** Se trae la información que haya llenado hasta el momento */
        //String startSupport = etStartSupportLowVoltage.getText().toString();
        //String finalSupport = etFinalSupportLowVoltage.getText().toString();
        String material = spnMaterialLowVoltage.getSelectedItem().toString();
        String canalization = spnCanalizationLowVoltage.getSelectedItem().toString();

            //!TextUtils.isEmpty(startSupport) && !TextUtils.isEmpty(finalSupport)
        if( !TextUtils.isEmpty(material) && !TextUtils.isEmpty(canalization)){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(0, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(0, false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            if (!TextUtils.isEmpty(material) && !TextUtils.isEmpty(canalization)) {
                return true;
            }
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(material)){
            TextView errorText = (TextView) spnMaterialLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(canalization)){
            TextView errorText = (TextView) spnCanalizationLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }

        return false;
    }

    /** Función que se mostrara al aceptar en la ventana emergente */
    public void failActivity(){
        String id = Globals.cardGeneralActivitySelected.getStrId();
        /** Se obtiene el estado con el que inicia la actividad */
        String activityState = Globals.cardGeneralActivitySelected.getStrState();

        /** Actualiza estado y razon por la que fue fallida en la CARD */
        Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedReasonActivity, id);
        Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedState, id);

        /** Asigno el id de la card finalizada */
        Globals.mapLowVoltageSectionActivityData.put("idActivity", id);
        /** Actualiza estado y razon por la que fue fallida en el mapa que contiene la información de la actividad */
        Globals.mapLowVoltageSectionActivityData.put("isFailed", true);
        Globals.mapLowVoltageSectionActivityData.put("failReason", Globals.strFailedReasonActivity);
        Globals.mapLowVoltageSectionActivityData.put("failDetail", Globals.strFailedDetailActivity);
        //Utils.createLowVoltageSectionActivityObjToSqlite();
        Utils.updateLowVoltageSectionActivityObjToSQLite(getContext());

        /** Se actualiza en la base de datos*/
        int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
        CardGeneralActivity generalActivity = Globals.dataInfrastructureSurveyActivitiesArray.get(position);
        Date dateFinishActivity = Utils.currentTime();
        generalActivity.setDateStartFailed(Globals.dateStartActivity);
        generalActivity.setDateFinishFailed(dateFinishActivity);
        Utils.updateGeneralActivityToSQLite(getContext(), generalActivity);

        /** Se limpia el mapa de información de la actividad */
        Globals.mapLowVoltageSectionActivityData = new HashMap();

        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;
        ((MainActivity)getActivity()).onlyBack();
        Utils.toastMessage("La actividad ha sido marcada como fallida", 1, getContext());
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        LowVoltageSectionActivity lowVoltageSectionActivity = Globals.lowVoltageSectionActivity;
        etStartSupportLowVoltage.setText(lowVoltageSectionActivity.getStrStartSupport());
        etFinalSupportLowVoltage.setText(lowVoltageSectionActivity.getStrFinalSupport());

        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            spnMaterialLowVoltage.setSelection(((ArrayAdapter<String>)spnMaterialLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrMaterial()));
            spnCanalizationLowVoltage.setSelection(((ArrayAdapter<String>)spnCanalizationLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrCanalization()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar spinner si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnMaterialLowVoltage,getContext());
            Utils.changeSpinnerStyleDisable(spnCanalizationLowVoltage,getContext());
        }

        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            btnFailActivity.setEnabled(false);
            btnFailActivity.setBackground(getResources().getDrawable(R.drawable.button_gray));
            btnFailActivity.setElevation(0);
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(etStartSupportLowVoltage.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvStartSupportLowVoltage, ivInfoStartSupportLowVoltage, getContext());
            }

            if(TextUtils.isEmpty(etFinalSupportLowVoltage.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvFinalSupportLowVoltage, ivInfoFinalSupportLowVoltage, getContext());
            }

            if(TextUtils.isEmpty(spnMaterialLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvMaterialLowVoltage, ivInfoMaterialLowVoltage, getContext());
            }

            if(TextUtils.isEmpty(spnCanalizationLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvCanalizationLowVoltage, ivInfoCanalizationLowVoltage, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        Globals.mapLowVoltageSectionActivityData.put("startSupport", etFinalSupportLowVoltage.getText().toString());
        Globals.mapLowVoltageSectionActivityData.put("finalSupport", etStartSupportLowVoltage.getText().toString());
        Utils.addListenerSpinner(spnMaterialLowVoltage, Globals.mapLowVoltageSectionActivityData, "material");
        Utils.addListenerSpinner(spnCanalizationLowVoltage, Globals.mapLowVoltageSectionActivityData, "canalization");
    }
}
