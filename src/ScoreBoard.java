public class ScoreBoard {
    static ScoreBoard instance = null;
    private long score = 0;

    public long getScore(){return score;}
    public void setScore(long score){this.score = score;}
    public void sumScore(long score, int mult){this.score += score * mult;}

    public static ScoreBoard getInstance(){
        if(instance == null){
            instance = new ScoreBoard();
        }
        return instance;
    }
}
