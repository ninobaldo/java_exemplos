package testes;

import javax.naming.InitialContext;

import sessionbeans.LancadorDeDado;

public class TesteDeAcesso {
	
	public static void main(String[] args) throws Exception{
		InitialContext ic = new InitialContext();
		
		LancadorDeDado lancadorDeDado = (LancadorDeDado) ic.lookup("java:global/dadoWeb/LancadorDeDadoBean");
		System.out.print(lancadorDeDado.lanca());
	}

}
