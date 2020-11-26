package Model.stmt;

import Model.CustomExceptions.MyException;
import Model.ProgramState;

import java.io.IOException;

public class NullStmt implements IStmt{
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
