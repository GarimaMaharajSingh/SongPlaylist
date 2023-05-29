package org.example;
import java.util.Scanner;

class SongPlaylist {
    public static void main(String[] args) {
        LinkedList playlist = new LinkedList();
        Scanner input=new Scanner(System.in);
        Scanner inputS=new Scanner(System.in);// this will be used for String

        while (true) {
            System.out.println("************ Song Playlist ************");
            System.out.println("1. Add a song to the playlist");
            System.out.println("2. Delete a song from the playlist");
            System.out.println("3. Find a song by name");
            System.out.println("4. Next track / Previous track");
            System.out.println("5. Sort playlist by song title");
            System.out.println("6. Display playlist");
            System.out.println("7. Exit");
            System.out.println("***************************************");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Song Title: ");
                    String title = inputS.nextLine();
                    System.out.print("Enter Song Duration (mm:ss): ");
                    String duration = inputS.nextLine();
                    playlist.addSong(title, duration);
                    break;
                case 2:
                    System.out.print("Enter the position of the song to delete: ");
                    int position = input.nextInt();
                    playlist.deleteSong(position);
                    break;
                case 3:
                    System.out.print("Enter the song name to find: ");
                    //scanner.nextLine();  // Consume the newline character
                    String songName = inputS.nextLine();
                    int position1 = playlist.findSong(songName);
                    if (position1 != -1) {
                        System.out.println("Song found at position: " + position1);
                    }
                    break;
                case 4:
                    System.out.print("Enter a track number to start from: ");
                    int start = input.nextInt();
                    playlist.playNextTrack(start);
                    break;
                case 5:
                    playlist.sortPlaylist();
                    break;
                case 6:
                    playlist.displayPlaylist();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}