#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <linux/input.h>
#include <linux/uinput.h>
#include <stdio.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>

static int uinp_fd = -1;
    
int main(int argc, char **argv){

    uinp_fd = open("/dev/uinput", O_WRONLY | O_NDELAY | O_NONBLOCK);
    if (uinp_fd == NULL)
    {
        printf("Unable to open /dev/uinput\n");
        return -1;
    }

    ioctl(uinp_fd, UI_DEV_DESTROY);
    close(uinp_fd);

   } 