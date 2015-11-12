package com.bene.uni_leipzig.informatik.nlp.asse.general.io.in;

import com.bene.uni_leipzig.informatik.nlp.asse.general.io.PropertiesManager;
import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bene
 */
public class ImportHTMLDoc implements ImportInterface {

    @Override
    public ArrayList<LetterComponent> importData(String path) {
        File[] entries = new File(path).listFiles();
        System.out.println(entries.length);
        HashMap<String, String> classByColorCodeMap = PropertiesManager.getClassByColorCode("ClassColorCode.properties");
        ArrayList<LetterComponent> components = new ArrayList<>();
        
        for (File file : entries) {
            String docname = file.getName().split("\\.")[0].replace(" ", "_");
            int lineNumber = 0;
            try {
                FileReader reader = new FileReader(file);
                BufferedReader breader = new BufferedReader(reader);
                for (String line = ""; (line = breader.readLine()) != null;) {
                    if (line.startsWith("<span")) {
                        Matcher m = Pattern.compile("rgba(\\([^\\)]+\\)).+left:(\\d+)px.+top:(\\d+)px.+width:(\\d+)px[^>]+>([^<]+)<").matcher(line);
                        if (m.find()) {
                            lineNumber++;
                            String className = classByColorCodeMap.get(m.group(1));
                            String leftS = m.group(2);
                            String topS = m.group(3);
                            String widthS = m.group(4);
                            String text = m.group(5).trim();
                            
                            if(!text.equals("")){
                                Double left = Double.parseDouble(leftS);
                                Double top = Double.parseDouble(topS);
                                Double width = Double.parseDouble(widthS);
                                Double[] position = {left, top, width};
                                ArrayList<String> dataList = new ArrayList<>();
                                String[] splitText = text.split("\\s+");
                                for (String data : splitText) {
                                    if (dataList.equals("")) {
                                        continue;
                                    }
                                    dataList.add(data);
                                }
                                LetterComponent component = new LetterComponent(docname+"_"+lineNumber, className, position, dataList);
                                components.add(component);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ImportHTMLDoc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return components;
    }

}
