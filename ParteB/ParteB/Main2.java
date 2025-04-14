package ParteB;

import java.security.*;
import java.util.Scanner;

public class Main2 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Escriba un mensaje de texto:");
            String mensaje = sc.nextLine();
            System.out.println("Input en texto plano: " + mensaje);

            // Convertir a byte[]
            byte[] textoClaro = mensaje.getBytes();
            System.out.print("Input en bytes[]: ");
            imprimir(textoClaro);

            // Generar llaves
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            PrivateKey llavePrivada = keyPair.getPrivate();
            PublicKey llavePublica = keyPair.getPublic();

            // Cifrar con llave privada
            long tiempoInicialCifrado = System.nanoTime();
            byte[] textoCifrado = Asimetrico.cifrar(llavePrivada, ALGORITMO, mensaje);
            long tiempoFinalCifrado = System.nanoTime();
            long tiempoCifrado = tiempoFinalCifrado - tiempoInicialCifrado;

            System.out.print("Texto cifrado en byte[]: ");
            imprimir(textoCifrado);
            System.out.println("Tiempo de cifrado (ns): " + tiempoCifrado);

            // Descifrar con llave pública
            long tiempoInicialDescifrado = System.nanoTime();
            byte[] textoDescifrado = Asimetrico.descifrar(llavePublica, ALGORITMO, textoCifrado);
            long tiempoFinalDescifrado = System.nanoTime();
            long tiempoDescifrado = tiempoFinalDescifrado - tiempoInicialDescifrado;

            System.out.print("Input descifrado en byte[]: ");
            imprimir(textoDescifrado);
            System.out.println("Tiempo de descifrado (ns): " + tiempoDescifrado);

            // Convertir a String
            String mensajeDescifrado = new String(textoDescifrado);
            System.out.println("Input descifrado convertido a texto plano: " + mensajeDescifrado);

        } catch (Exception e) {
            System.out.println("Error en Main2: " + e.getMessage());
        }
    }

    public static void imprimir(byte[] contenido) {
        for (int i = 0; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println();
    }
}
