package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.*;
import apap.tugas.bobaxixixi.repository.BobaTeaDB;
import apap.tugas.bobaxixixi.repository.StoreBobaTeaDB;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService{
    @Autowired
    BobaTeaDB bobaTeaDB;

    @Autowired
    StoreBobaTeaDB storeBobaTeaDB;


    @Override
    public BobaTeaModel getBobaByIdBobaTea(Long idBobaTea) {
        Optional<BobaTeaModel> boba = bobaTeaDB.findByIdBobaTea(idBobaTea);
        if (boba.isPresent()) {
            return boba.get();
        }
        return null;
    }


    @Override
    public void addBoba(BobaTeaModel boba) {
        bobaTeaDB.save(boba);
        if (boba.getTopping() != null) {
            int hargaTotal = boba.getHargaBobaTea() + boba.getTopping().getHargaTopping();
            boba.setHargaBobaTea(hargaTotal);
        }


    }

    @Override
    public String updateBoba(BobaTeaModel boba) {
        if (!(boba.getListStoreBobaTea().isEmpty())) {
            for (StoreBobaTeaModel sb : boba.getListStoreBobaTea()) {
                if (sb.getStore().getOpenHour().isBefore(LocalTime.now()) && sb.getStore().getCloseHour().isAfter(LocalTime.now())) {
                    return "waktu-failed";
                }
            }
        }

        if (boba.getTopping().getIdTopping() != 0) {
            int hargaTotal = boba.getHargaBobaTea() + boba.getTopping().getHargaTopping();
            boba.setHargaBobaTea(hargaTotal);
        }
        bobaTeaDB.save(boba);
        return "update-success";
    }

    @Override
    public String deleteBoba(BobaTeaModel boba) {
        if (boba.getListStoreBobaTea().isEmpty()) {
            bobaTeaDB.delete(boba);
            return "delete-success";
        } else {
            return "delete-boba-failed";
        }
    }

    @Override
    public List<BobaTeaModel> getBobaTeaList() {    return bobaTeaDB.findAll();    }

    @Override
    public BobaTeaModel getBobaByNamaBoba(String namaVarianBobaTea) {
        Optional<BobaTeaModel> boba = bobaTeaDB.findByNamaVarianBobaTea(namaVarianBobaTea);
        if (boba.isPresent()) {
            return boba.get();
        }
        return null;
    }

    @Override
    public List<StoreBobaTeaModel> searchBobaByTopping(String namaBoba, String namaTopping) {

        List<StoreBobaTeaModel> listStoreBoba = storeBobaTeaDB.findAll();
        List<StoreBobaTeaModel> listAvailableStoreBoba = new ArrayList();

        LocalTime now = LocalTime.now();
        for (StoreBobaTeaModel sb : listStoreBoba) {
            //Kalau store buka
            if (now.isAfter(sb.getStore().getOpenHour()) && now.isBefore(sb.getStore().getCloseHour())) {
                //Kalau sesuai dengan query boba dan topping yang dicari
                System.out.println("kalau toko buka");
                if (sb.getBoba_tea().getNamaVarianBobaTea().equals(namaBoba) && sb.getBoba_tea().getTopping().getNamaTopping().equals(namaTopping)) {
                    System.out.println("masuk sini");
                    listAvailableStoreBoba.add(sb);
                }
            }
        }

        if (!(listAvailableStoreBoba.isEmpty())) {
            return listAvailableStoreBoba;
        }
        else {
            return null;
        }
    }


}
