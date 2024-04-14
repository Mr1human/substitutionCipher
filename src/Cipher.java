import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Cipher {
    private static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
    private char [] key = new char[alphabet.length()];
    private String result;
    private String text;
    private int index;
    private FileWriter fileWriter;

    public void encryption(String text_path) {
        result = ""; index = 0; text="";
        text = readFile(text_path);
        text = text.toLowerCase().replaceAll("[^" + alphabet + "]","");
        key = keyGeneration();

        for (int i = 0; i < text.length(); i++) {
            index = alphabet.indexOf(text.charAt(i));
            result+=key[index];
        }
        writeResult("encrypted.txt", result);
    }


    public void decryption(String encryptedPath, String keyPath){
        index = 0; result = ""; text = "";
        String keyDecryption ="";

        text = readFile(encryptedPath);
        keyDecryption = readKey(keyPath);

        for (int i = 0; i < text.length(); i++) {
            index = keyDecryption.indexOf(text.charAt(i));
            result+=alphabet.charAt(index);
        }
        writeResult("decrypted.txt", result);
    }

    private char[] keyGeneration(){
        char [] k = alphabet.toCharArray();
        Random random = new Random();

        for (int i = k.length-1; i>0 ; i--) {
            int j = random.nextInt(i);

            char tmp = k[i];
            k[i] = k[j];
            k[j] = tmp;
        }

        try{
            fileWriter = new FileWriter("key.txt", StandardCharsets.UTF_8);
            for (int i = 0; i < alphabet.length(); i++) {
                fileWriter.write(alphabet.charAt(i) +"-" + k[i] + "\n");
            }
            fileWriter.flush();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return k;
    }


    private String readKey(String keyPath){
        String readKey = "";
        String text_key = readFile(keyPath).toLowerCase();

        for (int i = 0; i < text_key.length(); i++) {
            if (text_key.charAt(i) == '-'){
                readKey += text_key.charAt(i+1);
            }
        }
        return readKey;
    }

    private void writeResult(String path, String result){
        try{
            fileWriter = new FileWriter(path, StandardCharsets.UTF_8);
            fileWriter.write(result);
            fileWriter.flush();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private String readFile(String path){
        String read = "";
        try{
            FileReader fileReader = new FileReader(path, StandardCharsets.UTF_8);
            int symbol;
            while ((symbol=fileReader.read())!=-1){
                read+=(char)symbol;
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return read;
    }
}
