package pl.zgorzalek.web_spy.css;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = Css.TABLE_NAME)
public class Css {
    public static final String TABLE_NAME = "css";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @OneToMany
    @JoinColumn(name = "id_page")
    private List<Css> css = new ArrayList<>();
}
