import java.util.ArrayList;
import java.util.List;

class Board implements Ilayout, Cloneable {

    private static final int dim = 3;
    private int board[][];

    public Board() {
        board = new int[dim][dim];
    }

    public Board(String str) throws IllegalStateException {

        if (str.length() != dim * dim){
            throw new IllegalStateException("Invalid arg in Board constructor");
        }

        board = new int[dim][dim];
        int si = 0;

        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                board[i][j] = Character.getNumericValue(str.charAt(si++));
            }
        }
    }

    public Board(Board other) {
        board = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(other.board[i], 0, board[i], 0, dim);
        }
    }

    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                if (board[i][j] == 0){
                    stringBuilder.append(" ");
                }
                else {
                    stringBuilder.append(board[i][j]);
                }
            }
            if(i < dim - 1){
                stringBuilder.append("\r\n");
            }
        }

        return stringBuilder.toString();
    }

    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    public int hashCode() {
        return 0;
    }

    private ArrayList<Integer> locateSpace(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0){
                    result.add(i);
                    result.add(j);
                    return result;
                }
            }
        }
        return result;
    }


    @Override
    public List<Ilayout> children() {
        ArrayList<Ilayout> children = new ArrayList<>();
        ArrayList<Integer> spaceLocation = locateSpace();

        Board temp;

        try{
            temp = (Board) this.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

        int numberToChange;

        if (spaceLocation.get(0) + 1 < 3){
            numberToChange = temp.board[spaceLocation.get(0) + 1][spaceLocation.get(1)];
            temp.board[spaceLocation.get(0) + 1][spaceLocation.get(1)] = 0;
            temp.board[spaceLocation.get(0)][spaceLocation.get(1)] = numberToChange;
            children.add(temp);
        }

        try{
            temp = (Board) this.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

        if (spaceLocation.get(0) - 1 >= 0){
            numberToChange = temp.board[spaceLocation.get(0) - 1][spaceLocation.get(1)];
            temp.board[spaceLocation.get(0) - 1][spaceLocation.get(1)] = 0;
            temp.board[spaceLocation.get(0)][spaceLocation.get(1)] = numberToChange;
            children.add(temp);
        }

        try{
            temp = (Board) this.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

        if (spaceLocation.get(1) + 1 < 3){
            numberToChange = temp.board[spaceLocation.get(0)][spaceLocation.get(1) + 1];
            temp.board[spaceLocation.get(0)][spaceLocation.get(1) + 1] = 0;
            temp.board[spaceLocation.get(0)][spaceLocation.get(1)] = numberToChange;
            children.add(temp);
        }

        try{
            temp = (Board) this.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

        if (spaceLocation.get(1) - 1 >= 0){
            numberToChange = temp.board[spaceLocation.get(0)][spaceLocation.get(1) - 1];
            temp.board[spaceLocation.get(0)][spaceLocation.get(1) - 1] = 0;
            temp.board[spaceLocation.get(0)][spaceLocation.get(1)] = numberToChange;
            children.add(temp);
        }

        return children;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return equals(l);
    }

    @Override
    public double getK() {
        return 1;
    }
}
