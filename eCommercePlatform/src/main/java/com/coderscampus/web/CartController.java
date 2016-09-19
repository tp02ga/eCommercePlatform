package com.coderscampus.web;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderscampus.domain.Cart;
import com.coderscampus.domain.Product;
import com.coderscampus.domain.User;
import com.coderscampus.repository.CartRepository;
import com.coderscampus.repository.UserRepository;

@Controller
public class CartController
{
  private CartRepository cartRepo;
  private UserRepository userRepo;
  
  @RequestMapping(value="cart", method=RequestMethod.GET)
  public String showCart (@AuthenticationPrincipal User user, ModelMap model)
  {
    user = userRepo.findOne(user.getId());
    
    Cart cart = user.getCart();
    
    double total = cart.getProducts().stream()
                      .map(Product::getPrice)
                      .mapToDouble(Double::doubleValue).sum();
    
    model.put("total", total);
    model.put("cart", cart);
    
    return "cart";
  }

  @RequestMapping(value="cart", method=RequestMethod.POST)
  public String purchase (@AuthenticationPrincipal User user, ModelMap model)
  {
    user = userRepo.findOne(user.getId());
    
    Cart cart = user.getCart();
    
//    cart.getProducts().forEach(product -> {
//      cart.getProducts().remove(product);
//      product.getCarts().remove(cart);
//      });

    Iterator<Product> itr = cart.getProducts().iterator();
    
    while (itr.hasNext())
    {
      Product product = itr.next();
      product.getCarts().remove(cart);
      itr.remove();
    }
    
    cartRepo.save(cart);
    
    return "redirect:/";
  }
  
  @Autowired
  public void setCartRepo(CartRepository cartRepo)
  {
    this.cartRepo = cartRepo;
  }
  @Autowired
  public void setUserRepo(UserRepository userRepo)
  {
    this.userRepo = userRepo;
  }
}
