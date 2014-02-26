package cz.mpelant.droidmotepc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * The Class InputSimulator.
 */
public class InputSimulator {

    /**
     * Press key.
     *
     * @param input the input string
     * @param keys the keys
     * @param gui the gui for log
     */
    public static void pressKey(String input, Keys keys, MainWindow gui) {
        String[] parts = input.split(":");
        if (parts[0].equals(Commands.COMMAND_CHAR))
            sendCommand(keys.getKeyEvent(parts[1].charAt(0)), gui);
        else if (parts[0].equals(Commands.COMMAND_UTF8))
            sendUTF8(parts[1]);
        else if (parts[0].equals(Commands.COMMAND_KEY_EVENT_ID))    {
//           if(parts[1].equals("VK_W") ) {
//
//               try{
//                   Robot cacabot=new Robot();
//                   cacabot.keyPress(KeyEvent.VK_W);
//                   cacabot.keyRelease(KeyEvent.VK_W);
//
//               }
//               catch (AWTException e)
//               {
//                   System.out.println(e);
//               }
//
//           }
//
//            if(parts[1].equals("VK_S") ) {
//
//                try{
//                    Robot cacabot=new Robot();
//                    cacabot.keyPress(KeyEvent.VK_S);
//                    cacabot.keyRelease(KeyEvent.VK_S);
//                }
//                catch (AWTException e)
//                {
//                    System.out.println(e);
//                }
//
//            }
//            if(parts[1].equals("VK_A") ) {
//
//                try{
//                    Robot cacabot=new Robot();
//                    cacabot.keyPress(KeyEvent.VK_A);
//                    cacabot.keyRelease(KeyEvent.VK_A);
//
//                }
//                catch (AWTException e)
//                {
//                    System.out.println(e);
//                }
//
//            }
//
//            if(parts[1].equals("VK_D") ) {
//
//                try{
//                    Robot cacabot=new Robot();
//
//
//                    cacabot.keyPress(KeyEvent.VK_D);
//                    cacabot.keyRelease(KeyEvent.VK_D);
//
//                }
//                catch (AWTException e)
//                {
//                    System.out.println(e);
//                }
//
//            }
//
//            try{
//                Robot cacabot=new Robot();
//                while(true)
//                {
//                    cacabot.keyPress(KeyEvent.VK_W);
//                    cacabot.keyRelease(KeyEvent.VK_W);
//                }
//            }
//            catch (AWTException e)
//            {
//                System.out.println(e);
//            }

        }
        ///	sendCommand(keys.getKeyEvent(parts[1]), gui);
        else if (parts[0].equals(Commands.COMMAND_STRING)) {
            for (int i = 0; i < parts[1].length(); i++) {
                sendCommand(keys.getKeyEvent(parts[1].charAt(i)), gui);
            }
        } else if (parts[0].equals(Commands.COMMAND_MULTICHAR)) {
            int[] keyEvents = new int[parts[1].length()];
            for (int i = 0; i < parts[1].length(); i++) {
                keyEvents[i] = keys.getKeyEvent(parts[1].charAt(i));
            }
            sendCommand(keyEvents, gui);
        } else if (parts[0].equals(Commands.COMMAND_MULTI_KEY_EVENT_ID)) {
            String[] keyEventsString = parts[1].split(";");
            int[] keyEvents = new int[keyEventsString.length];
            for (int i = 0; i < keyEventsString.length; i++) {
                keyEvents[i] = keys.getKeyEvent(keyEventsString[i]);
            }
            sendCommand(keyEvents, gui);

        } else if (parts[0].equals(Commands.COMMAND_MOUSE_MOVE)) {
            String[] coordinates = parts[1].split(";");
            moveMouse(Integer.parseInt(coordinates[0],10), Integer.parseInt(coordinates[1],10));
        } else if (parts[0].equals(Commands.COMMAND_MOUSE_PRESS)) {
            mousePress(parts[1]);
        } else if (parts[0].equals(Commands.COMMAND_MOUSE_RELEASE)) {
            mouseRelease(parts[1]);
        } else if (parts[0].equals(Commands.COMMAND_MOUSE_CLICK)) {
            mousePress(parts[1]);
            mouseRelease(parts[1]);
        } else {
            gui.println("Unknown command " + input);
        }

    }

