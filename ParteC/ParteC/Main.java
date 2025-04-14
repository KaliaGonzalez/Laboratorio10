package ParteC;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try {
            // Leer texto
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Escriba un mensaje de texto: ");
            String texto = scanner.nextLine(); 
            System.out.println("\n Mensaje recibido: " + texto); 
            byte[] textoClaro = texto.getBytes();

            // Calcular Digest MD5
            byte[] digestMD5 = Digest.getDigest("MD5", textoClaro);
            System.out.println("\n El digest MD5 es: ");
            Digest.imprimirHexa(digestMD5);

            // Calcular Digest SHA-1
            byte[] digestSHA1 = Digest.getDigest("SHA-1", textoClaro);
            System.out.println("\n El digest SHA-1 es: ");
            Digest.imprimirHexa(digestSHA1);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
