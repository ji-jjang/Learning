#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]){
    char *p = "hello soojebi world";
    char *pSoojebi = "soojebi";
    char *pTemp;
    pTemp = strstr(p, pSoojebi);

    if(pTemp != NULL ) {
        printf("%s", pTemp);
    } else {
        printf("%s", p);
    }
    return 0;
 }