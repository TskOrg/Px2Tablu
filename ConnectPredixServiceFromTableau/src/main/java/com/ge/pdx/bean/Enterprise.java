package com.ge.pdx.bean;

public class Enterprise
{
    private String name;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Enterprise [name = "+name+"]";
    }
}
