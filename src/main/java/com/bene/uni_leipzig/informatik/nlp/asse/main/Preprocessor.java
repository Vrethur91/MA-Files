package com.bene.uni_leipzig.informatik.nlp.asse.main;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing.BigramGenerator;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing.XFeatureGenerator;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing.DataProcessor;
import com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing.ClassConverter;
import com.bene.uni_leipzig.informatik.nlp.asse.general.io.in.ImportInterface;
import com.bene.uni_leipzig.informatik.nlp.asse.general.io.in.ImportMalletFormat;
import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import com.bene.uni_leipzig.informatik.nlp.asse.general.io.converter.SVMFormatConverter;
import com.bene.uni_leipzig.informatik.nlp.asse.general.io.out.DataFileWriter;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Bene
 */
public class Preprocessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        preprocess();
        //oneVsAll();
        //testground();
    }

    public static void testground(){
        Properties prop = new Properties();
        prop.put("Hallo", 1);
        prop.put("Hallo", Integer.parseInt(prop.get("Hallo").toString())+1);
        System.out.println(prop.size());
        System.out.println(prop.get("Hallo"));
    }

    public static void preprocess() {
        ImportInterface dataImport = new ImportMalletFormat();
        ArrayList<LetterComponent> letterComponents = dataImport.importData("./data/4L.txt");

        ClassConverter converter = new ClassConverter();
        //converter.transformToHigherLevel(letterComponents, 2);

        DataProcessor cleaner = new DataProcessor();
        //cleaner.removeStopWords(letterComponents, "stopwords_short.txt");
        //cleaner.removeUniqueWords(letterComponents);
        //cleaner.keepNTopWordsOnly(letterComponents, 20);
        //cleaner.separateWordsAndNumbers(letterComponents);

        XFeatureGenerator xGenerator = new XFeatureGenerator();
        //xGenerator.createXFeatures(letterComponents);

        BigramGenerator gramGenerator = new BigramGenerator();
        //gramGenerator.transformToBigrams(letterComponents);

        SVMFormatConverter svmConverter = new SVMFormatConverter();
        ArrayList<String> svm = svmConverter.convertToSVMFormat(letterComponents, true, true, true);

        DataFileWriter export = new DataFileWriter();
        //export.writeInFile("./Output/4L_test", svm);
        //Evaluation.evaluate(letterComponents);
    }

    public static void oneVsAll() {
        String[] ids = {"1421", "1422", "1123", "141", "1121", "1", "15", "1133", "162", "154", "1124",
            "1211", "1131", "151", "1122", "1125", "153", "1611", "111", "1212", "1132", "123", "1612", "143",
            "122", "112", "11", "12", "152", "13", "121", "1134"};
        for (String id : ids) {
            ImportInterface dataImport = new ImportMalletFormat();
            ArrayList<LetterComponent> letterComponents = dataImport.importData("./data/4L.txt");

            ClassConverter converter = new ClassConverter();
            //converter.transformToHigherLevel(letterComponents, 2);
            converter.transformToOneVsAll(letterComponents, id);

            DataProcessor cleaner = new DataProcessor();
            //cleaner.removeStopWords(letterComponents, "stopwords_short.txt");
            //cleaner.removeUniqueWords(letterComponents);
            //cleaner.keepNTopWordsOnly(letterComponents, 20);
            //cleaner.separateWordsAndNumbers(letterComponents);

            XFeatureGenerator xGenerator = new XFeatureGenerator();
            //xGenerator.createXFeatures(letterComponents);
            BigramGenerator gramGenerator = new BigramGenerator();
            //gramGenerator.transformToBigrams(letterComponents);

            SVMFormatConverter svmConverter = new SVMFormatConverter();
            ArrayList<String> svm = svmConverter.convertToSVMFormat(letterComponents, true, true, true);

            DataFileWriter export = new DataFileWriter();
            export.writeInFile("./Output/onevsall/4L_" + id, svm);
            //Evaluation.evaluate(letterComponents);
        }
    }
}
