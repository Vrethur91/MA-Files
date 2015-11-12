package com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing;

import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import java.util.ArrayList;

/**
 *
 * @author Bene
 */
public class BigramGenerator {

    public void transformToBigrams(ArrayList<LetterComponent> components) {
        for (LetterComponent component : components) {
            ArrayList<String> bigrams = new ArrayList<>();
            ArrayList<String> dataList = component.getData();

            for (int i = 0; i < dataList.size() - 1; i++) {
                String tempBigram = dataList.get(i) + "_" + dataList.get(i + 1);
                bigrams.add(tempBigram);
            }
            component.setBigramList(bigrams);

            ArrayList<String> xFeatures;
            if ((xFeatures = component.getXFeatures()) != null) {
                ArrayList<String> xBigrams = new ArrayList<>();

                for (int i = 0; i < xFeatures.size() - 1; i++) {
                    String tempBigram = xFeatures.get(i) + "_" + xFeatures.get(i + 1);
                    xBigrams.add(tempBigram);
                }
                component.setBigramXList(xBigrams);
            }

        }
    }
}
