package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;

import java.util.List;

public interface ManagerService {
    ManagerModel getManagerByIdManager(Long idManager);

    List<ManagerModel> getManagerList();

    List<ManagerModel> getNonAssignedManagerList();

    List<ManagerModel> getNonAssignedManagerList(ManagerModel manager);
}
