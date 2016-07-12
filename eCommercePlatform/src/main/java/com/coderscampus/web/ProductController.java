package com.coderscampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.Product;
import com.coderscampus.repository.ProductRepository;

@Controller
@RequestMapping("dashboard/products")
public class ProductController
{
  private ProductRepository productRepo;
  
  @RequestMapping(value="", method=RequestMethod.POST)
  public @ResponseBody Product addProduct ()
  {
    Product product = new Product();
    
    return productRepo.save(product);
  }
  
  @RequestMapping(value="{productId}", method=RequestMethod.POST)
  public @ResponseBody Product updateProduct (@PathVariable Long productId, @RequestParam String imageUrl)
  {
    Product product = productRepo.findOne(productId);
    
    product.setImageUrl(imageUrl);
    
    return productRepo.save(product);
  }

  @Autowired
  public void setProductRepo(ProductRepository productRepo)
  {
    this.productRepo = productRepo;
  }
}
