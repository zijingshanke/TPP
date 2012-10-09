package com.fordays.fdpay.agent.biz;

import java.util.List;

import com.fordays.fdpay.agent.Coterie;
import com.fordays.fdpay.agent.CoterieListForm;
import com.neza.exception.AppException;

public interface CoterieBiz {

	public Coterie getCoterieById(long id) throws AppException;

	public List getCoteries(CoterieListForm alf) throws AppException;

	public void saveCoterie(Coterie Coterie) throws AppException;

	public long updateCoterieInfo(Coterie Coterie) throws AppException;

	public void deleteCoterie(long id) throws AppException;

	public Coterie getCoterieByName(String name) throws AppException;

	public long updateCoterieKey(Coterie coterie) throws AppException;

	public Coterie getCoterieByEmail(String email) throws AppException;

	public int getCoterieByAgent_Id(long id) throws AppException;  
	
	public boolean checkCoerieByAllowMult(long  agentId) throws AppException;
	
}
