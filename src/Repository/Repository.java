package Repository;

import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository implements IRepository{
    ArrayList<ProgramState> listOfPrograms;
    final String filePath;

    public Repository(String filePath) throws IOException {
        this.listOfPrograms = new ArrayList<>();
        this.filePath = filePath;
        FileWriter printWriter = new FileWriter(this.filePath, false);
        printWriter.write("");
        printWriter.close();
    }
    public ProgramState getProgram(int i){
        return this.listOfPrograms.get(i);
    }

    @Override
    public ArrayList<String> getRepresentation() {
        return this.listOfPrograms.stream()
                .map(e->e.getExeStack().toString())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ProgramState deleteExcutedProgram(int i){
        return this.listOfPrograms.remove(i);
    }
    @Override
    public void addPrg(ProgramState state) {
        this.listOfPrograms.add(state);
    }

    @Override
    public ProgramState getCurrentPrg() {
        return this.listOfPrograms.get(this.listOfPrograms.size() - 1);
    }

    public void logProgState() throws IOException {
        BufferedWriter bufferedReader = new BufferedWriter(new FileWriter(this.filePath, true));
        String print = this.listOfPrograms.get(this.listOfPrograms.size() - 1).toString();
        bufferedReader.write(print);
        bufferedReader.close();
    }
}
