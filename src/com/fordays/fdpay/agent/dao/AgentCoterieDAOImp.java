package com.fordays.fdpay.agent.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.AgentCoterie;
import com.fordays.fdpay.agent.AgentCoterieListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class AgentCoterieDAOImp extends BaseDAOSupport implements AgentCoterieDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(AgentCoterie agentCoterie) throws AppException {
		/*
		 * this.getHibernateTemplate().saveOrUpdate(agent); return
		 * agent.getId();
		 */
		
		this.getHibernateTemplate().save(agentCoterie);
		return agentCoterie.getId();
	}

	public long merge(AgentCoterie agentCoterie) throws AppException {
		this.getHibernateTemplate().merge(agentCoterie);
		return agentCoterie.getId();
	}

	public long update(AgentCoterie agentCoterie) throws AppException {
		
		if (agentCoterie.getId() > 0){
			this.getHibernateTemplate().merge(agentCoterie);
			return agentCoterie.getId();
		}
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		this.getHibernateTemplate().delete(this.getAgentCoterieById(id));
	}

	public AgentCoterie  getAgentCoterieById(long id) throws AppException {
		// Session session=HibernateServiceProvider.getSessin();
		AgentCoterie agentCoterie;
		if (id > 0) {
			agentCoterie = (AgentCoterie) this.getHibernateTemplate().get(AgentCoterie.class,
					new Long(id));
			return agentCoterie;
		} else
			return new AgentCoterie();
	}

	public AgentCoterie queryById(long id) throws AppException {
		Query query = this.getQuery("from AgentCoterie where id=" + id);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (AgentCoterie) query.list().get(0);
		else
			return new AgentCoterie();
	}

	



	public List list(AgentCoterieListForm clf) throws AppException {
		Hql hql=new Hql();
		 hql.add( " from AgentCoterie where 1=1");

		 
		System.out.println("hql= " + hql);
		
		return this.list(hql, clf);
	}

	public List<AgentCoterie> getAgentCoterieByCoterieId(AgentCoterieListForm alf) throws AppException {
		Hql hql=new Hql();
		hql.add("from AgentCoterie ac where ac.coterie.id=?");
		hql.addParamter(alf.getCoterieId());
		
		if(alf.getAgentEmail()!=null && !"".equals(alf.getAgentEmail())){
			hql.add(" and LOWER(ac.agent.loginName) like LOWER(?)");
			hql.addParamter("%"+alf.getAgentEmail().trim()+"%");
		}
		
		System.out.println("HQL=>>>"+hql);
		
		return this.list(hql, alf);
	}

	public AgentCoterie getAgentCoterieByCoterieAndAgent(String partner,String agentId,String email) throws AppException {
		Hql hql=new Hql();
		StringBuffer sb=new StringBuffer();
		sb.append("from AgentCoterie ac where ac.coterie.partner=? ");
		if(agentId!=null&&!"".equals(agentId)){
			sb.append(" and ac.agent.id=?");
		}else{
			sb.append(" and exists (from Agent where email=?)");			
		}
		hql.add(sb.toString());
		hql.addParamter(partner);
		if(agentId!=null&&!"".equals(agentId)){
			hql.addParamter(new Long(agentId));
		}else{
			hql.addParamter(email);		
		}
		Query query = this.getQuery(hql);
		
		if (query != null && query.list() != null && query.list().size() > 0)
			return (AgentCoterie) query.list().get(0);
		else
			return null;
	}

	public List getAgentCoterieByCoterieId(long id) throws AppException {
		Hql hql=new Hql();
		hql.add("from AgentCoterie ac where ac.coterie.id=?");
		hql.addParamter(id);
		System.out.println("HQL=>>>"+hql);
		Query q=this.getQuery(hql);
		return q.list();
	}
	
	public int  getAgentCoterieByAgent_Id(long id) throws AppException {
		Hql hql=new Hql();
		hql.add("from AgentCoterie ac where ac.agent.id=?");
		hql.addParamter(id);
		System.out.println("HQL=>>>"+hql);
		Query q=this.getQuery(hql);
		return q.list().size();
	}
	public List  getAgentCoterieByAgent_Id(long id,long coterieId) throws AppException {
		Hql hql=new Hql();
		hql.add("from AgentCoterie ac where ac.agent.id=? and ac.coterie.id=?");
		hql.addParamter(id);
		hql.addParamter(coterieId);
		System.out.println("HQL=>>>"+hql);
		Query q=this.getQuery(hql);
		return q.list();
	}

	
	
}
