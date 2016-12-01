package net.dqsy.papermg.papermanager.vo;

import java.io.Serializable;

public class TeacherNameVO
  implements Serializable
{
  private int teacherId;
  private String teacherName;

  public TeacherNameVO(int teacherId, String teacherName)
  {
    this.teacherId = teacherId;
    this.teacherName = teacherName;
  }

  public String getTeacherName() {
    return this.teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public int getTeacherId() {
    return this.teacherId;
  }

  public void setTeacherId(int teacherId) {
    this.teacherId = teacherId;
  }
}