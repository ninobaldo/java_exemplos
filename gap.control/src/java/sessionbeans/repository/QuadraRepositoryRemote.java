/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans.repository;

import entidades.Quadra;
import exceptions.GAPBusinessException;
import java.util.List;

/**
 *
 * @author avsilva
 */
public interface QuadraRepositoryRemote {

    Quadra adicionar(Quadra q) throws GAPBusinessException;

    Quadra alterar(Quadra q) throws GAPBusinessException;

    List<Quadra> findAll();

    Quadra findById(Long id) throws GAPBusinessException;

    void remover(Quadra q) throws GAPBusinessException;
}
