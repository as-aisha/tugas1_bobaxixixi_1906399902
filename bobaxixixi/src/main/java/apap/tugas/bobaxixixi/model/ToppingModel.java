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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "topping")

public class ToppingModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Range(min=0, max=20)
    private Long idTopping;

    @NotNull
    @Size(max=255)
    @Column(nullable = false)
    private String namaTopping;

    @NotNull
    @Column(nullable = false)
    private Integer hargaTopping;

    //Relasi dengan BobaTeaModel
    @OneToMany(mappedBy = "topping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BobaTeaModel> listBobaTea;

}