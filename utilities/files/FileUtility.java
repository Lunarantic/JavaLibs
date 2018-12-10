package utilities.files;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtility {

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}

	public static String readFile(File file) {
		try {
			return readFile(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String readFile(InputStream inputStream) {

		StringBuilder stringBuilder = new StringBuilder();
		int ch;
		try {
			while ((ch = inputStream.read()) != -1) {
				stringBuilder.append((char) ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(inputStream);
		}
		return stringBuilder.toString();
	}

	public static String readFile(String filePath) {
		File file = new File(filePath);
		return readFile(file);
	}

	public static void writeTotFile(String fileName, String content) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
                file.createNewFile();
            }
			fileWriter = new FileWriter(fileName, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(bufferedWriter);
			close(fileWriter);
		}
	}
	
	public static void close(Closeable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
