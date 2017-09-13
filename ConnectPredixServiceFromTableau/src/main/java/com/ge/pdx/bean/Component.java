package com.ge.pdx.bean;

/**
 * @author T S Karthikeyan
 *
 */
public class Component {

	private String sourceKey;

    private ReservedAttributes reservedAttributes;

    private String description;

    private String name;

    private String parent;

    private String label;

    private String type;

    private String uri;

    public String getSourceKey ()
    {
        return sourceKey;
    }

    public void setSourceKey (String sourceKey)
    {
        this.sourceKey = sourceKey;
    }

    public ReservedAttributes getReservedAttributes ()
    {
        return reservedAttributes;
    }

    public void setReservedAttributes (ReservedAttributes reservedAttributes)
    {
        this.reservedAttributes = reservedAttributes;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getParent ()
    {
        return parent;
    }

    public void setParent (String parent)
    {
        this.parent = parent;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sourceKey = "+sourceKey+", reservedAttributes = "+reservedAttributes+", description = "+description+", name = "+name+", parent = "+parent+", label = "+label+", type = "+type+", uri = "+uri+"]";
    }
}
