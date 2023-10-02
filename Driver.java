import java.util.Arrays;
import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException {

		// Create polynomials
		double[] const1 = { 2, -3, 6 }; // 2x - 3x^3 + 6x^5
		int[] exp1 = { 1, 3, 5 };
		Polynomial poly1 = new Polynomial(const1, exp1);

		double[] const2 = { 3, 1, -2, 4 }; // 3 + x - 2x^2 + 4x^3
		int[] exp2 = { 0, 1, 2, 3 };
		Polynomial poly2 = new Polynomial(const2, exp2);

		double[] const3 = { 1, 1 }; // 1 + 1x^3
		int[] exp3 = { 0, 3 };
		Polynomial poly3 = new Polynomial(const3, exp3);

		// Add polynomials
		Polynomial sum = poly1.add(poly2);
		System.out.println(
				"Sum of polynomials: " + Arrays.toString(sum.constants) + "  " + Arrays.toString(sum.exponents));
		System.out.println("Expected: [3.0, 3.0, -2.0, 1.0, 6.0]  [0, 1, 2, 3, 5]");
		System.out.println();

		// Evaluate polynomial at x = 2
		double x = 2;
		double result = poly1.evaluate(x);
		System.out.println("poly1(2) = " + result); // Expected: 172.0
		System.out.println("Expected: 172.0");
		System.out.println();

		// Check if poly1 has a root at x = 1
		if (poly1.hasRoot(1)) {
			System.out.println("1 is a root of poly1");
		} else {
			System.out.println("1 is not a root of poly1");
		}
		System.out.println();

		// Check if poly3 has a root at x = -1
		if (poly3.hasRoot(-1)) {
			System.out.println("-1 is a root of poly3");
		} else {
			System.out.println("-1 is not a root of poly3");
		}
		System.out.println();

		// Add polynomials
		Polynomial product = poly1.multiply(poly2);
		System.out.println("product of polynomials: " + Arrays.toString(product.constants) + "  "
				+ Arrays.toString(product.exponents));
		System.out.println("Expected: [6.0, 2.0, -13.0, 5.0, 24.0, -6.0, -12.0, 24.0]  [1, 2, 3, 5, 6, 7, 8]");
		System.out.println();

		// Save polynomial to file
		poly1.saveToFile("poly.txt");
		System.out.println("Saved Polynomial");

		// Create polynomial from file
		File f = new File("poly.txt");
		Polynomial newPoly = new Polynomial(f);
		System.out.println("Polynomial read from file: " + Arrays.toString(newPoly.constants) + "  "
				+ Arrays.toString(newPoly.exponents));
		System.out.println("Expected: [2.0, -3.0, 6.0]  [1, 3, 5]");
		System.out.println();

	}
}