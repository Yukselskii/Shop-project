package store.util;

import store.model.Receipt;

import java.io.*;

public class FileUtil {
    private static final String DIR = "receipts";

    static {
        new File(DIR).mkdirs();

    }

    public static void saveReceipt(Receipt receipt) throws IOException {
        String fileName = String.format("%s/receipt_%d.txt", DIR, receipt.getNumber());
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.print(receipt.toString());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                String.format("%s/receipt_%d.ser", DIR, receipt.getNumber())))) {
            oos.writeObject(receipt);
        }
    }

    public static Receipt loadReceipt(long number) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                String.format("%s/receipt_%d.ser", DIR, number)))) {
            return (Receipt) ois.readObject();
        }
    }

}
