package sessionbeans;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(LancadorDeDado.class)
public class LancadorDeDadoBean implements LancadorDeDado {
	public Random gerador = new Random();
	
	public int lanca(){
		return this.gerador.nextInt(5) + 1;
	}
	
	//Callbacks
	@PostConstruct
	public void inicializando()
	{
		System.out.println("Mais um lançador de dado criado...");
	}
	
	@PreDestroy
	public void destruindo()
	{
		System.out.println("Mais um lançador de dado será destruído...");
	}
}
