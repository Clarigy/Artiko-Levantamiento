package com.example.artikolevyinf.Model.InfrastructureSurvey;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class DistributionTransformerActivity {

    String strIdTransformerActivity;

    //Parametros del fragment 1
    String strEnrollment;
    ArrayList<Bitmap> imagesBeforeArray;
    Integer intTechnicalPlate;
    ArrayList<Bitmap> imagesAfterArray;

    //Parametros del fragment 2
    String strVegetableOil;
    Double dbTransformerLatitude;
    Double dbTransformerLongitude;
    String strAreaType;
    String strPcbEquip;
    String strType;

    //Parametros del fragment 3
    String strPhaseR;
    String strPhaseS;
    String strPhaseT;
    String strEquipState;
    String strTransformerState;

    //Parametros del fragment 4
    Date dateFabricationDate;
    Integer intGroup;
    String strInstallationOrigin;
    String strBrand;

    //Parametros del fragment 5
    String strObservation;

    //Parametros del fragment 6
    String strActivePci;
    String dbNominalPower;
    String strProperty;
    String strPhaseSequence;
    String strSubnormal;

    //Parametros del fragment 7
    String dbPrimaryVoltage;
    String strSecondaryVoltage;
    String strConnectionType;
    String strTransformerType;

    //Parametros del fragment 8
    String strSecondatyNetType;
    String strResourcesUse;
    ArrayList<Bitmap> imageInstalledPlateArray;

    //En caso de ser una actividad fallida
    Boolean boolIsFailed;
    String strFailReason;
    String strFailDetail;

    public DistributionTransformerActivity(String strIdTransformerActivity, String strEnrollment, ArrayList<Bitmap> imagesBeforeArray, Integer intTechnicalPlate, ArrayList<Bitmap> imagesAfterArray, String strVegetableOil, Double dbTransformerLatitude, Double dbTransformerLongitude, String strAreaType, String strType, String strPcbEquip, String strPhaseR, String strPhaseS, String strPhaseT, String strEquipState, String strTransformerState, Date dateFabricationDate, Integer intGroup, String strInstallationOrigin, String strBrand, String strObservation, String strActivePci, String dbNominalPower, String strProperty, String strPhaseSequence, String strSubnormal, String dbPrimaryVoltage, String strSecondaryVoltage, String strConnectionType, String strTransformerType, String strSecondatyNetType, String strResourcesUse, ArrayList<Bitmap> imageInstalledPlateArray, Boolean boolIsFailed, String strFailReason, String strFailDetail) {
        this.strIdTransformerActivity = strIdTransformerActivity;
        this.strEnrollment = strEnrollment;
        this.imagesBeforeArray = imagesBeforeArray;
        this.intTechnicalPlate = intTechnicalPlate;
        this.imagesAfterArray = imagesAfterArray;
        this.strVegetableOil = strVegetableOil;
        this.dbTransformerLatitude = dbTransformerLatitude;
        this.dbTransformerLongitude = dbTransformerLongitude;
        this.strAreaType = strAreaType;
        this.strType = strType;
        this.strPcbEquip = strPcbEquip;
        this.strPhaseR = strPhaseR;
        this.strPhaseS = strPhaseS;
        this.strPhaseT = strPhaseT;
        this.strEquipState = strEquipState;
        this.strTransformerState = strTransformerState;
        this.dateFabricationDate = dateFabricationDate;
        this.intGroup = intGroup;
        this.strInstallationOrigin = strInstallationOrigin;
        this.strBrand = strBrand;
        this.strObservation = strObservation;
        this.strActivePci = strActivePci;
        this.dbNominalPower = dbNominalPower;
        this.strProperty = strProperty;
        this.strPhaseSequence = strPhaseSequence;
        this.strSubnormal = strSubnormal;
        this.dbPrimaryVoltage = dbPrimaryVoltage;
        this.strSecondaryVoltage = strSecondaryVoltage;
        this.strConnectionType = strConnectionType;
        this.strTransformerType = strTransformerType;
        this.strSecondatyNetType = strSecondatyNetType;
        this.strResourcesUse = strResourcesUse;
        this.imageInstalledPlateArray = imageInstalledPlateArray;
        this.boolIsFailed = boolIsFailed;
        this.strFailReason = strFailReason;
        this.strFailDetail = strFailDetail;
    }

    public String getStrIdTransformerActivity() {
        return strIdTransformerActivity;
    }

    public void setStrIdTransformerActivity(String strIdTransformerActivity) {
        this.strIdTransformerActivity = strIdTransformerActivity;
    }

    public String getStrEnrollment() {
        return strEnrollment;
    }

    public void setStrEnrollment(String strEnrollment) {
        this.strEnrollment = strEnrollment;
    }

    public ArrayList<Bitmap> getImagesBeforeArray() {
        return imagesBeforeArray;
    }

    public void setImagesBeforeArray(ArrayList<Bitmap> imagesBeforeArray) {
        this.imagesBeforeArray = imagesBeforeArray;
    }

    public Integer getIntTechnicalPlate() {
        return intTechnicalPlate;
    }

    public void setIntTechnicalPlate(Integer intTechnicalPlate) {
        this.intTechnicalPlate = intTechnicalPlate;
    }

    public ArrayList<Bitmap> getImagesAfterArray() {
        return imagesAfterArray;
    }

    public void setImagesAfterArray(ArrayList<Bitmap> imagesAfterArray) {
        this.imagesAfterArray = imagesAfterArray;
    }

    public String getStrVegetableOil() {
        return strVegetableOil;
    }

    public void setStrVegetableOil(String strVegetableOil) {
        this.strVegetableOil = strVegetableOil;
    }

    public Double getDbTransformerLatitude() {
        return dbTransformerLatitude;
    }

    public void setDbTransformerLatitude(Double dbTransformerLatitude) {
        this.dbTransformerLatitude = dbTransformerLatitude;
    }

    public Double getDbTransformerLongitude() {
        return dbTransformerLongitude;
    }

    public void setDbTransformerLongitude(Double dbTransformerLongitude) {
        this.dbTransformerLongitude = dbTransformerLongitude;
    }

    public String getStrAreaType() {
        return strAreaType;
    }

    public void setStrAreaType(String strAreaType) {
        this.strAreaType = strAreaType;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getStrPcbEquip() {
        return strPcbEquip;
    }

    public void setStrPcbEquip(String strPcbEquip) {
        this.strPcbEquip = strPcbEquip;
    }

    public String getStrPhaseR() {
        return strPhaseR;
    }

    public void setStrPhaseR(String strPhaseR) {
        this.strPhaseR = strPhaseR;
    }

    public String getStrPhaseS() {
        return strPhaseS;
    }

    public void setStrPhaseS(String strPhaseS) {
        this.strPhaseS = strPhaseS;
    }

    public String getStrPhaseT() {
        return strPhaseT;
    }

    public void setStrPhaseT(String strPhaseT) {
        this.strPhaseT = strPhaseT;
    }

    public String getStrEquipState() {
        return strEquipState;
    }

    public void setStrEquipState(String strEquipState) {
        this.strEquipState = strEquipState;
    }

    public String getStrTransformerState() {
        return strTransformerState;
    }

    public void setStrTransformerState(String strTransformerState) {
        this.strTransformerState = strTransformerState;
    }

    public Date getDateFabricationDate() {
        return dateFabricationDate;
    }

    public void setDateFabricationDate(Date dateFabricationDate) {
        this.dateFabricationDate = dateFabricationDate;
    }

    public Integer getIntGroup() {
        return intGroup;
    }

    public void setIntGroup(Integer intGroup) {
        this.intGroup = intGroup;
    }

    public String getStrInstallationOrigin() {
        return strInstallationOrigin;
    }

    public void setStrInstallationOrigin(String strInstallationOrigin) {
        this.strInstallationOrigin = strInstallationOrigin;
    }

    public String getStrBrand() {
        return strBrand;
    }

    public void setStrBrand(String strBrand) {
        this.strBrand = strBrand;
    }

    public String getStrObservation() {
        return strObservation;
    }

    public void setStrObservation(String strObservation) {
        this.strObservation = strObservation;
    }

    public String getStrActivePci() {
        return strActivePci;
    }

    public void setStrActivePci(String strActivePci) {
        this.strActivePci = strActivePci;
    }

    public String getStrNominalPower() {
        return dbNominalPower;
    }

    public void setDbNominalPower(String dbNominalPower) {
        this.dbNominalPower = dbNominalPower;
    }

    public String getStrProperty() {
        return strProperty;
    }

    public void setStrProperty(String strProperty) {
        this.strProperty = strProperty;
    }

    public String getStrPhaseSequence() {
        return strPhaseSequence;
    }

    public void setStrPhaseSequence(String strPhaseSequence) {
        this.strPhaseSequence = strPhaseSequence;
    }

    public String getStrSubnormal() {
        return strSubnormal;
    }

    public void setStrSubnormal(String strSubnormal) {
        this.strSubnormal = strSubnormal;
    }

    public String getStrPrimaryVoltage() {
        return dbPrimaryVoltage;
    }

    public void setDbPrimaryVoltage(String dbPrimaryVoltage) {
        this.dbPrimaryVoltage = dbPrimaryVoltage;
    }

    public String getStrSecondaryVoltage() {
        return strSecondaryVoltage;
    }

    public void setStrSecondaryVoltage(String strSecondaryVoltage) {
        this.strSecondaryVoltage = strSecondaryVoltage;
    }

    public String getStrConnectionType() {
        return strConnectionType;
    }

    public void setStrConnectionType(String strConnectionType) {
        this.strConnectionType = strConnectionType;
    }

    public String getStrTransformerType() {
        return strTransformerType;
    }

    public void setStrTransformerType(String strTransformerType) {
        this.strTransformerType = strTransformerType;
    }

    public String getStrSecondatyNetType() {
        return strSecondatyNetType;
    }

    public void setStrSecondatyNetType(String strSecondatyNetType) {
        this.strSecondatyNetType = strSecondatyNetType;
    }

    public String getStrResourcesUse() {
        return strResourcesUse;
    }

    public void setStrResourcesUse(String strResourcesUse) {
        this.strResourcesUse = strResourcesUse;
    }

    public ArrayList<Bitmap> getImageInstalledPlateArray() {
        return imageInstalledPlateArray;
    }

    public void setImageInstalledPlateArray(ArrayList<Bitmap> imageInstalledPlateArray) {
        this.imageInstalledPlateArray = imageInstalledPlateArray;
    }

    public Boolean getBoolIsFailed() {
        return boolIsFailed;
    }

    public void setBoolIsFailed(Boolean boolIsFailed) {
        this.boolIsFailed = boolIsFailed;
    }

    public String getStrFailReason() {
        return strFailReason;
    }

    public void setStrFailReason(String strFailReason) {
        this.strFailReason = strFailReason;
    }

    public String getStrFailDetail() {
        return strFailDetail;
    }

    public void setStrFailDetail(String strFailDetail) {
        this.strFailDetail = strFailDetail;
    }

    @Override
    public String toString() {
        return "DistributionTransformerActivity{" +
                "strIdTransformerActivity='" + strIdTransformerActivity + '\'' +
                ", strEnrollment='" + strEnrollment + '\'' +
                ", imagesBeforeArray=" + imagesBeforeArray +
                ", intTechnicalPlate=" + intTechnicalPlate +
                ", imagesAfterArray=" + imagesAfterArray +
                ", strVegetableOil='" + strVegetableOil + '\'' +
                ", dbTransformerLatitude=" + dbTransformerLatitude +
                ", dbTransformerLongitude=" + dbTransformerLongitude +
                ", strAreaType='" + strAreaType + '\'' +
                ", strPcbEquip='" + strPcbEquip + '\'' +
                ", strType='" + strType + '\'' +
                ", strPhaseR='" + strPhaseR + '\'' +
                ", strPhaseS='" + strPhaseS + '\'' +
                ", strPhaseT='" + strPhaseT + '\'' +
                ", strEquipState='" + strEquipState + '\'' +
                ", strTransformerState='" + strTransformerState + '\'' +
                ", dateFabricationDate=" + dateFabricationDate +
                ", intGroup=" + intGroup +
                ", strInstallationOrigin='" + strInstallationOrigin + '\'' +
                ", strBrand='" + strBrand + '\'' +
                ", strObservation='" + strObservation + '\'' +
                ", strActivePci='" + strActivePci + '\'' +
                ", dbNominalPower='" + dbNominalPower + '\'' +
                ", strProperty='" + strProperty + '\'' +
                ", strPhaseSequence='" + strPhaseSequence + '\'' +
                ", strSubnormal='" + strSubnormal + '\'' +
                ", dbPrimaryVoltage='" + dbPrimaryVoltage + '\'' +
                ", strSecondaryVoltage='" + strSecondaryVoltage + '\'' +
                ", strConnectionType='" + strConnectionType + '\'' +
                ", strTransformerType='" + strTransformerType + '\'' +
                ", strSecondatyNetType='" + strSecondatyNetType + '\'' +
                ", strResourcesUse='" + strResourcesUse + '\'' +
                ", imageInstalledPlateArray=" + imageInstalledPlateArray +
                ", boolIsFailed=" + boolIsFailed +
                ", strFailReason='" + strFailReason + '\'' +
                ", strFailDetail='" + strFailDetail + '\'' +
                '}';
    }
}
