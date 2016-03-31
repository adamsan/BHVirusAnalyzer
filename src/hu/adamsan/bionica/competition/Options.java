package hu.adamsan.bionica.competition;

public class Options {
    private String server = null;
    public void process(String[] args) {
        for (int i = 0; i + 1 < args.length; i += 2) {
            switch (args[i].toLowerCase()) {
            case "--server":
                server = args[i + 1];
                break;
            default:
                throw new IllegalArgumentException("Invalid switch. Valid switches are: --server <SERVER_URL>");
            }
        }
    }
    public String getServer() {
        return server;
    }
}
