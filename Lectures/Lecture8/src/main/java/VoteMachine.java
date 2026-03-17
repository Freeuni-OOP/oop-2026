public class VoteMachine {
    private int votes;

    public VoteMachine() {
        votes = 0;
    }

    public int getVotes() {
        return votes;
    }

    // Creates a regular Clicker object -- one click, one vote.
    Clicker createClicker() {
        // Make a little anonymous inner class object implementing Clicker.
        Clicker anon = new Clicker() {
            public void click() {
                votes++;
            }
        };
        return anon;
    }

    // Creates a special clicker object -- one click, N votes.
    Clicker createClicker(int change) {
        // Make Clicker anon inner class.
        // TRICK: Anon inner code cannot refer to local variables
        // from where it is defined.
        // However, anon inner can refer to FINAL local variables.
        final int finalChange = change;
        Clicker clicker = new Clicker() {
            public void click() {
                // votes += change; // NO does not compile
                votes += finalChange; // ok, this works
            }
        };
        change++; // change changes!
        return clicker;
    }

    public static void main(String[] args) {
        VoteMachine voteMachine = new VoteMachine();
        Clicker a = voteMachine.createClicker();
        Clicker b = voteMachine.createClicker(10);

        // a and b are objects -- can store them, pass them around, etc.
        a.click();
        a.click();
        b.click();
        System.out.println(voteMachine.getVotes()); // 12
    }
}