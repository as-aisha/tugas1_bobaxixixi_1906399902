package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;

import java.util.List;

public interface ToppingService {

    //Method untuk mendapatkan daftar Topping yang telah tersimpan
    List<ToppingModel> getToppingList();

    //Method untuk mendapatkan data sebuah Topping berdasarkan id topping
    ToppingModel getToppingByIdTopping(Long idTopping);

    ToppingModel getToppingByNamaTopping(String namaTopping);
}
