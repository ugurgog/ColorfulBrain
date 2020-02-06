package uren.com.colorfulbrains.interfaces;

public interface FileSaveCallback {
    void Saved(String realPath);
    void OnError(Exception e);
}
