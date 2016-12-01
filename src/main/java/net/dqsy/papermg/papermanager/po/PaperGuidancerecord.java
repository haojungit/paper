package net.dqsy.papermg.papermanager.po;

import java.io.Serializable;
import java.util.Date;

public class PaperGuidancerecord
  implements Serializable
{
  private Integer guidId;
  private PaperTitle paperTitle;
  private String guidance;
  private String guidanceType;
  private Date guidanceDate;
  private Date guidanceUpDate;
  private String filePath;
  private Integer flag;
  private String remark;

  public PaperGuidancerecord()
  {
  }

  public PaperGuidancerecord(Integer guidId, String guidance, String guidanceType, Date guidanceDate)
  {
    this.guidId = guidId;
    this.guidance = guidance;
    this.guidanceType = guidanceType;
    this.guidanceDate = guidanceDate;
  }

  public Integer getGuidId()
  {
    return this.guidId;
  }

  public void setGuidId(Integer guidId) {
    this.guidId = guidId;
  }

  public PaperTitle getPaperTitle() {
    return this.paperTitle;
  }

  public void setPaperTitle(PaperTitle paperTitle) {
    this.paperTitle = paperTitle;
  }

  public String getGuidance() {
    return this.guidance;
  }

  public void setGuidance(String guidance) {
    this.guidance = guidance;
  }

  public String getGuidanceType() {
    return this.guidanceType;
  }

  public void setGuidanceType(String guidanceType) {
    this.guidanceType = guidanceType;
  }

  public Date getGuidanceDate() {
    return this.guidanceDate;
  }

  public void setGuidanceDate(Date guidanceDate) {
    this.guidanceDate = guidanceDate;
  }

  public Integer getFlag()
  {
    return this.flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getGuidanceUpDate() {
    return this.guidanceUpDate;
  }

  public void setGuidanceUpDate(Date guidanceUpDate) {
    this.guidanceUpDate = guidanceUpDate;
  }
}