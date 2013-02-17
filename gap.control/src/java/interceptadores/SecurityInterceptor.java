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
public class SecurityInterceptor implements InterceptorInterface {

    @AroundInvoke
    @Override
    public Object interceptador(InvocationContext ic) throws Exception {
        System.out.printf("%tc", Calendar.getInstance());
        System.out.printf(
                "Validando a segurança do %S com os parametros:", 
                ic.getMethod().getName());
        
//        for (Object object : ic.getParameters()) {
//            System.out.printf("%S", object);
//        }

        Object retorno = ic.proceed();

        return retorno;
    }
}