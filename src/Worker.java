public class Worker extends Client{

    public static final int REGULAR_WORKER = 1;
    public static final int MANAGER = 2;
    public static final int MANAGEMENT_TEAM = 3;

    private int rank;

    public Worker(String firstName, String lastName, String username, String password, boolean isWorker, boolean isClubMember, int rank) {
        super(firstName, lastName, username, password, isWorker, isClubMember);
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String toString() {
        String rankStr = "";
        switch (this.rank) {
            case REGULAR_WORKER: {
                rankStr = "Regular worker";
                break;
            }
            case MANAGER: {
                rankStr = "Manager";
                break;
            }
            case MANAGEMENT_TEAM: {
                rankStr = "Member of the management team";
                break;
            }
        }
        return (super.toString() + "("+ rankStr + ")");
    }
}