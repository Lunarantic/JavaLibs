package utilities.files;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileUtility {

	public static boolean deleteFile(String fileName) {
		return (new File(fileName)).delete();
	}

	public static String readFile(InputStream inputStream) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int ch;
		try {
			while ((ch = inputStream.read()) != -1) {
				stringBuilder.append((char) ch);
			}
		} finally {
			close(inputStream);
		}
		return stringBuilder.toString();
	}

    public static String readFile(File file) throws IOException {
        return readFile(new FileInputStream(file));
    }

	public static String readFile(String fileName) throws IOException {
	    return readFile(new FileInputStream(fileName));
	}

    public static void writeToFile(FileWriter fileWriter, String content) throws IOException {
        BufferedWriter bufferedWriter = null;
	    try {
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } finally {
            close(bufferedWriter);
            close(fileWriter);
        }
    }

    public static void writeToFile(File file, String content) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        writeToFile(new FileWriter(file, true), content);
    }

    public static void writeToFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        writeToFile(new FileWriter(fileName, true), content);
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws IOException {
        try {
            byte[] buf = new byte[1024];
            int i;
            while ((i = fileInputStream.read(buf)) != -1)
                fileOutputStream.write(buf, 0 , i);
        } finally {
            close(fileInputStream);
            close(fileOutputStream);
        }
    }

    public static void copyFile(File input, File output) throws IOException {
        if (!input.exists())
            return;
        if (!output.exists())
            output.createNewFile();
        copyFile(new FileInputStream(input), new FileOutputStream(output));
    }

    public static void copyFile(String inputFileName, String outputFileName) throws IOException {
	    copyFile(new FileInputStream(inputFileName), new FileOutputStream(outputFileName));
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
    
    public static List<String> readFileLines(InputStream inputStream) throws IOException {
		List<String> lines = new LinkedList<>();
		StringBuilder stringBuilder = new StringBuilder();
		int ch;
		try {
			while ((ch = inputStream.read()) != -1) {
				if (ch == '\n') {
					lines.add(stringBuilder.toString());
					stringBuilder = new StringBuilder();
				} else {
					stringBuilder.append((char) ch);
				}
			}
		} finally {
			close(inputStream);
		}
		return lines;
	}
    
    public static List<String> readFileLines(File file) throws IOException {
        return readFileLines(new FileInputStream(file));
    }

	public static List<String> readFileLines(String fileName) throws IOException {
	    return readFileLines(new FileInputStream(fileName));
	}
	
	public static Integer[][] readNumberInLines(InputStream inputStream) throws IOException {
		List<Integer[]> lines = new LinkedList<>();
		StringBuilder stringBuilder = new StringBuilder();
		String[] line;
		Integer[] n;
		int ch;
		try {
			while ((ch = inputStream.read()) != -1) {
				if (ch == '\n') {
					line = stringBuilder.toString().trim().split("\\s*(=>|,|\\s)\\s*");
					n = new Integer[line.length];
					for (int i = 0; i < line.length; ++i) {
						n[i] = Integer.parseInt(line[i]);
					}
					lines.add(n);
					stringBuilder = new StringBuilder();
				} else {
					stringBuilder.append((char) ch);
				}
			}
		} finally {
			close(inputStream);
		}
		
		Integer[][] res = {};
		
		return lines.toArray(res);
	}
	
    public static Integer[][] readNumberInLines(File file) throws IOException {
        return readNumberInLines(new FileInputStream(file));
    }

	public static Integer[][] readNumberInLines(String fileName) throws IOException {
	    return readNumberInLines(new FileInputStream(fileName));
	}
}
