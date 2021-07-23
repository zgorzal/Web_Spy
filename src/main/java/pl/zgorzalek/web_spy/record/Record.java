package pl.zgorzalek.web_spy.record;

import lombok.Data;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.value.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = Record.TABLE_NAME)
public class Record {
    public static final String TABLE_NAME = "records";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int downloadId;

    private LocalDateTime dateAdded;

    @ManyToOne
    private Css css;

    @OneToMany(mappedBy = "record")
    private List<Value> values = new ArrayList<>();
}
