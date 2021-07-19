package pl.zgorzalek.web_spy.record;

import lombok.Data;
import pl.zgorzalek.web_spy.css.Css;


import javax.persistence.*;

@Entity
@Data
@Table(name = Record.TABLE_NAME)
public class Record {
    public static final String TABLE_NAME = "records";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    private Css css;
}
