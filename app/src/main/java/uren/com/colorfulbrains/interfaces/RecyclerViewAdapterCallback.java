package uren.com.colorfulbrains.interfaces;


public interface RecyclerViewAdapterCallback {
    void OnRemoved();
    void OnInserted();
    void OnChanged(Object object);
}
