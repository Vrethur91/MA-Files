package com.bene.uni_leipzig.informatik.nlp.asse.general.objects;

/**
 *
 * @author Bene
 */
public class ClassInfo {
    private String classID;
    private String parentClassID;
    private int depth;

    public ClassInfo(String classID, int depth, String parentClassID){
        this.classID = classID;
        this.parentClassID = parentClassID;
        this.depth = depth;
    }
    
    /**
     * @return the classID
     */
    public String getClassID() {
        return classID;
    }

    /**
     * @param classID the classID to set
     */
    public void setClassID(String classID) {
        this.classID = classID;
    }

    /**
     * @return the parentClassID
     */
    public String getParentClassID() {
        return parentClassID;
    }

    /**
     * @param parentClassID the parentClassID to set
     */
    public void setParentClassID(String parentClassID) {
        this.parentClassID = parentClassID;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
