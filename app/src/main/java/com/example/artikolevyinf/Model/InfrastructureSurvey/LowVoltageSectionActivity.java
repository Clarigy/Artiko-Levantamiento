package com.example.artikolevyinf.Model.InfrastructureSurvey;

public class LowVoltageSectionActivity {

    String strIdActivity;
    /** Fragment 1 */
    String strStartSupport;
    String strFinalSupport;
    String strMaterial;
    String strCanalization;
    /** Fragment 2 */
    String strNConductors;
    String strDispositionConductor;
    String strLength;
    /** Fragment 3 */
    String strObservation;
    /** Fragment 4 */
    String strProperty;
    String strAreaType;
    String strConductorType1;
    String strConductorType2;
    String strConductorType3;
    /** Fragment 5 */
    String strNeutralConductor;
    String strSectionType;
    String strResourcesUse;
    /** En caso de ser una actividad fallida */
    Boolean boolIsFailed;
    String strFailReason;
    String strFailDetail;

    public LowVoltageSectionActivity(String strIdActivity, String strStartSupport, String strFinalSupport, String strMaterial, String strCanalization, String strNConductors, String strDispositionConductor, String strLength, String strObservation, String strProperty, String strAreaType, String strConductorType1, String strConductorType2, String strConductorType3, String strNeutralConductor, String strSectionType, String strResourcesUse, Boolean boolIsFailed, String strFailReason, String strFailDetail) {
        this.strIdActivity = strIdActivity;
        this.strStartSupport = strStartSupport;
        this.strFinalSupport = strFinalSupport;
        this.strMaterial = strMaterial;
        this.strCanalization = strCanalization;
        this.strNConductors = strNConductors;
        this.strDispositionConductor = strDispositionConductor;
        this.strLength = strLength;
        this.strObservation = strObservation;
        this.strProperty = strProperty;
        this.strAreaType = strAreaType;
        this.strConductorType1 = strConductorType1;
        this.strConductorType2 = strConductorType2;
        this.strConductorType3 = strConductorType3;
        this.strNeutralConductor = strNeutralConductor;
        this.strSectionType = strSectionType;
        this.strResourcesUse = strResourcesUse;
        this.boolIsFailed = boolIsFailed;
        this.strFailReason = strFailReason;
        this.strFailDetail = strFailDetail;
    }

    public String getStrIdActivity() {
        return strIdActivity;
    }

    public void setStrIdActivity(String strIdActivity) {
        this.strIdActivity = strIdActivity;
    }

    public String getStrStartSupport() {
        return strStartSupport;
    }

    public void setStrStartSupport(String strStartSupport) {
        this.strStartSupport = strStartSupport;
    }

    public String getStrFinalSupport() {
        return strFinalSupport;
    }

    public void setStrFinalSupport(String strFinalSupport) {
        this.strFinalSupport = strFinalSupport;
    }

    public String getStrMaterial() {
        return strMaterial;
    }

    public void setStrMaterial(String strMaterial) {
        this.strMaterial = strMaterial;
    }

    public String getStrCanalization() {
        return strCanalization;
    }

    public void setStrCanalization(String strCanalization) {
        this.strCanalization = strCanalization;
    }

    public String getStrNConductors() {
        return strNConductors;
    }

    public void setStrNConductors(String strNConductors) {
        this.strNConductors = strNConductors;
    }

    public String getStrDispositionConductor() {
        return strDispositionConductor;
    }

    public void setStrDispositionConductor(String strDispositionConductor) {
        this.strDispositionConductor = strDispositionConductor;
    }

    public String getStrLength() {
        return strLength;
    }

    public void setStrLength(String strLength) {
        this.strLength = strLength;
    }

    public String getStrObservation() {
        return strObservation;
    }

    public void setStrObservation(String strObservation) {
        this.strObservation = strObservation;
    }

    public String getStrProperty() {
        return strProperty;
    }

    public void setStrProperty(String strProperty) {
        this.strProperty = strProperty;
    }

    public String getStrAreaType() {
        return strAreaType;
    }

    public void setStrAreaType(String strAreaType) {
        this.strAreaType = strAreaType;
    }

    public String getStrConductorType1() {
        return strConductorType1;
    }

    public void setStrConductorType1(String strConductorType1) {
        this.strConductorType1 = strConductorType1;
    }

    public String getStrConductorType2() {
        return strConductorType2;
    }

    public void setStrConductorType2(String strConductorType2) {
        this.strConductorType2 = strConductorType2;
    }

    public String getStrConductorType3() {
        return strConductorType3;
    }

    public void setStrConductorType3(String strConductorType3) {
        this.strConductorType3 = strConductorType3;
    }

    public String getStrNeutralConductor() {
        return strNeutralConductor;
    }

    public void setStrNeutralConductor(String strNeutralConductor) {
        this.strNeutralConductor = strNeutralConductor;
    }

    public String getStrSectionType() {
        return strSectionType;
    }

    public void setStrSectionType(String strSectionType) {
        this.strSectionType = strSectionType;
    }

    public String getStrResourcesUse() {
        return strResourcesUse;
    }

    public void setStrResourcesUse(String strResourcesUse) {
        this.strResourcesUse = strResourcesUse;
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
        return "LowVoltageSectionActivity{" +
                "strIdActivity='" + strIdActivity + '\'' +
                ", strStartSupport='" + strStartSupport + '\'' +
                ", strFinalSupport='" + strFinalSupport + '\'' +
                ", strMaterial='" + strMaterial + '\'' +
                ", strCanalization='" + strCanalization + '\'' +
                ", strNConductors='" + strNConductors + '\'' +
                ", strDispositionConductor='" + strDispositionConductor + '\'' +
                ", dbLength=" + strLength +
                ", strObservation='" + strObservation + '\'' +
                ", strProperty='" + strProperty + '\'' +
                ", strAreaType='" + strAreaType + '\'' +
                ", strConductorType1='" + strConductorType1 + '\'' +
                ", strConductorType2='" + strConductorType2 + '\'' +
                ", strConductorType3='" + strConductorType3 + '\'' +
                ", strNeutralConductor='" + strNeutralConductor + '\'' +
                ", strSectionType='" + strSectionType + '\'' +
                ", strResourcesUse='" + strResourcesUse + '\'' +
                ", boolIsFailed=" + boolIsFailed +
                ", strFailReason='" + strFailReason + '\'' +
                ", strFailDetail='" + strFailDetail + '\'' +
                '}';
    }
}
