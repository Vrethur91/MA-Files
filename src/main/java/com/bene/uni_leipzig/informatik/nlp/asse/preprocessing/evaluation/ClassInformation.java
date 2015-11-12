package com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.evaluation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bene
 */
public class ClassInformation {
    private Map<String, Integer> featureList;
    private Map<String, Integer> classUniqueFeatureList;

    public ClassInformation(){
        featureList = new HashMap<>();
        classUniqueFeatureList = new HashMap<>();
    }
    
    public void addFeature(String feature){
        if(featureList.containsKey(feature)){
            featureList.put(feature, featureList.get(feature) + 1);
        } else{
            featureList.put(feature, 1);
        }
    }
    
    public void addUniqueFeature(String feature){
        classUniqueFeatureList.put(feature, featureList.get(feature));
    }

    public Map<String, Integer> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(Map<String, Integer> featureList) {
        this.featureList = featureList;
    }

    public Map<String, Integer> getClassUniqueFeatureList() {
        return classUniqueFeatureList;
    }

    public void setClassUniqueFeatureList(Map<String, Integer> classUniqueFeatureList) {
        this.classUniqueFeatureList = classUniqueFeatureList;
    }
}
