package com.fordays.fdpay.information.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.information.News;
import com.fordays.fdpay.information.biz.NewsBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class NewsAction extends BaseAction{
	private News news;
	private NewsBiz newsBiz;
	
	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
			String forwardPage = "";
			News news = (News) form;
			Inform inf = new Inform();
				try
				{	
					News tempNews =  newsBiz.getNewsById(news.getId());
					
					tempNews.setContent(news.getContent());
					tempNews.setTitle(news.getTitle());
					tempNews.setRank(news.getRank());
					tempNews.setStatus(news.getStatus());
					tempNews.setCreateDate(new Timestamp(System.currentTimeMillis()));
					newsBiz.updateInfo(tempNews);
					request.setAttribute("news", tempNews);
	
					inf.setMessage("您已经成功更新了新闻！");
					inf.setForwardPage("/information/newslist.do");
					inf.setParamId("thisAction");
					inf.setParamValue("list");
	
				}
				catch (Exception ex)
				{
					inf.setMessage("更新新闻出错！错误信息是：" + ex.getMessage());
					inf.setBack(true);
				}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
			SysUser user = this.getUserByURI(request);
			String forwardPage = "";
			News news=(News)form;
			Inform inf = new Inform();
			if(user!=null){
				try
				{
					News tempNews = new News();
					tempNews.setCreateDate(new Timestamp(System.currentTimeMillis()));
					tempNews.setContent(news.getContent());
					tempNews.setTitle(news.getTitle());
					tempNews.setUserName(user.getUserName());
					tempNews.setRank(news.getRank());
					tempNews.setStatus(news.getStatus());
					tempNews.setReadNum(new Long(0));
					newsBiz.save(tempNews);
					request.setAttribute("news", tempNews);
	
					inf.setMessage("您已经成功添加了新闻！");
					inf.setForwardPage("/information/newslist.do");
					inf.setParamId("thisAction");
					inf.setParamValue("list");
				}catch (Exception ex)
				{
					inf.setMessage("添加新闻出错！错误信息是：" + ex.getMessage());
					inf.setBack(true);
				}
			}else{
				request.getSession().invalidate();
				return mapping.findForward("exit");
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
	public NewsBiz getNewsBiz() {
		return newsBiz;
	}
	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
}
