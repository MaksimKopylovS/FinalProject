package marcet.repository;

import marcet.model.Address;
import marcet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    //Address findByUserId(Long userId);
    Address findByUser(User user);
}
