package ParteB;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class Main4 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) {
        try {
            // Recuperar la llave pública desde el archivo
            PublicKey llavePublica = recuperarLlave("llavePublica.key");

            // Recuperar la llave privada desde el archivo
            PrivateKey llavePrivada = recuperarLlavePrivada("llavePrivada.key");

            // Recuperar el mensaje cifrado desde el archivo
            byte[] textoCifrado = recuperarTextoCifrado("mensajeCifrado.obj");

            // Descifrar el mensaje utilizando la llave privada
            String mensajeDescifrado = descifrar(llavePrivada, ALGORITMO, textoCifrado);
            System.out.println("Mensaje descifrado: " + mensajeDescifrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar la llave (pública o privada)
    public static <T> T recuperarLlave(String nombreArchivo) throws IOException, ClassNotFoundException {
        FileInputStream archivo = new FileInputStream(nombreArchivo);
        ObjectInputStream ois = new ObjectInputStream(archivo);
        T llave = (T) ois.readObject();
        ois.close();
        return llave;
    }

    // Método específico para recuperar la llave privada
    public static PrivateKey recuperarLlavePrivada(String nombreArchivo) throws Exception {
        return (PrivateKey) recuperarLlave(nombreArchivo);
    }

    // Método para recuperar el texto cifrado desde el archivo
    public static byte[] recuperarTextoCifrado(String nombreArchivo) throws IOException, ClassNotFoundException {
        FileInputStream archivo = new FileInputStream(nombreArchivo);
        ObjectInputStream ois = new ObjectInputStream(archivo);
        byte[] textoCifrado = (byte[]) ois.readObject();
        ois.close();
        return textoCifrado;
    }

    // Método para descifrar el texto con la llave privada
    public static String descifrar(PrivateKey llavePrivada, String algoritmo, byte[] textoCifrado) {
        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, llavePrivada);
            byte[] textoDescifrado = cifrador.doFinal(textoCifrado);
            return new String(textoDescifrado);
        } catch (Exception e) {
            System.out.println("Excepción durante el descifrado: " + e.getMessage());
            return null;
        }
    }
}
