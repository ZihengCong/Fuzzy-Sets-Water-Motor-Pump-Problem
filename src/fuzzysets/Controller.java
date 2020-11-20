package fuzzysets;

import java.util.ArrayList;

/**
 *
 * @author Ziheng Cong
 * 
 * This class creates the objects of pressure, frequency, and change in frequency 
 * F using the FuzzyVariable and FuzzyMember classes. This also creates the RuleSet, 
 * an ArrayList of FuzzyRule and stores the given rules. 
 * 
 */
public class Controller {

    FuzzyVariable frequencyTemp;
    FuzzyVariable pressureTemp;
    FuzzyVariable frequencyChange;
    private ArrayList<FuzzyRule> ruleSet;
    
    public Controller() {
        //Create objects of pressure, frequency and change in frequency
        frequencyTemp = new FuzzyVariable(8,0, (float) 5.8,"Frequency");
        pressureTemp = new FuzzyVariable(5, 5, 46, "Pressure");
        frequencyChange = new FuzzyVariable (7, (float)-0.31, (float)0.35, "FrequencyChange");
        ruleSet = new ArrayList <>(); 
        
        this.AddMembers();
    }
    
    
    public void AddMembers(){
        //Add members to each fuzzy set
        try{
            frequencyTemp.getMembers().add(new FuzzyMember(0, (float) 0.2,"LeftTriangle", "F1"));
            frequencyTemp.getMembers().add(new FuzzyMember((float) 1.8, 2, "RightTriangle", "F1")); 
            frequencyTemp.getMembers().add(new FuzzyMember((float) 1.8, 2, "LeftTriangle", "F2"));
            frequencyTemp.getMembers().add(new FuzzyMember((float) 2.5, 3, "RightTriangle", "F2")); 
            frequencyTemp.getMembers().add(new FuzzyMember((float) 2.7, (float) 2.8, "LeftTriangle", "F3")); 
            frequencyTemp.getMembers().add(new FuzzyMember(4, (float) 4.3, "RightTriangle", "F3"));
            frequencyTemp.getMembers().add(new FuzzyMember((float) 3.8,(float) 4.2, "LeftTriangle", "F4"));
            frequencyTemp.getMembers().add(new FuzzyMember((float) 5.8, 6, "RightTriangle", "F4")); 

            pressureTemp.getMembers().add(new FuzzyMember(5, 14, "Triangle", "Low"));
            pressureTemp.getMembers().add(new FuzzyMember(13, 15,"Triangle", "Optimum"));
            pressureTemp.getMembers().add(new FuzzyMember(14, 30, "Triangle", "High")); //Change the numerator of 30 to 1 that can get the correct outputs
            pressureTemp.getMembers().add(new FuzzyMember(22, 32, "LeftTriangle", "veryHigh"));
            pressureTemp.getMembers().add(new FuzzyMember(45, 46, "RightTriangle", "veryHigh")); //Just write as triangle but actually its not


            frequencyChange.getMembers().add(new FuzzyMember((float) -0.31,(float) 0.3,"LeftTriangle","negativeLow"));
            frequencyChange.getMembers().add(new FuzzyMember((float) -0.2, (float)-0.1,"RightTriangle","negativeLow")); 
            frequencyChange.getMembers().add(new FuzzyMember((float) -0.2, 0,"Triangle","negativeMedium"));
            frequencyChange.getMembers().add(new FuzzyMember((float) -0.01, (float) 0.01,"Triangle","Zero"));
            frequencyChange.getMembers().add(new FuzzyMember(0,(float) 0.2,"Triangle","positiveMedium"));
            frequencyChange.getMembers().add(new FuzzyMember((float) 0.1, (float) 0.2,"LeftTriangle","positiveHigh"));
            frequencyChange.getMembers().add(new FuzzyMember((float) 0.3, (float) 0.35,"RightTriangle","positiveHigh"));
        } 
        catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
    
     
    //Getters & Setters
    public FuzzyVariable getFrequencyTemp() {
        return frequencyTemp;
    }

    public void setFrequencyTemp(FuzzyVariable frequencyTemp) {
        this.frequencyTemp = frequencyTemp;
    }

    public FuzzyVariable getPressureTemp() {
        return pressureTemp;
    }

    public void setPressureTemp(FuzzyVariable pressureTemp) {
        this.pressureTemp = pressureTemp;
    }

    public FuzzyVariable getFrequencyChange() {
        return frequencyChange;
    }

    public void setFrequencyChange(FuzzyVariable frequencyChange) {
        this.frequencyChange = frequencyChange;
    }

    public ArrayList<FuzzyRule> getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(ArrayList<FuzzyRule> ruleSet) {
        this.ruleSet = ruleSet;
    }
    
}
