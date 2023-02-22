package Controller;

import View.UrediRezervacije;
/**
 * Class that presents command that deletes table.
 */
public class DeleteTableCMND extends AbstractComand {
    public DeleteTableCMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.deleteTable();
    }
}
