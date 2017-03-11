package com.homebudget.session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserSession {
	
	private static int userID = -1;
	private static String username = null;
	private static String lastLogin = null;
	public static final String DB_URL = "jdbc:mysql://localhost:3306/homebudget?useUnicode=true&characterEncoding=utf-8";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "sportberry";
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	public final static String SALT = "szyfer123";
	
	public UserSession(){
		
	}
	
	/*
	 * Error codes:
	 * -1 - wrong password/username
	 * -2 - no connection to database
	 * -3 - app error
	 */
	public static int login(String user, String password){
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			pst = con.prepareStatement("SELECT * FROM Users WHERE NAME=?;");
			pst.setString(1, user);
			rs = pst.executeQuery();
			if(rs.next()){
				String shaPassword = getSHA512(password);
				if(shaPassword != null){
					if(rs.getString(3).equals(shaPassword)){
						userID = rs.getInt(1);
						username = rs.getString(2);
						lastLogin = rs.getString(4);
						if(lastLogin == null) lastLogin = "brak";
						closeConnection();
						return 0;
					}else{
						closeConnection();
						return -1;
					}
				}else{
					closeConnection();
					return -3;
				}
			}else{
				closeConnection();
				return -1;
			}
		} catch (SQLException e) {
			return -2;
		}
	}
	
	/*
	 * Error codes:
	 * -1 - user already exists
	 * -2 - no connection to database
	 * -3 - app error
	 */
	public static int register(String user, String password){
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			pst = con.prepareStatement("SELECT * FROM Users WHERE NAME=?;");
			pst.setString(1, user);
			rs = pst.executeQuery();
			if(!rs.next()){
				
				String shaPassword = getSHA512(password);
				if(shaPassword != null){
					pst.close();
					pst = con.prepareStatement("INSERT INTO Users(NAME, PASSWORD) VALUES (?,?);");
					pst.setString(1, user);
					pst.setString(2, shaPassword);
					pst.executeUpdate();
					closeConnection();
					return 0;
				}else{
					closeConnection();
					return -3;
				}

			}else{
				closeConnection();
				return -1;
			}
		} catch (SQLException e) {
			return -2;
		}
	}
	
	public static void logout(){
		userID = -1;
		username = null;
	}
	
	public static boolean isUserLogged(){
		if(username != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getSHA512(String passwordToHash){
		String generatedPassword = null;
	    try{
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        byte[] bytes;
	        try {
	        	md.update(SALT.getBytes("UTF-8"));
				bytes = md.digest(passwordToHash.getBytes("UTF-8"));
			}catch (UnsupportedEncodingException e){
				return null;
			}
	         
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++) sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        generatedPassword = sb.toString();
	    }catch (NoSuchAlgorithmException e){
	    	return null;
	    }
	    return generatedPassword;
	}
	
	/*
	 * Error codes
	 * -1 - user not logged in
	 * -2 - no connection to database
	 */
	public static int updateLastLogin(){
		if(isUserLogged()){
			Calendar dateNow = new GregorianCalendar();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(dateNow.getTime());
			
			try {
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				pst = con.prepareStatement("UPDATE Users SET LAST_LOGIN=? WHERE NAME=?;");
				pst.setString(1, date);
				pst.setString(2, username);
				pst.executeUpdate();
				closeConnection();
				return 0;
				
			} catch (SQLException e) {
				return -2;
			}
			
			
		}else{
			return -1;
		}
	}
	
	private static void closeConnection() throws SQLException{
		pst.close();
		rs.close();
		con.close();
	}

	public static int getUserID() {
		return userID;
	}

	public static void setUserID(int userID) {
		UserSession.userID = userID;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		UserSession.username = username;
	}

	public static String getLastLogin() {
		return lastLogin;
	}

	public static void setLastLogin(String lastLogin) {
		UserSession.lastLogin = lastLogin;
	}
	
	
}
