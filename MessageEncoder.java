//for reading from files
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

//for writing to files
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

//for i/o exceptions
import java.io.IOException;


class  MessageEncoder {
	
	//applies a shift cypher to the text of the amount given
	//can be positive or negative
	static String shift(String s, int shift_amt) {
		StringBuilder sb = new StringBuilder(s.toLowerCase());
		for(int i = 0; i < sb.length(); ++i) {
			if((int) sb.charAt(i) <= 122 && (int) sb.charAt(i) >= 97) {
				int newCharDecimal = (int) sb.charAt(i) + shift_amt;
				while(newCharDecimal > 122) {
					newCharDecimal -= 26;
				}
				while (newCharDecimal < 97) {
					newCharDecimal += 26;
				}
				sb.setCharAt(i, (char) newCharDecimal);
			}
		}
		return sb.toString();
	}
	
	public static void main(String... args) {
		System.out.println("Hello! Welcome to Zach's file encoding/decoding program!");
		System.out.println("What file would you like to encode/decode?");
		String filename;
		String targetfile;
		try (BufferedReader ins = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))) {
			filename = ins.readLine();
			System.out.println("What file would you like to write the message to?");
			targetfile = ins.readLine();
			System.out.println("What shift would you like to apply?");
			int shift = Integer.parseInt(ins.readLine());
			System.out.println("Got it! Encoding " + filename + " and writing the encrypted content to " + targetfile);
			
			String content;
			try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
				content = in.readLine();
				content = shift(content, shift);
				try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetfile)))) {
					out.write(content);
					System.out.println("Done!");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch (IOException e) {
				System.out.println("Sorry, something went wrong with finding your file");
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}