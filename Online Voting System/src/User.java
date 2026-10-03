public class User {

    private String email;
    private String password;
    private String name;

    private User(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private String email;
        private String password;
        private String name;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
