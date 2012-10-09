package com.fordays.fdpay.transaction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.transaction._entity._Draw;

public class Draw extends _Draw
{
	private static final long serialVersionUID = 1L;
	protected com.fordays.fdpay.user.SysUser sysUser1;// 核准人
	protected com.fordays.fdpay.user.SysUser sysUser3;// 转帐人
	
	public static long STATUS_0=0;//待批准
	public static long STATUS_1=1;//待核准
	public static long STATUS_2=2;//待转账
	public static long STATUS_3=3;//已转账
	public static long STATUS_4=4;//已撤销
	public static long TYPE_0=0;//普通提现
	public static long TYPE_1=1;//实名认证
	public static long TYPE_2=2;//系统扣款
	private String note1;
	protected Long userId;
	protected Long user1Id;
	private String state = "";
	private String typeTo = "";
	private String agentLoginName="";
	
	public String getTypeTo()
	{
		String typ = "";
		if (type != null)
		{
			if (type == TYPE_1)
			{
				typ = "实名认证";
			}
			if (type == TYPE_0)
			{
				typ = "普通提现";
			}
			if (type == TYPE_2)
			{
				typ = "系统扣款";
			}
		}
		return typ;
	}

	public String getBankTo()
	{
		String bk = "";
		if (bank != null)
		{
			if (bank.equals("OTHER"))
			{
				bk = "其它";
			}
			if (bank.equals("ICBC"))
			{
				bk = "工商银行";
			}
			if (bank.equals("CCB"))
			{
				bk = "建设银行";
			}
			if (bank.equals("ABC"))
			{
				bk = "农业银行";
			}
			if (bank.equals("CMBC"))
			{
				bk = "民生银行";
			}
			if (bank.equals("BCM"))
			{
				bk = "交通银行";
			}
			if (bank.equals("BOC"))
			{
				bk = "中国银行";
			}
			if (bank.equals("CMB"))
			{
				bk = "招商银行";
			}
			if (bank.equals("CIB"))
			{
				bk = "兴业银行";
			}
			if (bank.equals("BOB"))
			{
				bk = "北京银行";
			}
			if (bank.equals("CEB"))
			{
				bk = "光大银行";
			}
			if (bank.equals("CITIC"))
			{
				bk = "中信银行";
			}
			if (bank.equals("GDB"))
			{
				bk = "广发银行";
			}
			if (bank.equals("SPDB"))
			{
				bk = "浦发银行";
			}
			if (bank.equals("SDB"))
			{
				bk = "深发银行";
			}
		}
		return bk;
	}

	public String getState()
	{
		if (status == STATUS_0)
		{
			state = "待批准";
		}
		else if (status == STATUS_1)
		{
			state = "待核准";
		}
		else if (status == STATUS_2)
		{
			state = "待转账";
		}
		else if (status == STATUS_3 && type==TYPE_2)
		{
			state = "已完成";
		}
		else if (status == STATUS_3 && type!=TYPE_2)
		{
			state = "已转账";
		}
		else if (status == STATUS_4)
		{
			state = "已撤销";
		}
		else
			state = "";
		return state;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public com.fordays.fdpay.user.SysUser getSysUser1()
	{
		return sysUser1;
	}

	public void setSysUser1(com.fordays.fdpay.user.SysUser sysUser1)
	{
		this.sysUser1 = sysUser1;
	}

	public com.fordays.fdpay.user.SysUser getSysUser3()
  {
  	return sysUser3;
  }

	public void setSysUser3(com.fordays.fdpay.user.SysUser sysUser3)
  {
  	this.sysUser3 = sysUser3;
  }

	public String getNote1()
	{
		return note1;
	}

	public void setNote1(String note1)
	{
		this.note1 = note1;
	}

	public String getNoteCaption()
	{

		if (note != null && !note.equals(""))
		{
			return note.replace("\\n", "<br/>");
		}
		else
			return "";
	}

	public Long getUser1Id()
	{
		return user1Id;
	}

	public void setUser1Id(Long user1Id)
	{
		this.user1Id = user1Id;
	}

	public String getAgentLoginName() {
		return agentLoginName;
	}

	public void setAgentLoginName(String agentLoginName) {
		this.agentLoginName = agentLoginName;
	}

	public void reset(ActionMapping actionMapping,
		    HttpServletRequest httpServletRequest)
		{
			this.type=99l;
			this.status=99l;
		}
}
