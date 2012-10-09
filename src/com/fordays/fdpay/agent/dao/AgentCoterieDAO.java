package com.fordays.fdpay.agent.dao;

import java.util.List;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.AgentCoterieListForm;
import com.neza.base.BaseDAO;
import com.neza.exception.AppException;


public interface AgentCoterieDAO extends BaseDAO {
	public AgentCoterie getAgentCoterieById(long id)throws AppException;

	//public List list(AgentCoterieListForm alf)throws AppException;
	
	public long save(AgentCoterie agentCoterie)throws AppException;

	public long merge(AgentCoterie agentCoterie)throws AppException;

	public long update(AgentCoterie agentCoterie)throws AppException;

	public void deleteById(long id)throws AppException;

	public AgentCoterie queryById(long id)throws AppException;
	
	public List<AgentCoterie> getAgentCoterieByCoterieId(AgentCoterieListForm alf)throws AppException;
	
	public AgentCoterie getAgentCoterieByCoterieAndAgent(String coterieId,String agentId,String email) throws AppException;
	
	public List getAgentCoterieByCoterieId(long id)throws AppException;
	
	public int getAgentCoterieByAgent_Id(long id) throws AppException;
	
	public List  getAgentCoterieByAgent_Id(long id,long coterieId) throws AppException;
}
