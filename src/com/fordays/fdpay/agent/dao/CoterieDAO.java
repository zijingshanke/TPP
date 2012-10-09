package com.fordays.fdpay.agent.dao;

import java.util.List;
import com.neza.exception.AppException;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.CoterieListForm;
import com.neza.base.BaseDAO;

public interface CoterieDAO extends BaseDAO {
	public Coterie getCoterieById(long id)throws AppException;

	public List list(CoterieListForm clf)throws AppException;

	public long save(Coterie coterie)throws AppException;

	public long merge(Coterie coterie)throws AppException;

	public long update(Coterie coterie)throws AppException;

	public void deleteById(long id)throws AppException;

	public Coterie queryById(long id)throws AppException;
	
	public Coterie getCoterieByName(String name)throws AppException;
	
	public Coterie getCoterieByPartner(String partner) throws AppException ;
	
	public Coterie getCoterieByEmail(String email) throws AppException;
	
	public int getCoterieByAgent_Id(long id) throws AppException;
	
	public boolean checkCoerieByAllowMult(long   agentId) throws AppException;
}
