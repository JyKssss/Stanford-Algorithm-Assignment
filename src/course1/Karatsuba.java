package course1;
import java.awt.print.Printable;
import java.lang.Math;
import java.math.BigInteger;
public class Karatsuba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger i1 = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
		BigInteger i2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
//		BigInteger i3 =i1.shiftLeft(1);
		BigInteger result = bigProduct(i1,i2);
		System.out.println(result.toString());
		System.out.println(i1.multiply(i2).toString());
	};
	public static BigInteger bigProduct(BigInteger x ,BigInteger y) {
		int N = Math.max(x.bitLength(), y.bitLength());
		if (N<100) {
			return x.multiply(y);
		}
		N =N/2 + N%2;
		BigInteger a = x.shiftRight(N);
		BigInteger b = x.subtract(a.shiftLeft(N));
		BigInteger c = y.shiftRight(N);
		BigInteger d = y.subtract(c.shiftLeft(N));
		
		BigInteger ac = bigProduct(a, c);
		BigInteger bd = bigProduct(b, d);
		BigInteger abcd = bigProduct(a.add(b), c.add(d));
		
		
		return bd.add(abcd.subtract(ac).subtract(bd).shiftLeft(N).add(ac.shiftLeft(2*N)));
		
	}
}
