import java.util.ArrayList;
import java.util.Arrays;
//use new data structure (hashset)

public class Board {
    static ArrayList<int[]> boards = new ArrayList<int[]>();
    static ArrayList<int[]> winningboards = new ArrayList<>();
    static ArrayList<int[]> losingboards = new ArrayList<>();
    static ArrayList<int[]> currentlosingboards = new ArrayList<>();
    static int[] placeholder = new int[10];//change here for width
    static ArrayList<int[]> boardscopy = new ArrayList<>();

    public static void Initialize(int width, int height) {
        int[] firstloss = new int[width];
        firstloss[0] = 1;
        currentlosingboards.add(firstloss);

        allboardstates(0, height);
        boards.remove(0);
        boardscopy = (ArrayList) boards.clone();
        boards.remove(0);


        while (true) {
            // find all winning boards one move away from currentlosingboards
            if (boards.size() == 0) {
                PrintSet(currentlosingboards);
                break;
            }
            //find winning and insert/remove respectively
            System.out.println("find winning boards");
            int j = 0;
            while (j < boards.size()) {
                int p = 0;
                int[] copiedarray = boards.get(j);
                for (int[] i : currentlosingboards) {
                    if (Board.OneMove(copiedarray, i) == true)
                    {
                        p++;
                        break;
                    }
                }
                if (p == 1) {
                    winningboards.add(copiedarray);
                    boards.remove(j);
                }
                else {
                    j++;
                }
            }


            //find losing and insert/remove
            System.out.println("finding losing boards");
            int k = 0;
            while (k < boards.size()) {
                int[] copiedarray = boards.get(k);
                boolean losing = checkLosingPos(copiedarray);
                if (losing == true) {
                    currentlosingboards.add(copiedarray);
                    boards.remove(k);
                }
                else {
                    k++;
                }
            }
            System.out.println("loop done: " + boards.size());
        }

    }





    //winning function
    static boolean checkLosingPos(int[]currentboard) {
        int[] emptyarray = new int[currentboard.length];
        for(int i=0; i<currentboard.length;i++){
            for (int j=0;j<currentboard[i]; j++){
                int[] copyboard = currentboard.clone();
                for (int k=i; k<currentboard.length;k++){
                    if(currentboard[k]>j){
                        copyboard[k]=j;
                    }
                }
                int counter = 0;
                for(int[] m : winningboards){
                    if(Arrays.equals(copyboard, m) == true){
                        counter++;
                        break;
                    }
                }
                if(Arrays.equals(copyboard, emptyarray) == true){
                    counter++;
                }
                if(counter == 0){
                    return false;
                }
            }
        }
        return true;
    }



    //subtract 2 boards to get new array  - for all non 0 elements of that array the corresponding elements of subtracted board must be equal
    public static boolean OneMove(int[] input1, int[] input2)//input 1 is greater than input 2
    {
        ArrayList<Integer>subtraction = new ArrayList<>();
        if(Arrays.equals(input1, input2)){
            return false;
        }
        for(int i=0;i<input1.length;i++){
            if(input1[i]-input2[i]>0){
                subtraction.add(i);
            }
            if(input1[i]-input2[i]<0){
                return false;
            }
        }
        for(int j : subtraction) {
            if (input2[subtraction.get(0)]==input2[j]){

            }
            else{
                return (false);
            }
        }

        return (true);
    }

    //
    public static void InsertBoard(ArrayList<int[]> boardSet, int[] set1)
    {
        boolean add = true;
        for (int[] i : boardSet) {
            if (Arrays.equals(i, set1) == true) {
                add = false;
                break;
            }
        }
        if (add == true) {
            boardSet.add(set1);
        }
    }

    public static void PrintSet(ArrayList<int[]> boardSet)
    {
        for (int[] i : boardSet) {
            System.out.println(Arrays.toString(i));
        }
    }
    public static void allboardstates (int start, int height)
    {
        if (start == 10){//change here for width
            int[] copy = placeholder.clone();
            boards.add(copy);
            return;
        }
        for (int i = 0; i <= height; i++) {
            placeholder[start] = i;
            allboardstates(start + 1, i);
        }
    }
    //public static void main (String[] args){
    //    int[] a = {2,2,0};
    //    int[] b = {1,0,0};
    //    System.out.print(OneMove(a,b));
    //}
    }



