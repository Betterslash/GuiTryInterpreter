package Repository;

import Model.ProgramState;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepository {
    void addPrg(ProgramState state);
    ProgramState getCurrentPrg();
    void logProgState() throws IOException;
    ProgramState deleteExcutedProgram(int i);
    ProgramState getProgram(int i);
    ArrayList<String> getRepresentation();
}
