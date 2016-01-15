package ie.gmit.sw.io;

import java.util.Scanner;

import ie.gmit.sw.cryptography.CypherBreaker;
import ie.gmit.sw.cryptography.Decryptor;
import ie.gmit.sw.cryptography.Encryptor;

/**
 * Creates a CLI shell for command line application control.
 * <p>
 * This is the largest and least object orientated class in the application.
 * This is because this class deals with a lot of potential runtime errors and 
 * has to create a flow for the user which is robust. This meant using of switch statements
 * and several other flow control structures that dont exits in the other OO classes.
 * 
 * @author John Malcolm Anderson
 * @version 1.0
 * @since 11/01/2016
 * 
 */
public class CommandLineIO {
	// Keyboard and cypher text class objects.
	private static Scanner console = new Scanner(System.in);
	private static String cypherText;

	/** 
	 * Used to begin the command line application. 
	 */
	public static void startCLI() {

		// Print user welcome and menu to standard output.
		System.out.printf("%s","\n   *** Welcome to the RailFence cypher ***\n\n");

		// This do while allows the user to use several functions of the app and then shutdown when done. 
		String userChoice = null;
		do {
			// Options to standard output.
			System.out.println("Please choose from one of the following options:");
			System.out.println("1 = Encrypt using key");
			System.out.println("2 = Decrypt using key");
			System.out.println("3 = Decypher without key using the Cypher Breaker");
			System.out.println("4 = Exit Program");
			userChoice = console.nextLine();

			// Handles main meny selection
			switch (userChoice) {
			// Encrypt with a key.
			case "1":
				System.out.println("Would you like to (1) encrypt a text file or (2) input text via the keyboard?");
				String textOrFileChoice = console.nextLine();
				switch (textOrFileChoice) {
				// Encrypt a text file with a key
				case "1":

					// Check validity of file URI
					String fileURI;
					if ((fileURI = getAndCheckUserFile()).equals("menu")) {
						break;
					}

					// Parses the users file plain text and passes text to String called plainText.
					String plainText1 = FileIO.parseTextFile(fileURI);
					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText1, keyInput(plainText1.length()));
					// Prints cypher text to the standard output
					System.out.println("Cypher text = " + cypherText + "\n");
					break;

					// Encrypt message from the keyboard
				case "2":
					// Get message from the user
					System.out.println("Please enter the message you would like to encrypt. (Minimum 15 chars.)");
					String plainText2 = console.nextLine();

					// Check size of the message
					if (plainText2.length() < 15) {
						System.out.println("Message too small: Minimum 15 chars.");
						continue;
					}

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText2.replaceAll("[^a-zA-Z]+", "").toUpperCase(), keyInput(plainText2.length()));
					System.out.println("Cypher text = " + cypherText + "\n");
					break;

				default:
					// Alert the user that there selection was invalid
					System.out.println(textOrFileChoice + " is not a valid option. Please try again.");
					break;
				}
				break;
				// Encrypt cypher text from the last encryption. 
			case "2":
				System.out.println("Would you like to decrypt the cypher text from last encryption? (yes/no)");
				String lastEncryptChoice = null;
				do {
					lastEncryptChoice = console.nextLine();
					switch (lastEncryptChoice) {
					case "yes":
						// Check if user has performed encytion since starting the app
						if (cypherText == null) {
							System.out.println("Sorry you have not run the encryption since starting the app.\n");
							break;
						}
						// Get key from the user decrypt and print to screen
						System.out.println("Please enter the key");
						int keyLastEncrypt = console.nextInt();
						console.nextLine();
						String plainText = Decryptor.decrypt(cypherText, keyLastEncrypt);
						System.out.println("Decrypted text = " + plainText + "\n");
						break;

					case "no":
						// Check if user wants to exit back to menu
						String fileURI;
						if ((fileURI = getAndCheckUserFile()).equals("menu")) {
							break;
						}

						// Parse text file
						String cypherText2 = FileIO.parseTextFile(fileURI);

						// Key input, decryption and print to standard outut
						System.out.println("Please enter the key");
						int keyLastEncrypt2 = console.nextInt();
						console.nextLine();							
						String plainText2 = Decryptor.decrypt(cypherText2, keyLastEncrypt2);
						System.out.println("Decrypted text = " + plainText2 + "\n");
						break;

					default:
						System.out.println("That is not a valid option. Please choose either \"yes\" or \"no\"");
						lastEncryptChoice = null;
						break;
					}
				} while (lastEncryptChoice == null);
				break;
				// Brute force decypher
			case "3":
				// Last input?
				System.out.println("Would you like to decrypt the cypher text from last encryption? (yes/no)");
				String lastChoice = null;
				do {
					lastChoice = console.nextLine();
					switch (lastChoice) {
					case "yes":
						// Check if exists
						if (cypherText == null) {
							System.out.println("Sorry you have not run the encryption since starting the app.\n");
							break;
						}
						// Creates new CypherBreaker object.
						CypherBreaker cb = new CypherBreaker();

						// Decyphers cypherText
						cb.decypher(cypherText);
						System.out.println("Decyphered text = " + cb.getQueue().peek().getPlainText());
						System.out.println("Key = " + cb.getQueue().peek().getKey() + "\n");
						break;

						// File or keyboard 
					case "no":
						System.out.println("Would you like to (1) decypher a text file or (2) decypher text via the keyboard?");
						String textOrFileChoice2 = console.nextLine();
						switch (textOrFileChoice2) {
						// File
						case "1":
							// Check if exists
							String fileURI;
							if ((fileURI = getAndCheckUserFile()).equals("menu")) {
								break;
							}

							// Parses the users file plain text and passes text to String called plainText.
							String cypherTextBreaker = FileIO.parseTextFile(fileURI);

							// Creates new CypherBreaker object.
							CypherBreaker cb2 = new CypherBreaker();

							// Decyphers cypherText
							cb2.decypher(cypherTextBreaker);
							System.out.println("Decyphered text = " + cb2.getQueue().peek().getPlainText());
							System.out.println("Key = " + cb2.getQueue().peek().getKey() + "\n");
							break;

							// Keyboard
						case "2":
							//Get message
							System.out.println("Please enter the message you would like to decrypt.");
							String plainText2 = console.nextLine();

							// Check size
							if (plainText2.length() < 15) {
								System.out.println("Message too small: Minimum 15 chars.");
								continue;
							}

							// Creates new CypherBreaker object.
							CypherBreaker cb3 = new CypherBreaker();

							// Decyphers cypherText
							cb3.decypher(plainText2);
							System.out.println("Decyphered text = " + cb3.getQueue().peek().getPlainText());
							System.out.println("Key = " + cb3.getQueue().peek().getKey() + "\n");
							break;

						default:
							System.out.println(textOrFileChoice2 + " is not a valid option. Please try again.");
							break;
						}
						break;

						// Default statment if user enters incorrect option
					default:
						System.out.println("That is not a valid option. Please choose either \"yes\" or \"no\"");
						lastChoice = null;
						break;
					}
				} while (lastChoice == null);

				break;
				// Exit program
			case "4":
				userChoice = null;
				break;
				// Handle incorrect I/O
			default:
				System.out.println("That is not a valid option. Try again...");
				userChoice = "Try Again";
				break;
			}
		}while (!(userChoice == null));

		// Print Bye and cypher text message
		System.out.println("Bye. CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ");
		console.close();
	} 

	/**
	 * Get and check validity of user file input.
	 * Delegates to FileIO.FileParser.
	 * @return String containing fileURI if valid or "" if not.
	 * @see ie.gmit.sw.io.FileIO#checkFileExists(String)
	 */
	private static String getAndCheckUserFile(){
		boolean validFile;
		String fileURI;

		do {
			// Resets flag
			validFile = true;

			// Promps for file url
			System.out.println("Please enter the file URI.");
			fileURI = console.nextLine();
			validFile = FileIO.checkFileExists(fileURI);
			if (fileURI.equals("menu")) {
				break;
			}
			// Tells user that the file url provided is invalid
			if (validFile == false) {
				System.out.println("Invalid file url. Try again. Or type \"menu\" to return to the main menu.");
			}
			// Keeps looping until user enters a valid file url
		} while (validFile == false);
		return fileURI;
	}

	/**
	 * Get and validate key from user.
	 * Validation based on criteria: key must be bigger than 2 and smaller than (plainTextLenght / 2).
	 * 
	 * @param plainTextLenght which is the length of the plain text
	 * @return int which is the valid key.
	 */
	private static int keyInput(int plainTextLenght){
		int key = 0;
		do {
			// Gets key from the user.
			System.out.println("Please enter a key for encryption.");
			key = console.nextInt();
			console.nextLine();

			// Checks size and prompts user for correct key while suggesting constraints.
			if (key > (plainTextLenght / 2) - 1) {
				System.out.println("Sorry that key is too large. Try something smaller than " + (plainTextLenght /2));
				key = 0;
			} else if (key < 3){
				System.out.println("Sorry that key is too small. Try something larger than 2.");
				key = 0;
			} else {
				return key;
			}
		} while (key == 0);

		return key;
	}
}
