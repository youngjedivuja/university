#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {

    pid_t pid = fork();
    if (pid == 0) {
        printf("C) Child's pid:  %d\n", getpid());
        printf("D) Child's child pid : %d\n", getppid());
    } else {
        printf("A) Paren pid: %d\n", pid);
        wait(NULL);
        printf("B) Parent's child pid:  %d\n", getpid());
    }
    return 0;
}
