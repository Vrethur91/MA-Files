package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.converter;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bene
 */
public class MalletFormatConverter {

    public ArrayList<String> convertToMalletFormat(ArrayList<LetterComponent> components, boolean hasPosition, boolean hasData) {
        ArrayList<String> convertedLines = new ArrayList<>();

        for (LetterComponent component : components) {
            String line = component.getDocLineID();
            line += " " + component.getClassID();

            if (hasPosition) {
                Double[] position = component.getPosition();
                String left = "left" + position[0].toString();
                String top = "top" + position[1].toString();
                String width = "width" + position[2].toString();
                line += " " + left + " " + top + " " + width;

                if (hasData) {
                    for (String data : component.getData()) {
                        line += " " + data;
                    }

                    ArrayList<String> xFeatures;
                    if ((xFeatures = component.getXFeatures()) != null) {
                        for (String xFeature : xFeatures) {
                            line += " " + xFeature;
                        }
                    }

                    ArrayList<String> bigrams;
                    if ((bigrams = component.getBigramList()) != null) {
                        for (String bigram : bigrams) {
                            line += " " + bigram;
                        }
                    }

                    ArrayList<String> xBigrams;
                    if ((xBigrams = component.getBigramXList()) != null) {
                        for (String xBigram : xBigrams) {
                            line += " " + xBigram;
                        }
                    }
                }
            }
            convertedLines.add(line);
        }
        return convertedLines;
    }
}
