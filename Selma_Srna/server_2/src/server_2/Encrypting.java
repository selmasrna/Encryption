package server_2;

import java.io.File;

public interface Encrypting {
    public void encrypt(String key, File in, File out);
    public void decrypt(String key, File in, File out);
}
