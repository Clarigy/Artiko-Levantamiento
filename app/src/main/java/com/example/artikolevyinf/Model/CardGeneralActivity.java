package com.example.artikolevyinf.Model;

import java.io.Serializable;
import java.util.Date;

public class CardGeneralActivity implements Serializable, Comparable<CardGeneralActivity> {

    String strId;
    String strActivityTitle;
    String strTitle;
    String strName;
    String strSerial;
    String strOrder;
    String strAddress;
    double dbLatitude;
    double dbLongitude;
    String strState;
    String strType;
    String strFailedReason;
    double dbDistance;
    boolean isUploadAPI;
    String strTypeMeter;

    /** Parametros para actividad apoyo de baja tensión -> Levantamiento de infraestructura */
    double dbLatitude2;
    double dbLongitude2;

    /** Parametros para la actividad de lectura*/
    String strRoute;
    String strConsecutive;
    String strMeasurer;
    String strBrand;
    String strContract;
    boolean boolIsIndicatorType;
    boolean boolIsActive;
    boolean boolIsReactive;
    boolean boolIsDemand;

    /** Parametros para horas de inicio y terminado*/
    Date dateStartExecuted;
    Date dateFinishExecuted;
    Date dateStartFailed;
    Date dateFinishFailed;

    /** Si la actividad es creada o enviada del servidor */
    boolean isCreated;

    public CardGeneralActivity(String strId, String activityTitle, String title, String name, String serial, String order, String address, double latitude, double longitude, String state, String type, String failedReason, double distance, boolean isUploadAPI, Date dateStartExecuted, Date dateFinishExecuted, Date dateStartFailed, Date dateFinishFailed, boolean isCreated) {
        this.strId = strId;
        this.strActivityTitle = activityTitle;
        this.strTitle = title;
        this.strName = name;
        this.strSerial = serial;
        this.strOrder = order;
        this.strAddress = address;
        this.dbLatitude = latitude;
        this.dbLongitude = longitude;
        this.strState = state;
        this.strType = type;
        this.strFailedReason = failedReason;
        this.dbDistance = distance;
        this.isUploadAPI = isUploadAPI;
        this.dateStartExecuted = dateStartExecuted;
        this.dateFinishExecuted = dateFinishExecuted;
        this.dateStartFailed = dateStartFailed;
        this.dateFinishFailed = dateFinishFailed;
        this.isCreated = isCreated;
    }

    public CardGeneralActivity(String strId, String strActivityTitle, String strTitle, String strName, String strSerial, String strOrder, String strAddress, double dbLatitude, double dbLongitude, String strState, String strType, String strFailedReason, double dbDistance, double dbLatitude2, double dbLongitude2, boolean isUploadAPI, Date dateStartExecuted, Date dateFinishExecuted, Date dateStartFailed, Date dateFinishFailed, boolean isCreated) {
        this.strId = strId;
        this.strActivityTitle = strActivityTitle;
        this.strTitle = strTitle;
        this.strName = strName;
        this.strSerial = strSerial;
        this.strOrder = strOrder;
        this.strAddress = strAddress;
        this.dbLatitude = dbLatitude;
        this.dbLongitude = dbLongitude;
        this.strState = strState;
        this.strType = strType;
        this.strFailedReason = strFailedReason;
        this.dbDistance = dbDistance;
        this.isUploadAPI = isUploadAPI;
        this.dbLatitude2 = dbLatitude2;
        this.dbLongitude2 = dbLongitude2;
        this.dateStartExecuted = dateStartExecuted;
        this.dateFinishExecuted = dateFinishExecuted;
        this.dateStartFailed = dateStartFailed;
        this.dateFinishFailed = dateFinishFailed;
        this.isCreated = isCreated;
    }

    public CardGeneralActivity(String strId, String strAddress, double dbLatitude, double dbLongitude, String strState, String strType, String strFailedReason, boolean isUploadAPI, String strRoute, String intConsecutive, String lgMeasurer, String strBrand, String lgContract, boolean strIndicatorType, boolean boolIsActive, boolean boolIsReactive, boolean boolIsDemand, Date dateStartExecuted, Date dateFinishExecuted, Date dateStartFailed, Date dateFinishFailed, boolean isCreated, String strTypeMeter) {
        this.strId = strId;
        this.strAddress = strAddress;
        this.dbLatitude = dbLatitude;
        this.dbLongitude = dbLongitude;
        this.strState = strState;
        this.strType = strType;
        this.strFailedReason = strFailedReason;
        this.isUploadAPI = isUploadAPI;
        this.strRoute = strRoute;
        this.strConsecutive = intConsecutive;
        this.strMeasurer = lgMeasurer;
        this.strBrand = strBrand;
        this.strContract = lgContract;
        this.boolIsIndicatorType = strIndicatorType;
        this.boolIsActive = boolIsActive;
        this.boolIsReactive = boolIsReactive;
        this.boolIsDemand = boolIsDemand;
        this.dateStartExecuted = dateStartExecuted;
        this.dateFinishExecuted = dateFinishExecuted;
        this.dateStartFailed = dateStartFailed;
        this.dateFinishFailed = dateFinishFailed;
        this.isCreated = isCreated;
        this.strTypeMeter = strTypeMeter;

    }

    public String getStrTypeMeter() {
        return strTypeMeter;
    }

