package com.bene.uni_leipzig.informatik.nlp.asse.general.io.out;

import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bene
 */
public class DataFileWriter{
    
    public void writeInFile(String path, ArrayList<String> letterComponents) {
        try(BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
            bWriter.write("");
            for(String line:letterComponents){
                bWriter.append(line).append("\n");
            }
            bWriter.close();
        } catch (Exception ex) {
            Logger.getLogger(DataFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
