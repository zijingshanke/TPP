package com.fordays.fdpay.transaction.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.dao.AgentDAO;
import com.fordays.fdpay.base.Constant;
import com.fordays.fdpay.transaction.Charge;
import com.fordays.fdpay.transaction.ChargeListForm;
import com.fordays.fdpay.transaction.OrderDetails;
import com.fordays.fdpay.transaction.Transaction;
import com.fordays.fdpay.transaction.dao.ChargeDAO;
import com.fordays.fdpay.transaction.dao.OrderDetailsDAO;
import com.fordays.fdpay.transaction.dao.TransactionDAO;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.tool.DateUtil;

public class ChargeBizImp implements ChargeBiz {
	private NoUtil noUtil; 
	private OrderDetailsDAO orderDetailsDAO;
	private ChargeDAO chargeDAO;
	private TransactionTemplate transactionTemplate;
	private TransactionDAO transactionDAO;
	private AgentDAO agentDAO;

	public Charge getChargeById(long id) throws AppException {
		return chargeDAO.getChargeById(id);
	}

	public List getCharges(ChargeListForm ulf) throws AppException {
		return chargeDAO.list(ulf);
	}
	
	public List getAllCharge(ChargeListForm ulf) throws AppException {
		return chargeDAO.getAllChargePaging(ulf);
	}

	public void saveCharge(Charge charge) throws AppException {
		chargeDAO.save(charge);
	}
	
