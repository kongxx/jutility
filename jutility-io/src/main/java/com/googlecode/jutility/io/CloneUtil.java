package com.googlecode.jutility.io;

import java.io.*;

public class CloneUtil {
	public static Object clone(Object obj) {
		Object newObj = null;
		byte[] bytes;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
		} catch(IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// ignore me
				}
			}
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			newObj = ois.readObject();
		} catch(IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} catch(ClassNotFoundException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// ignore me
				}
			}
		}
		return newObj;
	}
}
