package com.bene.uni_leipzig.informatik.nlp.asse.main;

import cc.mallet.types.InstanceList;
import com.bene.uni_leipzig.informatik.nlp.asse.classification.mallet.*;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.converter.MalletFormatConverter;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.in.ImportRegionHTMLDoc;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.processing.XFeatureGenerator;
import java.util.ArrayList;

/**
 *
 * @author Bene
 */
public class Classificator {
    public static void main(String[] args){
        ImportRegionHTMLDoc imp = new ImportRegionHTMLDoc();
        ArrayList<LetterComponent> components = imp.importData("./docs");
        
        XFeatureGenerator xGen = new XFeatureGenerator();
        //xGen.createXFeatures(components);
        
        MalletFormatConverter conv = new MalletFormatConverter();
        ArrayList<String> convList = conv.convertToMalletFormat(components, true, true);
        
        MalletInstanceConverter miConv = new MalletInstanceConverter();
        InstanceList instanceList = miConv.convertData(convList);
        
        String output = "";
        MalletClassifier classifier = new MalletClassifier();
        output += classifier.classifyNaiveBayes(instanceList);
        //output += classifier.classifyMaximumEntropy(instanceList);
        //output += classifier.classifyMaximumEntropyL1(instanceList);
        //output += classifier.classifyBalancedWinnow(instanceList);
        
        System.out.println(output);
    }
}
