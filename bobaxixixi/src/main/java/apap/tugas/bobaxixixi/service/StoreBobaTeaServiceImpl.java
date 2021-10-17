package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.BobaTeaDB;
import apap.tugas.bobaxixixi.repository.ManagerDB;
import apap.tugas.bobaxixixi.repository.StoreBobaTeaDB;
import apap.tugas.bobaxixixi.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreBobaTeaServiceImpl implements StoreBobaTeaService {
    @Autowired
    StoreBobaTeaDB storeBobaTeaDB;

    @Autowired
    BobaTeaDB bobaTeaDB;

    @Autowired
    StoreDB storeDB;

    @Override
    public void assignBobaToStore(StoreModel store, List<Long> listIdBobaTea) {
        List<StoreBobaTeaModel> storeBobaTeaModelList = new ArrayList<>();

        for (Long id : listIdBobaTea) {
            StoreBobaTeaModel sb = new StoreBobaTeaModel();
            Optional<BobaTeaModel> b = bobaTeaDB.findByIdBobaTea(id);
            BobaTeaModel boba = b.get();
            String productionCode = generateProductionCode(store, boba);
            sb.setStore(store);
            sb.setBoba_tea(boba);
            sb.setProductionCode(productionCode);
            storeBobaTeaModelList.add(sb);
        }
        store.setListStoreBobaTea(storeBobaTeaModelList);
    }

    @Override
    public void assignStoreToBoba(BobaTeaModel boba, List<Long> listIdStore) {
        List<StoreBobaTeaModel> storeBobaTeaModelList = new ArrayList<>();

        for (Long id : listIdStore) {
            StoreBobaTeaModel sb = new StoreBobaTeaModel();
            Optional<StoreModel> s = storeDB.findByIdStore(id);
            StoreModel store = s.get();
            String productionCode = generateProductionCode(store, boba);
            sb.setStore(store);
            sb.setBoba_tea(boba);
            sb.setProductionCode(productionCode);
            storeBobaTeaModelList.add(sb);
        }
        boba.setListStoreBobaTea(storeBobaTeaModelList);
    }

    @Override
    public String generateProductionCode(StoreModel store, BobaTeaModel boba) {
        String storeCode = String.format("%03d", store.getIdStore());
        String bobaCode = String.format("%03d", boba.getIdBobaTea());
        String toppingCode = (boba.getTopping().getIdTopping() == 0 ? "0" : "1");
        String tempProductionCode = "PC" + storeCode + toppingCode + bobaCode;
        return tempProductionCode;
    }

    @Override
    public void resetStoreBobaTeaList(StoreModel store) {
        List<StoreBobaTeaModel> listStoreBobaTea = storeBobaTeaDB.findAll();

        for (StoreBobaTeaModel sb : listStoreBobaTea) {
            if (sb.getStore().getIdStore() == (store.getIdStore())) {
                storeBobaTeaDB.delete(sb);
            }
        }
    }

    @Override
    public void resetStoreBobaTeaList(BobaTeaModel boba) {
        List<StoreBobaTeaModel> listStoreBobaTea = storeBobaTeaDB.findAll();

        for (StoreBobaTeaModel sb : listStoreBobaTea) {
            if (sb.getBoba_tea().getIdBobaTea() == (boba.getIdBobaTea())) {
                storeBobaTeaDB.delete(sb);
            }
        }
    }
}
