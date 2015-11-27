package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.processing;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.PropertiesManager;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.ClassInfo;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Bene
 */
public class ClassConverter {

    public void transformToHigherLevel(ArrayList<LetterComponent> components, int toLevel) {
        HashMap<String, ClassInfo> infoMap = getClassHierarchy();
        for (LetterComponent component : components) {
            String classID = component.getClassID();
            ClassInfo info = infoMap.get(classID);
            if (info.getDepth() <= toLevel) {
                continue;
            } else {
                boolean foundClassID = false;
                while (!foundClassID) {
                    info = infoMap.get(info.getParentClassID());
                    if (info.getDepth() <= toLevel) {
                        component.setClassID(info.getClassID());
                        foundClassID = true;
                    }
                }
            }
        }
    }

    public void transformToOneVsAll(ArrayList<LetterComponent> components, String singleClass) {
        for (LetterComponent component : components) {
            String classID = component.getClassID();
            if (!classID.equals(singleClass)) {
                component.setClassID("0");
            }
        }
    }

    public void transformToOneVsAllWithMapping(ArrayList<LetterComponent> components, String singleClass) {
        HashMap<String, ClassInfo> infoMap = getClassHierarchy();
        int classLevel = infoMap.get(singleClass).getDepth();

        for (LetterComponent component : components) {
            String classID = component.getClassID();
            ClassInfo info = infoMap.get(classID);
            if (info.getDepth() <= classLevel) {
                continue;
            } else {
                boolean foundClassID = false;
                while (!foundClassID) {
                    info = infoMap.get(info.getParentClassID());
                    if (info.getDepth() <= classLevel) {
                        component.setClassID(info.getClassID());
                        foundClassID = true;
                    }
                }
            }

            if (!component.getClassID().equals(singleClass)) {
                component.setClassID("0");
            }
        }
    }

    public void mapLowerOccToHigherLevel(ArrayList<LetterComponent> components, int threshold) {
        HashMap<String, ClassInfo> hierarchy = getClassHierarchy();
        
        boolean done = false;
        while (!done) {
            HashMap<String, Integer> occCount = new HashMap<>();
            for (LetterComponent component : components) {
                String classID = component.getClassID();
                if (occCount.containsKey(classID)) {
                    occCount.put(classID, occCount.get(classID) + 1);
                } else {
                    occCount.put(classID, 1);
                }
            }
            
            HashSet<String> delSet = new HashSet<>();
            for(LetterComponent component : components){
                String classID = component.getClassID();
                if(occCount.containsKey(classID)){
                    if(occCount.get(classID) < threshold){
                        component.setClassID(hierarchy.get(classID).getParentClassID());
                    } else {
                    }
                }
            }
                
            
            occCount = new HashMap<>();
            for (LetterComponent component : components) {
                String classID = component.getClassID();
                if (occCount.containsKey(classID)) {
                    occCount.put(classID, occCount.get(classID) + 1);
                } else {
                    occCount.put(classID, 1);
                }
            }
            
        }
    }

    private HashMap<String, ClassInfo> getClassHierarchy() {
        String hierarchyPath = "ClassHierarchy.properties";
        HashMap<String, ClassInfo> infoMap = new HashMap<>();
        ArrayList<String> keySet = PropertiesManager.getKeySet(hierarchyPath);
        for (String classID : keySet) {
            infoMap.put(classID, PropertiesManager.getClassInfo(hierarchyPath, classID));
        }

        return infoMap;
    }
}
