package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreBobaTeaDB extends JpaRepository<StoreBobaTeaModel, Long> {
    Optional<StoreBobaTeaModel> findByIdStoreBobaTea(Long idStoreBobaTea);
}
