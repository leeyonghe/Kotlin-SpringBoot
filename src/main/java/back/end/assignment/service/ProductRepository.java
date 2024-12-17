package back.end.assignment.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import back.end.assignment.domain.Product;
import back.end.assignment.dto.ProductResponseDto;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT new back.end.assignment.dto.ProductResponseDto(name, price, stock, id) from Product")
	@Transactional
    List<ProductResponseDto> getAllList();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Product SET stock = (stock - 1) WHERE id = :id")
	@Transactional
	void updateStock(@Param("id") Long id);

}
