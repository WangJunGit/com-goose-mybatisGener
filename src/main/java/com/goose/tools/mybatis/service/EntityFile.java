package com.goose.tools.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.goose.tools.mybatis.entity.FrameInfo;
import com.goose.tools.mybatis.entity.TableProperties;
import com.goose.tools.mybatis.utils.MyUtils;


public class EntityFile
{
    public static void createFile(String tableName, String table_comment, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            if (table_comment.indexOf("\n") > 0)
            {
                table_comment = table_comment.substring(0, table_comment.indexOf("\n") - 1);
            }
            String className = MyUtils.formatToClassName(tableName);
            out = new PrintWriter(info.getPath_entity() + className + ".java", info.getEncode());
            out.println("package " + info.getPackage_entity() + ";");
            out.println();
            for (TableProperties tableProperties : list)
            {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("import java.util.Date;");
                    break;
                }
            }
            for (TableProperties tableProperties : list)
            {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("BigInteger".equals(type))
                {
                    out.println("import java.math.BigInteger;");
                    break;
                }
            }
            out.println("import java.io.Serializable;");
            out.println();
            out.println("/**");
            out.println(" * " + table_comment);
            out.println(" */");
            out.println();
            out.println("public class " + className + " implements Serializable");
            out.println("{");
            out.println();
            out.println("    private static final long serialVersionUID = 1L;");
            out.println();
            for (TableProperties tableProperties : list)
            {
                String comment = tableProperties.getComment();
                if (comment.indexOf("\n") > 0)
                {
                    comment = comment.substring(0, comment.indexOf("\n") - 1);
                }
                out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + ";//" + comment);
                out.println();
            }
            for (TableProperties tableProperties : list)
            {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                if ("Date".equals(type))
                {
                    out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + "_F;//" + tableProperties.getComment() + "，用于按时间查询，如果此字段有值，可查询出>此字段值的记录");
                    out.println();
                    out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + "_T;//" + tableProperties.getComment() + "，用于按时间查询，如果此字段有值，可查询出<此字段值的记录");
                    out.println();
                    out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + "_FE;//" + tableProperties.getComment() + "，用于按时间查询，如果此字段有值，可查询出>=此字段值的记录");
                    out.println();
                    out.println("    private " + MyUtils.formatDataType(tableProperties.getDateType()) + " " + MyUtils.formatToVarName(tableProperties.getName()) + "_TE;//" + tableProperties.getComment() + "，用于按时间查询，如果此字段有值，可查询出<=此字段值的记录");
                    out.println();
                }
            }
            String varNameTemp = "";
            String classNameTemp = "";
            for (TableProperties tableProperties : list)
            {
                varNameTemp = MyUtils.formatToVarName(tableProperties.getName());
                classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
                out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "()");
                out.println("    {");
                out.println("        return " + varNameTemp + ";");
                out.println("    }");
                out.println("");
                out.println("    public void set" + classNameTemp + "(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + ")");
                out.println("    {");
                out.println("        this." + varNameTemp + " = " + varNameTemp + ";");
                out.println("    }");
                out.println();
            }
            for (TableProperties tableProperties : list)
            {
                String type = MyUtils.formatDataType(tableProperties.getDateType());
                varNameTemp = MyUtils.formatToVarName(tableProperties.getName());
                classNameTemp = MyUtils.formatToClassName(tableProperties.getName());
                if ("Date".equals(type))
                {
                    out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "_F()");
                    out.println("    {");
                    out.println("        return " + varNameTemp + "_F;");
                    out.println("    }");
                    out.println("");
                    out.println("    public void set" + classNameTemp + "_F(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + "_F)");
                    out.println("    {");
                    out.println("        this." + varNameTemp + "_F = " + varNameTemp + "_F;");
                    out.println("    }");
                    out.println();
                    out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "_T()");
                    out.println("    {");
                    out.println("        return " + varNameTemp + "_T;");
                    out.println("    }");
                    out.println("");
                    out.println("    public void set" + classNameTemp + "_T(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + "_T)");
                    out.println("    {");
                    out.println("        this." + varNameTemp + "_T = " + varNameTemp + "_T;");
                    out.println("    }");
                  //新增大于小于的日期判断
                    out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "_FE()");
                    out.println("    {");
                    out.println("        return " + varNameTemp + "_FE;");
                    out.println("    }");
                    out.println("");
                    out.println("    public void set" + classNameTemp + "_FE(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + "_FE)");
                    out.println("    {");
                    out.println("        this." + varNameTemp + "_FE = " + varNameTemp + "_FE;");
                    out.println("    }");
                    out.println();
                    out.println("    public " + MyUtils.formatDataType(tableProperties.getDateType()) + " get" + classNameTemp + "_TE()");
                    out.println("    {");
                    out.println("        return " + varNameTemp + "_TE;");
                    out.println("    }");
                    out.println("");
                    out.println("    public void set" + classNameTemp + "_TE(" + MyUtils.formatDataType(tableProperties.getDateType()) + " " + varNameTemp + "_TE)");
                    out.println("    {");
                    out.println("        this." + varNameTemp + "_TE = " + varNameTemp + "_TE;");
                    out.println("    }");
                    out.println();
                }
            }
            out.println("    public String toString()");
            out.println("    {");
            out.print("        return ");
            boolean is_first = true;
            for (TableProperties tableProperties : list)
            {
                if (is_first)
                {
                    out.print("this." + MyUtils.formatToVarName(tableProperties.getName()));
                    is_first = false;
                } else {
                    out.print(" + \"^\" + this." + MyUtils.formatToVarName(tableProperties.getName()));
                }
            }
            out.println(";");
            out.println("    }");
            out.println("}");
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
