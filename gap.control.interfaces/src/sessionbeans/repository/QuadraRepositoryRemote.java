/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Quadra;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface QuadraRepositoryRemote {

    void adicionar(Quadra q);

    void alterar(Quadra q);

    List<Quadra> findAll();

    Quadra findById(Long id);

    void remover(Quadra q);
    
}
