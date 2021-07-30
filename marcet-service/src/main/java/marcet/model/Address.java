package marcet.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "addresses_tbl")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "region_fld")
    private String region;

    @Column(name = "city_fld")
    private String city;

    @Column(name = "street_fld")
    private String street;

    @Column(name = "house_number_fld")
    private String houseNumber;

    @Column(name = "user_id")
    private Long userId;

//    @ManyToMany
//    @JoinTable(name = "orders_tbl",
//            joinColumns = @JoinColumn(name = "address_id"),
//            inverseJoinColumns = @JoinColumn(name = "address_id"))
//    private Collection<Order> ordersCollection;

}
