package net.dqsy.papermg.sysmanager.po;

import java.io.Serializable;

/**
 * 
 *  
 * <p>
 * Description: 用户角色  
 * </p>
 * <p>
 * Company: www.itheima.com
 * </p>
 * 创建时间：2015年12月22日 下午3:39:31
 *
 * @author King-Bao
 * @version 1.0
 */
public class PaperUserRole implements Serializable {
	/**
	 * 用户角色ID
	 */
	private Integer userRoleId;
	/**
	 * 用户
	 */
	private PaperUser paperUser;
	/**
	 * 角色
	 */
	private PaperRole paperRole;
	/**
	 * 逻辑删除标记，1正常（默认），0屏蔽
	 */
	private Integer flag;

	public PaperUserRole() {
	}

	public PaperUserRole(PaperUser paperUser, PaperRole paperRole) {
		this.paperUser = paperUser;
		this.paperRole = paperRole;
	}

	public PaperUserRole(PaperUser paperUser, PaperRole paperRole, Integer flag) {
		this.paperUser = paperUser;
		this.paperRole = paperRole;
		this.flag = flag;
	}

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public PaperUser getPaperUser() {
		return this.paperUser;
	}

	public void setPaperUser(PaperUser paperUser) {
		this.paperUser = paperUser;
	}

	public PaperRole getPaperRole() {
		return this.paperRole;
	}

	public void setPaperRole(PaperRole paperRole) {
		this.paperRole = paperRole;
	}

	public String toString() {
		return "PaperUserRole [paperRole=" + this.paperRole + ", paperUser=" + this.paperUser.getUserName()
				+ ", userRoleId=" + this.userRoleId + "]";
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}