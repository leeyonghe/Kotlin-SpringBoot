package back.end.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {	
	
	private String pname;
	private long price;
	private int pcount;
	private long id;
	
	public ProductResponseDto(String pname,long price, int pcount, long id) {
		this.pname = pname;
		this.price = price;
		this.pcount = pcount;
		this.id = id;
	}
	
}
