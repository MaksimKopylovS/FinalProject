package marcet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_categoriy")
    private Long id_categoriy;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;
}
