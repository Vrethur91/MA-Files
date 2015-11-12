package com.bene.uni_leipzig.informatik.nlp.asse.general.io;

import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.ClassInfo;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bene
 */
public class PropertiesManager {
    public static ClassInfo getClassInfo(String path, String classID){
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path))){
            Properties properties = new Properties();
            properties.load(stream);
            stream.close();
            String[] info = properties.getProperty(classID).split(",");
            ClassInfo classInfo = new ClassInfo(classID,Integer.parseInt(info[0]),info[1]);
            
            return classInfo;
        } catch (Exception ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static ArrayList<String> getKeySet(String path){
        try {
            ArrayList<String> keySet = new ArrayList<>();
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            Set<Object> propKeySet = prop.keySet();
            for(Object obj:propKeySet){
                keySet.add(obj.toString());
            }
            
            return keySet;
        } catch (Exception ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    
    public static HashMap<String, String> getClassByColorCode(String path) {
        try{
            HashMap<String, String> classCodeMap = new HashMap<>();
            Properties prop = new Properties();
            prop.load(new FileInputStream(path));
            Set<Object> propKeySet = prop.keySet();
            for(Object obj:propKeySet){
                classCodeMap.put(obj.toString(), prop.getProperty(obj.toString()));
            }
            
            return classCodeMap;
        } catch (Exception ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static Properties getProperties(String path){
        try{
            Properties prop = new Properties();
            File file = new File(path);
            
            if(file.exists()&&!file.isDirectory()){
                prop.load(new FileInputStream(file));
            } else{
                System.out.println("No file for \""+path+"\" found!\n"
                        + "Returning empty Properties-Object.");
            }
            
            return prop;
        } catch (Exception ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }    
    
    public static void writeProperties(String path, Properties prop){
        try {
            OutputStream out = new FileOutputStream(path);
            prop.store(out, null);
            
        } catch (Exception ex) {
            Logger.getLogger(PropertiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
