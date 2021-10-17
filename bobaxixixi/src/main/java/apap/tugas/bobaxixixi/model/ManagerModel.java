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
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "manager")

public class ManagerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Range(min=0, max=20)
    private Long idManager;

    @NotNull
    @Size(max=255)
    @Column(nullable = false)
    private String namaManager;

    @NotNull
    @Column(nullable = false)
    private Integer gender;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    //Relasi dengan StoreModel
    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private StoreModel store;
}
