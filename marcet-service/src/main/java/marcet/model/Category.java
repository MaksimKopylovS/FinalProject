package marcet.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categories_tbl")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "title_fld")
    private String title;

    public Category(String title) {
        this.title = title;
    }
}
