package ie.gmit.sw.io;

import java.io.IOException;
import java.util.Scanner;

import ie.gmit.sw.cryptography.CypherBreaker;
import ie.gmit.sw.cryptography.Decryptor;
import ie.gmit.sw.cryptography.Encryptor;

public class CommandLineIO {
	private static Scanner console = new Scanner(System.in);
	private static String cypherText;

	public static void startCLI() throws IOException, InterruptedException {
		System.out.printf("%s","\n   *** Welcome to the RailFence cypher ***\n\n");
		String userChoice = null;
		do {
			System.out.println("Please choose from one of the following options:");
			System.out.println("1 = Encrypt using key");
			System.out.println("2 = Decrypt using key");
			System.out.println("3 = Decypher without key using the Cypher Breaker");
			System.out.println("4 = Exit Program");
			userChoice = console.nextLine();

			switch (userChoice) {
			case "1":
				System.out.println("Would you like to (1) encrypt a text file or (2) input text via the keyboard?");
				String textOrFileChoice = console.nextLine();
				switch (textOrFileChoice) {
				case "1":
					boolean validFile;
					String fileURI;

					do {
						// Resets flag
						validFile = true;

						// Promps for file url
						System.out.println("Please enter the file URI.");
						fileURI = console.nextLine();
						validFile = FileIO.checkFileExists(fileURI);
						// Tells user that the file url provided is invalid
						if (validFile == false) {
							System.out.println("Invalid file url. Try again.");
						}
						// Keeps looping until user enters a valid file url
					} while (validFile == false);

					// Parses the users file plain text and passes text to String called plainText.
					String plainText1 = FileIO.parseTextFile(fileURI);

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText1, keyInput(plainText1.length()));

					System.out.println("Cypher text = " + cypherText + "\n");
					break;

				case "2":
					System.out.println("Please enter the message you would like to encrypt. (Should be english and spelled correctly)");
					String plainText2 = console.nextLine();

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText2.replaceAll("[^a-zA-Z]+", "").toUpperCase(), keyInput(plainText2.length()));
					System.out.println("Cypher text = " + cypherText + "\n");

					break;

				default:
					System.out.println(textOrFileChoice + " is not a valid option. Please try again.");
					break;
				}
				break;
			case "2":
				System.out.println("Would you like to decrypt the cypher text from last encryption? (yes/no)");
				String lastEncryptChoice = null;
				do {
					lastEncryptChoice = console.nextLine();
					switch (lastEncryptChoice) {
					case "yes":
						if (cypherText == null) {
							System.out.println("Sorry you have not run the encryption since starting the app.\n");
							break;
						}
						System.out.println("Please enter the key");
						int keyLastEncrypt = console.nextInt();
						console.nextLine();
						String plainText = Decryptor.decrypt(cypherText, keyLastEncrypt);
						System.out.println("Decrypted text = " + plainText + "\n");
						break;

					case "no":
						
						// Parses the users file plain text and passes text to String called plainText.
						String cypherText2 = FileIO.parseTextFile(getAndCheckUserFile());

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
			case "3":
				System.out.println("Would you like to decrypt the cypher text from last encryption? (yes/no)");
				String lastChoice = null;
				do {
					lastChoice = console.nextLine();
					switch (lastChoice) {
					case "yes":
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

					case "no":
						System.out.println("Would you like to (1) decypher a text file or (2) decypher text via the keyboard?");
						String textOrFileChoice2 = console.nextLine();
						switch (textOrFileChoice2) {
						case "1":

							// Parses the users file plain text and passes text to String called plainText.
							String cypherTextBreaker = FileIO.parseTextFile(getAndCheckUserFile());

							// Creates new CypherBreaker object.
							CypherBreaker cb2 = new CypherBreaker();

							// Decyphers cypherText
							cb2.decypher(cypherTextBreaker);
							System.out.println("Decyphered text = " + cb2.getQueue().peek().getPlainText());
							System.out.println("Key = " + cb2.getQueue().peek().getKey() + "\n");
							break;

						case "2":
							System.out.println("Please enter the message you would like to decrypt.");
							String plainText2 = console.nextLine();

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

					default:
						System.out.println("That is not a valid option. Please choose either \"yes\" or \"no\"");
						lastChoice = null;
						break;
					}
				} while (lastChoice == null);

				break;
			case "4":
				userChoice = null;
				break;

			default:
				System.out.println("That is not a valid option. Try again...");
				userChoice = "Try Again";
				break;
			}
		}while (!(userChoice == null));

		System.out.println("Bye. CSSBUNMTPRRORCINAOPTAAOYUTOEEWTAMEIEOEUMEHSNIOECSGDSPTECMRTORTTOSBLSESIKUNOCSRAEDJ");
		console.close();
	} 
	
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
		// Tells user that the file url provided is invalid
		if (validFile == false) {
			System.out.println("Invalid file url. Try again.");
		}
		// Keeps looping until user enters a valid file url
	} while (validFile == false);
		return fileURI;
	}
	
	private static int keyInput(int plainTextLenght){
		int key = 0;
		do {
			System.out.println("Please enter a key for encryption.");
			key = console.nextInt();
			console.nextLine();

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
