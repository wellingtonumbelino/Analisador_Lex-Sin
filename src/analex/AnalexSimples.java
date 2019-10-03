package analex;

import java.util.ArrayList;
import java.util.List;

public class AnalexSimples {
	private List<TokenLexema> tokenLexemaList;
	private String cadeia;
	private int i;
	private char cadeiaArray[];
	/**
	 * Recebe uma cadeia a ser analisada
	 */
	public AnalexSimples(String cadeia) {
		this.cadeia = cadeia;
		this.tokenLexemaList = new ArrayList<TokenLexema>();
	}
	/**
	 * Analisa a cadeia.
	 * @return uma lista de {@link TokenLexema} ou <code>null</code>, caso a cadeia tenha um lexema inválido.
	 */
	public List<TokenLexema> analex() {
		if (this.cadeia == null)
			return null;
	 
		cadeiaArray = this.cadeia.toCharArray();
		for (i = 0; i < cadeiaArray.length; i++) {
			if (!this.ehParentesis(cadeiaArray[i]))
			if (!this.ehOperador(cadeiaArray[i]))
			if (!this.ehPontoVirgula(cadeiaArray[i]))
			//if (!this.ehLiteralInteiro(cadeiaArray[i])) 
			if (!this.ehConstanteFloat(cadeiaArray[i]))
			if (!this.ehIdentificador(cadeiaArray[i]))
			if (!Character.isWhitespace(cadeiaArray[i]))
			if (!this.ehExponencial(cadeiaArray[i]))
			if(!this.ehBegin(cadeiaArray[i]))
			if(!this.ehEnd(cadeiaArray[i]))
			{
				//ERRO pois não é nada reconhecido...
				//retorne null
				System.out.println("ERRO, nenhum lexema reconhecido...");
				return null;
			}
		}

		return this.tokenLexemaList;
	}

	private boolean ehParentesis(char c) {
		if (c == Token.PARENTESIS_ESQ.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_ESQ, "" + Token.PARENTESIS_ESQ.getValor()));
			return true;
		} else if (c == Token.PARENTESIS_DIR.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PARENTESIS_DIR, "" + Token.PARENTESIS_DIR.getValor()));
			return true;
		}
		return false;
	}

	private boolean ehOperador(char c) {
		if (c == Token.OPERADOR_ATRIB.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_ATRIB, "" + Token.OPERADOR_ATRIB.getValor()));
			return true;
		} else if (c == Token.OPERADOR_MULT.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_MULT, "" + Token.OPERADOR_MULT.getValor()));
			return true;
		} else if (c == Token.OPERADOR_SOMA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SOMA, "" + Token.OPERADOR_SOMA.getValor()));
			return true;
		} else if (c == Token.OPERADOR_SUBT.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_SUBT, "" + Token.OPERADOR_SUBT.getValor()));
			return true;
		} else if (c == Token.OPERADOR_DIVI.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.OPERADOR_DIVI, "" + Token.OPERADOR_DIVI.getValor()));
			return true;
		}
		return false;
	}
	
	public boolean ehExponencial(char c) {
		if (c == Token.EXPONENCIAL.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.EXPONENCIAL, "" + Token.EXPONENCIAL.getValor()));
			return true;
		}
		return false;
	}

	public boolean ehPontoVirgula(char c) {
		if (c == Token.PONTO_VIRGULA.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.PONTO_VIRGULA, "" + Token.PONTO_VIRGULA.getValor()));
			return true;
		}
		return false;
	}

	/*public boolean ehLiteralInteiro(char c) {
		if (Character.isDigit(c)) {
			String digito = "";
			do {
				digito += cadeiaArray[i] + "";
				i = i + 1;
			} while (i != cadeiaArray.length && Character.isDigit(cadeiaArray[i]));

			this.tokenLexemaList.add(new TokenLexema(Token.LITERAL_INTEIRO, digito));
			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}*/

	public boolean ehIdentificador(char c) {
		if (Character.isLetter(c)) {
			String identificador = "";
			do {
				identificador += cadeiaArray[i];
				i = i + 1;
			} while (i != cadeiaArray.length && (Character.isLetter(cadeiaArray[i]) || Character.isDigit(cadeiaArray[i])));
			this.tokenLexemaList.add(new TokenLexema(Token.IDENTIFICADOR, identificador));
			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}
	
	public boolean ehBegin(char c) {
		if (c == Token.BEGIN.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.BEGIN, "" + Token.BEGIN.getValor()));
			return true;
		}
		return false;
	}
	
	public boolean ehEnd(char c) {
		if (c == Token.END.getValor()) {
			this.tokenLexemaList.add(new TokenLexema(Token.END, "" + Token.END.getValor()));
			return true;
		}
		return false;
	}
	
	public boolean ehConstanteFloat(char c) {
		if (Character.isDigit(c)) {
			String digito = "";
			do {
				digito += cadeiaArray[i] + "";
				i = i + 1;
			} while (i != cadeiaArray.length && (Character.isDigit(cadeiaArray[i]) || cadeiaArray[i] == Token.CONSTANTE_FLOAT.getValor()));

			char cadeiaDig[];
			cadeiaDig = digito.toCharArray();
			int j = 0;
			int cont = 0;
			while(j < cadeiaDig.length) {
				if(cadeiaDig[j] == Token.CONSTANTE_FLOAT.getValor())
					cont++;
				j++;
			}
			if(cont > 0) {
				this.tokenLexemaList.add(new TokenLexema(Token.CONSTANTE_FLOAT, digito));
			}else {
				this.tokenLexemaList.add(new TokenLexema(Token.LITERAL_INTEIRO, digito));	
			}
			
			if (i != cadeiaArray.length) {
				i = i - 1;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (TokenLexema tl : this.tokenLexemaList) {
			res += tl;
			res += "\n";
		}
		return res;
	}
}
