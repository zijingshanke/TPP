package com.fordays.fdpay.transaction.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.agent.biz.AgentBiz;
import com.fordays.fdpay.right.UserRightInfo;
import com.fordays.fdpay.transaction.Draw;
import com.fordays.fdpay.transaction.DrawList;
import com.fordays.fdpay.transaction.DrawListForm;
import com.fordays.fdpay.transaction.biz.DrawBiz;
import com.fordays.fdpay.transaction.biz.DrawLogBiz;
import com.fordays.fdpay.transaction.biz.TransactionBiz;
import com.fordays.fdpay.user.SysUser;
import com.neza.base.BaseAction;
import com.neza.base.DownLoadFile;
import com.neza.base.Inform;
import com.neza.base.NoUtil;
import com.neza.exception.AppException;
import com.neza.mail.MailUtil;
import com.neza.tool.DateUtil;
import com.neza.utility.FileUtil;

public class DrawListAction extends BaseAction {
	private DrawBiz drawBiz;
	private DrawLogBiz drawLogBiz;
	private NoUtil noUtil;
	private TransactionBiz transactionBiz;
	private AgentBiz agentBiz;

	public SysUser getUserByURI(HttpServletRequest request) {
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		if (uri != null && uri.getUser() != null)
			return uri.getUser();
		else {
			return null;
		}
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DrawListForm dlf = (DrawListForm) form;
		long id = 0;
		List<Draw> drawlist = new ArrayList();
		for (int i = 0; i < dlf.getSelectedItems().length; i++) {
			id = dlf.getSelectedItems()[i];
			if (id > 0) {
				Draw draw = (Draw) drawBiz.queryDrawById(id);
				drawlist.add(draw);
			}
		}
		request.setAttribute("bank", dlf.getBank());
		request.setAttribute("type", dlf.getType());
		request.setAttribute("drawlist", drawlist);
		request.setAttribute("statu", dlf.getStatus());
		forwardPage = "editDraw";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward viewDraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";

		int id = Integer.parseInt(request.getParameter("drawId"));
		if (id > 0) {
			Draw draw = (Draw) drawBiz.queryDrawById(id);

			draw.setThisAction("viewDraw");
			request.setAttribute("draw", draw);
		} else {
			forwardPage = "login";
		}

		forwardPage = "viewDraw";
		return (mapping.findForward(forwardPage));
	}

