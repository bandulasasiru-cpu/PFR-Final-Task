import java.util.*;
class Customer {
    private String customerID;
    private double billValue;

    Customer(String customerID, double billValue) {
        this.customerID = customerID;
        this.billValue = billValue;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getBillValue() {
        return billValue;
    }

    public void setBillValue(double billValue) {
        this.billValue = billValue;
    }
}

class Task {
    public static int inputDetails(Customer customers[]) {
        Scanner input = new Scanner(System.in);
        int count = 0;

        while (true) {

            System.out.print("Enter Customer ID (Enter -1 to stop) : ");
            String customerID = input.next();

            if (customerID.equals("-1")) {
                break;
            }

            System.out.print("Enter Bill Value : ");
            double billValue = input.nextDouble();

            customers[count] = new Customer(customerID, billValue);

            count++;
        }

        return count;
    }

    public static int calculateTotal(
            Customer customers[],
            int count,
            Customer total[]) {

        int customerCount = 0;

        for (int i = 0; i < count; i++) {
            int index = -1;
            for (int j = 0; j < customerCount; j++) {
                if (total[j].getCustomerID()
                        .equals(customers[i].getCustomerID())) {

                    index = j;
                    break;
                }
            }

            if (index == -1) {
                total[customerCount] = new Customer(
                        customers[i].getCustomerID(),
                        customers[i].getBillValue()
                    );

                customerCount++;

            } else {
                total[index].setBillValue(
                    total[index].getBillValue()
                    + customers[i].getBillValue()
                );
            }
        }

        return customerCount;
    }

    public static void sortCustomers(
            Customer customers[],
            int customerCount) {

        for (int i = 0; i < customerCount - 1; i++) {
            for (int j = 0;
                    j < customerCount - i - 1;
                    j++) {
                if (customers[j].getBillValue()
                        < customers[j + 1].getBillValue()) {

                    Customer temp = customers[j];

                    customers[j] = customers[j + 1];

                    customers[j + 1] = temp;
                }
            }
        }
    }
    public static void displayReport(
            Customer customers[],
            int customerCount) {

        System.out.println();
        System.out.println("Customer ID    |  Total Bill Value");
        System.out.println( "-------------------------------------" );

        for (int i = 0; i < customerCount; i++) {
            System.out.printf("%-15s|%15.0f%n",
                customers[i].getCustomerID(),
                customers[i].getBillValue()
            );
        }
    }

    public static void main(String args[]) {
        Customer customers[] =new Customer[100];
        int count =inputDetails(customers);
        Customer total[] =new Customer[count];
        int customerCount =calculateTotal(customers,count,total);

        sortCustomers(total,customerCount);
        displayReport( total, customerCount);
    }
}
