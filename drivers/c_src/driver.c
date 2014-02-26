#include <stdio.h>
#include <unistd.h>
#include <linux/input.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/time.h>

int main(int argc, char **argv){
  struct input_event event_x, event_end_x,event_y, event_end_y;
  int x,y;
  x=atoi(argv[1]);
  y=atoi(argv[2]);
  printf("The event being written to:%s",argv[3]);

  //argv[3]
  //"/dev/input/event12"
  int fd = open(argv[3], O_RDWR);
  if(!fd){
    printf("Errro open mouse:%s\n", strerror(errno));
    return -1;
  }
  memset(&event_x, 0, sizeof(event_x));
  memset(&event_x, 0, sizeof(event_end_x));
  gettimeofday(&event_x.time, NULL);
  event_x.type = EV_REL;
  event_x.code = REL_X;
  event_x.value = x;
  gettimeofday(&event_end_x.time, NULL);
  event_end_x.type = EV_SYN;
  event_end_x.code = SYN_REPORT;
  event_end_x.value = 0;
  //for(int i=0; i<20; i++){
    write(fd, &event_x, sizeof(event_x));// Move the mouse
    write(fd, &event_end_x, sizeof(event_end_x));// Show move
  


  memset(&event_y, 0, sizeof(event_y));
  memset(&event_y, 0, sizeof(event_end_y));
  gettimeofday(&event_y.time, NULL);
  event_y.type = EV_REL;
  event_y.code = REL_Y;
  event_y.value = y;
  gettimeofday(&event_end_y.time, NULL);
  event_end_y.type = EV_SYN;
  event_end_y.code = SYN_REPORT;
  event_end_y.value = 0;
  //for(int i=0; i<20; i++){
    write(fd, &event_y, sizeof(event_y));// Move the mouse
    write(fd, &event_end_y, sizeof(event_end_y));// Show move
  //  sleep(1);// wait
  //}
  close(fd);
  return 0;
}
