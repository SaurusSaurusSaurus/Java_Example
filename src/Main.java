import entities.Expense;
import entities.ExpenseCategory;
import exception.InvalidExpenseException;
import interfaces.ExpenseAmountValidator;
import interfaces.ExpenseAmountValidatorImpl;
import interfaces.ExpenseCalculator;
import interfaces.ExpenseCalculatorImpl;
import util.Utilities;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int counter = 1;

    public static void main(String[] args) throws InvalidExpenseException{

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        Double amount;
        boolean isWrongType = false;
        int cantGastosAIngresar = 0;

        ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        Map<String, Integer> countCategory = new HashMap<>();

    /*        do {
           System.out.println("Ingrese la cantidad de gastos a registrar: ");
            if(scanner.hasNextInt()){
               cantGastosAIngresar = scanner.nextInt();
           } else {
                System.out.println("Dato erróneo");
            }
        } while (isWrongType);*/
        boolean cutLogicVar;
        System.out.print("¿Desea cargar un gasto? TRUE/FALSE");
        cutLogicVar = scanner.nextBoolean();

        List<Expense> expenses = new ArrayList<>();

        while(cutLogicVar){
            Expense expense = new Expense();
            ExpenseCategory category = new ExpenseCategory();

            System.out.println("Ingrese el monto del gasto " + (index+1) + ": ");
            amount = scanner.nextDouble();

            if(!expenseAmountValidator.notValidAmount(amount)){
                System.out.println("El monto es válido");
            }

            scanner.nextLine();

            System.out.println("Ingrese la categoría del gasto: ");
            String name = scanner.nextLine().toLowerCase().trim();
            category.setName(name);

            System.out.println("Ingrese la fecha del gasto: (dd/MM/yyyy)");
            String date = scanner.nextLine();

            countCategory.put(name, countCategory.getOrDefault(name, 0)+1);

            expense.setId(counter);
            expense.setAmount(amount);
            expense.setCategory(category);
            expense.setDate(date);


            expenses.add(expense);

            counter++;
            index++;

            System.out.println("¿Desea cargar otro gasto? TRUE/FALSE: ");
            cutLogicVar = scanner.nextBoolean();
        }

        System.out.println("Total de gastos ingresados: " + expenseCalculator.calculateTotalExpense(expenses));

        System.out.println("CONTADOR POR CATEGORÍA");
        countCategory.forEach((category, count) -> System.out.println(category + " : " + count)) ;

        System.out.println("DETALLE DE GASTOS INGRESADOS");
        Utilities.printElements(expenses);

    }
}