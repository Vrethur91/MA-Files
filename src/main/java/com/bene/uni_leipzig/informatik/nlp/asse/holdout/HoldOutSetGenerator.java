package com.bene.uni_leipzig.informatik.nlp.asse.holdout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bene
 */
public class HoldOutSetGenerator {

    public static void main(String[] args) {
        HoldOutSetGenerator gen = new HoldOutSetGenerator();
        gen.generate("./data/Briefkopfelemente_4Ebenen.txt", 132);
    }

    public void generate(String path, int size) {
        boolean loop = true;
        ArrayList<String> lines = loadList(path);
        ArrayList<String> rest = new ArrayList<>();
        ArrayList<String> holdout = null;
        
        while (loop) {
            holdout = new ArrayList<>();
            System.out.println("Generate Index Set");
            HashSet<Integer> indexSet = new HashSet<>();
            while(indexSet.size()<size) {
                boolean isNewIndex = false;
                int newIndex;
                while (!isNewIndex) {
                    newIndex = (int) (Math.random() * lines.size() - 1);
                    if (!indexSet.contains(newIndex)) {
                        indexSet.add(newIndex);
                        holdout.add(lines.get(newIndex));
                        isNewIndex = true;
                    }
                }
            }

            HashMap<String, Integer> countAll = countClasses(lines);
            HashMap<String, Integer> countHoldout = countClasses(holdout);
            System.out.println("Put out class counts");
            Set<String> keySet = countAll.keySet();
            for (String key : keySet) {
                String out = key + ": " + countAll.get(key) + " " + ((countHoldout.containsKey(key) ? countHoldout.get(key) : 0));
                System.out.println(out);
            }

            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Save?(y/n):");
                String in = input.readLine();
                if (in.equals("y")) {
                    saveList(holdout, "holdout.txt");

                    for (int j = 0; j < lines.size(); j++) {
                        if (!indexSet.contains(j)) {
                            rest.add(lines.get(j));
                        }
                    }

                    saveList(rest, "4L.txt");

                    loop = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(HoldOutSetGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ArrayList<String> loadList(String path) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception ex) {
            Logger.getLogger(HoldOutSetGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lines;
    }

    private void saveList(ArrayList<String> lines, String name) {
        try (BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"))) {
            bWriter.write("");
            for (String line : lines) {
                bWriter.append(line).append("\n");
            }
            bWriter.close();
        } catch (Exception ex) {
            Logger.getLogger(HoldOutSetGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HashMap<String, Integer> countClasses(ArrayList<String> lines) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String line : lines) {
            String lineClass = line.split(" ", 3)[1];
            if (map.containsKey(lineClass)) {
                map.put(lineClass, map.get(lineClass) + 1);
            } else {
                map.put(lineClass, 1);
            }
        }

        return map;
    }
}
