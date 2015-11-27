package com.bene.uni_leipzig.informatik.nlp.asse.preprocessor.objects;

import java.util.ArrayList;

/**
 *
 * @author Bene
 */
public class LetterComponent {
    private String docLineID;
    private String classID;
    private Double[] position;
    private ArrayList<String> data;
    private ArrayList<String> xFeatures;
    private ArrayList<String> bigramList;
    private ArrayList<String> bigramXList;
    
            

    public LetterComponent(String docLineID, String classID, Double[] position, ArrayList<String> data){
        this.docLineID = docLineID;
        this.classID = classID;
        this.position = position;
        this.data = data;
    }
    
    /**
     * @return the docLineID
     */
    public String getDocLineID() {
        return docLineID;
    }

    /**
     * @param docLineID the docLineID to set
     */
    public void setDocLineID(String docLineID) {
        this.docLineID = docLineID;
    }

    /**
     * @return the classID
     */
    public String getClassID() {
        return classID;
    }

    /**
     * @param className the classID to set
     */
    public void setClassID(String classID) {
        this.classID = classID;
    }

    /**
     * @return the position
     */
    public Double[] getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Double[] position) {
        this.setPosition(position);
    }

    /**
     * @return the data
     */
    public ArrayList<String> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    /**
     * @return the xFeatures
     */
    public ArrayList<String> getXFeatures() {
        return xFeatures;
    }

    /**
     * @param xFeatures the xFeatures to set
     */
    public void setXFeatures(ArrayList<String> xFeatures) {
        this.xFeatures = xFeatures;
    }

    /**
     * @return the bigramList
     */
    public ArrayList<String> getBigramList() {
        return bigramList;
    }

    /**
     * @param bigramList the bigramList to set
     */
    public void setBigramList(ArrayList<String> bigramList) {
        this.bigramList = bigramList;
    }

    /**
     * @return the bigramXList
     */
    public ArrayList<String> getBigramXList() {
        return bigramXList;
    }

    /**
     * @param bigramXList the bigramXList to set
     */
    public void setBigramXList(ArrayList<String> bigramXList) {
        this.bigramXList = bigramXList;
    }
}
