package back.end.assignment.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import back.end.assignment.domain.Basket;
import back.end.assignment.dto.BasketResponseDto;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

	@Query("SELECT\n"
			+ "	new back.end.assignment.dto.BasketResponseDto(c.name as uname,p.name as pname,p.price, bi.pcount, b.id) \n"
			+ "from\n"
			+ "	Basket b\n"
			+ "Left Join BasketItems bi on\n"
			+ "	b.id = bi.basket.id\n"
			+ "Left Join Customer c on\n"
			+ "	c.id = b.customer.id\n"
			+ "Left Join Product p on\n"
			+ "	p.id = bi.product.id\n"
			+ "where b.sold = 0")
	@Transactional
    List<BasketResponseDto> getAllList();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Basket SET sold = 2 WHERE id = :id")
	@Transactional
    void setAllDisable(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Basket SET sold = :sold WHERE id = :id")
	@Transactional
    void setSold(@Param("id") long id, @Param("sold") int sold);

}