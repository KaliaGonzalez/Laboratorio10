package ParteB;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class Main3 {

    private final static String ALGORITMO = "RSA";

    public static void main(String[] args) {
        try {
            // Generar par de llaves
            KeyPairGenerator generador = KeyPairGenerator.getInstance(ALGORITMO);
            generador.initialize(1024);
            KeyPair parLlaves = generador.generateKeyPair();
            PublicKey llavePublica = parLlaves.getPublic();
            PrivateKey llavePrivada = parLlaves.getPrivate();

            // Guardar llaves en archivos
            guardarObjeto("llavePublica.key", llavePublica);
            guardarObjeto("llavePrivada.key", llavePrivada);

            // Leer mensaje desde consola
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Escriba un mensaje de texto: ");
            String mensaje = br.readLine();
            System.out.println("Input en texto plano: " + mensaje);

            // Convertir a bytes
            byte[] textoClaro = mensaje.getBytes();
            System.out.print("Input en bytes[]: ");
            imprimir(textoClaro);

            // Cifrar con la llave pública
            byte[] textoCifrado = cifrar(llavePublica, ALGORITMO, mensaje);
            System.out.print("Texto cifrado en RSA en byte[]: ");
            imprimir(textoCifrado);

            // Guardar texto cifrado en archivo
            guardarObjeto("mensajeCifrado.obj", textoCifrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimir(byte[] contenido) {
        for (int i = 0; i < contenido.length; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println();
    }

    public static void guardarObjeto(String nombreArchivo, Object objeto) throws IOException {
        FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        ObjectOutputStream oos = new ObjectOutputStream(archivo);
        oos.writeObject(objeto);
        oos.close();
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
}
