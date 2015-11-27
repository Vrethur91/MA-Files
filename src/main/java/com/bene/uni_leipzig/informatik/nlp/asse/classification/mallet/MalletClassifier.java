package com.bene.uni_leipzig.informatik.nlp.asse.classification.mallet;

import cc.mallet.classify.BalancedWinnow;
import cc.mallet.classify.BalancedWinnowTrainer;
import cc.mallet.classify.MaxEnt;
import cc.mallet.classify.MaxEntL1Trainer;
import cc.mallet.classify.MaxEntTrainer;
import cc.mallet.classify.NaiveBayes;
import cc.mallet.classify.NaiveBayesTrainer;
import cc.mallet.classify.Trial;
import cc.mallet.types.InstanceList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bene
 */
public class MalletClassifier {

    public String classifyNaiveBayes(InstanceList iList) {
        InstanceList.CrossValidationIterator it = iList.crossValidationIterator(10);
        //HashMap<String, Double> recall = new HashMap<>();
        //HashMap<String, Double> precision = new HashMap<>();
        HashMap<String, Double> f1 = new HashMap<>();
        HashMap<String, Integer> labelC = new HashMap<>();
        ArrayList<String> labels = new ArrayList<>();

        for (Object label : iList.targetLabelDistribution().getLabelAlphabet().toArray()) {
            labels.add(label.toString());
            labelC.put(label.toString(), 0);
            //recall.put(label.toString(), 0.0);
            //precision.put(label.toString(), 0.0);
            f1.put(label.toString(), 0.0);
        }

        double acc = 0;
        int i = 0;
        while (it.hasNext()) {
            i++;
            InstanceList[] splitList = it.nextSplit();
            InstanceList trainSplit = splitList[0];
            InstanceList testSplit = splitList[1];

            ArrayList<String> testLabel = new ArrayList<>();
            for (Object label : testSplit.targetLabelDistribution().getLabelAlphabet().toArray()) {
                testLabel.add(label.toString());
            }

            NaiveBayesTrainer trainer = new NaiveBayesTrainer();
            NaiveBayes classifier = trainer.train(trainSplit);
            Trial trial = new Trial(classifier, testSplit);
            acc += trial.getAccuracy();
            for (String label : testLabel) {
                labelC.put(label, labelC.get(label) + 1);
                //recall.put(label, recall.get(label) + trial.getRecall(label));
                //precision.put(label, precision.get(label) + trial.getPrecision(label));
                f1.put(label, f1.get(label) + trial.getF1(label));
            }

        }

        String output = "Naive Bayes:\n";
        for (Object label : labels) {
            output += label  + " F1 Score: " + (f1.get(label) / labelC.get(label))+"\n";
        }

        output += "Accuracy: "+acc / 10+"\n";
        
        return output;
    }
    
    public String classifyMaximumEntropy(InstanceList iList) {
        InstanceList.CrossValidationIterator it = iList.crossValidationIterator(10);
        //HashMap<String, Double> recall = new HashMap<>();
        //HashMap<String, Double> precision = new HashMap<>();
        HashMap<String, Double> f1 = new HashMap<>();
        HashMap<String, Integer> labelC = new HashMap<>();
        ArrayList<String> labels = new ArrayList<>();

        for (Object label : iList.targetLabelDistribution().getLabelAlphabet().toArray()) {
            labels.add(label.toString());
            labelC.put(label.toString(), 0);
            //recall.put(label.toString(), 0.0);
            //precision.put(label.toString(), 0.0);
            f1.put(label.toString(), 0.0);
        }

        double acc = 0;
        int i = 0;
        while (it.hasNext()) {
            i++;
            InstanceList[] splitList = it.nextSplit();
            InstanceList trainSplit = splitList[0];
            InstanceList testSplit = splitList[1];

            ArrayList<String> testLabel = new ArrayList<>();
            for (Object label : testSplit.targetLabelDistribution().getLabelAlphabet().toArray()) {
                testLabel.add(label.toString());
            }

            MaxEntTrainer trainer = new MaxEntTrainer();
            MaxEnt classifier = trainer.train(trainSplit);
            Trial trial = new Trial(classifier, testSplit);
            acc += trial.getAccuracy();
            for (String label : testLabel) {
                labelC.put(label, labelC.get(label) + 1);
                //recall.put(label, recall.get(label) + trial.getRecall(label));
                //precision.put(label, precision.get(label) + trial.getPrecision(label));
                f1.put(label, f1.get(label) + trial.getF1(label));
            }

        }

        String output = "Maximum Entropy:\n";
        for (Object label : labels) {
            output += label  + " F1 Score: " + (f1.get(label) / labelC.get(label))+"\n";
        }

        output += "Accuracy: "+acc / 10+"\n";
        
        return output;
    }
    
