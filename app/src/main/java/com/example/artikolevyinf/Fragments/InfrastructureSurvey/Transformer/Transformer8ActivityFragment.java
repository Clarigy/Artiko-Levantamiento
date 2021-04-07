package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.MainActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Transformer8ActivityFragment extends Fragment {

    Spinner spnSecondaryNetTransformer, spnResourcesUseTransformer;
    Button btnCameraInstalledPlateTransformer, btnPreviousTransformer, btnFinishedTransformer;
    LinearLayout linearLayoutImagesCameraInstalledPlateTransformer;
    ImageView ivInfoIcSecondaryNetTransformer, ivInfoIcResourcesUseTransformer, ivInfoIcImageInstalledTransformer;
    TextView tvSecondaryNetTransformer, tvResourcesUseTransformer, tvImageInstalledTransformer;

    public Transformer8ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer8_activity, container, false);

        spnSecondaryNetTransformer = (Spinner) rootView.findViewById(R.id.transformer_secondary_net_spn_transformer);
        spnResourcesUseTransformer = (Spinner) rootView.findViewById(R.id.transformer_resources_use_spn_transformer);
        btnCameraInstalledPlateTransformer = (Button) rootView.findViewById(R.id.camera_btn_installed_plate_btn_transformer);
        btnPreviousTransformer = (Button) rootView.findViewById(R.id.previous_btn_transformer8);
        btnFinishedTransformer = (Button) rootView.findViewById(R.id.finish_btn_transformer);
        linearLayoutImagesCameraInstalledPlateTransformer = (LinearLayout) rootView.findViewById(R.id.linear_images_Installed_Plate_Transformer);
        ivInfoIcSecondaryNetTransformer = (ImageView) rootView.findViewById(R.id.info_ic_secondary_net_iv_transformer);
        ivInfoIcResourcesUseTransformer = (ImageView) rootView.findViewById(R.id.info_ic_resources_use_iv_transformer);
        ivInfoIcImageInstalledTransformer = (ImageView) rootView.findViewById(R.id.info_ic_image_installed_iv_transformer);

        tvSecondaryNetTransformer = rootView.findViewById(R.id.textView1);
        tvResourcesUseTransformer = rootView.findViewById(R.id.textView2);
        tvImageInstalledTransformer = rootView.findViewById(R.id.textView3);

        //Se crean los spinners
        String[] optionSecondaryNet = {"","NO APLICA", "RED CHILENA CON MED. CENTRALIZADA", "RED BT EXTREMO CRUCETA LMT CON MED. CENTRALIZADA", "PROTECCION TRAFO CON BIGOTE", "RED ABIERTA", "RED ABIERTA CON MED. CENTRALIZADA", "ACOMETIDA BT", "TIPO RED BT SUBTERRANEA", "CUADRUPLEX 4/0", "CAJA EN MEDIO DE BAJANTES", "RED CHILENA", "RED CHILENA CON CAJA ENCIMA DE TRAFO", "RED BT EN EXTREMO DE CRUCETA DE LMT", "RED BT CON ANILLO A 7.6 KV", "RED BT CON HILO A 7.6 KV", "RED TRENZADA CON MED CENTRALIZADA", "RED TRENZADA", "USO EXCLUSIVO DEL CLIENTE", "PENDIENTE"};
        ArrayAdapter<String> adapterSecondaryNet = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionSecondaryNet);
        spnSecondaryNetTransformer.setAdapter(adapterSecondaryNet);
        //Se crean los spinners
        String[] optionResourcesUse = {"","USO GENERAL (FAER)", "USO GENERAL (PRONE)", "USO EXCLUSIVO","USO GENERAL (MUNICIPIOS)", "USO GENERAL (PROPIOS)", "USO GENERAL (PARTICULAR)", "USO (POR VERIFICAR)", "USO GENERAL (FNR)", "USO GENERAL (PGN)", "USO GENERAL (NO CONTRATADO)"};
        ArrayAdapter<String> adapterResourcesUse = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionResourcesUse);
        spnResourcesUseTransformer.setAdapter(adapterResourcesUse);

        //Botón que llama a la camara
        btnCameraInstalledPlateTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 111);
            }
        });
        //Se cargan las imagenes de la lista global antes
        for(int i = 0; i < Globals.imagePlateInstalledTransformerArray.size(); i++){
            Utils.addImageCamera(Globals.imagePlateInstalledTransformerArray.get(i), Globals.imagePlateInstalledTransformerArray, true, 3, getContext(), linearLayoutImagesCameraInstalledPlateTransformer);
        }
        //Boton para marcar actividad fallida
        btnPreviousTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });

        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState)){
            btnFinishedTransformer.setText("Salir");
            btnFinishedTransformer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).onlyBack();
                }
            });
        }else{
            //Boton para marcar actividad finalizada
            btnFinishedTransformer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(verifyInformation()) {
                        /** Se verifica que todas las paginas estan completas o incompletas */
                        if (Globals.boolArrayCompleteInfrastructurePages.contains(false)) {
                            Globals.boolIsActivityFinishComplete = false;
                        } else {
                            Globals.boolIsActivityFinishComplete = true;
                        }

                        /** Función que se enviara a la ventana emergente */
                        Callable<Void> function = new Callable<Void>() {
                            public Void call() {
                                /** finaliza la tarea */
                                finishActivity();
                                return null;
                            }
                        };

                        if (Globals.boolIsActivityFinishComplete) {
                            /** Se abre la ventana emergente */
                            Utils.alertInformation("Finalizar Orden", "Recuerde que una vez finalizada la orden no podrá modificarla." + "\n" + "¿Seguro que desea finalizarla?", getContext(), getActivity(), function, null);
                        } else {
                            Utils.alertInformation("Finalizar Orden", "Al finalizar la orden, esta quedará marcada como fallida debido a que la información esta incompleta." + "\n" + "¿Seguro que desea finalizar la orden?", getContext(), getActivity(), function, null);
                        }
                    }

                }
            });
        }

        ivInfoIcSecondaryNetTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcResourcesUseTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.",0,getContext());
            }
        });
        ivInfoIcImageInstalledTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Tomar una fotografía del transformador completo, donde se visualice la placa amarilla.",1,getContext());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            //Se actualiza la imagen
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Utils.addImageCamera(bitmap, Globals.imagePlateInstalledTransformerArray, false, 3, getContext(), linearLayoutImagesCameraInstalledPlateTransformer);
            ArrayList<Bitmap> arrayImagePlate = new ArrayList<Bitmap>() {{addAll(Globals.imagePlateInstalledTransformerArray);}};
            Globals.mapTransformerActivityData.put("imageInstalledPlate", arrayImagePlate.size() == 0 ? null: arrayImagePlate);
        }
    }

    private void finishActivity() {
        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;

        /** Se actualiza el estado */
        String id = Globals.cardGeneralActivitySelected.getStrId();

        /** Se obtiene el estado con el que inicia la actividad */
        String activityState = Globals.cardGeneralActivitySelected.getStrState();

        /** Se actualiza la capacidad del tranformador si es necesario*/
        String capacity = Globals.mapTransformerActivityData.get("capacity") == null ? "" : Globals.mapTransformerActivityData.get("capacity").toString();

        /** En caso de que no tenga capacidad, se pone la que haya llenado en el campo de la pag 2 */
        if(TextUtils.isEmpty(Globals.cardGeneralActivitySelected.getStrSerial())){
            int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
            Globals.dataInfrastructureSurveyActivitiesArray.get(position).setStrSerial(capacity == "" ? "" : "Capacidad "+ capacity);
        }

        if(Globals.boolIsActivityFinishComplete){
            /** Estado de finalizada */
            Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strExecuteState, id);
            Globals.mapTransformerActivityData.put("isFailed", false);
            Globals.mapTransformerActivityData.put("failReason", "");
            Globals.mapTransformerActivityData.put("failDetail", "");
            Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, "", id);
            Utils.toastMessage("La actividad ha sido marcada como ejecutada", 1, getContext());
        }else{
            /** Se marca la razon por la que quedo fallida la actividad */
            Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, "Datos incompletos", id);
            /** Estado de finalizada incompleta */
            Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedStateIncomplete, id);
            /** Se asigna el motivo por el cual la actividad queda marcada como fallida "Datos incompletos" */
            Globals.mapTransformerActivityData.put("isFailed", true);
            Globals.mapTransformerActivityData.put("failReason", "Datos incompletos");
            Globals.mapTransformerActivityData.put("failDetail", "Datos incompletos");
            Utils.toastMessage("La actividad ha sido marcada como fallida", 1, getContext());
        }

        /** Se actualiza la actividad general */
        int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
        CardGeneralActivity generalActivity = Globals.dataInfrastructureSurveyActivitiesArray.get(position);
        /** Se guarda la hora min y seg cuando se abre la actividad y se guarda de acuerdo al estado */
        Date dateFinishActivity = Utils.currentTime();
        generalActivity.setDateStartExecuted(Globals.dateStartActivity);
        generalActivity.setDateFinishExecuted(dateFinishActivity);
        Utils.updateGeneralActivityToSQLite(getContext(), generalActivity);

        /** Retorna a la lista de cards */
        ((MainActivity)getActivity()).onlyBack();

        /** Asigno el id de la card finalizada */
        Globals.mapTransformerActivityData.put("idTransformerActivity", Globals.cardGeneralActivitySelected.getStrId());

        /** Se verifica el estado de la actividad para crearla o actualizarla */
        if(activityState.equals(Globals.strTodoState)){
            Utils.createTransformerActivityObjToSQLite(getContext());
        }else {
            Utils.updateTransformerActivityObjToSQLite(getContext());
        }

        /** Se limpia el mapa de información de la actividad */
        Globals.mapTransformerActivityData = new HashMap();
        /** Se limpian las listas de imagenes */
        Globals.imageBeforeTransformerArray.clear();
        Globals.imageAfterTransformerArray.clear();
        Globals.imagePlateInstalledTransformerArray.clear();

        /** Reestablecer valores por defecto */
        Globals.boolArrayCompleteInfrastructurePages = new ArrayList<>();
        Globals.boolIsActivityFinishComplete = true;
        /** Actualiza las cards de inicio*/
        Utils.getActivities();
    }

    private boolean verifyInformation() {
        //True porque todos los campos de esta pag son opcionales
        boolean informationComplete = true;

        //Strings para validar la información
        String strTransformer_secondary_net = spnSecondaryNetTransformer.getSelectedItem().toString();
        String strTransformer_resources_use = spnResourcesUseTransformer.getSelectedItem().toString();

        if(!TextUtils.isEmpty(strTransformer_secondary_net) && !TextUtils.isEmpty(strTransformer_resources_use) && !(Globals.imagePlateInstalledTransformerArray.size() == 0)) {
            Globals.boolArrayCompleteInfrastructurePages.set(7, true);
        }else{
            //  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla.
            Globals.boolArrayCompleteInfrastructurePages.set(7, false);
        }

        return informationComplete;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;

            spnSecondaryNetTransformer.setSelection(((ArrayAdapter<String>)spnSecondaryNetTransformer.getAdapter()).getPosition(transformerActivity.getStrSecondatyNetType()));
            spnResourcesUseTransformer.setSelection(((ArrayAdapter<String>)spnResourcesUseTransformer.getAdapter()).getPosition(transformerActivity.getStrResourcesUse()));

            for (int i = 0; i < 3; i++) {
                try {
                    Bitmap imageInstalledPlate = transformerActivity.getImageInstalledPlateArray().get(i);
                    if(imageInstalledPlate != null){
                        Utils.addImageCamera(imageInstalledPlate, Globals.imagePlateInstalledTransformerArray, false, 3, getContext(), linearLayoutImagesCameraInstalledPlateTransformer);
                        ArrayList<Bitmap> arrayImagePlate = new ArrayList<Bitmap>() {{addAll(Globals.imagePlateInstalledTransformerArray);}};
                        Globals.mapTransformerActivityData.put("imageInstalledPlate", arrayImagePlate.size() == 0 ? null: arrayImagePlate);
                    }
                }catch (Exception e){}
            }
        }
    }

    private void changeTitleColorIconColor(){
        //En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            if(TextUtils.isEmpty(spnSecondaryNetTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSecondaryNetTransformer, ivInfoIcSecondaryNetTransformer, getContext());
            }

            if(TextUtils.isEmpty(spnResourcesUseTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvResourcesUseTransformer, ivInfoIcResourcesUseTransformer, getContext());
            }

            if(Globals.imagePlateInstalledTransformerArray.size() == 0){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvImageInstalledTransformer, ivInfoIcImageInstalledTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        //Validar el estado de la actividad
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            //deshabilitar edit text y spinner
            Utils.changeSpinnerStyleDisable(spnSecondaryNetTransformer,getContext());
            Utils.changeSpinnerStyleDisable(spnResourcesUseTransformer,getContext());

            //deshabilitar botones
            btnCameraInstalledPlateTransformer.setEnabled(false);
        }
    }

    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnSecondaryNetTransformer, Globals.mapTransformerActivityData, "secondaryNetType");
        Utils.addListenerSpinner(spnResourcesUseTransformer, Globals.mapTransformerActivityData, "resourcesUse");
    }

}