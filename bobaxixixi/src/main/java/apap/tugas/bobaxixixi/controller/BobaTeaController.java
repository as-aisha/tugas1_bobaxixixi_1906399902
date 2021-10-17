package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;

import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BobaTeaController {

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Autowired
    private ToppingService toppingService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    @GetMapping("/boba/add")
    public String addBobaForm(Model model) {
        model.addAttribute("boba", new BobaTeaModel());
        model.addAttribute("listTopping", toppingService.getToppingList());
        return "form-add-boba";
    }

    @PostMapping("/boba/add")
    public String addBobaSubmit(
            @ModelAttribute BobaTeaModel boba,
            Model model
    ) {
        bobaTeaService.addBoba(boba);
        model.addAttribute("namaBoba", boba.getNamaVarianBobaTea());
        return "add-boba";
    }

    @GetMapping("/boba")
    public String listBoba(Model model) {
        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
        model.addAttribute("listBoba", listBoba);
        return "viewall-boba";
    }

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateBobaForm(
            @PathVariable Long idBobaTea,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaByIdBobaTea(idBobaTea);
        model.addAttribute("boba", boba);
        model.addAttribute("listTopping", toppingService.getToppingList());
        return "form-update-boba";
    }

    @PostMapping("/boba/update")
    public String updateBobaSubmit(
            @ModelAttribute BobaTeaModel boba,
            Model model
    ) {
        String keyword = bobaTeaService.updateBoba(boba);
        model.addAttribute("namaBoba", boba.getNamaVarianBobaTea());

        if (keyword.equals("waktu-failed")) {
            String action = "update";
            model.addAttribute("failedAction", action);
            return "failed-update-boba";
        } else {
            return "update-boba";
        }
    }

    @GetMapping("/boba/delete/{idBobaTea}")
    public String deleteBoba(
            @PathVariable Long idBobaTea,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaByIdBobaTea(idBobaTea);
        model.addAttribute("namaBoba", boba.getNamaVarianBobaTea());
        String keyword = bobaTeaService.deleteBoba(boba);

        if (keyword.equals("delete-boba-failed")) {
            return "failed-delete-boba";
        } else {
            return "delete-boba";
        }
    }

    @GetMapping("/boba/{idBobaTea}/assign-store")
    public String assignStoreForm(
            @PathVariable Long idBobaTea,
            Model model
    ) {

        BobaTeaModel boba = bobaTeaService.getBobaByIdBobaTea(idBobaTea);
        List<StoreModel> listStore = storeService.getStoreList();
        storeBobaTeaService.resetStoreBobaTeaList(boba);
        model.addAttribute("boba", boba);
        model.addAttribute("namaBoba", boba.getNamaVarianBobaTea());
        model.addAttribute("listStore", listStore);

        return "form-assign-store";
    }

    @PostMapping(value = "/boba/{idBobaTea}/assign-store")
    public String assignStoreSubmit (
            @PathVariable(value = "idBobaTea") Long idBobaTea,
            @RequestParam("storeChecked") List<Long> idStores,
            Model model
    ) {
        ArrayList<Long> selectedStore = new ArrayList<>();
        if (idStores != null) {
            for (Long idStore : idStores) {
                selectedStore.add(idStore);
            }
        }

        BobaTeaModel boba = bobaTeaService.getBobaByIdBobaTea(idBobaTea);
        storeBobaTeaService.assignStoreToBoba(boba, selectedStore);
        model.addAttribute("boba", boba);
        model.addAttribute("namaBoba", boba.getNamaVarianBobaTea());
        model.addAttribute("listStoreBoba", boba.getListStoreBobaTea());
        return "assign-store";
    }
}
