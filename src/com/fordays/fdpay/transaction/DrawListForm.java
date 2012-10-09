package com.fordays.fdpay.transaction;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.ListActionForm;

public class DrawListForm extends ListActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysUserName = "";
	private String agentName = "";
	private Agent agent = new Agent();
	private SysUser sysUser = new SysUser();
	private String beginDate = "";
	private String endDate = "";
	private BigDecimal minAmount = new BigDecimal(0);
	private BigDecimal maxAmount = new BigDecimal(100000);
	private String bank = "";
	private long status = 99;
	private long type = 99;
	private String ids = "";
	private String allSelected = "0";
	private long drawId=0;
	private String note;
	private String note1;
	private String orderNo;
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public void reset(ActionMapping actionMapping,

			HttpServletRequest httpServletRequest) {
		type = 99;
		status = 99;
		bank = "";
		thisAction = "";
		beginDate = "";
		endDate = "";
		agentName="";
		minAmount = new BigDecimal(0);
		maxAmount = new BigDecimal(1000000);
		orderNo="";
		this.setIntPage(1);
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAllSelected() {
		return allSelected;
	}

	public void setAllSelected(String allSelected) {
		this.allSelected = allSelected;
	}	


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public long getDrawId() {
		return drawId;
	}

	public void setDrawId(long drawId) {
		this.drawId = drawId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


}
