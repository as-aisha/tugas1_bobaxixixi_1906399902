package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.repository.ManagerDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    ManagerDB managerDB;

    @Override
    public ManagerModel getManagerByIdManager(Long idManager) {
        Optional<ManagerModel> manager = managerDB.findByIdManager(idManager);
        if (manager.isPresent()) {
            return manager.get();
        }
        return null;
    }

    @Override
    public List<ManagerModel> getManagerList() {    return managerDB.findAll();    }

    @Override
    public List<ManagerModel> getNonAssignedManagerList() {
        List<ManagerModel> lisManager =  managerDB.findAll();
        List<ManagerModel> listNonAssignedManager = new ArrayList<>();
        for (ManagerModel m : lisManager) {
            if (m.getStore() == null) {
                listNonAssignedManager.add(m);
            }
        }
        return listNonAssignedManager;
    }

    @Override
    public List<ManagerModel> getNonAssignedManagerList(ManagerModel manager) {
        List<ManagerModel> listNonAssignedManager = getNonAssignedManagerList();
        listNonAssignedManager.add(manager);
        return listNonAssignedManager;
    }
}
