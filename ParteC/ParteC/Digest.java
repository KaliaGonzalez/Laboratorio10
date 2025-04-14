package ParteC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Digest {

    // Métodos
    public static byte[] getDigest(String algorithm, byte[] buffer) {
        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(buffer);
            return digest.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static void imprimirHexa(byte[] byteArray){
        String out = "";
        for (int i = 0; i < byteArray.length; i++){
            if ((byteArray[i & 0xff]) <= 0xf) {
                out += "0";
            }
            out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
        }
        System.out.println(out);
    }

    public static void verificar(byte[] digest1, byte[] digest2){
        if (digest1.length != digest2.length) {
            System.out.println("Los digests no son iguales");
            return;
        }
        for (int i = 0; i < digest1.length; i++){
            if (digest1[i] != digest2[i]) {
                System.out.println("Los digests no son iguales");
                return;
            }
        }
        System.out.println("Los digests son iguales");
    }

    public static byte[] getDigestFile(String algorithm, String fileName) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);

            FileInputStream in = new FileInputStream(fileName);
            byte [] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }       

        return md.digest();
    }
    
}
