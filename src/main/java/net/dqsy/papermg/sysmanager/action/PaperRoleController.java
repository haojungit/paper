package net.dqsy.papermg.sysmanager.action;

import org.springframework.stereotype.Controller;

@Controller
public class PaperRoleController {
//    private int roleId;
//    private String roleName;
//    private String description;
//    private boolean flag;
//    private PaperRoleService paperRoleService;
//    private List<PaperRole> roleList;
//    private List<PaperPermission> permissionList;
//
//    public String addRole() {
//        this.flag = this.paperRoleService.save(new PaperRole(this.roleName, this.description, Integer.valueOf(1)));
//        if (this.flag)
//            return "SUCCESS";
//        return "ERROR";
//    }
//
//    public String updateRole() {
//        PaperRole paperRole = (PaperRole) this.paperRoleService.findById(this.roleId);
//        paperRole.setRoleName(this.roleName);
//        paperRole.setDescription(this.description);
//
//        this.flag = this.paperRoleService.update(paperRole);
//        if (this.flag)
//            return "SUCCESS";
//        return "ERROR";
//    }
//
//    public String deleteRole() {
//        PaperRole paperRole = (PaperRole) this.paperRoleService.findById(this.roleId);
//        this.flag = this.paperRoleService.del(paperRole);
//        if (this.flag)
//            return "SUCCESS";
//        return "ERROR";
//    }
//
//    public String getAllRole() {
//        this.roleList = this.paperRoleService.getAllRole().getList();
//        if (this.roleList == null)
//            return null;
//        return "SUCCESS";
//    }
//
//    public String getAllPermission() {
//        this.permissionList = this.paperRoleService.getAllPermission().getList();
//        if (this.permissionList == null)
//            return null;
//        return "SUCCESS";
//    }
//
//    public void setPaperRoleService(PaperRoleService paperRoleService) {
//        this.paperRoleService = paperRoleService;
//    }
//
//    public List<PaperRole> getRoleList() {
//        return this.roleList;
//    }
//
//    public String getRoleName() {
//        return this.roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public String getDescription() {
//        return this.description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public boolean isFlag() {
//        return this.flag;
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
//    public List<PaperPermission> getPermissionList() {
//        return this.permissionList;
//    }
//
//    public void setPermissionList(List<PaperPermission> permissionList) {
//        this.permissionList = permissionList;
//    }
}