    public String classifyMaximumEntropyL1(InstanceList iList) {
        InstanceList.CrossValidationIterator it = iList.crossValidationIterator(10);
        //HashMap<String, Double> recall = new HashMap<>();
        //HashMap<String, Double> precision = new HashMap<>();
        HashMap<String, Double> f1 = new HashMap<>();
        HashMap<String, Integer> labelC = new HashMap<>();
        ArrayList<String> labels = new ArrayList<>();

        for (Object label : iList.targetLabelDistribution().getLabelAlphabet().toArray()) {
            labels.add(label.toString());
            labelC.put(label.toString(), 0);
            //recall.put(label.toString(), 0.0);
            //precision.put(label.toString(), 0.0);
            f1.put(label.toString(), 0.0);
        }

        double acc = 0;
        int i = 0;
        while (it.hasNext()) {
            i++;
            InstanceList[] splitList = it.nextSplit();
            InstanceList trainSplit = splitList[0];
            InstanceList testSplit = splitList[1];

            ArrayList<String> testLabel = new ArrayList<>();
            for (Object label : testSplit.targetLabelDistribution().getLabelAlphabet().toArray()) {
                testLabel.add(label.toString());
            }

            MaxEntL1Trainer trainer = new MaxEntL1Trainer();
            MaxEnt classifier = trainer.train(trainSplit);
            Trial trial = new Trial(classifier, testSplit);
            acc += trial.getAccuracy();
            for (String label : testLabel) {
                labelC.put(label, labelC.get(label) + 1);
                //recall.put(label, recall.get(label) + trial.getRecall(label));
                //precision.put(label, precision.get(label) + trial.getPrecision(label));
                f1.put(label, f1.get(label) + trial.getF1(label));
            }

        }

        String output = "Maximum Entropy L1:\n";
        for (Object label : labels) {
            output += label  + " F1 Score: " + (f1.get(label) / labelC.get(label))+"\n";
        }

        output += "Accuracy: "+acc / 10+"\n";
        
        return output;
    }
    
    public String classifyBalancedWinnow(InstanceList iList) {
        InstanceList.CrossValidationIterator it = iList.crossValidationIterator(10);
        //HashMap<String, Double> recall = new HashMap<>();
        //HashMap<String, Double> precision = new HashMap<>();
        HashMap<String, Double> f1 = new HashMap<>();
        HashMap<String, Integer> labelC = new HashMap<>();
        ArrayList<String> labels = new ArrayList<>();

        for (Object label : iList.targetLabelDistribution().getLabelAlphabet().toArray()) {
            labels.add(label.toString());
            labelC.put(label.toString(), 0);
            //recall.put(label.toString(), 0.0);
            //precision.put(label.toString(), 0.0);
            f1.put(label.toString(), 0.0);
        }

        double acc = 0;
        int i = 0;
        while (it.hasNext()) {
            i++;
            InstanceList[] splitList = it.nextSplit();
            InstanceList trainSplit = splitList[0];
            InstanceList testSplit = splitList[1];

            ArrayList<String> testLabel = new ArrayList<>();
            for (Object label : testSplit.targetLabelDistribution().getLabelAlphabet().toArray()) {
                testLabel.add(label.toString());
            }

            BalancedWinnowTrainer trainer = new BalancedWinnowTrainer();
            BalancedWinnow classifier = trainer.train(trainSplit);
            Trial trial = new Trial(classifier, testSplit);
            acc += trial.getAccuracy();
            for (String label : testLabel) {
                labelC.put(label, labelC.get(label) + 1);
                //recall.put(label, recall.get(label) + trial.getRecall(label));
                //precision.put(label, precision.get(label) + trial.getPrecision(label));
                f1.put(label, f1.get(label) + trial.getF1(label));
            }

        }

        String output = "Balanced Winnow:\n";
        for (Object label : labels) {
            output += label  + " F1 Score: " + (f1.get(label) / labelC.get(label))+"\n";
        }

        output += "Accuracy: "+acc / 10+"\n";
        
        return output;
    }
}
