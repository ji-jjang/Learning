package chapter10;

// 크기를 모르는 배열, 크기 구하기
public class Q4 {

  public static void main(String[] args) {
    Integer[] array = new Integer[1000];
    for (int i = 0; i < 507; i++) {
      array[i] = i;
    }

    int length = findArrayLength(array);
    System.out.println("length = " + length);
  }

  public static int findArrayLength(Integer[] array) {

    int low = 0;
    int high = 1;

    while (array[high] != null) {
      low = high;
      high = 2 * high;
    }

    return binarySearchLength(array, low, high);
  }

  private static int binarySearchLength(Integer[] array, int low, int high) {

    while (low <= high) {
      int mid = low + high >>> 1;
      if (array[mid] == null) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}
