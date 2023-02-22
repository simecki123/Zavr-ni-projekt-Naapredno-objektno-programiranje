package Controller;

import View.UrediRezervacije;
/**
 * Class that presents command that clears table.
 */
public class ClearTableMND extends AbstractComand{
    public ClearTableMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.clearTable();
    }
}