    /**
     * Mouse button press.
     *
     * @param value the value
     */
    private static void mousePress(String value) {

        int mouseButton = getMouseButtonID(value);
        if (mouseButton == -1)
            return;
        try {
            Robot robot = new Robot();
            robot.mousePress(mouseButton);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mouse button release.
     *
     * @param value the value
     */
    private static void mouseRelease(String value) {
        int mouseButton = getMouseButtonID(value);
        if (mouseButton == -1)
            return;
        try {
            Robot robot = new Robot();
            robot.mouseRelease(mouseButton);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the mouse button id.
     *
     * @param value the value
     * @return the mouse button id
     */
    private static int getMouseButtonID(String value) {
        if (value.equals(Commands.VALUE_MOUSE_LEFT))
            return InputEvent.BUTTON1_MASK;
        if (value.equals(Commands.VALUE_MOUSE_RIGHT))
            return InputEvent.BUTTON3_MASK;
        if (value.equals(Commands.VALUE_MOUSE_MIDDLE))
            return InputEvent.BUTTON2_MASK;
        else
            return -1;
    }

    /**
     * Move mouse.
     *
     * @param x the diff of x axis
     * @param y the diff of y axis
     */
    private static void moveMouse(int x, int y) {

            try{
                Robot pula=new Robot();
                //System.out.println("x = "+x);
                //System.out.println("y = "+y);
                //Process proc = Runtime.getRuntime().exec("");
//                pula.keyPress(KeyEvent.VK_W);
//                pula.keyRelease(KeyEvent.VK_W);

//                String[] envar = {"VAR=path"};
                String[] cmd = //{"/home/mihai/IdeaProjects/ecaHACK/driver.sh",x+"",y+""};
                        {"/bin/bash","-c","xte \"mousermove "+ x +" "+ y +"\""};

                System.out.println(cmd[1]+' '+cmd[2]);
                Process pb = Runtime.getRuntime().exec(cmd);//,envar);

//                pula.mouseMove( x+60,y+60);
            }
            catch(AWTException cacat)
            {

            }
            catch(IOException cacat)
            {
                System.out.println("oh, cacat:"+cacat);
            }



 }
//            int nX = MouseInfo.getPointerInfo().getLocation().x; // Get location
//            int nY = MouseInfo.getPointerInfo().getLocation().y; // Get location


    /**
     * Press the utf8 character as a alt+keycode combination
     *
     * @param keycode the keycode
     */
    private static void sendUTF8(String keycode) {
        Robot robot = null;
        try {
            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);

            for (int i = 0; i < keycode.length(); i++) {
                robot.keyPress(KeyEvent.class.getDeclaredField("VK_NUMPAD" + keycode.charAt(i)).getInt(null));
                robot.keyRelease(KeyEvent.class.getDeclaredField("VK_NUMPAD" + keycode.charAt(i)).getInt(null));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        robot.keyRelease(KeyEvent.VK_ALT);

    }

    /**
     * Send command.
     *
     * @param keyEvent the key event
     * @param gui the gui
     */
    private static void sendCommand(int keyEvent, MainWindow gui) {
        try {
            Robot robot = new Robot();
            gui.println("pressing " + KeyEvent.getKeyText(keyEvent) + " (" + keyEvent + ")");
            robot.keyPress(keyEvent);
            robot.keyRelease(keyEvent);
            gui.println("releasing " + KeyEvent.getKeyText(keyEvent) + " (" + keyEvent + ")");
        } catch (Exception e) {
            gui.println("unrecognized KeyEvent");
        }

    }

    /**
     * Send command.
     *
     * @param keyEvents the key events
     * @param gui the gui
     */
    private static void sendCommand(int[] keyEvents, MainWindow gui) {
        try {
            Robot robot = new Robot();
            for (int keyEvent : keyEvents) {
                gui.println("pressing " + KeyEvent.getKeyText(keyEvent) + " (" + keyEvent + ")");
                robot.keyPress(keyEvent);
            }
        } catch (Exception e) {
            gui.println("unrecognized KeyEvent");
        } finally {
            try {
                Robot robot = new Robot();
                for (int keyEvent : keyEvents) {
                    gui.println("releasing " + KeyEvent.getKeyText(keyEvent) + " (" + keyEvent + ")");
                    robot.keyRelease(keyEvent);
                }
            } catch (Exception e) {
                gui.println("unrecognized KeyEvent");
            }
        }
    }

}
