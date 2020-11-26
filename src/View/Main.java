package View;

import Controller.Controller;
import Model.ProgramState;
import Model.exp.*;
import Model.stmt.*;
import Model.types.IntType;
import Model.types.RefType;
import Model.vals.IntValue;
import Repository.IRepository;
import Repository.Repository;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        IStmt ex2 = new CompStmt( new VarDeclStmt(new IntType(), "a"),
                new CompStmt(new VarDeclStmt(new IntType(), "b"),
                        new CompStmt(new AssignStmt("a", new ArithmExp('+',new ValueExp(new IntValue(2)),
                                new ArithmExp('*',new ValueExp(new IntValue(3)),
                                        new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithmExp('+',new VarExp("a"),
                                        new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        VarDeclStmt v = new VarDeclStmt(new RefType(new IntType()), "v");
        HeapAllocStmt heapAllocStmt = new HeapAllocStmt("v", new ValueExp(new IntValue(20)));
        VarDeclStmt rdc = new VarDeclStmt(new RefType(new RefType(new IntType())),"a");
        HeapAllocStmt rfc = new HeapAllocStmt("a",new VarExp("v"));
        HeapAllocStmt rfc1 = new HeapAllocStmt("v",new ValueExp(new IntValue(30)));
        CompStmt cms = new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))));
        IStmt ex3 = new CompStmt(v ,new CompStmt(heapAllocStmt, new CompStmt(rdc, new CompStmt(rfc, new CompStmt(rfc1, cms)))));
        IStmt ex4 = new CompStmt(new VarDeclStmt(new RefType(new IntType()),"v"),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithmExp('+',new ValueExp(new IntValue(5)),new ReadHeapExp(new VarExp("v"))))))));
        IStmt ex5 = new CompStmt(new VarDeclStmt(new IntType(), "v") ,
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),new CompStmt(
                        new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithmExp('-',
                                                new VarExp("v"),
                                                new ValueExp(new IntValue(1))))
                                )),new PrintStmt(new VarExp("v")))));

        ArrayList<IStmt> arrayList = new ArrayList<>();
        arrayList.add(new VarDeclStmt(new IntType(), "a"));
        arrayList.add(new AssignStmt("a",new ValueExp(new IntValue(8))));
        IStmt stmt = new MultiStmt(arrayList);

        ProgramState programState1 = new ProgramState(ex2);
        ProgramState programState2 = new ProgramState(ex3);
        ProgramState programState3 = new ProgramState(ex4);
        ProgramState programState4 = new ProgramState(ex5);
        ProgramState programState5 = new ProgramState(stmt);
        IRepository repository = new Repository("File.in");
        repository.addPrg(programState1);
        repository.addPrg(programState2);
        repository.addPrg(programState3);
        repository.addPrg(programState4);
        repository.addPrg(programState5);
        Controller controller = new Controller(repository);
        primaryStage.setTitle("Application");
        LogInScene logInStage = new LogInScene(primaryStage, controller);
        CreationScene creationScene= new CreationScene(primaryStage, controller);
        primaryStage.setScene(creationScene.scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
