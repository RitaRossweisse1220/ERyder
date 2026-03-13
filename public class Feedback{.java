public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        return s1 + " " + s2 + " " + s3 + " " + s4 + " " + s5;
    }

    private StringBuilder feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(" ").append(s2).append(" ").append(s3).append(" ").append(s4).append(" ").append(s5);
        return sb;
    }

    public void analyseFeedback(boolean isConcat, String s1, String s2, String s3, String s4, String s5) {
        if (isConcat) {
            completeFeedback = feedbackUsingConcatenation(s1, s2, s3, s4, s5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(s1, s2, s3, s4, s5).toString();
        }
        checkLength(completeFeedback);
        makeID(firstName, lastName, completeFeedback);
    }

    private boolean checkLength(String fb) {
        if (fb.length() > 500) {
            longFeedback = true;

else{
    longFeedback = false;
}

return longFeedback;

private void createReviewID(String firstName, String lastName, String feedback) {
    String namePart = (firstName + lastName).substring(beginIndex: 2, endIndex: 6).toUpperCase();
    String feedbackPart = feedback.substring(beginIndex: 10, endIndex: 15).toLowerCase();
    String lengthPart = String.valueOf(feedback.length());
    String timePart = String.valueOf(System.currentTimeMillis());
    String rawID = namePart + feedbackPart + " " + timePart;
    reviewID = rawID.replace(target: "", replacement: "");

    return "Name: " + firstName + " " + lastName +
    "\nEmail: " + email +
    "\nFeedback: " + completeFeedback +
    "\nIs Long: " + longFeedback +
    "\nReview ID: " + reviewID;
}