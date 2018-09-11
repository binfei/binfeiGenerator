package com.binfei.Generator.util;

import com.binfei.Generator.entity.Column;
import com.binfei.Generator.entity.EntityDto;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelpInfo {

    private static  String driver ="";
    private static  String pwd="";
    private static  String user="";
    private static  String url = "";
    private static  String packageName = "";
    private static  String tableNamep = "";
    private static Connection getConnection=null;

    public static void main(String[] args) {
//        EntityDto entityDto=   genSetEntityDto("SYS_USER");
        redConfig();
    }

    public static void redConfig(){
        Properties prop = new Properties();
        try {
            //装载配置文件
            prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\GeneratorConfig.properties")));

            driver=prop.getProperty("driver");
            user=prop.getProperty("user");
            pwd=prop.getProperty("pwd");
            url=prop.getProperty("url")+ "?user=" + user + "&password=" + pwd
                    + "&useUnicode=true&characterEncoding=UTF-8";
            packageName=prop.getProperty("packageName");
            tableNamep=prop.getProperty("tableName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static EntityDto genSetEntityDto(){
        redConfig();
        FileSystemView fsv=FileSystemView.getFileSystemView();
        String path=fsv.getHomeDirectory().toString();//获取当前用户桌面路径
        getConnection=getConnections();
        EntityDto entityDto=new EntityDto();
        try {

            DatabaseMetaData dbmd=getConnection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", tableNamep, new String[] { "TABLE" });
            while (resultSet.next()) {
                String tableName=resultSet.getString("TABLE_NAME");
                //System.out.println(tableName);
                //if(tableName.equals("sys_user")){
                //其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
                //ResultSet rs =getConnection.getMetaData().getColumns(null, getXMLConfig.getSchema(),tableName.toUpperCase(), "%");
                ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                File directory = new File(path);
                FileWriter fw = new FileWriter(directory+ "\\"+tableName.toUpperCase()+".xml");
                PrintWriter pw = new PrintWriter(fw);
                System.out.println("表名："+tableName+"\t\n表字段信息：");
                pw.write("<p filid=\"xx\" table=\""+tableName.toUpperCase()+"\" zj=\"xx\"/>\n");
                List<Column> columns=new ArrayList<Column>();
                while(rs.next()){
                    pw.write("\t<p code=\""+rs.getString("COLUMN_NAME").toUpperCase()+"\" name=\""+rs.getString("REMARKS")+"\"/>\n");
                    System.out.println("字段名："+rs.getString("COLUMN_NAME")+"\t属性名:"+camelName(rs.getString("COLUMN_NAME"))+"\t字段属性:"+getFieldType(rs.getString("TYPE_NAME"))+"\t字段注释："+rs.getString("REMARKS")+"\t字段数据类型："+rs.getString("TYPE_NAME"));
                    Column column=new Column();
                    column.setColumnName(rs.getString("COLUMN_NAME"));
                    if("INT".equals(rs.getString("TYPE_NAME"))){
                        column.setJdbcType("INTEGER");
                    }else if("DATETIME".equals(rs.getString("TYPE_NAME"))){
                        column.setJdbcType("TIMESTAMP");
                    }else{
                        column.setJdbcType(rs.getString("TYPE_NAME"));
                    }

                    column.setPropertyName(camelName(rs.getString("COLUMN_NAME")));
                    String ptSnd[]=getFieldType(rs.getString("TYPE_NAME"));
                    column.setPropertyType(ptSnd[1]);
                    column.setIsSND(ptSnd[0]);
                    column.setRemarks(rs.getString("REMARKS"));
                    columns.add(column);

                }
                pw.write("</p>");
                pw.flush();
                pw.close();
                entityDto.setTableName(tableName);
                entityDto.setColumns(columns);
                String  tableNameStr=tableName.substring(tableName.indexOf("_")+1);
                String tabStr=camelName(tableNameStr);
                entityDto.setClassName(tabStr.substring(0, 1).toUpperCase()+tabStr.substring(1).toLowerCase());
                entityDto.setPropertyName(tabStr);
                entityDto.setPackageName(packageName);
                entityDto.setModeName(tableName.substring(0,tableName.indexOf("_")));
                ResultSet result2 = dbmd.getPrimaryKeys(
                        null, null, tableName);
                String primaryKey="";
                while(result2.next()){
                    entityDto.setPrimaryKey(result2.getString(4));
                    entityDto.setPrimaryPropertyKey(camelName(result2.getString(4)));
                }
                System.out.println("生成成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityDto;
    }

    public static Connection getConnections(){
        try {
            //Properties props =new Properties();
            //props.put("remarksReporting","true");
            Class.forName(driver);
            getConnection= DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getConnection;
    }
    ////其他数据库不需要这个方法 oracle和db2需要
    public static String getSchema() throws Exception {
        String schema;
        schema =getConnection.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }

    /**
     * 设置字段类型 MySql数据类型
     *
     * @param columnType
     *            列类型字符串
     * @return
     */
    public static String [] getFieldType(String columnType) {
        String [] result={"",""};
        columnType = columnType.toLowerCase();

        if (columnType.equals("varchar") || columnType.equals("nvarchar")|| columnType.equals("char")|| columnType.equals("tinytext")|| columnType.equals("text")
                || columnType.equals("mediumtext")|| columnType.equals("longtext")) {
            result[0]="s";
            result[1]="String";
            return result;
        } else if (columnType.equals("tinyblob")
                ||columnType.equals("blob")
                ||columnType.equals("mediumblob")
                ||columnType.equals("longblob")) {
            result[0]="s";
            result[1]="byte[]";
            return result;
        } else if (columnType.equals("datetime")||columnType.equals("date")||columnType.equals("timestamp")||columnType.equals("time")||columnType.equals("year")) {
//            sbpackage.append("import java.util.Date;\r\n");
            result[0]="d";
            result[1]="Date";
            return result;
        } else if (columnType.equals("bit") ||columnType.equals("int")||columnType.equals("tinyint") ||columnType.equals("smallint")||columnType.equals("integer")
//                ||columnType.equals("bool")
//                ||columnType.equals("mediumint")
        ){
            result[0]="n";
            result[1]="Integer";
            return result;
        }else if (columnType.equals("float")) {
            result[0]="n";
            result[1]="Float";
            return result;
        } else if (columnType.equals("double")) {
            result[0]="n";
            result[1]="Double";
            return result;
        } else if (columnType.equals("decimal")) {
//            sbpackage.append("import java.math.BigDecimal;\r\n");
            result[0]="n";
            result[1]="BigDecimal";
            return result;
        }else if (columnType.equals("bigint")){
            result[0]="n";
            result[1]="Long";
            return result;
        }
        result[0]="-1";
        result[1]="-1";
        return result;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.length()==0) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.toLowerCase();
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.length()==0) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


}
