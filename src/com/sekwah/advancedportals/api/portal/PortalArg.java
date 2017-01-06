package com.sekwah.advancedportals.api.portal;

public class PortalArg {

    public final String NAME;
    public final String VALUE;
    //public final int type;

    public PortalArg(String argName, String value/*, int type*/) {
        this.NAME = argName;
        this.VALUE = value;
        // may be used if values need to be 100% not string
        //this.type = type;
    }

}
