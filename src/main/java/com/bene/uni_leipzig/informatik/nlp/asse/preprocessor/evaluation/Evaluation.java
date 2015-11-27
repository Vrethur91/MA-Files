package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.evaluation;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Bene
 */
public class Evaluation {

    public static void evaluate(ArrayList<LetterComponent> components){
        HashMap<String, ArrayList<String>> featureIndexMap = new HashMap<>();
        HashMap<String, ClassInformation> classInfoMap = new HashMap<>();
        Map<String, Integer> classCountMap = new HashMap<>();
        ArrayList<String> lines = new ArrayList<>();
        
        for(LetterComponent component:components){
            String classID = component.getClassID();
            if(classCountMap.containsKey(classID)){
                classCountMap.put(classID, classCountMap.get(classID) + 1);
            } else{
                classCountMap.put(classID, 1);
            }
            
            if(!classInfoMap.containsKey(classID)){
                ClassInformation info = new ClassInformation();
                classInfoMap.put(classID, info);
            }
            
            for(String token:component.getData()){
                if(featureIndexMap.containsKey(token)){
                    if(!featureIndexMap.get(token).contains(classID)){
                        featureIndexMap.get(token).add(classID);
                    }
                } else{
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(classID);
                    featureIndexMap.put(token, tempList);
                }
                
                classInfoMap.get(classID).addFeature(token);
            }
        }
        for(Entry<String,ArrayList<String>> entry:featureIndexMap.entrySet()){
            if(entry.getValue().size()<=1){
                classInfoMap.get(entry.getValue().get(0)).addUniqueFeature(entry.getKey());
            }
        }
        
        classCountMap = sortedByValuesDesc(classCountMap);
        for(Entry<String,Integer> entry:classCountMap.entrySet()){
            String classID = entry.getKey();
            ClassInformation info = classInfoMap.get(classID);
            info.setFeatureList(sortedByValuesDesc(info.getFeatureList()));
            info.setClassUniqueFeatureList(sortedByValuesDesc(info.getClassUniqueFeatureList()));
            
            String line = classID;
            System.out.println(classID+":");
            
            line += " & "+classCountMap.get(classID);
            System.out.println("Vorkommen: "+classCountMap.get(classID));
            
            line += " & "+info.getFeatureList().size();
            System.out.println("#Features: "+info.getFeatureList().size());
            Map<String,Integer> featureList = info.getFeatureList();
            System.out.println("Top 3 Features");
            int n = 0;
            for(Entry<String,Integer> feature:featureList.entrySet()){
                if(n==3) break;
                System.out.println(feature.getKey()+": "+feature.getValue());
                n++;
            }
                    
            line += " & "+info.getClassUniqueFeatureList().size() + " \\\\ ";
            System.out.println("#UniqueFeatures: "+info.getClassUniqueFeatureList().size());
            Map<String,Integer> classUniqueFeatureList = info.getClassUniqueFeatureList();
            n = 0;
            System.out.println("Top 3 unique Features");
            for(Entry<String,Integer> feature:classUniqueFeatureList.entrySet()){
                if(n==3) break;
                System.out.println(feature.getKey()+": "+feature.getValue());
                n++;
            }
            
            System.out.println();
            lines.add(line);
        }
        
        for(String line:lines){
            System.out.println(line);
        }
    }
    
    public static <K, V extends Comparable<? super V>>
            HashMap<K, V> sortedByValuesDesc(Map<K, V> map) {

        List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Entry<K, V>>() {
                    @Override
                    public int compare(Entry<K, V> e1, Entry<K, V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }
        );
        
        HashMap<K, V> sortedMap = new LinkedHashMap<>();
        for(Entry<K, V> entry:sortedEntries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
