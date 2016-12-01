package net.dqsy.papermg.sysmanager.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.dqsy.papermg.sysmanager.po.PaperPermission;
import net.dqsy.papermg.sysmanager.po.PaperRole;
import net.dqsy.papermg.sysmanager.po.PaperRolePermission;
import net.dqsy.papermg.sysmanager.po.PaperStudent;
import net.dqsy.papermg.sysmanager.po.PaperTeacher;
import net.dqsy.papermg.sysmanager.po.PaperUser;
import net.dqsy.papermg.sysmanager.po.PaperUserRole;
import net.dqsy.papermg.sysmanager.service.PaperPermissionService;
import net.dqsy.papermg.sysmanager.service.PaperRolePermissionService;
import net.dqsy.papermg.sysmanager.service.PaperRoleService;
import net.dqsy.papermg.sysmanager.service.PaperUserRoleService;
import net.dqsy.papermg.sysmanager.service.PaperUserService;
import net.dqsy.papermg.sysmanager.vo.LoginVO;
import net.dqsy.papermg.sysmanager.po.*;
import net.dqsy.papermg.sysmanager.service.PaperRolePermissionService;
import net.dqsy.papermg.sysmanager.service.PaperRoleService;
import net.dqsy.papermg.sysmanager.service.PaperUserService;
import net.dqsy.papermg.sysmanager.vo.LoginVO;

/**
 * 
 * <p>
 * Description: TODO(这里用一句话描述这个类的作用)
 * </p>
 * <p>
 * Company: www.itheima.com
 * </p>
 * 创建时间：2015年12月22日 下午3:03:59
 *
 * @author  King-Bao
 * @version 1.0
 */
public class PaperUserAction extends ActionSupport {
	private int userId;
	private int roleId;
	private int permissionId;
	private String userName;
	private String passWord;
	private String rolelist;
	private String permissionlist;
	private String checkCode;
	private List<PaperUserRole> userRoleList;
	private List<PaperRolePermission> rolePermissionList;
	private String SUCCESS = "SUCCESS";
	private PaperUserService paperUserService;
	private PaperRoleService paperRoleService;
	private PaperPermissionService paperPermissionService;
	private PaperUserRoleService paperUserRoleService;
	private PaperRolePermissionService paperRolePermissionService;

