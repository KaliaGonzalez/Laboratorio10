import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class Main3 {
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
            //Ciframos el texto con la primera llave 
            SecretKey k1 = llave1.generateKey(); 
            //Guardamos la llave secreta en un archivo 
            FileOutputStream archivo = new FileOutputStream("llave1.key"); 
            ObjectOutputStream oos = new ObjectOutputStream(archivo);
            oos.writeObject(k1); 
            oos.close();
            byte[] tc1 = CifradoSimetrico.cifrar(k1, texto);
            //Almacenamos el texto cifrado en un archivo 
            FileOutputStream archivo2 = new FileOutputStream("textoCifrado.txt"); 
            ObjectOutputStream oos2 = new ObjectOutputStream(archivo2); 
            oos2.writeObject(tc1);
            oos2.close();
            System.out.println("\n El texto cifrado es: ");
            imprimir(tc1);
            byte[] td2 = CifradoSimetrico.decifrar(k1, tc1);
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
