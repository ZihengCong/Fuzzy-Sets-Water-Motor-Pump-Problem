package fuzzysets;

/**
 *
 * @author Ziheng Cong
 * 
 * This should store: start point, endpoint, membership type. This should have a 
 * fuzzy member method which takes an input and converts it to fuzzified (normalised) 
 * value. You need appropriate membership functions such as leftTriangle, rightTriangle 
 * and Triangle as required. You should also modify the toString() method to display 
 * output properly. 
 * 
 */
public class FuzzyMember {
    private float startPoint,endPoint;
    private String functionType;
    private String name;
    private final String[] FUNCTION_TYPES  = { "LeftTriangle", "RightTriangle", "Triangle"};
   
    public FuzzyMember(float start, float end, String function, String name) throws Exception {
        boolean found = false;
        if (start  >= end) {
            this.startPoint = end;
            this.endPoint = start;
        }
        else {
            this.startPoint = start;
            this.endPoint = end;  
        }
        for (String s: FUNCTION_TYPES) 
            if (s.equalsIgnoreCase(function)) {
                this.functionType = s;
                found = true;
          }
        if (!found)
            throw new Exception("undefined functionType: Should be one of: LeftTriangle, RightTriangle, Triangle "); 
        this.name = name;  
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

    public String getFunctionType() {
       return functionType;
    }

    public void setFunctionType(String functionType) throws Exception{
         boolean found = false;
         for (String s: FUNCTION_TYPES) 
           if (s.equalsIgnoreCase(functionType)) {
               this.functionType = s;
               found = true;
           }
         if (!found)
             throw new Exception("undefined functionType: Should be one of: LeftTriangle, RightTriangle, Triangle "); 
    }

    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }
   
    public double normalise (float a) {
         double normalised = -1;
         if  (this.functionType.equalsIgnoreCase(this.FUNCTION_TYPES[0]))
             normalised = this.LeftTriangle(a);
         else if (this.functionType.equalsIgnoreCase(this.FUNCTION_TYPES[1]))
             normalised = this.RightTriangle(a);
         else if (this.functionType.equalsIgnoreCase(this.FUNCTION_TYPES[2]))
             normalised = this.Triangle(a);
         return normalised;
    }
   
    /**
     * Calculate the fuzzy values from trapezoid with two triangle
     * @param a Input value
     * @param f The right triangle of trapezoid
     * @return  Normalised value
     */
    public double trapezoidNormaliseWithTwoTriangle(float a, FuzzyMember f){
        double trapezoidNormalised = -1;
        if (a >= this.getStartPoint() && a < this.getEndPoint()) {
            trapezoidNormalised = this.RightTriangle(a);
        }else if(a >= this.getEndPoint() && a <= f.getStartPoint()){
            trapezoidNormalised = 1.0;
        }else if(a > f.getStartPoint() && a < f.getEndPoint()){
            trapezoidNormalised = f.LeftTriangle(a);
        }else{
            trapezoidNormalised = 0.0;
        }
        return trapezoidNormalised;
    }
   
    /**
     * Calculate the fuzzy values from trapezoid with one left triangle
     * @param a Input value
     * @param f The right triangle of trapezoid
     * @return  Normalised value
     */
     public double trapezoidNormaliseWithOneLeftTriangle(float a, FuzzyMember f){
         double trapezoidNormalised = -1;
         if (a >= this.getStartPoint() && a < this.getEndPoint()) {
             trapezoidNormalised = this.RightTriangle(a);
         }else if(a >= this.getEndPoint() && a <= f.getEndPoint()){
             trapezoidNormalised = 1.0;
         }else{
             trapezoidNormalised = 0.0;
         }
         return trapezoidNormalised;
     }

    /**
     * This method is used to calculate membership value of a variable a if 
     * it is within a Triangular membership function.
     * @param a
     * @param firstPoint
     * @param secondPoint
     * @return fuzzy value
     */  
     private float Triangle(float a)
     {
           float tmp,middle;

           middle=(this.endPoint-this.startPoint)/2;

           if (a < this.startPoint || a > this.endPoint) {
               tmp = 0.0f;
           }else if (a<=(middle+this.startPoint)) {
               tmp=(a-this.startPoint)/middle;
           }else{
               tmp=(this.endPoint-a)/middle;
           }
           return tmp;
     }
     
    /**
     * This method is used to calculate membership value of a variable a if it is within a Left Triangular membership function.
     * @param a
     * @param firstPoint
     * @param secondPoint
     * @return 
     */
    private float LeftTriangle(float a)
    {
          float tmp;

          if (a<=this.startPoint)
              tmp=1.0f;
          else if (a>=this.endPoint )
                  tmp = 0.0f;
          else
              tmp=(this.endPoint-a)/(this.endPoint-this.startPoint);

          return tmp; 
    }
  
    /**
     * This method is used to calculate membership value of a variable a if it is within a Right Triangular membership function.
     * @param a
     * @param firstPoint
     * @param secondPoint
     * @return 
     */
    private float RightTriangle(float a )
    {
          float tmp;
          if (a <= this.startPoint)
             tmp=0.0f;
          else
            if (a>=this.endPoint)
              tmp=1.0f;
      else
          tmp=(a-this.startPoint)/(this.endPoint-this.startPoint);
      return tmp;
    }
    
    /**
     * This method is used to defuzzify a value if the fuzzified  value is calculated from a Triangle.
     * This method returns the right maximum.
     * if middle-of-maximum return (tmp[0]+ tmp[1])/2;
     */
    public double defuzzifyTriangle(double a) throws Exception {
        double []tmp = new double [2];
        if (a >1 || a < 0)
            throw new Exception("given value is outside the range of the Triangle");
        if (a == 0){
            tmp[0] = this.startPoint;
            tmp[1] = this.endPoint;
    }
        else if (a == 1)
        tmp[0] = tmp[1] = 0.5* (this.endPoint-this.startPoint)+ this.startPoint;
        else {
            double middle = (this.endPoint - this.startPoint)/2;
            tmp[0] = a*middle+this.startPoint ;
            tmp[1] = this.endPoint - a*middle;
        }
        if (tmp[0] > tmp[1])
            return tmp[0];
        else
            return tmp[1];
  }
}
