#include <stdio.h>

struct SOOJEBI_STRUCT {
    int n;
    char c;
};

int main() {
    struct SOOJEBI_STRUCT s;
    printf("%ld",sizeof(struct SOOJEBI_STRUCT));
    printf("%ld", sizeof(s));
    return 0;
}