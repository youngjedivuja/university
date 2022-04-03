#include <stdio.h>
#include <bits/pthreadtypes.h>
#include <pthread.h>
#include <unistd.h>

void* function1();
void* function2();
void* function3();
void* function4();
pthread_mutex_t first_mutex;
pthread_mutex_t second_mutex;

int main() {
    pthread_t thread1, thread2, thread3, thread4;
    pthread_create(&thread1, NULL, function1, NULL);
    pthread_create(&thread2, NULL, function2, NULL);
    pthread_create(&thread3, NULL, function3, NULL);
    pthread_create(&thread4, NULL, function4, NULL);
    return 0;
}

void* function1() {
    printf("Thread ONE started\n");
    sleep(1);
    printf("Thread ONE finished\n");
    return NULL;
}

void* function2() {
    pthread_mutex_lock(&second_mutex);
    printf("Thread TWO acquired second_mutex\n");
    sleep(1);
    pthread_mutex_lock(&first_mutex);
    printf("Thread TWO acquired first_mutex\n");
    pthread_mutex_unlock(&first_mutex);
    printf("Thread TWO released first_mutex\n");
    pthread_mutex_unlock(&second_mutex);
    printf("Thread TWO released second_mutex\n");
    return NULL;
}

void* function3() {
    printf("Thread THREE started\n");
    sleep(1);
    printf("Thread THREE finished\n");
    return NULL;
}

void* function4() {
    pthread_mutex_lock(&first_mutex);
    printf("Thread ONE acquired first_mutex\n");
    sleep(1);
    pthread_mutex_lock(&second_mutex);
    printf("Thread ONE acquired second_mutex\n");
    pthread_mutex_unlock(&second_mutex);
    printf("Thread ONE released second_mutex\n");
    pthread_mutex_unlock(&first_mutex);
    printf("Thread ONE released first_mutex\n");
    return NULL;
}