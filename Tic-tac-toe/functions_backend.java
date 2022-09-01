public class functions_backend {
    int n = 3;
    int[][] a = new int[n][n];
    int win;
    functions_backend() {
        fillArray();
    }

    public static void main(String[] args) {
        functions_backend obj = new functions_backend();
        obj.fillArray();
        obj.display(obj.a);
    }

    public boolean row_check(int[][] a, int num) {
        boolean flag;
        for (int i = 0; i < n; i++) {
            flag = true;
            for (int j = 0; j < n; j++) {
                if (num != a[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                win = i;
                return true;
            }
        }
        return false;
    }

    public boolean col_check(int[][] a, int num) {
        boolean flag;
        for (int i = 0; i < n; i++) {
            flag = true;
            int j;
            for (j = 0; j < n; j++) {
                if (num != a[j][i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                win = i;
                return true;
            }
        }
        return false;
    }

    public boolean diag_checker(int[][] a, int num) {
        boolean flag = true;
        int i ;
        for ( i = 0; i < n; i++) {
            if (a[i][i] != num) {
                flag = false;
                break;
            }
        }
        if (flag) {
            win = 0;
            return true;
        }
        flag = true;
        for (i = 0; i < n; i++) {
            if (a[i][n - i - 1] != num) {
                flag = false;
                break;
            }
        }
        if (flag)
        {
            win = 2;
            return true;
        }
        return false;
    }

    public boolean isTied(int[][] a)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == -1)return false;
            }
        }
        return true;
    }

    public boolean Checker(int[][] a, int num) {
        return row_check(a, num) | col_check(a, num) | diag_checker(a, num) ;
    }
    public int getWin(int[][] a,int num)
    {
        if (row_check(a,num))
        {
            return -1;
        }
        if (col_check(a,num))return 0;
        if (diag_checker(a,num))return 1;
        return -2;
    }
    public void display(int[][] a) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillArray() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = -1;
            }
        }
    }

    public void setArray(int num, int row, int col) {
        a[row][col] = num;
    }

    public boolean isEmpty(int[][] a, int row, int col) {
        return a[row][col] == -1;
    }

}
