package com.coderscampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.Cart;
import com.coderscampus.domain.Product;
import com.coderscampus.domain.User;
import com.coderscampus.repository.ProductRepository;
import com.coderscampus.repository.UserRepository;

@Controller
public class ECommerceStoreController
{
  private ProductRepository productRepo;
  private UserRepository userRepo;
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String rootGet (ModelMap model)
  {
    Page<Product> products = productRepo.findAll(new PageRequest(0, 10));
    
    model.put("products", products);
    
    return "store";
  }
  
  @RequestMapping(value="itemsInCart", method=RequestMethod.POST)
  public @ResponseBody Cart getItemsInCart (@AuthenticationPrincipal User user) {
    if (user != null)
    {
      user = userRepo.findOne(user.getId());
      return user.getCart();
    }
    return null;
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

  @Autowired
  public void setUserRepo(UserRepository userRepo)
  {
    this.userRepo = userRepo;
  }
  
}
