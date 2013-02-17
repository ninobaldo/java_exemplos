/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Professor;
import exceptions.GAPBusinessException;
import exceptions.ProfessorInvalidoException;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface ProfessorRepositoryRemote {

    Professor adicionar(Professor professor) throws GAPBusinessException;

    Professor alterar(Professor professor) throws GAPBusinessException;

    List<Professor> findAll() throws GAPBusinessException;

    Professor findById(Long id) throws GAPBusinessException;

    void remover(Professor professor) throws GAPBusinessException;
}
