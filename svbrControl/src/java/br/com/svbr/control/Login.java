/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.svbr.control;

import br.com.svbr.model.Usuario;


/**
 *
 * @author avsilva
 */
public interface Login {
    Usuario login(String usuario, String senha);
}
