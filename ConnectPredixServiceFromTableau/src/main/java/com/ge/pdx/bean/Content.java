package com.ge.pdx.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Content
{
    private String id;

    private AssociatedMonitoredEntityUuid associatedMonitoredEntityUuid;

    private Alarm alarm;

    private String isTrip;

    private AlarmState alarmState;

    private String name;

    private String tagsOfInterest;

    private String severity;

    private String uuid;

    private String storageReceiveTime;

    private AssociatedMonitoredEntity associatedMonitoredEntity;

    private Incident incident;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public AssociatedMonitoredEntityUuid getAssociatedMonitoredEntityUuid ()
    {
        return associatedMonitoredEntityUuid;
    }

    public void setAssociatedMonitoredEntityUuid (AssociatedMonitoredEntityUuid associatedMonitoredEntityUuid)
    {
        this.associatedMonitoredEntityUuid = associatedMonitoredEntityUuid;
    }

    public Alarm getAlarm ()
    {
        return alarm;
    }

    public void setAlarm (Alarm alarm)
    {
        this.alarm = alarm;
    }

    public String getIsTrip ()
    {
        return isTrip;
    }

    public void setIsTrip (String isTrip)
    {
        this.isTrip = isTrip;
    }

    public AlarmState getAlarmState ()
    {
        return alarmState;
    }

    public void setAlarmState (AlarmState alarmState)
    {
        this.alarmState = alarmState;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTagsOfInterest ()
    {
        return tagsOfInterest;
    }

    public void setTagsOfInterest (String tagsOfInterest)
    {
        this.tagsOfInterest = tagsOfInterest;
    }

    public String getSeverity ()
    {
        return severity;
    }

    public void setSeverity (String severity)
    {
        this.severity = severity;
    }

    public String getUuid ()
    {
        return uuid;
    }

    public void setUuid (String uuid)
    {
        this.uuid = uuid;
    }

    public String getStorageReceiveTime ()
    {
        return storageReceiveTime;
    }

    public void setStorageReceiveTime (String storageReceiveTime)
    {
        this.storageReceiveTime = storageReceiveTime;
    }

    public AssociatedMonitoredEntity getAssociatedMonitoredEntity ()
    {
        return associatedMonitoredEntity;
    }

    public void setAssociatedMonitoredEntity (AssociatedMonitoredEntity associatedMonitoredEntity)
    {
        this.associatedMonitoredEntity = associatedMonitoredEntity;
    }

    public Incident getIncident ()
    {
        return incident;
    }

    public void setIncident (Incident incident)
    {
        this.incident = incident;
    }

    @Override
    public String toString()
    {
        return "Content [id = "+id+", associatedMonitoredEntityUuid = "+associatedMonitoredEntityUuid+", alarm = "+alarm+", isTrip = "+isTrip+", alarmState = "+alarmState+", name = "+name+", tagsOfInterest = "+tagsOfInterest+", severity = "+severity+", uuid = "+uuid+", storageReceiveTime = "+storageReceiveTime+", associatedMonitoredEntity = "+associatedMonitoredEntity+", incident = "+incident+"]";
    }
}