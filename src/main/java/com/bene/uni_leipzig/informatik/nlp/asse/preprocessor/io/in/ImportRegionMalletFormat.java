package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.in;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bene.uni_leipzig.informatik.nlp.asse.main.Preprocessor;

/**
 *
 * @author Bene
 */
public class ImportRegionMalletFormat implements ImportRegionInterface{

    @Override
    public ArrayList<LetterComponent> importData(String path) {
        ArrayList<LetterComponent> letterComponents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s", 6);
                String docLineID = split[0];
                String classID = split[1];
                Double left = Double.parseDouble(split[2].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1]);
                Double top = Double.parseDouble(split[3].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1]);
                Double width = Double.parseDouble(split[4].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1]);
                Double[] position = {left, top, width};
                String[] splitData = split[5].split("\\s+");
                ArrayList<String> data = new ArrayList<>();
                for(String tData:splitData){
                    if(tData.equals("")){
                        continue;
                    }
                    data.add(tData);
                }
                
                LetterComponent component = new LetterComponent(docLineID, classID, position, data);
                letterComponents.add(component);
            }
        } catch (Exception ex) {
            Logger.getLogger(Preprocessor.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return letterComponents;
    }
    
}