	// 批量批准提现
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		// 获取登陆SysUser
		SysUser user = this.getUserByURI(request);
		DrawListForm dlf = (DrawListForm) form;
		int errorDraw = 0;
		try {
			long id = 0;
			for (int i = 0; i < dlf.getSelectedItems().length; i++) {
				id = dlf.getSelectedItems()[i];
				if (id > 0) {
					Draw draw = drawBiz.getDrawById(id);
					// agentBiz.synallowBalance(agentBiz.getAgentById(draw.getAgent().getId()));
					Agent agent = agentBiz
							.getAgentById(draw.getAgent().getId());
					if (dlf.getStatus() == Draw.STATUS_0
							&& dlf.getStatus() != Draw.STATUS_1) {
						if (agentBiz.getAgentNotAllowBalance(agent.getId())
								.compareTo(draw.getAmount()) == -1
								&& draw.getType() == Draw.TYPE_0) {
							errorDraw = errorDraw + 1;
						} else {
							if (dlf.getNote() != null) {
								draw.setNote(dlf.getNote());
							} else {
								draw.setNote("");
							}
							draw.setStatus(Draw.STATUS_1);
							draw.setSysUser(user);
							draw.setCheckDate(new Timestamp(System
									.currentTimeMillis()));
							drawBiz.updateInfo(draw);
							// 添加提现日志
							drawLogBiz.approval(draw, agent, dlf.getNote());
						}
					}
				}
			}
			String message = "您已经成功批准这些商家提现请求！";
			if (errorDraw != 0 && errorDraw < dlf.getSelectedItems().length)
				message = "您成功批准了部分请求，其中有" + errorDraw + "条提现批准失败，由于金额不足提现！";
			if (errorDraw != 0 && errorDraw == dlf.getSelectedItems().length)
				message = "批准失败，全部请求都由于金额不足提现！";
			inf.setMessage(message);
			inf
					.setForwardPage("/transaction/drawlist.do?thisAction=listDraw&status="
							+ dlf.getStatus()
							+ "&bank="
							+ dlf.getBank()
							+ "&type=" + dlf.getType());
		} catch (Exception ex) {
			inf.setMessage("批准出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
			ex.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	// 批量核准提现
	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		// 获取登陆SysUser
		SysUser user = this.getUserByURI(request);
		int errorDraw = 0;
		try {
			DrawListForm dlf = (DrawListForm) form;
			long id = 0;
			for (int i = 0; i < dlf.getSelectedItems().length; i++) {
				id = dlf.getSelectedItems()[i];
				if (id > 0) {
					Draw draw = drawBiz.getDrawById(id);
					// agentBiz.synallowBalance(agentBiz.getAgentById(draw.getAgent().getId()));
					Agent agent = agentBiz
							.getAgentById(draw.getAgent().getId());
					if (dlf.getStatus() == Draw.STATUS_1
							&& dlf.getStatus() != Draw.STATUS_2) {
						if (agentBiz.getAgentNotAllowBalance(agent.getId())
								.compareTo(draw.getAmount()) == -1
								&& draw.getType() == Draw.TYPE_0) {
							errorDraw = errorDraw + 1;
						} else {
							if (dlf.getNote1() != null) {
								draw.setNote(dlf.getNote() + "\\n"
										+ dlf.getNote1());
							} else {
								draw.setNote(dlf.getNote());
							}
							draw.setStatus(Draw.STATUS_2);
							draw.setSysUser1(user);
							draw.setUser1Id(user.getUserId());
							draw.setCheck1Date(new Timestamp(System
									.currentTimeMillis()));
							draw.setCheckDate(draw.getCheckDate());
							drawBiz.updateInfo(draw);
							// 添加提现日志
							drawLogBiz.audit(draw, agent, dlf.getNote1());
						}
					}
				}
			}
			String message = "您已经成功核准了这些商家提现请求！";
			if (errorDraw != 0 && errorDraw < dlf.getSelectedItems().length)
				message = "您成功核准了部分提现请求，其中有" + errorDraw + "条提现核准失败，由于金额不足提现！";
			if (errorDraw != 0 && errorDraw == dlf.getSelectedItems().length)
				message = "核准失败！全部请求都由于金额不足提现！";
			inf.setMessage(message);
			inf
					.setForwardPage("/transaction/drawlist.do?thisAction=listDraw&status="
							+ dlf.getStatus()
							+ "&bank="
							+ dlf.getBank()
							+ "&type=" + dlf.getType());

		} catch (Exception ex) {
			inf.setMessage("核准出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
			ex.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	// 批量完成提现,转账
	public ActionForward achieve(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		int errorDraw = 0;
		Inform inf = new Inform();
		// 获取登陆SysUser
		SysUser user = this.getUserByURI(request);
		try {
			DrawListForm dlf = (DrawListForm) form;
			long id = 0;
			for (int i = 0; i < dlf.getSelectedItems().length; i++) {
				id = dlf.getSelectedItems()[i];
				if (id > 0) {
					if (dlf.getStatus() != Draw.STATUS_3
							&& dlf.getStatus() == Draw.STATUS_2) {
						Draw draw = drawBiz.getDrawById(id);
						// agentBiz.synallowBalance(draw.getAgent());
						Agent agent = draw.getAgent();
						if (agentBiz.getAgentNotAllowBalance(agent.getId())
								.compareTo(draw.getAmount()) == -1
								&& draw.getType() == Draw.TYPE_0) {
							errorDraw = errorDraw + 1;
						} else {
							draw.setStatus(Draw.STATUS_3);
							draw.setSysUser3(user);
							draw.setCheck3Date(new Timestamp(System
									.currentTimeMillis()));
							draw.setDrawDate(new Timestamp(System
									.currentTimeMillis()));
							drawBiz.updateInfo(draw);
							// 修改商户账户金额,生成交易
							drawBiz.achieveDraw(draw, agent, draw.getAmount());
							// 添加提现日志
							drawLogBiz.achieve(draw, agent, "完成");

							if (draw.getType() == Draw.TYPE_1) {
								HashMap<String, String> params = new HashMap<String, String>();
								params.put("$RealyName$", agent.getName());
								String result = MailUtil.send("实名认证成功", "0006",
										agent.getLoginName(), params); // 发邮件
							}
							if (draw.getType() == Draw.TYPE_0) {
								HashMap<String, String> params = new HashMap<String, String>();
								params.put("$RealyName$", agent.getName());
								params.put("$amount$", draw.getAmount()
										.toString());
								String result = MailUtil.send("提现成功", "0007",
										agent.getLoginName(), params); // 发邮件
							}
						}
					}

				}
			}
			String message = "您已经向此商户银行账户注入资金！！";
			if (errorDraw != 0 && errorDraw < dlf.getSelectedItems().length) {
				message = "您成功转账,其中有" + errorDraw + "条提现，由于金额不足提现，不能提现！";
			}
			if (errorDraw != 0 && errorDraw == dlf.getSelectedItems().length)
				message = "转账失败！全部请求都由于金额不足提现！";
			inf.setMessage(message);
			inf
					.setForwardPage("/transaction/drawlist.do?thisAction=listDraw&status="
							+ dlf.getStatus()
							+ "&bank="
							+ dlf.getBank()
							+ "&type=" + dlf.getType());
		} catch (Exception ex) {
			inf.setMessage("出错！错误信息是：" + ex.getMessage());
			inf.setBack(true);
			ex.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	// 批量撤销提现
	public ActionForward revocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		DrawListForm dlf = (DrawListForm) form;
		int errorDraw = 0;
		try {
			long id = 0;
			for (int i = 0; i < dlf.getSelectedItems().length; i++) {
				id = dlf.getSelectedItems()[i];
				if (id > 0) {
					Draw draw = drawBiz.getDrawById(id);
					Agent agent = agentBiz
							.getAgentById(draw.getAgent().getId());
					// 撤销，对商户金额进行处理
					if (agentBiz.getAgentNotAllowBalance(agent.getId())
							.compareTo(draw.getAmount()) == -1
							&& draw.getType() == Draw.TYPE_0) {
						errorDraw = errorDraw + 1;
					} else {
						draw.setStatus(Draw.STATUS_4);
						drawBiz.updateInfo(draw);
						if (draw.getType() == Draw.TYPE_1) {
							drawBiz.modifiedAuthentication(draw);
						}
						if (draw.getType() == Draw.TYPE_0) {
							drawBiz.modifiedDraw(draw);
						}
					}

				}
				String message = "您已经撤销了这些商户的提现请求！";
				if (errorDraw != 0 && errorDraw < dlf.getSelectedItems().length)
					message = "您成功撤销了部分提现请求，其中有" + errorDraw
							+ "条提现，由于金额不足提现，不能撤销！";
				if (errorDraw != 0
						&& errorDraw == dlf.getSelectedItems().length)
					message = "撤销失败！全部请求都由于金额不足提现！";
				inf.setMessage(message);
				inf
						.setForwardPage("/transaction/drawlist.do?thisAction=listDraw&status="
								+ dlf.getStatus()
								+ "&bank="
								+ dlf.getBank()
								+ "&type=" + dlf.getType());
			}
		} catch (Exception e) {
			inf.setMessage("出错！错误信息是：" + e.getMessage());
			inf.setBack(true);
			e.printStackTrace();
		}
		request.setAttribute("inf", inf);
		forwardPage = "inform";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		Draw draw = new Draw();
		draw.setThisAction("insert");
		request.setAttribute("draw", draw);
		String forwardPage = "editDraw";
		return (mapping.findForward(forwardPage));
	}

	public ActionForward listDraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DrawListForm dlf = (DrawListForm) form;
		if (dlf == null)
			dlf = new DrawListForm();

		List list = drawBiz.getDraws(dlf);
		dlf.setThisAction("listDraw");
		dlf.setList(list);
		dlf.setPerPageNum(8);
		dlf.addSumField(1, "amount");
		request.setAttribute("stat", dlf.getStatus());
		request.setAttribute("dlf", dlf);
		forwardPage = "listDraw";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward listSubtract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DrawListForm dlf = (DrawListForm) form;
		if (dlf == null)
			dlf = new DrawListForm();

		List list = drawBiz.getSubtracts(dlf);
		dlf.setThisAction("listSubtract");
		dlf.setList(list);
		dlf.setPerPageNum(8);
		dlf.addSumField(1, "amount");
		request.setAttribute("stat", dlf.getStatus());
		request.setAttribute("dlf", dlf);
		forwardPage = "listSubtract";

		return (mapping.findForward(forwardPage));
	}

	public ActionForward downloadDraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DrawListForm dlf = (DrawListForm) form;

		ArrayList<ArrayList<Object>> lists = drawBiz.getAllDraw(dlf);
		String outFileName = DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
		String outText = FileUtil.createCSVFile(lists);
		try {
			outText = new String(outText.getBytes("UTF-8"));
		} catch (Exception ex) {

		}
		DownLoadFile df = new DownLoadFile();
		df.performTask(response, outText, outFileName, "GBK");
		return null;
	}

	public ActionForward downloadSubtract(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		DrawListForm dlf = (DrawListForm) form;

		ArrayList<ArrayList<Object>> lists = drawBiz.getAllSubtract(dlf);
		String outFileName = DateUtil.getDateString("yyyyMMddhhmmss") + ".csv";
		String outText = FileUtil.createCSVFile(lists);
		try {
			outText = new String(outText.getBytes("UTF-8"));
		} catch (Exception ex) {

		}
		DownLoadFile df = new DownLoadFile();
		df.performTask(response, outText, outFileName, "GBK");
		return null;
	}

	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		DrawListForm dlf = (DrawListForm) form;
		if (dlf == null)
			dlf = new DrawListForm();

		List list = drawBiz.getDrawsInfo(dlf);
		Long[] ids = drawBiz.getAllDrawInfoIds(dlf);// 所有记录的ID
		if (ids != null) {
			String idsStr = getSqlStrOfArray(ids);
			dlf.setIds(idsStr);
		}
		dlf.setAllSelected("1");

		dlf.setList(list);
		dlf.setThisAction("download");
		dlf.addSumField(1, "amount");
		request.setAttribute("dlf", dlf);
		forwardPage = "downloadDraw";
		return (mapping.findForward(forwardPage));
	}

	public String getSqlStrOfArray(Long[] ids) {
		String idsStr = "";
		for (int i = 0; i < ids.length; i++) {
			idsStr = idsStr + ids[i] + ",";
		}
		idsStr = idsStr.substring(0, idsStr.length() - 1);
		return idsStr;
	}

	// 下载提现文件
	public ActionForward exportDrawFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DrawListForm drawlistform = (DrawListForm) form;
		String returnMsg = drawBiz.exportDrawFile(drawlistform, response);

		if ("SUCCESS".equals(returnMsg)) {
			drawBiz.saveDrawOprationLog(request);// 保存系统日志
		}
		return null;
	}

	// 导向确认提现页面
	public ActionForward toDrawConfirm(List tempdrawlist) throws AppException {
		HttpServletRequest request = null;
		BigDecimal totalAmount = new BigDecimal(0);
		for (int i = 0; i < tempdrawlist.size(); i++) {
			DrawList dl = (DrawList) tempdrawlist.get(i);
			totalAmount = totalAmount.add(dl.getDraw().getAmount());
		}
		request.setAttribute("totalAmount", totalAmount);
		request.setAttribute("templist", tempdrawlist);
		return new ActionForward("/transaction/confirmDrawList.jsp");
	}

	// 确认批量支付
	public ActionForward confirmDraw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		DrawListForm drawlistform = (DrawListForm) form;

		List tempdrawlist = drawBiz.getToBankDrawList(drawlistform);

		// -------------...
		return null;
	}

	public void setDrawBiz(DrawBiz drawBiz) throws AppException {
		this.drawBiz = drawBiz;
	}

	public void setDrawLogBiz(DrawLogBiz drawLogBiz) {
		this.drawLogBiz = drawLogBiz;
	}

	public void setTransactionBiz(TransactionBiz transactionBiz)
			throws AppException {
		this.transactionBiz = transactionBiz;
	}

	public void setNoUtil(NoUtil noUtil) {
		this.noUtil = noUtil;
	}

	public void setAgentBiz(AgentBiz agentBiz) {
		this.agentBiz = agentBiz;
	}
}
