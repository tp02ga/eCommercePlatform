package com.coderscampus.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.Product;
import com.coderscampus.domain.User;
import com.coderscampus.repository.ProductRepository;
import com.coderscampus.service.SaveHelperService;

@Controller
@RequestMapping("dashboard/products")
public class ProductController
{
  private ProductRepository productRepo;
  
  @RequestMapping(value="", method=RequestMethod.POST)
  public @ResponseBody Product addProduct (@AuthenticationPrincipal User user)
  {
    Product product = new Product();
    
    product.setUser(user);
    
    return productRepo.save(product);
  }
  
  @RequestMapping(value="{productId}", method=RequestMethod.POST)
  public @ResponseBody Product updateProduct (@PathVariable Long productId, 
      @RequestParam String fieldName,
      @RequestParam String fieldValue) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    
    Product product = productRepo.findOne(productId);
    
    return SaveHelperService.save(productRepo, product, Product.class, fieldName, fieldValue);
  }
  
  @RequestMapping(value="{productId}", method=RequestMethod.GET)
  public String productGet (@PathVariable Long productId, ModelMap model)
  {
    Product product = productRepo.findOne(productId);
    
    model.put("product", product);
    
    return "product";
  }
  
  
  @Autowired
  public void setProductRepo(ProductRepository productRepo)
  {
    this.productRepo = productRepo;
  }
}
