/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Professor;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface ProfessorRepositoryRemote  {

    void adicionar(Professor professor);

    void alterar(Professor professor);

    List<Professor> findAll();

    Professor findById(Long id);

    void remover(Professor professor);
    
}
