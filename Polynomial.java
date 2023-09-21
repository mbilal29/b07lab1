
public class Polynomial {
	
	private double[] constants;
	
	//no-argument constructor that sets the polynomial to zero
	public Polynomial() {
		constants = new double[] {0};
			
	}
	
	//constructor that takes an array of double as an argument and sets the coefficients accordingly
	public Polynomial(double[] input) {
		
		int len = input.length;
		
		constants = new double[len];
		
		for (int i=0; i<len; i++) {
			constants[i] = input[i];
		}
	
	}
	
	//takes one argument of type Polynomial and returns the polynomial resulting from adding the calling object and the argument
	public Polynomial add(Polynomial input) {
		
        int maxLen = Math.max(constants.length, input.constants.length);
        
        double[] newPoly = new double[maxLen];
        
        double a, b;

        for (int i = 0; i < maxLen; i++) {
        	
        	a = 0;
        	b = 0;
        	
        	if (i < constants.length) {
        		a = constants[i];
        	}
        	
        	if (i < input.constants.length) {
        		b = input.constants[i];
        	}
        	
            newPoly[i] = a + b;
            
        }

        return new Polynomial(newPoly);
    }
		
	
	//takes one argument of type double representing a value of x and evaluates the polynomial accordingly
	public double evaluate(double x) {
		
		double total = 0;
		
		for (int i=0; i<constants.length ; i++) {
			
			total += constants[i]*(Math.pow(x, i));
		}
		
		return total;
	}
	
	//takes one argument of type double and determines whether this value is a root of the polynomial or not
	public boolean hasRoot(double x) {
		
		return evaluate(x) == 0;
		
	}
	
	
}


