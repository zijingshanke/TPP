package com.fordays.fdpay.information.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.fordays.fdpay.information.News;
import com.fordays.fdpay.information.NewsListForm;
import com.fordays.fdpay.information.biz.NewsBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class NewsListAction extends DispatchAction {
	private NewsBiz newsBiz;

	public NewsBiz getNewsBiz() {
		return newsBiz;
	}

	public void setNewsBiz(NewsBiz newsBiz) {
		this.newsBiz = newsBiz;
	}

	public SysUser getUserByURI(HttpServletRequest request){
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute("URI");
		if(uri!=null&&uri.getUser()!=null)
			return uri.getUser();
		else{
			return null;
		}
	}
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		NewsListForm nlf = (NewsListForm) form;
		if (nlf == null)
			nlf = new NewsListForm();
		
		
		List list = newsBiz.getNews(nlf);
		if(nlf.getSelectedItems()!=null){
			int [] items=new int[nlf.getSelectedItems().length];
			for(int i=0;i<nlf.getSelectedItems().length;i++){
				items[i]=0;
			}
			nlf.setSelectedItems(items);
		}
		nlf.setList(list);
		request.setAttribute("nlf", nlf);
		forwardPage = "listInformation";
		return mapping.findForward(forwardPage);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException
	{
		String forwardPage="";
		SysUser user = this.getUserByURI(request);
		if(user!=null){
			News news = new News();
			news.setThisAction("insert");
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("news", news);
			forwardPage = "editNews";
			
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		SysUser user = this.getUserByURI(request);
		String forwardPage = "";
		News news=null;
		if(user!=null){
			NewsListForm nlf = (NewsListForm) form;
			int id = 0;
			if (nlf.getSelectedItems().length > 0) {
				id = nlf.getSelectedItems()[0];
			} else
				id = nlf.getId();
			if (id > 0) {
			    news =  newsBiz.getNewsById(id);
			    news.setThisAction("update");
				request.setAttribute("news", news);
			} else
				request.setAttribute("news", new News());
			request.setAttribute("userName",user.getUserName());
			forwardPage = "editNews";
		}else{
			request.getSession().invalidate();
			return mapping.findForward("exit");
		}
		return (mapping.findForward(forwardPage));
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws AppException
		{
		NewsListForm nlf = (NewsListForm) form;
		
			String forwardPage = "";
			int id = 0;
			Inform inf = new Inform();
				try
				{
					for (int i = 0; i < nlf.getSelectedItems().length; i++)
					{
						id = nlf.getSelectedItems()[i];
						if (id > 0){
								newsBiz.deleteNewsById(id);
								inf.setMessage("您已经成功删除了新闻！");
						}
					}
					inf.setForwardPage("/information/newslist.do");
					inf.setParamId("thisAction");
					inf.setParamValue("list");
				}
				catch (Exception ex)
				{
					inf.setMessage("删除新闻出错！错误信息是：" + ex.getMessage());
					inf.setBack(true);
				}
			
			request.setAttribute("inf", inf);
			forwardPage = "inform";
			return (mapping.findForward(forwardPage));
		}
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		String forwardPage = "";
		int id=Integer.parseInt(request.getParameter("id"));
		
			News news = (News) newsBiz.getNewsById(id);
			
			news.setThisAction("view");
			request.setAttribute("news", news);
			
		
		forwardPage = "viewNews";
		return (mapping.findForward(forwardPage));
	}
}
