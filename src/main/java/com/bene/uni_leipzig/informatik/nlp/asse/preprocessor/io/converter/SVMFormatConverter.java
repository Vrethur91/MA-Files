package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.converter;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.PropertiesManager;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author Bene
 */
public class SVMFormatConverter {

    private HashMap<String, Integer> stringToNumericMap;
    private Properties prop;
    private int nextID;

    public ArrayList<String> convertToSVMFormat(ArrayList<LetterComponent> components, boolean hasPosition, boolean positionAsText, boolean hasData) {
        
        prop = PropertiesManager.getProperties("wordToNumericConversion.properties");
        stringToNumericMap = new HashMap<>();
        ArrayList<HashMap<Integer, Integer>> componentElements = new ArrayList<>();
        ArrayList<String> convertedLines = new ArrayList<>();

        if (!positionAsText && hasPosition) {
            nextID = 4;
        } else {
            nextID = 1;
        }

        for (LetterComponent component : components) {
            HashMap<Integer, Integer> tempMap = new HashMap<>();

            if (positionAsText && hasPosition) {
                Double[] position = component.getPosition();
                String left = "left" + position[0].toString();
                String top = "top" + position[1].toString();
                String width = "width" + position[2].toString();

                int leftID = getStringID(left);
                int topID = getStringID(top);
                int widthID = getStringID(width);
                if (tempMap.containsKey(leftID)) {
                    tempMap.put(leftID, tempMap.get(leftID) + 1);
                } else {
                    tempMap.put(leftID, 1);
                }

                if (tempMap.containsKey(topID)) {
                    tempMap.put(topID, tempMap.get(topID) + 1);
                } else {
                    tempMap.put(topID, 1);
                }

                if (tempMap.containsKey(widthID)) {
                    tempMap.put(widthID, tempMap.get(widthID) + 1);
                } else {
                    tempMap.put(widthID, 1);
                }
            }
            if (hasData) {
                for (String data : component.getData()) {
                    int dataID = getStringID(data);
                    if (tempMap.containsKey(dataID)) {
                        tempMap.put(dataID, tempMap.get(dataID) + 1);
                    } else {
                        tempMap.put(dataID, 1);
                    }
                }

                ArrayList<String> xFeatures;
                if ((xFeatures = component.getXFeatures()) != null) {
                    for (String xFeature : xFeatures) {
                        int dataID = getStringID(xFeature);
                        if (tempMap.containsKey(dataID)) {
                            tempMap.put(dataID, tempMap.get(dataID) + 1);
                        } else {
                            tempMap.put(dataID, 1);
                        }
                    }
                }

                ArrayList<String> bigrams;
                if ((bigrams = component.getBigramList()) != null) {
                    for (String bigram : bigrams) {
                        int dataID = getStringID(bigram);
                        if (tempMap.containsKey(dataID)) {
                            tempMap.put(dataID, tempMap.get(dataID) + 1);
                        } else {
                            tempMap.put(dataID, 1);
                        }
                    }
                }

                ArrayList<String> xBigrams;
                if ((xBigrams = component.getBigramXList()) != null) {
                    for (String xBigram : xBigrams) {
                        int dataID = getStringID(xBigram);
                        if (tempMap.containsKey(dataID)) {
                            tempMap.put(dataID, tempMap.get(dataID) + 1);
                        } else {
                            tempMap.put(dataID, 1);
                        }
                    }
                }

            }

            componentElements.add(tempMap);
        }

        for (int i = 0; i < components.size(); i++) {
            LetterComponent component = components.get(i);
            HashMap<Integer, Integer> tempSet = componentElements.get(i);
            String line = "";
            line += component.getClassID();

            if (!positionAsText && hasPosition) {
                Double[] position = component.getPosition();
                line += " 1:" + position[0].toString();
                line += " 2:" + position[1].toString();
                line += " 3:" + position[2].toString();
            }

            if (hasData || (positionAsText && hasPosition)) {
                for (int j = 1; j <= prop.size(); j++) {
                //for (int j = 1; j <= stringToNumericMap.size(); j++) {
                    if (tempSet.containsKey(j)) {
                        line += " " + j + ":" + tempSet.get(j);
                        //line += " " + j + ":1";
                    }
                }
            }

            convertedLines.add(line);
        }
        PropertiesManager.writeProperties("wordToNumericConversion.properties", prop);
        return convertedLines;
    }

    private int getStringID(String token) {
        int stringID;
        
        if (prop.containsKey(token)) {
            stringID = Integer.parseInt(prop.get(token).toString());
        } else {
            stringID = nextID;
            prop.put(token, ""+stringID);
            nextID++;
        }
        
        /*
        if (stringToNumericMap.containsKey(token)) {
            stringID = stringToNumericMap.get(token);
        } else {
            stringID = nextID;
            stringToNumericMap.put(token, stringID);
            nextID++;
        }*/

        return stringID;
    }
}
