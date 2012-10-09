package com.fordays.fdpay.agent.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.AgentBalance;
import com.fordays.fdpay.agent.AgentException;
import com.fordays.fdpay.agent.AgentListForm;
import com.fordays.fdpay.agent.CertInfo;
import com.fordays.fdpay.agent.MailAgent;
import com.fordays.fdpay.agent.MailAgentListForm;
import com.fordays.fdpay.agent.RepeatCharge;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.database.SelectDataBean;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class AgentDAOImp extends BaseDAOSupport implements AgentDAO
{
	private TransactionTemplate transactionTemplate;

	public void setTransactionManager(
	    PlatformTransactionManager transactionManager)
	{
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	public long save(Agent agent) throws AppException
	{
		/*
		 * this.getHibernateTemplate().saveOrUpdate(agent); return agent.getId();
		 */
		this.getHibernateTemplate().merge(agent);
		return agent.getId();
	}

	public long merge(Agent agent) throws AppException
	{
		this.getHibernateTemplate().merge(agent);
		return agent.getId();
	}

	public long update(Agent agent) throws AppException
	{
		if (agent.getId() > 0)
			return save(agent);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public Agent getAgentById(long id) throws AppException
	{
		// Session session=HibernateServiceProvider.getSessin();
		Agent agent;
		if (id > 0)
		{
			agent = (Agent) this.getHibernateTemplate()
			    .get(Agent.class, new Long(id));
			return agent;
		}
		else
			return new Agent();
	}

	public Agent queryByName(String name) throws AppException
	{
		Hql hql = new Hql();
		hql.add("from Agent where name=?");
		hql.addParamter(name);
		System.out.println("HQL = " + hql);
		Query query = this.getQuery(hql);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Agent) query.list().get(0);
		else
			return new Agent();
	}

	public Agent getAgentByEmailTempEmail(String loginName) throws AppException
	{
		Hql hql = new Hql();
		hql
		    .add("from Agent a where LOWER(a.loginName)=LOWER(?) or LOWER(a.tempEmail)=LOWER(?)");
		hql.addParamter(loginName);
		hql.addParamter(loginName);
		Query query = this.getQuery(hql);
		if (query != null && query.list().size() > 0)
			return (Agent) query.list().get(0);
		else
		{
			return new Agent();
		}
	}

	public Agent getAgentByEmail(String loginName) throws AppException
	{
		Hql hql = new Hql();
		hql.add("from Agent a where LOWER(a.loginName)=LOWER(?) ");
		hql.addParamter(loginName);
		Query query = this.getQuery(hql);
		if (query != null)
		{
			List list = query.list();
			if (list != null && list.size()>0)
			{
				Agent agent = (Agent) list.get(0);
				return agent;
			}
			return new Agent();
		}
		else
		{
			return new Agent();
		}
	}

	public CertInfo getCertById(long id) throws AppException
	{
		Hql hql = new Hql();
		hql.add("from CertInfo where id =?");
		hql.addParamter(id);
		Query query = this.getQuery(hql);
		if (query != null)
		{
			List list = query.list();
			if (list != null) { return (CertInfo) list.get(0);
			}
		}

		return new CertInfo();

	}

	public Agent getAgentByName(String name) throws AppException
	{
		Query query = this.getQuery("from Agent where name ='" + name + "'");
		List list = query.list();
		if (query != null && query.list() != null && query.list().size() > 0)

			return (Agent) query.list().get(0);
		else
			return new Agent();
	}

	public boolean hasSameUserNo(String agentNo) throws AppException
	{
		Session session = this.getSession();
		String hql = "from Agent where agentNo='" + agentNo + "'";
		List list = session.createQuery(hql).list();
		if (list != null && list.size() > 0)
		{
			releaseSession(session);
			return true;
		}
		else
		{
			releaseSession(session);
			return false;
		}
	}

	public Agent login(String agentNo, String agentPassword) throws AppException
	{
		Query query = this.getSession().createQuery(
		    "from Agent where agentNo='" + agentNo + "' and agentPassword='"
		        + agentPassword + "' and agentStatus=1");
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Agent) query.list().get(0);
		else
			return null;
	}

	public void deleteById(long id) throws AppException
	{
		this.getHibernateTemplate().delete(this.getAgentById(id));
	}

	public List list(AgentListForm alf) throws AppException
	{
		Hql hql = new Hql();
		hql.add(" from Agent a where 1=1");

		if (alf.getAgentName() != null && !alf.getAgentName().equals(""))
		{
			hql.add("  and a.name like ?");
			hql.addParamter("%" + alf.getAgentName().trim() + "%");
		}
		if (alf.getCompanyName() != null && !alf.getCompanyName().equals(""))
		{
			hql.add("  and a.companyName like ?");
			hql.addParamter("%" + alf.getCompanyName().trim() + "%");
		}
		if (alf.getAgentEmail() != null && !"".equals(alf.getAgentEmail()))
		{
			hql.add(" and LOWER(a.loginName) like LOWER(?)");
			hql.addParamter("%" + alf.getAgentEmail().trim() + "%");
		}
		if (alf.getSort() >= 0)
		{
			if (alf.getSort() == 0)
			{
				hql.add(" order by a.name");
			}
			if (alf.getSort() == 1)
			{
				hql.add(" order by a.loginName");
			}
			if (alf.getSort() == 2)
			{
				hql.add(" order by (a.allowBalance+a.notallowBalance+a.creditBalance)");
			}
			if (alf.getSort() == 3)
			{
				hql.add(" order by (a.allowBalance+a.notallowBalance+a.creditBalance) desc");
			}
			
			if (alf.getSort() == 4)
			{
				hql.add(" order by a.allowBalance");
			}
			if (alf.getSort() == 5)
			{
				hql.add(" order by a.allowBalance desc");
			}
			if (alf.getSort() == 6)
			{
				hql.add(" order by a.notallowBalance");
			}
			if (alf.getSort() == 7)
			{
				hql.add(" order by a.notallowBalance desc");
			}
			if (alf.getSort() == 8)
			{
				hql.add(" order by a.creditBalance");
			}
			if (alf.getSort() == 9)
			{
				hql.add(" order by a.creditBalance desc");
			}
			
		}

		System.out.println("hql= " + hql);
		return this.list(hql, alf);
	}

	public void createAgentBalance(String sessionId, int userId, AgentListForm alf)
	    throws AppException
	{
		Timestamp balanceDate;
		if (alf.getDownloadDate() == null || alf.getDownloadDate().equals(""))
			alf.setDownloadDate(com.neza.tool.DateUtil
			    .getDateString("yyyy-MM-dd HH:mm:ss"));

		balanceDate = Timestamp.valueOf(alf.getDownloadDate());

		try
		{
			CallableStatement call = this.getSessionFactory().getCurrentSession()
			    .connection().prepareCall("{Call create_agent_balance(?,?,?)}");
			call.setString("sessionId", sessionId);
			call.setInt("userId", userId);
			call.setTimestamp("balanceDate", balanceDate);
			call.executeUpdate();
		}
		catch (Exception ex)
		{
			System.out.println("execute procedure fails:" + ex.getMessage());
		}
	}

	// 下载列表，不要分页；
	public List listForAllBalance(AgentListForm alf, String sessionId, int userId)
	    throws AppException
	{

		Hql hql = new Hql();

		hql.add("from AgentBalance a where a.sessionId=? and a.userId=?");
		hql.addParamter(sessionId);
		hql.addParamter(userId);

		if (!alf.getAgentName().equals(""))
		{
			hql.add("  and a.agent.name like ?");
			hql.addParamter("%" + alf.getAgentName().trim() + "%");
		}
		if (!alf.getCompanyName().equals(""))
		{
			hql.add("  and a.agent.companyName like ?");
			hql.addParamter("%" + alf.getCompanyName().trim() + "%");
		}
		if (!"".equals(alf.getAgentEmail()))
		{
			hql.add(" and LOWER(a.agent.loginName) like LOWER(?)");
			hql.addParamter("%" + alf.getAgentEmail().trim() + "%");
		}

		hql.add(" order by a.agent.id desc");
		Query query = this.getQuery(hql);
		return query.list();
	}

	// 商户列表
	public List listForBalance(AgentListForm alf, String sessionId, int userId)
	    throws AppException
	{
		Hql hql = new Hql();
		hql.add("from AgentBalance a where a.sessionId=? and a.userId=?");
		hql.addParamter(sessionId);
		hql.addParamter(userId);

		if (alf.getAgentName() != null && !alf.getAgentName().equals(""))
		{
			hql.add("  and a.agent.name like ?");
			hql.addParamter("%" + alf.getAgentName().trim() + "%");
		}
		if (alf.getCompanyName() != null && !alf.getCompanyName().equals(""))
		{
			hql.add("  and a.agent.companyName like ?");
			hql.addParamter("%" + alf.getCompanyName().trim() + "%");
		}
		if (alf.getAgentEmail() != null && !"".equals(alf.getAgentEmail()))
		{
			hql.add(" and LOWER(a.agent.loginName) like LOWER(?)");
			hql.addParamter("%" + alf.getAgentEmail().trim() + "%");
		}
		if (alf.getSort() >= 0)
		{
			if (alf.getSort() == 0)
			{
				hql.add(" order by a.agent.name");
			}
			if (alf.getSort() == 1)
			{
				hql.add(" order by a.agent.loginName");
			}
			if (alf.getSort() == 2)
			{
				hql.add(" order by (a.agent.agent.allowBalance+a.agent.notallowBalance+a.agent.creditBalance)");
			}
			if (alf.getSort() == 3)
			{
				hql.add(" order by (a.agent.allowBalance+a.agent.notallowBalance+a.agent.creditBalance) desc");
			}
			if (alf.getSort() == 4)
			{
				hql.add(" order by a.agent.allowBalance");
			}
			if (alf.getSort() == 5)
			{
				hql.add(" order by a.agent.allowBalance desc");
			}
			if (alf.getSort() == 6)
			{
				hql.add(" order by a.agent.notallowBalance");
			}
			if (alf.getSort() == 7)
			{
				hql.add(" order by a.agent.notallowBalance desc");
			}
			if (alf.getSort() == 8)
			{
				hql.add(" order by a.agent.creditBalance");
			}
			if (alf.getSort() == 9)
			{
				hql.add(" order by a.agent.creditBalance desc");
			}
			
		}
		System.out.println("hql= ========" + hql);
		return this.list(hql, alf);
	}

	public Agent queryByPartner(String partner) throws AppException
	{
		Query query = this.getQuery("from Agent where partner=?");
		query.setString(0, partner);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Agent) query.list().get(0);
		else
			return new Agent();
	}

	public List getAgentList(AgentListForm alf) throws AppException
	{
		Hql hql = new Hql();
		hql.add(" from Agent a where 1=1");
		if (!"".equals(alf.getAgentName()))
		{
			hql.add("  and a.name like ?");
			hql.addParamter("%" + alf.getAgentName().trim() + "%");
		}
		if (!"".equals(alf.getCompanyName()))
		{
			hql.add("  and a.companyName like ?");
			hql.addParamter("%" + alf.getCompanyName().trim() + "%");
		}
		if (!"".equals(alf.getAgentEmail()))
		{
			hql.add(" and LOWER(a.email) like LOWER(?)");
			hql.addParamter("%" + alf.getAgentEmail().trim() + "%");
		}
		if (alf.getBankId() != null && alf.getBankId()!=0)
		{
			hql
			    .add("  and exists(from Account c where c.bankId=? and c.agent.id=a.id and c.bindStatus=2)");
			hql.addParamter(alf.getBankId());
		}

		hql.add("  order by a.id desc");
		System.out.println("hql= " + hql);
		Query query=this.getQuery(hql);
		return query.list();
	}

	public Agent queryByEmailAndPassword(String email, String password)
	    throws AppException
	{
		Query query = this
		    .getQuery("from Agent where LOWER(loginName)=LOWER(?) and password=?");
		query.setString(0, email);
		query.setString(1, password);
		if (query.list() != null && query.list().size() > 0)
			return (Agent) query.list().get(0);
		else
			return new Agent();
	}

	public Agent queryAgentByOrderAndToAgent(long orderId, long agnetId)
	    throws AppException
	{
		Agent agent = null;
		if (orderId > 0)
		{
			Hql hql = new Hql(
			    "from Agent where exists (from Transaction where orderDetails.id=? and toAgent.id=? and type=1)");
			hql.addParamter(new Long(orderId));
			hql.addParamter(new Long(agnetId));
			Query query = this.getQuery(hql);
			if (query != null && query.list() != null && query.list().size() > 0)
			{
				agent = (Agent) query.list().get(0);
			}
		}
		else
			agent = new Agent();
		return agent;
	}

	public Agent querySellerByOrderAndFromAgent(long orderId, long agnetId)
	    throws AppException
	{
		Agent agent = null;
		if (orderId > 0)
		{
			// Hql hql = new Hql("from Agent where exists (from Transaction where
			// orderDetails.id=? and fromAgent.id=? and type=1) order by id");
			Hql hql = new Hql(
			    "from Agent where id in (select toAgent.id from Transaction where orderDetails.id=? and fromAgent.id=? and type=1) order by id");
			hql.addParamter(new Long(orderId));
			hql.addParamter(new Long(agnetId));
			Query query = this.getQuery(hql);
			if (query != null && query.list() != null && query.list().size() > 0)
			{
				agent = (Agent) query.list().get(0);
			}
		}
		else
			agent = new Agent();
		return agent;
	}

	public MailAgent queryMailAgentById(int mailagentId) throws AppException
	{
		Hql hql = new Hql();
		System.out.println("queryMailAgentById   mailagentId---" + mailagentId);
		hql.add("from MailAgent where id=?");
		hql.addParamter(mailagentId);
		Query query = this.getQuery(hql);

		if (query.list() != null && query.list().size() > 0)
			return (MailAgent) query.uniqueResult();
		else
			return new MailAgent();
	}

	public List queryMailAgentBySession(String sessionId) throws AppException
	{
		Hql hql = new Hql();
		hql.add("from MailAgent where sessionId=?");
		hql.addParamter(sessionId);
		Query query = this.getQuery(hql);
		return query.list();
	}

	public List getMailAgents(MailAgentListForm malf, String sessionId)
	    throws AppException
	{

		String hql = "select new MailAgent(m.id,a) from MailAgent m ,Agent a where 1=1";
		if ((malf.getAgentName() != null) && (!malf.getAgentName().equals("")))
			hql += "  and name like '%" + malf.getAgentName() + "%'";

		if ((malf.getAgentEmail() != null) && (malf.getAgentEmail() != ""))
			hql += " and LOWER(loginName) like LOWER('%" + malf.getAgentEmail()
			    + "%')";

		if (!sessionId.equals(""))
			hql += " and m.sessionId ='" + sessionId + "'";

		hql += " and LOWER(m.agentEmail)=LOWER(email)";

		hql = hql + " order by name";

		System.out.println("hql= " + hql);
		List list = this.list(hql, malf);

		return list;
	}

	public void saveMailAgent(String mails, String sessionId) throws AppException
	{
		List mailagents = this.queryMailAgentBySession(sessionId);
		this.getHibernateTemplate().deleteAll(mailagents);
		this.getHibernateTemplate().flush();
		String[] arrMail = mails.split(",");
		for (int i = 0; i < arrMail.length; i++)
		{
			MailAgent ma = new MailAgent();
			ma.setSessionId(sessionId);
			ma.setAgentEmail(arrMail[i]);
			ma.setCreateDate(new Timestamp(System.currentTimeMillis()));
			this.getHibernateTemplate().save(ma);
		}

	}

	// 增加商户余额
	public void addAmount(Agent agent, BigDecimal amount) throws AppException
	{
		agent.setAllowBalance(agent.getAllowBalance().add(amount));
		// agent.setBalance(agent.getBalance().add(amount));
		this.update(agent);
	}

	// 扣除商户余额
	public void deleteAmount(Agent agent, BigDecimal amount) throws AppException
	{
		agent.setAllowBalance(agent.getAllowBalance().subtract(amount));
		// agent.setBalance(agent.getBalance().subtract(amount));
		this.update(agent);
	}
	
	// 增加商户信用余额
	public void addCreditAmount(Agent agent, BigDecimal amount) throws AppException
	{
		agent.setCreditBalance(agent.getCreditBalance().add(amount));
		// agent.setBalance(agent.getBalance().add(amount));
		this.update(agent);
	}

	// 扣除商户信用余额
	public void deleteCreditAmount(Agent agent, BigDecimal amount) throws AppException
	{
		agent.setCreditBalance(agent.getCreditBalance().subtract(amount));
		// agent.setBalance(agent.getBalance().subtract(amount));
		this.update(agent);
	}

	

	// 移动商户从冻结余额到可用余额
	public void moveNotallowBalanceToAllowBalance(Agent agent, BigDecimal amount)
	    throws AppException
	{
		agent.setNotallowBalance(agent.getNotallowBalance().subtract(amount));
		agent.setAllowBalance(agent.getAllowBalance().add(amount));
		this.update(agent);
	}

	// 移动商户从可用余额到冻结余额
	public void moveAllowBalanceToNotallowBalance(Agent agent, BigDecimal amount)
	    throws AppException
	{
		agent.setNotallowBalance(agent.getNotallowBalance().add(amount));
		agent.setAllowBalance(agent.getAllowBalance().subtract(amount));
		this.update(agent);
	}

	// 从冻结金额中扣除提现金额
	public void reduceNotallowBalance(Agent agent, BigDecimal amount)
	    throws AppException
	{
		agent.setNotallowBalance(agent.getNotallowBalance().subtract(amount));
		this.update(agent);
	}

	public void deleteMailAgentsById(int mailagentId) throws AppException
	{
		this.getHibernateTemplate().delete(this.queryMailAgentById(mailagentId));
	}

	public String getBandBankNameByAgentId(long id) throws AppException
	{
		Hql hql = new Hql();
		hql
		    .add("select b.cname from Agent a,Bank b,Account c  where a.id=c.agent.id and c.bank.id =b.id and c.bindStatus=2 and a.id=? ");
		hql.addParamter(id);
		Query query = this.getQuery(hql);
		if (query != null && query.list().size() > 0) { return (String) query
		    .list().get(0); }
		return null;
	}

	public void importAgent()
	{
		Hql hql = new Hql();
		hql.add("select email from temp_agent_x");
		SelectDataBean sdb = new SelectDataBean();
		SelectDataBean sdb1 = new SelectDataBean();
		sdb.setQuerySQL(hql.getSql());
		sdb.executeQuery();

		for (int i = 0; i < sdb.getRowCount(); i++)
		{
			String email = sdb.getColValue(i, "email");
			sdb1.setQuerySQL("select * from temp_agent where email='" + email + "'");
			sdb1.executeQuery();
			if (sdb1.getRowCount() > 0)
			{
				String sql = "";
				sql = "insert into temp_agent_y (cname,name,tel,email) values('"
				    + sdb1.getColValue(1, "cname") + "'";
				sql = sql + ",'" + sdb1.getColValue(1, "name") + "'";
				sql = sql + ",'" + sdb1.getColValue(1, "tel") + "'";
				sql = sql + ",'" + email + "')";
				sdb1.executeUpdateSQL(sql);
			}
		}

	}

	public long getCountAgentCertification() throws AppException
	{
		String hql = "select count(id) from Draw where type=1 and status not in (3,4)";
		Query query = this.getQuery(hql);
		if (query != null && query.list().size() > 0)
			return Long.parseLong(query.uniqueResult().toString());
		else
			return 0;
	}

	public AgentBalance getAgentBalance(long agentId, String sessionId, int userId)
	    throws AppException
	{
		this.createAgentBalance(sessionId, userId, new AgentListForm());
		Hql hql = new Hql();
		hql
		    .add("from AgentBalance a where a.sessionId=? and a.userId=? and a.agent.id = ?");
		hql.addParamter(sessionId);
		hql.addParamter(userId);
		hql.addParamter(agentId);
		System.out.println(hql);
		Query query = this.getQuery(hql);
		AgentBalance agentBalance = new AgentBalance();
		if (query != null && query.list() != null && query.list().size() > 0)
		{
			agentBalance = (AgentBalance) query.list().get(0);
		}
		return agentBalance;
	}
	
	public AgentBalance getAgentBalance(long agentId,String date) throws AppException
	{
        
        String tempDate=date.substring(0,10);
		String temp = "to_date('"+tempDate+"','yyyy-MM-dd')";
		Hql hql = new Hql();

		hql.add("select cal_curr_bal_by_date(" + agentId + "," + temp + ")as curr,");
		hql.add("cal_notallow_bal_by_date(" + agentId + "," + temp + ")as notallow,");
		hql.add("cal_credit_bal_by_date(" + agentId + "," + temp + ")");
		hql.add("as credit from dual");

		Query query = this.getSessionFactory().openSession().createSQLQuery(
		    hql.getSql()).addScalar("curr", Hibernate.BIG_DECIMAL).addScalar("notallow",
		    Hibernate.BIG_DECIMAL).addScalar("credit", Hibernate.BIG_DECIMAL);
		if (query != null)
		{
			Object[] bals =(Object[]) query.uniqueResult();
			if(bals.length==3)
			return new AgentBalance(agentId,new BigDecimal(bals[0].toString()),new BigDecimal(bals[1].toString()),new BigDecimal(bals[2].toString()));
		}
	 
		return new AgentBalance(agentId,new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"));
	}
	
	
	public AgentBalance getAgentBalance(long agentId) throws AppException
	{

		String temp = "to_date('3000-01-01','yyyy-MM-dd')";
		Hql hql = new Hql();

		hql.add("select cal_curr_bal_by_date(" + agentId + "," + temp + ")as curr,");
		hql.add("cal_notallow_bal_by_date(" + agentId + "," + temp + ") as notallow,");
		hql.add("cal_credit_bal_by_date(" + agentId + "," + temp + ")");
		hql.add("as credit from dual");

		Query query = this.getSessionFactory().openSession().createSQLQuery(
		    hql.getSql()).addScalar("curr", Hibernate.BIG_DECIMAL).addScalar("notallow",
		    Hibernate.BIG_DECIMAL).addScalar("credit", Hibernate.BIG_DECIMAL);
		if (query != null)
		{
			Object[] bals =(Object[]) query.uniqueResult();
			if(bals.length==3)
			return new AgentBalance(agentId,new BigDecimal(bals[0].toString()),new BigDecimal(bals[1].toString()),new BigDecimal(bals[2].toString()));
		}
	 
		return new AgentBalance(agentId,new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"));
	}
	

	
	
	
	public void _synallowBalance(Agent agent) throws AppException
	{
		Hql hql = new Hql();

		hql.add("update Agent ");
		hql.add("set allow_balance  = cal_curr_bal_by_date(?,  sysdate),");
		hql.add("notallow_balance = cal_notallow_bal_by_date(?,  sysdate),");
		hql.add(" credit_balance = cal_credit_bal_by_date(?,  sysdate)");
		hql.add(" where id = ?");
		hql.addParamter(agent.getId());
		hql.addParamter(agent.getId());
		hql.addParamter(agent.getId());
		hql.addParamter(agent.getId());
		this.getQuery(hql).executeUpdate();
	}
	
	public void synallowBalance(Agent agent) throws AppException
	{
		Session session = this.getSessionFactory().getCurrentSession();

		// Transaction tx= session.beginTransaction();
		try
		{
			Connection con = session.connection();
			con.setAutoCommit(true);
			CallableStatement call = con.prepareCall("{Call S_B(?)}");
			call.setInt("agentId", (int) agent.getId());
			call.execute();
			call.close();
			con.commit();
			con.close();

			/*
			 * Connection con = session.connection();
			 * 
			 * String procedure = "{Call S_B(?)}"; CallableStatement cstmt =
			 * con.prepareCall(procedure); cstmt.setInt("agentId", (int)
			 * agent.getId()); cstmt.executeUpdate(); tx.commit();
			 */

		}
		catch (Exception ex)
		{
			System.out
			    .println("---------------------------同步余额失败：" + ex.getMessage());

			/*
			 * if (tx != null) { tx.rollback(); }
			 */

		}

	}

	public BigDecimal getAgentAllowBalance(long agentId) throws AppException
	{
		Hql hql = new Hql("select cal_curr_bal_by_date("+agentId+",to_date('3000-01-01','yyyy-MM-dd')) as bal from dual");
		
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(hql.getSql()).addScalar("bal",Hibernate.BIG_DECIMAL);
		if (query != null)
		{
			BigDecimal balance=(BigDecimal)query.uniqueResult();
			if (balance != null) { return balance; }
		}
		return new BigDecimal("0");
	}
	
	public BigDecimal getAgentNotAllowBalance(long agentId) throws AppException
	{
		Hql hql = new Hql("select cal_notallow_bal_by_date("+agentId+",to_date('3000-01-01','yyyy-MM-dd')) as bal from dual");
		
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(hql.getSql()).addScalar("bal",Hibernate.BIG_DECIMAL);
		if (query != null)
		{
			BigDecimal balance=(BigDecimal)query.uniqueResult();
			if (balance != null) { return balance; }
		}
		return new BigDecimal("0");
	}
	
	public BigDecimal getAgentCreditBalance(long agentId) throws AppException
	{
       Hql hql = new Hql("select cal_credit_bal_by_date("+agentId+",to_date('3000-01-01','yyyy-MM-dd')) as bal from dual");
		
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(hql.getSql()).addScalar("bal",Hibernate.BIG_DECIMAL);
		if (query != null)
		{
			BigDecimal balance=(BigDecimal)query.uniqueResult();
			if (balance != null) { return balance; }
		}
		return new BigDecimal("0");
	}

	public List getExceptionalAgents() throws AppException {
		// TODO Auto-generated method stub
//		Hql hql = new Hql();
//		hql.add("select new com.fordays.fdpay.agent.AgentException(a.id,a.email,");
//		hql.add("select new com.fordays.fdpay.agent.AgentException(");
//		hql.add("cal_curr_bal_by_date(a.id,sysdate)");
//		hql.add("cal_notallow_bal_by_date(a.id,sysdate),");
//		hql.add("cal_credit_bal_by_date(a.id,sysdate)");
//		hql.add(")");
//		hql.add("from Agent a");
		//hql.add("where rownum<=3 ");
	//	hql.add("and exists ( from Transaction t where (sysdate-t.account_date)*24<1 and a.id=t.fromAgent.id)");
		
//		Query query = this.getQuery(hql);
//		if(query!=null&&query.list().size()>0){
//			System.out.println(query.list().size());
//		return query.list();
//		}
//		else
//			return null;
		
//		Hql hql = new Hql();
//		hql.add("select * from (");
//			hql.add("select a.id,a.email,a.name,");
//			hql.add("cal_curr_bal_by_date(a.id,sysdate) as  allowBalance,");
//			hql.add("cal_notallow_bal_by_date(a.id,sysdate) as  notallow,");
//			hql.add("cal_credit_bal_by_date(a.id,sysdate) as  credirtallow");
//			hql.add("from agent a where rownum<=3");
//			hql.add(" and exists (select * from Transaction t where (sysdate-account_date)*24<1 and a.id=t.from_agent_id)");
//		hql.add(")");
//		hql.add(" newagent where newagent.allowBalance<2000 or newagent.notallow<2000 or newagent.credirtallow<2000");
//		SelectDataBean sdb = new SelectDataBean();
//		sdb.setQuerySQL(hql.getSql());
//		sdb.executeQuery();
//		System.out.println(sdb.getRowCount());
//		List list = new ArrayList();
//		for(int i=1;i<=sdb.getRowCount();i++){
//			AgentException ae = new AgentException();
//			ae.setId(Long.parseLong(sdb.getColValue(i,"id")));
//			ae.setEmail(sdb.getColValue(i,"email"));
//			ae.setName(sdb.getColValue(i,"name"));
//			ae.setAllowBalance(new BigDecimal(sdb.getColValue(i,"allowBalance")));
//			ae.setNotallowBalance(new BigDecimal(sdb.getColValue(i,"notallow")));
//			ae.setCreditBalance(new BigDecimal(sdb.getColValue(i,"credirtallow")));
//			list.add(ae);
//		}
//		if(list.size()>0){
//			return list;
//		}
//		return null;
		
		Session session = this.getSessionFactory().getCurrentSession();

		// Transaction tx= session.beginTransaction();
		try
		{
			Connection con = session.connection();
			con.setAutoCommit(true);
			CallableStatement call = con.prepareCall("{Call S_A_E(?)}");
			call.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
		 	 call.execute();
		 	ResultSet rs=(ResultSet) call.getObject(1);
		 	List list = new ArrayList();
			while(rs.next()){
				AgentException ae = new AgentException();
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("email"));
//				System.out.println(rs.getString("ALLTALLOW"));
//				System.out.println(rs.getString("NOTALLOW"));
//				System.out.println(rs.getString("credirtallow"));
				ae.setId(Long.parseLong(rs.getString("id")));
				ae.setEmail(rs.getString("email"));
				ae.setName(rs.getString("name"));
				ae.setAllowBalance(new BigDecimal(rs.getString("ALLTALLOW")));
				ae.setNotallowBalance(new BigDecimal(rs.getString("NOTALLOW")));
				ae.setCreditBalance(new BigDecimal(rs.getString("credirtallow")));
				list.add(ae);
			}
			rs.close();
			call.close();
			con.commit();
			con.close();
			if(list.size()>0){
				return list;
			}
			System.out.println("没有异常!");
			return null;
			/*
			 * Connection con = session.connection();
			 * 
			 * String procedure = "{Call S_B(?)}"; CallableStatement cstmt =
			 * con.prepareCall(procedure); cstmt.setInt("agentId", (int)
			 * agent.getId()); cstmt.executeUpdate(); tx.commit();
			 */

		}
		catch (Exception ex)
		{
			System.out
			    .println("---------------------------读取失败：" + ex.getMessage());

			/*
			 * if (tx != null) { tx.rollback(); }
			 */

		}
		
		return null;
	}
	public List getRepeatCharge(int hour) throws AppException {
	Session session = this.getSessionFactory().getCurrentSession();
	Connection con =session.connection();
	try {
		con.setAutoCommit(true);
		CallableStatement call = con.prepareCall("{Call Q_M_W(?,?)}");
		call.setInt(1,hour);
		call.registerOutParameter(2,oracle.jdbc.OracleTypes.CURSOR);
		call.execute();
		ResultSet rs=(ResultSet) call.getObject(2);
	 	List list = new ArrayList();
		while(rs.next()){
			RepeatCharge rc = new RepeatCharge();
//			System.out.println(rs.getString("name"));
//			System.out.println(rs.getString("email"));
			rc.setAgentEmail(rs.getString("email"));
			rc.setAgentName(rs.getString("name"));
			rc.setOrderId(rs.getString("order_Id"));
			rc.setAmount(rs.getString("amount"));
			rc.setOrderNo(rs.getString("order_No"));
			rc.setMark(rs.getString("mark"));
			rc.setRepeatCount(Integer.parseInt(rs.getString("dd")));
			list.add(rc);
		}
		rs.close();
		call.close();
		con.commit();
		con.close();
		if(list.size()>0){
			return list;
		}
		System.out.println("没有异常!");
		return null;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
}
