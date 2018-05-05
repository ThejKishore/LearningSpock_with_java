package com.kish.helper;

import java.util.Collection;
import java.util.Map;

public class BaseHelper {

    public static final String CLASSPATH = "classpath";
    public static final String RELATIVE = "relative";
    public static final String FILE = "file";
    public static final String ABSOLUTE = "absolute";


    private BaseHelper(){}

    public enum BaseHelperSingleton{

        INSTANCE;
        private BaseHelper baseHelper = new BaseHelper();

        public BaseHelper getBaseHelper(){
            return this.baseHelper;
        }
    }

    public String getPath(String filePath){
        String[] fileDetails = filePath.split(":");
        if(fileDetails.length < 1) return filePath;

        String fileType = fileDetails[0];
        String returnFilePath = fileDetails[1];
        if(isValidString(fileType))
            if(CLASSPATH.equals(fileType) || RELATIVE.equals(fileType))
                returnFilePath = this.getClass().getResource(returnFilePath).getPath();
            else if(FILE.equals(fileType) || ABSOLUTE.equals(fileType))
                returnFilePath = fileDetails[1];

        return isValidString(returnFilePath) ? returnFilePath.trim() : returnFilePath;
    }

    public boolean isValidString(String value){
        return (value != null && value.trim().length() > 0) ? true : false;
    }


    public <T> boolean isValidCollection(Collection<T> value){
        return (value != null && value.size() > 0) ? true : false;
    }

    public <K,V> boolean isValidMap(Map<K,V> value){
        return (value != null && value.size() > 0) ? true : false;
    }



}
