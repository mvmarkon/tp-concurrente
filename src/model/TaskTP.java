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
    private double[] originalVector;
    private double[] otherVector;
    private  List<Integer> indexes;
    private int position;


    public TaskTP(){}


    public TaskTP(double[] originalVector, double[] otherVector, List<Integer> indexes){
        this.originalVector = originalVector;
        this.otherVector = otherVector;
        this.indexes = indexes;
    }


    public TaskTP(double[] originalVector, List<Integer> indexes){
        this.originalVector = originalVector;
        this.indexes = indexes;
    }



    public double getValue(){
        return this.value;
    }



}
