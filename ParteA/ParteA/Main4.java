package ParteA;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.SecretKey;

public class Main4 {
    static CifradoSimetrico cifradoSimetrico = new CifradoSimetrico(); 
    public static void main(String[] args) {
        try {
            //Recuperar la llave desde el archivo 
            FileInputStream archivoLlave = new FileInputStream("llave1.key");
            ObjectInputStream ois = new ObjectInputStream(archivoLlave);
            SecretKey llave = (SecretKey) ois.readObject();
            ois.close(); 
            //Recuperar el texto cifrado desde el archivo 
            FileInputStream archivoTexto = new FileInputStream("textoCifrado.txt");
            ObjectInputStream oisTexto = new ObjectInputStream(archivoTexto);
            byte[] textoCifrado = (byte[]) oisTexto.readObject();
            oisTexto.close(); 
            System.out.println("\n El texto cifrado recuperado: ");
            imprimir(textoCifrado);
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
