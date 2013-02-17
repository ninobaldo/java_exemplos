/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Professor;
import exceptions.ClienteInvalidoException;
import exceptions.GAPBusinessException;
import exceptions.ProfessorInvalidoException;
import exceptions.ProfessorNaoEncontradoException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author avsilva
 */
@Stateless
@Remote(ProfessorRepositoryRemote.class)
public class ProfessorRepository implements ProfessorRepositoryRemote {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Professor adicionar(Professor professor) throws GAPBusinessException {
        validarProfessor(professor, false);

        manager.persist(professor);
        return professor;
    }

    @Override
    public void remover(Professor professor) throws GAPBusinessException {
        validarProfessor(professor, true);
        manager.remove(manager.merge(professor));
    }

    @Override
    public Professor alterar(Professor professor) throws GAPBusinessException {
        validarProfessor(professor, true);
        manager.merge(professor);
        return professor;
    }

    @Override
    public List<Professor> findAll() throws GAPBusinessException {
        TypedQuery<Professor> query = this.manager.createNamedQuery(
                "Professor.findAll", Professor.class);
        
        List<Professor> professores = query.getResultList();

        return professores;
    }

    @Override
    public Professor findById(Long id) throws GAPBusinessException {
        Professor professor = this.manager.find(Professor.class, id);

        if (professor == null || professor.getId() == null || professor.getId() == 0l) {
            throw new ProfessorNaoEncontradoException();
        }
        return professor;
    }

    private void validarProfessor(Professor professor, boolean validaId) throws ProfessorInvalidoException {
        if (professor == null) {
            throw new ProfessorInvalidoException("Professor está nulo!");
        }
        else if(validaId && (professor.getId() == null || professor.getId() == 0l))
            throw new ProfessorInvalidoException("Professor está sem id!");
        else if (professor.getNome() == null || professor.getNome().equals("")) {
            throw new ProfessorInvalidoException("O nome do professor deve ser preenchido");
        } else if (professor.getDocumento() == null || professor.getDocumento().equals("")) {
            throw new ProfessorInvalidoException("O documento do professor deve ser preenchido");
        }
    }
}
