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
    
    Method method = clazz.getMethod("set" + fieldName, String.class);
    
    // ex: setImageUrl("theImageUrl")
    method.invoke(obj, fieldValue);
    
    return repo.save(obj);
  }
}
