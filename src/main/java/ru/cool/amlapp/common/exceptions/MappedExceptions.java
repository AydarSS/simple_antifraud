package ru.cool.amlapp.common.exceptions;

public enum MappedExceptions {
    INVALID_ARGUMENTS (0,"Invalid Arguments"),
    RECORDEXISTS (2,"Record is already exists"),
    RECORDNOTEXISTS (3,"Record is not exists");


    private String textexception;
    private int    code;

    MappedExceptions( int code,String textexception) {
        this.code = code;
        this.textexception = textexception;
    }

    public int getCode() {
        return code;
    }

    public String getTextexception() {
        return textexception;
    }
}
