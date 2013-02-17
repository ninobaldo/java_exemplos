/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author avsilva
 */
public class HashFactory {

    /**
     * Transforma bytes em string
     *
     * @param bytes hash gerado
     * @return string dos bytes
     */
    private static String stringHexa(byte[] bytes) {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    public static String gerarHash(String frase, AlgoritmoHash algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo.toString());
            md.update(frase.getBytes());
            return stringHexa(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
 * Algoritmos de Hash
 * @author Álvaro
 * @see http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#MessageDigest
 */
    public enum AlgoritmoHash {

        MD5 {
            @Override
            public String toString(){
                return "MD5";
            }
        },
        SHA1{
            @Override
            public String toString(){
                return "SHA-1";
            } 
        },
        SHA256{
            @Override
            public String toString(){
                return "SHA-256";
            }
        },
        
        SHA384{
            @Override
            public String toString(){
                return "SHA-384";
            }
        },
        SHA512{
            @Override
            public String toString(){
                return "SHA-512";
            }
        }
    }
}
