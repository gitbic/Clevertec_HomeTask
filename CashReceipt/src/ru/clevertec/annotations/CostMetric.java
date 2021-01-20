package ru.clevertec.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CostMetric {

    String getTotalCost() default "no total cost";
//    String getDiscountCost() default "no discount cost";
//    String getFinalCost() default "no final cost";
}
