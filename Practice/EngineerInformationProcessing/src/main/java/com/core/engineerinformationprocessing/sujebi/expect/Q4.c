#include <stdio.h>

int main() {
    int a[3][2] = {{2, 3}, {5}, {7}};
    int i, sum = 0;
    int *p;
    p = a[0];
    for (i = 0; i < 3; ++i)
    sum += *(p + i);
    printf("%d\n", sum);
}