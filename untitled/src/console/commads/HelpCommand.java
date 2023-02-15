package console.commads;

public class HelpCommand implements Command {

    @Override
    public void execute(String command) {
        System.out.println("Help");
    }
}
