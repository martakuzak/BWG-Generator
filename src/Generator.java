import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;



public class Generator {
	final static int VERTEX_PART_NUMBER = 20;
	final static int MIN_WEIGHT = 1;
	final static int MAX_WEIGHT = 101;
	final static String OUT_DIR = "output/";
	
	@SuppressWarnings("unused")
	public static void main(String [] args) {
		if(MAX_WEIGHT < MIN_WEIGHT) {
			System.err.println("MAX_WEIGHT must not be smaller than MIN_WEIGHT");
			return;
		} else if (MAX_WEIGHT < 0 || MIN_WEIGHT < 0) {
			System.err.println("Weights must not be negative");
			return;
		} else if (VERTEX_PART_NUMBER <=0) {
			System.err.println("VERTEX_PART_NUMBER must be positive");
			return;
		}
		
		new File(OUT_DIR).mkdir();
		File file = new File(OUT_DIR + getFileName());
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter bw = new BufferedWriter(fw);
			 int indeks = 0;
			 bw.write("VERTEX_PART_NUMBER\t" + VERTEX_PART_NUMBER +"\n" );
			 bw.write("WEIGHT\t" + MIN_WEIGHT + " - " + MAX_WEIGHT + "\n" );
			 for(int firstV = 1; firstV < VERTEX_PART_NUMBER + 1; ++ firstV) {
					for(int secondV = VERTEX_PART_NUMBER + 1; secondV < 2*VERTEX_PART_NUMBER + 1; ++ secondV ) {
						Random generator = new Random();
						int weight = generator.nextInt(MAX_WEIGHT - MIN_WEIGHT) + MIN_WEIGHT + 1;
						bw.write(firstV + "\t" + secondV + "\t" + weight + "\n");
					}
				}
			 bw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	static String getFileName() {
		return new String(VERTEX_PART_NUMBER + "_" + MIN_WEIGHT + "_" + MAX_WEIGHT + ".txt");
	}
}
