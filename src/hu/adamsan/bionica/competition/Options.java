package hu.adamsan.bionica.competition;

public class Options {
    public enum DB {
        POSTGRES, SQLITE, MEMORY;
    }

    private String server = null;
    private DB db;

    public void process(String[] args) {
        for (int i = 0; i + 1 < args.length; i += 2) {
            switch (args[i].toLowerCase()) {
            case "--server":
                server = args[i + 1];
                break;
            case "--db":
                switch (args[i + 1].toLowerCase()) {
                case "postgres":
                    db = DB.POSTGRES;
                    break;
                case "sqlite":
                    db = DB.SQLITE;
                    break;
                case "memory":
                    db = DB.MEMORY;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid parameter for switch --db. Valid parameters are: postgres, sqlite, memory");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid switch. Valid switches are: --server <SERVER_URL>, --db <DB_TYPE>");
            }
        }
    }

    public String getServer() {
        return server;
    }

    public DB getDb() {
        return db;
    }
}
