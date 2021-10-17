package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;

import apap.tugas.bobaxixixi.service.ManagerService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;


    @GetMapping("/search")
    public String searchBoba(
            @RequestParam(value = "boba", required = false) String namaVarianBobaTea,
            @RequestParam(value = "topping", required = false) String namaTopping,
            Model model
    ){
        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
        List<ToppingModel> listTopping = toppingService.getToppingList();
        List<StoreBobaTeaModel> listSearchedBobaTopping = bobaTeaService.searchBobaByTopping(namaVarianBobaTea, namaTopping);

        model.addAttribute("listBoba", listBoba);
        model.addAttribute("listTopping", listTopping);
        model.addAttribute("listSearchedBobaTopping", listSearchedBobaTopping);
        return "search-boba";
    }

    
}
