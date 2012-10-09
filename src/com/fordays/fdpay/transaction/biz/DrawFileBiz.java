package com.fordays.fdpay.transaction.biz;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.neza.exception.AppException;

/**
 * 提现文件接口
 */
public interface DrawFileBiz {
	public boolean exportDrawFile_ABC_Person(List list,
			HttpServletResponse response) throws AppException;

	public boolean exportDrawFile_BCM_Person(List list,
			HttpServletResponse response) throws AppException;

	public boolean exportDrawFile_CCB_Person(List list,
			HttpServletResponse response) throws AppException;

	public boolean exportDrawFile_CITIC_Person(List list,
			HttpServletResponse response) throws AppException;

	public boolean exportDrawFile_CMBC(List list, HttpServletResponse response)
			throws AppException;

	public boolean exportDrawFile_ICBC(List list, HttpServletResponse response)
			throws AppException;
}
