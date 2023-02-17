package console.commads.generalCommands;

import application.App;

/**
 * AppCommand is abstract class which collaborate with App
 *
 * @see Command
 * @see App
 */
public abstract class AppCommand implements Command {
    private App app;

    public AppCommand(App app) {
        this.app = app;
    }

    public App getApp() {
        return this.app;
    }

}
