package Controller;

import View.UrediRezervacije;

/**
 * Class that presents command that clears row.
 */
public class ClearRowCMND extends AbstractComand{
    public ClearRowCMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.clearRow();
    }
}
