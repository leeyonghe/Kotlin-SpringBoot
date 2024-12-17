package back.end.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import back.end.assignment.domain.Basket;
import back.end.assignment.domain.BasketItems;
import back.end.assignment.domain.Customer;
import back.end.assignment.domain.Orders;
import back.end.assignment.domain.Product;
import back.end.assignment.dto.AmountDto;
import back.end.assignment.dto.BasketResponseDto;
import back.end.assignment.dto.OrderHistoryResponseDto;
import back.end.assignment.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllService {
	
	private final BasketRepository basketRepository;
	private final BasketItemsRepository basketItemsRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;

    public List<ProductResponseDto> getProduct() {
        List<ProductResponseDto> result = productRepository.getAllList();
        return result;
    }

    public List<BasketResponseDto> getBasket() {
        List<BasketResponseDto> result = basketRepository.getAllList();
        return result;
    }

    public List<OrderHistoryResponseDto> getOrders() {
        List<OrderHistoryResponseDto> result = orderRepository.getAllList();
        return result;
    }

	public void setAllDisable(int i) {
		basketRepository.setAllDisable(i);
	}
	
	public void setSold(int b_id, int sold) {
		basketRepository.setSold(b_id, sold);
	}
	
	public Long addNewBasket() {
		Basket basket = new Basket();
		Customer customer = customerRepository.findById((long)1).get();
		basket.setCustomer(customer);
		basket.setSold(0);
		Basket obj = basketRepository.save(basket);
		return obj.getId();
	}

	public void addBasket(long b_id, long p_id, int pcount) {
		
		Basket basket = basketRepository.findById(b_id).get();
		Product product = productRepository.findById(p_id).get();				
		
		BasketItems basketItems = new BasketItems();
		basketItems.setBasket(basket);
		basketItems.setProduct(product);
		basketItems.setPcount(pcount);
		
		basketItemsRepository.saveAndFlush(basketItems);
		
	}

	public Long addOrder(long b_id) {
		
		Basket basket = basketRepository.findById(b_id).get();
		
		Orders orders = new Orders();
		orders.setBasket(basket);
		
		Orders obj = orderRepository.saveAndFlush(orders);
		return obj.getId();		
		
	}

	public AmountDto getAmount(long id) {
		
		AmountDto amount = basketItemsRepository.getBasketAmount(id);
		
		return amount;
		
	}

	public void setOrderTXN(String txn, long id) {
		orderRepository.setOrderTXN(txn, id);
	}

	public void removeOrder(long id) {
		orderRepository.deleteById(id);	
	}

	public List<ProductResponseDto> getBasketItems(long bid) {		
		return basketItemsRepository.getBasketList(bid);
	}

	public void updateStock(Long id) {
		productRepository.updateStock(id);
	}

}
