package pl.zgorzalek.web_spy.css;

import lombok.Data;
import pl.zgorzalek.web_spy.page.Page;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @ManyToOne
    private Page page;
}
