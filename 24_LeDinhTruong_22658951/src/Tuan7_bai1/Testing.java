package Tuan7_bai1;

import java.util.Scanner;

public class Testing {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ManageProduct dom = new ManageProduct();
        int luaChon;
        boolean flag = true;

        do {
            createMenu();
            luaChon = sc.nextInt();
            sc.nextLine();

            switch (luaChon) {
                case 1:
                    Product p = createNewProduct();
                    dom.addProduct(p);
                    break;

                case 2:
                    System.out.print("Enter productID: ");
                    String id = sc.nextLine();
                    dom.deleteProduct(id);
                    break;

                case 3:
                    System.out.print("Enter productID: ");
                    id = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double price = sc.nextDouble();
                    dom.updatePrice(id, price);
                    break;

                case 4:
                    dom.printAll();
                    break;

                case 5:
                    dom.writeXMLFile();
                    break;

                case 0:
                    flag = false;
                    break;
            }
        } while (flag);
    }

    private static Product createNewProduct() {
        System.out.print("Enter productID: ");
        String id = sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter manufacture: ");
        String manu = sc.nextLine();

        System.out.print("Enter description: ");
        String des = sc.nextLine();

        Supplier s = createNewSupplier();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        return new Product(id, name, manu, des, s, price);
    }

    private static Supplier createNewSupplier() {
        System.out.print("Enter supplier name: ");
        String name = sc.nextLine();

        System.out.print("Enter country: ");
        String country = sc.nextLine();

        System.out.print("Enter website: ");
        String web = sc.nextLine();

        return new Supplier(name, country, web);
    }

    private static void createMenu() {
        System.out.println("\n======= MENU =======");
        System.out.println("1. Add product");
        System.out.println("2. Delete product");
        System.out.println("3. Update price");
        System.out.println("4. Print all");
        System.out.println("5. Write XML file");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }
}








