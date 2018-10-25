package model;

import java.util.List;

/**
 * Created by mariano on 22/10/18.
 */
public class Task {


    public enum Operation {
        ADD, SUM, MUL, ABS, MEAN, PROD, NORM
    }


    private double value;
    public Operation operation;
    private SeqVector originalVector;
    private SeqVector otherVector;
    private  List<Integer> indexes;
    private int position;

    public Task(){}


    public Task(double value, Operation operation, SeqVector originalVector, SeqVector otherVector, List<Integer> indexes, int position){
        this.value = value;
        this.operation = operation;
        this.originalVector = originalVector;
        this.otherVector = otherVector;
        this.indexes = indexes;
        this.position = position;
    }


    public Task(SeqVector originalVector, List<Integer> indexes){
        this.originalVector = originalVector;
        this.indexes = indexes;
    }



    public double getValue(){
        return this.value;
    }



}
