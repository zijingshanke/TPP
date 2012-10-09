package com.fordays.fdpay.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.system.PatternShortMessage;
import com.fordays.fdpay.system.biz.PatternShortMessageBiz;
import com.neza.base.BaseAction;
import com.neza.base.Inform;
import com.neza.exception.AppException;

public class PatternShortMessageAction extends BaseAction {
	private PatternShortMessageBiz patternShortMessageBiz;

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternShortMessage patternShortMessage = (PatternShortMessage) form;
		Inform inf = new Inform();
		if (isTokenValid(request, true)) {
			try {
				PatternShortMessage tempPatternShortMessage = (PatternShortMessage) patternShortMessageBiz
						.getPatternShortMessageById(patternShortMessage.getId());

				tempPatternShortMessage.setName(patternShortMessage.getName());
				tempPatternShortMessage.setStatus(patternShortMessage
						.getStatus());
				tempPatternShortMessage.setContent(patternShortMessage
						.getContent());
				tempPatternShortMessage.setCode(patternShortMessage.getCode());
				tempPatternShortMessage.setDescription(patternShortMessage
						.getDescription());

				patternShortMessageBiz
						.updatePatternShortMessage(tempPatternShortMessage);
				request.setAttribute("patternShortMessage",
						tempPatternShortMessage);
				inf.setMessage("您已经成功更新了短信!");
				inf.setForwardPage("/system/patternShortMessagelist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("更新短信出错！错误信息是: " + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
		} else {
			saveToken(request);
			inf.setMessage("请不要重复提交!!");
			request.setAttribute("message", inf);
		}
		return (mapping.findForward(forwardPage));
	}

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		PatternShortMessage patternShortMessage = (PatternShortMessage) form;
		Inform inf = new Inform();
		if (isTokenValid(request, true)) {
			try {
				PatternShortMessage tempPatternShortMessage = new PatternShortMessage();

				tempPatternShortMessage.setName(patternShortMessage.getName());
				tempPatternShortMessage.setStatus(patternShortMessage
						.getStatus());
				tempPatternShortMessage.setContent(patternShortMessage
						.getContent());
				tempPatternShortMessage.setCode(patternShortMessage.getCode());
				tempPatternShortMessage.setDescription(patternShortMessage
						.getDescription());

				patternShortMessageBiz
						.savePatternShortMessage(tempPatternShortMessage);
				request.setAttribute("patternShortMessage",
						tempPatternShortMessage);
				inf.setMessage("您已经成功增加了系统状态!");
				inf.setForwardPage("/system/patternShortMessagelist.do");
				inf.setParamId("thisAction");
				inf.setParamValue("list");
			} catch (Exception ex) {
				inf.setMessage("增加系统状态出错！错误信息是: " + ex.getMessage());
				inf.setBack(true);
			}
			request.setAttribute("inf", inf);
			forwardPage = "inform";
		} else {
			saveToken(request);
			inf.setMessage("请不要重复提交!!");
			request.setAttribute("message", inf);
		}
		return (mapping.findForward(forwardPage));
	}

	public void setPatternShortMessageBiz(
			PatternShortMessageBiz patternShortMessageBiz) {
		this.patternShortMessageBiz = patternShortMessageBiz;
	}
}
