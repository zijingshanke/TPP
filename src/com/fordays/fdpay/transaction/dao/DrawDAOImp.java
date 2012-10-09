package com.fordays.fdpay.transaction.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;
import com.neza.utility.ArrayUtil;

public class DrawDAOImp extends BaseDAOSupport implements DrawDAO {
	private TransactionTemplate drawTemplate;

	/**
	 * @获取向银行发送的提现订单
	 * @param String
	 * @param int[]
	 */
	public List getToBankDrawList(String bank, int[] drawlistid)
			throws AppException {
		String temp = ArrayUtil.getSqlStrOfArray(drawlistid);// 转换成形如：(210,211)
//		System.out.println("bank:" + bank + "-temp --" + temp);
		Hql hql = new Hql();
		hql.add("select new com.fordays.fdpay.transaction.DrawList(d,a,c,p)");
		hql.add(" from Draw d,Agent a,City c,Province p ");
		hql.add(" where d.status=2 and d.bank=? ");
		hql.add(" and d.id in " + temp);
		hql.add(" and d.agent.id=a.id ");
		hql.add(" and d.city.id=c.id and p.id=c.province.id ");
		hql.addParamter(bank);

//		System.out.println("---获取向银行发送的提现订单hql==>" + hql.toString());
		Query query = this.getQuery(hql);
		List list = query.list();
//		System.out.println("list:" + list.size());
		return list;
	}

	// /**
	// * @获取向银行发送的提现订单
	// * @param String
	// */
	// public List getToBankDrawList(String bank) throws AppException {
	// Hql hql = new Hql();
	// hql.add("select new com.fordays.fdpay.transaction.DrawList(d,a,m,c,p)");
	// hql.add(" from Draw d,Agent a,Account m,City c,Province p");
	// hql.add(" where d.status=2 and d.bank=?");
	// hql.add(" and d.agent.id=a.id and a.id=m.agent.id ");
	// hql.add(" and m.bindStatus=2 ");
	// hql.add(" and c.id=m.city.id and p.id=c.province.id");
	// hql.addParamter(bank);
	//
	// // System.out.println("hql=" + hql.toString());
	// Query query = this.getQuery(hql);
	// List list = query.list();
	// return list;
	// }

