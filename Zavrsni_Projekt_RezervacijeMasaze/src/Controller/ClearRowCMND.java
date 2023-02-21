package Controller;

import View.UrediRezervacije;

public class ClearRowCMND extends AbstractComand{
    public ClearRowCMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.clearRow();
    }
}
