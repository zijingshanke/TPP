package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fordays.fdpay.agent.Account;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.dao.AccountDAO;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.bank.BankUtil;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.system.LogDetail;
import com.fordays.fdpay.system.SysLog;
import com.fordays.fdpay.system.TaskTimestamp;
import com.fordays.fdpay.system.dao.SysLogDAO;
import com.fordays.fdpay.system.dao.TaskTimestampDAO;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.dao.DrawDAO;
import com.fordays.fdpay.transaction.dao.TransactionDAOImp;
import com.fordays.fdpay.user.SysUser;
import com.fordays.fdpay.user.dao.UserDAO;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.mail.MailUtil;
import com.neza.tool.DateUtil;

/**
 * 提现业务实现类
 */

public class DrawBizImp extends DrawFileBizImp implements DrawBiz {
	private AgentDAO agentDAO;
	private AccountDAO accountDAO;
	private DrawDAO drawDAO;
	private SysLogDAO sysLogDAO;
	private UserDAO userDAO;
	private TaskTimestampDAO tasktimestampDAO;
	private TransactionDAOImp transactionDAO;
	private NoUtil noUtil;

	/**
	 * 下载提现文件
	 * 
	 * @param DrawListForm
	 *            drawlistform
	 * @param HttpServletResponse
	 *            response
	 * @return String returnMsg
	 */
	public String exportDrawFile(DrawListForm drawlistform,
			HttpServletResponse response) throws AppException {
		String returnMsg = "";
		String bank = drawlistform.getBank();
		List bankDrawList = getToBankDrawList(drawlistform);

		if (bankDrawList.size() > 0) {
			if ("ICBC".equals(bank)) {
				exportDrawFile_ICBC(bankDrawList, response);
			} else if ("CCB".equals(bank)) {
				exportDrawFile_CCB_Person(bankDrawList, response);// 建行个人网银批量支付
			} else if ("CMBC".equals(bank)) {
				exportDrawFile_CMBC(bankDrawList, response);
			} else if ("ABC".equals(bank)) {
				exportDrawFile_ABC_Person(bankDrawList, response);
			} else if ("BCM".equals(bank)) {
				exportDrawFile_BCM_Person(bankDrawList, response);
			} else if ("CITIC".equals(bank)) {
				exportDrawFile_CITIC_Person(bankDrawList, response);
			} else {
				return returnMsg = "can not find exportDrawFile Method Of this bank"
						+ bank;
			}
			returnMsg = "SUCCESS";
		} else {
			return returnMsg = "bankDrawList.size>0 false";
		}
		return returnMsg;
	}

	/**
	 * 获取BankDrawList
	 * 
	 * @param DrawListForm
	 *            drawlistform
	 * @return List
	 */
	public List getToBankDrawList(DrawListForm drawlistform)
			throws AppException {
		List bankDrawList = null;
		int[] dlfId = drawlistform.getSelectedItems();
		String ids = drawlistform.getIds();

		if (!"".equals(ids) && ids != null) {
			int m = ids.indexOf(",");
			if (m == 0) {
				ids = ids.substring(1, ids.length());
			}
			dlfId = BankUtil.getSplitInt(ids, ",");
			String bank = drawlistform.getBank();
			bankDrawList = getToBankDrawList(bank, dlfId);
		}
		return bankDrawList;
	}

	/**
	 * 根据复选框选中状态，获取提现单
	 * 
	 * @param String
	 *            bank
	 * @param int[]
	 *            drawlistid
	 * @return List
	 */
	public List getToBankDrawList(String bank, int[] drawlistid)
			throws AppException {
		return drawDAO.getToBankDrawList(bank, drawlistid);
	}

//	public List getToBankDrawList(String bank) throws AppException {
//		return drawDAO.getToBankDrawList(bank);
//	}

	public Draw getDrawById(long id) throws AppException {
		return drawDAO.getDrawById(id);
	}

	public List getDraws(DrawListForm ulf) throws AppException {
		return drawDAO.list(ulf);
	}

