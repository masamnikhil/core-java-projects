import java.util.*;

public class OnlineVotingSystem {

    static Scanner sc = new Scanner(System.in);

    static VotingService votingService = new VotingService();

    public static void main(String[] args) {

        // candidates are added here
        votingService.addCandidates();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("       ONLINE VOTING SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Candidates");
            System.out.println("4. View Total Votes");
            System.out.println("5. Display Results");
            System.out.println("6. Display Runner-Up");
            System.out.println("7. Exit");

            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {

                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
                continue;
            }

            try {

                switch (choice) {

                    case 1:
                        votingService.registerUser(sc);
                        break;

                    case 2:
                        User user = votingService.loginUser(sc);
                        userMenu(user);
                        break;

                    case 3:
                        votingService.viewCandidates();
                        break;

                    case 4:
                        votingService.viewTotalVotes();
                        break;

                    case 5:
                        votingService.displayResult();
                        break;

                    case 6:
                        votingService.displayRunnerUp();
                        break;

                    case 7:
                        System.out.println("Thank you for using Online Voting System.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (VotingException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    // shows user menu when user logged in
    public static void userMenu(User user) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("            USER MENU");
            System.out.println("         Welcome " + user.getName());
            System.out.println("=================================");

            System.out.println("1. Cast Vote");
            System.out.println("2. Check Voting Status");
            System.out.println("3. Logout");

            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {

                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
                continue;
            }

            try {

                switch (choice) {

                    case 1:
                        votingService.castVote(sc, user);
                        break;

                    case 2:
                        votingService.checkVotingStatus(user.getEmail());
                        break;

                    case 3:
                        System.out.println("Logged out successfully.");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (VotingException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }
}
