package socketJava;

public class ValidateIpAddress {

	public static void main(String[] args) {
		String ipAddress = "-192.168.0.1";
		boolean isValid = validateIpAddress(ipAddress);
		System.out.println("Value" + isValid);

	}

	public static  boolean validateIpAddress(String ipAddress) {
		String[] numbers = ipAddress.split("\\.");
		if (numbers.length !=4) {
			return false;
		}
		for (String string : numbers) {
			int i = Integer.parseInt(string);
			if (i< 0 || i> 255) {
				return false;
			}
		}
		return true;
	}
}
