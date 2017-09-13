package com.ge.pdx.bean;

/**
 * @author T S Karthikeyan
 *
 */

public class ReservedAttributes
{
    private String familyType;

    private String series;

    private String model;

    private String equipmentType;

    private Status status;

    private State state;

    private String maintenanceCriticalityRiskScore;

    private String serialNumber;

    private String faultMode;

    private String make;

    public String getFamilyType ()
    {
        return familyType;
    }

    public void setFamilyType (String familyType)
    {
        this.familyType = familyType;
    }

    public String getSeries ()
    {
        return series;
    }

    public void setSeries (String series)
    {
        this.series = series;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public String getEquipmentType ()
    {
        return equipmentType;
    }

    public void setEquipmentType (String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public Status getStatus ()
    {
        return status;
    }

    public void setStatus (Status status)
    {
        this.status = status;
    }

    public State getState ()
    {
        return state;
    }

    public void setState (State state)
    {
        this.state = state;
    }

    public String getMaintenanceCriticalityRiskScore ()
    {
        return maintenanceCriticalityRiskScore;
    }

    public void setMaintenanceCriticalityRiskScore (String maintenanceCriticalityRiskScore)
    {
        this.maintenanceCriticalityRiskScore = maintenanceCriticalityRiskScore;
    }

    public String getSerialNumber ()
    {
        return serialNumber;
    }

    public void setSerialNumber (String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getFaultMode ()
    {
        return faultMode;
    }

    public void setFaultMode (String faultMode)
    {
        this.faultMode = faultMode;
    }

    public String getMake ()
    {
        return make;
    }

    public void setMake (String make)
    {
        this.make = make;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [familyType = "+familyType+", series = "+series+", model = "+model+", equipmentType = "+equipmentType+", status = "+status+", state = "+state+", maintenanceCriticalityRiskScore = "+maintenanceCriticalityRiskScore+", serialNumber = "+serialNumber+", faultMode = "+faultMode+", make = "+make+"]";
    }
}