	public void saveDraw(Draw draw) throws AppException {
		drawDAO.save(draw);
	}

	public long updateInfo(Draw draw) throws AppException {
		drawDAO.update(draw);
		return 0;
	}

	public void deleteDraw(long id) throws AppException {
		drawDAO.deleteById(id);
	}

	public Draw queryDrawById(long id) throws AppException {
		return drawDAO.queryDrawById(id);
	}

	public Draw getDrawByOrderNo(String orderNo) throws AppException {
		return drawDAO.getDrawByOrderNo(orderNo);
	}

	public List getCountByDrawList() throws AppException {
		return drawDAO.getCountByDrawList();
	}

	public List getDrawsInfo(DrawListForm clf) throws AppException {
		clf.setStatus(Draw.STATUS_2);
		return drawDAO.getDrawsInfo(clf);
	}

	/**
	 * 全选
	 */
	public Long[] getAllDrawInfoIds(DrawListForm dlf) throws AppException {
		Long[] ids = new Long[10];
		List list = null;
		try {
			dlf.setStatus(Draw.STATUS_2);
			list = drawDAO.getAllDrawsInfo(dlf);
			if (list.size() > 0) {
				ids = new Long[list.size()];
				for (int i = 0; i < list.size(); i++) {
					Draw draw = (Draw) list.get(i);
					ids[i] = draw.getId();
				}
			} else {
				ids = null;
			}
		} catch (AppException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public void achieveDraw(Draw draw, Agent agent, BigDecimal amount)
			throws AppException {
		drawDAO.update(draw);
		Transaction transaction = new Transaction();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPaymentPrice(draw.getAmount());
		String shopName = "";
		if (draw.getType() == Draw.TYPE_1) {// 实名认证
			agentDAO.deleteAmount(agent, amount);
			agentDAO.addAmount(com.fordays.fdpay.base.Constant.platChargeAgent,
					amount);
			agent.setCheckAmount(draw.getAmount());
			agent.setStatus(Agent.status_31);
			agentDAO.update(agent);
			shopName = "实名认证金额提现";
			transaction.setType(Transaction.TYPE_70);
			
		} else if (draw.getType() == Draw.TYPE_0) {
			// 非实名认证，正常提现
			
	 
			agentDAO.reduceNotallowBalance(agent, amount);
			agentDAO.addAmount(com.fordays.fdpay.base.Constant.platChargeAgent,
					amount);
			shopName = "提现金额转账";
			transaction.setType(Transaction.TYPE_30);
		}
		orderDetails.setShopName(shopName);
		transaction.setMark(shopName);
		transaction.setAmount(draw.getAmount());
		transaction.setFromAgent(agent);
		transaction.setToAgent(com.fordays.fdpay.base.Constant.platChargeAgent);
		transaction.setStatus(Transaction.STATUS_3);
		transaction.setPayDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setFinishDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setDetailsContent(shopName);
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		orderDetails.setOrderNo(draw.getOrderNo());
		orderDetails.setShopPrice(draw.getAmount());
		orderDetails.setShopTotal(new Long(1));
		if(draw.getType() == Draw.TYPE_1){
			orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_1);
		}else{
			orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_6);
		}	
		
		String no = noUtil.getTransactionNo();
		transaction.setNo(no);
		transactionDAO.addOrderDetails(orderDetails);
		transaction.setOrderDetails(orderDetails);
		transaction.setAccountDate(new Timestamp(System.currentTimeMillis()));
		transactionDAO.save(transaction);
	}

	/**
	 * 保存提现操作日志
	 */
	public void saveDrawOprationLog(HttpServletRequest request)
			throws AppException {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		SysUser sysUser = uri.getUser();
		sysUser = userDAO.getUserByNo(sysUser);
		if (sysUser != null) {
			LogDetail logDetails = new LogDetail();
			logDetails.setRefrenceCode("refrenceCode");
			logDetails.setContent("content");

			SysLog sysLog = new SysLog();
			sysLog.setLogType(SysLog.TYPE_TRANSACTION_DRAW);
			sysLog.setLogDate(new Timestamp(System.currentTimeMillis()));
			sysLog.setLogContent("下载提现文件");
			sysLog.setLocate(new Long(0));// 0--后台，1--前台
			// sysLog.setLogDetails(logDetails);

			sysLog.setSysUser(sysUser);
			// sysLog.setAgent(null);
			sysLogDAO.save(sysLog);
		}
	}

