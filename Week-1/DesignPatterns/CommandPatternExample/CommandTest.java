package com.command;
// Exercise 3: Command Pattern
public class CommandTest {
    public static void main(String[] args) {
        Light livingRoom = new Light("Living Room");
        RemoteControl remote = new RemoteControl();

        remote.submit(new LightOnCommand(livingRoom));
        remote.undo();
        remote.submit(new LightOffCommand(livingRoom));
        remote.undo();
    }
}
