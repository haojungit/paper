package net.dqsy.papermg.sysmanager.vo;

import java.io.Serializable;

public class StudentMajorVO
        implements Serializable
{
    private String valueField;
    private String studentMajor;

    public StudentMajorVO(String studentMajor) {
        this.valueField = studentMajor;
        this.studentMajor = studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentMajor() {
        return this.studentMajor;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (
                this.studentMajor == null ? 0 : this.studentMajor.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentMajorVO other = (StudentMajorVO) obj;
        if (this.studentMajor == null) {
            if (other.studentMajor != null)
                return false;
        } else if (!this.studentMajor.equals(other.studentMajor))
            return false;
        return true;
    }

    public String toString() {
        return "StudentMajorVO [studentMajor=" + this.studentMajor + "]";
    }

    public String getValueField() {
        return this.valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }
}