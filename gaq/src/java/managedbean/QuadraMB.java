/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entidades.Quadra;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import sessionbeans.QuadraRepository;

/**
 *
 * @author avsilva
 */
@ManagedBean
public class QuadraMB {

    @EJB
    private QuadraRepository quadraRepository;
    private Quadra quadra = new Quadra();
    private List<Quadra> quadras;

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public QuadraRepository getQuadraRepository() {
        return quadraRepository;
    }

    public void setQuadraRepository(QuadraRepository quadraRepository) {
        this.quadraRepository = quadraRepository;
    }

    public List<Quadra> getQuadras() {

        if (this.quadras == null) {
            this.quadras = this.quadraRepository.findAll();
        }
        return quadras;
    }

    public void setQuadras(List<Quadra> quadras) {
        this.quadras = quadras;
    }

    public void save() {
        if (quadra.getId() == null) {
            this.quadraRepository.adicionar(this.getQuadra());
        } else {
            this.quadraRepository.alterar(this.getQuadra());
        }
        this.quadra = new Quadra();
        this.quadras = null;
    }

    public void delete(Quadra c) {
        this.quadraRepository.remover(c);
        this.quadras = null;
    }

    public void prepareEdit(Long id) {
        this.quadra = this.quadraRepository.findById(id);
    }
}
