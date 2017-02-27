package com.linkwee.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionGenerator {

	public static void main(String[] args) {
		genePermissionSign();
	}
	
	public static void genePermissionSign(){
		String sql = "SELECT * FROM `tsys_permission`";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://10.16.0.200:3306/channel?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull",
					"root", "root123");
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				String permissionName = rs.getString("permission_name");
				String permissionSign = rs.getString("permission_sign");
				System.out.println("/**");
				System.out.println("* "+permissionName);
				System.out.println("*/");
				System.out.println("public static final String "+permissionSign.replace("-", "_").replace(":", "_").replace("*", "ALL").toUpperCase()+" = \""+permissionSign+"\";");
			}
			rs.close();
			conn.close();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
