package net.dqsy.papermg.sysmanager.action;

import net.dqsy.papermg.sysmanager.po.*;
import net.dqsy.papermg.sysmanager.service.*;
import net.dqsy.papermg.sysmanager.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PaperUserController {
    @Autowired
    private PaperUserService paperUserService;
    @Autowired
    private PaperRoleService paperRoleService;
    @Autowired
    private PaperPermissionService paperPermissionService;
    @Autowired
    private PaperUserRoleService paperUserRoleService;
    @Autowired
    private PaperRolePermissionService paperRolePermissionService;
//
//    @RequestMapping(value = "/user/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        try {
//            Thread.sleep(800L);
//            if (session != null) {
//                if (session.getAttribute("user") != null)
//                    session.removeAttribute("user");
//                if (session.getAttribute("teacher") != null)
//                    session.removeAttribute("teacher");
//                if (session.getAttribute("student") != null)
//                    session.removeAttribute("student");
//                if (session.getAttribute("paperRole") != null)
//                    session.removeAttribute("paperRole");
//                if (session.getAttribute("navi") != null) {
//                    session.removeAttribute("navi");
//                }
//                session.invalidate();
//                return "login";
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "error";
//    }
//
    @RequestMapping("/user/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String randCheckCode = ((String) session.getAttribute("randCheckCode"));
        if(randCheckCode == null){
            return "/user/login";
        }
        String low_randCheckCode = randCheckCode.toLowerCase();

        String checkCode = request.getParameter("checkCode");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        if (!low_randCheckCode.equals(checkCode.toLowerCase())) {
            request.setAttribute("checkCodeError","验证码不正确");
            return "/user/login";
        }

        List loginVoList = this.paperUserService.login(userName, passWord);
        LoginVO loginVO = (LoginVO) loginVoList.get(0);

        if ((loginVO == null) || (loginVO.getFlag().intValue() == 0)) {
            return "/user/login";
        }
        PaperUser paperUser = new PaperUser(loginVO.getUserId(), loginVO.getUsername(), loginVO.getPassword(),
                loginVO.getIdentity(), loginVO.getFlag());

        session.setAttribute("user", paperUser);

        if (paperUser.getIdentity().equals("同学")) {
            PaperStudent paperStudent = new PaperStudent(loginVO.getStudentid(), loginVO.getStudentname(),
                    loginVO.getStudentsex(), loginVO.getStudentFaculty(), loginVO.getStudentMajor(),
                    loginVO.getStudentDirection(), loginVO.getStudentGrade(), loginVO.getStudentAge(),
                    loginVO.getStudentPhone(), loginVO.getStudentNumber());
            session.setAttribute("student", paperStudent);
            if (session.getAttribute("teacher") != null)
                session.removeAttribute("teacher");
        } else {
            PaperTeacher paperTeacher = new PaperTeacher(loginVO.getTeacherid(), loginVO.getTeacherName(),
                    loginVO.getTeacherSex(), loginVO.getTeacherAge(), loginVO.getTeacherUnits(),
                    loginVO.getTeacherMajor(), loginVO.getTeacherDirection(), loginVO.getTeacherEducation(),
                    loginVO.getTeacherJobTitle(), loginVO.getTeacherPhone(), loginVO.getTeacherNumber());
            session.setAttribute("teacher", paperTeacher);
            if (session.getAttribute("student") != null) {
                session.removeAttribute("student");
            }
        }

        PaperRole paperRole = new PaperRole(loginVO.getRoleid(), loginVO.getRoleName(), loginVO.getDescription());
        session.setAttribute("paperRole", paperRole);

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

        session.setAttribute("navi", navi);

        return "/index";
    }
//
//    public String updatePassword(String userName, String oldPassWord, String newPassWord) {
//        return this.paperUserService.updatePassword(userName, oldPassWord, newPassWord);
//    }
//
//    public String getUserRole() {
//        this.userRoleList = this.paperUserRoleService.getUserRole(this.userId).getList();
//        if ((this.userRoleList == null) && (this.userRoleList.size() == 0))
//            return "ERROR";
//        return this.SUCCESS;
//    }
//
//    public String getRolePermission() {
//        this.rolePermissionList = this.paperRolePermissionService.getRolePermission(this.roleId).getList();
//        if ((this.rolePermissionList == null) && (this.rolePermissionList.size() == 0))
//            return "ERROR";
//        return this.SUCCESS;
//    }
//
//    public String addRolePermissions() {
//        try {
//            String[] list = this.permissionlist.split(",");
//            for (int i = 0; i < list.length; i++) {
//                setPermissionId(Integer.parseInt(list[i]));
//                addRolePermission();
//            }
//        } catch (Exception e) {
//            return "ERROR";
//        }
//        return this.SUCCESS;
//    }
//
//    public String removeRolePermissions() {
//        try {
//            String[] list = this.permissionlist.split(",");
//            for (int i = 0; i < list.length; i++) {
//                setPermissionId(Integer.parseInt(list[i]));
//                removeRolePermission();
//            }
//        } catch (Exception e) {
//            return "ERROR";
//        }
//        return this.SUCCESS;
//    }
//
//    public synchronized String addRolePermission() {
//        try {
//            List list = this.paperRolePermissionService.find("from PaperRolePermission where paperPermission.id = '"
//                    + this.permissionId + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();
//
//            System.out.println(this.roleId);
//            System.out.println(this.permissionId);
//
//            if (list.size() == 0) {
//                PaperRole role = (PaperRole) this.paperRoleService.findById(this.roleId);
//
//                PaperPermission permission = new PaperPermission();
//                PaperRolePermission rp = new PaperRolePermission();
//
//                if (role != null) {
//                    permission = (PaperPermission) this.paperPermissionService.findById(this.permissionId);
//                    rp.setPaperRole(role);
//                    rp.setPaperPermission(permission);
//                    rp.setFlag(Integer.valueOf(1));
//                    this.paperRolePermissionService.save(rp);
//                    return this.SUCCESS;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ERROR";
//    }
//
//    public synchronized String removeRolePermission() {
//        List list = this.paperRolePermissionService.find("from PaperRolePermission where paperPermission.id = '"
//                + this.permissionId + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();
//
//        if (list.size() == 1) {
//            this.paperRolePermissionService.del(list.get(0));
//            return this.SUCCESS;
//        }
//
//        return "ERROR";
//    }
//
//    public String addUserRoles() {
//        try {
//            String[] list = this.rolelist.split(",");
//            for (int i = 0; i < list.length; i++) {
//                setRoleId(Integer.parseInt(list[i]));
//                addUserRole();
//            }
//        } catch (Exception e) {
//            return "ERROR";
//        }
//        return this.SUCCESS;
//    }
//
//    public String removeUserRoles() {
//        try {
//            String[] list = this.rolelist.split(",");
//            for (int i = 0; i < list.length; i++) {
//                setRoleId(Integer.parseInt(list[i]));
//                removeUserRole();
//            }
//        } catch (Exception e) {
//            return "ERROR";
//        }
//        return this.SUCCESS;
//    }
//
//    public synchronized String addUserRole() {
//        List list = this.paperUserRoleService.find("from PaperUserRole where paperUser.userId = '" + this.userId
//                + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();
//
//        if (list.size() == 0) {
//            PaperUser paperUser = (PaperUser) this.paperUserService.findById(this.userId);
//
//            PaperRole role = new PaperRole();
//            PaperUserRole ur = new PaperUserRole();
//
//            if (paperUser != null) {
//                role = (PaperRole) this.paperRoleService.findById(this.roleId);
//                ur.setPaperRole(role);
//                ur.setPaperUser(paperUser);
//                ur.setFlag(Integer.valueOf(1));
//                this.paperUserRoleService.save(ur);
//                return this.SUCCESS;
//            }
//        }
//        return "ERROR";
//    }
//
//    public synchronized String removeUserRole() {
//        List list = this.paperUserRoleService.find("from PaperUserRole where paperUser.userId = '" + this.userId
//                + "' and paperRole.roleId = " + this.roleId, 1, 1).getList();
//
//        if (list.size() == 1) {
//            this.paperUserRoleService.del(list.get(0));
//            return this.SUCCESS;
//        }
//        return "ERROR";
//    }
//
//
//    public int getUserId() {
//        return this.userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public List<PaperUserRole> getUserRoleList() {
//        return this.userRoleList;
//    }
//
//    public void setUserRoleList(List<PaperUserRole> userRoleList) {
//        this.userRoleList = userRoleList;
//    }
//
//    public String getUserName() {
//        return this.userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getSUCCESS() {
//        return this.SUCCESS;
//    }
//
//    public String getPassWord() {
//        return this.passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public int getRoleId() {
//        return this.roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }
//
//    public String getRolelist() {
//        return this.rolelist;
//    }
//
//    public void setRolelist(String rolelist) {
//        this.rolelist = rolelist;
//    }
//
//    public List<PaperRolePermission> getRolePermissionList() {
//        return this.rolePermissionList;
//    }
//
//    public void setRolePermissionList(List<PaperRolePermission> rolePermissionList) {
//        this.rolePermissionList = rolePermissionList;
//    }
//
//    public int getPermissionId() {
//        return this.permissionId;
//    }
//
//    public void setPermissionId(int permissionId) {
//        this.permissionId = permissionId;
//    }
//
//
//    public String getPermissionlist() {
//        return this.permissionlist;
//    }
//
//    public void setPermissionlist(String permissionlist) {
//        this.permissionlist = permissionlist;
//    }
//
//    public String getCheckCode() {
//        return this.checkCode;
//    }
//
//    public void setCheckCode(String checkCode) {
//        this.checkCode = checkCode;
//    }
}