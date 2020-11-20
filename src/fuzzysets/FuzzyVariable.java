package fuzzysets;

import java.util.ArrayList;

/**
 *
 * @author Ziheng Cong
 * 
 * This class stores the fuzzy sets corresponding to a particular type of input or 
 * output. For example, if you have three different fuzzy members F1, F2, and F3 
 * related to frequency, then a FuzzyVariable frequency will store all the three 
 * members. You can use an ArrayList of FuzzyMembers. 
 * 
 */
public class FuzzyVariable {
    private int          setSize;
    private float     startPoint,
                        endPoint;
    private String   variableName;
    private ArrayList<FuzzyMember>  members;
   
    public FuzzyVariable (int size, float start, float end, String name) {

       this.setSize = size;
       this.startPoint = start;
       this.endPoint = end;
       this.variableName = name;

       members = new ArrayList <>();
    }

    public int getSetSize() {
       return setSize;
    }

    public void setSetSize(int setSize) {
       this.setSize = setSize;
    }

    public float getStartPoint() {
       return startPoint;
    }

    public void setStartPoint(float startPoint) {
       this.startPoint = startPoint;
    }

    public float getEndPoint() {
       return endPoint;
    }

    public void setEndPoint(float endPoint) {
       this.endPoint = endPoint;
    }

    public String getVariableName() {
       return variableName;
    }

    public void setVariableName(String variableName) {
       this.variableName = variableName;
    }

    public ArrayList<FuzzyMember> getMembers() {
       return members;
    }

    public void setMembers(ArrayList<FuzzyMember> members) {
       this.members = members;
    }
   
    public void setOneMember (int index, FuzzyMember one) {
       this.members.set(index, one);
    }
    
    public FuzzyMember getOneMember(int index) {
       return this.members.get(index);
    }
    
   /**
    * For a number of fuzzified values the union is the maximum of those 
    * values using Zadeh's AND.In case of unfuzzified values, they should 
    * belong to the same FuzzySet defined here.
    * @param fuzzifiedVlaues
    * @return
    */  
    public double FuzzyUnion (double fuzzifiedValues[] ) {
        double max = fuzzifiedValues[0];
        for (int i=1; i<fuzzifiedValues.length; i++ )
             if ( max < fuzzifiedValues[i] )
                max = fuzzifiedValues[i];
        return max;
    }
    
    /**
     * This can be used for a set of normalized values.
     * @return min
     */
    public double FuzzyInterSection (double fuzzifiedValues[] ) {
        double min = fuzzifiedValues[0];
        for (int i=1; i<fuzzifiedValues.length; i++ )
             if ( min > fuzzifiedValues[i] )
                min = fuzzifiedValues[i];
        return min;
    } 
    
    @Override
    public String toString() {
       StringBuilder st = new StringBuilder();
       st.append(this.variableName);
       st.append(String.format(":  set size: %d  StartPoint: %.2f,  EndPoint: %.2f\n ", this.getSetSize(), this.getStartPoint(), this.getEndPoint()));
       for (FuzzyMember fm: this.getMembers())
            st.append(fm.toString());
       return st.toString();
    }
}
