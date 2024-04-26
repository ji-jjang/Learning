#include <stdio.h>
int main(int argc, char *argv[])
{
    int a = 7; int s = 0; int sum=0;
    while(a<10){
        a++;
        if (a%2 == 1) continue;
        sum += a;
    }
    switch(a/2){
    case 2 : s++;
    case 3 : a += s;
    default : a++;
    }
    printf("%d%d", s, a); return 0;
}
// 011