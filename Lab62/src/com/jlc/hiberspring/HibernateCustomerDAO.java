package com.jlc.hiberspring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class HibernateCustomerDAO implements CustomerDAO {
	@Autowired
	HibernateTemplate hibernateTemp;
	@Autowired
	HibernateTransactionManager txManager=null;

	@Override
	public void addCustomer(CustomerTO cto) {
	Customer cust=new Customer(cto.getCname(),cto.getEmail(),cto.getPhone(),cto.getCity());
	TransactionDefinition txDef=new DefaultTransactionDefinition();
	TransactionStatus ts=txManager.getTransaction(txDef);
	hibernateTemp.save(cust);
	txManager.commit(ts);
	
	}
	@Override
	public void updateCustomer(CustomerTO cto) {
	TransactionDefinition txDef=new DefaultTransactionDefinition();
	TransactionStatus ts=txManager.getTransaction(txDef);
	Customer cust1=(Customer)hibernateTemp.load(Customer.class, cto.getCid());
	cust1.setCid(cto.getCid());
	cust1.setCname(cto.getCname());
	cust1.setEmail(cto.getEmail());
	cust1.setPhone(cto.getPhone());
	hibernateTemp.update(cust1,LockMode.NONE);
	txManager.commit(ts);
	}

@Override
	public void deleteCustomer(int cid) {
		TransactionDefinition txDef=new DefaultTransactionDefinition();
		TransactionStatus ts=txManager.getTransaction(txDef);
		Customer c=(Customer)hibernateTemp.load(Customer.class, cid);
		hibernateTemp.delete(c,LockMode.NONE);
		txManager.commit(ts);
		}
		
	public List<CustomerTO>getAllCustomer(){
		List<CustomerTO>ctoList=new ArrayList<CustomerTO>();
		String hql="from Customer c";
		List<Customer>list=(List<Customer>)hibernateTemp.find(hql);
		for(Customer c:list){
			CustomerTO cto=new CustomerTO(c.getCid(),c.getCname(),c.getEmail(),c.getPhone(),c.getCity());
			ctoList.add(cto);
		}
		return ctoList;
	}

	@Override
	public CustomerTO getCustomerByCid(int cid) {
	Customer  c=(Customer)hibernateTemp.load(Customer.class, cid);
	CustomerTO cto=new CustomerTO(c.getCid(),c.getCname(),c.getEmail(),c.getPhone(),c.getCity());
	return cto;
	}

}
