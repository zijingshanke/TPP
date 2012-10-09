package com.fordays.fdpay.information.dao;

import java.util.List;

import org.hibernate.Query;

import com.fordays.fdpay.information.News;
import com.fordays.fdpay.information.NewsListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;
public class NewsDAOImpl extends BaseDAOSupport implements NewsDAO{

	public List getNews(NewsListForm clf) throws AppException {
		Hql hql=new Hql();
		hql.add("from News n where 1=1");
		if(clf!=null&&!"".equals(clf.getTitle()))
		{
			hql.add(" and n.title like ?");
			hql.addParamter("%"+clf.getTitle().trim()+"%");
		}
		if(clf!=null&&!"".equals(clf.getUserName()))
		{
			hql.add(" and n.userName like ?");
			hql.addParamter("%"+clf.getUserName().trim()+"%");
		}
		
		hql.add(" order by n.createDate desc");
		System.out.println("hql==="+hql);
		return this.list(hql, clf);
	}

	public News getNewsById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from News where id=?");
		hql.addParamter(id);
		System.out.println("HQL = " + hql);
		Query query = this.getQuery(hql);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (News) query.list().get(0);
		else
			return new News();
	}

	public long updateInfo(News news) throws AppException {
		
		if (news.getId() > 0)
			return save(news);
		else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public long save(News news) throws AppException {
		this.getHibernateTemplate().saveOrUpdate(news);
		return news.getId();
	}

	public void deleteNewsById(int id) throws AppException {
		this.getHibernateTemplate().delete(this.getNewsById(id));
	}
}
