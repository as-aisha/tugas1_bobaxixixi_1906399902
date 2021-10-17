package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "store")

public class StoreModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Range(min=0, max=20)
    private Long idStore;

    @NotNull
    @Size(max=255)
    @Column(nullable = false)
    private String namaStore;

    @NotNull
    @Size(max=255)
    @Column(nullable = false)
    private String alamatStore;

    @NotNull
    @Size(max=10)
    @Column(nullable = false, unique = true)
    private String storeCode;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeHour;

    //Relasi dengan ManagerModel
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_manager")
    private ManagerModel manager;

    //Relasi dengan StoreBobaTeaModel
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StoreBobaTeaModel> listStoreBobaTea;

}