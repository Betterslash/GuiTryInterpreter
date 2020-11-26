package Model.stmt;

import Model.CustomExceptions.MyException;
import Model.ProgramState;
import Model.adts.IStack;

import java.util.ArrayList;

public class MultiStmt implements IStmt{
    final ArrayList<IStmt> stmtArrayList;
    final ArrayList<IStmt> stmtArrayList1;
    CompStmt compStmt;
    public MultiStmt(ArrayList<IStmt> stmtArrayList) {
        this.stmtArrayList = stmtArrayList;
        this.stmtArrayList1 = (ArrayList<IStmt>) stmtArrayList.clone();
    }

    public IStmt makeStmt(){
        if(this.stmtArrayList1.size() > 0) {
            IStmt stmt = this.stmtArrayList1.get(this.stmtArrayList1.size() - 1);
            this.stmtArrayList1.remove(this.stmtArrayList1.size() - 1);
            compStmt = new CompStmt(makeStmt(), stmt);
        }
        else {
            return new NullStmt();
        }
        return compStmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        IStack<IStmt> stack = state.getExeStack();
        IStmt stmt = this.makeStmt();
        stack.push(stmt);
        return state;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(IStmt stmt: this.stmtArrayList){
            stringBuilder.append(stmt).append(";");
        }
        return stringBuilder.toString();
    }
}
