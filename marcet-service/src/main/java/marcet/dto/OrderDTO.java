package marcet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    private String userName;
    private Long   addressId;
}

