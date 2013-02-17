
import entidades.*;
import enums.DiaSemana;
import exceptions.UsuarioNaoEncontradoException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retorno.HorarioMensalista;
import retorno.HorarioReserva;
import sessionbeans.business.AlugaQuadraRemote;
import sessionbeans.business.LoginRemote;
import sessionbeans.business.MensalistaRemote;
import sessionbeans.repository.ClienteRepositoryRemote;
import sessionbeans.repository.UsuarioRepositoryRemote;
import util.ServiceLocator;

/**
 * @author avsilva
 */
public class Main {

    public static void main(String[] args) {
        try {
            listarHorariosReservaValidos();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void findAllUsuario() throws Exception {
        UsuarioRepositoryRemote remote = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        List<Usuario> usuarios = remote.findAll();

        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome());
        }
    }

    private static void regex() {
        String expression = "\\d{4}-?\\d{3,4}";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher("7220-8244");

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    private static void criarMensalista() throws Exception {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 8);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Mensalista mensalista = new Mensalista();
        mensalista.setDataFimVigencia(c.getTime());
        mensalista.setMensalistaPK(new MensalistaPK(2, 1, sdf.parse("10:00"), new Date(), DiaSemana.QUINTA.getValor()));

        MensalistaRemote remote = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        remote.criarMensalista(mensalista);
    }

    private static void pesquisarMensalista() throws Exception {
        MensalistaRemote remote = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        List<HorarioMensalista> horarios =
                remote.getHorarioMensalista(DiaSemana.QUINTA, new Quadra(1l, true, "Norte"));

        for (HorarioMensalista h : horarios) {
            System.out.printf("A quadra %S está %S no(a) %S às %S\n", h.getNomeQuadra(), h.getSituacao(), h.getDiaSemana(), h.getHora());
        }
    }

    private static void login() throws Exception {
        LoginRemote login = ServiceLocator.getBean(LoginRemote.class, "LoginBean");
        Usuario user = login.login("avsilva", "avsilva");
        if (user == null) {
            return;
        }
    }

    private static void criarUsuario() throws Exception {
        UsuarioRepositoryRemote remote = ServiceLocator.getBean(UsuarioRepositoryRemote.class, "UsuarioRepository");
        Usuario u = remote.adicionar(new Usuario("avsilva", "avsilva"));
    }

    private static void trocarSenha() throws Exception, UsuarioNaoEncontradoException {
        LoginRemote l = ServiceLocator.getBean(LoginRemote.class, "LoginBean");
        l.trocarSenha("avsilva", "123gap", "avsilva");
    }

    private static void pesquisaCliente() throws Exception {
        ClienteRepositoryRemote remote = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        List<Cliente> clientes = remote.findByNomeOrDocumento("t", "");

        for (Cliente cliente : clientes) {
            for (Email email : cliente.getEmails()) {
                System.out.println(email.getEmail());
            }
        }
    }

    private static void criarCliente() throws Exception {
        ClienteRepositoryRemote remote = ServiceLocator.getBean(ClienteRepositoryRemote.class, "ClienteRepository");
        Cliente c = new Cliente();
    }

    private static void excluirMensalista() throws Exception {
        MensalistaRemote remote = ServiceLocator.getBean(MensalistaRemote.class, "MensalistaBean");
        List<HorarioMensalista> horarios =
                remote.getHorarioMensalista(DiaSemana.SEGUNDA, new Quadra(1l, true, "Norte"));

        if (horarios.isEmpty()) {
            throw new Exception("Não foram encontrados horarios para o parametro");
        }
        HorarioMensalista hora = horarios.get(0);
        Mensalista mensalidade = remote.getMensalidade(hora.getDiaSemana(), hora.getQuadra(), hora.getHora());

        System.out.println(mensalidade);

        System.out.println("Removendo");

        remote.alteraMensalidade(mensalidade);
    }

    private static void listarHorariosReservaValidos() throws Exception {
        AlugaQuadraRemote aluga = ServiceLocator.getBean(AlugaQuadraRemote.class, "AlugaQuadraBean");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<HorarioReserva> horariosLivres = aluga.getHorariosLivres(sdf.parse("30/01/2012"));

        for (HorarioReserva h : horariosLivres) {
            System.out.println(
                    "horario: " + h.getHoraExtenso() + 
                    " Quadra: " + h.getNomeQuadra());
        }
    }
}