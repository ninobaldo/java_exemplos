/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptadores;

import java.util.Calendar;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author avsilva
 */
public class LoggingInterceptor implements InterceptorInterface {

    @AroundInvoke
    @Override
    public Object interceptador(InvocationContext ic) throws Exception {

        System.out.printf("%tc log antes do metodo %S", Calendar.getInstance(), ic.getMethod().getName());
        Object retornoDoMetodoDeNegocio = ic.proceed();
        System.out.printf("%tc log depois do metodo %S", Calendar.getInstance(), ic.getMethod().getName());
        
        return retornoDoMetodoDeNegocio;
    }
}
