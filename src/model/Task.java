package model;

import java.util.List;

/**
 * Created by mariano on 22/10/18.
 */
public class Task {

    public enum Operation {
        SET, ADD, SUM, MUL, ABS, MEAN, PROD, NORM, ASSIGN, ASSIGN_MASK
    }


    private double value;
    public Operation operation;
    private SeqVector originalVector;
    private SeqVector otherVector;
    private  List<Integer> indexes;
    private int position;
    private SeqVector auxVector;
    private double result;


    public Task(){}


    public Task(Operation operation, SeqVector originalVector, int position){
        // this.value = value;
        this.operation = operation;
        this.originalVector = originalVector;
        //this.otherVector = otherVector;
        //this.indexes = indexes;
        this.position = position;
    }

    public Task(SeqVector originalVector, List<Integer> indexes){
        this.originalVector = originalVector;
        this.indexes = indexes;
    }


    public double getResult() {
        return result;
    }

    public void setResult(double res) {
        this.result = res;
    }

    public void setAuxVector(SeqVector auxVector){
        this.auxVector = auxVector;
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setOtherVector(SeqVector otherVector) {
        this.otherVector = otherVector;
    }

    public SeqVector getOriginalVector() {
        return originalVector;
    }

    public int getPosition() {
        return position;
    }


    public void set() {
        this.originalVector.set(this.value);
    }


    public void add() {
        this.originalVector.add(this.otherVector);
    }

    public void sum() {
        this.setResult(this.originalVector.sum());
    }

    public void mul() {
        this.originalVector.mul(this.otherVector);
    }

    public void abs() {
        this.originalVector.abs();
    }

    public void mean() {
    }

    public void norm() {
    }

    public void prod() {
    }

    public void assign(){
        this.originalVector.assign(this.otherVector);
    }


    public void assignWithMask(){
        this.originalVector.assign(this.auxVector, this.otherVector);
    }

}
