package cat.itb.m09.apirestarian.model.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data @Entity @SequenceGenerator(name = "seq", initialValue = 1)
public class GameItem {
    @Id @GeneratedValue(generator = "seq")
    private long id;
    private String name;
    private String date;
    private String description;
    private String author;
}
