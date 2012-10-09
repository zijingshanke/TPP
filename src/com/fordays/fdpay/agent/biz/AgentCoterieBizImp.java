package com.fordays.fdpay.agent.biz;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.AgentCoterieListForm;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.dao.AgentCoterieDAO;

public class AgentCoterieBizImp implements AgentCoterieBiz{
	private AgentCoterieDAO agentCoterieDAO;
	private TransactionTemplate transactionTemplate;
	
	public void setTransactionManager(
			HibernateTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}
	public void deleteAgentCoterie(long id) throws AppException {
		agentCoterieDAO.deleteById(id);
		
		
		
	}

	
	public AgentCoterie getAgentCoterieById(long id) throws AppException {
	
		return agentCoterieDAO.getAgentCoterieById(id);
	}

	public List<AgentCoterie> getAgentCoterieByCoterieId(AgentCoterieListForm alf) throws AppException {
	
		return agentCoterieDAO.getAgentCoterieByCoterieId(alf);
	}

	public void saveAgentCoterie(AgentCoterie agentCoterie) throws AppException {
		
		agentCoterieDAO.save(agentCoterie);
	}

	public long updateAgentCoterieInfo(AgentCoterie agentCoterie)
			throws AppException {
	
		agentCoterieDAO.update(agentCoterie);
		return 0;
	}

	public AgentCoterieDAO getAgentCoterieDAO() {
		return agentCoterieDAO;
	}

	public void setAgentCoterieDAO(AgentCoterieDAO agentCoterieDAO) {
		this.agentCoterieDAO = agentCoterieDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public List getAgentCoterieByCoterieId(long id) throws AppException {
		return agentCoterieDAO.getAgentCoterieByCoterieId(id);
	}
	
	public int getAgentCoterieByAgent_Id(long id) throws AppException {
		return agentCoterieDAO.getAgentCoterieByAgent_Id(id);
	}
	
	public List  getAgentCoterieByAgent_Id(long id,long coterieId) throws AppException{
		return agentCoterieDAO.getAgentCoterieByAgent_Id(id, coterieId);
	}
}


