package com.enzhico.jwt;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * FTPClientTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/24
 */
public class FTPClientTest {
    @Test
    public void connect() throws Exception {
        FTPClient f = new FTPClient();
        String server = "119.29.12.177";
        f.setControlEncoding("UTF-8");
        f.connect(server);
        boolean login = f.login("ftpuser1", "123456");
        System.out.println("login = " + login);
        f.setDefaultPort(11024);
        System.out.println("reply = " + f.getReplyString());
        int replyCode = f.getReplyCode();
        System.out.println("replyCode = " + replyCode);
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            f.disconnect();
            System.err.println("FTP server refused connection.");
        }
        f.enterLocalPassiveMode();
        //f.enterLocalActiveMode();
        FTPListParseEngine engine = f.initiateListParsing("");
        while (engine.hasNext()) {
            FTPFile[] files = engine.getNext(25);  // "page size" you want
            System.out.println("当前目录的文件数量 = " + files.length);
            for (FTPFile ftpFile : files) {
                System.out.println("文件名：" + ftpFile.getName());
            }
            //do whatever you want with these files, display them, etc.
            //expensive FTPFile objects not created until needed.
        }

        f.disconnect();
    }

    @Test
    public void upload() throws Exception {
        FTPClient ftpClient = new FTPClient();
        String server = "119.29.12.177";
        ftpClient.connect(server);
        ftpClient.login("ftpuser1", "123456");
        InputStream sourceStream = null;
        try {
            sourceStream = new FileInputStream(new File(""));
            //设置上传目录
            ftpClient.changeWorkingDirectory("");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile("hero.txt", sourceStream);
        } finally {
            sourceStream.close();
            ftpClient.disconnect();
        }
    }
}
