package commands;

import java.util.Stack;

public class CommandManager {

    // TODO
    /* - void undo()
       - void redo()
       - void executeCommand(Command c)
       - maybe check if undo() and redo() are available ?
             -> check GameState class, see the errors
       - keep track of the commands somehow
    */
    Stack<Command> commandsStack = new Stack<Command>();
    Stack<Command> redoStack = new Stack<Command>();

    public void executeCommand(Command c) {
        commandsStack.push(c);
        c.execute();
    }
    public void undo() {
        Command c = commandsStack.pop();
        redoStack.push(c);
        c.undo();
    }
    public void redo() {
        Command c = redoStack.pop();
        c.execute();
    }
    public boolean isUndoAvailable() {
        return !commandsStack.empty();
    }

    public boolean isRedoAvailable() {
        return !redoStack.empty();
    }
}
