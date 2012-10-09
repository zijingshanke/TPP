package com.fordays.fdpay.agent.dao;

import java.util.List;

import org.hibernate.Query;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentRelationship;
import com.fordays.fdpay.agent.AgentRelationshipListForm;
import com.fordays.fdpay.agent.Coterie;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;


public class AgentRelationshipDAOImpl extends BaseDAOSupport implements AgentRelationshipDAO{

	

	

	public List getAgentParentByAgent(AgentRelationshipListForm alf) throws AppException {
		Hql hql=new Hql("from AgentRelationship where agent.id=?");
		hql.addParamter(alf.getId());
		return this.list(hql, alf);
	}
	
	public List getAgentChildByAgent(AgentRelationshipListForm alf) throws AppException {
		Hql hql=new Hql("from AgentRelationship where parentAgent.id=?");
		hql.addParamter(alf.getId());
		return this.list(hql, alf);
	}

	
	
}
