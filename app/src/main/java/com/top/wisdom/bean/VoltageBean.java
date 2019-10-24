package com.Scanner.service;

import java.util.List;

/**
 * 作者：李阳
 * 时间：2019/4/2
 * 描述：
 */
public class VoltageBean {


    //测量时间
    private String MeasureTime;

    //企业编号
    private String CompanyNumber;

    //车间号
    private String WorkshopNumber;


    //槽号
    private String SlotNumber;

    //极数
    private int NumberofPoles;

    //A面数据
    private List<String> ADatas;

    //B面数据
    private List<String> BDatas;


    public String getMeasureTime() {

        return MeasureTime;
    }

    public void setMeasureTime(String measureTime) {
        MeasureTime = measureTime;
    }

    public String getCompanyNumber() {
        return CompanyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        CompanyNumber = companyNumber;
    }

    public String getWorkshopNumber() {
        return WorkshopNumber;
    }

    public void setWorkshopNumber(String workshopNumber) {
        WorkshopNumber = workshopNumber;
    }

    public String getSlotNumber() {
        return SlotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        SlotNumber = slotNumber;
    }

    public int getNumberofPoles() {
        return NumberofPoles;
    }

    public void setNumberofPoles(int numberofPoles) {
        NumberofPoles = numberofPoles;
    }

    public List<String> getADatas() {
        return ADatas;
    }

    public void setADatas(List<String> ADatas) {
        this.ADatas = ADatas;
    }

    public List<String> getBDatas() {
        return BDatas;
    }

    public void setBDatas(List<String> BDatas) {
        this.BDatas = BDatas;
    }


    @Override
    public String toString() {
        return "VoltageBean{" +
                "MeasureTime='" + MeasureTime + '\'' +
                ", CompanyNumber='" + CompanyNumber + '\'' +
                ", WorkshopNumber='" + WorkshopNumber + '\'' +
                ", SlotNumber='" + SlotNumber + '\'' +
                ", NumberofPoles=" + NumberofPoles +
                ", ADatas=" + ADatas +
                ", BDatas=" + BDatas +
                '}';
    }
}
