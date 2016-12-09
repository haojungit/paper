//package net.dqsy.publics.upload;
//
//import net.dqsy.papermg.papermanager.po.PaperGuidancerecord;
//import net.dqsy.papermg.papermanager.service.PaperGuidancerecordService;
//import org.apache.struts2.ServletActionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Controller
//@Scope("prototype")
//public class DownloadController{
//    private int guidId;
//    @Autowired
//    PaperGuidancerecordService paperGuidancerecordService;
//    private String fname;
//    private ArrayList<String> fnList;
//
//    public String getFname() {
//        return reCode(this.fname);
//    }
//
//    public ArrayList<String> getFnList() {
//        return this.fnList;
//    }
//
//    public void setFnList(ArrayList<String> fnList) {
//        this.fnList = fnList;
//    }
//
//	public void setCone(int cone) {
//        this.fname = Data.fileMap.get(Integer.valueOf(cone));
//	}
//
//
//    public InputStream getDownloadFile() {
//        String strPath = "/upload/" + this.fname;
//        return ServletActionContext.getServletContext().getResourceAsStream(strPath);
//    }
//
//    public String getNameList() {
//        try {
//            String path = "/upload/paperTemplate/";
//            String realPath = ServletActionContext.getServletContext().getRealPath(path);
//            File files = new File(realPath);
//            this.fnList = new ArrayList(Arrays.asList(files.list()));
//            return "SUCCESS";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ERROR";
//    }
//
//    public InputStream getPaperTemplate() {
//        String path = "/upload/paperTemplate/";
//        String realPath = ServletActionContext.getServletContext().getRealPath(path);
//        File file = new File(realPath);
//
//        String[] test = file.list();
//        this.fname = test[0].toString();
//        String strPath = "/upload/paperTemplate/" + this.fname;
//        System.out.println(strPath);
//        return ServletActionContext.getServletContext().getResourceAsStream(strPath);
//    }
//
//    public InputStream getPaper() {
//        PaperGuidancerecord paperGuidancerecord = (PaperGuidancerecord) this.paperGuidancerecordService
//                .findById(this.guidId);
//        String realPath = paperGuidancerecord.getFilePath();
//        File file = new File(realPath);
//        this.fname = file.list()[0].toString();
//        String strPath = realPath.substring(
//                realPath.indexOf(new StringBuilder(String.valueOf(File.separator)).append("upload").toString()))
//                + File.separator + this.fname;
//        return ServletActionContext.getServletContext().getResourceAsStream(strPath);
//    }
//
//    public String execute() throws Exception {
//        return "success";
//    }
//
//    private String reCode(String str) {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        String Agent = request.getHeader("User-Agent");
//        String filename = str;
//        if (Agent != null) {
//            Agent = Agent.toLowerCase();
//            try {
//                if (Agent.indexOf("firefox") != -1)
//                    filename = new String(str.getBytes(), "iso8859-1");
//                else if (Agent.indexOf("msie") != -1)
//                    filename = URLEncoder.encode(str, "UTF-8");
//                else
//                    filename = URLEncoder.encode(str, "UTF-8");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return filename;
//    }
//
//    public int getGuidId() {
//        return this.guidId;
//    }
//
//    public void setGuidId(int guidId) {
//        this.guidId = guidId;
//    }
//
//}
