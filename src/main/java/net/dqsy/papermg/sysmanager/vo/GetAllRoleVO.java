package net.dqsy.papermg.sysmanager.vo;

import java.io.Serializable;

public class GetAllRoleVO
  implements Serializable
{
  private Integer roleId;
  private String roleName;
  private String description;
  private Integer flag;

  public GetAllRoleVO(Integer roleId, String roleName, String description, Integer flag)
  {
    this.roleId = roleId;
    this.roleName = roleName;
    this.description = description;
    this.flag = flag;
  }

  public Integer getRoleId() {
    return this.roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GetAllRoleVO other = (GetAllRoleVO)obj;
    if (this.description == null) {
      if (other.description != null)
        return false;
    } else if (!this.description.equals(other.description))
      return false;
    if (this.roleId == null) {
      if (other.roleId != null)
        return false;
    } else if (!this.roleId.equals(other.roleId))
      return false;
    if (this.roleName == null) {
      if (other.roleName != null)
        return false;
    } else if (!this.roleName.equals(other.roleName))
      return false;
    return true;
  }

  public String toString()
  {
    return "PaperRoleActionVO [roleId=" + this.roleId + ", roleName=" + this.roleName + 
      ", description=" + this.description + "]";
  }

  public Integer getFlag() {
    return this.flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }
}