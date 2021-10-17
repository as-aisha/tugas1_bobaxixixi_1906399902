package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BobaTeaService {

    //Method untuk menambah Boba Tea
    void addBoba(BobaTeaModel boba);

    //Method untuk meng-update Boba Tea
    String updateBoba(BobaTeaModel boba);

    //Method untuk menghapus sebuah Boba Tea
    String deleteBoba(BobaTeaModel boba);

    //Method untuk mendapatkan daftar Boba Tea yang telah tersimpan
    List<BobaTeaModel> getBobaTeaList();

    //Method untuk mendapatkan daftar pencarian Boba Tea berdasarkan varian boba dan topping
    List<StoreBobaTeaModel> searchBobaByTopping(String namaVarianBobaTea, String namaTopping);

    //Method untuk mendapatkan data sebuah Boba Tea berdasarkan id Boba Tea
    BobaTeaModel getBobaByIdBobaTea(Long idBobaTea);

    BobaTeaModel getBobaByNamaBoba(String namaVarianBobaTea);

//    //Method untuk meng-generate store code
//    String generateStoreCode(StoreModel store);
//
//    //Method untuk meng-generate random string
//    String randomString(int len);
}