	public long save(Draw draw) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(draw);
		return draw.getId();
	}

	public long merge(Draw draw) throws AppException {
		this.getHibernateTemplate().merge(draw);
		return draw.getId();
	}

	public long update(Draw draw) throws AppException {
		if (draw.getId() > 0)
			return save(draw);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			Draw draw = (Draw) this.getHibernateTemplate().get(Draw.class,
					new Long(id));
			this.getHibernateTemplate().delete(draw);
		}
	}

	public Draw getDrawById(long id) throws AppException {
		Draw draw;
		if (id > 0) {
			draw = (Draw) this.getHibernateTemplate().get(Draw.class,
					new Long(id));
			return draw;

		} else
			return new Draw();
	}

	public Draw queryDrawById(long id) throws AppException {

		if (id > 0) {
			Draw draw = (Draw) this.getQuery("from Draw where id=" + id)
					.uniqueResult();
			if (draw != null) {
				return draw;
			} else {
				return new Draw();
			}

		} else
			return new Draw();
	}

	public List list(DrawListForm tlf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Draw tat where 1=1");

		if (!"".equals(tlf.getAgentName())) {
			hql
					.add(" and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
			hql.addParamter("%" + tlf.getAgentName().trim() + "%");
			hql.addParamter("%" + tlf.getAgentName().trim() + "%");
		}
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}

		if (tlf.getOrderNo() != null && !"".equals(tlf.getOrderNo())) {
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + tlf.getOrderNo().trim() + "%");
		}

		hql.add(" and(  tat.amount >= ? and tat.amount <= ? )");
		hql.addParamter(tlf.getMinAmount());
		hql.addParamter(tlf.getMaxAmount());

		if ("".equals(tlf.getBank()) == false && !"0".equals(tlf.getBank())
				&& !"OTHER".equals(tlf.getBank())) {
			hql.add(" and tat.bank=?");
			hql.addParamter(tlf.getBank().trim());
		} else {
			hql.add(" and tat.bank<>'OTHER'");
		}
		if (tlf.getStatus() != 99) {
			hql.add(" and tat.status = ?");
			hql.addParamter(tlf.getStatus());
		}
		if (tlf.getType() != 99) {
			hql.add(" and tat.type=?");
			hql.addParamter(tlf.getType());
		}
		hql.add(" order by tat.requestDate desc");
		System.out.println("hql===========" + hql);
		return this.list(hql, tlf);
	}

	public Hql subtractHql(DrawListForm tlf) {
		Hql hql = new Hql();
		hql.add("from Draw tat where 1=1");

		if (!"".equals(tlf.getAgentName())) {
			hql
					.add(" and (tat.agent.name like ? or LOWER(tat.agent.loginName) like LOWER(?))");
			hql.addParamter("%" + tlf.getAgentName().trim() + "%");
			hql.addParamter("%" + tlf.getAgentName().trim() + "%");
		}
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}

		hql.add(" and(  tat.amount >= ? and tat.amount <= ? )");
		hql.addParamter(tlf.getMinAmount());
		hql.addParamter(tlf.getMaxAmount());

		if (tlf.getOrderNo() != null && !"".equals(tlf.getOrderNo())) {
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + tlf.getOrderNo().trim() + "%");
		}

		hql.add(" and tat.bank='OTHER'");
		if (tlf.getStatus() != 99) {
			hql.add(" and tat.status = ?");
			hql.addParamter(tlf.getStatus());
		}
		hql.add(" order by tat.requestDate desc");
		System.out.println("hql===========" + hql);
		return hql;
	}

	public List listSubtract(DrawListForm tlf) throws AppException {
		Hql hql = subtractHql(tlf);
		return this.list(hql, tlf);
	}

	public List getAllSubtract(DrawListForm tlf) throws AppException {
		Hql hql = subtractHql(tlf);
		List list = this.getQuery(hql).list();
		return list;
	}

	public Draw getDrawByOrderNo(String orderNo) throws AppException {
		Hql hql = new Hql();
		hql.add("from Draw tat where tat.orderNo=?");
		hql.addParamter(orderNo);
		Draw draw = (Draw) this.getQuery(hql).uniqueResult();
		if (draw != null)
			return draw;
		else
			return new Draw();
	}

	public List getCountByDrawList() throws AppException {
		Hql hql = new Hql();
		hql.add("select new com.fordays.fdpay.transaction.TradingStatistics(");
		hql.add("count(*),");
		hql.add("sum(amount),");
		hql.add("d.status,");
		hql.add("d.bank");
		hql.add(")");
		hql.add("from  Draw d group by d.status,d.bank order by status");
		System.out.println(hql);
		Query query = this.getQuery(hql);
		List list = query.list();
		return list;
	}

	// 查询提现列表(数据分页)
	public List getDrawsInfo(DrawListForm dlf) throws AppException {
		Hql hql = getDrawHqlOfListForm(dlf);
		return this.list(hql, dlf);
	}

	// 查询提现列表(一次取出全部)
	public List getAllDrawsInfo(DrawListForm dlf) throws AppException {
		Hql hql = getDrawHqlOfListForm(dlf);
		List list = this.getQuery(hql).list();
		return list;
	}

	public Hql getDrawHqlOfListForm(DrawListForm dlf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Draw tat where tat.status=?");
		hql.addParamter(dlf.getStatus());

		if (!"".equals(dlf.getAgentName().trim())) {
			hql.add(" and tat.agent.name like ?");
			hql.addParamter("%" + dlf.getAgentName().trim() + "%");
		}

		if (!"".equals(dlf.getBank()) && !"99".equals(dlf.getBank())) {
			hql.add(" and tat.bank=?");
			hql.addParamter(dlf.getBank().trim());
			// System.out.println("--getDrawHqlOfListForm--bank=" +
			// dlf.getBank());
		}

		if (dlf.getType() < 99) {
			hql.add(" and tat.type=?");
			hql.addParamter(dlf.getType());
		}
		if (dlf.getOrderNo() != null && !"".equals(dlf.getOrderNo())) {
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + dlf.getOrderNo().trim() + "%");
		}
		String beginDate = dlf.getBeginDate();
		String endDate = dlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}
		hql.add("  order by tat.check1Date desc");
		// System.out.println("--getDrawHqlOfListForm--hql=" + hql);
		return hql;
	}

	public void setDrawManager(PlatformTransactionManager drawManager) {
		this.drawTemplate = new TransactionTemplate(drawManager);
	}

	public List getAllDrawList(DrawListForm tlf) throws AppException {
		Hql hql = new Hql();
		hql.add("from Draw tat where 1=1");

		if (!"".equals(tlf.getAgentName())) {
			hql.add(" and tat.agent.name like ?");
			hql.addParamter("%" + tlf.getAgentName().trim() + "%");
		}
		String beginDate = tlf.getBeginDate();
		String endDate = tlf.getEndDate();
		if ("".equals(beginDate) == false && "".equals(endDate) == true) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') > ?");
			hql.addParamter(beginDate);
		}
		if ("".equals(beginDate) == true && "".equals(endDate) == false) {
			hql
					.add(" and to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss') < ?");
			hql.addParamter(endDate);
		}
		if ("".equals(beginDate) == false && "".equals(endDate) == false) {
			hql
					.add(" and  to_char(tat.requestDate,'yyyy-mm-dd hh24:mi:ss')  between ? and ? ");
			hql.addParamter(beginDate);
			hql.addParamter(endDate);
		}
		if (tlf.getOrderNo() != null && !"".equals(tlf.getOrderNo())) {
			hql.add(" and tat.orderNo like ?");
			hql.addParamter("%" + tlf.getOrderNo().trim() + "%");
		}
		hql.add(" and(  tat.amount >= ? and tat.amount <= ? )");
		hql.addParamter(tlf.getMinAmount());
		hql.addParamter(tlf.getMaxAmount());

		if ("".equals(tlf.getBank()) == false && !"0".equals(tlf.getBank())
				&& !"OTHER".equals(tlf.getBank())) {
			hql.add(" and tat.bank=?");
			hql.addParamter(tlf.getBank().trim());
		} else {
			hql.add(" and tat.bank<>'OTHER'");
		}
		if (tlf.getStatus() != 99) {
			hql.add(" and tat.status = ?");
			hql.addParamter(tlf.getStatus());
		}
		if (tlf.getType() != 99) {
			hql.add(" and tat.type=?");
			hql.addParamter(tlf.getType());
		}
		hql.add(" order by tat.requestDate desc");
		// System.out.println("hql===========" + hql);

		Query query = this.getQuery(hql);
		return query.list();
	}
}
