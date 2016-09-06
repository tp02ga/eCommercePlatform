package com.coderscampus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.Product;
import com.coderscampus.domain.User;
import com.coderscampus.repository.ProductRepository;
import com.coderscampus.repository.UserRepository;

@Controller
@RequestMapping("products")
public class ConsumerProductController
{
  private ProductRepository productRepo;
  private UserRepository userRepo;
  
  @RequestMapping(value="{productId}", method=RequestMethod.GET)
  public String viewProduct (@PathVariable Long productId, ModelMap model)
  {
    Product product = productRepo.findOne(productId);
    
    model.put("product", product);
    
    return "product";
  }
  
  @RequestMapping(value="{productId}", method=RequestMethod.POST)
  public @ResponseBody String addProductToCart (@AuthenticationPrincipal User user, 
      HttpServletRequest request, @PathVariable Long productId, ModelMap model)
  {
    // if user isn't logged in, then store the cart information on the session
    if (user == null)
    {
      HttpSession session = request.getSession();
      
      // create the shopping cart object and populate it with the product
    }
    else
    {
      // store cart information on user domain object
      user = userRepo.findOne(user.getId());

      // create the shopping cart object and populate it with the product
    }
    
    return "{\"success\": true}";
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
