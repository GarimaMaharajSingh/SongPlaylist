package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

class SongPlaylist {
    public static void main(String[] args) {
        LinkedList playlist = new LinkedList();
        Scanner input = new Scanner(System.in);
        Scanner inputS = new Scanner(System.in);// this will be used for String
        int choice;

        do {
            System.out.println("\nPlaylist Operations:\n");
            System.out.println("1. Add a song to the playlist");
            System.out.println("2. Delete a song from the playlist");
            System.out.println("3. Find a song by name");
            System.out.println("4. Next track / Previous track");
            System.out.println("5. Sort playlist by song title");
            System.out.println("6. Display playlist");
            System.out.println("7. Exit\n");
            System.out.print("Enter Menu Option:");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1:
                        System.out.print("Enter Song Title: ");
                        String title = inputS.nextLine();
                        while (title.isBlank() || title == (String) " ") {
                            System.out.println("Invalid title. Enter Song Title again: ");
                            title = inputS.nextLine();
                        }
                        System.out.print("Enter Song Duration (mm:ss): ");
                        String duration = inputS.nextLine();
                        playlist.addSong(title, duration);
                        break;
                    case 2:
                        System.out.print("Enter the position of the song to delete: ");
//                    int position = input.nextInt();
//                    playlist.deleteAtPos(position);
                        try {
                            String x = inputS.nextLine();
                            int position = Integer.parseInt(x);
                            playlist.deleteAtPos(position);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the Name of the Song: ");
                        //scanner.nextLine();  // Consume the newline character
                        String songName = inputS.nextLine();
                        int position1 = playlist.findSong(songName);
                        if (position1 != -1) {
                            System.out.println("The Song is present at position " + position1);
                        }
                        break;
                    case 4:
                        System.out.print("Enter a track number to start from: ");
                        String start = inputS.nextLine();
                        playlist.playTrack(start);
                        break;
                    case 5:
                        playlist.sortList();
                        playlist.display();
                        break;
                    case 6:
                        playlist.display();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid menu option number.");
                input.nextLine(); // Consume the invalid input
                choice = -1; // Set option to an invalid value to continue the loop
            }
        } while (choice != 7);
    }
}