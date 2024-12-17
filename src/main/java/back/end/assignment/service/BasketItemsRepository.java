package back.end.assignment.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import back.end.assignment.domain.BasketItems;
import back.end.assignment.dto.AmountDto;
import back.end.assignment.dto.ProductResponseDto;

@Repository
public interface BasketItemsRepository extends JpaRepository<BasketItems, Long> {

	@Query("SELECT\n"
			+ "	new back.end.assignment.dto.AmountDto(SUM(p.price)*bi.pcount)\n"
			+ "from\n"
			+ "	Orders o\n"
			+ " Left Join Basket b on o.basket.id = b.id\n"
			+ "Left Join BasketItems bi on\n"
			+ "	b.id = bi.basket.id\n"
			+ "Left Join Customer c on\n"
			+ "	c.id = b.customer.id\n"
			+ "Left Join Product p on\n"
			+ "	p.id = bi.product.id\n"
			+ "WHERE o.id = :id\n"
			+ " GROUP by o.id")
	@Transactional
	AmountDto getBasketAmount(@Param("id") long id);

	@Query("SELECT new back.end.assignment.dto.ProductResponseDto('',0,0,p.id) FROM BasketItems bi \n"
			+ "left join product p on bi.product.id = p.id \n"
			+ "WHERE bi.basket.id = :bid")
	@Transactional
	List<ProductResponseDto> getBasketList(@Param("bid") long bid);

}