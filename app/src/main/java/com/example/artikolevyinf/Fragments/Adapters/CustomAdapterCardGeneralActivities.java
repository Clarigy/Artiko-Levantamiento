package com.example.artikolevyinf.Fragments.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Fragments.ActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;

import java.util.ArrayList;

public class CustomAdapterCardGeneralActivities extends RecyclerView.Adapter<CustomAdapterCardGeneralActivities.MyViewHolder>{
    private ArrayList<CardGeneralActivity> dataSet;
    private final Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // Se declaran los elementos que hacen parte del componente cardview_scr_activities
        TextView tvTitle;
        TextView tvName;
        TextView tvSerial;
        TextView tvOrder;
        TextView tvAddress;
        TextView tvFailedReason;
        CardView cvLineColor;
        ImageView ivSyncActivity;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.title_card_general_activities);
            this.tvName = (TextView) itemView.findViewById(R.id.name_card_general_activities);
            this.tvSerial = (TextView) itemView.findViewById(R.id.serial_card_general_activities);
            this.tvOrder = (TextView) itemView.findViewById(R.id.order_card_general_activities);
            this.tvAddress = (TextView) itemView.findViewById(R.id.address_card_general_activities);
            this.tvFailedReason = (TextView) itemView.findViewById(R.id.failed_reason_card_general_activities);
            this.cvLineColor = (CardView) itemView.findViewById(R.id.lineColor);
            this.ivSyncActivity = (ImageView) itemView.findViewById(R.id.ic_syncActivity_iv);
        }

    }

    // Asigna la lista de objetos CardActivity recibida a el array dataSet
    public CustomAdapterCardGeneralActivities(ArrayList<CardGeneralActivity> data, Context mContext) {
        this.dataSet = data;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // se trae el diseño en xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_general_activities, parent, false);

        // Se le asigna un onClick a la card que esta en ActivityFragment
        view.setOnClickListener(ActivityFragment.myOnClickListener);

        CustomAdapterCardGeneralActivities.MyViewHolder myViewHolder = new CustomAdapterCardGeneralActivities.MyViewHolder(view);
        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final CustomAdapterCardGeneralActivities.MyViewHolder holder, final int listPosition) {

        // Se definen los valores traidos del array
        TextView textViewTitle = holder.tvTitle;
        TextView textViewName = holder.tvName;
        TextView textViewSerial = holder.tvSerial;
        TextView textViewOrder = holder.tvOrder;
        TextView textViewAddress = holder.tvAddress;
        TextView textViewFailedReason = holder.tvFailedReason;
        CardView lineColor = holder.cvLineColor;
        ImageView iconSync = holder.ivSyncActivity;

        // Se asignan los valores a los elementos que hacen parte del componente cardview_activities
        if(dataSet.get(listPosition).getStrTitle().length() > 1){
            textViewTitle.setText(dataSet.get(listPosition).getStrActivityTitle() + " - " +dataSet.get(listPosition).getStrTitle());
        }else{
            textViewTitle.setText(dataSet.get(listPosition).getStrActivityTitle());
        }
        textViewName.setText(dataSet.get(listPosition).getStrName());
        textViewSerial.setText(dataSet.get(listPosition).getStrSerial());
        textViewOrder.setText(dataSet.get(listPosition).getStrOrder());
        textViewAddress.setText(dataSet.get(listPosition).getStrAddress());
        textViewFailedReason.setText(dataSet.get(listPosition).getStrFailedReason());

        if(textViewOrder.getText().toString().length() < 1){
            //Se oculta el ultimo parametro
            textViewOrder.setWidth(0);
            textViewOrder.setHeight(0);
        }

        if(textViewFailedReason.getText().toString().length() < 1){
            //Se oculta el ultimo parametro
            textViewFailedReason.setWidth(0);
            textViewFailedReason.setHeight(0);
        }

        if(textViewSerial.getText().toString().length() < 1){
            /** Se oculta el parametro de capacidad si esta vacio (Tramo aereo) */
            textViewSerial.setWidth(0);
            textViewSerial.setHeight(0);
        }

        if(textViewAddress.getText().toString().length() < 1){
            /** Se oculta el parametro de direccion si esta vacio (Tramo aereo) */
            textViewAddress.setWidth(0);
            textViewAddress.setHeight(0);
        }

        /** Icono azul de sincronizar las que ya estan y rojo las que faltan por sincronizar (Actividades ejecutadas) */
            Drawable iconSyncDrawable = mContext.getResources().getDrawable(R.drawable.ic_syncro);
        if(dataSet.get(listPosition).getIsUploadAPI()){
            iconSyncDrawable.setTint(mContext.getResources().getColor(R.color.colorBlue));
            iconSync.setImageDrawable(iconSyncDrawable);
        }else if (dataSet.get(listPosition).getStrState().equals(Globals.strExecuteState)){
            iconSyncDrawable.setTint(mContext.getResources().getColor(R.color.colorRed));
            iconSync.setImageDrawable(iconSyncDrawable);
        }

        int color = mContext.getResources().getColor(R.color.colorBlue);
        String title = dataSet.get(listPosition).getStrActivityTitle();


        lineColor.setCardBackgroundColor(color);
        textViewTitle.setTextColor(color);
    }

    // Retorna la cantidad de cards en el array
    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
