package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDB storeDB;

    @Override
    public StoreModel getStoreByStoreCode(String storeCode) {
        Optional<StoreModel> store = storeDB.findByStoreCode(storeCode);
        if (store.isPresent()) {
            return store.get();
        }
        return null;
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore) {
        Optional<StoreModel> store = storeDB.findByIdStore(idStore);
        if (store.isPresent()) {
            return store.get();
        }
        return null;
    }

    @Override
    public void addStore(StoreModel store) {
        String generatedStoreCode = generateStoreCode(store);
        store.setStoreCode(generatedStoreCode);
        storeDB.save(store);
    }

    @Override
    public String updateStore(StoreModel store) {
        LocalTime now = LocalTime.now();
        if (now.isBefore(store.getOpenHour()) || now.isAfter(store.getCloseHour())) {
            Optional<StoreModel> sstoree = storeDB.findByIdStore(store.getIdStore());
            StoreModel oldStore = sstoree.get();

            if (!(oldStore.getNamaStore().equals(store.getNamaStore())) || !(oldStore.getOpenHour().equals(store.getOpenHour())) || !(oldStore.getOpenHour().equals(store.getOpenHour()))) {
                String generatedStoreCode = generateStoreCode(store);
                store.setStoreCode(generatedStoreCode);
                storeDB.save(store);
                return "update-success";
            } else {
                storeDB.save(store);
                return "update-success";
            }
        } else {
            return "waktu-failed";
        }
    }

    @Override
    public String deleteStore(StoreModel store) {
        LocalTime now = LocalTime.now();
        if (store.getListStoreBobaTea().isEmpty()) {
            if (now.isBefore(store.getOpenHour()) || now.isAfter(store.getCloseHour())) {
                storeDB.delete(store);
                return "delete-success";
            } else {
                return "waktu-failed";
            }
        } else {
            return "delete-store-failed";
        }
    }

    @Override
    public List<StoreModel> getStoreList() {    return storeDB.findAll();    }

    @Override
    public String generateStoreCode(StoreModel store) {
        String rawNameCode = store.getNamaStore().substring(0, 3);
        String nameCode = new StringBuilder(rawNameCode).reverse().toString().toUpperCase();

        int rawOpenHour = store.getOpenHour().getHour();
        String openHourCode;
        if (rawOpenHour < 10) {
            openHourCode = "0" + Integer.toString(rawOpenHour);
        } else {
            openHourCode = Integer.toString(rawOpenHour);
        }

        int rawCloseHour = Math.floorDiv(store.getCloseHour().getHour(), 10);
        String closeHourCode = Integer.toString(rawCloseHour);

        String generatedString = randomString(2);
        String tempStoreCode = "SC" + nameCode + openHourCode + closeHourCode + generatedString;

        while (getStoreByStoreCode(tempStoreCode) != null) {
            generatedString = randomString(2);
            tempStoreCode = "SC" + nameCode + openHourCode + closeHourCode + generatedString;
        }

        return tempStoreCode;
    }

    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();
    @Override
    public String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}

