import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Polynomial {

	public double[] constants;
	public int[] exponents;

	// no-argument constructor that sets the polynomial to zero
	public Polynomial() {
		constants = new double[] { 0 };
		exponents = new int[] { 0 };
	}

	// constructor that takes an array of double as an argument and sets the
	// coefficients accordingly
	public Polynomial(double[] constants, int[] exponents) {

		this.constants = constants;
		this.exponents = exponents;

	}

	public Polynomial(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();

		if (br.equals("")) {
			constants = null;
			exponents = null;
		}

		else {

			line = line.replace("+", "#+");
			line = line.replace("-", "#-");

			String[] splitPoly = line.split("#");

			double[] newConst = new double[splitPoly.length];
			int[] newExp = new int[splitPoly.length];

			for (int i = 0; i < splitPoly.length; i++) {

				String num = splitPoly[i];
				int index = num.indexOf('x');

				if (index != -1) {

					newConst[i] = Double.parseDouble(num.substring(0, index));
					newExp[i] = Integer.parseInt(num.substring(index + 1));

				} else {

					newConst[i] = Double.parseDouble(num);
					newExp[i] = 0;
				}
			}

			constants = newConst;
			exponents = newExp;

		}
		br.close();
	}

	// takes one argument of type Polynomial and returns the polynomial resulting
	// from adding the calling object and the argument
	public Polynomial add(Polynomial input) {

		int[] hold = new int[exponents.length + input.exponents.length];

		for (int i = 0; i < exponents.length; i++) {

			hold[i] = exponents[i];
		}

		int k = 0;

		for (int i = exponents.length; i < hold.length; i++) {

			hold[i] = input.exponents[k];

			k++;
		}

		// find # of distinct values in hold[]
		int maxExp = 0;

		for (int i = 0; i < hold.length; i++) {

			if (hold[i] > maxExp)
				maxExp = hold[i];

		}
		int total = 0;
		int count = 0;

		for (int i = 0; i <= maxExp; i++) {

			count = 0;
			for (int j = 0; j < hold.length; j++) {

				if (hold[j] == i)
					count++;

			}

			if (count > 0)
				total++;

		}

		double[] newPolyConst = new double[total];
		int[] newPolyExp = new int[total];

		k = 0;

		boolean check = false;

		// populate newPolyExp
		for (int i = 0; i <= maxExp; i++) {

			check = false;
			for (int j = 0; j < hold.length; j++) {

				if (hold[j] == i) {

					newPolyExp[k] = i;
					check = true;

				}
			}
			if (check)
				k++;
		}

		// populate newPolyConst
		for (int i = 0; i < exponents.length; i++) {

			count = 0;
			while (count < newPolyExp.length && exponents[i] != newPolyExp[count])
				count++;

			newPolyConst[count] = constants[i];

		}

		for (int i = 0; i < input.exponents.length; i++) {

			count = 0;

			while (count < newPolyExp.length && input.exponents[i] != newPolyExp[count])
				count++;

			newPolyConst[count] += input.constants[i];

		}

		return new Polynomial(newPolyConst, newPolyExp);

	}

	// takes one argument of type double representing a value of x and evaluates the
	// polynomial accordingly
	public double evaluate(double x) {

		double total = 0;

		for (int i = 0; i < constants.length; i++) {

			total += constants[i] * (Math.pow(x, exponents[i]));
		}

		return total;
	}

	// takes one argument of type double and determines whether this value is a root
	// of the polynomial or not
	public boolean hasRoot(double x) {

		return evaluate(x) == 0;

	}

	public Polynomial multiply(Polynomial input) {

		if (constants == null || input.constants == null) {

			return new Polynomial();
		}

		int len1 = constants.length;
		int len2 = input.constants.length;

		double[] newConst = new double[len1 * len2];
		int[] newExp = new int[len1 * len2];

		int k = 0;

		for (int i = 0; i < len1; i++) {

			for (int j = 0; j < len2; j++) {

				newConst[k] = constants[i] * input.constants[j];
				newExp[k] = exponents[i] + input.exponents[j];

				k++;
			}
		}

		// find # of distinct values in newExp[]
		int maxExp = 0;

		for (int i = 0; i < newExp.length; i++) {

			if (newExp[i] > maxExp)
				maxExp = newExp[i];

		}
		int total = 0;
		int count = 0;

		for (int i = 0; i <= maxExp; i++) {

			count = 0;
			for (int j = 0; j < newExp.length; j++) {

				if (newExp[j] == i)
					count++;

			}

			if (count > 0)
				total++;

		}

		double[] newPolyConst = new double[total];
		int[] newPolyExp = new int[total];

		k = 0;
		boolean check = false;

		for (int i = 0; i <= maxExp; i++) {

			check = false;
			for (int j = 0; j < newExp.length; j++) {

				if (newExp[j] == i) {

					newPolyConst[k] += newConst[j];
					newPolyExp[k] = i;
					check = true;

				}
			}
			if (check)
				k++;
		}

		return new Polynomial(newPolyConst, newPolyExp);

	}

	public void saveToFile(String name) throws IOException {

		File file = new File(name);

		if (file.exists() == false) {
			file.createNewFile();
		}

		FileWriter output = new FileWriter(name);
		int i = 0;

		output.write(Double.toString(constants[i]));
		if (exponents[i] != 0) {
			output.write("x");
			output.write(Integer.toString(exponents[i]));
		}

		for (i = 1; i < constants.length; i++) {
			if (constants[i] > 0) {
				output.write("+");
			}

			output.write(Double.toString(constants[i]));

			if (exponents[i] != 0) {
				output.write("x");
				output.write(Integer.toString(exponents[i]));
			}
		}

		output.close();

	}
}