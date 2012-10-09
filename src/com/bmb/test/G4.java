package com.bmb.test;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.global.App;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;

public class G4 {
	public static void g() {
		double a=10.00;
		double b=0.0825;
		double x=a*b;
		System.out.println(x); // 0.8250000000000001
		
		BigDecimal c=new BigDecimal(10.00);
		BigDecimal d=new BigDecimal(0.0825);
		BigDecimal e=c.multiply(d);
		System.out.println(e.toString()); // 0.82500000000000003885780586188047891482710838317871093750
		
		BigDecimal f=new BigDecimal("1.5");
		System.out.println("1.5="+f.toString());
		
		
		String num = "299792.457999999984";
        BigDecimal val = new BigDecimal(num);
        System.out.println("big decimal: " + val.toString()); // 299792.457999999984
        DecimalFormat nf = new DecimalFormat("#.0000000000");
        System.out.println("double: "+val.doubleValue()); // 299792.458
        System.out.println("double formatted: "+nf.format(val.doubleValue())); // 299792.458
		
		
        BigDecimal amount = new BigDecimal("100.05"); 
        BigDecimal discountPercent = new BigDecimal("0.10"); 
        BigDecimal discount = amount.multiply(discountPercent); 
        discount = discount.setScale(2, RoundingMode.HALF_UP); 
        BigDecimal total = amount.subtract(discount);
        total = total.setScale(2, RoundingMode.HALF_UP); 
        BigDecimal taxPercent = new BigDecimal("0.05"); 
        BigDecimal tax = total.multiply(taxPercent); 
        tax = tax.setScale(2, RoundingMode.HALF_UP); 
        BigDecimal taxedTotal = total.add(tax);
        taxedTotal = taxedTotal.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Subtotal : " + amount);
        System.out.println("Discount : " + discount);
        System.out.println("Total : " + total); 
        System.out.println("Tax : " + tax); 
        System.out.println("Tax+Total: " + taxedTotal); 
        
        
        String awal="100000888";
        if (awal.matches("[0-9]*\\.?[0-9]*")) {
			System.out.println("benar");
		}
        String awal2="2";
        if (awal2.matches("[0-9]")) {
			System.out.println("benar2");
		}
        String kmd =CharMatcher.DIGIT.retainFrom(awal);
        System.out.println(kmd);
	}

}
