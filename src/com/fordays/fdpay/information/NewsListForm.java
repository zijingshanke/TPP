package com.fordays.fdpay.information;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.neza.base.ListActionForm;

public class NewsListForm extends ListActionForm{
	private static final long serialVersionUID = 1L;
	private String title="";
	private String content="";
	private Long status=new Long(0);
	private Long rank=new Long(0);
	private String userName="";
	private int id=0;
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}


	public Long getRank() {
		return rank;
	}


	public void setRank(Long rank) {
		this.rank = rank;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest)
	{
		title="";
		userName="";
		thisAction = "";
		this.setIntPage(1);
	}

}
