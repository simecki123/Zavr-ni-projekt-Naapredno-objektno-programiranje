package Controller;

import View.UrediRezervacije;

public class ClearTable extends AbstractComand{
    public ClearTable(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.clearTable();
    }
}
