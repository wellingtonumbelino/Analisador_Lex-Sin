package analex;

public class AnalexSimpPrincipal {
	public static void main(String[] args) {
		//O token: '>' é o mesmo que BEGIN e o token: '!' o mesmo que END
		String exp = "> x = ((soma2 + 34.5)) * (fator1 / 2) !";
		AnalexSimples analexSimples = new AnalexSimples(exp);
		analexSimples.analex();
		System.out.println(analexSimples);
	}
}
