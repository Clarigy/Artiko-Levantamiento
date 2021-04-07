package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport;

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
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;


public class LowVoltageSupport1ActivityFragment extends Fragment {

    EditText etSupportHeight, etCircuitsSupport;
    Spinner spnLoadSupport, spnFoundationSupport;
    TextView tvSupportHeight, tvLoadSupport, tvFoundationSupport, tvCircuitsSupport, tvDeleteActivity;
    ImageView ivInfoSupportHeight, ivInfoLoadSupport, ivInfoFoundationSupport, ivInfoCircuitsSupport;
    Button btnFailActivity, btnNextActivity;

    public LowVoltageSupport1ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support1_activity, container, false);

        etSupportHeight = rootView.findViewById(R.id.supportHeight_et_lvSupport);
        etSupportHeight.setTransformationMethod(null);
        etCircuitsSupport = rootView.findViewById(R.id.circuits_et_lvSupport);
        etCircuitsSupport.setTransformationMethod(null);
        spnLoadSupport = rootView.findViewById(R.id.load_spn_lvSupport);
        spnFoundationSupport = rootView.findViewById(R.id.foundation_spn_lvSupport);
        tvSupportHeight = rootView.findViewById(R.id.textView1);
        tvLoadSupport = rootView.findViewById(R.id.textView2);
        tvFoundationSupport = rootView.findViewById(R.id.textView3);
        tvCircuitsSupport = rootView.findViewById(R.id.textView4);
        ivInfoSupportHeight = rootView.findViewById(R.id.info_ic_supportHeight_iv_lvSupport);
        ivInfoLoadSupport = rootView.findViewById(R.id.info_ic_load_iv_lvSupport);
        ivInfoFoundationSupport = rootView.findViewById(R.id.info_ic_foundation_iv_lvSupport);
        ivInfoCircuitsSupport = rootView.findViewById(R.id.info_ic_circuits_iv_lvSupport);
        btnFailActivity = rootView.findViewById(R.id.failed_btn_lvSupport);
        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport);
        tvDeleteActivity = rootView.findViewById(R.id.deleteActivity_tv_lvSupport);
        /** Se muestra el mensaje para eliminar la actividad en caso de que sea creada */
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
                            Utils.deleteLowVoltageSupportActivityToSQLite(getContext(), Globals.cardGeneralActivitySelected.getStrId());
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

        /** Se incluye la información a los Spinners */
        String[] optionLoad = {"","510","750","3000","1704","22745,8","2500","1250","1030","1600","2013","1440","8092","7341,5","1510","1350","2000","1050"};
        ArrayAdapter<String> adapterLoad = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, optionLoad);
        spnLoadSupport.setAdapter(adapterLoad);
        String[] optionFoundation = {"","Cimentación cilíndrica","Cimentación cuadrada","NO","Directa a tierra"};
        ArrayAdapter<String> adapterFoundation = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionFoundation);
        spnFoundationSupport.setAdapter(adapterFoundation);

        /** Mensaje toast para los iconos de información */
        ivInfoSupportHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la altura del apoyo.", 0, getContext());
            }
        });
        ivInfoLoadSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoFoundationSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoCircuitsSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la cantidad de circuitos en el apoyo.", 0, getContext());
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

        /** Se obtiene la información que va diligenciando para guardarla inmediatamente */
        saveInstantInformation();

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada, las fallidas no tendrán habilitado el botón de marcar como fallida */
        disableElementsByState();

        return rootView;
    }

    private boolean verifyInformation(){
        /** Se trae la información que haya llenado hasta el momento */
        String height = etSupportHeight.getText().toString();
        String load = spnLoadSupport.getSelectedItem().toString();
        String foundation = spnFoundationSupport.getSelectedItem().toString();
        String circuits = etCircuitsSupport.getText().toString();

        if( !TextUtils.isEmpty(height) && !TextUtils.isEmpty(load) && !TextUtils.isEmpty(foundation) && !TextUtils.isEmpty(circuits)){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(0, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(0, false);
        }
        if(TextUtils.isEmpty(height)){
            etSupportHeight.setError("Este campo no puede quedar vacío");
        }
        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(load)){
            TextView errorText = (TextView) spnLoadSupport.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(foundation)){
            TextView errorText = (TextView) spnFoundationSupport.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(circuits)){
            etCircuitsSupport.setError("Este campo no puede quedar vacío");
        }

        return true;
    }

    /** Función que se mostrara al aceptar en la ventana emergente */
    public void failActivity(){
        String id = Globals.cardGeneralActivitySelected.getStrId();

        /** Actualiza estado y razon por la que fue fallida en la CARD */
        Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedReasonActivity, id);
        Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedState, id);

        /** Asigno el id de la card finalizada */
        Globals.mapLowVoltageSupportActivityData.put("idActivity", id);
        /** Actualiza estado y razon por la que fue fallida en el mapa que contiene la información de la actividad */
        Globals.mapLowVoltageSupportActivityData.put("isFailed", true);
        Globals.mapLowVoltageSupportActivityData.put("failReason", Globals.strFailedReasonActivity);
        Globals.mapLowVoltageSupportActivityData.put("failDetail", Globals.strFailedDetailActivity);

        Utils.updateLowVoltageSupportActivityObjToSQLite(getContext());

        /** Se actualiza en la base de datos*/
        int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
        CardGeneralActivity generalActivity = Globals.dataInfrastructureSurveyActivitiesArray.get(position);
        Date dateFinishActivity = Utils.currentTime();
        generalActivity.setDateStartFailed(Globals.dateStartActivity);
        generalActivity.setDateFinishFailed(dateFinishActivity);

        Utils.updateGeneralActivityToSQLite(getContext(), generalActivity);

        /** Se limpia el mapa de información de la actividad */
        Globals.mapLowVoltageSupportActivityData = new HashMap();

        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;
        ((MainActivity)getActivity()).onlyBack();
        Utils.toastMessage("La actividad ha sido marcada como fallida", 1, getContext());
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            etSupportHeight.setText(lowVoltageSupportActivity.getIntSupportHeight() == 0 ? "" : String.valueOf(lowVoltageSupportActivity.getIntSupportHeight()));
            spnLoadSupport.setSelection(((ArrayAdapter<String>)spnLoadSupport.getAdapter()).getPosition(lowVoltageSupportActivity.getStrLoadSupport()));
            spnFoundationSupport.setSelection(((ArrayAdapter<String>)spnFoundationSupport.getAdapter()).getPosition(lowVoltageSupportActivity.getStrFoundationSupport()));
            etCircuitsSupport.setText(lowVoltageSupportActivity.getIntCircuitsSupport() == 10 ? "" : String.valueOf(lowVoltageSupportActivity.getIntCircuitsSupport()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){
        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar spinner si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnLoadSupport,getContext());
            Utils.changeSpinnerStyleDisable(spnFoundationSupport,getContext());
            Utils.changeEditTextStyleDisable(etSupportHeight, getContext());
            Utils.changeEditTextStyleDisable(etCircuitsSupport, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnLoadSupport.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvLoadSupport, ivInfoLoadSupport, getContext());
            }
            if(TextUtils.isEmpty(spnFoundationSupport.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvFoundationSupport, ivInfoFoundationSupport, getContext());
            }

            if(TextUtils.isEmpty(etSupportHeight.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSupportHeight, ivInfoSupportHeight, getContext());
            }
            if(TextUtils.isEmpty(etCircuitsSupport.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvCircuitsSupport, ivInfoCircuitsSupport, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etSupportHeight, Globals.mapLowVoltageSupportActivityData, "height", "int");
        Utils.addListenerSpinner(spnLoadSupport, Globals.mapLowVoltageSupportActivityData, "load");
        Utils.addListenerSpinner(spnFoundationSupport, Globals.mapLowVoltageSupportActivityData, "foundation");
        Utils.addListenerEditText(etCircuitsSupport, Globals.mapLowVoltageSupportActivityData, "circuits", "int");
    }
}