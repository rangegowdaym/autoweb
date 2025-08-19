package pojo;

public class RegistrationPojo {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String confirmPassword;
    private boolean newsletterSubscription;
    private boolean privacyPolicyAgreed;

    // Simplified constructor
    public RegistrationPojo() {
    }

    // Simplified setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setNewsletterSubscription(boolean newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    public void setPrivacyPolicyAgreed(boolean privacyPolicyAgreed) {
        this.privacyPolicyAgreed = privacyPolicyAgreed;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean isNewsletterSubscription() {
        return newsletterSubscription;
    }

    public boolean isPrivacyPolicyAgreed() {
        return privacyPolicyAgreed;
    }
}