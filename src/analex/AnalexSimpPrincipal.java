package analex;

public class AnalexSimpPrincipal {
	public static void main(String[] args) {
		//String exp = "x = ((soma2 + 34)) * (fator1 / 2);";
		String exp = "x = 2.1 ^ 1;";
		AnalexSimples analexSimples = new AnalexSimples(exp);
		analexSimples.analex();
		System.out.println(analexSimples);
	}
}
