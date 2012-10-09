package com.fordays.fdpay.information.biz;

import java.util.List;

import com.fordays.fdpay.information.News;
import com.fordays.fdpay.information.NewsListForm;
import com.fordays.fdpay.information.dao.NewsDAO;
import com.neza.exception.AppException;

public class NewsBizImpl implements NewsBiz{
 private NewsDAO newsDAO;
	public List getNews(NewsListForm clf) throws AppException {
		return newsDAO.getNews(clf);
	}
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	public News getNewsById(long id) throws AppException {
		
		return newsDAO.getNewsById(id);
	}
	public long updateInfo(News news) throws AppException {
		return newsDAO.updateInfo(news);
	}
	public long save(News news) throws AppException {
		return newsDAO.save(news);
	}
	public void deleteNewsById(int id) throws AppException {
		newsDAO.deleteNewsById(id);
	}

}
