package com.command;

public class RemoteControl {
    private Command lastCommand;

    public void submit(Command command) {
        command.execute();
        lastCommand = command;
    }

    public void undo() {
        if (lastCommand != null) lastCommand.undo();
    }
}
