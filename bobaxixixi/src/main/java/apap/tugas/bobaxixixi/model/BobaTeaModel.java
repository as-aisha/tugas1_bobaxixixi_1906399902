package apap.tugas.bobaxixixi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "boba_tea")

public class BobaTeaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Range(min=0, max=20)
    private Long idBobaTea;

    @NotNull
    @Size(max=255)
    @Column(nullable = false)
    private String namaVarianBobaTea;

    @NotNull
    @Column(nullable = false)
    private Integer hargaBobaTea;

    @NotNull
    @Column(nullable = false)
    private Integer size;

    @NotNull
    @Column(nullable = false)
    private Integer iceLevel;

    @NotNull
    @Column(nullable = false)
    private Integer sugarLevel;

    //Relasi dengan ToppingModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_topping", referencedColumnName = "idTopping", nullable = false)
    private ToppingModel topping;

    //Relasi dengan StoreBobaTeaModel
    @OneToMany(mappedBy = "boba_tea")
    private List<StoreBobaTeaModel> listStoreBobaTea;

}
