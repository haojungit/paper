package net.dqsy.papermg.sysmanager.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 *  
 * <p>
 * Description: 用户
 * </p>
 * <p>
 * Company: www.itheima.com
 * </p>
 * 创建时间：2015年12月22日 下午3:35:59
 *
 * @author King-Bao
 * @version 1.0
 */
public class PaperUser implements Serializable {
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 身份
	 */
	private String identity;
	/**
	 * 逻辑删除标记，1正常（默认），0屏蔽
	 */
	private Integer flag;

	private Set paperUserRoles = new HashSet(0);

	public PaperUser() {
	}

	public PaperUser(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public PaperUser(String userName, String passWord, String identity) {
		this.userName = userName;
		this.passWord = passWord;
		this.identity = identity;
	}

	public PaperUser(String userName, String passWord, String identity, Integer flag) {
		this.userName = userName;
		this.passWord = passWord;
		this.identity = identity;
		this.flag = flag;
	}

	public PaperUser(Integer userId, String userName, String passWord, String identity, Integer flag) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.identity = identity;
		this.flag = flag;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Set getPaperUserRoles() {
		return this.paperUserRoles;
	}

	public void setPaperUserRoles(Set paperUserRoles) {
		this.paperUserRoles = paperUserRoles;
	}
}