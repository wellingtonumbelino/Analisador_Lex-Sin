package anasin;

public class AnasinPrincipal {
	public static void main(String[] args) {
		String exp = "> x = 4.1 ^ 3; soma = total + (3 + y) !";
		AnasinSimp anasin = new AnasinSimp(exp);
		anasin.anasint();
	}
}
