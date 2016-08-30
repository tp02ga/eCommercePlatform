package com.coderscampus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderscampus.domain.Product;
import com.coderscampus.repository.ProductRepository;

@Controller
public class ECommerceStoreController
{
  private ProductRepository productRepo;
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String rootGet (ModelMap model)
  {
    Page<Product> products = productRepo.findAll(new PageRequest(0, 10));
    
    model.put("products", products);
    
    return "store";
  }
  
  @RequestMapping(value="page{pageNum}", method=RequestMethod.GET)
  public String rootGet (@PathVariable Integer pageNum, ModelMap model)
  {
    Page<Product> products = productRepo.findAll(new PageRequest(pageNum, 10));
    
    model.put("products", products);
    
    return "store";
  }

  @Autowired
  public void setProductRepo(ProductRepository productRepo)
  {
    this.productRepo = productRepo;
  }
}
