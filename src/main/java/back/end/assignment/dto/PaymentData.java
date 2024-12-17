package back.end.assignment.dto;

import lombok.Data;

@Data
public class PaymentData {
	
    private String status;
    private String transactionId;
    private String message;

}
