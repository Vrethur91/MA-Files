package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.processing;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bene
 */
public class ClassFilter {
    public ArrayList<LetterComponent> filterClasses(ArrayList<LetterComponent> letterComponents, ArrayList<String> filterByClassIDList){
        ArrayList<LetterComponent> resultLetterComponentList = new ArrayList<>();
        HashMap<String, ArrayList<LetterComponent>> sortedByClass = sortByClass(letterComponents);
        for(String classID:filterByClassIDList){
            resultLetterComponentList.addAll(sortedByClass.get(classID));
        }
                
        return resultLetterComponentList;
    }
    
    private HashMap<String, ArrayList<LetterComponent>> sortByClass(ArrayList<LetterComponent> letterComponents){
        HashMap<String, ArrayList<LetterComponent>> sortedByClass = new HashMap<>();
        for(LetterComponent component: letterComponents){
            String classID = component.getClassID();
            if(sortedByClass.containsKey(classID)){
                sortedByClass.get(classID).add(component);
            } else{
                ArrayList<LetterComponent> tempList = new ArrayList<>();
                tempList.add(component);
                sortedByClass.put(classID, tempList);
            }
        }
        
        return sortedByClass;
    }
}
