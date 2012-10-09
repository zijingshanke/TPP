package com.fordays.fdpay.system.dao;

import java.util.List;

import org.hibernate.Query;

import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.CityListForm;
import com.neza.base.BaseDAOSupport;
import com.neza.base.Hql;
import com.neza.exception.AppException;

public class CityDAOImpl extends BaseDAOSupport implements CityDAO {
	public CityDAOImpl() {

	}

	public List getCityByProvinceId(int provinceId) throws AppException {
		Hql hql = new Hql("from City where province.id=?");
		hql.addParamter(provinceId);
		Query query = this.getQuery(hql);
		return query.list();
	}

	public City getCityByCName(String cname) throws AppException {
		Hql hql = new Hql("from City where cname=?");
		hql.addParamter(cname);
		Query query = this.getQuery(hql);
		City city = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			city = (City) query.list().get(0);
		}
		return city;
	}

	public City getCityById(Long cityId) throws AppException {
		Hql hql = new Hql("from City where id=?");
		hql.addParamter(cityId);
		Query query = this.getQuery(hql);
		City city = null;
		if (query != null && query.list() != null && query.list().size() > 0) {
			city = (City) query.list().get(0);
		}
		return city;
	}

	public List getCitys(CityListForm clf) throws AppException {
		Hql hql = new Hql("from City where 1=1 ");
		if (clf.getProvinceId() > 0) {
			hql.add(" and province.id=?");
			hql.addParamter(clf.getProvinceId());
		}

		return this.list(hql, clf);
	}

	public void save(City city) throws AppException {
		this.getHibernateTemplate().save(city);

	}

	public void deleteById(long id) throws AppException {
		if (id > 0) {
			City city = (City) this.getHibernateTemplate().get(City.class,
					new Long(id));
			this.getHibernateTemplate().delete(city);

		}
	}

}
