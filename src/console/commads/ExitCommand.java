package console.commads;

public class ExitCommand implements Command {

    @Override
    public void execute(String command) {
        System.out.println("До новой встречи!");
        System.exit(0);
    }
}
