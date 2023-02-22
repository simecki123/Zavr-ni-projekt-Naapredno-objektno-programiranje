package Controller;

import View.UrediRezervacije;

public abstract class AbstractComand {
    protected UrediRezervacije urediRezervacije;

    public AbstractComand(UrediRezervacije urediRezervacije){
        this.urediRezervacije = urediRezervacije;
    }

    /**
     * Method that executes command.
     *
     */
    public abstract void execute();

    /**
     * Method for undo.
     */
    public void undo(){
        urediRezervacije.undo();
    }

    /**
     * Method for redo.
     */
    public void redo(){
        urediRezervacije.redo();
    }
}
