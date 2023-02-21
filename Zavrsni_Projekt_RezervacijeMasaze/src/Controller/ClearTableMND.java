package Controller;

import View.UrediRezervacije;

public class ClearTableMND extends AbstractComand{
    public ClearTableMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.clearTable();
    }
}
