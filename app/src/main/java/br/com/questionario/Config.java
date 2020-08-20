package br.com.questionario;

public class Config {
    //DATABASE PGS
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "questionario.db";

    public static int getDatabaseVersion() { return DATABASE_VERSION; }
    public static String getDatabaseName() {
        return DATABASE_NAME;
    }
}