	public void modifiedAuthentication(Draw draw) throws AppException {
		Account account = accountDAO.getAccountByBanknum(draw.getCardNo());
		Agent agent = agentDAO.getAgentById(draw.getAgent().getId());
		TaskTimestamp tasktimestamp = tasktimestampDAO.getTaskTimestamp(agent,
				TaskTimestamp.type_6);

		tasktimestamp.setAgent(agent);
		tasktimestamp.setStatus(TaskTimestamp.status_1);
		tasktimestamp.setTaskType(TaskTimestamp.type_7);
		tasktimestamp.setTaskHour(new Long(24));
		tasktimestamp.setTaskDate(new Timestamp(System.currentTimeMillis()));
		tasktimestampDAO.updateStatus(tasktimestamp);

		agent.setStatus(Agent.status_1);
		agent.setCheckAmount(new BigDecimal(0));// 删除实名认证的检查金额，归零
		agentDAO.update(agent);
		accountDAO.deleteById(account.getId());

		System.out.println("---删除实名认证失败的卡号---");

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("$RealyName$", agent.getName());
		String mailResult = MailUtil.send("0005", agent.getEmail(), params); // 

	}
  //撤销提现
	public void modifiedDraw(Draw draw) throws AppException {
		Agent agent = agentDAO.getAgentById(draw.getAgent().getId());
		agentDAO.moveNotallowBalanceToAllowBalance(agent, draw.getAmount());
		
		Transaction transaction = new Transaction();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPaymentPrice(draw.getAmount());
		String shopName = "撤销提现金额";
		transaction.setType(Transaction.TYPE_41);
		orderDetails.setShopName(shopName);
		transaction.setMark(shopName);
		transaction.setAmount(draw.getAmount());
		transaction.setFromAgent(agent);
		transaction.setToAgent(agent);
		transaction.setStatus(Transaction.STATUS_3);
		transaction.setPayDate(new Timestamp(System.currentTimeMillis()));
		
		orderDetails.setFinishDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setDetailsContent(shopName);
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		orderDetails.setOrderNo(draw.getOrderNo());
		orderDetails.setShopPrice(draw.getAmount());
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_6);
		String no = noUtil.getTransactionNo();
		transaction.setNo(no);
		transactionDAO.addOrderDetails(orderDetails);
		transaction.setOrderDetails(orderDetails);
		transaction.setAccountDate(new Timestamp(System.currentTimeMillis()));
		transactionDAO.save(transaction);
		
		
	}

	public ArrayList<ArrayList<Object>> getAllDraw(DrawListForm cf)
			throws AppException {
		List data = drawDAO.getAllDrawList(cf);
		System.out
				.println(data.size()
						+ "-----------------------------------------------------------------------");
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#提现报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		list_title = new ArrayList<Object>();

		list_title.add("交易号");
		list_title.add("商户名称 (企业名称)");
		list_title.add("商户账号");
		list_title.add("申请时间");
		list_title.add("提现银行");
		list_title.add("银行卡号");
		list_title.add("提现时间");
		list_title.add("提现金额（元）");
		list_title.add("类型");
		list_title.add("状态");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++) {
			Draw draw = (Draw) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(draw.getOrderNo());
			if (draw.getAgent().getRegisterType() == 0)
				list_context_item.add(draw.getAgent().getName());
			if (draw.getAgent().getRegisterType() == 1)
				list_context_item.add(draw.getAgent().getName() + "("
						+ draw.getAgent().getCompanyName() + ")");
			list_context_item.add(draw.getAgent().getLoginName());
			list_context_item.add(DateUtil.getDateString(draw.getRequestDate(),
					"yyyy年MM月dd日HH:mm:ss"));
			list_context_item.add(draw.getBankTo());
			list_context_item.add(draw.getCardNo());
			if (draw.getDrawDate() != null)
				list_context_item.add(DateUtil.getDateString(
						draw.getDrawDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			list_context_item.add(draw.getAmount());
			list_context_item.add(draw.getTypeTo());
			list_context_item.add(draw.getState());
			list_context.add(list_context_item);
		}
		return list_context;
	}

	public ArrayList<ArrayList<Object>> getAllSubtract(DrawListForm cf)
			throws AppException {
		List data = drawDAO.getAllSubtract(cf);
		System.out
				.println(data.size()
						+ "-----------------------------------------------------------------------");
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#系统扣款报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		list_title = new ArrayList<Object>();

		list_title.add("交易号");
		list_title.add("商户名称 (企业名称)");
		list_title.add("商户账号");
		list_title.add("申请时间");
		list_title.add("完成时间");
		list_title.add("扣款金额（元）");
		list_title.add("状态");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++) {
			Draw draw = (Draw) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(draw.getOrderNo());
			if (draw.getAgent().getRegisterType() == 0)
				list_context_item.add(draw.getAgent().getName());
			if (draw.getAgent().getRegisterType() == 1)
				list_context_item.add(draw.getAgent().getName() + "("
						+ draw.getAgent().getCompanyName() + ")");
			list_context_item.add(draw.getAgent().getLoginName());
			list_context_item.add(DateUtil.getDateString(draw.getRequestDate(),
					"yyyy年MM月dd日HH:mm:ss"));
			if (draw.getDrawDate() != null)
				list_context_item.add(DateUtil.getDateString(
						draw.getDrawDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			list_context_item.add(draw.getAmount());
			list_context_item.add(draw.getState());
			list_context.add(list_context_item);
		}
		return list_context;
	}

	public void save(Draw draw) throws AppException {
		drawDAO.save(draw);
	}

	public List getSubtracts(DrawListForm dlf) throws AppException {
		return drawDAO.listSubtract(dlf);
	}

	// 完成扣款
	public void achieveSubtract(Draw draw, Agent agent, BigDecimal amount)
			throws AppException {
		agentDAO.deleteAmount(agent, amount);
		agentDAO.addAmount(com.fordays.fdpay.base.Constant.platChargeAgent,
				amount);
		Transaction transaction = new Transaction();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPaymentPrice(draw.getAmount());
		String shopName = "系统扣款";
		transaction.setType(Transaction.TYPE_44);
		orderDetails.setShopName(shopName);
		transaction.setMark(shopName);
		transaction.setAmount(draw.getAmount());
		transaction.setFromAgent(agent);
		transaction.setToAgent(com.fordays.fdpay.base.Constant.platChargeAgent);
		transaction.setStatus(Transaction.STATUS_3);
		transaction.setPayDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setFinishDate(new Timestamp(System.currentTimeMillis()));
		orderDetails.setDetailsContent(shopName);
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		orderDetails.setOrderNo(draw.getOrderNo());
		orderDetails.setShopPrice(draw.getAmount());
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_1);
		String no = noUtil.getTransactionNo();
		transaction.setNo(no);
		transactionDAO.addOrderDetails(orderDetails);
		transaction.setOrderDetails(orderDetails);
		transaction.setAccountDate(new Timestamp(System.currentTimeMillis()));
		transactionDAO.save(transaction);
	}

	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setDrawDAO(DrawDAO drawDAO) {
		this.drawDAO = drawDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public void setSysLogDAO(SysLogDAO sysLogDAO) {
		this.sysLogDAO = sysLogDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setTransactionDAO(TransactionDAOImp transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	public void setTasktimestampDAO(TaskTimestampDAO tasktimestampDAO) {
		this.tasktimestampDAO = tasktimestampDAO;
	}
}
