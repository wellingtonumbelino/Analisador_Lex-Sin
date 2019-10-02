package anasin;

import java.util.List;

import analex.AnalexSimples;
import analex.Token;
import analex.TokenLexema;

public class AnasinSimp {

	private List<TokenLexema> tokenLexemaList;
	private int contador = 0;

	public AnasinSimp(String cadeia) {
		AnalexSimples analex = new AnalexSimples(cadeia);
		this.tokenLexemaList = analex.analex();
	}
	
	public void anasint() {
		if(this.tokenLexemaList!=null) {
			this.program();
			if(this.contador!=tokenLexemaList.size()) {
				//ERRO NO ANALISADOR SINTÁTICO
				this.msg("ERRO NO ANALISADOR SINTÁTICO");
			}
		}
		else {
			//ERRO NO ANALISADOR LÉXICO
			this.msg("ERRO NO ANALISADOR LÉXICO");
		}
		
	}
	
	private void  program() {
		this.msg("Entrou program()");
		this.msg("begin");
		this.stmt_list();
		this.msg("end");
		this.msg("Saiu program() teste id, token: " + this.nextToken());
	}
	
	private void stmt_list() {
		this.msg("Entrou em stmt_list");
		this.stmt();
		while(this.nextToken().getToken() == Token.PONTO_VIRGULA) {
			this.msg("token: " + this.nextToken());
			this.lex();
			this.stmt_list();
		}
		this.msg("Saiu do stmt_list");
	}
	
	private void stmt() {
		this.msg("Entrou em stmt, token: " + this.nextToken());
		if(this.nextToken().getToken() == Token.IDENTIFICADOR) {
			this.msg("ID: " + this.nextToken());
			this.lex();
			if(this.nextToken().getToken() == Token.OPERADOR_ATRIB) {
				this.msg("token: " + this.nextToken());
				this.lex();
				this.expression();
			}
		}
		this.msg("Saiu do stmt, teste ID, token: " + this.nextToken());
	}
	
	private void expression() {
		this.msg("Entrou em expression(), token: " + this.nextToken());
		this.term();
		while(this.nextToken().getToken() == Token.OPERADOR_SOMA ||
			  this.nextToken().getToken() == Token.OPERADOR_SUBT) {
			this.lex();
			this.term();
		}
		this.msg("Saiu de expression(), token: " + this.nextToken());
	}
	
	private void term() {
		this.msg("Entrou em term(), token: " + this.nextToken());
		this.factor();
		while(this.nextToken().getToken() == Token.OPERADOR_MULT ||
			  this.nextToken().getToken() == Token.OPERADOR_DIVI) {
			this.lex();
			this.factor();
		}
		this.msg("Saiu de term(), token: " + this.nextToken());
	}
	
	private void factor() {
		this.msg("Entrou em factor(), token: " + this.nextToken());
		this.expr();
		if(this.nextToken().getToken() == Token.EXPONENCIAL) {
			this.lex();
			this.factor();
		}
		this.msg("Saiu de factor(), token: " + this.nextToken());
	}
	
	private void expr() {
		this.msg("Entrou em expr(), token: " + this.nextToken());
		if(this.nextToken().getToken() == Token.IDENTIFICADOR) {
			this.msg("ID: " + this.nextToken());
			this.lex();
		}else if(this.nextToken().getToken() == Token.LITERAL_INTEIRO) {
			this.lex();
		}else if(this.nextToken().getToken() == Token.CONSTANTE_FLOAT) {
				this.lex();
		}else if(this.nextToken().getToken() == Token.LITERAL_INTEIRO) {
					this.msg("Token: CONSTANTE_FLOAT ");
		}else if(this.nextToken().getToken() == Token.PARENTESIS_ESQ) {
			this.msg(""+this.nextToken());
			this.lex();
			this.expression();
			if(this.nextToken().getToken() == Token.PARENTESIS_DIR) {
				this.msg(""+this.nextToken());
			}else
				this.msg("ERRO: PARENSTESIS_DIR esperado");
		}else
			this.msg("ERRO: IDENTIFICADOR, LITERAL_INTEIRO, PARENTESIS_ESQ esperados");
		
		this.msg("Saiu de expr(), token: " + this.nextToken());
	}
	
	private void msg(String cadeia) {
		System.out.println(cadeia);
	}
	
	private void lex() {
		if(this.contador==this.tokenLexemaList.size()) return;
		this.contador++;
	}
	
	private TokenLexema nextToken() {
		if(this.contador == this.tokenLexemaList.size()) return new TokenLexema(Token.FIM, Token.FIM.getValor()+"");
		return this.tokenLexemaList.get(this.contador);
	}

}
