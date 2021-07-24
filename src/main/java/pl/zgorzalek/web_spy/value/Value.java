package pl.zgorzalek.web_spy.value;

import lombok.Data;
import pl.zgorzalek.web_spy.record.Record;

import javax.persistence.*;

@Entity
@Data
@Table(name = Value.TABLE_NAME)
public class Value {
    public static final String TABLE_NAME = "values_records";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String value;

    @ManyToOne
    private Record record;
}
