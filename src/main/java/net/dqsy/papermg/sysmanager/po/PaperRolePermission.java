package net.dqsy.papermg.sysmanager.po;

import java.io.Serializable;

/**
 *  
 * <p>
 * Description: 角色权限
 * </p>
 * <p>
 * Company: net.dqsy.web
 * </p>
 * 创建时间：2015年12月22日 下午3:19:18
 *
 * @author King-Bao
 * @version 1.0
 */
public class PaperRolePermission implements Serializable {

    private static final long serialVersionUID = 9032040076057046484L;
    /**
     * 角色权限ID
     */
    private Integer id;
    /**
     * 权限
     */
    private PaperPermission paperPermission;
    /**
     * 角色
     */
    private PaperRole paperRole;
    /**
     * 排序
     */
    private Integer porder;
    /**
     * 逻辑删除标记，1正常（默认），0屏蔽
     */
    private Integer flag;

    public PaperRolePermission() {
    }

    public PaperRolePermission(PaperPermission paperPermission, PaperRole paperRole) {
        this.paperPermission = paperPermission;
        this.paperRole = paperRole;
    }

    public PaperRolePermission(PaperPermission paperPermission, PaperRole paperRole, Integer porder) {
        this.paperPermission = paperPermission;
        this.paperRole = paperRole;
        this.porder = porder;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaperPermission getPaperPermission() {
        return this.paperPermission;
    }

    public void setPaperPermission(PaperPermission paperPermission) {
        this.paperPermission = paperPermission;
    }

    public PaperRole getPaperRole() {
        return this.paperRole;
    }

    public void setPaperRole(PaperRole paperRole) {
        this.paperRole = paperRole;
    }

    public String toString() {
        return "PaperRolePermission [id=" + this.id + ", paperPermission=" + this.paperPermission + "]";
    }

    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getPorder() {
        return this.porder;
    }

    public void setPorder(Integer porder) {
        this.porder = porder;
    }
}