/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.watson.fantasybaseball.contract;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contract")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 *
 * @author Administrator
 */
public class Contract implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
    private int price;
    private int length;
    private int startYear;
    private boolean rostered;
    
    public Contract(String name, int price, int length, int startYear, boolean active) {
        this.name = name;
        this.price = price;
        this.length = length;
        this.startYear = startYear;
        this.rostered = active;
    }
    
    public Contract() {
    	
    }

    public String getName() {
        return name;
    }

    //@XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    //@XmlElement
    public void setPrice(int price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    //@XmlElement
    public void setLength(int length) {
        this.length = length;
    }

	public boolean isRostered() {
		return rostered;
	}

	public void setRostered(boolean active) {
		this.rostered = active;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

}
