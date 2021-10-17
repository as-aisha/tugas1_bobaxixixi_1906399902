package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;

import java.util.List;

public interface StoreService {

    //Method untuk menambah Store
    void addStore(StoreModel store);

    //Method untuk meng-update Store
    String updateStore(StoreModel store);

    //Method untuk menghapus sebuah Store
    String deleteStore(StoreModel store);

    //Method untuk mendapatkan daftar Store yang telah tersimpan
    List<StoreModel> getStoreList();

    //Method untuk mendapatkan data sebuah Store berdasarkan store code
    StoreModel getStoreByStoreCode(String storeCode);

    //Method untuk mendapatkan data sebuah Store berdasarkan id Store
    StoreModel getStoreByIdStore(Long idStore);

    //Method untuk meng-generate store code
    String generateStoreCode(StoreModel store);

    //Method untuk meng-generate random string
    String randomString(int len);
}


