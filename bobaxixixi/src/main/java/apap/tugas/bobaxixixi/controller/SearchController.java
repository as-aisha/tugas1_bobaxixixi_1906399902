//package apap.tugas.bobaxixixi.controller;
//
//import apap.tugas.bobaxixixi.model.BobaTeaModel;
//import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
//import apap.tugas.bobaxixixi.model.ToppingModel;
//import apap.tugas.bobaxixixi.service.BobaTeaService;
//
//import apap.tugas.bobaxixixi.service.ManagerService;
//import apap.tugas.bobaxixixi.service.ToppingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class SearchController {
//
//    @Qualifier("bobaTeaServiceImpl")
//    @Autowired
//    private BobaTeaService bobaTeaService;
//
//    @Qualifier("toppingServiceImpl")
//    @Autowired
//    private ToppingService toppingService;
//
//    @Qualifier("managerServiceImpl")
//    @Autowired
//    private ManagerService managerService;
//
//    @GetMapping("/search")
//    public String searchBobaPage(
//            Model model
//    ){
//        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
//        List<ToppingModel> listTopping = toppingService.getToppingList();
//        model.addAttribute("listBobaExist", listBoba);
//        model.addAttribute("lisToppingExist", listTopping);
//        return "search-boba";
//    }
//
//    @GetMapping(value = "/search", params = {"search"})
//    public String searchBobaResult(
//            @ModelAttribute(value = "boba") BobaTeaModel boba,
//            @ModelAttribute(value = "topping") ToppingModel topping,
//            Model model
//    ){
////        System.out.println("CEK PASIEN");
////        System.out.println(vaksin.getJenisVaksin());
////        System.out.println(faskes.getNamaFaskes());
//
//        List<StoreBobaTeaModel> listAvailableStoreBoba = bobaTeaService.getListAvailableStoreBoba(boba, topping);
//
//        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
//        List<ToppingModel> listTopping = toppingService.getToppingList();
//        model.addAttribute("listBobaExist", listBoba);
//        model.addAttribute("listToppingExist", listTopping);
//        model.addAttribute("listAvailableStoreBoba", listAvailableStoreBoba);
//        return "search-boba";
//    }
//}
