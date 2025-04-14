package ParteA;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main2 {
    private final static String ALGORITMO = "AES";
    static CifradoSimetrico cifradoSimetrico = new CifradoSimetrico();
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el texto a cifrar: ");
            String texto = scanner.nextLine(); 
            System.out.println("\n El texto cifrado fue: " + texto); 
             byte[] textoClaro = texto.getBytes();
            System.out.println("\n texto claro es: "); 
            imprimir(textoClaro);
            //Creacion de la primera llave 
            KeyGenerator llave1 = KeyGenerator.getInstance(ALGORITMO); 
            //Creacion de la segunda llave
            KeyGenerator llave2 = KeyGenerator.getInstance(ALGORITMO); 
            //Ciframos el texto con la primera llave 
            SecretKey k1 = llave1.generateKey(); 
            byte[] tc1 = CifradoSimetrico.cifrar(k1, texto);
            System.out.println("\n El texto cifrado es: ");
            imprimir(tc1);
            //Ciframos el texto con la segunda llave 
            SecretKey k2 = llave2.generateKey();
            byte[] tc2 = CifradoSimetrico.cifrar(k2, texto);
            //System.out.println("\n El texto cifrado es: ");
            //Decicframos tc1 con k1 
            //byte[] td1 = CifradoSimetrico.decifrar(k1, tc1); 
           // System.out.println("\n El texto decifrado es: ");
            //imprimir(td1);
            //Deciframos tc1 con k2 
            byte[] td2 = CifradoSimetrico.decifrar(k2, tc1);
            System.out.println("\n El texto decifrado es: ");
            imprimir(td2);
        scanner.close();  
        } catch (Exception e) {
            // TODO: handle exception
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
