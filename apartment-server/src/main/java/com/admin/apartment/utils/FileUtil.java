package com.admin.apartment.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class FileUtil {

    @Value("${upload.ftpIp}")
    private String FTP_IP;

    @Value("${upload.ftpport}")
    private Integer FTP_PORT;

    @Value("${upload.ftpname}")
    private String FTP_NAME;

    @Value("${upload.ftppws}")
    private String FTP_PWS;

    @Value("${upload.ftppath}")
    private String FTP_BASEPATH;

    /**
     * 图片裁剪方法 在我的Java图片裁剪博客有详细介绍
     * @param file
     * @param fileName
     * @param suffix
     * @param width
     * @param height
     * @return
     */
    public boolean cutImage(MultipartFile file,String fileName,String suffix, int width,int height){
        int x = 0;
        int y = 0;
        boolean flag = false;
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            BufferedImage bf = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
            bf.getGraphics().drawImage(bufferedImage,x,y,width,height,null);
            ByteArrayOutputStream baot = new ByteArrayOutputStream();
            ImageIO.write(bf,suffix,baot);
            byte[]  buff =  baot.toByteArray();
            InputStream inputStream =  new ByteArrayInputStream(buff);
            flag = ftpUpload(inputStream,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return flag;
    }

    /**
     * 图片上传方法
     * @param inputStream
     * @param fileName
     * @return
     */
    public boolean ftpUpload(InputStream inputStream, String fileName){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            int reply;
            //服务器地址和端口
            ftpClient.connect(FTP_IP,FTP_PORT);
            //ftpClient.enterLocalPassiveMode();
            //登录的用户名和密码
            ftpClient.login(FTP_NAME,FTP_PWS);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return flag;
            }
            //ftp不会报错只会返回状态码，可以根据状态码查找错误
            System.out.println(reply);
            ftpClient.enterLocalPassiveMode();

            //设置文件类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //设置上传路径
            ftpClient.changeWorkingDirectory(FTP_BASEPATH);

            //读取本地文件，给出的是本地文件地址
            //InputStream inputStream = file.getInputStream();
            //1.服务器端保存的文件名，fileName
            //2.上传文件的inputstream
            ftpClient.storeFile(fileName,inputStream);
            inputStream.close();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }finally {
            try {
                ftpClient.logout();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return flag;
    };

    /**
     * 删除服务器上的图片 登陆图片服务器后 到相应的目录下根据图片名称删除图片
     * @param FileName
     * @return
     */
    public boolean deleteFile(String FileName) {
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            // 连接FTP服务器
            ftp.connect(FTP_IP, FTP_PORT);
            // 登录
            ftp.login(FTP_NAME, FTP_PWS);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return flag;
            }
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH);
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            flag = ftp.deleteFile(FileName);
            //System.out.println(ftp.getReplyCode()); //250 表示删除成功
            //System.out.println(flag);  //flag==true 表示成功
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }

    /**
     * 文件重新命名
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName){
        //获取文件类型
        String type = fileName.substring(fileName.lastIndexOf("."));
        //获取文件名字
        String oldname = fileName.substring(0,fileName.indexOf("."));
        String newname = String.valueOf(Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())).getTime())+oldname.hashCode();
        return newname+type;
    }

    /**
     * 获取服务器图片名字
     * @param fileUrl
     * @return
     */
    public static String getFile(String fileUrl){
        String filName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        return filName;
    }
}
