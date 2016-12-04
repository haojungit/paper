package net.dqsy.publics.upload;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import net.dqsy.papermg.papermanager.po.PaperGuidancerecord;
import net.dqsy.papermg.papermanager.service.PaperGuidancerecordService;
import net.dqsy.papermg.sysmanager.service.PaperStudentService;
import net.dqsy.papermg.sysmanager.service.PaperTeacherService;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Conversion
@Scope("prototype")
public class UploadAction extends ActionSupport {

    @Autowired
    private PaperGuidancerecordService paperGuidancerecordService;
    @Autowired
    private PaperStudentService paperStudentService;
    @Autowired
    private PaperTeacherService paperTeacherService;
    private File upload;
    private String titleId;
    private String guideTimes;
    private String uploadFileName;
    private String message;

    public String uploadPaperTemplate() {
        String realPath = getRealPath(File.separatorChar + "upload" +
                File.separatorChar + "paperTemplate" + File.separatorChar);
        delAllFile(realPath);
        return uploadToPath(realPath);
    }

    public String uploadPaper() {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy" +
                    File.separator + "MM" + File.separator + "dd");
            String dateString = formatter.format(date);
            String path = getRealPath(File.separatorChar + "upload" +
                    File.separatorChar + "papers" + File.separatorChar +
                    dateString + File.separatorChar + this.titleId +
                    File.separatorChar + this.guideTimes);

            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            else {
                delAllFile(path);
            }
            PaperGuidancerecord paperGuidancerecord = new PaperGuidancerecord();
            paperGuidancerecord.setGuidanceUpDate(date);
            paperGuidancerecord.setFilePath(path);

            PaperGuidancerecord paperGuidancerecord1 = null;
            List list = this.paperGuidancerecordService.findWithTitleId(
                    Integer.parseInt(this.titleId));

            if (list.size() != 0) {
                paperGuidancerecord1 = (PaperGuidancerecord) list.get(list
                        .size() - 1);
                if (paperGuidancerecord1.getGuidance() != null)
                    this.paperGuidancerecordService.saveWithTitleId(
                            Integer.parseInt(this.titleId), paperGuidancerecord);
            } else {
                this.paperGuidancerecordService.saveWithTitleId(
                        Integer.parseInt(this.titleId), paperGuidancerecord);
            }
            return uploadToPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public String uploadExcel() {
        try {
            String realPath = getRealPath(File.separatorChar + "upload" +
                    File.separatorChar + "bulkImport" + File.separatorChar);
            delAllFile(realPath);
            File target = new File(realPath, this.uploadFileName);

            FileUtils.copyFile(this.upload, target);
        } catch (IOException e) {
            e.printStackTrace();
            this.message = "上传失败!";
        }
        this.message = "上传成功!";
        return "SUCCESS";
    }

    public String importStudent() {
        String path = File.separatorChar + "upload" + File.separatorChar +
                "bulkImport" + File.separatorChar;
        String realPath = getRealPath(path);
        File files = new File(getRealPath(path));
        List fnList = new ArrayList(Arrays.asList(files.list()));
        System.out.println("ImportStudentFileSize:" + fnList.size());
        if (fnList.size() == 1)
            this.message = this.paperStudentService.importStudent(realPath +
                    File.separator + fnList.get(0));
        else
            this.message = "导入失败,可能是文件不存在!";
        return "SUCCESS";
    }

    public String importTeacher() {
        String path = File.separatorChar + "upload" + File.separatorChar +
                "bulkImport" + File.separatorChar;
        String realPath = getRealPath(path);
        File files = new File(realPath);
        List fnList = new ArrayList(Arrays.asList(files.list()));
        if (fnList.size() == 1) {
            this.message = this.paperTeacherService.importTeacher(realPath +
                    File.separator + fnList.get(0));
        } else
            this.message = "导入失败,可能是文件不存在!";
        return "SUCCESS";
    }

    public String uploadToPath(String realPath) {
        try {
            System.out.println("上传文件到目录： " + realPath + " , 文件名：" +
                    this.uploadFileName);
            File target = new File(realPath, this.uploadFileName);

            FileUtils.copyFile(this.upload, target);
        } catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public File getUpload() {
        return this.upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return this.uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String delPaperTemplate() {
        File file = new File(getRealPath(new StringBuilder(String.valueOf(File.separatorChar)).append("upload")
                .append(File.separatorChar).append("paperTemplate").append(File.separatorChar).toString()) + File.separator + this.uploadFileName);
        if (!file.exists()) return "error";
        if (file.isFile()) file.delete();
        return "SUCCESS";
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator))
                temp = new File(path + tempList[i]);
            else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    private String getRealPath(String path) {
        String realPath = ServletActionContext.getServletContext().getRealPath(
                path);
        return realPath;
    }

    public String getTitleId() {
        return this.titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getGuideTimes() {
        return this.guideTimes;
    }

    public void setGuideTimes(String guideTimes) {
        this.guideTimes = guideTimes;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}