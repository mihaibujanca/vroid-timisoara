// create_device.c
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
struct uinput_user_dev uinp;
// uInput device structure
struct input_event event; // Input device structure
/* Setup the uinput device */
int setup_uinput_device()
{
    int i=0;
    uinp_fd = open("/dev/uinput", O_WRONLY | O_NDELAY | O_NONBLOCK);
    if (uinp_fd == NULL)
    {
        printf("Unable to open /dev/uinput\n");
        return -1;
    }
    //printf("%d",uinp_fd);
    memset(&uinp,0,sizeof(uinp)); // Intialize the uInput device to NULL
    strncpy(uinp.name, "VROID", UINPUT_MAX_NAME_SIZE);
    uinp.id.version = 4;
    uinp.id.bustype = BUS_USB;

    // Setup the uinput device
    ioctl(uinp_fd, UI_SET_EVBIT, EV_KEY);
    ioctl(uinp_fd, UI_SET_EVBIT, EV_REL);
    ioctl(uinp_fd, UI_SET_RELBIT, REL_X);
    ioctl(uinp_fd, UI_SET_RELBIT, REL_Y);
    for (i=0; i < 256; i++)
    {
        ioctl(uinp_fd, UI_SET_KEYBIT, i);
    }
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_MOUSE);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_TOUCH);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_MOUSE);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_LEFT);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_MIDDLE);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_RIGHT);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_FORWARD);
    ioctl(uinp_fd, UI_SET_KEYBIT, BTN_BACK);
    /* Create input device into input sub-system */
    write(uinp_fd, &uinp, sizeof(uinp));
    if (ioctl(uinp_fd, UI_DEV_CREATE))
    {
        printf("Unable to create UINPUT device.");
        return -1;
    }
    return 1;

}


int main()
{
    if( setup_uinput_device() < 0 )
    {
        printf("Unable to find uinput device\n");
        return -1;
    }

    printf("opened device");

   /* 
    while( 0 )
    {
	read(0, &event, sizeof(event));

//		printf(&event);
        write(uinp_fd, &event, sizeof(event));
		usleep( 1000 );
		fflush(stdout);
		
    }
    */

    /*
    static int uinp_fd = -1;
    uinp_fd = open("/dev/uinput", O_WRONLY | O_NDELAY | O_NONBLOCK);
    if (uinp_fd == NULL)
    {
        printf("Unable to open /dev/uinput\n");
        return -1;
    }
*/
    //ioctl(uinp_fd, UI_DEV_DESTROY);
    close(uinp_fd);
    
}
