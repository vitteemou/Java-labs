package by.bsu.fpmi;
public class Runner {
    public static String findSubstring(String s1, String s2)
    {
        String subStr = new String("");
        if(s1.isEmpty() || s2.isEmpty())
        {
            return subStr;
        }
        int m = s1.length();
        int n = s2.length();

        int curIndex = 0;
        int curLen = 0;

        int arr[][] = new int [m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if (s1.charAt(i) == s2.charAt(j))
                {
                    if (i > 0 && j > 0)
                    {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                    else
                    {
                        arr[i][j] = 1;
                    }
                    if (arr[i][j] > curLen)
                    {
                        curLen = arr[i][j];
                        curIndex = i - curLen + 1;
                    }
                }
            }
        }
        subStr = s1.substring(curIndex,curIndex+curLen);
        return subStr;
    }

    public static void main(String[] args) {
        String s1 = args[0];
        String s2 = args[1];
        System.out.println("(" + findSubstring(s1, s2) + ")");
    }
}
