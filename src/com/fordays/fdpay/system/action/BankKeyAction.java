package com.fordays.fdpay.system.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fordays.fdpay.system.BankKey;
import com.neza.base.BaseAction;
import com.neza.base.Constant;
import com.neza.exception.AppException;
import com.neza.tool.FileUtil;

public class BankKeyAction extends BaseAction {
	public ActionForward readKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
			BankKey bk=(BankKey) form;
			 String bkKey="";
				if(bk.getKeyType().equals("pub"))
					bkKey="公钥";
				if(bk.getKeyType().equals("pri"))
					bkKey="私钥";
				
			 String bkName="";
				if(bk.getBankName().equals("CCB"))
					bkName="中国建设银行";
				if(bk.getBankName().equals("ICBC"))
					bkName="中国工商银行";
			
		   String pathFile=Constant.WEB_INFO_PATH+File.separator+bk.getBankName()+bk.getKeyType()+"key.txt";
		   try {
			   request.setAttribute("bkName", bkName);
			   request.setAttribute("bkKey", bkKey);
				request.setAttribute("key", FileUtil.read(pathFile));
				request.setAttribute("bankKey", bk);
				 return mapping.findForward("editBankKey");
			} catch (IOException e) {
				request.setAttribute("msUpdate", "找不到密钥文件！");
			    return mapping.findForward("listBank");
				
			}
		 
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws AppException{
		
		BankKey bk=(BankKey) form;
	   try {
		FileUtil.save(Constant.WEB_INFO_PATH+File.separator+bk.getBankName()+bk.getKeyType()+"key.txt",bk.getKey());
	    System.out.println(FileUtil.read(Constant.WEB_INFO_PATH+File.separator+"ccbpubkey.txt")+"修改后");
	    request.setAttribute("msUpdate", "修改密钥成功！");
	    return mapping.findForward("listBank");
		} catch (IOException e) {
			request.setAttribute("msUpdate", "密钥修改失败！请联系专业人员！");
		    return mapping.findForward("listBank");
			
		}
	  
	}
}
