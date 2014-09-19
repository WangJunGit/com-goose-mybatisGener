package com.goose.tools.mybatis.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public final class DbAccess {
	
	private java.sql.Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement prepstmt = null;
	private CallableStatement callstmt = null;
	
	/**
	 * ��ͨ��ѯ������ҳ��
	 * @param sql	��ѯ���
	 * @param para	��ѯ����
	 * @return		��ѯ���
	 */
	public String[][] executeQuery(String sql, List<String> para) {
		try {
			setParameter(sql, para);
			this.rs = this.prepstmt.executeQuery();
			return getResult();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	
	/**
	 * ���ò���
	 * @param sql	sql���
	 * @param para	�������ϣ�ÿһ��Ԫ�ض�ΪString����
	 * @throws Exception
	 */
	private void setParameter(String sql, List<String> para) throws Exception {
		if (para == null) {
			para = new ArrayList<String>();
		}
		this.conn = DbBean.getConnectionFromJDBC();
		this.prepstmt = this.conn.prepareStatement(sql);
		for (int i = 0; i < para.size(); i++) {
			this.prepstmt.setString(i + 1, (String)para.get(i));
		}
	}
	
	/**
	 * �ӽ������ȡ����
	 * @return	��ѯ���
	 * @throws Exception
	 */
	private String[][] getResult() throws Exception {
		String[][] result = null;
		ResultSetMetaData md = this.rs.getMetaData();
		int length = md.getColumnCount();
		List<String[]> tmpList = new ArrayList<String[]>();
		String[] tmpStr = null;
		while (this.rs.next()) {
			tmpStr = new String[length];
			for (int i = 0; i < length; i++) {
				tmpStr[i] = this.rs.getString(i + 1);
			}
			tmpList.add(tmpStr);
		}
		this.rs.close();
		this.rs = null;
		//��List�е�����ת����String[][]
		if (tmpList != null) {
			result = new String[tmpList.size()][length];
			for (int i = 0; i < tmpList.size(); i++) {
				result[i] = (String[])tmpList.get(i);
			}
		}
		return result;
	}
	
	/**
	 * �ͷ�������Դ
	 */
	private void close() {
		try {
			if(this.rs != null){
				this.rs.close();
				this.rs = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if (this.prepstmt != null) {
				this.prepstmt.close();
				this.prepstmt = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if (this.callstmt != null) {
				this.callstmt.close();
				this.callstmt = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if (this.conn != null) {
				if (!this.conn.getAutoCommit()) {
					try {
						this.conn.setAutoCommit(true);
					} catch(Exception ex) {
						ex.printStackTrace();
						this.conn.rollback();
						this.conn.setAutoCommit(true);
					}
				}
				this.conn.close();
				this.conn = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}