	public ArrayList<ArrayList<Object>> listAllGeneralCharge(ChargeListForm cf )throws AppException {
		List data =chargeDAO.getAllChargeList(cf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#普通充值报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("商户名");
		list_title.add("商户账号");
		list_title.add("交易号");
		list_title.add("充值银行");
		list_title.add("充值时间");
		list_title.add("充值状态");
		list_title.add("充值金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Charge charge = (Charge) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(charge.getAgent().getName());
			list_context_item.add(charge.getAgent().getLoginName());
			list_context_item.add(charge.getOrderNo());
			list_context_item.add(charge.getBankTo());
			if(charge.getChargeDate()!=null)
				list_context_item.add(DateUtil.getDateString(charge.getChargeDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			list_context_item.add(charge.getStatusByBank());
			list_context_item.add(charge.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
	}
	public ArrayList<ArrayList<Object>> listAllCharge(ChargeListForm cf )throws AppException {
		List data =chargeDAO.getAllChargeNoPaging(cf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#充值报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		
		list_title =  new ArrayList<Object>();
		list_title.add("商户名");
		list_title.add("商户账号");
		list_title.add("交易号");
		list_title.add("充值银行");
		list_title.add("充值时间");
		list_title.add("充值状态");
		list_title.add("充值金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Charge charge = (Charge) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(charge.getAgent().getName());
			list_context_item.add(charge.getAgent().getLoginName());
			list_context_item.add(charge.getOrderNo());
			list_context_item.add(charge.getBankTo());
			if(charge.getChargeDate()!=null)
				list_context_item.add(DateUtil.getDateString(charge.getChargeDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			list_context_item.add(charge.getStatusByAll());
			list_context_item.add(charge.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
	}
	public ArrayList<ArrayList<Object>> getAllOtherCharge(ChargeListForm cf)throws AppException {
		List data =chargeDAO.getAllOtherChargeList(cf);
		ArrayList<ArrayList<Object>> list_context = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> list_title = new ArrayList<Object>();
		list_title.add("#线下充值报表");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add("#下载时间：");
		list_context.add(list_title);
		list_title = new ArrayList<Object>();
		list_title.add(DateUtil.getDateString("yyyy年MM月dd日HH:mm:ss"));
		list_context.add(list_title);
		list_title =  new ArrayList<Object>();
		list_title.add("商户名");
		list_title.add("商户账号");
		list_title.add("交易号");
		list_title.add("银行");
		list_title.add("充值时间");
		list_title.add("批准人");
		list_title.add("批准时间");
		list_title.add("核准人");
		list_title.add("核准时间");
		list_title.add("当前状态");
		list_title.add("充值金额（元）");
		list_context.add(list_title);
		for (int i = 0; i < data.size(); i++)
		{
			Charge charge = (Charge) data.get(i);
			ArrayList<Object> list_context_item = new ArrayList<Object>();
			list_context_item.add(charge.getAgent().getName());
			list_context_item.add(charge.getAgent().getLoginName());
			list_context_item.add(charge.getOrderNo());
			list_context_item.add(charge.getBankTo());
			if(charge.getChargeDate()!=null)
				list_context_item.add(DateUtil.getDateString(charge.getChargeDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			if(charge.getSysUser()!=null)
				list_context_item.add(charge.getSysUser().getUserName());
			else
				list_context_item.add("");
			if(charge.getCheckDate()!=null)
				list_context_item.add(DateUtil.getDateString(charge.getCheckDate(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			if(charge.getSysUser1()!=null)
				list_context_item.add(charge.getSysUser1().getUserName());
			else
				list_context_item.add("");
			if(charge.getCheck1Date()!=null)
				list_context_item.add(DateUtil.getDateString(charge.getCheck1Date(), "yyyy年MM月dd日HH:mm:ss"));
			else
				list_context_item.add("");
			list_context_item.add(charge.getStatusByOther());
			list_context_item.add(charge.getAmount());
			list_context.add(list_context_item);
		}
		return list_context;
	}
	
	
			public int updateInfo(Charge charge) throws AppException {
				chargeDAO.update(charge);
				return 0;
			}
		
			public void deleteCharge(int id) throws AppException {
				chargeDAO.deleteById(id);
			}
		
			public Charge queryChargeById(int id) throws AppException {
				return chargeDAO.queryChargeById(id);
			}
		
			public long save(Charge charge) throws AppException {
				return chargeDAO.save(charge);
			}
		
			public List getOtherCharges(ChargeListForm clf) throws AppException {
				return chargeDAO.getOtherCharges(clf);
			}
		
			public Charge getChargeByOrderNo(String orderNo) throws AppException {
				return chargeDAO.getChargeByOrderNo(orderNo);
			}


	public long getCountByChargeBank(long app) throws AppException {	
		return chargeDAO.getCountByChargeBank(app);
	}
	
	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
		this.orderDetailsDAO = orderDetailsDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}
	
	public void setTransactionManager(HibernateTransactionManager thargeManager) {
		this.transactionTemplate = new TransactionTemplate(thargeManager);
	}

	public void setChargeDAO(ChargeDAO chargeDAO) throws AppException {
		this.chargeDAO = chargeDAO;
	}

	public Charge createCharge(Charge charge, Agent agent) throws AppException {
		Charge newcharge = new Charge();
		newcharge.setAgent(agent);
		newcharge.setAmount(charge.getAmount());
		newcharge.setOrderNo(noUtil.getChargeNo());
		newcharge.setBank(charge.getBank());
		newcharge.setStatus(Charge.CHARGE_STATUS_0);
		newcharge.setType(Charge.CHARGE_TYPE_BACKGROUND);
		if(charge.getNote()!=null)
		newcharge.setNote(charge.getNote());
		chargeDAO.save(newcharge);
		Charge c=chargeDAO.getChargeByOrderNo(newcharge.getOrderNo());
		return c;
	}
	public Charge creditCharge(Charge charge, Agent agent) throws AppException {
		Charge newcharge = new Charge();
		newcharge.setAgent(agent);
		newcharge.setAmount(charge.getAmount());
		newcharge.setOrderNo(noUtil.getChargeNo());
		newcharge.setBank("CREDIT");
		newcharge.setStatus(Charge.CHARGE_STATUS_0);
		newcharge.setType(Charge.CHARGE_TYPE_CREDIT);
		if(charge.getNote()!=null)
			newcharge.setNote(charge.getNote());
		chargeDAO.save(newcharge);
		Charge c=chargeDAO.getChargeByOrderNo(newcharge.getOrderNo());
		return c;
	}
	

	public void achieveCharge(Charge charge, Agent agent) throws AppException {
		chargeDAO.save(charge);
		//生成订单
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderNo(charge.getOrderNo());
		if(charge.getType().equals(Charge.CHARGE_TYPE_CREDIT)){
			orderDetails.setShopName("信用金额充值");
			orderDetails.setDetailsContent("信用金额线下充值");
			orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_2);
		}
		else{
			orderDetails.setShopName("线下充值");
			orderDetails.setDetailsContent("线下充值");
			orderDetails.setOrderDetailsType(OrderDetails.ORDER_DETAILS_TYPE_1);
		}
		orderDetails.setShopTotal(new Long(1));
		orderDetails.setShopPrice(charge.getAmount());
		orderDetails.setPaymentPrice(charge.getAmount());
		
		orderDetails.setOrderDetailsNo(noUtil.getOrderDetailsNo());
		
		orderDetailsDAO.save(orderDetails);
		
		Transaction tempTransaction = new Transaction();
		tempTransaction.setNo(noUtil.getTransactionNo());
		tempTransaction.setAmount(charge.getAmount());
		if(charge.getType().equals(Charge.CHARGE_TYPE_CREDIT))
			tempTransaction.setType(Transaction.TYPE_23);// 信用金额线下充值
		else
			tempTransaction.setType(Transaction.TYPE_22);// 线下充值
		tempTransaction.setStatus(Transaction.STATUS_3);// 充值成功
		tempTransaction.setAccountDate(charge.getChargeDate());// 创建时间
		tempTransaction.setPayDate(new Timestamp(System.currentTimeMillis()));// 付款时间
		tempTransaction.setToAgent(agent); 
		tempTransaction.setFromAgent(Constant.platChargeAgent);
		tempTransaction.setOrderDetails(orderDetails);
		if(charge.getType().equals(Charge.CHARGE_TYPE_CREDIT))
			tempTransaction.setMark("信用金额充值");
		else
			tempTransaction.setMark("线下充值");
		transactionDAO.save(tempTransaction);
		
		if(charge.getType().equals(Charge.CHARGE_TYPE_CREDIT)){
			//改变agnet信用金额
			 agentDAO.addCreditAmount(agent,charge.getAmount());
			 agentDAO.deleteCreditAmount(com.fordays.fdpay.base.Constant.platChargeAgent, charge.getAmount());
		}else {
			//改变agnet金额
			 agentDAO.addAmount(agent,charge.getAmount());
			 agentDAO.deleteAmount(com.fordays.fdpay.base.Constant.platChargeAgent, charge.getAmount());
		}
		agentDAO.synallowBalance(agent);
		
	}

	public AgentDAO getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

	public BigDecimal getSumAmountByCharge(ChargeListForm clf)
			throws AppException {
		return chargeDAO.getSumAmountByCharge(clf);
	}
	public BigDecimal getSumAmountByGeneralCharge(ChargeListForm clf)
	throws AppException {
		return chargeDAO.getSumAmountByGeneralCharge(clf);
	}
}

