package fuzzysets;

/**
 *
 * @author Ziheng Cong
 * 
 * This class stores the variables required to construct a rule and these include: 
 * Linguistic variables for input and output Fuzzified output and defuzzified outcome, 
 * if multiple rules are not fired at the same time.
 * 
 */
public class FuzzyRule {
    //Linguistic variables for input
    private double frequency;
    private double pressure;
    //Defuzzified outcome
    private double defuzzifiedOutcome;
    //The maximum of the flow changes
    private double max;
    //Index of max in set normalisedValues
    private int indexOfMax;
    //Index of frequency to output
    private int indexOfFrequency;
    //Index of indexOfPressure
    private int indexOfPressure;
    //Name of output linguisitic value
    private String deltaFName;
    //Create linguistic variables for fuzzified frequency output 
    private double fuzzifiedFrequencyF1;
    private double fuzzifiedFrequencyF2;
    private double fuzzifiedFrequencyF3;
    private double fuzzifiedFrequencyF4;
    //Create linguistic variables for fuzzified pressure output 
    private double fuzzifiedPressureLow;
    private double fuzzifiedPressureOptimum;
    private double fuzzifiedPressureHigh;
    private double fuzzifiedPressureVeryHigh;
    //Fuzzified frequency & pressure list for output
    private double[] fuzzifiedFrequencyList = new double[4];
    private double[] fuzzifiedPressureList = new double[4];
    //Rule detail list for output
    String [] ruleDetail = new String[9];
    
    
    
    public FuzzyRule(double pressure, double frequency) {
        //Input values
        this.frequency = frequency;
        this.pressure = pressure;
        //Get Members
        Controller controller = new Controller();
        FuzzyVariable frequencyTemp = controller.getFrequencyTemp();
        FuzzyVariable pressureTemp = controller.getPressureTemp();
        FuzzyVariable frequencyChange = controller.getFrequencyChange();
        //Use normalise method to fuzzify the given frequency values
        fuzzifiedFrequencyF1 = frequencyTemp.getMembers().get(0).trapezoidNormaliseWithTwoTriangle((float) frequency, frequencyTemp.getMembers().get(1));
        fuzzifiedFrequencyF2 = frequencyTemp.getMembers().get(2).trapezoidNormaliseWithTwoTriangle((float) frequency, frequencyTemp.getMembers().get(3));
        fuzzifiedFrequencyF3 = frequencyTemp.getMembers().get(4).trapezoidNormaliseWithTwoTriangle((float) frequency, frequencyTemp.getMembers().get(5));
        fuzzifiedFrequencyF4 = frequencyTemp.getMembers().get(6).trapezoidNormaliseWithTwoTriangle((float) frequency, frequencyTemp.getMembers().get(7));
        //Use normalise method to fuzzify the given pressure values
        fuzzifiedPressureLow = pressureTemp.getMembers().get(0).normalise((float) pressure);
        fuzzifiedPressureOptimum = pressureTemp.getMembers().get(1).normalise((float) pressure);
        fuzzifiedPressureHigh = pressureTemp.getMembers().get(2).normalise((float) pressure);
        fuzzifiedPressureVeryHigh = pressureTemp.getMembers().get(3).trapezoidNormaliseWithOneLeftTriangle((float) pressure, pressureTemp.getMembers().get(4));
        //Set function for output
        this.newFuzzifiedFreqencyList();
        this.newFuzzifiedPressureList();
        this.addRuleDetail();
    }

    
    /**
     * Store fuzzified frequency in to a list for getting index to output.
     */
    public void newFuzzifiedFreqencyList(){
        fuzzifiedFrequencyList[0] = fuzzifiedFrequencyF1;
        fuzzifiedFrequencyList[1] = fuzzifiedFrequencyF2;
        fuzzifiedFrequencyList[2] = fuzzifiedFrequencyF3;
        fuzzifiedFrequencyList[3] = fuzzifiedFrequencyF4;
    }
    
    /**
     * Store fuzzified frequency in to a list for getting index to output.
     */
    public void newFuzzifiedPressureList(){
        fuzzifiedPressureList[0] = fuzzifiedPressureLow;
        fuzzifiedPressureList[1] = fuzzifiedPressureOptimum;
        fuzzifiedPressureList[2] = fuzzifiedPressureHigh;
        fuzzifiedPressureList[3] = fuzzifiedPressureVeryHigh;
    }
    
    /**
     * Store rule detail for output purpose.
     */
    public void addRuleDetail(){
        ruleDetail[0] = "Rule1, low, F1, positiveHigh";
        ruleDetail[1] = "Rule2, low, F2, positiveMedium";
        ruleDetail[2] = "Rule3, low, F3, positiveHigh";
        ruleDetail[3] = "Rule4, low, F4, positiveMedium";
        ruleDetail[4] = "Rule5, optimum, F2, zero";
        ruleDetail[5] = "Rule6, optimum, F3, zero";
        ruleDetail[6] = "Rule7, optimum, F4, zero";
        ruleDetail[7] = "Rule8, high, F4, negativeMedium";
        ruleDetail[8] = "Rule9, veryHigh, F4, negativeMedium"; 
    }
    
    
    /**
     * Set the index of output frequency.
     */
    public void setFrequencyIndex(){
        int indexOfSelectFrequency = Integer.parseInt(ruleDetail[indexOfMax].split(", ")[2].split("")[1]);
        if (indexOfSelectFrequency == 2) {
            indexOfFrequency = 1;
        }else if(indexOfSelectFrequency == 3){
            indexOfFrequency = 2;
        }else if(indexOfSelectFrequency == 4){
            indexOfFrequency = 3;
        }else if(indexOfSelectFrequency == 1){
            indexOfFrequency = 0;
        }
    }
    
