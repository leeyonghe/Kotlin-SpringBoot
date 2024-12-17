package back.end.assignment.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import back.end.assignment.domain.Orders;
import back.end.assignment.dto.OrderHistoryResponseDto;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	
	@Query("SELECT\n"
			+ "	new back.end.assignment.dto.OrderHistoryResponseDto(c.name, GROUP_CONCAT(p.name), SUM(p.price), o.createdAt, o.txn) \n"
			+ "from\n"
			+ "	Orders o\n"
			+ " Left Join Basket b on b.id = o.basket.id \n"
			+ " Left Join BasketItems bi on b.id = bi.basket.id \n"
			+ " Left Join Customer c on c.id = b.customer.id \n"
			+ " Left Join Product p on p.id = bi.product.id \n"
			+ " GROUP by o.id")
	@Transactional
    List<OrderHistoryResponseDto> getAllList();
	

	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Orders SET txn = :txn, updatedAt = current_timestamp WHERE id = :id")
	@Transactional
    void setOrderTXN(@Param("txn") String txn,@Param("id")  long id);

}
