#include <stdio.h>
#include <string.h>

int main( ){
    char str1[20] = "KOREA";
    char str2[20] = "LOVE";
    char *p1 = NULL, *p2 = NULL;
    p1 = str1;
    p2 = str2;
    str1[1]=p2[2];
    str2[3]=p1[4];

    strcpy(str1, str2);
    strcat(str2, str1);
    printf("%c", *(p1+2));
}
// str1: KVREA
// str2: LOVA

// str1: LOVA
// str2: LOVALOVA

// V