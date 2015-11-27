package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.io.in;

import com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects.LetterComponent;
import java.util.ArrayList;

/**
 *
 * @author Bene
 */
public interface ImportRegionInterface {
    public ArrayList<LetterComponent> importData(String path);
}
