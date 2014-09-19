package com.goose.tools.mybatis.entity;

public class FrameInfo {

	private String ip;
	private String port;
	private String schema;
	private String user;
	private String password;
	private String package_base;
	private String package_entity;
    private String package_config;
    private String package_mapper;
    private String package_interface;
    private String package_impl;
    private String package_test;
	private String path_base;
	private String path_entity;
	private String path_config;
	private String path_query_config;
	private String path_core_config;
	private String path_mapper;
	private String path_query_mapper;
	private String path_core_mapper;
	private String path_interface;
	private String path_impl;
	private String path_test;
	private String encode;
    public String getEncode()
    {
        return encode;
    }
    public void setEncode(String encode)
    {
        this.encode = encode;
    }
    public String getIp()
    {
        return ip;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    public String getPort()
    {
        return port;
    }
    public void setPort(String port)
    {
        this.port = port;
    }
    public String getSchema()
    {
        return schema;
    }
    public void setSchema(String schema)
    {
        this.schema = schema;
    }
    public String getUser()
    {
        return user;
    }
    public void setUser(String user)
    {
        this.user = user;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPackage_base()
    {
        return package_base;
    }
    public void setPackage_base(String packageBase)
    {
        package_base = packageBase;
    }
    public String getPackage_entity()
    {
        return package_entity;
    }
    public void setPackage_entity(String packageEntity)
    {
        package_entity = packageEntity;
    }
    public String getPackage_config()
    {
        return package_config;
    }
    public void setPackage_config(String packageConfig)
    {
        package_config = packageConfig;
    }
    public String getPackage_mapper()
    {
        return package_mapper;
    }
    public void setPackage_mapper(String packageMapper)
    {
        package_mapper = packageMapper;
    }
    public String getPackage_interface()
    {
        return package_interface;
    }
    public void setPackage_interface(String packageInterface)
    {
        package_interface = packageInterface;
    }
    public String getPackage_impl()
    {
        return package_impl;
    }
    public void setPackage_impl(String packageImpl)
    {
        package_impl = packageImpl;
    }
    public String getPackage_test()
    {
        return package_test;
    }
    public void setPackage_test(String packageTest)
    {
        package_test = packageTest;
    }
    public String getPath_base()
    {
        return path_base;
    }
    public void setPath_base(String pathBase)
    {
        path_base = pathBase;
    }
    public String getPath_entity()
    {
        return path_entity;
    }
    public void setPath_entity(String pathEntity)
    {
        path_entity = pathEntity;
    }
    public String getPath_config()
    {
        return path_config;
    }
    public void setPath_config(String pathConfig)
    {
        path_config = pathConfig;
    }
    public String getPath_mapper()
    {
        return path_mapper;
    }
    public void setPath_mapper(String pathMapper)
    {
        path_mapper = pathMapper;
    }
    public String getPath_interface()
    {
        return path_interface;
    }
    public void setPath_interface(String pathInterface)
    {
        path_interface = pathInterface;
    }
    public String getPath_impl()
    {
        return path_impl;
    }
    public void setPath_impl(String pathImpl)
    {
        path_impl = pathImpl;
    }
    public String getPath_test()
    {
        return path_test;
    }
    public void setPath_test(String pathTest)
    {
        path_test = pathTest;
    }
	public String getPath_query_mapper() {
		return path_query_mapper;
	}
	public void setPath_query_mapper(String pathQueryMapper) {
		path_query_mapper = pathQueryMapper;
	}
	public String getPath_core_mapper() {
		return path_core_mapper;
	}
	public void setPath_core_mapper(String pathCoreMapper) {
		path_core_mapper = pathCoreMapper;
	}
	public String getPath_query_config() {
		return path_query_config;
	}
	public void setPath_query_config(String pathQueryConfig) {
		path_query_config = pathQueryConfig;
	}
	public String getPath_core_config() {
		return path_core_config;
	}
	public void setPath_core_config(String pathCoreConfig) {
		path_core_config = pathCoreConfig;
	}
	@Override
	public String toString() {
		return "FrameInfo [ip=" + ip + ", port=" + port + ", schema=" + schema
				+ ", user=" + user + ", password=" + password
				+ ", package_base=" + package_base + ", package_entity="
				+ package_entity + ", package_config=" + package_config
				+ ", package_mapper=" + package_mapper + ", package_interface="
				+ package_interface + ", package_impl=" + package_impl
				+ ", package_test=" + package_test + ", path_base=" + path_base
				+ ", path_entity=" + path_entity + ", path_config="
				+ path_config + ", path_query_config=" + path_query_config
				+ ", path_core_config=" + path_core_config + ", path_mapper="
				+ path_mapper + ", path_query_mapper=" + path_query_mapper
				+ ", path_core_mapper=" + path_core_mapper
				+ ", path_interface=" + path_interface + ", path_impl="
				+ path_impl + ", path_test=" + path_test + ", encode=" + encode
				+ "]";
	}  
	
}
