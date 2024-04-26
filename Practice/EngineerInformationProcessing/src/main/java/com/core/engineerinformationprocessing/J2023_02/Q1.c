#include <stdio.h>

int main(void) {

    int n[5];
    int i;

    for (i = 0; i < 5; i++) {
        printf("숫자를 입력해주세요 : ");
        scanf("%d", &n[i]);
    }
    // 5 4 3 2 1

    // 4 3 2 1 5

    for (i = 0; i < 5; i++) {
     printf("%d", n[(i+1) % 5] );
    }

    return 0;
}