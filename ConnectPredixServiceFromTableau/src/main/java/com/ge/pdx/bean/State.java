package com.ge.pdx.bean;

/**
 * @author T S Karthikeyan
 *
 */

public class State {

	private String key;

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [key = "+key+"]";
    }
	
}
