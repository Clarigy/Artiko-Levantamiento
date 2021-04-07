package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

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
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class Transformer7ActivityFragment extends Fragment {

    EditText etPrimaryVoltageTransformer;
    Spinner spnConnectionTypeTransformer, spnTransformerType;
    SearchableSpinner spnSecondaryVoltageTransformer;
    ImageView ivInfoIcPrimaryVoltageTransformer, ivInfoIcSecondaryVoltageTransformer, ivInfoIcConnectionTypeTransformer, ivInfoIcTransformerTypeTransformer;
    Button btnNextTransformer7, btnPreviousTransformer7;
    TextView tvPrimaryVoltageTransformer, tvSecondaryVoltageTransformer, tvConnectionTypeTransformer, tvTransformerType;

    public Transformer7ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer7_activity, container, false);

        etPrimaryVoltageTransformer = (EditText) rootView.findViewById(R.id.primary_voltage_et_transformer);
        //Se muestran los números del campo de potencia nominal
        etPrimaryVoltageTransformer.setTransformationMethod(null);

        spnSecondaryVoltageTransformer = (SearchableSpinner) rootView.findViewById(R.id.secondary_voltage_spn_transformer);
        spnConnectionTypeTransformer = (Spinner) rootView.findViewById(R.id.connection_type_spn_transformer);
        spnTransformerType = (Spinner) rootView.findViewById(R.id.transformer_type_spn_transformer);

        ivInfoIcPrimaryVoltageTransformer = (ImageView) rootView.findViewById(R.id.info_ic_primary_voltage_iv_transformer);
        ivInfoIcSecondaryVoltageTransformer = (ImageView) rootView.findViewById(R.id.info_ic_secondary_voltage_iv_transformer);
        ivInfoIcConnectionTypeTransformer = (ImageView) rootView.findViewById(R.id.info_ic_connection_type_iv_transformer);
        ivInfoIcTransformerTypeTransformer = (ImageView) rootView.findViewById(R.id.info_ic_transformer_type_iv_transformer);

        tvPrimaryVoltageTransformer = rootView.findViewById(R.id.textView1);
        tvSecondaryVoltageTransformer = rootView.findViewById(R.id.textView2);
        tvConnectionTypeTransformer = rootView.findViewById(R.id.textView3);
        tvTransformerType = rootView.findViewById(R.id.textView4);

        //Se escucha cada cambio del campo de texto y se toma el string para concatenar una "," en los valores
        etPrimaryVoltageTransformer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Utils.addCharToString(etPrimaryVoltageTransformer, ".",6);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Se llenan los spinner
        String[] secondaryVoltageArray = {"","4600/2655.8","440","240/120","220/127","480/277.1","203/115","240/120","225/123","132/128","440/254","127/220","4160/2401.8","214/123","216/125","480/277","480/240","492/285","261/252","6200/220","380/219","460","498/288","451/261","4160/2401.7","390/225","390/195","0","4350/2511","2400/1386","225/130","231/133","456/263","440/220","228/132","227/131","229/132","462/231","226/130","457/267","495/286","6900/3984","224/112","458/264","218/125.9","460/215","459/265","389/224","452/260","34500/6300","13200/620","462","490","213/123","214/123.6","215/124","400/230.9","225/129","400/230","492/284.1","230/126","440/253","460/260","455/262.7","34500/19918.6","261/150.7","261/130.5","254/146.6","240/138.6","232/133.9","228.6/132","225/112.5","214/107","218/109","203/117.2","13200/7621","400/231","414/239","460/268","226/132","455/268","457/264","496/286","460/265.6","451/260.4","474/273.7","13750/7939","10455/6036","408","457/263.8","453/262","208/120","226/131","473/273","500/289","228/131","380/220","492/222","460/265","508","13689","13800/7967","476/274.8","231/133.4","212/122","496/286.4","478","420/242","232","450/260","494/285","474/273","452/260","226/113","500/288.7","472/272.5","452/261","216/124.7","456/263.3","220/110","440/220","454/262","240/120","214/124","452/261","438","216/124","453/261","457/263","392/226","225/129.9","230/132.8","458/265","222/128","6000/3464.1","225/132","220/132","225/120","220/127","254/240","450/259.8","460/266","6600/3810","34500","6900/3300","4160/2400","3300","6900/3983.7","1300","231/133.4","13800/7967.4","215/124.1","227/123","232/128","233/134","440/263","462/267","220/110","2400/1385.6","7620","480/277","480/240","216/125","400/122","208/120","216/115","230/133","226/130.5","214/123","217/125","224/129.3","228/131.6","213/122","451/260","262","469/462","454/262.1","476/275","440/254","7240","480/227"};
        ArrayAdapter<String> adapterSecondaryVoltage = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, secondaryVoltageArray);
        spnSecondaryVoltageTransformer.setAdapter(adapterSecondaryVoltage);
        spnSecondaryVoltageTransformer.setTitle("Seleccione la tensión secundaria");
        spnSecondaryVoltageTransformer.setPositiveButton("Aceptar");

        String[] connectionTypeArray = {"", "PARALELO MONOFASICO", "BANCO EN Y", "BANCO DELTA ABIERTO", "BANCO EN SERIE MONOFASICO", "PARALELO TRIFASICO", "BANCO EN Y ABIERTO", "DESCONOCIDO", "BANCO EN DELTA", "TRIFASICO", "MONOFASICO", "0", "MONOFASICO BIFILAR", "SI", "NO"};
        ArrayAdapter<String> adapteConnectionType = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, connectionTypeArray);
        spnConnectionTypeTransformer.setAdapter(adapteConnectionType);
        String[] transformerTypeArray = {"", "ALUMBRADO PUBLICO", "DISTRIBUCION + ALUMBRADO PUBLICO", "DISTRIBUCION", "AUTOTRANSFORMADOR", "EQUIPO TELECONTROLADO", "CAMARA VIGILANCIA"};
        ArrayAdapter<String> adapteTransformerType = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, transformerTypeArray);
        spnTransformerType.setAdapter(adapteTransformerType);

        ivInfoIcPrimaryVoltageTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la tensión primaria.",0,getContext());
            }
        });
        ivInfoIcSecondaryVoltageTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcConnectionTypeTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcTransformerTypeTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer7 = rootView.findViewById(R.id.previous_btn_transformer7);
        btnPreviousTransformer7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer7 = rootView.findViewById(R.id.next_btn_transformer7);
        btnNextTransformer7.setOnClickListener(new View.OnClickListener() {
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

    private boolean verifyInformation() {
        //True porque todos los campos de esta pag son opcionales
        boolean informationComplete = true;

        //Strings para validar la información
        String strPrimaryVoltageTransformer = etPrimaryVoltageTransformer.getText().toString();
        String strSecondaryVoltage = spnSecondaryVoltageTransformer.getSelectedItem().toString();
        String strConnectionType = spnConnectionTypeTransformer.getSelectedItem().toString();
        String strTransformerType = spnTransformerType.getSelectedItem().toString();

        if(!TextUtils.isEmpty(strPrimaryVoltageTransformer) && !TextUtils.isEmpty(strSecondaryVoltage) && !TextUtils.isEmpty(strConnectionType) && !TextUtils.isEmpty(strTransformerType)) {
            Globals.boolArrayCompleteInfrastructurePages.set(6,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(6,false);
        }

        return informationComplete;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            etPrimaryVoltageTransformer.setText(transformerActivity.getStrPrimaryVoltage());
            spnSecondaryVoltageTransformer.setSelection(((ArrayAdapter<String>)spnSecondaryVoltageTransformer.getAdapter()).getPosition(transformerActivity.getStrSecondaryVoltage()));
            spnConnectionTypeTransformer.setSelection(((ArrayAdapter<String>)spnConnectionTypeTransformer.getAdapter()).getPosition(transformerActivity.getStrConnectionType()));
            spnTransformerType.setSelection(((ArrayAdapter<String>)spnTransformerType.getAdapter()).getPosition(transformerActivity.getStrTransformerType()));
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(TextUtils.isEmpty(etPrimaryVoltageTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPrimaryVoltageTransformer, ivInfoIcPrimaryVoltageTransformer, getContext());
            }

            if(TextUtils.isEmpty(spnSecondaryVoltageTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSecondaryVoltageTransformer, ivInfoIcSecondaryVoltageTransformer, getContext());
            }

            if(TextUtils.isEmpty(spnConnectionTypeTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvConnectionTypeTransformer, ivInfoIcConnectionTypeTransformer, getContext());
            }

            if(TextUtils.isEmpty(spnTransformerType.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvTransformerType, ivInfoIcTransformerTypeTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text y spinner
            Utils.changeEditTextStyleDisable(etPrimaryVoltageTransformer, getContext());
            Utils.changeSpinnerStyleDisable(spnSecondaryVoltageTransformer,getContext());
            Utils.changeSpinnerStyleDisable(spnConnectionTypeTransformer,getContext());
            Utils.changeSpinnerStyleDisable(spnTransformerType,getContext());
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etPrimaryVoltageTransformer, Globals.mapTransformerActivityData, "primaryVoltage", "string");
        Utils.addListenerSpinner(spnSecondaryVoltageTransformer, Globals.mapTransformerActivityData, "secondaryVoltage");
        Utils.addListenerSpinner(spnConnectionTypeTransformer, Globals.mapTransformerActivityData, "connectionType");
        Utils.addListenerSpinner(spnTransformerType, Globals.mapTransformerActivityData, "transformerType");
    }
}