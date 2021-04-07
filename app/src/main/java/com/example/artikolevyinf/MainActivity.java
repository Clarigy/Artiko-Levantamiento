package com.example.artikolevyinf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.artikolevyinf.Fragments.ActivityFragment;
import com.example.artikolevyinf.Fragments.Map.MapFragment;
import com.example.artikolevyinf.Fragments.Map.MapInfrastructureFragment;
import com.example.artikolevyinf.Fragments.ProfileFragment;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.UI.Login.LoginActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;
    Toolbar mToolBar;
    boolean blnChangeColor;

    String strArrowBackIconNav;
    String strPhoneIconNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /** bolenado que indicara solo cambio de color */
        blnChangeColor = false;

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mToolBar = findViewById(R.id.appbar);
        setSupportActionBar(mToolBar);

        /** Se muestra el Fragment de activities por defecto */
        showSelectedFragment(new ActivityFragment(), "Activities");

        /** Se selecciona el icono de actividades por defecto */
        mBottomNavigationView.setSelectedItemId(R.id.menu_activity);

        /** Listen change in Bottom Navigation */
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                /** Se cambia el icono del appbar y el texto */
                Drawable icon = getResources().getDrawable(R.drawable.ic_arrow_back);
                updateAppBar(icon, " ");

                if (item.getItemId() == R.id.menu_syncro) {
                    Utils.toastMessage("Se están sincronizando las actividades, por favor espere.", 1, MainActivity.this);

                    /** Se carga la información de la BD para enviarla al servidor */
                    /** Transformador */
                    ArrayList<DistributionTransformerActivity> distributionTransformerActivityArray = new ArrayList<>();
                    distributionTransformerActivityArray.addAll(Utils.getAllTransformerActivitiesToSQLite(MainActivity.this));
                    boolean dataTransformer = Utils.sendDataTransformerActivityFromSQLiteToAPI(distributionTransformerActivityArray, MainActivity.this);
                    /** Tramo aereo */
                    ArrayList<LowVoltageSectionActivity> lowVoltageSectionActivitiesArray = new ArrayList<>();
                    lowVoltageSectionActivitiesArray.addAll(Utils.getAllLowVoltageActivities(MainActivity.this));
                    boolean dataLowVoltage = Utils.sendDataLowVoltageSectionActivityFromSQLiteToAPI(lowVoltageSectionActivitiesArray, MainActivity.this);

                    /** Se valida que si se hayan enviado datos*/
                    if(distributionTransformerActivityArray.size() > 0) {
                        /** Mensaje de confirmación */
                        if (dataTransformer) {
                            Utils.toastMessage("Actividades de transformadores sincronizadas satisfactoriamente.", 1, MainActivity.this);
                        } else {
                            Utils.toastMessage("Error al sincronizar las actividades de transformadores.", 1, MainActivity.this);
                        }
                    }

                    /** Se valida que si se hayan enviado datos*/
                    if(lowVoltageSectionActivitiesArray.size() > 0) {
                        /** Mensaje de confirmación */
                        if (dataLowVoltage) {
                            Utils.toastMessage("Actividades de tramos aereos sincronizadas satisfactoriamente.", 1, MainActivity.this);
                        } else {
                            Utils.toastMessage("Error al sincronizar las actividades de tramos aereos.", 1, MainActivity.this);
                        }
                    }

                    /** Se recarga la pagina de inicio*/
                    blnChangeColor = false;
                    mBottomNavigationView.setSelectedItemId(R.id.menu_activity);
                }
                if (item.getItemId() == R.id.menu_activity) {
                    /** Si no es un cambio de color se llama al nuevp fragment */
                    if (!blnChangeColor) {
                        Globals.strCurrentActivities = Globals.strAllActivities;
                        clearStack();
                        showSelectedFragment(new ActivityFragment(), "Activities");
                    }
                    blnChangeColor = false;

                }
                if (item.getItemId() == R.id.menu_map) {
                    /** Si no es un cambio de color se llama al nuevp fragment */
                    if (!blnChangeColor) {
                        if(Globals.strCurrentActivities.equals(Globals.strInfrastructureSurveyActivities)){
                            showSelectedFragment(new MapInfrastructureFragment(), "Map");
                        }else {
                            showSelectedFragment(new MapFragment(), "Map");
                        }
                    }
                    blnChangeColor = false;
                }

                return true;
            }
        });

    }

    public void showSelectedFragment(Fragment fragment, String tag) {

        /** Se verifica que no exista el fragment */
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (currentFragment == null) {
            /** Se añade el fragment si no existe */
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag)
                    .addToBackStack(tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        } else {
            /** Se elimina el fragment si existe */
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            /** Se añade el fragment para que este quede visible */
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag)
                    .addToBackStack(tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /** actualiza el titulo que se mostrara en el menu y lo dehabilita para clickear */
        MenuItem userName = menu.findItem(R.id.user_name);
        userName.setTitle("Fabian Rivera");
        userName.setEnabled(false);

        MenuItem userPhoto = menu.findItem(R.id.user_photo);
        /** Se crea un bitmap a partir de una imagen del drawable */
        Bitmap iconProfile = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.user);
        /**Se cambia crea un drawable con el bitmap pero redondeado */
        Drawable iconPrfileRounded = new BitmapDrawable(getResources(), getCroppedBitmap(iconProfile));
        /** Se cambia el icono del appbar por el circular */
        userPhoto.setIcon(iconPrfileRounded);

        /** Se cambia el icono del appbar y el texto */
        Drawable icon = getResources().getDrawable(R.drawable.ic_arrow_back);

        updateAppBar(icon, " ");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /** Detecta el item seleccionado en el appbar */
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                /** Se define el icono que tenga actualmente el appbar y el icono de la felcha hacia atras */
                String appbarIcon = String.valueOf(mToolBar.getNavigationIcon().getCurrent());

                /** Si el icono que tiene el appbar es la flecha hacia atras se devuelve */
                if (appbarIcon.equals(strArrowBackIconNav)) {
                    onBackPressed();
                } else if (appbarIcon.equals(strPhoneIconNav)) {
                    Utils.callPhoneNumber("3183926343", this, this);
                }

                return true;

            case R.id.user_profile:
                showSelectedFragment(new ProfileFragment(), "Profile");

                /** Se cambia el icono del appbar y el texto */
                Drawable icon = getResources().getDrawable(R.drawable.ic_phone_white);
                updateAppBar(icon, "Llamar");

                return true;

            case R.id.user_logout:

                /** Función que se enviara a la ventana emergente */
                Callable<Void> function = new Callable<Void>() {
                    public Void call() {
                        /** Se envia a login */
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertInformation("Cerrar Sesión", "Las actividades por hacer de hoy que no alcanzaste a realizar serán reprogramadas." + "\n" + "¿Seguro que desea salir?", MainActivity.this, MainActivity.this, function, null);

                return true;

            case R.id.user_close_terminal:

                /** Función que se enviara a la ventana emergente */
                Callable<Void> functionCloseTerminal = new Callable<Void>() {
                    public Void call() {

                        /** Validar que no existan tareas por hacer*/
                        if(Utils.existToDoActivities(Globals.dataInfrastructureSurveyActivitiesArray)){
                            Utils.toastMessage("Aún hay actividades de levantamiento de infraestructura por hacer. Finalice todas las tareas e inténteloFsynbc nuevamente.", 1, MainActivity.this);
                        }else {
                            /** Se carga la información de la BD para enviarla al servidor */
                            /** Transformador */
                            ArrayList<DistributionTransformerActivity> distributionTransformerActivityArray = new ArrayList<>();
                            distributionTransformerActivityArray.addAll(Utils.getAllTransformerActivitiesToSQLite(MainActivity.this));
                            boolean dataTransformer = Utils.sendDataTransformerActivityFromSQLiteToAPI(distributionTransformerActivityArray, MainActivity.this);
                            /** Tramo aereo */
                            ArrayList<LowVoltageSectionActivity> lowVoltageSectionActivitiesArray = new ArrayList<>();
                            lowVoltageSectionActivitiesArray.addAll(Utils.getAllLowVoltageActivities(MainActivity.this));
                            boolean dataLowVoltage = Utils.sendDataLowVoltageSectionActivityFromSQLiteToAPI(lowVoltageSectionActivitiesArray, MainActivity.this);

                            /** Si el tamaño es cero se vuelven verdaderas*/
                            if (distributionTransformerActivityArray.size() == 0) {
                                dataTransformer = true;
                            }

                            if (lowVoltageSectionActivitiesArray.size() == 0) {
                                dataLowVoltage = true;
                            }

                            /** Mensaje de confirmación */
                            if (dataLowVoltage && dataTransformer) {
                                Utils.toastMessage("Actividades sincronizadas satisfactoriamente.", 1, MainActivity.this);

                                /** Se eliminan de la BD local*/
                                Utils.deleteAllTransformerActivityToSQLite(distributionTransformerActivityArray, MainActivity.this);
                                Utils.deleteAllLowVoltageSectionActivityToSQLite(lowVoltageSectionActivitiesArray, MainActivity.this);
                                Utils.deleteAllGeneralActivityToSQLite(Globals.dataInfrastructureSurveyActivitiesArray, MainActivity.this);

                                /** Se limpian los globals*/
                                Globals.dataInfrastructureSurveyActivitiesArray.clear();

                                /** Actualiza las cards de inicio*/
                                Utils.getActivities();

                                /** Se recarga la pagina de inicio*/
                                blnChangeColor = false;
                                mBottomNavigationView.setSelectedItemId(R.id.menu_activity);
                            } else {
                                Utils.toastMessage("Error al sincronizar las actividades.", 1, MainActivity.this);
                            }
                        }

                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertInformation("Cerrar terminal", "Las actividades por hacer de hoy que no alcanzaste a realizar serán reprogramadas." + "\n" + "¿Seguro que desea salir?", MainActivity.this, MainActivity.this, functionCloseTerminal, null);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        final Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.container);
        final String tagFragment = fragment.getTag();
        if ((tagFragment == "Infrastructure")  && !Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState)) {
            /** Función que se enviara a la ventana emergente */
            Callable<Void> function = new Callable<Void>() {
                public Void call() {
                    onBackLogic(fragment, tagFragment);
                    /** Si se va hacia atras de la pantalla transformer se limpia las listas de imagenes y se reestablece la posicion de paginas */
                    if(tagFragment == "Infrastructure"){
                        Globals.imageAfterTransformerArray.clear();
                        Globals.imageBeforeTransformerArray.clear();
                        Globals.imagePlateInstalledTransformerArray.clear();
                        Globals.intPageSelected = 0;
                    }
                    return null;
                }
            };

            /** VENTANA EMERGENTE, si es si, llama al onBackLogic */
            Utils.alertInformation("Precaución", "¿Está seguro que desea volver a la página anterior?\nEs posible que pierda su progreso actual.", this, this, function, null);
        }
        else {
            onBackLogic(fragment, tagFragment);
        }

    }

    private void onBackLogic(Fragment fragment, String tagFragment){
        /** Se cierra el fragment actual */
        super.onBackPressed();

        /** Obtiene el numero de Fragments */
        int fragmentsCount = getSupportFragmentManager().getBackStackEntryCount();

        /** Se entra si aun hay Fragments para mostrar, si no se cierra la app */
        if (fragmentsCount > 0) {
            /** Se obtiene el tag del fragment que quedo visible */
            fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.container);
            tagFragment = fragment.getTag();

            if(tagFragment.equals("Infrastructure")){
                /** Corregir más adelante, ESTA HACIENDO DOBLE BACK, PIERDE INFORMACIÓN */
                super.onBackPressed();
            }

            /** Se verifica el tab del fragment para seleccionarl el icono el el bottom navigator */
            if ((tagFragment == "Activities") || (tagFragment == "Infrastructure")) {
                if(tagFragment == "Main Activities"){
                    Globals.strCurrentActivities = Globals.strAllActivities;
                }
                blnChangeColor = true;
                mBottomNavigationView.setSelectedItemId(R.id.menu_activity);
            } else if (tagFragment == "Map") {
                blnChangeColor = true;
                mBottomNavigationView.setSelectedItemId(R.id.menu_map);
            }

            /** Se cambia el icono del appbar deacuerdo al fragment */
            if (tagFragment == "Profile") {
                /** Se cambia el icono del appbar y el texto */
                Drawable icon = getResources().getDrawable(R.drawable.ic_phone_white);
                updateAppBar(icon, "Llamar");
            } else {
                /** Se cambia el icono del appbar y el texto */
                Drawable icon = getResources().getDrawable(R.drawable.ic_arrow_back);
                updateAppBar(icon, " ");
            }

        } else {
            moveTaskToBack(true);
        }
    }

    public void onlyBack(){
        /** Se cierra el fragment actual */
        super.onBackPressed();

        /** Se obtiene el fragment visible */
        Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.container);
        String tagFragment = fragment.getTag();

        /** Se verifica el tab del fragment para seleccionarl el icono el el bottom navigator */
        if ((tagFragment == "Activities") || (tagFragment == "Infrastructure")) {
            blnChangeColor = true;
            mBottomNavigationView.setSelectedItemId(R.id.menu_activity);
        } else if (tagFragment == "Map") {
            blnChangeColor = true;
            mBottomNavigationView.setSelectedItemId(R.id.menu_map);
        }
    }

    public void clearStack() {
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }

        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
    }

    public void updateAppBar(Drawable icon, String title) {
        /** Se cambia el icono del appbar y el texto */
        mToolBar.setNavigationIcon(icon);
        mToolBar.setTitle(title);
        /** Se actualiza el icon */
        Drawable currentIcon = mToolBar.getNavigationIcon().getCurrent();
        if (title.equals("Llamar")) {
            strPhoneIconNav = String.valueOf(currentIcon);
        } else {
            strArrowBackIconNav = String.valueOf(currentIcon);
        }
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


}