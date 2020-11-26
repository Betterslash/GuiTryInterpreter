package Controller;

import Model.CustomExceptions.MyException;
import Model.ProgramState;
import Model.adts.IStack;
import Model.stmt.IStmt;
import Model.vals.RefValue;
import Model.vals.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public ProgramState oneStep(ProgramState state) throws MyException, IOException {
        IStack<IStmt> exeStack = state.getExeStack();
        IStmt curStmt = exeStack.pop();
        return curStmt.execute(state);
    }

    public Map<Integer, Value> garbageCollector(Set<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet()
        .stream()
        .filter(e -> symTableAddr.contains(e.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    Set<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Map<Integer, Value> heap) {
        Set<Integer> toReturn = new HashSet<>();
        symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .forEach(v -> {
                    while(v instanceof RefValue) {
                        toReturn.add(( (RefValue)v).getAddress() );
                        v = heap.get( ((RefValue)v).getAddress() );
                    }
                });
        return toReturn;
    }

    public void allStep() throws MyException, IOException {
        ProgramState state = this.repository.getCurrentPrg();
        executeAllProgram(state);
    }
    public String allStepSpecific(int i) throws MyException, IOException {
        ProgramState state = this.repository.getProgram(i);
        String s = executeAllProgram(state);
        this.repository.deleteExcutedProgram(i);
        return s;
    }

    private String executeAllProgram(ProgramState state) throws IOException {
        StringBuilder text = new StringBuilder();
        while(!state.getExeStack().isEmpty()){
            System.out.println(state);
            text.append(state);
            repository.logProgState();
            oneStep(state);
            state.getHeap().setContent(
                    garbageCollector(
                            getAddrFromSymTable(
                                    state.getSymTable().getContent().values(),
                                    state.getHeap().getContent()
                            ),
                            state.getHeap().getContent()
                    )
            );
        }
        repository.logProgState();
        System.out.println(state);
        text.append(state);
        return text.toString();
    }

    public IRepository getRepository() {
        return repository;
    }
}

