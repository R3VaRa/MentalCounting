package com.example.mentalcounting;

import java.util.Random;

public class Calculate {

    private Integer firstItem;
    private Integer secondItem;
    private TypeOperationEnum typeOperation;
    private Double result;
    private final Integer HIGH_STATION;

    public Calculate(Integer HIGH_STATION){
        this.HIGH_STATION = HIGH_STATION;
        randomAll();
    }

    public Calculate(){
        this.HIGH_STATION = 963;
        randomAll();
    }

    public void randomAll(){
        randomTypeOperation();
        randomValue();
    }

    private void randomTypeOperation(){
        switch (new Random().nextInt(3)){ // pour le moment pas de division trop compliqué a vérif en 2h de travail
            case 0:
                ajouterTypeOperation(TypeOperationEnum.ADD);
                break;
            case 1:
                ajouterTypeOperation(TypeOperationEnum.SUBSTRACT);
                break;
            case 2:
                ajouterTypeOperation(TypeOperationEnum.MULTIPLY);
                break;
            case 3:
                ajouterTypeOperation(TypeOperationEnum.DIVIDE);
                break;
        }
    }

    private Double randomValue(){

        firstItem = new Random().nextInt(HIGH_STATION);
        secondItem = new Random().nextInt(HIGH_STATION);

        result = calculResultat(firstItem,secondItem,typeOperation);

        if(result <= HIGH_STATION*2 && result >= 0){
            return result;
        }else {
            return randomValue();
        }
    }

    private void ajouterTypeOperation(TypeOperationEnum typeOperation){
        this.typeOperation=typeOperation;
    }

    private Double calculResultat(Integer fI, Integer sI, TypeOperationEnum tO){
        switch (tO.getSymbol()){
            case "+":
                return (double) (fI + sI);
            case "-":
                return (double) (fI  - sI);
            case "×":
                return (double) (fI  * sI);
            case "÷":
                return (double) (fI / sI);
        }
        return randomValue();
    }

    public boolean verification(Double inputResultat){
        return inputResultat.equals(result);
    }

    public Integer getFirstItem() {
        return firstItem;
    }

    public Integer getSecondItem() {
        return secondItem;
    }

    public String getSymbolTypeOperation() {
        return typeOperation.getSymbol();
    }
}
