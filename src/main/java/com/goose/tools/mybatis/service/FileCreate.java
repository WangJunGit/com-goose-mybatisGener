package com.goose.tools.mybatis.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.goose.tools.mybatis.dao.DbBean;
import com.goose.tools.mybatis.entity.FrameInfo;
import com.goose.tools.mybatis.entity.TableProperties;
import com.goose.tools.mybatis.utils.Data;
import com.goose.tools.mybatis.utils.MyUtils;

public class FileCreate
{
    private static String tempPath = "";

    public static boolean start(FrameInfo info)
    {
        try
        {
            String base_schema = "";
            String base_bao = info.getPackage_base();
            String base_mapper=info.getPackage_base()+".mapper";
            String package_entity=info.getPackage_base()+".entity";
            String[] name = info.getSchema().split(",");
            for (String string2 : name)
            {
                info.setSchema(base_schema + string2);
                info.setPackage_base(base_bao);
                info.setPackage_mapper(base_mapper);
                info.setPackage_entity(package_entity);
                //info.setEncode("GBK");
                info.setEncode("UTF-8");
                DbBean.setMysql_url("jdbc:mysql://" + info.getIp() + ":" + info.getPort() + "/" + info.getSchema());
                DbBean.setMysql_user(info.getUser());
                DbBean.setMysql_passwd(info.getPassword());
                formatFrameInfo(info);
                createLocalPath(info.getPath_entity());
//                createLocalPath(info.getPath_config());
                createLocalPath(info.getPath_core_config());
                createLocalPath(info.getPath_query_config());
//                createLocalPath(info.getPath_mapper());
                createLocalPath(info.getPath_query_mapper());
                createLocalPath(info.getPath_core_mapper());
                createLocalPath(tempPath + "core-service/src/main/resources/dao/");
//            createLocalPath(info.getPath_interface());
//            createLocalPath(info.getPath_impl());
//            createLocalPath(info.getPath_test());
                MySql mySql = new MySql();
                List<String> list = mySql.listAllTables(info.getSchema());
                for (String string : list)
                {
                    String table_name = string.substring(0, string.indexOf("#####"));
                    String table_comment = string.substring(string.indexOf("#####") + 5);
                    List<TableProperties> list2 = mySql.listTableProperties(info.getSchema(), table_name);
                    EntityFile.createFile(table_name, table_comment, list2, info);
//                    Mapper.createFile(table_name, list2, info);
                    QueryMapper.createFile(table_name, list2, info);
                    CoreMapper.createFile(table_name, list2, info);
//                    ConfigFile.createFile(table_name, list2, info);
                    QueryCofigFile.createFile(table_name, list2, info);
                    CoreConfigFile.createFile(table_name, list2, info);
//                InterfaceFile.createFile(table_name, list2, info);
//                Impl.createFile(table_name, list2, info);
                    //TODO 暂缺                Test.createFile(table_name, list2, info);
                }
                createFile(list, info, tempPath + "core-service/src/main/resources/dao/");
                createFile(list, info, tempPath + "query-service/src/main/resources/dao/");
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private static void formatFrameInfo(FrameInfo info)
    {
        String base_path = Data.getProjectLocalPath();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        base_path = base_path + "/" + df.format(new Date()) + "/";
        base_path = base_path + "/";
        tempPath = base_path;
        String temp = "";
        String temp_package = info.getPackage_base().substring(0, info.getPackage_base().lastIndexOf("."));
        String temp_path = genePath(base_path, temp_package);
//        info.setPackage_entity(temp_package + ".entity" + "." + temp);
//        info.setPackage_config(temp_package + ".config" + "." + temp);
//        info.setPackage_mapper(temp_package + ".mapper" + "." + temp);
//        info.setPackage_interface(temp_package + ".service" + "." + temp);
//        info.setPackage_impl(temp_package + ".service" + "." + temp + ".impl");
//        info.setPackage_test(temp_package + ".test" + "." + temp);
//        info.setPath_base(genePath(base_path, info.getPackage_base()));
//        info.setPath_entity(temp_path + "entity/" + temp + "/");
//        info.setPath_config(temp_path + "config/" + temp + "/");
//        info.setPath_mapper(temp_path + "mapper/" + temp + "/");
//        info.setPath_interface(temp_path + "service/" + temp + "/");
//        info.setPath_impl(temp_path + "service/" + temp + "/impl/");
//        info.setPath_test(temp_path + "test/" + temp + "/");
        
        
        info.setPackage_entity(info.getPackage_entity()  + temp);
        info.setPackage_mapper(info.getPackage_mapper()+ temp);
        info.setPath_entity(base_path + "api/src/main/java/"+genePath(info.getPackage_base())+"/entity/" + temp + "/");
        info.setPath_mapper(base_path + "core-service/src/main/java/"+genePath(info.getPackage_base())+"/mapper/" + temp + "/");
        
        info.setPath_query_mapper(base_path + "query-service/src/main/java/"+genePath(info.getPackage_base())+"/mapper/" + temp + "/");
        info.setPath_core_mapper(base_path + "core-service/src/main/java/"+genePath(info.getPackage_base())+"/mapper/" + temp + "/");

        String temp1 = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
        info.setPackage_config("dao.sqlmap." + temp1);
//        info.setPath_config(base_path + "dao/sqlmap/" + temp1 + "/");
        info.setPath_config(base_path + "core-service/src/main/resources/dao/sqlmap/" + temp1 + "/");
        
        info.setPath_query_config(base_path + "query-service/src/main/resources/dao/sqlmap/" + temp1 + "/");
        info.setPath_core_config(base_path + "core-service/src/main/resources/dao/sqlmap/" + temp1 + "/");
        
        Gson gson = new Gson();
        Data.writeToSys("mybatis-5623487", gson.toJson(info));
    }

    private static String genePath(String base_path, String src)
    {
        String[] tmp = src.split("\\.");
        String path = base_path;
        for (String string : tmp)
        {
            path += string + "/";
        }
        return path;
    }
    
    private static String genePath(String src)
    {
        String[] tmp = src.split("\\.");
        String path = "";
        for (String string : tmp)
        {
            path += string + "/";
        }
        return path;
    }

    /**
     * 创建本地目录
     * @param path	目录
     * @return		true-成功 false-失败
     */
    public static boolean createLocalPath(String path) throws Exception
    {
        java.io.File f = new java.io.File(path);
        if (f.exists())
        {
            System.out.println("Local directory is already exist:" + path);
            return true;
        }
        if (f.mkdirs())
        {
            System.out.println("create Local directory success:" + path);
            return true;
        }
        else
        {
            System.out.println("create Local directory fail:" + path);
            return false;
        }
    }

    /**
     * 删除本地目录及该目录下所有文件
     * @param path	目录
     * @return		true-成功 false-失败
     */
    public static boolean deleteLocalPath(String path)
    {
        if (path == null || path.equals(""))
        {
            return false;
        }
        java.io.File f = new java.io.File(path);
        if (!f.exists())
        {
            System.out.println("Local directory is not exist:" + path);
            return true;
        }
        String[] tmp = f.list();
        for (int i = 0; tmp != null && i < tmp.length; i++)
        {
            deleteLocalPath(path + "/" + tmp[i]);
        }
        if (f.delete())
        {
            System.out.println("delete Local directory success:" + path);
            return true;
        }
        else
        {
            System.out.println("delete Local directory fail:" + path);
            return false;
        }
    }

    public static void createFile(List<String> list, FrameInfo info, String path) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String temp = info.getPackage_base().substring(info.getPackage_base().lastIndexOf(".") + 1);
            out = new PrintWriter(path + "mybatis-" + temp + ".xml", info.getEncode());
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            out.println("<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">");
            out.println("<configuration>");
            out.println("  <mappers>");
            for (String string : list)
            {
                String table_name = string.substring(0, string.indexOf("#####"));
                out.println("    <mapper resource=\"dao/sqlmap/" + temp + "/" + MyUtils.formatToClassName(table_name) + "-Mapper.xml\" /> ");
            }
            out.println("  </mappers>");
            out.println("</configuration>");
            out.flush();
            out.close();
            out = null;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
}
