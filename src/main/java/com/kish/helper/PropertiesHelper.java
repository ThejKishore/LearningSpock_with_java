package com.kish.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    BaseHelper baseHelper;

    private  PropertiesHelper(){
        baseHelper = BaseHelper.BaseHelperSingleton.INSTANCE.getBaseHelper();
    }

    public enum PropertiesSingletonHelper{

        INSTANCE;

        private PropertiesHelper propertiesHelper = new PropertiesHelper();

        public PropertiesHelper getPropertiesHelper(){
            return propertiesHelper;
        }
    }


    public Properties loadProperties(String filePath) throws IOException {
        FileInputStream fis = null;
        Properties properties =new Properties();
        try {
            fis = new FileInputStream(new File(baseHelper.getPath(filePath)));
            properties.load(fis);
        }finally {
            if(fis != null)
                fis.close();
        }
        return properties;
    }


}
