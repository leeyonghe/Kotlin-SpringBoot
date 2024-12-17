package back.end.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.TooManyRequests;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import back.end.assignment.dto.AmountDto;
import back.end.assignment.dto.ProductResponseDto;
import back.end.assignment.service.AllService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainPageController {
		
	@Autowired
	private AllService service;

    @GetMapping("/")
    public ModelAndView goHome(HttpServletRequest request) throws Exception {
    	ModelAndView mav = new ModelAndView("home");
    	mav.addObject("products", service.getProduct());
        mav.addObject("baskets", service.getBasket());
        mav.addObject("orders", service.getOrders());
        return mav;
	}
    
    @PostMapping("/setAllDisable")
    @ResponseBody
    public HashMap<String, String> setAllDisable(@RequestBody Map<String, String> param) {
    	int id = Integer.parseInt(param.get("id"));
    	service.setAllDisable(id);
    	HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", "SUCCESS");
		map.put("transactionId", "txn_xxxxxxxxxxx");
		map.put("message", "Payment processed successfully");
    	return map;
	}
    
    @PostMapping("/addBasket")
    @ResponseBody
    public HashMap<String, String> addBasket(@RequestBody Map<String, String> param) {

    	if (param.get("b_id") == null) {
    		
    		long b_id = service.addNewBasket();
    		long p_id = Long.parseLong(param.get("p_id"));
	    	int pcount = Integer.parseInt(param.get("pcount"));
	    	service.addBasket(b_id, p_id, pcount);

	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("status", "SUCCESS");
			map.put("transactionId", "txn_xxxxxxxxxxx");
			map.put("message", "Payment processed successfully");
	    	return map;
	    	
		}else {
			
			long b_id = Long.parseLong(param.get("b_id"));
	    	long p_id = Long.parseLong(param.get("p_id"));
	    	int pcount = Integer.parseInt(param.get("pcount"));
	    	service.addBasket(b_id, p_id, pcount);

	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("status", "SUCCESS");
			map.put("transactionId", "txn_xxxxxxxxxxx");
			map.put("message", "Payment processed successfully");
	    	return map;
	    	
		}
	}
    
    @PostMapping("/paymentRequest")
    @ResponseBody
    public HashMap<String, String> paymentRequest(@RequestBody Map<String, String> param) {
			
		long b_id = Long.parseLong(param.get("b_id"));	
		long id = service.addOrder(b_id);
		AmountDto amount = service.getAmount(id);
		
		try {
			
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("orderId", Long.toString(id));
            parameters.add("amount", Long.toString(amount.getAmount()));

            String url = "https://allra.free.beeceptor.com/api/v1/payment";
            ResponseEntity<String> res = new RestTemplate().postForEntity(url, parameters, String.class);
            System.out.println(res.getBody());
            
            JSONParser jsonParser = new JSONParser();
        	
			JSONObject jsonObject = (JSONObject) jsonParser.parse(res.getBody());
			
			String transactionId = (String) jsonObject.get("transactionId");
			
			String status = (String) jsonObject.get("status");
			
			if (status.equals("SUCCESS")) {
		        
		        service.setSold((int)b_id, 1);
		        
		        service.setOrderTXN(transactionId, id);
		        
		        List<ProductResponseDto> list = service.getBasketItems(b_id);
		        
		        for (ProductResponseDto basketItems : list) {
		        	service.updateStock(basketItems.getId());
				}
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("status", "SUCCESS");
				map.put("transactionId", transactionId);
				map.put("message", "Payment processed successfully");
		    	return map;
				
			} else {
				
				service.removeOrder(id);
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("status", "FAILED");
				map.put("transactionId", null);
				map.put("message", "Something wrong!");
		    	return map;
				
			}
            
		} catch (TooManyRequests e) {

			/**
			 * 
			 * 429 Too Many Requests on POST request for "https://allra.free.beeceptor.com/api/v1/payment": 
			 * "429 - Too Many Requests. Refer: https://beeceptor.com/pricing/"
			 * 
			 * 너무 많은 요청으로 호출이 막혀서 아래와 같이 무조건 성공으로 처리
			 * 
			 */			
			
			service.setSold((int)b_id, 1);
	        
	        service.setOrderTXN("txn_xxxxxxxxxxx", id);
	        
	        List<ProductResponseDto> list = service.getBasketItems(b_id);
	        
	        for (ProductResponseDto basketItems : list) {
	        	service.updateStock(basketItems.getId());
			}

	        HashMap<String, String> map = new HashMap<String, String>();
			map.put("status", "SUCCESS");
			map.put("transactionId", "txn_xxxxxxxxxxx");
			map.put("message", "Payment processed successfully");
	    	return map;
			
		} catch (ParseException e) {
			service.removeOrder(id);

	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("status", "FAILED");
			map.put("transactionId", null);
			map.put("message", "Something wrong!");
	    	return map;
		}
	}
    
}