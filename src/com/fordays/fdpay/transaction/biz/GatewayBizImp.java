package com.fordays.fdpay.transaction.biz;


import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.neza.exception.AppException;

public class GatewayBizImp implements GatewayBiz {
	private AgentDAO agentDAO;
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public AgentDAO getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) throws AppException{
		this.agentDAO = agentDAO;
	}

	public Agent getAgent(Agent agent) throws AppException{
		Agent agt = null;
		try {
			if (agent != null) {
				if (agent.getId() != 0) {
					agt = agentDAO.getAgentById(agent.getId());
				} else if (agent.getEmail() != null
						&& !"".equals(agent.getEmail().trim())) {
					agt = agentDAO.getAgentByEmail(agent.getEmail());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return agt;
	}

	public boolean validateAmount(Agent agent, double balance) throws AppException {
		boolean flag = false;
		try {
			
			if (agent != null && agent.getAllowBalance() != null) {
				agentDAO.synallowBalance(agent);
				if (agent.getAllowBalance().doubleValue() >= balance) {
					flag = true;
				}
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return flag;
	}

	public boolean validatePartner(String partner) throws AppException {
		boolean flag = false;
		try {
			Agent agt = agentDAO.queryByPartner(partner);
			if (agt != null) {
				flag = true;
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return flag;
	}
}
