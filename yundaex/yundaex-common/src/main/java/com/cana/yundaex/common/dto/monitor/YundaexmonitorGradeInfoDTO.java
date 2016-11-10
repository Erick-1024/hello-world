package com.cana.yundaex.common.dto.monitor;

import java.io.Serializable;
import java.math.BigDecimal;

public class YundaexmonitorGradeInfoDTO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8054641679860109779L;

	/**
     *序号 主键
     */
    private Integer id;

    /**
     *最小分值
     */
    private BigDecimal minPoints;

    /**
     *最大分值
     */
    private BigDecimal maxPoints;

    /**
     *等级
     */
    private String grade;

    /**
     *利率定价
     */
    private BigDecimal beta;

    /**
     *系数值
     */
    private BigDecimal ratio;


    /**
     *序号 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     *序号 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *最小分值
     */
    public BigDecimal getMinPoints() {
        return minPoints;
    }

    /**
     *最小分值
     */
    public void setMinPoints(BigDecimal minPoints) {
        this.minPoints = minPoints;
    }

    /**
     *最大分值
     */
    public BigDecimal getMaxPoints() {
        return maxPoints;
    }

    /**
     *最大分值
     */
    public void setMaxPoints(BigDecimal maxPoints) {
        this.maxPoints = maxPoints;
    }

    /**
     *等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     *等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     *利率定价
     */
    public BigDecimal getBeta() {
        return beta;
    }

    /**
     *利率定价
     */
    public void setBeta(BigDecimal beta) {
        this.beta = beta;
    }

    /**
     *系数值
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     *系数值
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}
