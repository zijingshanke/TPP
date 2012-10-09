package com.fordays.fdpay.agent.biz;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.CoterieListForm;
import com.fordays.fdpay.agent.dao.CoterieDAO;
import com.neza.exception.AppException;



public class CoterieBizImp implements CoterieBiz{
	private CoterieDAO coterieDAO;
	private TransactionTemplate transactionTemplate;
	
	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	public void deleteCoterie(long id) throws AppException {
		
		coterieDAO.deleteById(id);
	}


	public Coterie getCoterieById(long id) throws AppException {
		
		return coterieDAO.getCoterieById(id);
	}

	public Coterie getCoterieByName(String name) throws AppException {
	
		return coterieDAO.getCoterieByName(name);
	}

	public List getCoteries(CoterieListForm clf) throws AppException {
	
		return coterieDAO.list(clf);
	}

	public void saveCoterie(Coterie coterie) throws AppException {
	
		coterieDAO.save(coterie);
		
	}

	public long updateCoterieInfo(Coterie coterie) throws AppException {
		coterieDAO.update(coterie);
		return 0;
	}

	public CoterieDAO getCoterieDAO() {
		return coterieDAO;
	}

	public void setCoterieDAO(CoterieDAO coterieDAO) {
		this.coterieDAO = coterieDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public long updateCoterieKey(Coterie coterie) throws AppException {
		
		return coterieDAO.update(coterie);
	}
	public Coterie getCoterieByEmail(String email) throws AppException {
		
		return coterieDAO.getCoterieByEmail(email);
	}

	public int getCoterieByAgent_Id(long id) throws AppException{
		
		return coterieDAO.getCoterieByAgent_Id(id);
	}
	public boolean checkCoerieByAllowMult(long   agentId) throws AppException{
		
		return coterieDAO.checkCoerieByAllowMult(agentId);
	}
}