    /**
     * Set the index of output pressure.
     */
    public void setPressureIndex(){
        String indexOfSelectPressure = ruleDetail[indexOfMax].split(", ")[1];
        if (indexOfSelectPressure.equals("low")) {
            indexOfPressure = 0;
        }else if(indexOfSelectPressure.equals("optimum")){
            indexOfPressure = 1;
        }else if(indexOfSelectPressure.equals("high")){
            indexOfPressure = 2;
        }else if (indexOfSelectPressure.equals("veryHigh")) {
            indexOfPressure = 3;
        }
    }
    
    //Getters & Setters
    public double getFuzzifiedFrequencyF1() {
        return fuzzifiedFrequencyF1;
    }

    public void setFuzzifiedFrequencyF1(double fuzzifiedFrequencyF1) {
        this.fuzzifiedFrequencyF1 = fuzzifiedFrequencyF1;
    }

    public double getFuzzifiedFrequencyF2() {
        return fuzzifiedFrequencyF2;
    }

    public void setFuzzifiedFrequencyF2(double fuzzifiedFrequencyF2) {
        this.fuzzifiedFrequencyF2 = fuzzifiedFrequencyF2;
    }

    public double getFuzzifiedFrequencyF3() {
        return fuzzifiedFrequencyF3;
    }

    public void setFuzzifiedFrequencyF3(double fuzzifiedFrequencyF3) {
        this.fuzzifiedFrequencyF3 = fuzzifiedFrequencyF3;
    }

    public double getFuzzifiedFrequencyF4() {
        return fuzzifiedFrequencyF4;
    }

    public void setFuzzifiedFrequencyF4(double fuzzifiedFrequencyF4) {
        this.fuzzifiedFrequencyF4 = fuzzifiedFrequencyF4;
    }

    public double getFuzzifiedPressureLow() {
        return fuzzifiedPressureLow;
    }

    public void setFuzzifiedPressureLow(double fuzzifiedPressureLow) {
        this.fuzzifiedPressureLow = fuzzifiedPressureLow;
    }

    public double getFuzzifiedPressureOptimum() {
        return fuzzifiedPressureOptimum;
    }

    public void setFuzzifiedPressureOptimum(double fuzzifiedPressureOptimum) {
        this.fuzzifiedPressureOptimum = fuzzifiedPressureOptimum;
    }

    public double getFuzzifiedPressureHigh() {
        return fuzzifiedPressureHigh;
    }

    public void setFuzzifiedPressureHigh(double fuzzifiedPressureHigh) {
        this.fuzzifiedPressureHigh = fuzzifiedPressureHigh;
    }

    public double getFuzzifiedPressureVeryHigh() {
        return fuzzifiedPressureVeryHigh;
    }

    public void setFuzzifiedPressureVeryHigh(double fuzzifiedPressureVeryHigh) {
        this.fuzzifiedPressureVeryHigh = fuzzifiedPressureVeryHigh;
    }

    public double[] getFuzzifiedFrequencyList() {
        return fuzzifiedFrequencyList;
    }

    public void setFuzzifiedFrequencyList(double[] fuzzifiedFrequencyList) {
        this.fuzzifiedFrequencyList = fuzzifiedFrequencyList;
    }

    public double[] getFuzzifiedPressureList() {
        return fuzzifiedPressureList;
    }

    public void setFuzzifiedPressureList(double[] fuzzifiedPressureList) {
        this.fuzzifiedPressureList = fuzzifiedPressureList;
    }

    public String[] getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String[] ruleDetail) {
        this.ruleDetail = ruleDetail;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getDefuzzifiedOutcome() {
        return defuzzifiedOutcome;
    }

    public void setDefuzzifiedOutcome(double defuzzifiedOutcome) {
        this.defuzzifiedOutcome = defuzzifiedOutcome;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public int getIndexOfMax() {
        return indexOfMax;
    }

    public void setIndexOfMax(int indexOfMax) {
        this.indexOfMax = indexOfMax;
    }

    public int getIndexOfFrequency() {
        return indexOfFrequency;
    }

    public void setIndexOfFrequency(int indexOfFrequency) {
        this.indexOfFrequency = indexOfFrequency;
    }

    public int getIndexOfPressure() {
        return indexOfPressure;
    }

    public void setIndexOfPressure(int indexOfPressure) {
        this.indexOfPressure = indexOfPressure;
    }

    public String getDeltaFName() {
        return deltaFName;
    }

    public void setDeltaFName(String deltaFName) {
        this.deltaFName = deltaFName;
    }
      
}
