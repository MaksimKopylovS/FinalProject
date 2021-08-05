package marcet.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcet.dto.AddressDTO;
import marcet.dto.UserDTO;
import marcet.model.Address;
import marcet.model.User;
import marcet.repository.AddressRepository;
import marcet.repository.UserRepository;
import org.aspectj.weaver.patterns.ParserException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
@Slf4j
@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<AddressDTO> getAdressNoUserName(String userName){
        User user = userRepository.findByUsername(userName).get();
        log.info("user ID {}", user.getUserId());
        List<AddressDTO> addressList = new ArrayList<>();

        addressList.add(convertToDto(addressRepository.findByUserId(user.getUserId())));
        log.info("Address List size  {}, {}", addressList.size(), addressList.get(0).getCity());
        return addressList;
    }

    public AddressDTO convertToDto(Address address){
        AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
        return addressDTO;
    }

    public Address convertyToEntity(AddressDTO addressDTO) throws ParserException {
        Address address = modelMapper.map(addressDTO, Address.class);
        return address;
    }
}
