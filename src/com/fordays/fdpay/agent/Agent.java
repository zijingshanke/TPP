package com.fordays.fdpay.agent;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent._entity._Agent;
import com.fordays.fdpay.base.Bank;

public class Agent extends _Agent {
	private static final long serialVersionUID = 1L;
	private static final int CERT_STATUS_0 = 0;// 没有申请证书
	private static final int CERT_STATUS_1 = 1;// 有申请证书,但没带证书
	private static final int CERT_STATUS_2 = 2;// 有申请证书,也带了证书，但验证失败
	private static final int CERT_STATUS_3 = 1;// 有申请证书,也带了证书，验证成功

	protected com.fordays.fdpay.agent.Agent agent;
	protected com.fordays.fdpay.agent.Agent subAgent;
	public static long status_0 = 0;// 注册
	public static long status_1 = 1;// 激活
	public static long status_2 = 2;// 正在申请实名认证
	public static long status_3 = 3;// 实名认证通过
	public static long status_7 = 7;// 冻结
	public static long status_10 = 10;// 注销
	public static long status_31 = 31;// 实名认证打款成功

	public static long mobile_status_0 = 0;// 未绑定
	public static long mobile_status_1 = 1;// 绑定
	public static long msg_type_1 = 1;// 申请绑定手机
	public static long msg_type_2 = 2;// 申请取消绑定
	public static long msg_type_3 = 3;// 手机找回登录密码，支付密码

	private String certTypeTo = "";
	private String statusTo = "";
	private String accountStatusTo = "";
	private String transactionMark = "";
	private BigDecimal tempAmount = new BigDecimal(0);

	// 获取批量提现文件账户名
	public String getDrawName() {
		if (registerType == 0) {
			return name;
		} else if (registerType == 1) {
			return companyName;
		} else {
			return "错误";
		}
	}

	public String getStatusTo() {
		if (status == status_1) {
			return "已激活";
		} else if (status == status_2) {
			return "申请实名认证中";
		} else if (status == status_3) {
			return "实名认证通过";
		} else if (status == status_7) {
			return "冻结";
		} else if (status == status_10) {
			return "注销";
		} else if (status == status_31) {
			return "实名认证打款成功";
		}
		return "";
	}

	public String getAccountStatusTo() {
		if (accountStatus == 0) {
			return "未开通";
		}
		if (accountStatus == 1) {
			return "开通";
		}
		if (accountStatus == 2) {
			return "冻结";
		}
		if (accountStatus == 3) {
			return "换了邮箱，待激活";
		}
		return "";
	}

	public com.fordays.fdpay.agent.Agent getAgent() {
		return agent;
	}

	public void setAgent(com.fordays.fdpay.agent.Agent agent) {
		this.agent = agent;
	}

	public com.fordays.fdpay.agent.Agent getSubAgent() {
		return subAgent;
	}

	public void setSubAgent(com.fordays.fdpay.agent.Agent subAgent) {
		this.subAgent = subAgent;
	}

	public String getCertTypeTo() {
		if (certType != null)
			return "身份证";
		return "";
	}

	public java.math.BigDecimal getConsumeBalance() {
		if (this.consumeBalance != null)
			return this.consumeBalance;
		else
			return new java.math.BigDecimal(0);
	}

	public java.math.BigDecimal getCreditBalance() {
		if (this.creditBalance != null)
			return this.creditBalance;
		else
			return new java.math.BigDecimal(0);
	}

	public java.math.BigDecimal getNotallowBalance() {
		if (this.notallowBalance != null)
			return this.notallowBalance;
		else
			return new java.math.BigDecimal(0);
	}

	public java.math.BigDecimal getAllowBalance() {
		if (this.allowBalance != null)
			return this.allowBalance;
		else
			return new java.math.BigDecimal(0);
	}

	public String getName() {
		if (name == null)
			return "";
		return name;
	}

	public String getEmail() {
		if (email == null)
			return "";
		return email;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		name = "";
		loginName = "";
	}

	public BigDecimal getAllowBalanceAndCheckAmount() {
		return this.allowBalance.add(this.checkAmount);
	}

	public BigDecimal getBalanceAndCheckAmount() {
		return this.allowBalance.add(this.notallowBalance).add(
				this.creditBalance);
	}

	public BigDecimal getBalance() {
		return this.allowBalance;
	}

	public String getBindedBankName() {
		if (accounts != null && accounts.size() > 0) {
			for (int i = 0; i < accounts.size(); i++) {
				Iterator itr = accounts.iterator();
				while (itr.hasNext()) {
					Account account = (Account) itr.next();
					if (account.getBindStatus().equals(new Long(2)))
						return Bank.getCName(account.getBankId() + "c");
				}
			}
		}
		return "";
	}

	public BigDecimal getTempAmount() {
		return tempAmount;
	}

	public void setTempAmount(BigDecimal tempAmount) {
		this.tempAmount = tempAmount;
	}

	public String getTransactionMark() {
		return transactionMark;
	}

	public void setTransactionMark(String transactionMark) {
		this.transactionMark = transactionMark;
	}
}
