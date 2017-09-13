package com.ge.pdx.bean;

public class Incident
{
    private String incidentEventCount;

    public String getIncidentEventCount ()
    {
        return incidentEventCount;
    }

    public void setIncidentEventCount (String incidentEventCount)
    {
        this.incidentEventCount = incidentEventCount;
    }

    @Override
    public String toString()
    {
        return "Incident [incidentEventCount = "+incidentEventCount+"]";
    }
}
