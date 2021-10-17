package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "store_boba_tea")

public class StoreBobaTeaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Range(min=0, max=20)
    private Long idStoreBobaTea;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_store", referencedColumnName = "idStore", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StoreModel store;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_boba_tea", referencedColumnName = "idBobaTea", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BobaTeaModel boba_tea;

    @NotNull
    @Size(max=12)
    @Column(nullable = false, unique = true)
    private String productionCode;

}
