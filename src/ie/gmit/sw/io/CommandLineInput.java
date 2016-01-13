package ie.gmit.sw.io;

import java.io.IOException;
import java.util.Scanner;

import ie.gmit.sw.cryptography.Decryptor;
import ie.gmit.sw.cryptography.Encryptor;

public class CommandLineInput {
	private static Scanner console = new Scanner(System.in);
	private static String cypherText;

	public static void main(String[] args) throws IOException {
		System.out.printf("%s","\n   *** Welcome to the RailFence cypher ***\n\n");
		String userChoice = null;
		do {
			System.out.println("Please choose from one of the following options:");
			System.out.println("1 = Encrypt using key");
			System.out.println("2 = Decrypt using key");
			System.out.println("3 = Decypher file without key");
			System.out.println("4 = Exit Program");
			userChoice = console.nextLine();

			switch (userChoice) {
			case "1":
				System.out.println("Would you like to (1) encrypt a text file or (2) input text via the keyboard?");
				String textOrFileChoice = console.nextLine();
				switch (textOrFileChoice) {
				case "1":
					String fileURI = FileIO.checkFileExists();

					// Parses the users file plain text and passes text to String called plainText.
					String plainText1 = FileIO.parsePlainText(fileURI);

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText1, keyInput(plainText1.length()));

					System.out.println("Cypher text = " + cypherText + "\n");
					break;

				case "2":
					System.out.println("Please enter the message you would like to encrypt. (Should be english and spelled correctly)");
					String plainText2 = console.nextLine();

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText2, keyInput(plainText2.length()));
					System.out.println("Cypher text = " + cypherText + "\n");

					// Encrypts using plainText string and specified key lenght as six. 
					cypherText = Encryptor.encrypt(plainText2, keyInput(plainText2.length()));
					break;

				default:
					System.out.println(textOrFileChoice + " is not a valid option. Please try again.");
					break;
				}
				break;
			case "2":
				if (!cypherText.isEmpty()) {
					System.out.println("Would you like to decrypt the cypher text from last encryption? (yes/no)");
					String lastEncryptChoice = null;
					do {
						lastEncryptChoice = console.nextLine();
						switch (lastEncryptChoice) {
						case "yes":
							System.out.println("Please enter the key");
							int keyLastEncrypt = console.nextInt();
							String plainText = Decryptor.decrypt(cypherText, keyLastEncrypt);
							System.out.println("Decrypted Text: " + plainText + "\n");
							break;

						case "no":
							System.out.println("Please enter the file URI for the cypher text.");
							break;

						default:
							System.out.println("That is not a valid option. Please choose either \"yes\" or \"no\"");
							lastEncryptChoice = null;
							break;
						}
					} while (lastEncryptChoice == null);
				}
				break;
			case "3":
				System.out.println("3");
				break;
			case "4":
				userChoice = "";
				break;

			default:
				System.out.println("That is not a valid option. Try again...");
				break;
			}
		}while (!userChoice.isEmpty());
		console.close();
	} 


	public static int keyInput(int plainTextLenght){
		int key = 0;
		do {
			System.out.println("Please enter a key for encryption.");
			key = console.nextInt();
			console.nextLine();

			if (key > (plainTextLenght / 2) - 1) {
				System.out.println("Sorry that key is too large!");
				System.out.println("Try something smaller than " + (plainTextLenght /2));
				key = 0;
			} else if (key < 3){
				System.out.println("Sorry that key is too small. Try something larger than 2");
				key = 0;
			} else {
				return key;
			}
		} while (key == 0);

		return 0;
	}
}
