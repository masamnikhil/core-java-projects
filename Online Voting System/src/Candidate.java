public class Candidate {

    private int id;
    private String name;
    private int votes;

    private Candidate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.votes = builder.votes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void addVote() {
        votes++;
    }

    // builder pattern for easy object creation
    public static class Builder {

        private int id;
        private String name;
        private int votes;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder votes(int votes) {
            this.votes = votes;
            return this;
        }

        public Candidate build() {
            return new Candidate(this);
        }
    }
}
