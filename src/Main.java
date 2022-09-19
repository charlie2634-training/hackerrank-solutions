import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// Use this to point to some file, so you don't have to copy paste the input all the time.
		System.setIn(new FileInputStream(new File(System.getenv("INPUT_PATH"))));
		
		try {
			Solution.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
