package pl.zgorzalek.web_spy.css;

import lombok.Data;
import pl.zgorzalek.web_spy.record.Record;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String name;

    @OneToMany
    @JoinColumn(name = "id_css")
    private List<Record> records = new ArrayList<>();
}
