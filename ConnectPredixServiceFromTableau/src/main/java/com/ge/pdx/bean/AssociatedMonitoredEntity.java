package com.ge.pdx.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AssociatedMonitoredEntity
{
    private Site site;

    private Alias alias;

    private String name;

    private String equipmentId;

    private Enterprise enterprise;

    public Site getSite ()
    {
        return site;
    }

    public void setSite (Site site)
    {
        this.site = site;
    }

    public Alias getAlias ()
    {
        return alias;
    }

    public void setAlias (Alias alias)
    {
        this.alias = alias;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEquipmentId ()
    {
        return equipmentId;
    }

    public void setEquipmentId (String equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Enterprise getEnterprise ()
    {
        return enterprise;
    }

    public void setEnterprise (Enterprise enterprise)
    {
        this.enterprise = enterprise;
    }

    @Override
    public String toString()
    {
        return "AssociatedMonitoredEntity [site = "+site+", alias = "+alias+", name = "+name+", equipmentId = "+equipmentId+", enterprise = "+enterprise+"]";
    }
}
