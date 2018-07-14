package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String telephone;
	private String email;
	private String haspda = "0";//1-有pda,0没有
	private String deltag = "0";//1-已删除，0正常
	private String station;
	private String standard;
	private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>(0);
	private Set workbills = new HashSet(0);
	private Set noticebills = new HashSet(0);


	// Constructors

	public Set getWorkbills() {
		return workbills;
	}

	public void setWorkbills(Set workbills) {
		this.workbills = workbills;
	}

	public Set getNoticebills() {
		return noticebills;
	}

	public void setNoticebills(Set noticebills) {
		this.noticebills = noticebills;
	}

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String id) {
		this.id = id;
	}

	/** full constructor */
	public Staff(String id, String name, String telephone, String email,
			String haspda, String deltag, String station, String standard,
			Set decidedzones) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.haspda = haspda;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
		this.decidedzones = decidedzones;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaspda() {
		return this.haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return this.deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set getDecidedzones() {
		return this.decidedzones;
	}

	public void setDecidedzones(Set decidedzones) {
		this.decidedzones = decidedzones;
	}

}