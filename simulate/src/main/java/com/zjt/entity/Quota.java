package com.zjt.entity;

import java.math.BigDecimal;

import javax.persistence.*;

public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String year;

    private String subject;

    @Column(name = "subject_value")
    private BigDecimal subjectValue;

    private String remarks;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    /**
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public BigDecimal getSubjectValue() {
		return subjectValue;
	}

	public void setSubjectValue(BigDecimal subjectValue) {
		this.subjectValue = subjectValue;
	}

	/**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}