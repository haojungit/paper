package net.dqsy.papermg.sysmanager.action;

import org.springframework.stereotype.Controller;

@Controller
public class PaperPermissionController {
//    private String id;
//    private List<PaperPermission> permissionlist;
//    Map<String, Object> session;
//    private PaperPermissionService paperPermissionService;
//
//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public int save(PaperPermission permission) {
//        permission.setFlag(Integer.valueOf(1));
//        return this.paperPermissionService.save(permission) ? 1 : 0;
//    }
//
//    public int del(int id) {
//        PaperPermission permission = (PaperPermission) this.paperPermissionService.findById(id);
//        return this.paperPermissionService.del(permission) ? 1 : 0;
//    }
//
//    public PaperPermission findById(int id) {
//        PaperPermission permission = (PaperPermission) this.paperPermissionService.findById(id);
//        return permission;
//    }
//
//    public PagingSupport getPage(int pageNO, int count) {
//        PagingSupport support = this.paperPermissionService.findAll(pageNO, count);
//        return support;
//    }
//
//    public int update(PaperPermission permission) {
//        try {
//            PaperPermission p = (PaperPermission) this.paperPermissionService.findById(permission.getId().intValue());
//            p.setDescription(permission.getDescription());
//            p.setPermission(permission.getPermission());
//            return this.paperPermissionService.update(p) ? 1 : 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public String execute() {
//        this.permissionlist = this.paperPermissionService.findAll(1, 999999).getList();
//        this.session.put("permission", this.permissionlist);
//        return "SUCCESS";
//    }
//
//    public void setPaperPermissionService(PaperPermissionService paperPermissionService) {
//        this.paperPermissionService = paperPermissionService;
//    }
//
//    public int delsoftOrRenew(int pid) {
//        try {
//            this.paperPermissionService.delsoftOrRenew(pid);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//        return 1;
//    }
}