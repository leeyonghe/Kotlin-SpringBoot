package back.end.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketResponseDto {	
	
	private String uname;
	private String pname;
	private long price;
	private int pcount;
	private long id;
	
	public BasketResponseDto(String uname,String pname,long price, int pcount, long id) {
		this.uname = uname;
		this.pname = pname;
		this.price = price;
		this.pcount = pcount;
		this.id = id;
	}
	
}
