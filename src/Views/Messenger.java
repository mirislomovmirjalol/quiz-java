package Views;

public class Messenger {
    public void oneLineTitle(String title) {
        System.out.println("""
                \n
                ______________________________________________________________
                """ + title + """
                \n______________________________________________________________
                """);
    }

    public void twoLineTitle(String firstTitle, String secondTitle) {
        System.out.println(
                """
                        \n
                        ______________________________________________________________
                        """ + firstTitle + "\n\n" + """
                        """ + secondTitle + """
                        \n______________________________________________________________
                        """);
    }

    public void loginSuccessfully(String username) {
        System.out.println("\n\n" +
                """
                        ______________________________
                         User logged in successfully
                         
                         Hello\t""" + username +
                """
                        \n______________________________
                        """
        );
    }

    public void loginFailed() {
        System.out.println("\n\n" +
                """
                        ______________________________
                         User login failed
                         
                         Please check your credentials
                        ______________________________
                        """
        );
    }

    public void registerSuccessfully(String username) {
        System.out.println("\n\n" + """
                ______________________________
                 User registered successfully
                 
                 """ + " Hello\t" + username + """
                \n______________________________
                """);
    }

    public void registerFailed() {
        System.out.println("\n\n" + """
                ______________________________
                 User registration failed
                 
                 Please try again
                ______________________________
                """);
    }

    public void notAdmin() {
        System.out.println("\n\n" + """
                ______________________________
                 You are not an admin!
                 
                 Please login as an admin
                ______________________________
                """);
    }

    public void notLoggedIn() {
        System.out.println("\n\n" + """
                ______________________________
                 You are not logged in!
                 
                 Please login for this option
                ______________________________
                """);
    }

    public void endQuiz(int score, int questionsSize) {
        System.out.println("""
                \n
                                
                ______________________________________________________________
                """ + "You got " + score + " out of " + questionsSize + " correct!" + """
                \n______________________________________________________________
                """);
    }

    public void updateQuestion(String oldQuestion) {
        System.out.println("\n\n" + """
                ______________________________________________________
                 Please enter the question
                 """ + "Old question: " + oldQuestion + """
                \n______________________________________________________
                """);
    }

    public void updateAnswer(String oldAnswer) {
        System.out.println("\n\n" + """
                ______________________________________________________
                 Please enter the answer
                 """ + "Old answer: " + oldAnswer + """
                \n______________________________________________________
                """);
    }

    public void updateOption(String oldOption) {
        System.out.println("\n\n" + """
                ______________________________________________________
                 Please enter the option
                 """ + "Old option: " + oldOption + """
                \n______________________________________________________
                """);
    }
}
