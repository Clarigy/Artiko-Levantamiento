package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Transformer4ActivityFragment extends Fragment {

    EditText etFabDateTransformer, etGroupTransformer, etBrandTransformer;
    ImageView ivInfoIcFabDateTransformer, ivInfoIcGroupTransformer, ivInfoIcV10InstallationTransformer, ivInfoIcBrandTransformer;
    Spinner spnTransformerV10InstallationTransformer;
    Button btnNextTransformer4, btnPreviousTransformer4;
    TextView tvFabDateTransformer, tvGroupTransformer, tvInstallationOriginTransformer, tvBrandTransformer;

    final Calendar myCalendar = Calendar.getInstance();
    Date currentTime;

    public Transformer4ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTime = Calendar.getInstance().getTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_transformer4_activity, container, false);

        etFabDateTransformer = (EditText) rootView.findViewById(R.id.fabDate_et_transformer);
        etGroupTransformer = (EditText) rootView.findViewById(R.id.group_et_transformer);
        etGroupTransformer.setTransformationMethod(null);
        etBrandTransformer = (EditText) rootView.findViewById(R.id.brand_et_transformer);

        spnTransformerV10InstallationTransformer = (Spinner) rootView.findViewById(R.id.transformer_v10Installation_spn_transformer);

        ivInfoIcFabDateTransformer = (ImageView) rootView.findViewById(R.id.info_ic_fabDate_iv_transformer);
        ivInfoIcGroupTransformer = (ImageView) rootView.findViewById(R.id.info_ic_group_iv_transformer);
        ivInfoIcV10InstallationTransformer = (ImageView) rootView.findViewById(R.id.info_ic_v10Installation_iv_transformer);
        ivInfoIcBrandTransformer = (ImageView) rootView.findViewById(R.id.info_ic_brand_iv_transformer);

        //Se crea un listener para la emergente para seleccionar la fecha
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                Globals.mapTransformerActivityData.put("fabricationDate", myCalendar.getTime());
            }
        };
        //Al seleccionar el edit text, se llama la emergente y se cambia el texto a la fecha seleccionada
        etFabDateTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(),R.style.DialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                //Se asigna una fecha máxima para el calendario
                pickerDialog.getDatePicker().setMaxDate(currentTime.getTime());
                pickerDialog.show();
            }
        });

        ivInfoIcFabDateTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione la fecha de fabricación del transformador.",0, getContext());
            }
        });
        ivInfoIcGroupTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite los dos números correspondientes al grupo.",0, getContext());
            }
        });
        ivInfoIcV10InstallationTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0, getContext());
            }
        });
        ivInfoIcBrandTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la marca del transformador.",0, getContext());
            }
        });

        String[] v10InstallationArray = {"","C250-01", "C250-02", "C250-03"};
        ArrayAdapter<String> adapterV10Installation = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, v10InstallationArray);
        spnTransformerV10InstallationTransformer.setAdapter(adapterV10Installation);

        //Botón para ir a la pagina siguiente o la anterior
        btnPreviousTransformer4 = rootView.findViewById(R.id.previous_btn_transformer4);
        btnPreviousTransformer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });
        btnNextTransformer4 = rootView.findViewById(R.id.next_btn_transformer4);
        btnNextTransformer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected +1,true);
                }
            }
        });

        tvFabDateTransformer = rootView.findViewById(R.id.textView1);
        tvGroupTransformer = rootView.findViewById(R.id.textView2);
        tvInstallationOriginTransformer = rootView.findViewById(R.id.textView3);
        tvBrandTransformer = rootView.findViewById(R.id.textView4);

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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etFabDateTransformer.setText(sdf.format(myCalendar.getTime()));
    }

    public boolean verifyInformation(){

        String fabricationDateTransformer = etFabDateTransformer.getText().toString();
        String groupTransformer = etGroupTransformer.getText().toString();
        String installationOriginTransformer = spnTransformerV10InstallationTransformer.getSelectedItem().toString();
        String brandTransformer = etBrandTransformer.getText().toString();

        Globals.mapTransformerActivityData.put("fabricationDate", TextUtils.isEmpty(etFabDateTransformer.getText().toString()) ? null : myCalendar.getTime());

        if(!TextUtils.isEmpty(fabricationDateTransformer) && !TextUtils.isEmpty(groupTransformer) && !TextUtils.isEmpty(installationOriginTransformer) && !TextUtils.isEmpty(brandTransformer)){
            Globals.boolArrayCompleteInfrastructurePages.set(3,true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(3,false);
        }

        return true;
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            etFabDateTransformer.setText(transformerActivity.getDateFabricationDate() == null ? "" : sdf.format(transformerActivity.getDateFabricationDate()));
            etGroupTransformer.setText(transformerActivity.getIntGroup() == 0 ? "" : String.valueOf(transformerActivity.getIntGroup()));
            spnTransformerV10InstallationTransformer.setSelection(((ArrayAdapter<String>)spnTransformerV10InstallationTransformer.getAdapter()).getPosition(transformerActivity.getStrInstallationOrigin()));
            etBrandTransformer.setText(transformerActivity.getStrBrand());
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(TextUtils.isEmpty(etFabDateTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvFabDateTransformer, ivInfoIcFabDateTransformer, getContext());
            }
            if(TextUtils.isEmpty(etGroupTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvGroupTransformer, ivInfoIcGroupTransformer, getContext());
            }
            if(TextUtils.isEmpty(spnTransformerV10InstallationTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvInstallationOriginTransformer, ivInfoIcV10InstallationTransformer, getContext());
            }
            if(TextUtils.isEmpty(etBrandTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvBrandTransformer, ivInfoIcBrandTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text y spinner
            Utils.changeEditTextStyleDisable(etFabDateTransformer, getContext());
            Utils.changeEditTextStyleDisable(etGroupTransformer, getContext());
            Utils.changeSpinnerStyleDisable(spnTransformerV10InstallationTransformer,getContext());
            Utils.changeEditTextStyleDisable(etBrandTransformer, getContext());
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etGroupTransformer, Globals.mapTransformerActivityData, "group", "int");
        Utils.addListenerSpinner(spnTransformerV10InstallationTransformer, Globals.mapTransformerActivityData, "installationOrigin");
        Utils.addListenerEditText(etBrandTransformer, Globals.mapTransformerActivityData, "brand", "string");
    }
}