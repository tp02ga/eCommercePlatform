package com.coderscampus.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

public class SaveHelperService 
{
  // if we think about this method in a SPECIFIC way, let's sub in "Product" every time we see "T"
//public static Product save (JpaRepository<Product, Long> repo, Product obj, Class<Product> clazz...
  public static <T> T save (JpaRepository<T, Long> repo, T obj, Class<T> clazz, String fieldName, String fieldValue) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
  {
    // passing in, for example: imageUrl (as the fieldName)
    //  therefore we need to capitalize the first letter to properly invoke the setter method
    fieldName = StringUtils.capitalize(fieldName);
    
    Method[] methods = clazz.getMethods();
    Method method = null; 
    
    for (Method aMethod : methods)
    {
      if (aMethod.getName().equals("set" + fieldName))
      {
        method = aMethod;
        if (method.getParameterTypes()[0].getName().indexOf("String") > -1)
        {
          // we know that this setter method takes a String
          method.invoke(obj, fieldValue);
        }
        else if (method.getParameterTypes()[0].getName().indexOf("Double") > -1)
        {
          // strip out any currency characters in the String before converting to a Double
          fieldValue = fieldValue.replace("$", "");
          
          // we know that this setter method takes a Double
          method.invoke(obj, Double.valueOf(fieldValue));
        }
        break;
      }
    }
    
    
    
    return repo.save(obj);
  }
}
