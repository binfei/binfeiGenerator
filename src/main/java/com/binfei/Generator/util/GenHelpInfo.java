package com.binfei.Generator.util;

import com.binfei.Generator.entity.EntityDto;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class GenHelpInfo {
    static String templateDir = "\\src\\main\\resources\\template";//模板位置
    static String sourcePath = System.getProperty("user.dir") + templateDir;//项目路径+模板位置
    static String targetBasePath = "D:\\binfeiOut\\";
    static String userName = "";

    public static void genFile(String Template,String targetPath,String targetFile,String msg,EntityDto entityDto) throws Exception {
        redConfig();
        //配置文件类
        Properties pro = new Properties();
        pro.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, sourcePath);//设置模板路径

        VelocityEngine ve = new VelocityEngine(pro);

        VelocityContext context = new VelocityContext();

        context.put("userName", userName);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        context.put("createDate", df.format(new Date()));
//        EntityDto entityDto = DBHelpInfo.genSetEntityDto();
        context.put("entityDto", entityDto);

        Template t = ve.getTemplate(Template, "UTF-8");

        //输出
//        String targetFile = entityDto.getClassName() + "BaseMapper.java";
        File file = new File(targetBasePath+targetPath+"\\", targetFile);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();
        FileOutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream,
                "UTF-8");
        BufferedWriter sw = new BufferedWriter(writer);
        t.merge(context, sw);
        sw.flush();
        sw.close();
        outStream.close();
        System.out.println(msg
                + (targetBasePath+targetPath+"\\" + targetFile).replaceAll("/", "\\\\"));
    }

    public static void redConfig(){
        Properties prop = new Properties();
        try {
            //装载配置文件
            prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\GeneratorConfig.properties")));
            userName=prop.getProperty("userName");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
