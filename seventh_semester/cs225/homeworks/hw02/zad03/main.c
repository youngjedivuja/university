#include <sys/stat.h>
#include <resolv.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>

#define FIFO "/fifo"

int main() {
    if (mkfifo(FIFO, 0777 | 04000) == -1) {
        printf("Unable to create FIFO");
        exit(1);
    }

    pid_t w_pid = fork();
    if (w_pid != 0) {
        const char *message = "Message";
        int fd = open(FIFO, 01);
        if (fd == -1) {
            printf("Unable to open FIFO");
            return 1;
        }
        fprintf(stderr, "Writing '%s'\n", message);
        write(fd, message, strlen(message));
        return 0;
    }
    pid_t r_pid = fork();
    if (r_pid != 0) {
        char buf[BUFSIZ] = {0};
        char *ptr = buf;
        int fd = open(FIFO, 00);
        if (fd == -1) {
            perror("Unable to open FIFO");
            return 1;
        }

        while (read(fd, ptr++, 1) > 0);
        fprintf(stderr, "Reading '%s'\n", buf);
        return 0;
    }

    waitpid(w_pid, NULL, 0);
    waitpid(r_pid, NULL, 0);

    fprintf(stderr, "Removing %s\n", FIFO);
    remove(FIFO);
    return 0;
}