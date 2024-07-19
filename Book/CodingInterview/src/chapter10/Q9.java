package chapter10;

// 각 행과 열이 오름차순으로 정렬된 M * N 행렬, 특정한 원소를 찾는 메서드
public class Q9 {

  static int[][] matrix = {
    {1, 4, 7, 11, 15},
    {2, 5, 8, 12, 19},
    {3, 6, 9, 16, 22},
    {10, 13, 14, 17, 24},
    {19, 21, 23, 26, 30}
  };

  public static void main(String[] args){

    int r = matrix.length;
    int c = matrix[0].length;
    int target = 16;
    int[] ans = searchMaxtrix(target, r, c);
    System.out.println(ans[0] + " " + ans[1]);
  }

  private static int[] searchMaxtrix(int target, int row, int col) {

    int r = row - 1;
    int c = 0;

    while (r >= 0 && c < col) {
      if (matrix[r][c] == target) {
        return new int[]{r, c};
      } else if (matrix[r][c] > target) {
        --r;
      } else {
        ++c;
      }
    }

    return new int[] {-1, -1};
  }
}
