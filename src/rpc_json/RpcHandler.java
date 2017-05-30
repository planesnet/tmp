package rpc_json;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class RpcHandler {
	
	private String url, db, username, password;
	private XmlRpcClientConfigImpl object_config;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public RpcHandler(String url, String db, String username, String password) throws MalformedURLException {
		super();
		this.url = url;
		this.db = db;
		this.username = username;
		this.password = password;
		this.object_config = new XmlRpcClientConfigImpl();
		this.object_config.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
	}

	private int startServer() throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();
		common_config.setServerURL(
		    new URL(String.format("%s/xmlrpc/2/common", url)));
		Object[] params = new Object[] {db, username, password}; 
		int uid = (int)client.execute(common_config, "login", params);
		return uid;
	}
	
	public Object[] search(String model, Object[] searchParams) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
			     searchParams
			     };
		HashMap<Object,Object> paramsRules = new HashMap<Object, Object>(); 
		Object[] params = new Object[] {db, startServer(), password, model, "search", paramsMethod}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return (Object[])result;
	}
	
	public Object read(String model, Object[] searchParams, Object[] fields) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
			     search(model, searchParams) 
			     };
		//HashMap<Object,Object> paramsRules = new HashMap<Object, Object>();
		HashMap<Object,Object[]> paramsRules = new HashMap<Object, Object[]>();
		paramsRules.put("fields", fields);
		Object[] params = new Object[] {db, startServer(), password, model, "read", paramsMethod, paramsRules}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return result;
	}
	
	public Object searchRead(String model, Object[] searchParams, Object[] fields) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
			     searchParams 
			     };
		//HashMap<Object,Object> paramsRules = new HashMap<Object, Object>();
		HashMap<Object,Object[]> paramsRules = new HashMap<Object, Object[]>();
		paramsRules.put("fields", fields);
		Object[] params = new Object[] {db, startServer(), password, model, "search_read", paramsMethod, paramsRules}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return result;
	}
	
	public Object create(String model, HashMap<String, Object> values) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
				values 
			     };
		Object[] params = new Object[] {db, startServer(), password, model, "create", paramsMethod}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return result;
	}
	
	public Object update(String model, Object[] searchParams, HashMap<String, Object> values) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
				search(model, searchParams),
				values
			     };
		Object[] params = new Object[] {db, startServer(), password, model, "write", paramsMethod}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return result;
	}
	
	public Object delete(String model, Object[] searchParams) throws MalformedURLException, XmlRpcException{
		XmlRpcClient client = new XmlRpcClient();
		Object[] paramsMethod = new Object[]{ 
				search(model, searchParams)
			     };
		Object[] params = new Object[] {db, startServer(), password, model, "unlink", paramsMethod}; 
		Object result = client.execute(object_config, "execute_kw", params);
		return result;
	}

}
