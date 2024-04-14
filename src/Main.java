public class Main {
    public static void main(String[] args) {
        Cipher cipher = new Cipher();
        cipher.encryption("text.txt");
        cipher.decryption("encrypted.txt", "key.txt");
    }
}