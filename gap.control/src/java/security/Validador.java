/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author avsilva
 */
public class Validador {

    
    // retirar conteúdo bom!
    
    /**
     * isEmailValid: Validate email address using Java reg ex. This method
     * checks if the input string is a valid email address.
     *
     * @param email String. Email address to validate
     * @return boolean: true if email address is valid, false otherwise.
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        /*
         * O e-mail deve ser formado por 2 blocos distintos divididos por uma @:
         * 1º bloco: nome de usuário do email, sendo que este não pode conter 
         * caracteres especiais, ou seja, apenas números e letras e .
         * 2º bloco: nome do dominio, segue a mesma regra do primeiro bloco, 
         * contudo nesse caso o nome tem que ter pelo menos um .
         */
//    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        
        CharSequence inputStr = email;
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
