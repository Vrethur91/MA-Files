package com.bene.uni_leipzig.informatik.nlp.asse.preprocessing.processing;

import com.bene.uni_leipzig.informatik.nlp.asse.general.objects.LetterComponent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Bene
 */
public class DataProcessor {

    /**
     * public void separateWordsAndNumbers(ArrayList<LetterComponent>
     * letterComponents){ for (LetterComponent component : letterComponents) {
     * ArrayList<String> data = component.getData(); Matcher m =
     * Pattern.compile("(([^\\s\\d\\.\\[\\]]+\\d+)|(\\d+[^\\s\\d\\.\\[\\]]+))").matcher(data);
     * while (m.find()) { String found = m.group(1); String repString = "";
     * String[] split = found.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"); for (int
     * i = 0; i < split.length; i++) { repString += split[i]; if (i <
     * split.length - 1) { repString += " "; } }
     *
     * data = data.replace(found, repString); } component.setData(data); } }
     */
    public void removeStopWords(ArrayList<LetterComponent> components, String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            HashSet<String> stopWords = new HashSet<>();
            String line = "";
            while ((line = br.readLine()) != null) {
                stopWords.add(line.trim());
            }

            for (LetterComponent component : components) {
                ArrayList<String> newData = new ArrayList<>();
                for (String data : component.getData()) {
                    if (!stopWords.contains(data.toLowerCase())) {
                        newData.add(data);
                    }
                }
                component.setData(newData);
            }
        } catch (Exception ex) {
            Logger.getLogger(DataProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void keepNTopWordsOnly(ArrayList<LetterComponent> components, int n) {
        HashMap<String, Integer> occurenceMap = countWordOccurence(components);
        ArrayList<String> wordsSortByOccAsc = new ArrayList<>();
        for (Entry<String, Integer> entry : occurenceMap.entrySet()) {
            wordsSortByOccAsc.add(entry.getKey());
        }

        HashSet<String> topNWords = new HashSet<>();
        for (int i = wordsSortByOccAsc.size(); i > wordsSortByOccAsc.size() - n; i--) {
            topNWords.add(wordsSortByOccAsc.get(i - 1));
        }

        for (LetterComponent component : components) {
            ArrayList<String> newData = new ArrayList<>();
            for (String data : component.getData()) {
                if (topNWords.contains(data)) {
                    newData.add(data);
                }
            }
            component.setData(newData);
        }
    }

    private HashMap<String, Integer> countWordOccurence(ArrayList<LetterComponent> components) {
        HashMap<String, Integer> occurenceMap = new HashMap<>();
        for (LetterComponent component : components) {
            for (String data : component.getData()) {
                if (occurenceMap.containsKey(data)) {
                    occurenceMap.put(data, occurenceMap.get(data) + 1);
                } else {
                    occurenceMap.put(data, 1);
                }
            }
        }

        return sortMapByValue(occurenceMap);
    }

    private static <K, V extends Comparable<? super V>> HashMap<K, V> sortMapByValue(HashMap<K, V> map) {
        HashMap<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    public void removeUniqueWords(ArrayList<LetterComponent> components) {
        HashMap<String, Integer> tokenCount = new HashMap<>();
        for (LetterComponent component : components) {
            for (String token : component.getData()) {
                if (token.equals("")) {
                    continue;
                }
                if (tokenCount.containsKey(token)) {
                    tokenCount.put(token, (tokenCount.get(token) + 1));
                } else {
                    tokenCount.put(token, 1);
                }
            }
        }
        for (LetterComponent component : components) {
            ArrayList<String> newDataList = new ArrayList<>();
            for (String token : component.getData()) {
                if (tokenCount.get(token) > 1) {
                    newDataList.add(token);
                }
            }
            component.setData(newDataList);
        }
    }

}
