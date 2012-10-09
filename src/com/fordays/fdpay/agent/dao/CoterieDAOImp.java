package com.fordays.fdpay.agent.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.CoterieListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class CoterieDAOImp extends BaseDAOSupport implements CoterieDAO {
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(Coterie coterie) throws AppException {
		/*
		 * this.getHibernateTemplate().saveOrUpdate(agent); return
		 * agent.getId();
		 */
		
		this.getHibernateTemplate().merge(coterie);
		return coterie.getId();
	}

	public long merge(Coterie coterie) throws AppException {
		this.getHibernateTemplate().merge(coterie);
		return coterie.getId();
	}

	public long update(Coterie coterie) throws AppException {
		
		if (coterie.getId() > 0)
			return save(coterie);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		this.getHibernateTemplate().delete(this.getCoterieById(id));
	}

	public Coterie  getCoterieById(long id) throws AppException {
		// Session session=HibernateServiceProvider.getSessin();
		Coterie coterie;
		if (id > 0) {
			coterie = (Coterie) this.getHibernateTemplate().get(Coterie.class,
					new Long(id));
			return coterie;
		} else
			return new Coterie();
	}

	public Coterie queryById(long id) throws AppException {
		Query query = this.getQuery("from Coterie where id=" + id);
		if (query.list() != null && query != null && query.list().size() > 0)
			return (Coterie) query.list().get(0);
		else
			return new Coterie();
	}

	



	public List list(CoterieListForm clf) throws AppException {
		
		 String hql= " from Coterie cr where 1=1";

		if ((clf.getCoterieName() != null) && (!clf.getCoterieName().trim().equals("")))
			{
				hql+="  and cr.name like '%"+clf.getCoterieName().trim()+"%'";
			
			}
		if ((clf.getAgentEmail() != null) && (!clf.getAgentEmail().trim().equals("")))
			{
			hql+=" and LOWER(cr.agent.email) like LOWER('%"+clf.getAgentEmail().trim()+"%')";
			
			}
		if ((clf.getTempAgentEmail() != null) && (!clf.getTempAgentEmail().trim().equals("")))
		{
			hql+=" and LOWER(cr.tempAgent.email) like LOWER('%"+clf.getTempAgentEmail().trim()+"%')";
			
		}
		
		System.out.println("hql= " + hql);
		
		return this.list(hql, clf);
	}

	public Coterie getCoterieByName(String name) throws AppException {
		
		Hql hql=new Hql();
		hql.add("from Coterie where name=?");
		hql.addParamter(name);
		Query query=getQuery(hql);
		if (query!=null&&query.list() != null && query != null && query.list().size() > 0)
			return (Coterie) query.list().get(0);
		else
			return new Coterie();
	}

	public Coterie getCoterieByPartner(String partner) throws AppException {
		
		Hql hql=new Hql();
		hql.add("from Coterie where partner=?");
		hql.addParamter(partner);
		Query query=getQuery(hql);
		Coterie coterie=null;
		if (query.list() != null && query != null && query.list().size() > 0)
			coterie= (Coterie) query.list().get(0);
		return coterie;
	}

	public Coterie getCoterieByEmail(String email) throws AppException {
		Hql hql=new Hql();
		hql.add("from Coterie c where LOWER(c.agent.loginName)=LOWER(?)");
		hql.addParamter(email);
		System.out.println(hql+">>>>>>>>>>>>>>");
		Query query=getQuery(hql);
		if (query!=null&&query.list() != null && query != null && query.list().size() > 0)
			return (Coterie) query.list().get(0);
		else
			return new Coterie();
	}
	
	public int getCoterieByAgent_Id(long id) throws AppException{
		Hql hql=new Hql();
		hql.add("from Coterie c where c.agent.id=?");
		hql.addParamter(id);
		System.out.println("HQL=>>>"+hql);
		Query q=this.getQuery(hql);
		return q.list().size();
	}
	
	public boolean checkCoerieByAllowMult(long  agentId) throws AppException{
		
		boolean check=false;
		Hql hql=new Hql();
		hql.add(" select c.allowMultcoterie from Coterie c,AgentCoterie a where a.agent.id=? and  a.coterie.id=c.id");
		hql.addParamter(agentId);
		System.out.println("HQL=>>>"+hql);
		Query q=this.getQuery(hql);
		
		if(q.list().size()>0){
			
		for (int i = 0; i < q.list().size(); i++) {
			System.out.println(q.list().get(i));
			if((q.list().get(i)).equals("0")){
				check=true;
			}
		}
		
		}
			return check;
		
	}
	
	
}
