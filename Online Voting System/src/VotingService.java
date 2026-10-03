import java.util.*;

public class VotingService {

    // users map to store users
    private static Map<String, User> users = new HashMap<>();

    //to track whether user voted or not
    private static Map<String, Boolean> userVoted = new HashMap<>();

    // to store candidates
    private static Map<Integer, Candidate> candidates = new HashMap<>();

    // 1. register user
    public void registerUser(Scanner sc) {

        System.out.println("\n---------- REGISTER ----------");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        if(name.isBlank())
            throw new VotingException("name is required");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if(email.isBlank())
            throw new VotingException("email is required");

        if (!email.matches("^student\\d+@college\\.com$")) {

            throw new VotingException("Invalid email. Use format: " +
                            "student123@college.com");
        }

        if (users.containsKey(email)) {

            throw new VotingException(
                    "Email already registered."
            );
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (password.isBlank()) {

            throw new VotingException(
                    "Password cannot be empty."
            );
        }

        User user = new User.Builder()
                .name(name)
                .email(email)
                .password(password)
                .build();


        users.put(email, user);

        userVoted.put(email, false);

        System.out.println("Registration successful.");
    }

    // 2. login the user
    public User loginUser(Scanner sc) {

        System.out.println("\n---------- LOGIN ----------");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if(email.isBlank())
            throw new VotingException("email is required");

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if(password.isBlank())
            throw new VotingException("password should not be empty");

        if (!users.containsKey(email)) {

            throw new VotingException("Incorrect email. Not Registered? Register now.");
        }

        User user = users.get(email);

        if (!user.getPassword().equals(password)) {

            throw new VotingException("Incorrect password.");
        }

        System.out.println("Login successful.");

        return user;
    }

    // 3. candidates are added automatically when application starts
    public void addCandidates() {

        List<String> candidateList = List.of("Bob", "Alice", "Justin", "Garry");

        int i = 0;
        while( i < candidateList.size() ) {
            candidates.put( i+1, new Candidate.Builder().id(i+1).name(candidateList.get(i)).build());
            i++;
        }

    }

    // 4. to view all candidates
    public void viewCandidates (){

        System.out.println("\n---------- CANDIDATES ----------");

        if (candidates.isEmpty()) {
            throw new VotingException("No candidates found.");
        }

        for (Map.Entry<Integer, Candidate> entry
                : candidates.entrySet()) {

            System.out.println(
                    entry.getKey() + ". "
                            + entry.getValue().getName()
            );
        }
    }

    // 5. casting user vote
    public void castVote(Scanner sc, User user) {

        String email = user.getEmail();

        if (userVoted.get(email)) {
            throw new VotingException("You have already voted.");
        }

        if (candidates.isEmpty()) {
            throw new VotingException("Candidates not found.");
        }

        viewCandidates();

        System.out.print("Enter candidate number: ");

        int candidateNumber;

        try {

            candidateNumber = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {

            throw new VotingException("Please enter a valid candidate number.");
        }

        if (!candidates.containsKey(candidateNumber)) {

            throw new VotingException("Invalid candidate.");
        }

        Candidate candidate =
                candidates.get(candidateNumber);

        candidate.addVote();

        userVoted.put(email, true);

        System.out.println("Vote successfully cast for " + candidate.getName());
    }

    // 6. check user voting status
    public void checkVotingStatus (String email){

        boolean voted = userVoted.get(email);

        if (voted) {
            System.out.println("You have already voted.");
        } else {
            System.out.println("You have not voted yet. you have opportunity to vote");
        }
    }

    // 7. to view all cast votes
    public void viewTotalVotes () {

        System.out.println("\n---------- TOTAL VOTES ----------");

        int totalVotes = 0;

        for (Candidate candidate : candidates.values()) {

            System.out.println(
                    candidate.getName()
                            + " : "
                            + candidate.getVotes()
            );

            totalVotes += candidate.getVotes();
        }

        System.out.println("-------------------------------");
        if (totalVotes == 0) {
            System.out.println("Elections about to conduct.");
        } else
            System.out.println("Total Votes : " + totalVotes);
    }

    // 8. display result automatically shows winner
    public void displayResult () {

        System.out.println("\n---------- ELECTION RESULTS ----------");

        Candidate winner = null;

        for (Candidate candidate : candidates.values()) {

            if (winner == null ||
                    candidate.getVotes() > winner.getVotes()) {

                winner = candidate;
            }
        }


        if (winner == null || winner.getVotes() == 0) {
            System.out.println("winner cannot be determined.");
            return;
        }

        System.out.println(
                "Winner: " + winner.getName()
        );

        System.out.println(
                "Votes: " + winner.getVotes()
        );
    }

    // 9. to display runner-up
    public void displayRunnerUp () {

        System.out.println("\n---------- RUNNER-UP ----------");

        Candidate winner = null;
        Candidate runnerUp = null;

        for (Candidate candidate : candidates.values()) {

            if (winner == null ||
                    candidate.getVotes() > winner.getVotes()) {

                runnerUp = winner;
                winner = candidate;

            } else if (runnerUp == null ||
                    candidate.getVotes() > runnerUp.getVotes()) {
                runnerUp = candidate;
            }
        }


        if (runnerUp == null || runnerUp.getVotes() == 0) {

            System.out.println(
                    "Runner-up cannot be determined."
            );

            return;
        }

        System.out.println(
                "Runner-Up: "
                        + runnerUp.getName()
        );

        System.out.println(
                "Votes: "
                        + runnerUp.getVotes()
        );
    }
}

