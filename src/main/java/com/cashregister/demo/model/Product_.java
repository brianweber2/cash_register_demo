package com.cashregister.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {
    public static volatile SingularAttribute<Product, String> sku;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, BigDecimal> defaultPrice;
    public static volatile SingularAttribute<Product, BigDecimal> discountPrice;

    public static final String SKU = "sku";
    public static final String NAME = "name";
    public static final String DEFAULT_PRICE = "defaultPrice";
    public static final String DISCOUNT_PRICE = "discountPrice";
}
