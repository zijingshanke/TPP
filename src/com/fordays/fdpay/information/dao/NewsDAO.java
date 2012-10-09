package com.fordays.fdpay.information.dao;

import java.util.List;

import com.fordays.fdpay.information.News;
import com.fordays.fdpay.information.NewsListForm;
import com.neza.exception.AppException;

public interface NewsDAO {
	public List getNews(NewsListForm clf)throws AppException;
	public News getNewsById(long id)throws AppException;
	public long updateInfo(News news)throws AppException;
	public long save(News news)throws AppException;
	public void deleteNewsById(int id)throws AppException;
}
