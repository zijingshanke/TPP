package com.fordays.fdpay.agent.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;


import com.fordays.fdpay.agent.AgentBalancePointListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.database.SelectDataBean;
import com.neza.exception.AppException;

public class AgentBalancePointDAOImp extends BaseDAOSupport implements AgentBalancePointDAO {
	
	
	//把agent表中字段 添加到 agent_balance_point 中

	public void addAgentToAgentBalancePoint() throws AppException
	{
		try
		{
			Connection con = super.getHibernateTemplate().getSessionFactory()
			    .getCurrentSession().connection();
			con.setAutoCommit(true);
			CallableStatement call = con.prepareCall("{Call S_P_A}");
			call.execute();
			call.close();
			con.commit();
			con.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	//清空表中数据
	public boolean truncateAgentBalancePoint() throws AppException
	{
		boolean flg=false;
		String sql="Truncate Table agent_balance_point";
	   try {
		SelectDataBean sdb1 = new SelectDataBean();
		sdb1.executeUpdateSQL(sql);
				
		flg=true;
		} catch (Exception e) {
			flg=false;
			e.printStackTrace();
		}
		System.out.println("hql= " + sql);
		return flg;
	}
	
	
	
	public void updateAgentBalancePointAmount(String date) throws AppException
	{
		
		
		String sql="select agent_id from agent_balance_point";
		SelectDataBean sdb = new SelectDataBean();
		sdb.setQuerySQL(sql);
		sdb.executeQuery();
		System.out.println(sql);
	
		for (int i = 1; i <= sdb.getRowCount(); i++)
		{
			String agent_idtmp = sdb.getColValue(i, "agent_id");
			System.out.println(i+"----------"+agent_idtmp);
		Hql hql = new Hql();
	    String tempDate=date.substring(0,19);
		String temp = "to_date('"+tempDate+"','yyyy-MM-dd hh24:mi:ss')";
		hql.add("update AgentBalancePoint ");
		hql.add("set allow_balance  = cal_curr_bal_by_date("+agent_idtmp+", "+temp+"),");
		hql.add("not_allow_balance = cal_notallow_bal_by_date("+agent_idtmp+", "+temp+"),");
		hql.add(" credit_balance = cal_credit_bal_by_date("+agent_idtmp+", "+temp+"),");
		hql.add(" balance_date = "+temp+"");
		hql.add(" where agent_id = "+agent_idtmp+"");
		System.out.println("hql= " + hql);
		this.getQuery(hql).executeUpdate();
		}
	}
	
	
	
	public List listAgentBalancePoint(AgentBalancePointListForm alf) throws AppException
	{
		Hql hql = new Hql();
		hql.add(" from AgentBalancePoint a where 1=1");

	if (alf.getAgentName() != null && !alf.getAgentName().equals(""))
		{
			hql.add("  and a.name like ?");
			hql.addParamter("%" + alf.getAgentName().trim() + "%");
		}
	
		if (alf.getAgentEmail() != null && !"".equals(alf.getAgentEmail()))
		{
			hql.add(" and LOWER(a.email) like LOWER(?)");
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
				hql.add(" order by a.email");
			}
			if (alf.getSort() == 2)
			{
				hql.add(" order by (a.allowBalance+a.notAllowBalance+a.creditBalance)");
			}
			if (alf.getSort() == 3)
			{
				hql.add(" order by (a.allowBalance+a.notAllowBalance+a.creditBalance) desc");
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
				hql.add(" order by a.notAllowBalance");
			}
			if (alf.getSort() == 7)
			{
				hql.add(" order by a.notAllowBalance desc");
			}
			if (alf.getSort() == 8)
			{
				hql.add(" order by a.creditBalance");
			}
			if (alf.getSort() == 9)
			{
				hql.add(" order by a.notAllowBalance desc");
			}
		}

		System.out.println("hql= " + hql);
		return this.list(hql, alf);
	}


}
