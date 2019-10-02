package anasin;

public class AnasinPrincipal {
	public static void main(String[] args) {
		String exp = "x = 2.1 + 1";
		AnasinSimp anasin = new AnasinSimp(exp);
		anasin.anasint();
	}
}
