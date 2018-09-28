package extensions.datatypes;

public class Strings {
	
	public static boolean isNull(String input) {
        if (null == input) {
            return true;
        }
        return false;
    }
	
	public static boolean isEmpty(String input) {
        if (isNull(input) || "".equals(input.trim()) || "null".equalsIgnoreCase(input.trim())) {
            return true;
        }
        return false;
    }
    
    public static String returnBlankForNull(String input) {
		if (isEmpty(input)) {
			return "";
		}
		return input.trim();
	}
    
    public static String returnNAForNull(String input) {
		if (isEmpty(input)) {
			return "-NA-";
		}
		return input.trim();
	}

	public static String returnZeroForNull(String input) {
		if (isEmpty(input)) {
			return "0";
		}
		return input.trim();
	}
	
	public static String removeWhiteCharacters(String input) {

		if (isEmpty(input)) {
			return input;
		}

		String dest = input;
		dest = dest.replaceAll("\n", "");
		dest = dest.replaceAll("\r", "");
		dest = dest.replaceAll("\t", "");
		dest = dest.replaceAll("\"", "");
		// for removing all non-readable chars
		dest = dest.replaceAll("[^\\p{L}\\p{Nd}]", "");
		return dest;
	}
	
	public static String replaceAllNonprintable(String input, String replace_with) {
		boolean status = true;
		
		if (isNull(input)) {
			System.err.println("extensions.datatypes.Strings :: argument input is empty");
		}
		
		if (isNull(replace_with)) {
			System.err.println("extensions.datatypes.Strings :: argument replace_with is empty");
		}
		
		if (status) {
			try {
				input = input.replaceAll("\\p{C}", replace_with);
			} catch (Throwable e) {
				System.err.println(e.getMessage());
			}
		}
		
		return input;
	}

	public static String replaceAllNonAlphNumeric(String input, String replace_with) {
		boolean status = true;
		
		if (isNull(input)) {
			System.err.println("extensions.datatypes.Strings :: argument input is empty");
		}
		
		if (isNull(replace_with)) {
			System.err.println("extensions.datatypes.Strings :: argument replace_with is empty");
		}
		
		if (status) {
			try {
				input = input.replaceAll("[^a-zA-Z0-9]", replace_with);
			} catch (Throwable e) {
				System.err.println(e.getMessage());
			}
		}
		return input;
	}

	public static String replaceAllNonAlphNumeric(String input) {
		boolean status = true;
		
		if (isNull(input)) {
			System.err.println("extensions.datatypes.Strings :: argument input is empty");
		}
		
		if (status) {
			try {
				input = input.replaceAll("[^a-zA-Z0-9]", "");
			} catch (Throwable e) {
				System.err.println(e.getMessage());
			}
		}
		return input;
	}
}
