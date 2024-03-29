package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.repository.ManagerDB;
import apap.tugas.bobaxixixi.repository.ToppingDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService {
    @Autowired
    ToppingDB toppingDB;

    @Override
    public List<ToppingModel> getToppingList() {    return toppingDB.findAll();    }

    @Override
    public ToppingModel getToppingByIdTopping(Long idTopping) {
        Optional<ToppingModel> topping = toppingDB.findByIdTopping(idTopping);
        if (topping.isPresent()) {
            return topping.get();
        }
        return null;
    }

    @Override
    public ToppingModel getToppingByNamaTopping (String namaTopping) {
        Optional<ToppingModel> topping = toppingDB.findByNamaTopping(namaTopping);
        if (topping.isPresent()) {
            return topping.get();
        }
        return null;
    }
}