	public String logout() {
		Map session = ActionContext.getContext().getSession();
		try {
			Thread.sleep(800L);
			if (session != null) {
				if (session.get("user") != null)
					session.remove("user");
				if (session.get("teacher") != null)
					session.remove("teacher");
				if (session.get("student") != null)
					session.remove("student");
				if (session.get("paperRole") != null)
					session.remove("paperRole");
				if (session.get("navi") != null) {
					session.remove("navi");
				}
				session.clear();
				return "login";
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "error";
	}

	public String login() {
		Map session = ActionContext.getContext().getSession();

		String low_randCheckCode = ((String) session.get("randCheckCode")).toLowerCase();
		if (!low_randCheckCode.equals(this.checkCode.toLowerCase())) {
			session.put("checkCodeError", "验证码不正确");
			return "LOGIN";
		}

		List loginVoList = this.paperUserService.login(this.userName, this.passWord);
		LoginVO loginVO = (LoginVO) loginVoList.get(0);

		if ((loginVO == null) || (loginVO.getFlag().intValue() == 0)) {
			return "LOGIN";
		}
		PaperUser paperUser = new PaperUser(loginVO.getUserId(), loginVO.getUsername(), loginVO.getPassword(),
				loginVO.getIdentity(), loginVO.getFlag());

		session.put("user", paperUser);

		if (paperUser.getIdentity().equals("同学")) {
			PaperStudent paperStudent = new PaperStudent(loginVO.getStudentid(), loginVO.getStudentname(),
					loginVO.getStudentsex(), loginVO.getStudentFaculty(), loginVO.getStudentMajor(),
					loginVO.getStudentDirection(), loginVO.getStudentGrade(), loginVO.getStudentAge(),
					loginVO.getStudentPhone(), loginVO.getStudentNumber());
			session.put("student", paperStudent);
			if (session.get("teacher") != null)
				session.remove("teacher");
		} else {
			PaperTeacher paperTeacher = new PaperTeacher(loginVO.getTeacherid(), loginVO.getTeacherName(),
					loginVO.getTeacherSex(), loginVO.getTeacherAge(), loginVO.getTeacherUnits(),
					loginVO.getTeacherMajor(), loginVO.getTeacherDirection(), loginVO.getTeacherEducation(),
					loginVO.getTeacherJobTitle(), loginVO.getTeacherPhone(), loginVO.getTeacherNumber());
			session.put("teacher", paperTeacher);
			if (session.get("student") != null) {
				session.remove("student");
			}
		}

		PaperRole paperRole = new PaperRole(loginVO.getRoleid(), loginVO.getRoleName(), loginVO.getDescription());
		session.put("paperRole", paperRole);

		Map navi = new LinkedHashMap();
		PaperPermission paperPermission = null;
		List list = null;
		String description = null;
		LoginVO lVo = null;
		for (int i = 0; i < loginVoList.size(); i++) {
			lVo = (LoginVO) loginVoList.get(i);
			description = lVo.getDescription();
			paperPermission = new PaperPermission(lVo.getId(), lVo.getPermission(), lVo.getPdescription());

			if (!navi.containsKey(description))
				list = new ArrayList();
			else {
				list = (List) navi.get(description);
			}
			list.add(paperPermission);
			navi.put(description, list);
		}

		session.put("navi", navi);

		return this.SUCCESS;
	}

	public String updatePassword(String userName, String oldPassWord, String newPassWord) {
		return this.paperUserService.updatePassword(userName, oldPassWord, newPassWord);
	}

	public String getUserRole() {
		this.userRoleList = this.paperUserRoleService.getUserRole(this.userId).getList();
		if ((this.userRoleList == null) && (this.userRoleList.size() == 0))
			return "ERROR";
		return this.SUCCESS;
	}

	public String getRolePermission() {
		this.rolePermissionList = this.paperRolePermissionService.getRolePermission(this.roleId).getList();
		if ((this.rolePermissionList == null) && (this.rolePermissionList.size() == 0))
			return "ERROR";
		return this.SUCCESS;
	}

	public String addRolePermissions() {
		try {
			String[] list = this.permissionlist.split(",");
			for (int i = 0; i < list.length; i++) {
				setPermissionId(Integer.parseInt(list[i]));
				addRolePermission();
			}
		} catch (Exception e) {
			return "ERROR";
		}
		return this.SUCCESS;
	}

	public String removeRolePermissions() {
		try {
			String[] list = this.permissionlist.split(",");
			for (int i = 0; i < list.length; i++) {
				setPermissionId(Integer.parseInt(list[i]));
				removeRolePermission();
			}
		} catch (Exception e) {
			return "ERROR";
		}
		return this.SUCCESS;
	}

	public synchronized String addRolePermission() {
		try {
			List list = this.paperRolePermissionService.find("from PaperRolePermission where paperPermission.id = '"
					+ this.permissionId + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();

			System.out.println(this.roleId);
			System.out.println(this.permissionId);

			if (list.size() == 0) {
				PaperRole role = (PaperRole) this.paperRoleService.findById(this.roleId);

				PaperPermission permission = new PaperPermission();
				PaperRolePermission rp = new PaperRolePermission();

				if (role != null) {
					permission = (PaperPermission) this.paperPermissionService.findById(this.permissionId);
					rp.setPaperRole(role);
					rp.setPaperPermission(permission);
					rp.setFlag(Integer.valueOf(1));
					this.paperRolePermissionService.save(rp);
					return this.SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

	public synchronized String removeRolePermission() {
		List list = this.paperRolePermissionService.find("from PaperRolePermission where paperPermission.id = '"
				+ this.permissionId + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();

		if (list.size() == 1) {
			this.paperRolePermissionService.del(list.get(0));
			return this.SUCCESS;
		}

		return "ERROR";
	}

	public String addUserRoles() {
		try {
			String[] list = this.rolelist.split(",");
			for (int i = 0; i < list.length; i++) {
				setRoleId(Integer.parseInt(list[i]));
				addUserRole();
			}
		} catch (Exception e) {
			return "ERROR";
		}
		return this.SUCCESS;
	}

	public String removeUserRoles() {
		try {
			String[] list = this.rolelist.split(",");
			for (int i = 0; i < list.length; i++) {
				setRoleId(Integer.parseInt(list[i]));
				removeUserRole();
			}
		} catch (Exception e) {
			return "ERROR";
		}
		return this.SUCCESS;
	}

	public synchronized String addUserRole() {
		List list = this.paperUserRoleService.find("from PaperUserRole where paperUser.userId = '" + this.userId
				+ "' and paperRole.roleId = " + this.roleId, 1, 1).getList();

		if (list.size() == 0) {
			PaperUser paperUser = (PaperUser) this.paperUserService.findById(this.userId);

			PaperRole role = new PaperRole();
			PaperUserRole ur = new PaperUserRole();

			if (paperUser != null) {
				role = (PaperRole) this.paperRoleService.findById(this.roleId);
				ur.setPaperRole(role);
				ur.setPaperUser(paperUser);
				ur.setFlag(Integer.valueOf(1));
				this.paperUserRoleService.save(ur);
				return this.SUCCESS;
			}
		}
		return "ERROR";
	}

	public synchronized String removeUserRole() {
		List list = this.paperUserRoleService.find("from PaperUserRole where paperUser.userId = '" + this.userId
				+ "' and paperRole.roleId = " + this.roleId, 1, 1).getList();

		if (list.size() == 1) {
			this.paperUserRoleService.del(list.get(0));
			return this.SUCCESS;
		}
		return "ERROR";
	}

	public void setPaperUserService(PaperUserService paperUserService) {
		this.paperUserService = paperUserService;
	}

	public void setPaperRoleService(PaperRoleService paperRoleService) {
		this.paperRoleService = paperRoleService;
	}

	public void setPaperUserRoleService(PaperUserRoleService paperUserRoleService) {
		this.paperUserRoleService = paperUserRoleService;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public PaperUserRoleService getPaperUserRoleService() {
		return this.paperUserRoleService;
	}

	public List<PaperUserRole> getUserRoleList() {
		return this.userRoleList;
	}

	public void setUserRoleList(List<PaperUserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSUCCESS() {
		return this.SUCCESS;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRolelist() {
		return this.rolelist;
	}

	public void setRolelist(String rolelist) {
		this.rolelist = rolelist;
	}

	public PaperRolePermissionService getPaperRolePermissionService() {
		return this.paperRolePermissionService;
	}

	public void setPaperRolePermissionService(PaperRolePermissionService paperRolePermissionService) {
		this.paperRolePermissionService = paperRolePermissionService;
	}

	public List<PaperRolePermission> getRolePermissionList() {
		return this.rolePermissionList;
	}

	public void setRolePermissionList(List<PaperRolePermission> rolePermissionList) {
		this.rolePermissionList = rolePermissionList;
	}

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public PaperPermissionService getPaperPermissionService() {
		return this.paperPermissionService;
	}

	public void setPaperPermissionService(PaperPermissionService paperPermissionService) {
		this.paperPermissionService = paperPermissionService;
	}

	public String getPermissionlist() {
		return this.permissionlist;
	}

	public void setPermissionlist(String permissionlist) {
		this.permissionlist = permissionlist;
	}

	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}