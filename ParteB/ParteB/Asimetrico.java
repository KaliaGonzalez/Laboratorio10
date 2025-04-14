package ParteB;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.Cipher;


public class Asimetrico {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) {
        try {
            // Leer texto del usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escriba un mensaje de texto:");
            String mensaje = scanner.nextLine();

            System.out.println("Input en texto plano: " + mensaje);

            // Convertir mensaje a byte[]
            byte[] textoClaro = mensaje.getBytes();
            System.out.print("Input en bytes[]: ");
            imprimir(textoClaro);

            // Generar par de llaves RSA de 1024 bits
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            PublicKey llavePublica = keyPair.getPublic();
            PrivateKey llavePrivada = keyPair.getPrivate();

            // Cifrado con medición de tiempo
            long tiempoInicial = System.nanoTime();
            byte[] textoCifrado = cifrar(llavePublica, ALGORITMO, mensaje);
            long tiempoFinal = System.nanoTime();
            long tiempoCifrado = tiempoFinal - tiempoInicial;
            System.out.print("Texto cifrado en " + ALGORITMO + " con llaves de 1024 bits en byte[]: ");
            imprimir(textoCifrado);
            System.out.println("Tiempo de cifrado (ns): " + tiempoCifrado);

            // Descifrado con medición de tiempo
            tiempoInicial = System.nanoTime();
            byte[] textoDescifrado = descifrar(llavePrivada, ALGORITMO, textoCifrado);
            tiempoFinal = System.nanoTime();
            long tiempoDescifrado = tiempoFinal - tiempoInicial;
            System.out.print("Input descifrado en byte[]: ");
            imprimir(textoDescifrado);
            System.out.println("Tiempo de descifrado (ns): " + tiempoDescifrado);

            // Convertir byte[] descifrado a String
            String textoPlano = new String(textoDescifrado);
            System.out.println("Input descifrado convertido a texto plano: " + textoPlano);

        } catch (Exception e) {
            System.out.println("Excepcion en main: " + e.getMessage());
        }
    }

    public static void imprimir(byte[] contenido) {
        for (int i = 0; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println();
    }

    public static byte[] cifrar(Key llave, String algoritmo, String texto) {
        byte[] textoCifrado;

        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            byte[] textoClaro = texto.getBytes();

            cifrador.init(Cipher.ENCRYPT_MODE, llave);
            textoCifrado = cifrador.doFinal(textoClaro);

            return textoCifrado;
        } catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
            return null;
        }
    }

    public static byte[] descifrar(Key llave, String algoritmo, byte[] texto) {
        byte[] textoClaro;
    
        try {
            Cipher cifrador = Cipher.getInstance(algoritmo);
            cifrador.init(Cipher.DECRYPT_MODE, llave);
            textoClaro = cifrador.doFinal(texto);
        } catch (Exception e) {
            System.out.println("Excepcion: " + e.getMessage());
            return null;
        }
    
        return textoClaro;
    }

}
