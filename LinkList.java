package LinkList;

import java.io.IOException;
import java.util.Random;

public class LinkList {

    Node head;

    static class Node {

        String word;
        String numLine;
        Node next;

        Node(String d, String n) {
            word = d;
            numLine = n;
            next = null;
        }
    }

    public static LinkList insert(LinkList list, String data, String num) {

        Node newNode = new Node(data, num);
        newNode.next = null;

        if (list.head == null) {
            list.head = newNode;
        } else {

            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            last.next = newNode;
        }

        return list;
    }

    static String readLn(int maxLg) {
        {
            byte lin[] = new byte[maxLg];
            int lg = 0, car = -1;
            String line = "";
            try {
                while (lg < maxLg) {
                    car = System.in.read();
                    if ((car < 0) || (car == '\n')) {
                        break;
                    }
                    lin[lg++] += car;
                }
            } catch (IOException e) {
                return (null);
            }
            if ((car < 0) && (lg == 0)) {
                return (null);
            }
            return (new String(lin, 0, lg));
        }

    }

    static String[] array = new String[15000];
    static int numArray = 0;
    static int numLines = 0;
    public static LinkList texto() {

        LinkList list = new LinkList();
        
        long startTime = 0;
        long time = 0;
        while (true) {
            String line = readLn(5000);
            String[] l = line.split(" |\\(|\\)|\\,|\\;|\\.");
            if (line.equals("FIM.")) {
                long endTime = System.nanoTime();
                time += (endTime - startTime);
                System.out.println("Time: " + time);
                break;
            }

            int flag = 0;
            for (String l1 : l) {
                array[numArray] = l1;
                numArray++;
                startTime = System.nanoTime();
                Node currNode = list.head;
                while (currNode != null) {
                    flag = 0;
                    if (currNode.word.equals(l1)) {
                        flag = 1;
                        String[] values = currNode.numLine.split(" ");
                        int noInsert = 0;
                        for (String value : values) {
                            if (value.equals(String.valueOf(numLines))) {
                                noInsert = 1;
                            }
                        }
                        if (noInsert == 0) {
                            currNode.numLine += " " + String.valueOf(numLines);
                        }
                        break;
                    }
                    currNode = currNode.next;
                }
                if (flag == 0) {
                    insert(list, l1, String.valueOf(numLines));
                }
            }
            numLines++;
        }

        return list;
    }

    public static void main(String[] args) {

        LinkList list = new LinkList();
        String[] stats = new String[1000];
        int numStats = 0;

        while (true) {
            String comands;
            comands = readLn(400);
            String[] action = comands.split(" ");
            if (action[0].equals("TEXTO")) {
                list = texto();
                stats[numStats] = "GUARDADO.";
                numStats++;

                break;
            }
        }

        while (true) {
            String comands;
            comands = readLn(400);
            String[] action = comands.split(" ");

            if (action[0].equals("TEXTO")) {
                list = texto();
                stats[numStats] = "GUARDADO.";
                numStats++;
                continue;
            } else if (action[0].equals("LINHAS")) {
                String[] arraySec = new String[10];
                for(int i = 0; i<10; i++){
                    Random rand = new Random();
                    int index = rand.nextInt(numArray);
                    arraySec[i] = array[index];
                }
                long startTimeLines = System.nanoTime();
                for (int i = 0; i < 500; i++) {
                    Random rand = new Random();
                    //int index = rand.nextInt(numArray);
                    //action[1] = array[index];
                    
                    int index = rand.nextInt(10);
                    action[1] = arraySec[index];
                    
                    Node currNode = list.head;
                    int noExist = 0;
                    while (currNode != null) {
                        if ((currNode.word.toUpperCase()).equals(action[1].toUpperCase())) {
                            stats[numStats] = currNode.numLine;
                            numStats++;
                            noExist = 1;
                            break;
                        }
                        currNode = currNode.next;
                    }
                    if (noExist == 0) {
                        stats[numStats] = "-1";
                        numStats++;
                    }
                }
                long timeEndLines = System.nanoTime();
                long timeLines = (timeEndLines - startTimeLines);
                System.out.println("Time Lines: " + timeLines);
                //end of for 

            } else if (action[0].equals("ASSOC")) {
                
                long startTimeAssoc = System.nanoTime();
                for (int j = 0; j < 50; j++) {
                    Random rand = new Random();
                    int index = rand.nextInt(numArray);
                    action[1] = array[index];
                    action[2] = String.valueOf(rand.nextInt(numLines));
                    
                    int flag = 0;
                    Node currNode = list.head;
                    while (currNode != null) {
                        if ((currNode.word.toUpperCase()).equals(action[1].toUpperCase())) {
                            String[] values = currNode.numLine.split(" ");
                            for (int i = 0; i < values.length; i++) {
                                if (values[i].equals(action[2])) {
                                    stats[numStats] = "ENCONTRADA.";
                                    numStats++;
                                    flag = 1;
                                    break;
                                }
                            }
                        }
                        currNode = currNode.next;
                    }
                    if (flag == 0) {
                        stats[numStats] = "NAO ENCONTRADA.";
                        numStats++;
                    }
                }
                long timeEndAssoc = System.nanoTime();
                long timeAssoc = (timeEndAssoc - startTimeAssoc);
                System.out.println("Time Lines: " + timeAssoc);
                
            } else if (action[0].equals("TCHAU")) {
                for (int i = 0; i < numStats; i++) {
                    System.out.println(stats[i]);
                }
                return;
            }
        }

    }

}
