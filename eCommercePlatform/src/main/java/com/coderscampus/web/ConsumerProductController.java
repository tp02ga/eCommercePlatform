package com.coderscampus.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.coderscampus.repository.CartRepository;
import com.coderscampus.repository.ProductRepository;
import com.coderscampus.repository.UserRepository;

@Controller
@RequestMapping("products")
public class ConsumerProductController
{
  private ProductRepository productRepo;
  private UserRepository userRepo;
  private CartRepository cartRepo;
  
  @RequestMapping(value="{productId}", method=RequestMethod.GET)
  public String viewProduct (@PathVariable Long productId, ModelMap model)
  {
    Product product = productRepo.findOne(productId);
    
    model.put("product", product);
    
    return "product";
  }
  
  @RequestMapping(value="{productId}", method=RequestMethod.POST)
  public @ResponseBody Cart addProductToCart (@AuthenticationPrincipal User user, 
      HttpServletRequest request, @PathVariable Long productId, ModelMap model)
  {
    Cart cart = new Cart();
    // if user isn't logged in, then have them login or create an account
    if (user == null)
    {
      return null;
    }
    else
    {
      // store cart information on user domain object
      user = userRepo.findOne(user.getId());
      Set<Product> products = new HashSet<>();
      
      if (user.getCart() == null)
      {
        // create the shopping cart object and populate it with the product
        user.setCart(cart);
        cart.setUser(user);
        
        cart = cartRepo.save(cart);
      }
      else
      {
        cart = user.getCart();
        products.addAll(cart.getProducts());
      }
      
      Set<Cart> carts = new HashSet<>();
      
      Product product = productRepo.findOne(productId);
      product.setCarts(carts);
      products.add(product);
      
      cart.setProducts(products);
      cart.setDateAdded(new Date());
      carts.add(cart);
      
      productRepo.save(product);
    }
    
    return cart;
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

  @Autowired
  public void setCartRepo(CartRepository cartRepo)
  {
    this.cartRepo = cartRepo;
  }
  
  
}