    public void setStrTypeMeter(String strTypeMeter) {
        this.strTypeMeter = strTypeMeter;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrActivityTitle() {
        return strActivityTitle;
    }

    public void setStrActivityTitle(String strActivityTitle) {
        this.strActivityTitle = strActivityTitle;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrSerial() {
        return strSerial;
    }

    public void setStrSerial(String strSerial) {
        this.strSerial = strSerial;
    }

    public String getStrOrder() {
        return strOrder;
    }

    public void setStrOrder(String strOrder) {
        this.strOrder = strOrder;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public double getDbLatitude() {
        return dbLatitude;
    }

    public void setDbLatitude(double dbLatitude) {
        this.dbLatitude = dbLatitude;
    }

    public double getDbLongitude() {
        return dbLongitude;
    }

    public void setDbLongitude(double dbLongitude) {
        this.dbLongitude = dbLongitude;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getStrFailedReason() {
        return strFailedReason;
    }

    public void setStrFailedReason(String strFailedReason) {
        this.strFailedReason = strFailedReason;
    }

    public double getDbDistance() {
        return dbDistance;
    }

    public void setDbDistance(double dbDistance) {
        this.dbDistance = dbDistance;
    }

    public boolean getIsUploadAPI() {
        return isUploadAPI;
    }

    public void setIsUploadAPI(boolean uploadAPI) {
        isUploadAPI = uploadAPI;
    }

    public double getDbLatitude2() {
        return dbLatitude2;
    }

    public void setDbLatitude2(double dbLatitude2) {
        this.dbLatitude2 = dbLatitude2;
    }

    public double getDbLongitude2() {
        return dbLongitude2;
    }

    public void setDbLongitude2(double dbLongitude2) {
        this.dbLongitude2 = dbLongitude2;
    }

    public String getStrRoute() {
        return strRoute;
    }

    public void setStrRoute(String strRoute) {
        this.strRoute = strRoute;
    }

    public String getStrConsecutive() {
        return strConsecutive;
    }

    public void setStrConsecutive(String strConsecutive) {
        this.strConsecutive = strConsecutive;
    }

    public String getStrMeasurer() {
        return strMeasurer;
    }

    public void setStrMeasurer(String strMeasurer) {
        this.strMeasurer = strMeasurer;
    }

    public String getStrBrand() {
        return strBrand;
    }

    public void setStrBrand(String strBrand) {
        this.strBrand = strBrand;
    }

    public String getStrContract() {
        return strContract;
    }

    public void setStrContract(String strContract) {
        this.strContract = strContract;
    }

    public boolean getBoolIsIndicatorType() {
        return boolIsIndicatorType;
    }

    public void setBoolIsIndicatorType(boolean boolIsIndicatorType) {
        this.boolIsIndicatorType = boolIsIndicatorType;
    }

    public boolean isBoolIsActive() {
        return boolIsActive;
    }

    public void setBoolIsActive(boolean boolIsActive) {
        this.boolIsActive = boolIsActive;
    }

    public boolean isBoolIsReactive() {
        return boolIsReactive;
    }

    public void setBoolIsReactive(boolean boolIsReactive) {
        this.boolIsReactive = boolIsReactive;
    }

    public boolean isBoolIsDemand() {
        return boolIsDemand;
    }

    public void setBoolIsDemand(boolean boolIsDemand) {
        this.boolIsDemand = boolIsDemand;
    }

    public Date getDateStartExecuted() {
        return dateStartExecuted;
    }

    public void setDateStartExecuted(Date dateStartExecuted) {
        this.dateStartExecuted = dateStartExecuted;
    }

    public Date getDateFinishExecuted() {
        return dateFinishExecuted;
    }

    public void setDateFinishExecuted(Date dateFinishExecuted) {
        this.dateFinishExecuted = dateFinishExecuted;
    }

    public Date getDateStartFailed() {
        return dateStartFailed;
    }

    public void setDateStartFailed(Date dateStartFailed) {
        this.dateStartFailed = dateStartFailed;
    }

    public Date getDateFinishFailed() {
        return dateFinishFailed;
    }

    public void setDateFinishFailed(Date dateFinishFailed) {
        this.dateFinishFailed = dateFinishFailed;
    }

    public boolean getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(boolean created) {
        isCreated = created;
    }

    @Override
    public String toString() {
        return "CardGeneralActivity{" +
                "strId='" + strId + '\'' +
                ", strActivityTitle='" + strActivityTitle + '\'' +
                ", strTitle='" + strTitle + '\'' +
                ", strName='" + strName + '\'' +
                ", strSerial='" + strSerial + '\'' +
                ", strOrder='" + strOrder + '\'' +
                ", strAddress='" + strAddress + '\'' +
                ", dbLatitude=" + dbLatitude +
                ", dbLongitude=" + dbLongitude +
                ", strState='" + strState + '\'' +
                ", strType='" + strType + '\'' +
                ", strFailedReason='" + strFailedReason + '\'' +
                ", dbDistance=" + dbDistance +
                ", isUploadAPI=" + isUploadAPI +
                ", dbLatitude2=" + dbLatitude2 +
                ", dbLongitude2=" + dbLongitude2 +
                ", strRoute='" + strRoute + '\'' +
                ", strConsecutive='" + strConsecutive + '\'' +
                ", strMeasurer='" + strMeasurer + '\'' +
                ", strBrand='" + strBrand + '\'' +
                ", strContract='" + strContract + '\'' +
                ", boolIsIndicatorType=" + boolIsIndicatorType +
                ", boolIsActive=" + boolIsActive +
                ", boolIsReactive=" + boolIsReactive +
                ", boolIsDemand=" + boolIsDemand +
                ", dateStartExecuted=" + dateStartExecuted +
                ", dateFinishExecuted=" + dateFinishExecuted +
                ", dateStartFailed=" + dateStartFailed +
                ", dateFinishFailed=" + dateFinishFailed +
                ", isCreated=" + isCreated +
                '}';
    }

    @Override
    public int compareTo(CardGeneralActivity another) {
        return (int) (getDbDistance() - another.getDbDistance());
    }


}
