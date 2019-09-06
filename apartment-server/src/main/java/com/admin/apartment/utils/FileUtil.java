package com.admin.apartment.utils;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.javassist.runtime.Inner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.*;
import java.net.SocketException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author liangming
 *
 * (已废弃)
 */
@Component
public class FileUtil {

    private static final Log LOGGER = LogFactory.get();

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
     * 图片不剪裁
     * @param file
     * @param fileName
     * @return
     */
    public boolean notCutImage(MultipartFile file,String fileName){
        boolean flag = false;
        try {
            InputStream inputStream =  new ByteArrayInputStream(file.getBytes());
            flag = ftpUpload(inputStream,fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 读取图片转 base64
     * @param fileName
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String download(String fileName) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        FTPClient ftpClient = new FTPClient();
        try {
            //服务器地址和端口
            ftpClient.connect(FTP_IP,FTP_PORT);
            //登录的用户名和密码
            ftpClient.login(FTP_NAME,FTP_PWS);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            // 获取远程ftp上指定文件的InputStream
            is = ftpClient.retrieveFileStream(fileName);
            if (null == is) {
                throw new FileNotFoundException(fileName);
            }
            bos = new ByteArrayOutputStream();
            int length;
            byte[] buf = new byte[2048];
            while (-1 != (length = is.read(buf, 0, buf.length))) {
                bos.write(buf, 0, length);
            }
            ByteArrayInputStream fis = new ByteArrayInputStream(
                    bos.toByteArray());
            bos.flush();
            is.close();
            bos.close();
            byte[] buffer = new byte[fis.available()];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file ");
            }
            fis.close();
            BASE64Encoder encoder = new BASE64Encoder();
            String imgStr = encoder.encode(buffer);
            return imgStr;     //超长的
        } catch (Exception e) {
            LOGGER.error("ftp通过文件名称获取远程文件流", e);
        } finally {
            try {
                is.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除图片
     * @param dir
     * @param fileName
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean del(String fileName,String dir) {
        FTPClient ftpClient = new FTPClient();
        try {
            //服务器地址和端口
            ftpClient.connect(FTP_IP,FTP_PORT);
            //登录的用户名和密码
            ftpClient.login(FTP_NAME,FTP_PWS);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.changeWorkingDirectory(dir);
            // 删除
            ftpClient.sendCommand("dele " + fileName + "\r\n");
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
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
            System.out.println(ftp.getReplyCode()); //250 表示删除成功
            System.out.println(flag);  //flag==true 表示成功
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
