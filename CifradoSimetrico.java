

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CifradoSimetrico {
    private final static String PADDIG = "AES/ECB/PKCS5Padding"; 

    public static byte[] cifrar(SecretKey llave, String texto){ 
        byte[] textoCifrado ; 
        try {
            Cipher cifrador = Cipher.getInstance(PADDIG); 
            byte[] textoClaro = texto.getBytes();

            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            textoCifrado = cifrador.doFinal(textoClaro);
            return textoCifrado;
        } catch (Exception e) {
            System.out.println("Error al cifrar el texto: " + e.getMessage());
            return null;
        }
    } 

    public static byte[] decifrar(SecretKey llave, byte[] textoCifrado){ 
        byte[] textoClaro; 
        try {
            Cipher cifrador = Cipher.getInstance(PADDIG); 

            cifrador.init(Cipher.DECRYPT_MODE, llave);
            textoClaro = cifrador.doFinal(textoCifrado);
        } catch (Exception e) {
            System.out.println("Error al decifrar el texto: " + e.getMessage());
            return null;
        }
         return textoClaro;
    }
    } 
    

