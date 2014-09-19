package com.goose.tools.mybatis.service;

import java.io.PrintWriter;
import java.util.List;

import com.goose.tools.mybatis.entity.FrameInfo;
import com.goose.tools.mybatis.entity.TableProperties;
import com.goose.tools.mybatis.utils.MyUtils;

public class Impl
{

    public static void createFile(String tableName, List<TableProperties> list, FrameInfo info) throws Exception
    {
        PrintWriter out = null;
        try
        {
            String className = MyUtils.formatToClassName(tableName);
            String varName = MyUtils.formatToVarName(tableName);
            String idName = MyUtils.formatToVarName(list.get(0).getName());
            String idType = MyUtils.formatDataType(list.get(0).getDateType());
            out = new PrintWriter(info.getPath_impl() + className + "ServiceImpl.java", info.getEncode());
            out.println("package " + info.getPackage_impl() + ";");
            out.println("");
            out.println("import java.util.List;");
            out.println("");
            out.println("import org.springframework.beans.factory.annotation.Autowired;");
            out.println("import org.springframework.stereotype.Service;");
            out.println("");
            out.println("import " + info.getPackage_entity() + "." + className + ";");
            out.println("import " + info.getPackage_mapper() + "." + className + "Mapper;");
            out.println("import " + info.getPackage_interface() + "." + className + "Service;");
            out.println("");
            out.println("@Service(value = \"" + varName + "Service\")");
            out.println("public class " + className + "ServiceImpl implements " + className + "Service");
            out.println("{");
            out.println("");
            out.println("    @Autowired");
            out.println("    " + className + "Mapper " + varName + "Mapper;");
            out.println("    ");
            out.println("    @Override");
            out.println("    public Integer count(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.count(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer add(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.add(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer deleteById(" + idType + " " + idName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.deleteById(" + idName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer deleteByExample(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.deleteByExample(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer updateAll(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.updateAll(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public Integer updateSelected(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.updateSelected(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public " + className + " findById(" + idType + " " + idName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.findById(" + idName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public List<" + className + "> findByExample(" + className + " " + varName + ")");
            out.println("    {");
            out.println("        return " + varName + "Mapper.findByExample(" + varName + ");");
            out.println("    }");
            out.println("");
            out.println("    @Override");
            out.println("    public List<" + className + "> pageByExample(" + className + " " + varName + ", Integer start, Integer size, String orderBy)");
            out.println("    {");
            out.println("        return " + varName + "Mapper.pageByExample(" + varName + ", start, size, orderBy);");
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
