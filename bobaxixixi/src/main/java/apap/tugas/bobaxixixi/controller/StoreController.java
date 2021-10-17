package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ManagerService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StoreController {

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private BobaTeaService bobaTeaService;

    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    //Routing URL yang diinginkan
    @GetMapping("/store/add")
    public String addStoreForm(Model model) {
        //Add variabel store ke "store" untuk dirender ke dalam thymeleaf
        model.addAttribute("store", new StoreModel());
        //Add variabel listManager ke "listManager" untuk dirender ke dalam thymeleaf
        model.addAttribute("listManager", managerService.getNonAssignedManagerList());
        //Return view template yang digunakan
        return "form-add-store";
    }

    @PostMapping("/store/add")
    public String addStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        storeService.addStore(store);
        model.addAttribute("namaStore", store.getNamaStore());
        model.addAttribute("storeCode", store.getStoreCode());
        Long idManager = store.getManager().getIdManager();
        ManagerModel manager = managerService.getManagerByIdManager(idManager);
        return "add-store";
    }

    @GetMapping("/store")
    public String listStore(Model model) {
        List<StoreModel> listStore = storeService.getStoreList();
        model.addAttribute("listStore", listStore);
        return "viewall-store";
    }

    @GetMapping("/store/{idStore}")
    public String detailStore(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<StoreBobaTeaModel> listStoreBobaTea = store.getListStoreBobaTea();
        model.addAttribute("store", store);
        model.addAttribute("listStoreBobaTea", listStoreBobaTea);
        return "view-store";
    }

    @GetMapping("/store/update/{idStore}")
    public String updateStoreForm(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        model.addAttribute("store", store);
        model.addAttribute("listManager", managerService.getNonAssignedManagerList(store.getManager()));
        return "form-update-store";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        String keyword = storeService.updateStore(store);
        StoreModel updatedStore = storeService.getStoreByIdStore(store.getIdStore());
        model.addAttribute("namaStore", updatedStore.getNamaStore());
        model.addAttribute("storeCode", updatedStore.getStoreCode());

        if (keyword.equals("waktu-failed")) {
            String action = "update";
            model.addAttribute("failedAction", action);
            return "failed-action-time-store";
        } else {
            return "update-store";
        }
    }

    @GetMapping("/store/delete/{idStore}")
    public String deleteStore(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        model.addAttribute("namaStore", store.getNamaStore());
        String keyword = storeService.deleteStore(store);

        if (keyword.equals("waktu-failed")) {
            String action = "delete";
            model.addAttribute("failedAction", action);
            return "failed-action-time-store";
        } else if (keyword.equals("delete-store-failed")) {
            return "failed-delete-store";
        } else {
            return "delete-store";
        }
    }

    @GetMapping("/store/{idStore}/assign-boba")
    public String assignBobaForm(
            @PathVariable Long idStore,
            Model model
    ) {

        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
        storeBobaTeaService.resetStoreBobaTeaList(store);
        model.addAttribute("store", store);
        model.addAttribute("listBoba", listBoba);

        return "form-assign-boba";
    }

    @PostMapping(value = "/store/{idStore}/assign-boba")
    public String assignBobaSubmit (
            @PathVariable(value = "idStore") Long idStore,
            @RequestParam("bobaChecked") List<Long> idBobas,
            Model model
    ) {
        ArrayList<Long> selectedBoba = new ArrayList<>();
        if (idBobas != null) {
            for (Long idBoba : idBobas) {
                selectedBoba.add(idBoba);
            }
        }

        StoreModel store = storeService.getStoreByIdStore(idStore);
        storeBobaTeaService.assignBobaToStore(store, selectedBoba);
        model.addAttribute("store", store);
        model.addAttribute("namaStore", store.getNamaStore());
        model.addAttribute("listStoreBoba", store.getListStoreBobaTea());
        return "assign-boba";
    }
}
