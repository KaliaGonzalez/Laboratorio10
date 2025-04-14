package ParteC;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args){

        try {
            // El archivo de prueba es T10_SuarezAdrian.pdf
            // Leer archivo
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Nombre archivo: ");
            String fileName = scanner.nextLine();

            // Digest MD5
            byte[] digestMD5 = Digest.getDigestFile("MD5", fileName);
            System.out.println("\n El digest MD5 es: ");
            Digest.imprimirHexa(digestMD5);

            // Diggest SHA-1
            byte[] digestSHA1 = Digest.getDigestFile("SHA-1", fileName);
            System.out.println("\n El digest SHA-1 es: ");
            Digest.imprimirHexa(digestSHA1);
            
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
