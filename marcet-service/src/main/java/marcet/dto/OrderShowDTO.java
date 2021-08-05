package marcet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class OrderShowDTO {
    private BigInteger orderId;
    private String title;
    private Short quantity;
    private BigDecimal price;
    private BigDecimal cost;
}
