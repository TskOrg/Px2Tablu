package com.ge.pdx.bean;

public class Alarms {

	private Content[] content;

    private String sort;

    private String numberOfElements;

    private String last;

    private String universalAssetData;

    private String totalElements;

    private String number;

    private String first;

    private String totalPages;

    private String size;

    public Content[] getContent ()
    {
        return content;
    }

    public void setContent (Content[] content)
    {
        this.content = content;
    }

    public String getSort ()
    {
        return sort;
    }

    public void setSort (String sort)
    {
        this.sort = sort;
    }

    public String getNumberOfElements ()
    {
        return numberOfElements;
    }

    public void setNumberOfElements (String numberOfElements)
    {
        this.numberOfElements = numberOfElements;
    }

    public String getLast ()
    {
        return last;
    }

    public void setLast (String last)
    {
        this.last = last;
    }

    public String getUniversalAssetData ()
    {
        return universalAssetData;
    }

    public void setUniversalAssetData (String universalAssetData)
    {
        this.universalAssetData = universalAssetData;
    }

    public String getTotalElements ()
    {
        return totalElements;
    }

    public void setTotalElements (String totalElements)
    {
        this.totalElements = totalElements;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public String getFirst ()
    {
        return first;
    }

    public void setFirst (String first)
    {
        this.first = first;
    }

    public String getTotalPages ()
    {
        return totalPages;
    }

    public void setTotalPages (String totalPages)
    {
        this.totalPages = totalPages;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", sort = "+sort+", numberOfElements = "+numberOfElements+", last = "+last+", universalAssetData = "+universalAssetData+", totalElements = "+totalElements+", number = "+number+", first = "+first+", totalPages = "+totalPages+", size = "+size+"]";
    }
	
}
