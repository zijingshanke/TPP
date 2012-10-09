package com.fordays.fdpay.transaction;

import com.fordays.fdpay.agent.Account;
import com.fordays.fdpay.agent.Agent;
import com.fordays.fdpay.system.City;
import com.fordays.fdpay.system.Province;

/**
 * @构造提现列表对象
 */
public class DrawList {
	private Draw draw;
	private Agent agent;
	private City city;
	private Province province;
	private String ids;

	public DrawList(Draw draw, Agent agent,City city,
			Province province) {
		this.draw = draw;
		this.agent = agent;
		this.city = city;
		this.province = province;
	}

	public Draw getDraw() {
		return draw;
	}

	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
