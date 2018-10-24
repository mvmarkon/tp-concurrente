package model;

import java.util.List;

/**
 * Created by mariano on 22/10/18.
 */
public class TaskTP {


    public enum Operation {
        ADD, SUM, MUL, ABS, MEAN, PROD, NORM
    }


    private double value;
    public Operation operation;
    private double[] originalVector;
    private double[] otherVector;
    private  List<Integer> indexes;
    private int position;


    public TaskTP(){}


    public TaskTP(double value, Operation operation, double[] originalVector, double[] otherVector, List<Integer> indexes, int position){
        this.value = value;
        this.operation = operation;
        this.originalVector = originalVector;
        this.otherVector = otherVector;
        this.indexes = indexes;
        this.position = position;
    }


    public TaskTP(double[] originalVector, List<Integer> indexes){
        this.originalVector = originalVector;
        this.indexes = indexes;
    }



    public double getValue(){
        return this.value;
    }



}
