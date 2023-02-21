package Controller;

import View.UrediRezervacije;

public class DeleteTableCMND extends AbstractComand {
    public DeleteTableCMND(UrediRezervacije urediRezervacije) {
        super(urediRezervacije);
    }

    @Override
    public void execute() {
        urediRezervacije.deleteTable();
    }
}
