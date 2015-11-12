package com.bene.uni_leipzig.informatik.nlp.asse.general.io.in;

import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import java.util.ArrayList;

/**
 *
 * @author Bene
 */
public interface ImportInterface {
    public ArrayList<LetterComponent> importData(String path);
}
