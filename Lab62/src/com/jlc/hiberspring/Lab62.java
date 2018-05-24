package com.jlc.hiberspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab62 {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("jlcindia.xml");
		CustomerDAO cdao=(CustomerDAO)ctx.getBean("cdao");
		//1) add Customer
		CustomerTO cto=new CustomerTO(510, "Shreya", "shreya@gmail.com", 778887659,"banglr" );
		cdao.addCustomer(cto);
		//CustomerTO cto1=new CustomerTO(511, "Mona", "mona@gmail.com", 778888959,"patna" );
		//cdao.addCustomer(cto1);
	//	CustomerTO cto2=new CustomerTO(512, "Sonali", "sonali@gmail.com", 778887489,"Vaishali" );
		///cdao.addCustomer(cto2);
		//CustomerTO cto3=new CustomerTO(513, "Subha", "sbubha@gmail.com", 778887929,"Samastipur" );
		//cdao.addCustomer(cto3);
		System.out.println("Record inserted");
	
		
	//2).Update Customer
	CustomerTO  cto4=new CustomerTO(501, "sanaya","sanaya@gmail.com", 74156089," Kolkata");
	 cdao.updateCustomer(cto4);
	 System.out.println("Record Updated");
	
//4)getCustomersBYCid
	System.out.println("getCustomersByCid");
	 cto=cdao.getCustomerByCid(1);
	System.out.println(cto);
}}
