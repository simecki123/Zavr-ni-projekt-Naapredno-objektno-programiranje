package Controller;

import View.UrediRezervacije;

public abstract class AbstractComand {
    protected UrediRezervacije urediRezervacije;

    public AbstractComand(UrediRezervacije urediRezervacije){
        this.urediRezervacije = urediRezervacije;
    }

    public abstract void execute();
    public void undo(){
        urediRezervacije.undo();
    }
    public void redo(){
        urediRezervacije.redo();
    }
}
