/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptadores;

import javax.interceptor.InvocationContext;

/**
 *
 * @author avsilva
 */
public interface InterceptorInterface {

    Object interceptador(InvocationContext ic) throws Exception;
}
