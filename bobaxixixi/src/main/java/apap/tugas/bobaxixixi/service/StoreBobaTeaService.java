package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;

import java.util.List;

public interface StoreBobaTeaService {

    void assignBobaToStore(StoreModel store, List<Long> listIdBobaTea);

    void assignStoreToBoba(BobaTeaModel boba, List<Long> listIdStore);

    String generateProductionCode(StoreModel store, BobaTeaModel boba);

    void resetStoreBobaTeaList(StoreModel store);

    void resetStoreBobaTeaList(BobaTeaModel boba);
}
