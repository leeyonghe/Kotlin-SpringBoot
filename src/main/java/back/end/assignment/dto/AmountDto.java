package back.end.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountDto {	
	
	private long amount;
	
	public AmountDto(long amount) {
		this.amount = amount;
	}
	
}
