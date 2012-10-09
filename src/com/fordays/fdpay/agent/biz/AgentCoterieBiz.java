package com.fordays.fdpay.agent.biz;


import java.util.List;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.AgentCoterieListForm;
import com.neza.exception.AppException;


public interface AgentCoterieBiz {

	public AgentCoterie getAgentCoterieById(long id)throws AppException;

	public List<AgentCoterie> getAgentCoterieByCoterieId(AgentCoterieListForm alf) throws AppException ;

	public void saveAgentCoterie(AgentCoterie agentCoterie)throws AppException;

	public long updateAgentCoterieInfo(AgentCoterie agentCoterie)throws AppException;
	
	public void deleteAgentCoterie(long id)throws AppException;
	
	public List getAgentCoterieByCoterieId(long id) throws AppException ;
	
	public int getAgentCoterieByAgent_Id(long id) throws AppException ;
	
	public List  getAgentCoterieByAgent_Id(long id,long coterieId) throws AppException;
}
