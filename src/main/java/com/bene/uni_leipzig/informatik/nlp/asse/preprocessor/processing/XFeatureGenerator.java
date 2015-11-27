package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.processing;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bene
 */
public class XFeatureGenerator {

    private int capNumber;
    private int capWord;

    public XFeatureGenerator() {
        capNumber = 0;
        capWord = 0;
    }

    public XFeatureGenerator(int capNumber, int capWord) {
        this.capNumber = capNumber;
        this.capWord = capWord;
    }
    
    public void createXFeatures(ArrayList<LetterComponent> components){
        for(LetterComponent component:components){
            ArrayList<String> xFeatures = new ArrayList<>();
            for(String data:component.getData()){
                xFeatures.add(createXFeature(data));
            }
            component.setXFeatures(xFeatures);
        }
    }

    private String createXFeature(String token) {

        String xFeature;
        

        if (Character.isDigit(token.charAt(0))) {
            xFeature = "X";
            for (int j = 0; (j < token.length()) && ((capNumber > 0) ? (xFeature.length() <= capNumber) : true); j++) {
                xFeature += "N";
            }
        } else if (Character.isUpperCase(token.charAt(0))) {
            xFeature = "X";
            Matcher m = Pattern.compile("([a-zA-ZÖÄÜöäüß]+)").matcher(token);
            if (m.find()) {
                token = m.group(0);
            }
            for (int j = 0; (j < token.length() - 1) && ((capWord > 0) ? (xFeature.length() < capWord) : true); j++) {
                xFeature += "x";
            }
        } else {
            xFeature = "x";
            Matcher m = Pattern.compile("([a-zA-ZÖÄÜöäüß]+)").matcher(token);
            if (m.find()) {
                token = m.group(0);
            }
            for (int j = 0; (j < token.length() - 1) && ((capWord > 0) ? (xFeature.length() < capWord) : true); j++) {
                xFeature += "x";
            }
        }

        return xFeature;
    }

}
