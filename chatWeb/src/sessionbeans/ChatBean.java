package sessionbeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ChatBean {
	
	private Set<String> salas = new HashSet<String>();
	
	public void criaSala(String sala)
	{
		this.salas.add(sala);
	}
	
	public List<String> listaSalas()
	{
		return new ArrayList<String>(this.salas);
	}
}
