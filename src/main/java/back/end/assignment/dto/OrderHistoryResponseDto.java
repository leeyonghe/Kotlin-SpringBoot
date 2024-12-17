package back.end.assignment.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderHistoryResponseDto {
	
	private String uname;
	private Object itemlist;
	private long total;
	private String txn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
	
	public OrderHistoryResponseDto(String uname,Object itemlist,long total,LocalDateTime createdAt, String txn) {
		this.uname = uname;
		this.itemlist = itemlist;
		this.total = total;
		this.createdAt = createdAt;
		this.txn = txn;
	}

}
