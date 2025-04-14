package ParteA;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {
    private final static String ALGORITMO = "AES";
    static CifradoSimetrico cifradoSimetrico = new CifradoSimetrico();
           public static void main(String[] args) {
            try{
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Ingrese el texto a cifrar: ");
            String texto = scanner.nextLine(); 
            System.out.println("\n El texto cifrado fue: " + texto); 
            byte[] textoClaro = texto.getBytes();
            System.out.println("\n texto claro es: "); 
            imprimir(textoClaro);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO); 
            SecretKey llave = keyGenerator.generateKey(); 
            long tiempoInicio = System.nanoTime();
            byte[] textoCifrado = CifradoSimetrico.cifrar(llave, texto);
            System.out.println("\n El texto cifrado es: ");
            imprimir(textoCifrado); 
            byte[] textoDes = CifradoSimetrico.decifrar(llave, textoCifrado); 
            long tiempoFin = System.nanoTime(); 
            System.out.println("\n El texto decifrado es: ");
            imprimir(textoClaro); 
            long tiempo = (tiempoFin - tiempoInicio);
            //Convertirmos el texto claro a String 
            String textoClaroString = new String(textoDes); 
            System.out.println("\n El texto claro es: " + textoClaroString);
            System.out.println("\n El tiempo  es: " + tiempo + " nanosegundos");
        scanner.close(); }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

} 
    public static void imprimir (byte[] contenido){ 
       int i = 0; 
       for(; i < contenido.length -1 ; i++){ 
           System.out.print(contenido[i] + " "); 
       } 
       System.out.print(contenido[i] + " ");
} 
    
}