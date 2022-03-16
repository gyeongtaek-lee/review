package com.chicken.review.login.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.chicken.review.HttpUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class RestController {
	
	public enum BaseProduct {
		DLRUDXOR("테스트 상품1 LG"),
		DLTLDN("테스트 상품2 LG"),
		RLARUDDHR("테스트 상품3 LG");
		
		private final String prodName;
		
		BaseProduct(String prodName) {this.prodName = prodName;}
		
		public String getProdName() {return prodName;}
	}
	

	@GetMapping("/main")
    public String mainIndexPage() {
        
        JsonObject param = new JsonObject();
        // POST 방식으로 호출.(GET, POST, PUT, DELETE 다 가능 합니다.)
        String result = HttpUtil.callApi(param, "POST");
        
        return result;
    }
	
	@GetMapping("/main2")
    public String mainIndexPage2() {
        
        JsonObject param = new JsonObject();
        // POST 방식으로 호출.(GET, POST, PUT, DELETE 다 가능 합니다.)
        String result = HttpUtil.insertBaseProduct(param, "POST");
        
        return result;
    }
	
	@GetMapping("/insertBaseProducts")
    public String insertBaseProducts() {
        
        JsonObject params = new JsonObject();
        
        // JSON 형식의 데이터 셋팅
        JsonObject commands = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        
        Arrays.stream(BaseProduct.values()).forEach(value ->{
        	
        	params.addProperty("prod_name", value.getProdName()+" "+value.name());
            params.addProperty("delivery_vendor", "28654");
            params.addProperty("maker", "LG전자");
            
            commands.addProperty("sku_cd", value.name()+".AKOR");
            commands.addProperty("model_no", value.name()+".AKOR");
            commands.addProperty("model", value.name());
            commands.addProperty("product_price", "100000");
            commands.addProperty("real_stock", "0");
            commands.addProperty("safe_stock", "0");
            commands.addProperty("opt1_type", "없음");
            jsonArray.add(commands);
            
            params.add("opt", jsonArray);
            
            // POST 방식으로 호출.(GET, POST, PUT, DELETE 다 가능 합니다.)
            String result = HttpUtil.insertBaseProducts(params, "POST");
        	
        });
        
        
        return null;
    }
	
	
	
	
}
