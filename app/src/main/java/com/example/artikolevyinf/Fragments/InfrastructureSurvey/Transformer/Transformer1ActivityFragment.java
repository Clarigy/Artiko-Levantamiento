package com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.MainActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Fragments.ActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Transformer1ActivityFragment extends Fragment {

    //Iconos de información
    ImageView ivInfoIcEnrollTransformer, ivInfoIcimgBeforeTransformer, ivInfoIcTechPlateTransformer, ivinfoIcimgAfterTransformer;
    //Botones
    Button btnCameraBeforeTransformer, btnCameraAfterTransformer, btnFailTransformer1, btnNextTransformer1;
    //LinearLayout
    LinearLayout linearLayoutImagesCameraBeforeTransformer, linearLayoutImagesCameraAfterTransformer;
    EditText etTechnicalPlateTransformer;
    String cameraClicked = "";

    TextView tvEnrollmentTransformer, tvImageBeforeTransformer, tvTechnicalPlateOptionalTransformer, tvImageAfterOptionalTransformer, tvDeleteActivity;


    //Spinners del fragment
    private Spinner spinnerEnrollTransformer;

    public Transformer1ActivityFragment() {
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
        final View rootView = inflater.inflate(R.layout.fragment_transformer1_activity, container, false);
        /** Se limpia la información al entrar para evitar duplicar */
        Globals.mapTransformerActivityData = new HashMap();
        Globals.imageAfterTransformerArray = new ArrayList<>();
        Globals.imageBeforeTransformerArray = new ArrayList<>();
        Globals.imagePlateInstalledTransformerArray = new ArrayList<>();

        //Se traen los iconos de información de la interfaz
        ivInfoIcEnrollTransformer = (ImageView) rootView.findViewById(R.id.info_ic_enroll_iv_transformer);
        ivInfoIcimgBeforeTransformer = (ImageView) rootView.findViewById(R.id.info_ic_image_before_iv_transformer);
        ivInfoIcTechPlateTransformer = (ImageView) rootView.findViewById(R.id.info_ic_technical_plate_iv_transformer);
        ivinfoIcimgAfterTransformer = (ImageView) rootView.findViewById(R.id.info_ic_image_after_iv_transformer);

        /** Se asignan un onclick para cada uno de los iconos de información */
        ivInfoIcEnrollTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione el número de matrícula correspondiente a la entidad a matricular.", 1, getContext());
            }
        });
        ivInfoIcimgBeforeTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Tomar una fotografía del transformador completo.", 0, getContext());
            }
        });
        ivInfoIcTechPlateTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite el número de 5 dígitos de la placa técnica del transformador.", 1, getContext());
            }
        });
        ivinfoIcimgAfterTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Tomar una fotografía de frente a la placa técnica del transformador donde se visualice su capacidad, marca, año de fabricación, tipo, peso del aceite, peso del equipo, serie. ", 1, getContext());
            }
        });

        //ScroolView
        linearLayoutImagesCameraBeforeTransformer = rootView.findViewById(R.id.linear_images_camera_before);
        linearLayoutImagesCameraAfterTransformer = rootView.findViewById(R.id.linear_images_camera_after);

        /** Boton de para abrir la camara */
        btnCameraBeforeTransformer = rootView.findViewById(R.id.camera_before_btn_transformer);
        btnCameraBeforeTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Globals.imageBeforeTransformerArray.size() == 3){
                    Utils.toastMessage("Has alcanzado el límite de 3 fotografías.", 0, getContext());
                }else{
                    cameraClicked = "before";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 111);
                }
            }
        });
        btnCameraAfterTransformer = rootView.findViewById(R.id.camera_after_btn_transformer);
        btnCameraAfterTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Globals.imageAfterTransformerArray.size() == 3){
                    Utils.toastMessage("Has alcanzado el límite de 3 fotografías.", 0, getContext());
                }else {
                    cameraClicked = "after";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 111);
                }
            }
        });
        /** Se cargan las imagenes de la lista global antes */
        for(int i = 0; i < Globals.imageBeforeTransformerArray.size(); i++){
            Utils.addImageCamera(Globals.imageBeforeTransformerArray.get(i), Globals.imageBeforeTransformerArray, true, 3, getContext(), linearLayoutImagesCameraBeforeTransformer);
        }
        /** Se cargan las imagenes de la lista global despues */
        for(int i = 0; i < Globals.imageAfterTransformerArray.size(); i++){
            Utils.addImageCamera(Globals.imageAfterTransformerArray.get(i), Globals.imageAfterTransformerArray, true, 3, getContext(), linearLayoutImagesCameraAfterTransformer);
        }

        /** Se crean los spinners */
        spinnerEnrollTransformer = (Spinner) rootView.findViewById(R.id.enroll_et_transformer);
        String[] optionSAM = {"","A12V34E2113", "B1Q236S548R", "12EQR36S900"};
        ArrayAdapter<String> adapterSAM = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionSAM);
        spinnerEnrollTransformer.setAdapter(adapterSAM);

        /** Botón para ir a la pagina siguiente o marcar la actividad como fallida */
        btnFailTransformer1 = rootView.findViewById(R.id.failed_btn_transformer1);
        btnFailTransformer1.setOnClickListener(new View.OnClickListener() {
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
        btnNextTransformer1 = rootView.findViewById(R.id.next_btn_transformer1);
        btnNextTransformer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()) {
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected + 1, true);
                }
            }
        });

        etTechnicalPlateTransformer = rootView.findViewById(R.id.technical_plate_et_transformer);
        etTechnicalPlateTransformer.setTransformationMethod(null);

        tvEnrollmentTransformer = rootView.findViewById(R.id.textView1);
        tvImageBeforeTransformer = rootView.findViewById(R.id.textView2);
        tvTechnicalPlateOptionalTransformer = rootView.findViewById(R.id.textView3);
        tvImageAfterOptionalTransformer = rootView.findViewById(R.id.textView4);

        tvDeleteActivity = rootView.findViewById(R.id.deleteActivity_tv_transformer);
        if(Globals.cardGeneralActivitySelected.getIsCreated()){
            tvDeleteActivity.setText("Eliminar actividad");
            tvDeleteActivity.setPaintFlags(tvDeleteActivity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            /**Funcionalidad del texto "Cambiar de actividad"*/
            tvDeleteActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /** Función que se enviara a la ventana emergente */
                    Callable<Void> function = new Callable<Void>() {
                        public Void call() {
                            ((MainActivity)getActivity()).showSelectedFragment(new ActivityFragment(), "Main Activities");
                            Utils.toastMessage("La actividad ha sido eliminada", 1, getContext());
                            Utils.deleteGeneralActivityToSQLite(getContext(), Globals.cardGeneralActivitySelected.getStrId());
                            Utils.deleteTransformerActivityToSQLite(getContext(), Globals.cardGeneralActivitySelected.getStrId());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        /** Se actualiza la imagen */
        if(data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if(cameraClicked.equals("before")){
                Utils.addImageCamera(bitmap, Globals.imageBeforeTransformerArray, false, 3, getContext(), linearLayoutImagesCameraBeforeTransformer);
                //ArrayList<Bitmap> arrayImageBefore = new ArrayList<Bitmap>() {{addAll(Globals.imageBeforeTransformerArray);}};
                Globals.mapTransformerActivityData.put("imagesBefore",  Globals.imageBeforeTransformerArray);
            }else if(cameraClicked.equals("after")){
                Utils.addImageCamera(bitmap, Globals.imageAfterTransformerArray, false, 3, getContext(), linearLayoutImagesCameraAfterTransformer);
                //ArrayList<Bitmap> arrayImageAfter = new ArrayList<Bitmap>() {{addAll(Globals.imageAfterTransformerArray);}};
                Globals.mapTransformerActivityData.put("imagesAfter", Globals.imageAfterTransformerArray);
            }
        }

    }

    /** Función que se mostrara al aceptar en la ventana emergente */
    public void failActivity(){
        /**Se actualiza el estado a FALLIDA*/
        String id = Globals.cardGeneralActivitySelected.getStrId();
        String capacity = Globals.mapTransformerActivityData.get("capacity") == null ? "" : Globals.mapTransformerActivityData.get("capacity").toString();
        /** Se obtiene el estado con el que inicia la actividad */
        String activityState = Globals.cardGeneralActivitySelected.getStrState();

        /** Actualiza estado y razon por la que fue fallida en la CARD */
        Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedReasonActivity, id);
        Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedState, id);
        /** En caso de que no tenga capacidad, se pone la que haya llenado en el campo de la pag 2 */
        if(TextUtils.isEmpty(Globals.cardGeneralActivitySelected.getStrSerial())){
            int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
            Globals.dataInfrastructureSurveyActivitiesArray.get(position).setStrSerial(capacity == "" ? "" : "Capacidad "+ capacity);
        }

        /** Actualiza estado y razon por la que fue fallida en el mapa que contiene la información de la actividad */
        Globals.mapTransformerActivityData.put("isFailed", true);
        Globals.mapTransformerActivityData.put("failReason", Globals.strFailedReasonActivity);
        Globals.mapTransformerActivityData.put("failDetail", Globals.strFailedDetailActivity);

        /** Se actualiza la actividad general */
        int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
        CardGeneralActivity generalActivity = Globals.dataInfrastructureSurveyActivitiesArray.get(position);
        Date dateFinishActivity = Utils.currentTime();
        generalActivity.setDateStartFailed(Globals.dateStartActivity);
        generalActivity.setDateFinishFailed(dateFinishActivity);
        Utils.updateGeneralActivityToSQLite(getContext(), generalActivity);

        /** Asigno el id de la card finalizada */
        Globals.mapTransformerActivityData.put("idTransformerActivity", Globals.cardGeneralActivitySelected.getStrId());
        Utils.createTransformerActivityObjToSQLite(getContext());

        /** Se limpia el mapa de información de la actividad */
        Globals.mapTransformerActivityData = new HashMap();
        /**Se limpia y se va hacia atras
        Se limpian las listas de imagenes */
        Globals.imageBeforeTransformerArray.clear();
        Globals.imageAfterTransformerArray.clear();
        Globals.imagePlateInstalledTransformerArray.clear();

        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;
        ((MainActivity)getActivity()).onlyBack();

        Utils.toastMessage("La actividad ha sido marcada como fallida", 1, getContext());
    }

    private boolean verifyInformation(){
        String spnEnroll = spinnerEnrollTransformer.getSelectedItem().toString();
        int lengthImagesBefore = Globals.imageBeforeTransformerArray.size();
        String etTecPlate = etTechnicalPlateTransformer.getText().toString();
        int lengthImagesAfter = Globals.imageAfterTransformerArray.size();

        if(!TextUtils.isEmpty(spnEnroll) && lengthImagesBefore > 0 && !TextUtils.isEmpty(etTecPlate) && lengthImagesAfter > 0){
            Globals.boolArrayCompleteInfrastructurePages.set(0, true);
        }else{
            /**  En caso de que la información este incompleta, se vuelve una actividad ejecutada incompleta al finalizarla. */
            Globals.boolArrayCompleteInfrastructurePages.set(0, false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else{
            if(!TextUtils.isEmpty(spnEnroll) && lengthImagesBefore > 0 ){
                return true;
            }
        }


        if(TextUtils.isEmpty(spnEnroll)){
            TextView errorText = (TextView) spinnerEnrollTransformer.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Este campo no puede quedar vacío");//changes the selected item text to this
        }

        if(lengthImagesBefore == 0){
            Utils.toastMessage("Debe de tomar al menos una foto antes de la matriculación.", 1, getContext());
        }

        return false;
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
        //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
        //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spinnerEnrollTransformer.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvEnrollmentTransformer, ivInfoIcEnrollTransformer, getContext());
            }

            if(Globals.imageBeforeTransformerArray.size() == 0 ){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvImageBeforeTransformer, ivInfoIcimgBeforeTransformer, getContext());
            }

            if(Globals.imageAfterTransformerArray.size() == 0 ){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvImageAfterOptionalTransformer, ivinfoIcimgAfterTransformer, getContext());
            }

            if(TextUtils.isEmpty(etTechnicalPlateTransformer.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvTechnicalPlateOptionalTransformer, ivInfoIcTechPlateTransformer, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState() {
        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar edit text y spinner */
            Utils.changeSpinnerStyleDisable(spinnerEnrollTransformer,getContext());
            Utils.changeEditTextStyleDisable(etTechnicalPlateTransformer, getContext());

            /** Desabilitar el boton fallido y camara */
            btnFailTransformer1.setEnabled(false);
            btnCameraBeforeTransformer.setEnabled(false);
            btnCameraAfterTransformer.setEnabled(false);

            btnFailTransformer1.setBackground(getResources().getDrawable(R.drawable.button_gray));
            btnFailTransformer1.setElevation(0);
        }

        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            btnFailTransformer1.setEnabled(false);
            btnFailTransformer1.setBackground(getResources().getDrawable(R.drawable.button_gray));
            btnFailTransformer1.setElevation(0);
        }

    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            DistributionTransformerActivity transformerActivity = Globals.transformerActivityData;
            spinnerEnrollTransformer.setSelection(((ArrayAdapter<String>)spinnerEnrollTransformer.getAdapter()).getPosition(transformerActivity.getStrEnrollment()));
            for (int i = 0; i < 3; i++) {
                try {
                    Bitmap imageBefore = transformerActivity.getImagesBeforeArray().get(i);
                    Bitmap imageAfter = transformerActivity.getImagesAfterArray().get(i);
                    if(imageBefore != null){
                        Utils.addImageCamera(imageBefore, Globals.imageBeforeTransformerArray, false, 3, getContext(), linearLayoutImagesCameraBeforeTransformer);
                        ArrayList<Bitmap> arrayImageBefore = new ArrayList<Bitmap>() {{addAll(Globals.imageBeforeTransformerArray);}};
                        Globals.mapTransformerActivityData.put("imagesBefore", arrayImageBefore.size() == 0 ? null : arrayImageBefore);
                    }
                    if(imageAfter != null){
                        Utils.addImageCamera(imageAfter, Globals.imageAfterTransformerArray, false, 3, getContext(), linearLayoutImagesCameraAfterTransformer);
                        ArrayList<Bitmap> arrayImageAfter = new ArrayList<Bitmap>() {{addAll(Globals.imageAfterTransformerArray);}};
                        Globals.mapTransformerActivityData.put("imagesAfter", arrayImageAfter.size() == 0 ? null: arrayImageAfter);
                    }
                }catch (Exception e){}
            }
            etTechnicalPlateTransformer.setText(transformerActivity.getIntTechnicalPlate() == 0 ? "" : transformerActivity.getIntTechnicalPlate().toString());
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spinnerEnrollTransformer, Globals.mapTransformerActivityData, "enrollment");
        Utils.addListenerEditText(etTechnicalPlateTransformer, Globals.mapTransformerActivityData, "technicalPlate", "int");
    }
}