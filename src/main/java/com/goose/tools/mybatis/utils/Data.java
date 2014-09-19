package com.goose.tools.mybatis.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Data {

	private Data() {
		
	}
	
	/**
     * ��ȡϵͳ�û���
     * 
     * @return ϵͳ�û���
     */
    public static String getSystemUser() {
        Properties p = System.getProperties();
        return p.getProperty("user.name");
    }
	
	/**
	 * ��ȡ·��
	 * @return
	 */
	public static String getProjectLocalPath() {
		try {
			String filepath = Data.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			filepath = URLDecoder.decode(filepath, "UTF-8");
			filepath = filepath.substring(0, filepath.lastIndexOf("/") + 1);
			filepath = new File(filepath).getAbsolutePath();
			return filepath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��������
	 * @param com
	 */
	public static void setPosition(Component com) {
		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = com.getSize();
			com.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����
	 * @param jList
	 */
	public static void moveUp(javax.swing.JList jList) {
		int x = jList.getSelectedIndex();
		DefaultListModel model = (DefaultListModel)jList.getModel();
		if (x == -1 || x == 0) {
			return;
		}
		model.add(x - 1, model.elementAt(x));
		model.remove(x + 1);
		jList.setSelectedIndex(x - 1);
	}

	/**
	 * ����
	 * @param jList
	 */
	public static void moveDown(javax.swing.JList jList) {
		int x = jList.getSelectedIndex();
		DefaultListModel model = (DefaultListModel)jList.getModel();
		if (x == -1 || x == model.getSize() - 1) {
			return;
		}
		model.add(x, model.elementAt(x + 1));
		model.remove(x + 2);
		jList.setSelectedIndex(x + 1);
	}
	
	/**
	 * ��ȡѡ�еĶ���
	 * @param jList
	 */
	public static Object getSelectedObject(javax.swing.JList jList) {
		int x = jList.getSelectedIndex();
		if (x == -1) {
			return null;
		}
		DefaultListModel model = (DefaultListModel)jList.getModel();
		return model.elementAt(x);
	}
	
	/**
	 * ���
	 * @param jList
	 * @param obj
	 */
	public static void addList(javax.swing.JList jList, Object obj) {
		DefaultListModel model = (DefaultListModel)jList.getModel();
		model.addElement(obj);
	}

	/**
	 * ɾ��
	 * @param jList
	 */
	public static void deleteList(javax.swing.JList jList) {
		int x = jList.getSelectedIndex();
		if (x == -1) {
			return;
		}
		DefaultListModel model = (DefaultListModel)jList.getModel();
		model.remove(x);
	}
	
	/**
	 * ���
	 * @param jList
	 */
	public static void clearList(javax.swing.JList jList) {
		DefaultListModel modelSrc = (DefaultListModel)jList.getModel();
		int x = modelSrc.size();
		for (int i = 0; i < x; i++) {
			modelSrc.remove(0);
		}
	}
	
	/**
	 * ��������
	 * @param jListLeft		����б�
	 * @param jListRight	�Ҳ��б�
	 */
	public static void moveOneToRight(javax.swing.JList jListLeft, javax.swing.JList jListRight) {
		moveOneElement(jListLeft, jListRight);
	}
	
	/**
	 * ��������
	 * @param jListLeft		����б�
	 * @param jListRight	�Ҳ��б�
	 */
	public static void moveOneToLeft(javax.swing.JList jListLeft, javax.swing.JList jListRight) {
		moveOneElement(jListRight, jListLeft);
	}
	
	/**
	 * �ƶ�����Ԫ��
	 * @param jListSrc		�Ƴ���list
	 * @param jListDest		Ŀ��list
	 */
	private static void moveOneElement(javax.swing.JList jListSrc, javax.swing.JList jListDest) {
		int x = jListSrc.getSelectedIndex();
		if (x == -1) {
			return;
		}
		DefaultListModel modelSrc = (DefaultListModel)jListSrc.getModel();
		DefaultListModel modelDest = (DefaultListModel)jListDest.getModel();
		modelDest.addElement(modelSrc.get(x));
		modelSrc.remove(x);
	}
	
	/**
	 * ��������
	 * @param jListLeft		����б�
	 * @param jListRight	�Ҳ��б�
	 */
	public static void moveAllToRight(javax.swing.JList jListLeft, javax.swing.JList jListRight) {
		moveAllElements(jListLeft, jListRight);
	}

	/**
	 * ��������
	 * @param jListLeft		����б�
	 * @param jListRight	�Ҳ��б�
	 */
	public static void moveAllToLeft(javax.swing.JList jListLeft, javax.swing.JList jListRight) {
		moveAllElements(jListRight, jListLeft);
	}
	
	/**
	 * �ƶ�
	 * @param jListSrc
	 * @param jListDest
	 */
	private static void moveAllElements(javax.swing.JList jListSrc, javax.swing.JList jListDest) {
		DefaultListModel modelSrc = (DefaultListModel)jListSrc.getModel();
		DefaultListModel modelDest = (DefaultListModel)jListDest.getModel();
		int x = modelSrc.size();
		for (int i = 0; i < x; i++) {
			modelDest.addElement(modelSrc.get(0));
			modelSrc.remove(0);
		}
	}
	
	/**
	 * ϵͳ����
	 * @param com
	 */
	public static void printERROR(Component com) {
		JOptionPane.showMessageDialog(com, "ϵͳ�������ش���", "ϵͳ����", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * ��ʾ��Ϣ
	 * @param com
	 * @param info
	 */
	public static void printINFO(Component com, String info) {
		JOptionPane.showMessageDialog(com, info, "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
     * ��ϵͳĿ¼��д���ļ�
     * 
     * @param filename
     *            �ļ���
     * @param content
     *            ����
     * @return true-д��ɹ���false-д��ʧ��
     */
    public static boolean writeToSys(String filename, String content) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(getSystemTempPath() + filename);
            out.print(content);
            out.flush();
            out.close();
            out = null;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }

    /**
     * ��ϵͳĿ¼�¶�ȡ�ļ�
     * 
     * @param filename
     *            �ļ���
     * @return �ļ�������
     */
    public static String readFromSys(String filename) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(getSystemTempPath() + filename);
            byte[] b = new byte[in.available()];
            in.read(b);
            return new String(b);
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
                in = null;
            }
        }
    }

    /**
     * ��ȡϵͳ��ʱ�ļ���
     * 
     * @return ϵͳ��ʱ�ļ���·��
     */
    public static String getSystemTempPath() {
        Properties p = System.getProperties();
        return p.getProperty("java.io.tmpdir");
    }
    
    public static String getCurrentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
